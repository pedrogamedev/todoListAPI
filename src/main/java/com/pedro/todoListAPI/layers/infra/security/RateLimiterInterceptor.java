package com.pedro.todoListAPI.layers.infra.security;

import com.pedro.todoListAPI.miscelaneous.exceptions.NoTokensLeftException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private final Bucket bucket;


    public RateLimiterInterceptor() {
        bucket = Bucket.builder().addLimit(Bandwidth.builder().capacity(10L).refillGreedy(2L, Duration.ofSeconds(20)).build()).build();
    }


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if(probe.isConsumed()){
            return true;
        }
        else{

            response.setStatus(429);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"" + "No tokens left." + "\"}");
            response.flushBuffer();
            return false;
        }
    }

}
