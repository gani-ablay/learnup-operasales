package ru.learnup.vtb.spring.boot.operasales.repository.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
@NamedQueries({
        @NamedQuery(name = "ticket.findlikename", query = "from TicketEntity t where t.name like :pattern"),
        @NamedQuery(name = "ticket.findbyopera", query = "from TicketEntity t where t.opera.id = :operaId"),
        @NamedQuery(name = "ticket.findbyoperaandname", query = "from TicketEntity t where t.opera.id = :operaId and t.name = :name")
})
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_generator")
    @SequenceGenerator(name = "ticket_generator", sequenceName = "tickets_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @JoinColumn(name = "opera_id")
    @ManyToOne
    private OperaEntity opera;

    @Override
    public String toString() {
        return String.format("%s %s (%d)", opera.getName(), name, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEntity ticketEntity = (TicketEntity) o;
        return Objects.equals(name, ticketEntity.name) && Objects.equals(opera, ticketEntity.opera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, opera);
    }
}
