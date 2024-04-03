package com.animals_back.aspects.pointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class RestPointcuts {
    @Pointcut("execution(public * getAnimalById(Integer))")
    public void getAnimalById() {}
}
