package dev.keva.redistest;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Developer")
@Data
@Builder
public class Developer implements Serializable {
    @Id
    private String name;
    private int age;
    private String language;
}
