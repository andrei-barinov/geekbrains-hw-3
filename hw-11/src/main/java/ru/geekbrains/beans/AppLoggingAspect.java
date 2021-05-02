package ru.geekbrains.beans;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.geekbrains.dto.CartDTO;

@Aspect
@Component
@Slf4j
public class AppLoggingAspect {

    @Before("execution(public * ru.geekbrains.controllers.CartController.addToCart(..))")
    public void beforeAddToCartMethodInCartControllersClassWithDetails(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Был вызван метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        if(args.length > 0) {
            log.info("Аргументы: ");
            for(Object o: args){
                log.info(o.toString());
            }
        }

    }

    @AfterReturning(
            pointcut = "execution(public * ru.geekbrains.controllers.CartController.getCart())",
    returning = "result")
    public void afterGetCartMethodInCartControllersClass(JoinPoint joinPoint, CartDTO result){
        log.info(result.toString());
    }


}
