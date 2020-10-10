package com.kenny.demo.oslodemowithjavajpa.clientfeign.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.kenny.demo.oslodemowithjavajpa.clientfeign"})
public class OpenFeignConfig {
}
