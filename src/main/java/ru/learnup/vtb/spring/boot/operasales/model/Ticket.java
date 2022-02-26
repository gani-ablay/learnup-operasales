package ru.learnup.vtb.spring.boot.operasales.model;

import lombok.*;
import java.util.HashSet;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    private String operaName;
    private HashSet<String> ticketSet;
}
