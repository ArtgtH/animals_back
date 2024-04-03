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
public class LoggingRest {
    @Before("com.animals_back.aspects.pointcuts.RestPointcuts.getAnimalById().getAnimalById()")
    public void beforeGetAnimalAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Logging.RestController.getAnimalById: Попытка получить зверя с id "
                + args[0] + ".");
    }

    @AfterReturning(pointcut = "com.animals_back.aspects.pointcuts.RestPointcuts.getAnimalById()", returning = "result")
    public void afterReturningGetAnimalAdvice(JoinPoint joinPoint, ResponseEntity<?> result) {
        Object[] args = joinPoint.getArgs();
        Integer animalId = (Integer) args[0];

        if (result.getStatusCode() == HttpStatus.OK) {
            System.out.println(ColorsForLogs.GREEN + "Logging.RestController.getAnimalById: Зверек с id "
                    + animalId + " был успешно получен!" + ColorsForLogs.RESET);
        } else if (result.getStatusCode() == HttpStatus.NOT_FOUND) {
            System.out.println(ColorsForLogs.RED + "Logging.RestController.getAnimalById: Зверек с id "
                    + animalId + " не найден." + ColorsForLogs.RESET);
        }
    }
}
