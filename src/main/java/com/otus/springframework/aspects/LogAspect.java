package com.otus.springframework.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private Logger aspectLogger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryClassMethods() {
    }

    @Pointcut("execution(* com.otus.springframework.services.SalaryService.*(..))")
    public void serviceClassMethods() {
    }

    @Before("repositoryClassMethods() || serviceClassMethods()")
    public void logMethodBeforeCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class declaringType = jp.getSignature().getDeclaringType();
        Logger logger = LoggerFactory.getLogger(declaringType);
        logger.info(">> Method {} was called with parameters {}", methodName, jp.getArgs());
    }

    @After("repositoryClassMethods() || serviceClassMethods()")
    public void logMethodAfterCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Class declaringType = jp.getSignature().getDeclaringType();
        Logger logger = LoggerFactory.getLogger(declaringType);
        logger.info("<< Method {} was called with parameters {}", methodName, jp.getArgs());
    }

//    @Around("repositoryClassMethods() || serviceClassMethods()")
//    public Object around(ProceedingJoinPoint jp) throws Throwable {
//        Signature signature = jp.getSignature();
//        String name = signature.getName();
//        Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
//        logger.info(">> Method {} was called with parameters {}", name, jp.getArgs());
//
//        try {
//            Object result = jp.proceed();
//            logger.info("<< Method {} has returned result {}", name, result);
//            return result;
//
//        } catch (Exception ex) {
//            logger.error("<X Method {} has thrown an exception result {}", name, ex);
//            throw ex;
//        }
//    }
}
