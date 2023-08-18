package com.senai.laziot.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.util.JedisURIHelper;

@Component
public class JedisConnection {

    private static final String USER     = "default";
    private static final String PASSWORD = "laziot1132";
    private static final String PORT     = "6379";
    private static final String ADDRESSREDISSERVICE = "localhost";

    public JedisConnection(){
        connectRedis();
    }

    public Jedis connectRedis(){
        try {
            Jedis jedis = new Jedis("redis://"+USER+":"+PASSWORD+"@"+ADDRESSREDISSERVICE+":"+PORT);
            jedis.connect();
            return jedis;
        }catch(Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
