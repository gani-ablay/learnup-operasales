package ru.learnup.vtb.spring.boot.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.vtb.spring.boot.operasales.model.EnAgeCategory;
import ru.learnup.vtb.spring.boot.operasales.model.Opera;
import ru.learnup.vtb.spring.boot.operasales.services.OperaService;
import ru.learnup.vtb.spring.boot.operasales.services.TicketService;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class OperasalesApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);
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



	}

}
