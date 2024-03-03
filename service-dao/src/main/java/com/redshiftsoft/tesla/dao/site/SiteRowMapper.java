package com.redshiftsoft.tesla.dao.site;

import com.redshiftsoft.tesla.dao.LocalDateUtil;
import com.redshiftsoft.util.StringTools;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteRowMapper implements RowMapper<Site> {

    public static final String SELECT_ALL = "" +
            "SELECT site.*, address.*, country.name, region.region_id, region.name " +
            "FROM site " +
            "JOIN address ON site.address_id = address.address_id " +
            "JOIN country ON address.country_id = country.country_id " +
            "JOIN region ON country.region_id = region.region_id";

    public static final String SELECT_ENABLED = SELECT_ALL + " WHERE enabled = TRUE";

    @Override
    public Site mapRow(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site();

        int c = 1;
        site.setId(rs.getInt(c++));
        site.setLocationId(rs.getString(c++));
        site.setName(rs.getString(c++));
        String statusString = rs.getString(c++);
        site.setStatus(StringTools.isEmpty(statusString) ? null : SiteStatus.valueOf(statusString.trim()));

        site.setDateOpened(LocalDateUtil.toLocalDate(rs.getTimestamp(c++)));
        site.setHours(rs.getString(c++));
        site.setEnabled(rs.getBoolean(c++));
        site.setCounted(rs.getBoolean(c++));

        c++; // address_id

        site.setGps(new SiteGPS(rs.getDouble(c++), rs.getDouble(c++)));

        site.setElevationMeters(rs.getInt(c++));
        site.setUrlDiscuss(rs.getString(c++));

        site.setStallCount(rs.getInt(c++));

        site.setPowerKilowatt(rs.getInt(c++));
        site.setSolarCanopy(rs.getBoolean(c++));
        site.setBattery(rs.getBoolean(c++));
        site.setDeveloperNotes(rs.getString(c++));

        site.setDateModified(LocalDateUtil.toLocalDateTime(rs.getTimestamp(c++)));
        site.setVersion(rs.getInt(c++));
        site.setOtherEVs(rs.getBoolean(c++));

        site.setAddress(new AddressRowMapper(c++).mapRow(rs, rowNum));

        // Used typed getObject() instead of specific getX() for better null handling
        site.setStallsUrban(rs.getObject(c++, Integer.class));
        site.setStallsV2(rs.getObject(c++, Integer.class));
        site.setStallsV3(rs.getObject(c++, Integer.class));
        site.setStallsV4(rs.getObject(c++, Integer.class));
        site.setStallsTrailerFriendly(rs.getObject(c++, Integer.class));

        site.setPlugsTeslaUS(rs.getObject(c++, Integer.class));
        site.setPlugsType2(rs.getObject(c++, Integer.class));
        site.setPlugsType2CCS2(rs.getObject(c++, Integer.class));
        site.setPlugsCCS2(rs.getObject(c++, Integer.class));
        site.setPlugsGBTChina(rs.getObject(c++, Integer.class));
        site.setPlugsNACS(rs.getObject(c++, Integer.class));

        site.setPaidParking(rs.getObject(c++, Boolean.class));
        site.setFacilityName(rs.getString(c++));
        site.setFacilityHours(rs.getString(c++));
        site.setAccessNotes(rs.getString(c++));
        site.setAddressNotes(rs.getString(c++));
        site.setPlugshareId(rs.getObject(c++, Long.class));
        site.setOsmId(rs.getObject(c++, Long.class));

        return site;
    }

}
