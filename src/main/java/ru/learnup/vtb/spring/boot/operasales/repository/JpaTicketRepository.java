package ru.learnup.vtb.spring.boot.operasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.repository.entities.TicketEntity;

import javax.persistence.LockModeType;
import java.util.List;

public interface JpaTicketRepository extends JpaRepository<TicketEntity, Long> {

    void deleteById(Long id);

    List<TicketEntity> findAllByNameLikeOrderById(String namePattern);

    @Query(name = "ticket.findlikename")
    List<TicketEntity> getMyFilteredResult(String pattern);

    @Query(name = "ticket.findbyoperaandname")
    List<TicketEntity> getByOperaAndName(Long operaId, String name);

    @Query(name = "ticket.findbyopera")
    List<TicketEntity> getByOpera(Long operaId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("from TicketEntity t where t.id = :id")
    TicketEntity getForUpdate(Long id);
}
