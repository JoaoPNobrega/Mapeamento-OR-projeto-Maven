package dev.sauloaraujo.sgb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa.Populador;

@SpringBootApplication
public class AplicacaoTeste {
	public static void main(String[] args) {
		var contexto = SpringApplication.run(AplicacaoTeste.class, args);

		var populador = contexto.getBean(Populador.class);
		populador.popular();

		populador.gerarRelatorioIneficiente();
		populador.gerarRelatorioEficiente();
	}
}