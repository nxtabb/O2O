package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.HeadLine;
import com.hrbeu.O2O.dao.HeadLineDao;
import com.hrbeu.O2O.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    HeadLineDao headLineDao;
    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
