package com.springboot.datasource.aspect;

import com.springboot.datasource.annotation.DynamicDb;
import com.springboot.datasource.annotation.MasterDb;
import com.springboot.datasource.annotation.SlaveDb;
import com.springboot.datasource.config.datasource.DbContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DbAspect implements Ordered {
    private static final Logger logger = LoggerFactory.getLogger(DbAspect.class);

    @Before("@annotation(masterDb)")
    public void beforeSwitch(JoinPoint joinPoint, MasterDb masterDb){
        System.out.println("进入之前");
    }

    @Around("@annotation(masterDb)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, MasterDb masterDb) throws Throwable{
        try {
            logger.info("set database connection to db_test only");
            DbContextHolder.setDbType(DbContextHolder.DbType.MASTER);
            Object result = proceedingJoinPoint.proceed();
            return result;

        }finally {
            DbContextHolder.clearDbType();
            logger.info("restore database connection");
        }
    }

    @Around("@annotation(slaveDb)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, SlaveDb slaveDb) throws Throwable{
        try {
            logger.info("set database connection to db_test only");
            DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
            Object result = proceedingJoinPoint.proceed();
            return result;
        }finally {
            DbContextHolder.clearDbType();
            logger.info("restore database connection");
        }
    }
    @Around("@annotation(dynamicDb)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, DynamicDb dynamicDb) throws Throwable{
        try {
            logger.info("set database connection to {} only",dynamicDb.value());
            DbContextHolder.setDb(dynamicDb.value());
            Object result = proceedingJoinPoint.proceed();
            return result;
        }finally {
            DbContextHolder.clearDb();
            logger.info("restore database connection");
        }
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
