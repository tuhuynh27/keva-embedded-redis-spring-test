package dev.keva.redistest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {TestRedisConfiguration.class})
public class RedisRepositoryIntegrationTest {
    @Autowired
    private RedisRepository repository;

    @Test
    public void shouldSaveDeveloper_toRedis() {
        Developer developer = Developer.builder()
                .name("Keva")
                .age(18)
                .language("Java")
                .build();
        repository.save(developer);

        // Test the repository is working fine
        assert repository.findById(developer.getName()).isPresent();
        assert repository.findById(developer.getName()).get().getAge() == 18;
        assert repository.findById(developer.getName()).get().getLanguage().equals("Java");
    }
}
