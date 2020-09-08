package com.hrbeu.O2O.dao;


import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest {
    @Autowired AreaDao areaDao;


    @Test
    public void test1(){
        List<Area> areas = areaDao.queryArea();
        for(Area area:areas){
            System.out.println(area.getAreaName());
            System.out.println(area.getPriority());
        }
    }
}
