package com.vengeang.learnredis.service;

import java.time.Duration;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vengeang.learnredis.entity.User;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;

@Service
public class RateLimitingService {
    @Autowired
    private UserService userService;

    @Autowired
    ProxyManager<String> proxyManager;

    public Bucket resolveBucket(String key){
        Supplier<BucketConfiguration> configSupplier = getConfigSupplier(key);
        return proxyManager.builder().build(key, configSupplier);
    }

    private Supplier<BucketConfiguration> getConfigSupplier(String apiKey){
       User user = userService.getUser(apiKey);
       Refill refill = Refill.intervally(user.getLimitToken(), Duration.ofMinutes(1));
       Bandwidth limit = Bandwidth.classic(user.getLimitToken(), refill);
       return ()-> (BucketConfiguration.builder().addLimit(limit).build());
    }
}
