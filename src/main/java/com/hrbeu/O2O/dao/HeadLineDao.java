package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.Pojo.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {

    List<HeadLine> queryHeadLine(@Param("headLineCondition")HeadLine headLineCondition);
}
