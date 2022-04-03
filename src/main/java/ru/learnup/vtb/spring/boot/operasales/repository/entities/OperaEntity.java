package ru.learnup.vtb.spring.boot.operasales.repository.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operas")
@NamedEntityGraph(
        name = "opera-with-tickets",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("name"),
                @NamedAttributeNode("description"),
//                @NamedAttributeNode("ageCategory"),
                @NamedAttributeNode("available"),
                @NamedAttributeNode(value = "tickets", subgraph = "opera-tickets")
        },

        subgraphs = {
                @NamedSubgraph(name = "opera-tickets", attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("opera")
                })
        }
)
public class OperaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opera_generator")
    @SequenceGenerator(name = "opera_generator", sequenceName = "operas_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "age_category", length = 64)
    private String ageCategory;

    @Column(name = "available")
    private int available;


    @OneToMany(mappedBy = "opera"/*, fetch = FetchType.EAGER*/)
    private Collection<TicketEntity> tickets;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%s (%d)\n", name, id));
        for (TicketEntity ticket : tickets) {
            sb.append(ticket).append("\n");
        }

        return sb.toString();
    }
}
