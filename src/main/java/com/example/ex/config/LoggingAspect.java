package com.example.ex.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.*;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger l=LogManager.getLogger(LoggingAspect.class);
    @After("execution(* com.example.ex.service.serviceExamenImpl.*(..))")
    public void logMethodeExit(JoinPoint joinPoint) {
        l.info("methode executed");
    }
    @Before("execution(* com.example.ex.service.serviceExamenImpl.*(..))")
    public void logMethodeEntry(JoinPoint joinPoint) {
        l.info("methode executed");
    }
    @Around("execution(* com.example.ex.service.serviceExamenImpl.*(..))")
    public Object profile(ProceedingJoinPoint proceedingJoinPoin) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj =proceedingJoinPoin.proceed();
        long elapsedTime = System.currentTimeMillis()-start;
        l.info("Arround methode : execution time : " + elapsedTime + " ms");
        return obj;
    }

}
