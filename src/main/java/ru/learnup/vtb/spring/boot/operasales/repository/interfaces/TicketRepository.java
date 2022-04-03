package ru.learnup.vtb.spring.boot.operasales.repository.interfaces;

import ru.learnup.vtb.spring.boot.operasales.repository.entities.TicketEntity;

import java.util.Collection;

public interface TicketRepository {

    Collection<TicketEntity> getAll();

    boolean save(TicketEntity ticket);

    boolean delete(long ticketId);
}
