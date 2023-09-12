package com.example.spring_boot_security.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${aspect.timing.enabled: false}")
public class ExecutionTimeAspect {

    @Around("@annotation(com.example.spring_boot_security.aop.ExecutionTime)")
    public Object exec(ProceedingJoinPoint point) throws Throwable {
        ExecutionTime annotation =
                ((MethodSignature) point.getSignature()).getMethod().getAnnotation(ExecutionTime.class);
        if (annotation.timeUnit() == TimeUnit.NANOSECONDS) {
            long start = System.nanoTime();
            Object proceed = point.proceed();
            long end = System.nanoTime();
            log.info("Execution time method: {} class {} - {} nanos",
                    point.getSignature().getName(),
                    point.getSignature().getDeclaringType().getSimpleName(),
                    (end - start));
            return proceed;
        } else {
            long start = System.currentTimeMillis();
            Object proceed = point.proceed();
            long end = System.currentTimeMillis();
            log.info("Execution time method: {} class {} - {} ms",
                    point.getSignature().getName(),
                    point.getSignature().getDeclaringType().getSimpleName(),
                    (end - start));
            return proceed;
        }
    }
}
