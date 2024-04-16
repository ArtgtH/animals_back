package com.animals_back.aspects.logging;

import com.animals_back.aspects.ColorsForLogs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingRestService {
    @Before("com.animals_back.aspects.pointcuts.RestServicePointcuts.getAnimalByIdPointcut().getAnimalById()")
    public void beforeGetAnimalAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.printf("%s: Попытка вернуть зверя с id %d.\n", joinPoint.getSignature(), (int) args[0]);
    }

    @AfterReturning(pointcut = "com.animals_back.aspects.pointcuts.RestServicePointcuts.getAnimalByIdPointcut()", returning = "result")
    public void afterReturningGetAnimalAdvice(JoinPoint joinPoint, ResponseEntity<?> result) {
        Object[] args = joinPoint.getArgs();

        if (result.getStatusCode() == HttpStatus.OK) {
            System.out.printf(ColorsForLogs.GREEN + "%s: Зверек с id %d был успешно отправлен в запросе!\n" + ColorsForLogs.RESET,
                    joinPoint.getSignature(), (int) args[0]);
        } else if (result.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.printf(ColorsForLogs.RED + "%s: Зверек с id %d не найден. Зверек не был передан в запросе.\n" + ColorsForLogs.RESET,
                    joinPoint.getSignature(), (int) args[0]);
        }
    }
    //---------------------------------------------------------------//
    @Before("com.animals_back.aspects.pointcuts.RestServicePointcuts.getAllAnimalsPointcut()")
    public void beforeGetAllAnimals(JoinPoint joinPoint) {
        System.out.printf("%s: Попытка вернуть всех зверей.\n", joinPoint.getSignature());
    }

    @AfterReturning("com.animals_back.aspects.pointcuts.RestServicePointcuts.getAllAnimalsPointcut()")
    public void afterReturningGetAllAnimals(JoinPoint joinPoint) {
        System.out.printf(ColorsForLogs.GREEN + "%s: Все зверьки были успешно отправлены в запросе!\n" + ColorsForLogs.RESET,
                joinPoint.getSignature());
    }

    //---------------------------------------------------------------//
}
