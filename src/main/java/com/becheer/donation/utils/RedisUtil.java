package com.becheer.donation.utils;

import org.thymeleaf.expression.Maps;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* RedisUtil redis 工具类
* Creator : xiaokepu
* Date : 2017-10-19
*/
public class RedisUtil extends RedisProvider{

    public static String SetKey(String key, String value) {
        Jedis jedis = null;
        String rtn = null;
        try {
            jedis = GetJedis();
            rtn = jedis.setex(key, EXPIRE, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
        return rtn;
    }

    public static String GetKey(String key) {
        Jedis jedis = null;
        String rtn = null;
        try {
            jedis = GetJedis();
            rtn = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
        return rtn;
    }


    public static void DelKey(String key) {
        Jedis jedis = null;
        try {
            jedis = GetJedis();
            jedis.del(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
    }

    public static void Flush() {
        Jedis jedis = null;
        jedis = GetJedis();
        jedis.flushAll();
    }

    public static long DelKeyArray(String[] key) {
        Jedis jedis = null;
        Long rtn = null;
        try {
            jedis = GetJedis();
            rtn = jedis.del(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
        return rtn;
    }
}