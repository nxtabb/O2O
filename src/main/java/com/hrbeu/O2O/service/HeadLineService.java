package com.hrbeu.O2O.service;

import com.hrbeu.O2O.Pojo.HeadLine;

import java.util.List;

public interface HeadLineService {
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
