package com.redshiftsoft.tesla.web.mvc.country;

import com.redshiftsoft.tesla.dao.site.Country;

import java.util.function.Function;

public class CountryDTOFunction implements Function<Country, CountryDTO> {

    @Override
    public CountryDTO apply(Country country) {
        CountryDTO countryDTO = new CountryDTO();

        countryDTO.setId(country.getId());
        countryDTO.setName(country.getName());
        countryDTO.setCode(country.getCode());
        countryDTO.setRegionId(country.getRegionId());
        countryDTO.setRegion(country.getRegionName());
        countryDTO.setPlugTPC(country.isPlugTPC());
        countryDTO.setPlugNACS(country.isPlugNACS());
        countryDTO.setPlugMagicDock(country.isPlugMagicDock());
        countryDTO.setPlugGBTChina(country.isPlugGBTChina());
        countryDTO.setPlugType2(country.isPlugType2());
        countryDTO.setPlugCCS2(country.isPlugCCS2());
        countryDTO.setPlugCCS2Type2(country.isPlugCCS2Type2());
        countryDTO.setPlugCCS2TPC(country.isPlugCCS2TPC());

        return countryDTO;
    }

}
