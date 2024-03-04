package com.redshiftsoft.tesla.dao.site;

import com.redshiftsoft.tesla.dao.LocalDateUtil;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static com.redshiftsoft.tesla.dao.DAOTools.string;

public class SiteUpdateStatementCreator implements PreparedStatementCreator {

    private static final String SQL = "update site " +
            "set location_id=?,name=?,status=?::SITE_STATUS_TYPE,opened_date=?,hours=?,enabled=?,counted=?," +
            "gps_latitude=?,gps_longitude=?,elevation_meters=?,url_discuss=?,stall_count=?,power_kwatt=?," +
            "has_solar_canopy=?,has_battery=?,developer_notes=?,modified_date=now(),version=version+1,other_evs=?," +
            "stalls_urban=?,stalls_v2=?,stalls_v3=?,stalls_v4=?,stalls_trailer=?," +
            "plugs_tesla_us=?,plugs_type2=?,plugs_type2_ccs2=?,plugs_ccs2=?,plugs_gbt_china=?,plugs_nacs=?," +
            "paid_parking=?,facility_name=?,facility_hours=?,access_notes=?,address_notes=?,plugshare_id=?,osm_id=?" +
            " where site_id=?";

    private final Site site;

    public SiteUpdateStatementCreator(Site site) {
        this.site = site;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement stat = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);

        int c = 1;
        stat.setString(c++, string(site.getLocationId()));
        stat.setString(c++, string(site.getName()));

        stat.setString(c++, site.getStatus().toString());

        stat.setTimestamp(c++, LocalDateUtil.toSQLDate(site.getDateOpened()));
        stat.setString(c++, string(site.getHours()));
        stat.setBoolean(c++, site.isEnabled());

        stat.setBoolean(c++, site.isCounted());

        stat.setDouble(c++, site.getGps().getLatitude());
        stat.setDouble(c++, site.getGps().getLongitude());

        stat.setInt(c++, site.getElevationMeters());

        stat.setString(c++, string(site.getUrlDiscuss()));

        stat.setInt(c++, site.getStallCount());

        stat.setInt(c++, site.getPowerKilowatt());
        stat.setBoolean(c++, site.isSolarCanopy());
        stat.setBoolean(c++, site.isBattery());
        stat.setString(c++, string(site.getDeveloperNotes()));
        stat.setBoolean(c++, site.isOtherEVs());

        // use setObject() instead of type-specific setX() for better null handling
        stat.setObject(c++, site.getStalls().getUrban(), Types.INTEGER);
        stat.setObject(c++, site.getStalls().getV2(), Types.INTEGER);
        stat.setObject(c++, site.getStalls().getV3(), Types.INTEGER);
        stat.setObject(c++, site.getStalls().getV4(), Types.INTEGER);
        stat.setObject(c++, site.getStalls().getTrailerFriendly(), Types.INTEGER);

        stat.setObject(c++, site.getPlugs().getTeslaUS(), Types.INTEGER);
        stat.setObject(c++, site.getPlugs().getType2(), Types.INTEGER);
        stat.setObject(c++, site.getPlugs().getType2CCS2(), Types.INTEGER);
        stat.setObject(c++, site.getPlugs().getCCS2(), Types.INTEGER);
        stat.setObject(c++, site.getPlugs().getGBTChina(), Types.INTEGER);
        stat.setObject(c++, site.getPlugs().getNACS(), Types.INTEGER);

        stat.setObject(c++, site.isPaidParking(), Types.BOOLEAN);
        stat.setString(c++, string(site.getFacilityName()));
        stat.setString(c++, string(site.getFacilityHours()));
        stat.setString(c++, string(site.getAccessNotes()));
        stat.setString(c++, string(site.getAddressNotes()));
        stat.setObject(c++, site.getPlugshareId(), Types.BIGINT);
        stat.setObject(c++, site.getOsmId(), Types.BIGINT);
        
        stat.setInt(c, site.getId());

        return stat;
    }


}
