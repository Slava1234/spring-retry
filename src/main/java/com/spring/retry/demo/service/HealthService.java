package com.spring.retry.demo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthService {
    private RestTemplate restTemplate = new RestTemplate();

    private int attempt;

    public void clearAttempt() {
        this.attempt = 0;
    }


    @Retryable(
            // Количество попыток
            maxAttempts = 3,
            // Что нужно ловить
            value = RuntimeException.class,
            // Через сколько времени повторить попытку, (пол секунды)
            // и на сколько умножить заданное время в случе неудачи (на два, то есть 1 сек.)
            backoff = @Backoff(delay = 100, multiplier = 2)
    )
    public String getHealth() {
        this.attempt++;
        return restTemplate.getForObject("http://localhost:8080/health", String.class) + " - " + this.attempt;
    }


    /**
     * Если все 10 попыток оказались неудачей
     * @return
     */
    @Recover
    public String recover() {
        return "Not ok";
    }

}
