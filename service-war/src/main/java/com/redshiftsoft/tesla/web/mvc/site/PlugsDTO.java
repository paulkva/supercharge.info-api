package com.redshiftsoft.tesla.web.mvc.site;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlugsDTO {

    private Integer TPC;
    private Integer NACS;
    private Integer MagicDock;
    private Integer GBTChina;
    private Integer Type2;
    private Integer CCS2;
    private Integer CCS2Type2;
    private Integer CCS2TPC;

    public PlugsDTO nullIfEmpty() {
        return TPC == null && NACS == null && MagicDock == null && GBTChina == null && Type2 == null && CCS2 == null && CCS2Type2 == null && CCS2TPC == null ? null : this;
    }

    public Integer getTPC() {
        return TPC;
    }
    public void setTPC(Integer TPC) {
        this.TPC = TPC;
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

    public Integer getGBTChina() {
        return GBTChina;
    }
    public void setGBTChina(Integer GBTChina) {
        this.GBTChina = GBTChina;
    }

    public Integer getType2() {
        return Type2;
    }
    public void setType2(Integer Type2) {
        this.Type2 = Type2;
    }

    public Integer getCCS2() {
        return CCS2;
    }
    public void setCCS2(Integer CCS2) {
        this.CCS2 = CCS2;
    }

    public Integer getCCS2Type2() {
        return CCS2Type2;
    }
    public void setCCS2Type2(Integer CCS2Type2) {
        this.CCS2Type2 = CCS2Type2;
    }

    public Integer getCCS2TPC() {
        return CCS2TPC;
    }
    public void setCCS2TPC(Integer CCS2TPC) {
        this.CCS2TPC = CCS2TPC;
    }

}
