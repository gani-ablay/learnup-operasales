package ru.learnup.vtb.spring.boot.operasales.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;

@Component
@Aspect
public class NotifierAspect {

    @Pointcut("@annotation(ru.learnup.vtb.spring.boot.operasales.annotations.TicketBuyNotified)")
    public void TicketServiceNotifier() {

    }

    @Around("TicketServiceNotifier()")
    public void aroundTicketBuy(ProceedingJoinPoint point) {
        Object[] args = point.getArgs().clone();
        print("Инициирована процедура покупки билета " + args[1].toString() + " на оперу \"" + args[0].toString() + "\"");

        try {
            point.proceed();
            print("Произведена покупка билета " + args[1].toString() + " на оперу \"" + args[0].toString() + "\"");
        } catch (Throwable throwable) {
            print("Не удалось произвести покупку билета " + args[1].toString() + " на оперу \"" + args[0].toString() + "\"");
            throwable.printStackTrace();
        }
    }

    @Pointcut("execution(* ru.learnup.vtb.spring.boot.operasales.services.OperaService.addOpera (..))")
    public void addOperaNotifier() {

    }

    @Around("addOperaNotifier()")
    public void aroundOperaAdd(ProceedingJoinPoint point) {
        Object[] args = point.getArgs().clone();
        Opera opera = (Opera) args[0];
        print("Подготовка анонса #" + opera.getId()  + " для оперы \"" + opera.getName() + "\"");

        try {
            point.proceed();
            print("Произведен анонс " + opera.getId() + " на оперу \"" + opera.getName() + "\"");
        } catch (Throwable throwable) {
            print("Не удалось анонсировать " + opera.getId() + " оперу \"" + opera.getName() + "\"");
            throwable.printStackTrace();
        }
    }

    @Pointcut("execution(* ru.learnup.vtb.spring.boot.operasales.services.OperaService.editOpera (..))")
    public void editOperaNotifier() {

    }

    @Around("editOperaNotifier()")
    public void aroundOperaEdit(ProceedingJoinPoint point) {
        Object[] args = point.getArgs().clone();

        print("Подготовка к редактированию премьеры \"" + args[0] + "\"");

        try {
            point.proceed();
            print("Произведено редактирование премьеры \"" + args[0] + "\"");
        } catch (Throwable throwable) {
            print("Не удалось редактировать премьеру \"" + args[0] + "\"");
            throwable.printStackTrace();
        }
    }

    public void print(String msg) {
        System.out.println("Уведомление: " + msg);
    }
}
