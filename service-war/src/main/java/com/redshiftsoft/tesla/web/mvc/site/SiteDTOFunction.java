package com.redshiftsoft.tesla.web.mvc.site;

import com.redshiftsoft.tesla.dao.site.Site;
import com.redshiftsoft.util.StringTools;

import java.util.function.Function;

public class SiteDTOFunction implements Function<Site, SiteDTO> {

    @Override
    public SiteDTO apply(Site site) {
        SiteDTO siteDTO = new SiteDTO();

        siteDTO.setId(site.getId());
        siteDTO.setLocationId(site.getLocationId());
        siteDTO.setName(site.getName());
        siteDTO.setStatus(site.getStatus());
        siteDTO.setAddress(AddressDTOFunctions.transform(site.getAddress()));
        siteDTO.setGps(site.getGps());
        siteDTO.setUrlDiscuss(StringTools.isNotEmpty(site.getUrlDiscuss()));
        siteDTO.setDateOpened(site.getDateOpened());
        siteDTO.setStallCount(site.getStallCount());
        siteDTO.setHours(site.getHours());
        siteDTO.setCounted(site.isCounted());
        siteDTO.setEnabled(site.isEnabled());
        siteDTO.setElevationMeters(site.getElevationMeters());
        siteDTO.setPowerKilowatt(site.getPowerKilowatt());
        siteDTO.setSolarCanopy(site.isSolarCanopy());
        siteDTO.setBattery(site.isBattery());
        siteDTO.setOtherEVs(site.isOtherEVs());
        siteDTO.setStallsUrban(site.getStallsUrban());
        siteDTO.setStallsV2(site.getStallsV2());
        siteDTO.setStallsV3(site.getStallsV3());
        siteDTO.setStallsV4(site.getStallsV4());
        siteDTO.setStallsTrailerFriendly(site.getStallsTrailerFriendly());
        siteDTO.setPlugsTeslaUS(site.getPlugsTeslaUS());
        siteDTO.setPlugsType2(site.getPlugsType2());
        siteDTO.setPlugsType2CCS2(site.getPlugsType2CCS2());
        siteDTO.setPlugsCCS2(site.getPlugsCCS2());
        siteDTO.setPlugsGBTChina(site.getPlugsGBTChina());
        siteDTO.setPlugsNACS(site.getPlugsNACS());
        siteDTO.setPaidParking(site.isPaidParking());
        siteDTO.setFacilityName(site.getFacilityName());
        siteDTO.setFacilityHours(site.getFacilityHours());
        siteDTO.setAccessNotes(site.getAccessNotes());
        siteDTO.setAddressNotes(site.getAddressNotes());
        siteDTO.setPlugshareId(site.getPlugshareId());
        siteDTO.setOsmId(site.getOsmId());

        return siteDTO;
    }

}
