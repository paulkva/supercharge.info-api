package com.redshiftsoft.tesla.web.mvc.site;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlugsDTO {

    private Integer TeslaUS;
    private Integer Type2;
    private Integer Type2CCS2;
    private Integer CCS2;
    private Integer GBTChina;
    private Integer NACS;
    private Integer MagicDock;

    public PlugsDTO nullIfEmpty() {
        return TeslaUS == null && Type2 == null && Type2CCS2 == null && CCS2 == null && GBTChina == null && NACS == null && MagicDock == null ? null : this;
    }

    public Integer getTeslaUS() {
        return TeslaUS;
    }
    public void setTeslaUS(Integer TeslaUS) {
        this.TeslaUS = TeslaUS;
    }

    public Integer getType2() {
        return Type2;
    }
    public void setType2(Integer Type2) {
        this.Type2 = Type2;
    }

    public Integer getType2CCS2() {
        return Type2CCS2;
    }
    public void setType2CCS2(Integer Type2CCS2) {
        this.Type2CCS2 = Type2CCS2;
    }

    public Integer getCCS2() {
        return CCS2;
    }
    public void setCCS2(Integer CCS2) {
        this.CCS2 = CCS2;
    }

    public Integer getGBTChina() {
        return GBTChina;
    }
    public void setGBTChina(Integer GBTChina) {
        this.GBTChina = GBTChina;
    }

    public Integer getNACS() {
        return NACS;
    }
    public void setNACS(Integer NACS) {
        this.NACS = NACS;
    }

    public Integer getMagicDock() {
        return MagicDock;
    }
    public void setMagicDock(Integer MagicDock) {
        this.MagicDock = MagicDock;
    }

}
