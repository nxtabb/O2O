package com.hrbeu.O2O.controller.superadminController;//package com.hrbeu.O2O.controller.superadminController;

import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getarea",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getArea(){
        logger.info("====start====");
        long startTime = System.currentTimeMillis();
        Map<String,Object> modelMap= new HashMap<>();
        List<Area> areas = null;
        try {
            areas = areaService.getAreaList();
            modelMap.put("rows",areas);
            modelMap.put("total",areas.size());
        }
        catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
        logger.error("test error");
        long endTime = System.currentTimeMillis();
        logger.debug("costTime=[{}ms]",endTime-startTime);
        logger.info("====end====");
        return modelMap;

    }
}
