package com.redshiftsoft.tesla.dao.site;

import com.redshiftsoft.tesla.dao.DAOConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DAOConfiguration.class)
public class RegionDAO_UT {

    @Resource
    private RegionDAO regionDAO;

    @Test
    public void getAll() {
        List<Region> allRegions = regionDAO.getAll();

        assertEquals(4, allRegions.size());
        assertEquals("Asia Pacific", allRegions.get(0).getName());
        assertTrue(allRegions.get(0).getId() > 0);
    }

    @Test
    public void getById() {
        Region region = regionDAO.getById(100);
        assertEquals(100, region.getId());
        assertEquals("North America", region.getName());
    }

}