package ru.learnup.vtb.spring.boot.operasales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.learnup.vtb.spring.boot.operasales.model.EnAgeCategory;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Configuration
public class OperaCollectionConfig {
    @Primary
    @Bean
    public Map<String, Opera> operaMap() {
        Map<String, Opera> operaMap = new HashMap<>();
        operaMap.put("Кармен",
                new Opera(1L,
                        "Кармен",
                        "Главные герои оперы – Кармен и Дон Хозе. Они влюбляются друг в друга, но это чувство приводит в их судьбы беду.",
                        EnAgeCategory.PG,
                        1
                )
        );
        operaMap.put("Аида",
                new Opera(2L,
                        "Аида",
                        "История любви между Радамесом, предводителем египетских войск, и Аидой, рабыней (а на самом деле, дочерью эфиопского царя, с войсками которого сражались египтяне)",
                        EnAgeCategory.PG,
                        2
                )
        );
        return operaMap;
    }

    @Bean
    public HashMap<String, HashSet<String>> ticketMap(){
        HashMap<String, HashSet<String>> ticketMap = new HashMap<>();
        return ticketMap;
    }
}
