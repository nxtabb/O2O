package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.dao.AreaDao;
import com.hrbeu.O2O.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
