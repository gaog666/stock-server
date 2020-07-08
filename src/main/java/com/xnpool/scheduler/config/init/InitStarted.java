package com.xnpool.scheduler.config.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
public class InitStarted implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args){
        log.debug("启动。。。");
    }
}
