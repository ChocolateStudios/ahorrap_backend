package com.chocolatestudios.ahorrapp.contexts._shared.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Before("execution(* com.chocolatestudios.ahorrapp.contexts.*.controllers.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("***********************************************************");
        logger.info("Executing method: {} on endpoint: {}", joinPoint.getSignature().getName(), request.getRequestURI());
        logger.info("***********************************************************");
    }

    @AfterReturning(pointcut = "execution(* com.chocolatestudios.ahorrapp.contexts.*.controllers.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("***********************************************************");
        logger.info("Method executed: " + joinPoint.getSignature().getName() + ", resultado: " + result);
        logger.info("***********************************************************");
    }

    @AfterThrowing(pointcut = "execution(* com.chocolatestudios.ahorrapp.contexts.*.controllers.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("***********************************************************");
        logger.error("Exception in method: {} on endpoint: {}", joinPoint.getSignature().getName(), ex.getMessage());
        logger.error("***********************************************************");
    }
}
