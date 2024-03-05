package com.redshiftsoft.tesla.web.mvc.site;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.redshiftsoft.tesla.dao.site.Stalls;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StallsDTO {
    
    private Integer urban;
    private Integer v2;
    private Integer v3;
    private Integer v4;
    private Integer trailerFriendly;

    public StallsDTO nullIfEmpty() {
        return urban == null && v2 == null && v3 == null && v4 == null && trailerFriendly == null ? null : this;
    }

    public Integer getUrban() {
        return urban;
    }
    public void setUrban(Integer urban) {
        this.urban = urban;
    }

    public Integer getV2() {
        return v2;
    }
    public void setV2(Integer v2) {
        this.v2 = v2;
    }

    public Integer getV3() {
        return v3;
    }
    public void setV3(Integer v3) {
        this.v3 = v3;
    }

    public Integer getV4() {
        return v4;
    }
    public void setV4(Integer v4) {
        this.v4 = v4;
    }

    public Integer getTrailerFriendly() {
        return trailerFriendly;
    }
    public void setTrailerFriendly(Integer trailerFriendly) {
        this.trailerFriendly = trailerFriendly;
    }

}
