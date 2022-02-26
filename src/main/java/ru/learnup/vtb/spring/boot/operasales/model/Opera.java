package ru.learnup.vtb.spring.boot.operasales.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Opera {

    private Long id;
    private String name;
    private String description;

    private EnAgeCategory ageCategory;

    private int available;

    @Override
    public String toString() {
        return "Opera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ageCategory=" + ageCategory +
                ", available=" + available +
                '}';
    }
}
