package com.redshiftsoft.tesla.dao.site;

import com.redshiftsoft.tesla.dao.LocalDateUtil;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlTypeValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import static com.redshiftsoft.tesla.dao.DAOTools.string;

public class SiteInsertStatementCreator implements PreparedStatementCreator {

    private static final String SQL = "insert into site values (DEFAULT,?,?,?::SITE_STATUS_TYPE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final Site site;

    public SiteInsertStatementCreator(Site site) {
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

        stat.setInt(c++, site.getAddress().getId());

        stat.setDouble(c++, site.getGps().getLatitude());
        stat.setDouble(c++, site.getGps().getLongitude());

        stat.setInt(c++, site.getElevationMeters());

        stat.setString(c++, string(site.getUrlDiscuss()));

        stat.setInt(c++, site.getStallCount());

        stat.setInt(c++, site.getPowerKilowatt());
        stat.setBoolean(c++, site.isSolarCanopy());
        stat.setBoolean(c++, site.isBattery());
        stat.setString(c++, string(site.getDeveloperNotes()));
        stat.setInt(c++, site.getVersion());
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

        return stat;
    }


}
