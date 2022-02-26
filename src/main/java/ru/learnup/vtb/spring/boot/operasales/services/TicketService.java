package ru.learnup.vtb.spring.boot.operasales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.model.Ticket;
import ru.learnup.vtb.spring.boot.operasales.services.interfaces.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
@Scope("singleton")
public class TicketService {
    @Autowired
    private HashMap<String, HashSet<String>> ticketMap;
    private Logger logger;

    @Autowired
    private OperaService operaService;

    @Autowired
    public TicketService(Logger logger) {
        this.logger = logger;
    }

    public void buyTicket(String operaName, String ticket) {

        if (!operaService.getAllOpera().containsKey(operaName)) {
            logger.print("Не найдена опера \"" + operaName + "\"");
            return;
        }
        Opera opera = operaService.getOperaByName(operaName);
        if (ticketMap.isEmpty()) {
            ticketMap = new HashMap<>();
            for (String key:operaService.getAllOpera().keySet()
                 ) {
                ticketMap.put(key, new HashSet<>());
            }
        }

        if (!ticketMap.containsKey(operaName) || ticketMap.get(operaName).isEmpty()) {
            ticketMap.put(operaName, new HashSet<String>());
        }

        HashSet<String> ticketSet = ticketMap.get(operaName);

        if (opera.getAvailable() <= ticketSet.size()) {
            logger.print("Билетов на оперу \"" + operaName + "\" не осталось ");
            return;
        }

        if (ticketSet.contains(ticket)) {
            logger.print("Билет " + ticket + " уже продан, выберите другой");
            return;
        }

        ticketSet.add(ticket);
        logger.print("Покупка билета " + ticket + " на оперу \"" + operaName + "\" успешно произведена" );
    }

    public void returnTicket(String operaName, String ticket) {

        if (!operaService.getAllOpera().containsKey(operaName)) {
            logger.print("Не найдена опера \"" + operaName +"\"");
            return;
        }
        Opera opera = operaService.getOperaByName(operaName);

        if (ticketMap.get(operaName) == null) {
            logger.print("На оперу \"" + operaName + "\" билеты не продавались. Возврат невозможен");
            return;
        }

        HashSet<String> ticketSet = ticketMap.get(operaName);

        if (ticketSet.size() == 0) {
            logger.print("Проданных билетов на оперу \"" + operaName + "\" нет. Возврат невозможен ");
            return;
        }

        if (!ticketSet.contains(ticket)) {
            logger.print("Билет " + ticket + " не найден. Возврат невозможен");
            return;
        }

        ticketSet.remove(ticket);
        logger.print("Билет " + ticket + " на оперу \"" + operaName + "\" возвращен ");
    }

    public void printAvailable(String operaName) {
        int size = 0;
        if (ticketMap.get(operaName) != null){
            size = ticketMap.get(operaName).size();
        }

        logger.print("На оперу \"" + operaName + "\" осталось билетов: " + (operaService.getOperaByName(operaName).getAvailable() - size));
    }

    public HashSet<String> getTicketSet(String operaName) {
        return ticketMap.get(operaName);
    }
}
