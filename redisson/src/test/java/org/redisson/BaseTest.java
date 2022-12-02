package org.redisson;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import java.net.URL;

public abstract class BaseTest {
    
    protected static RedissonClient redisson;

    @BeforeAll
    public static void beforeClass() throws IOException, InterruptedException {
        RedisRunner.startDefaultRedisServerInstance();
        redisson = createInstance();
    }

    @AfterAll
    public static void afterClass() throws InterruptedException {
        redisson.shutdown();
        RedisRunner.shutDownDefaultRedisServerInstance();
    }

    @BeforeEach
    public void before() throws IOException, InterruptedException {
        if (flushBetweenTests()) {
            redisson.getKeys().flushall();
        }
    }

    public static Config createConfig() {
//        String redisAddress = System.getProperty("redisAddress");
//        if (redisAddress == null) {
//            redisAddress = "127.0.0.1:6379";
//        }
        
//        config.setCodec(new MsgPackJacksonCodec());
//        config.useSentinelServers().setMasterName("mymaster").addSentinelAddress("127.0.0.1:26379", "127.0.0.1:26389");
//        config.useClusterServers().addNodeAddress("127.0.0.1:7004", "127.0.0.1:7001", "127.0.0.1:7000");

//        .setPassword("mypass1");
//        config.useMasterSlaveConnection()
//        .setMasterAddress("127.0.0.1:6379")
//        .addSlaveAddress("127.0.0.1:6399")
//        .addSlaveAddress("127.0.0.1:6389");
        try {
            URL url = BaseTest.class.getResource("ctest-injection.yaml");
            System.out.println(url);
            Config cfg = Config.fromYAML(url);
            System.out.println("[ctest] Ctest value injected!!");
            cfg.useSingleServer()
                .setAddress(RedisRunner.getDefaultRedisServerBindAddressAndPort());
            return cfg;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // Config config = new Config();
        // config.useSingleServer()
        //         .setAddress(RedisRunner.getDefaultRedisServerBindAddressAndPort());
        // System.out.println("!!!!using default");
        // return config;
    }

    public static RedissonClient createInstance() {
        Config config = createConfig();
        return Redisson.create(config);
    }

    protected boolean flushBetweenTests() {
        return true;
    }
}
