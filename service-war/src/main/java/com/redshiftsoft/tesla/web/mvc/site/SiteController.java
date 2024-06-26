package com.redshiftsoft.tesla.web.mvc.site;

import com.redshiftsoft.tesla.dao.changelog.ChangeLogDAO;
import com.redshiftsoft.tesla.dao.dbinfo.DBInfoDAO;
import com.redshiftsoft.tesla.dao.site.Site;
import com.redshiftsoft.tesla.dao.site.SiteStatus;
import com.redshiftsoft.tesla.dao.site.SiteDAO;
import com.redshiftsoft.tesla.dao.sitestallcount.SiteStallCountDAO;
import com.redshiftsoft.tesla.web.mvc.CachingHandler;
import com.redshiftsoft.tesla.web.mvc.PageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class SiteController {

    private static final Logger LOG = Logger.getLogger(SiteController.class.getName());

    @Resource
    private SiteDAO siteDAO;
    @Resource
    private ChangeLogDAO changeLogDAO;
    @Resource
    private DBInfoDAO dbInfoDAO;
    @Resource
    private SiteStallCountDAO siteStallCountDAO;

    private CachingHandler<SiteDTO> cachingHandler;

    @PostConstruct
    public void postConstruct() {
        cachingHandler = new CachingHandler<>(dbInfoDAO, "site", new SiteWithStatusDaysSupplier());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/allSites")
    @ResponseBody
    @Transactional
    public List<SiteDTO> allSites() {
        return cachingHandler.getValues();
    }

    /**
     * Service for paging through results.
     * <p>
     * Parameter names are those required by datatables.net (https://datatables.net/manual/server-side).
     * <p>
     * No parameters are required.  I specify defaultValue in the @RequestParam where a null default value is not
     * allowed.
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/sites")
    @ResponseBody
    public PageDTO<SiteDTO> pageDataTables(
        @RequestParam(required = false) Integer draw,
        @RequestParam(required = false, defaultValue = "0") Integer start,
        @RequestParam(required = false, defaultValue = "20") Integer length,
        @RequestParam(required = false) Integer regionId,
        @RequestParam(required = false) Integer countryId,
        @RequestParam(required = false) List<String> state,
        @RequestParam(required = false) List<SiteStatus> status,
        @RequestParam(required = false) Integer stalls,
        @RequestParam(required = false) Integer power,
        @RequestParam(required = false) List<String> stallType,
        @RequestParam(required = false) List<String> plugType,
        @RequestParam(required = false) List<Integer> parking,
        @RequestParam(required = false) List<Integer> openTo,
        @RequestParam(required = false) Boolean otherEVs,
        @RequestParam(required = false) Boolean solarCanopy,
        @RequestParam(required = false) Boolean battery,
        @RequestParam(required = false) String search,
        @RequestParam(required = false, defaultValue = "false") Boolean anyWord,
        @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Integer orderCol,
        @RequestParam(required = false, value = "order[0][dir]") String orderDir
    ) {
        List<SiteDTO> allList = cachingHandler.getValues();

        List<SiteDTO> filteredList = allList
                .stream()
                .sorted(SiteDTOComparatorFactory.build(orderCol, orderDir))
                .filter(s -> regionId == null || Objects.equals(s.getAddress().getRegionId(), regionId))
                .filter(s -> countryId == null || Objects.equals(s.getAddress().getCountryId(), countryId))
                .filter(s -> state == null || state.isEmpty() || state.contains(s.getAddress().getState()))
                .filter(s -> status == null || status.isEmpty() || status.contains(s.getStatus()) ||
                            (s.getStatus() == SiteStatus.EXPANDING && status.contains(SiteStatus.OPEN)))
                .filter(s -> stalls == null || s.getStallCount() >= stalls)
                .filter(s -> power == null || s.getPowerKilowatt() >= power)
                .filter(s -> stallType == null || stallType.isEmpty() ||
                            (s.getStalls() != null && s.getStalls().matches(String.join(" ", stallType), true)))
                .filter(s -> plugType == null || plugType.isEmpty() ||
                            (s.getPlugs() != null && s.getPlugs().matches(String.join(" ", plugType), true)))
                .filter(s -> s.hasParking(parking))
                .filter(s -> s.isOpenTo(openTo))
                // TODO: remove "other EVs" boolean filter in favor of "open to" above
                .filter(s -> otherEVs == null || s.isOtherEVs() == otherEVs)
                .filter(s -> solarCanopy == null || s.isSolarCanopy() == solarCanopy)
                .filter(s -> battery == null || s.isBattery() == battery)
                .filter(s -> search == null || s.matches(search, anyWord))
                .collect(Collectors.toList());

        List<SiteDTO> pageList = filteredList.stream()
                .skip(start)
                .limit(length)
                .collect(Collectors.toList());

        return new PageDTO<>(draw, length, filteredList.size(), allList.size(), pageList);
    }


    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/discuss")
    public String siteDiscuss(@RequestParam("siteId") Integer siteId,
                              HttpServletResponse response) {
        if (siteDAO.exists(siteId)) {
            Site site = siteDAO.getById(siteId);
            LOG.info(String.format("DISCUSS REDIRECT: siteId=%s, name=%s", siteId, site.getName()));
            return "redirect:" + site.getUrlDiscuss();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "";
        }
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/site/stallCount")
    @ResponseBody
    public List<StallCountDTO> stallCounts() {
        return siteStallCountDAO.getCounts()
                .stream().map(x -> new StallCountDTO(x.getDate(), x.getStallCount()))
                .collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, value = "/siteHistory")
    @ResponseBody
    public List<SiteHistoryDTO> siteHistory(@RequestParam("siteId") Integer siteId) {
        return changeLogDAO.getSiteList(siteId).entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(e -> new SiteHistoryDTO(e.getKey(), e.getValue().getSiteStatus(), e.getValue().getStallCount()))
                .collect(Collectors.toList());
    }

    /**
     * Supplies a list of Sites, but with the statusDays property of each set.
     */
    private class SiteWithStatusDaysSupplier implements Supplier<List<SiteDTO>> {

        @Override
        public List<SiteDTO> get() {
            Map<Integer, Integer> statusDays = changeLogDAO.getStatusDaysMap();

            List<SiteDTO> dtos = siteDAO.getAllSites().stream()
                    .map(new SiteDTOFunction())
                    .collect(Collectors.toList());

            dtos.stream()
                    .filter(site -> statusDays.containsKey(site.getId()))
                    .forEach(site -> site.setStatusDays(statusDays.get(site.getId())));

            return dtos;
        }
    }

}
