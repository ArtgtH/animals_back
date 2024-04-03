package com.animals_back.aspects.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingRest {
    @Before("com.animals_back.aspects.pointcuts.RestPointcuts.getAnimalById().getAnimalById()")
    public void beforeGetAnimalAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Logging.RestController.getAnimalById: Попытка получить зверя с id " + args[0]);
    }
}
