package com.springboot.datasource.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestB {

    private static final Logger logger = LoggerFactory.getLogger(TestB.class);

    @Async(value = "executor")
    public void insertB(){
        logger.info("BBBBBBBBBBBBB");
        int a = 1 / 0;
    }
}
