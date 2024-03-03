package com.redshiftsoft.tesla.web.mvc.siteadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redshiftsoft.tesla.dao.site.SiteGPS;
import com.redshiftsoft.tesla.dao.site.SiteStatus;
import com.redshiftsoft.tesla.web.json.LocalDateDeserializer;
import com.redshiftsoft.tesla.web.json.LocalDateSerializer;
import com.redshiftsoft.tesla.web.json.LocalDateTimeSerializer;
import com.redshiftsoft.tesla.web.mvc.site.AddressDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SiteEditDTO {

    private int id;
    private String locationId;
    private String name;
    private SiteStatus status = SiteStatus.OPEN;
    private AddressDTO address = new AddressDTO();
    private SiteGPS gps;
    private String urlDiscuss;
    private LocalDate dateOpened;
    private LocalDateTime dateModified;
    private int version;
    private int stallCount;
    private String hours;
    private boolean counted;
    private boolean enabled;
    private Integer elevationMeters;
    private String developerNotes;

    /* Power in kW */
    private int powerKiloWatt;
    private NotifyEnum notify;
    private boolean solarCanopy;
    private boolean battery;
    private boolean otherEVs;

    private Integer stallsUrban, stallsV2, stallsV3, stallsV4, stallsTrailerFriendly;
    private Integer plugsTeslaUS, plugsType2, plugsType2CCS2, plugsCCS2, plugsGBTChina, plugsNACS;

    private Boolean paidParking;
    private String facilityName, facilityHours, accessNotes, addressNotes;
    private Long plugshareId, osmId;

    // - - - - - - - - - - - - - - - - - - - - - - -
    // java.lang.Object
    // - - - - - - - - - - - - - - - - - - - - - - -

    @Override
    public String toString() {
        return "Site{" +
                "id='" + id + "'" +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", address=" + address +
                ", gps=" + gps +
                ", elevationMeters=" + elevationMeters +
                ", locationId='" + locationId + '\'' +
                ", urlDiscuss='" + urlDiscuss + '\'' +
                ", dateOpened=" + dateOpened +
                ", enabled=" + enabled +
                ", stallCount=" + stallCount +
                ", hours=" + hours +
                ", count=" + counted +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        return (this == object) || (object instanceof SiteEditDTO) && this.getIdentityFields().equals(((SiteEditDTO) object).getIdentityFields());
    }

    @Override
    public int hashCode() {
        return getIdentityFields().hashCode();
    }

    private List<?> getIdentityFields() {
        return Collections.singletonList(id);
    }

    // - - - - - - - - - - - - - - - - - - - - - - -
    // derived fields
    // - - - - - - - - - - - - - - - - - - - - - - -

    @JsonIgnore
    public boolean isOpen() {
        return getStatus().isOpen();
    }

    // - - - - - - - - - - - - - - - - - - - - - - -
    // getters/setters
    // - - - - - - - - - - - - - - - - - - - - - - -

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public SiteStatus getStatus() {
        return status;
    }
    public void setStatus(SiteStatus status) {
        this.status = status;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate getDateOpened() {
        return dateOpened;
    }
    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getDateModified() {
        return dateModified;
    }
    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public AddressDTO getAddress() {
        return address;
    }
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public SiteGPS getGps() {
        return gps;
    }
    public void setGps(SiteGPS gps) {
        this.gps = gps;
    }

    public Integer getElevationMeters() {
        return elevationMeters;
    }
    public void setElevationMeters(Integer elevationMeters) {
        this.elevationMeters = elevationMeters;
    }

    public String getUrlDiscuss() {
        return urlDiscuss;
    }
    public void setUrlDiscuss(String urlDiscuss) {
        this.urlDiscuss = urlDiscuss;
    }

    public int getStallCount() {
        return stallCount;
    }
    public void setStallCount(int stallCount) {
        this.stallCount = stallCount;
    }

    public boolean isCounted() {
        return counted;
    }
    public void setCounted(boolean counted) {
        this.counted = counted;
    }

    // Enabled is not part of our web service, only enabled sites are served by the web service.
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHours() {
        return hours;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public int getPowerKiloWatt() {
        return powerKiloWatt;
    }
    public void setPowerKiloWatt(int powerKiloWatt) {
        this.powerKiloWatt = powerKiloWatt;
    }

    public NotifyEnum getNotify() {
        return notify;
    }
    public void setNotify(NotifyEnum notify) {
        this.notify = notify;
    }

    public boolean isSolarCanopy() {
        return solarCanopy;
    }
    public void setSolarCanopy(boolean solarCanopy) {
        this.solarCanopy = solarCanopy;
    }

    public boolean isBattery() {
        return battery;
    }
    public void setBattery(boolean battery) {
        this.battery = battery;
    }

    public String getDeveloperNotes() {
        return developerNotes;
    }
    public void setDeveloperNotes(String developerNotes) {
        this.developerNotes = developerNotes;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isOtherEVs() {
        return otherEVs;
    }
    public void setOtherEVs(boolean otherEVs) {
        this.otherEVs = otherEVs;
    }

    public Integer getStallsUrban() {
        return stallsUrban;
    }
    public void setStallsUrban(Integer stallsUrban) {
        this.stallsUrban = stallsUrban;
    }

    public Integer getStallsV2() {
        return stallsV2;
    }
    public void setStallsV2(Integer stallsV2) {
        this.stallsV2 = stallsV2;
    }

    public Integer getStallsV3() {
        return stallsV3;
    }
    public void setStallsV3(Integer stallsV3) {
        this.stallsV3 = stallsV3;
    }

    public Integer getStallsV4() {
        return stallsV4;
    }
    public void setStallsV4(Integer stallsV4) {
        this.stallsV4 = stallsV4;
    }

    public Integer getStallsTrailerFriendly() {
        return stallsTrailerFriendly;
    }
    public void setStallsTrailerFriendly(Integer stallsTrailerFriendly) {
        this.stallsTrailerFriendly = stallsTrailerFriendly;
    }

    public Integer getPlugsTeslaUS() {
        return plugsTeslaUS;
    }
    public void setPlugsTeslaUS(Integer plugsTeslaUS) {
        this.plugsTeslaUS = plugsTeslaUS;
    }

    public Integer getPlugsType2() {
        return plugsType2;
    }
    public void setPlugsType2(Integer plugsType2) {
        this.plugsType2 = plugsType2;
    }

    public Integer getPlugsType2CCS2() {
        return plugsType2CCS2;
    }
    public void setPlugsType2CCS2(Integer plugsType2CCS2) {
        this.plugsType2CCS2 = plugsType2CCS2;
    }

    public Integer getPlugsCCS2() {
        return plugsCCS2;
    }
    public void setPlugsCCS2(Integer plugsCCS2) {
        this.plugsCCS2 = plugsCCS2;
    }

    public Integer getPlugsGBTChina() {
        return plugsGBTChina;
    }
    public void setPlugsGBTChina(Integer plugsGBTChina) {
        this.plugsGBTChina = plugsGBTChina;
    }

    public Integer getPlugsNACS() {
        return plugsNACS;
    }
    public void setPlugsNACS(Integer plugsNACS) {
        this.plugsNACS = plugsNACS;
    }

    public Boolean isPaidParking() {
        return paidParking;
    }
    public void setPaidParking(Boolean paidParking) {
        this.paidParking = paidParking;
    }

    public String getFacilityName() {
        return facilityName;
    }
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityHours() {
        return facilityHours;
    }
    public void setFacilityHours(String facilityHours) {
        this.facilityHours = facilityHours;
    }

    public String getAccessNotes() {
        return accessNotes;
    }
    public void setAccessNotes(String accessNotes) {
        this.accessNotes = accessNotes;
    }

    public String getAddressNotes() {
        return addressNotes;
    }
    public void setAddressNotes(String addressNotes) {
        this.addressNotes = addressNotes;
    }

    public Long getPlugshareId() {
        return plugshareId;
    }
    public void setPlugshareId(Long plugshareId) {
        this.plugshareId = plugshareId;
    }

    public Long getOsmId() {
        return osmId;
    }
    public void setOsmId(Long osmId) {
        this.osmId = osmId;
    }

    public enum NotifyEnum {
        yes, log, no
    }
}


