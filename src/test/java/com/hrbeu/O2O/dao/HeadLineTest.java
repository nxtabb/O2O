package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.HeadLine;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineTest extends BaseTest {
    @Autowired HeadLineDao headLineDao;
    @Test
    public void test01(){
        HeadLine headLine = new HeadLine();
        List<HeadLine> headLines = headLineDao.queryHeadLine(headLine);
        Assert.assertEquals(headLines.size(),1);
    }
}
