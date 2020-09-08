package com.hrbeu.O2O.service;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void test01(){
        List<Area> areas= areaService.getAreaList();
        for(Area area:areas){
            System.out.println(area.getAreaId());
        }

    }
}
