package ru.learnup.vtb.spring.boot.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.vtb.spring.boot.operasales.model.EnAgeCategory;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.services.OperaService;
import ru.learnup.vtb.spring.boot.operasales.services.TicketService;

@SpringBootApplication
@EntityScan({"ru.learnup.vtb.spring.boot.operasales.repository.entities"})
public class OperasalesApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);

/*
        ctx.getBean(OperaService.class).addOpera(new Opera(null,
                    "Фауст",
                "О любви Фауста и Маргариты. Фауст, продал душу демону Мефистофелю",
                        EnAgeCategory.PG,
                 2
        ));

        ctx.getBean(OperaService.class).addOpera(new Opera(null,
                    "Кармен",
                "Главные герои оперы – Кармен и Дон Хозе. Они влюбляются друг в друга, но это чувство приводит в их судьбы беду.",
                        EnAgeCategory.PG,
                 1
                )
        );
        
 */
        ctx.getBean(OperaService.class).addOpera(new Opera(null,
                    "Аида",
                "История любви между Радамесом, предводителем египетских войск, и Аидой, рабыней (а на самом деле, дочерью эфиопского царя, с войсками которого сражались египтяне)",
                        EnAgeCategory.PG,
                  2
                )
        );



        ctx.getBean(TicketService.class).printAvailable("Фауст");

        ctx.getBean(TicketService.class).buyTicket("Фауст", "1a");
        ctx.getBean(TicketService.class).buyTicket("Фауст", "2b");
        ctx.getBean(TicketService.class).printAvailable("Фауст");

        ctx.getBean(TicketService.class).returnTicket("Фауст", "1a");
        ctx.getBean(TicketService.class).printAvailable("Фауст");

        ctx.getBean(OperaService.class).printAllOpera();
        //        ctx.getBean(OperaService.class).deleteOpera("Аида");



/*
		ctx.getBean(OperaService.class).printOperaByName("Кармен");

		ctx.getBean(OperaService.class).printAllOpera();

		ctx.getBean(OperaService.class).addOpera(new Opera(3L,
				"Фауст",
				"О любви Фауста и Маргариты. Фауст, продал душу демону Мефистофелю",
				EnAgeCategory.PG,
				2
		));

		ctx.getBean(TicketService.class).buyTicket("Фауст", "1a");
		ctx.getBean(TicketService.class).printAvailable("Фауст");
		ctx.getBean(TicketService.class).returnTicket("Фауст", "1a");
		ctx.getBean(TicketService.class).printAvailable("Фауст");
		ctx.getBean(TicketService.class).buyTicket("Фауст", "1a");
		ctx.getBean(OperaService.class).printAllOpera();
		ctx.getBean(TicketService.class).buyTicket("Фауст", "1a");
		ctx.getBean(OperaService.class).printAllOpera();

		ctx.getBean(OperaService.class).editOpera("Фауст","Edited: О любви Фауста и Маргариты. Фауст, продал душу демону Мефистофелю", EnAgeCategory.G, 111);

		ctx.getBean(OperaService.class).printAllOpera();

		ctx.getBean(OperaService.class).deleteOpera("Фауст");

		ctx.getBean(OperaService.class).printAllOpera();
*/


    }

}
