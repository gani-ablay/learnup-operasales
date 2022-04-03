package ru.learnup.vtb.spring.boot.operasales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.vtb.spring.boot.operasales.annotations.TicketBuyNotified;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.repository.JpaOperaRepository;
import ru.learnup.vtb.spring.boot.operasales.repository.JpaTicketRepository;
import ru.learnup.vtb.spring.boot.operasales.repository.entities.OperaEntity;
import ru.learnup.vtb.spring.boot.operasales.repository.entities.TicketEntity;
import ru.learnup.vtb.spring.boot.operasales.services.interfaces.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
@Scope("singleton")
public class TicketService {
    private JpaTicketRepository ticketRepository;
    private JpaOperaRepository operaRepository;

    //    @Autowired
    private HashMap<String, HashSet<String>> ticketMap;
    private Logger logger;

    @Autowired
    private OperaService operaService;

    @Autowired
    public TicketService(Logger logger, JpaTicketRepository ticketRepository, JpaOperaRepository operaRepository) {
        this.logger = logger;
        this.ticketRepository = ticketRepository;
        this.operaRepository = operaRepository;
    }


    @TicketBuyNotified
    public void buyTicket(String operaName, String ticket) {

        if (operaRepository.getByName(operaName) == null) {
            logger.print("Не найдена опера \"" + operaName + "\"");
            throw new IllegalArgumentException("Не найдена опера \"" + operaName + "\"");
        }
        OperaEntity operaEntity = operaRepository.getByName(operaName);
        int size = 0;
        if (!operaEntity.getTickets().isEmpty()) {
            size = operaEntity.getTickets().size();
        }
        if (operaEntity.getAvailable() <= size) {
            logger.print("Билетов на оперу \"" + operaName + "\" не осталось ");
            throw new IllegalArgumentException("Билетов на оперу \"" + operaName + "\" не осталось ");
        }

        if (!ticketRepository.getByOperaAndName(operaEntity.getId(), ticket).isEmpty()) {
            logger.print("Билет " + ticket + " уже продан, выберите другой");
            throw new IllegalArgumentException("Билет " + ticket + " уже продан, выберите другой");
        }

        TicketEntity ticketEntity = new TicketEntity(null, ticket, operaEntity);
        ticketRepository.save(ticketEntity);
        logger.print("Покупка билета " + ticket + " на оперу \"" + operaName + "\" успешно произведена");
    }

    public void returnTicket(String operaName, String ticket) {

        if (operaRepository.getByName(operaName) == null) {
            logger.print("Не найдена опера \"" + operaName + "\"");
            throw new IllegalArgumentException("Не найдена опера \"" + operaName + "\"");
        }
        Opera opera = operaService.getOperaByName(operaName);

        List<TicketEntity> ticketEntities = ticketRepository.getByOpera(opera.getId());

        if (ticketEntities.isEmpty()) {
            logger.print("Проданных билетов на оперу \"" + operaName + "\" нет. Возврат невозможен ");
            throw new IllegalArgumentException("Проданных билетов на оперу \"" + operaName + "\" нет. Возврат невозможен ");
        }

        HashSet<String> ticketSet = new HashSet<>();
        for (TicketEntity ticketEntity : ticketEntities
        ) {
            ticketSet.add(ticketEntity.getName());
        }

        if (!ticketSet.contains(ticket)) {
            logger.print("Билет " + ticket + " не найден. Возврат невозможен");
            throw new IllegalArgumentException("Билет " + ticket + " не найден. Возврат невозможен");
        }

        List<TicketEntity> filteredTicketEntities = ticketRepository.getByOperaAndName(opera.getId(), ticket);
        for (TicketEntity ticketEntity : filteredTicketEntities
        ) {
            ticketRepository.deleteById(ticketEntity.getId());
        }

        logger.print("Билет " + ticket + " на оперу \"" + operaName + "\" возвращен ");
    }

    public void printAvailable(String operaName) {
        int size = 0;
        Opera opera = operaService.getOperaByName(operaName);
        List<TicketEntity> ticketEntities = ticketRepository.getByOpera(opera.getId());
        if (ticketEntities != null) {
            size = ticketEntities.size();
        }

        logger.print("На оперу \"" + operaName + "\" осталось билетов: " + (opera.getAvailable() - size));
    }

    public HashSet<String> getTicketSet(String operaName) {

        Opera opera = operaService.getOperaByName(operaName);

        List<TicketEntity> ticketEntities = ticketRepository.getByOpera(opera.getId());

        HashSet<String> ticketSet = new HashSet<>();
        for (TicketEntity ticketEntity : ticketEntities
        ) {
            ticketSet.add(ticketEntity.getName());
        }

        return ticketSet;
    }
}
