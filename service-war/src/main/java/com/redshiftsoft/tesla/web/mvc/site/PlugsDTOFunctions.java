package com.redshiftsoft.tesla.web.mvc.site;

import com.redshiftsoft.tesla.dao.site.Plugs;

import java.util.function.Function;

public class PlugsDTOFunctions {

    private static final PlugsFunction PLUGS_FUNCTION = new PlugsFunction();
    private static final PlugsDTOFunction PLUGS_DTO_FUNCTION = new PlugsDTOFunction();

    public static Plugs transform(PlugsDTO plugsDTO) {
        return PLUGS_FUNCTION.apply(plugsDTO);
    }

    public static PlugsDTO transform(Plugs plugs) {
        return PLUGS_DTO_FUNCTION.apply(plugs);
    }

    private static class PlugsDTOFunction implements Function<Plugs, PlugsDTO> {

        @Override
        public PlugsDTO apply(Plugs plugs) {
            if (plugs == null) return null;

            PlugsDTO plugsDTO = new PlugsDTO();

            plugsDTO.setTPC(plugs.getTPC());
            plugsDTO.setNACS(plugs.getNACS());
            plugsDTO.setMagicDock(plugs.getMagicDock());
            plugsDTO.setGBTChina(plugs.getGBTChina());
            plugsDTO.setType2(plugs.getType2());
            plugsDTO.setCCS2(plugs.getCCS2());
            plugsDTO.setCCS2Type2(plugs.getCCS2Type2());
            plugsDTO.setCCS2TPC(plugs.getCCS2TPC());

            return plugsDTO.nullIfEmpty();
        }
    }

    private static class PlugsFunction implements Function<PlugsDTO, Plugs> {

        @Override
        public Plugs apply(PlugsDTO plugsDTO) {
            if (plugsDTO == null) return null;

            Plugs plugs = new Plugs();

            plugs.setTPC(plugsDTO.getTPC());
            plugs.setNACS(plugsDTO.getNACS());
            plugs.setMagicDock(plugsDTO.getMagicDock());
            plugs.setGBTChina(plugsDTO.getGBTChina());
            plugs.setType2(plugsDTO.getType2());
            plugs.setCCS2(plugsDTO.getCCS2());
            plugs.setCCS2Type2(plugsDTO.getCCS2Type2());
            plugs.setCCS2TPC(plugsDTO.getCCS2TPC());

            return plugs.nullIfEmpty();
        }
    }

}