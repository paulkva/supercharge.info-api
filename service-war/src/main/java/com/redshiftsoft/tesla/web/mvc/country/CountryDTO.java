package com.redshiftsoft.tesla.web.mvc.country;

/**
 * Differences from Country class:
 * <ul>
 * <li>- isStateRequired field</li>
 * </ul>
 */
public class CountryDTO {

    private int id;
    private String name;
    private String code;
    private String region;
    private int regionId;

    private boolean plugTPC;
    private boolean plugNACS;
    private boolean plugMagicDock;
    private boolean plugGBTChina;
    private boolean plugType2;
    private boolean plugCCS2;
    private boolean plugCCS2Type2;
    private boolean plugCCS2TPC;

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

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public int getRegionId() {
        return regionId;
    }
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public boolean isPlugTPC() {
        return plugTPC;
    }
    public void setPlugTPC(boolean plugTPC) {
        this.plugTPC = plugTPC;
    }

    public boolean isPlugNACS() {
        return plugNACS;
    }
    public void setPlugNACS(boolean plugNACS) {
        this.plugNACS = plugNACS;
    }

    public boolean isPlugMagicDock() {
        return plugMagicDock;
    }
    public void setPlugMagicDock(boolean plugMagicDock) {
        this.plugMagicDock = plugMagicDock;
    }

    public boolean isPlugGBTChina() {
        return plugGBTChina;
    }
    public void setPlugGBTChina(boolean plugGBTChina) {
        this.plugGBTChina = plugGBTChina;
    }

    public boolean isPlugType2() {
        return plugType2;
    }
    public void setPlugType2(boolean plugType2) {
        this.plugType2 = plugType2;
    }

    public boolean isPlugCCS2() {
        return plugCCS2;
    }
    public void setPlugCCS2(boolean plugCCS2) {
        this.plugCCS2 = plugCCS2;
    }

    public boolean isPlugCCS2Type2() {
        return plugCCS2Type2;
    }
    public void setPlugCCS2Type2(boolean plugCCS2Type2) {
        this.plugCCS2Type2 = plugCCS2Type2;
    }

    public boolean isPlugCCS2TPC() {
        return plugCCS2TPC;
    }
    public void setPlugCCS2TPC(boolean plugCCS2TPC) {
        this.plugCCS2TPC = plugCCS2TPC;
    }

}