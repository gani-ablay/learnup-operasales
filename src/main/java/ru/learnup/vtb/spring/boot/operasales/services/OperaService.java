package ru.learnup.vtb.spring.boot.operasales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.vtb.spring.boot.operasales.model.EnAgeCategory;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.repository.JpaOperaRepository;
import ru.learnup.vtb.spring.boot.operasales.repository.entities.OperaEntity;
import ru.learnup.vtb.spring.boot.operasales.services.interfaces.Logger;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Scope("singleton")
public class OperaService {

    private JpaOperaRepository operaRepository;
    private Logger logger;

    @Autowired
    public OperaService(Logger logger, JpaOperaRepository operaRepository) {
        this.logger = logger;
        this.operaRepository = operaRepository;
    }

    public Opera getOperaByName(String name) {
        OperaEntity operaEntity = operaRepository.getByName(name);
        Opera opera = new Opera(operaEntity.getId(), operaEntity.getName(), operaEntity.getDescription(), EnAgeCategory.valueOf(operaEntity.getAgeCategory()), operaEntity.getAvailable());
        return opera;
    }

    public List<OperaEntity> getAllOpera() {
        return operaRepository.findAll();
    }

    public void printOperaByName(String name) {
        logger.print("Выводим оперу \"" + name + "\"");
        OperaEntity opera = operaRepository.getByName(name);
        System.out.println(opera.getName());
    }

    public void printAllOpera() {
        logger.print("Выводим все оперы: ");
        operaRepository.findAll().forEach(System.out::println);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            rollbackFor = {IOException.class, FileNotFoundException.class, EOFException.class}
    )
    public void addOpera(Opera opera) {
        OperaEntity operaEntity = new OperaEntity(null, opera.getName(), opera.getDescription(), opera.getAgeCategory().name(), opera.getAvailable(), null);
        if (operaRepository.getByName(opera.getName()) == null) {
            logger.print("Добавляем оперу \"" + opera.getName() + "\"");
            operaRepository.save(operaEntity);
        } else {
            logger.print("Эта опера \"" + opera.getName() + "\" уже есть, воспользуйтесь редактированием");
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            rollbackFor = {IllegalArgumentException.class}
    )
    public void editOpera(String name, String description, EnAgeCategory ageCategory, int available) {
        OperaEntity oldOpera = operaRepository.getByName(name);
        if (oldOpera != null) {
            logger.print("Редактируем оперу \"" + name + "\"");
            OperaEntity operaEntity = new OperaEntity(null, name, description, ageCategory.name(), available, oldOpera.getTickets());
            operaRepository.save(operaEntity);
        } else {
            logger.print("Не найдена опера \"" + name + "\"");
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            rollbackFor = {IOException.class, FileNotFoundException.class, EOFException.class}
    )
    public void deleteOpera(String name) {
        OperaEntity opera = operaRepository.getByName(name);
        if (operaRepository.getByName(name) != null) {
            logger.print("Удаляем оперу \"" + name + "\"");
            operaRepository.delete(opera);
        } else {
            logger.print("Не найдена опера \"" + name + "\"");
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            rollbackFor = {IllegalArgumentException.class}
    )
    public void save(Opera opera) {
        operaRepository.save(
                new OperaEntity(null, opera.getName(), opera.getDescription(), opera.getAgeCategory().name(), opera.getAvailable(), null)
        );
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            rollbackFor = {IllegalArgumentException.class}
    )
    public void delete(long operaId) {
        operaRepository.deleteById(operaId);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 2,
            rollbackFor = {IllegalArgumentException.class}
    )
    public void delete(String operaName) {
        OperaEntity opera = operaRepository.getByName(operaName);
        operaRepository.delete(opera);
    }


}