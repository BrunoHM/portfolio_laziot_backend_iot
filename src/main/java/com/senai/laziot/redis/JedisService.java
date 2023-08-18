package com.senai.laziot.redis;

import com.senai.laziot.event.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Service
public class JedisService {

    @Autowired
    JedisConnection jedisConnection;

    @Autowired
    private EventoService eventoService;

    public void saveDeviceEventPendency(Long idEvent, String uniqueDeviceCode) {
        jedisConnection = new JedisConnection();

        Jedis jedis = jedisConnection.connectRedis();
        jedis.hset(String.valueOf(idEvent), uniqueDeviceCode,"true"); //ex: 115, C8:2B:96:2F:C7:11, true
        jedis.close();
    }

    public boolean updateDeviceEventPendency(String idEvent, String uniqueDeviceCode) {
        Jedis jedis = jedisConnection.connectRedis();
        jedis.hset(idEvent, uniqueDeviceCode,"false"); //ex: 115, C8:2B:96:2F:C7:11, true
        jedis.close();

        return checkDeviceEventPendency(idEvent);
    }

    private boolean checkDeviceEventPendency(String idEvent) {
        Jedis jedis = jedisConnection.connectRedis();
        boolean eventExecutedByDevices = true;
        Map<String, String> mapStatusDevicesEvent = jedis.hgetAll(idEvent);

        for(String pending : mapStatusDevicesEvent.values()){
            if(pending.equals("true")){
                //Pendência de execução encontrada
                eventExecutedByDevices = false;
                break;
            }
        }

        jedis.close();

        if(eventExecutedByDevices) {
            return eventoService.updateEventToExecuted(idEvent);
        }
        return false;
    }

}
