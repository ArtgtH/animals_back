package com.animals_back.aspects.logging;

import com.animals_back.aspects.ColorsForLogs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAnimalService {
    @Before("com.animals_back.aspects.pointcuts.AnimalServicePointcuts.findAnimalByIdPointcut()")
    public void beforeFindAnimalAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.printf("%s: Попытка найти зверя с id %d в базе данных.\n", joinPoint.getSignature(), (int) args[0]);
    }

    @AfterReturning("com.animals_back.aspects.pointcuts.AnimalServicePointcuts.findAnimalByIdPointcut()")
    public void afterReturningFindAnimal(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.printf(ColorsForLogs.GREEN + "%s: Зверек с id %d был успешно найден базе и передан дальше!\n" + ColorsForLogs.RESET,
                joinPoint.getSignature(), (int) args[0]);
    }

    @AfterThrowing("com.animals_back.aspects.pointcuts.AnimalServicePointcuts.findAnimalByIdPointcut()")
    public void afterThrowingFindAnimal(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.printf(ColorsForLogs.RED + "%s: Зверек с id %d не был найден.\n" + ColorsForLogs.RESET,
                joinPoint.getSignature(), (int) args[0]);
    }

    //---------------------------------------------------------------//

//    @Before("com.animals_back.aspects.pointcuts.AnimalServicePointcuts.getAllAnimalsPointcut()")
//    public void beforeGetAllAnimals(JoinPoint joinPoint) {
//        System.out.printf("%s: Попытка взять всех зверей из базы.\n", joinPoint.getSignature());
//    }
//
//    @AfterReturning("com.animals_back.aspects.pointcuts.AnimalServicePointcuts.getAllAnimalsPointcut()")
//    public void afterReturningGetAllAnimals(JoinPoint joinPoint) {
//        System.out.printf(ColorsForLogs.GREEN + "%s: Все зверьки были успешно получены из базы и переданы дальше!\n" + ColorsForLogs.RESET,
//                joinPoint.getSignature());
//    }

    //---------------------------------------------------------------//
}
