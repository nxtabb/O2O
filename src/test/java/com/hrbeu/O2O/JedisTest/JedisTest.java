package com.hrbeu.O2O.JedisTest;


import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hrbeu.O2O.BaseTest;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisTest extends BaseTest {
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("119.3.191.202",6379);
        jedis.set("per","{name:'nxt',age:'10'}");
        String per = jedis.get("per");
        System.out.println(per);
    }
}
