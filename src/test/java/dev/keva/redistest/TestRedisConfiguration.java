package dev.keva.redistest;

import dev.keva.core.config.KevaConfig;
import dev.keva.core.server.KevaServer;
import org.springframework.boot.test.context.TestConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class TestRedisConfiguration {
    private KevaServer redisServer;

    public TestRedisConfiguration() {
        KevaConfig kevaConfig = KevaConfig.builder()
                .hostname("localhost")
                .port(6379)
                .persistence(false)
                .aof(false)
                .workDirectory("./")
                .build();
        this.redisServer = KevaServer.of(kevaConfig);
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.run();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.shutdown();
    }
}
