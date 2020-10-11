package com.kenny.demo.oslodemowithjavajpa.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.kenny.demo.oslodemowithjavajpa.common.constant.CommonConstant.OSLO_DEMO_ROOT_PACKAGE;

@SpringBootApplication(scanBasePackages = OSLO_DEMO_ROOT_PACKAGE)
public class HubApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubApplication.class, args);
    }

}
