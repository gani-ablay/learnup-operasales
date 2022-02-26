package ru.learnup.vtb.spring.boot.operasales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.vtb.spring.boot.operasales.model.EnAgeCategory;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.services.interfaces.Logger;

import java.util.Map;

@Service
//@Scope("prototype")
@Scope("singleton")
public class OperaService {

    private Map<String, Opera> operaMap;
    private Logger logger;

    @Autowired
    public OperaService(Logger logger, Map operaMap) {
        this.logger = logger;
        this.operaMap = operaMap;
    }

    public Opera getOperaByName(String name) {
        Opera opera = operaMap.get(name);
        return opera;
    }

    public Map<String, Opera> getAllOpera() {
        return operaMap;
    }

    public void printOperaByName(String name) {
        logger.print("Выводим оперу \"" + name + "\"");
        System.out.println(operaMap.get(name));
    }

    public void printAllOpera() {
        logger.print("Выводим все оперы: ");
        System.out.println(operaMap.keySet().toString());
    }

    public void addOpera(Opera opera) {
        if (this.operaMap.containsKey(opera.getName())) {
            logger.print("Редактируем оперу \"" + opera.getName() + "\"");
        } else {
            logger.print("Добавляем оперу \"" + opera.getName() + "\"");
        }
        this.operaMap.put(opera.getName(), opera);
    }

    public void editOpera(String name, String description, EnAgeCategory ageCategory, int available) {
        if (this.operaMap.containsKey(name)) {
            logger.print("Редактируем оперу \"" + name + "\"");
            Opera opera = operaMap.get(name);
            opera.setDescription(description);
            opera.setAgeCategory(ageCategory);
            opera.setAvailable(available);
            this.operaMap.put(opera.getName(), opera);
        } else {
            logger.print("Не найдена опера \"" + name + "\"");
        }
    }

    public void deleteOpera(String name) {
        if (this.operaMap.containsKey(name)) {
            logger.print("Удаляем оперу \"" + name + "\"");
            this.operaMap.remove(name);
        } else {
            logger.print("Не найдена опера \"" + name + "\"");
        }
    }
}