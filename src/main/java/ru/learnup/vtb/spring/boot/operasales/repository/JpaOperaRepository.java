package ru.learnup.vtb.spring.boot.operasales.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import ru.learnup.vtb.spring.boot.operasales.repository.entities.OperaEntity;
import ru.learnup.vtb.spring.boot.operasales.repository.entities.TicketEntity;

import javax.persistence.LockModeType;
import java.util.List;

public interface JpaOperaRepository extends JpaRepository<OperaEntity, Long> {

    @EntityGraph(value = "opera-with-tickets")
    @Override
    List<OperaEntity> findAll();

    @EntityGraph(value = "opera-with-tickets")
    OperaEntity getByName(String name);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("from OperaEntity t where t.id = :id")
    TicketEntity getForUpdate(Long id);
}
