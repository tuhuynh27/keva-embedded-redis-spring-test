package dev.keva.redistest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private final RedisRepository redisRepository;

    @GetMapping("/init")
    public ResponseEntity<Response> init() {
        Developer developer = Developer.builder()
                .name("Keva")
                .age(18)
                .language("java")
                .build();
        redisRepository.save(developer);
        return ResponseEntity.ok(new Response("OK"));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(new Response(redisRepository.findAll()));
    }

    @GetMapping("/insert")
    public ResponseEntity<Response> insert() {
        Developer developer = Developer.builder()
                .name(getRandomString(10))
                .age(18)
                .language(getRandomString(4))
                .build();
        redisRepository.save(developer);
        return ResponseEntity.ok(new Response("OK"));
    }

    @AllArgsConstructor
    public static class Response {
        public Object result;
    }

    private String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * 62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
