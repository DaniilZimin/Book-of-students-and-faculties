package ru.hogwarts.school.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

@Component
@Aspect
public class Audit {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    /**
     * Метод для логирования вызовов всех остальных методов программы
     *
     * @param joinPoint
     */
    @Before("execution(* ru.hogwarts.school.service.impl.*.*(..))")
    public void loggerInfo(JoinPoint joinPoint){
        logger.info("Был вызван метод с названием: {}", joinPoint.getSignature().getName());
    }

}
