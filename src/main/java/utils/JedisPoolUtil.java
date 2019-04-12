package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {

    private static JedisPool pool;


    static {
        //jedis配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(1000);//最大空闲
        config.setMaxTotal(10240);//最大连接数
        if(pool == null){//config：配置参数； 6379：默认端口号，可以更改；e_learning：密码
            pool = new JedisPool(config, "123.207.137.208", 12002, 0);
        }
    }
    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnJedis(Jedis jedis) {
        pool.returnResourceObject(jedis);
    }
}
