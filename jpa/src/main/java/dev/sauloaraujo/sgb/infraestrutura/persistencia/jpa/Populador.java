package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Populador {
	private @Autowired AutorJpaRepositorio autorJpaRepositorio;
	private @Autowired LivroJpaRepositorio livroJpaRepositorio;
	private @Autowired SocioJpaRepositorio socioRepositorio;
	private @Autowired ExemplarJpaRepositorio exemplarRepositorio;

	public void popular() {
		var andrew = new AutorJpa();
		andrew.nome = "Andrew Tanenbaum";
		autorJpaRepositorio.save(andrew);

		var ian = new AutorJpa();
		ian.nome = "Ian Sommerville";
		autorJpaRepositorio.save(ian);

		var marco = new AutorJpa();
		marco.nome = "Marco Tulio Valente";
		autorJpaRepositorio.save(marco);

		var redes = new LivroJpa();
		redes.id = "8582605609";
		redes.titulo = "Redes de Computadores";
		redes.autores = Arrays.asList(andrew);
		livroJpaRepositorio.save(redes);

		var sistemas = new LivroJpa();
		sistemas.id = "8543005671";
		sistemas.titulo = "Sistemas Operacionais Modernos";
		sistemas.autores = Arrays.asList(andrew);
		livroJpaRepositorio.save(sistemas);

		var engenharia = new LivroJpa();
		engenharia.id = "8543024978";
		engenharia.titulo = "Engenharia de Software";
		engenharia.autores = Arrays.asList(ian);
		livroJpaRepositorio.save(engenharia);

		var moderna = new LivroJpa();
		moderna.id = "6500019504";
		moderna.titulo = "Engenharia de Software Moderna";
		moderna.autores = Arrays.asList(marco);
		livroJpaRepositorio.save(moderna);

		var saulo = new SocioJpa();
		saulo.nome = "Saulo";
		socioRepositorio.save(saulo);

		var exemplar1 = new ExemplarJpa();
		exemplar1.livro = sistemas;
		exemplarRepositorio.save(exemplar1);

		var exemplar2 = new ExemplarJpa();
		exemplar2.livro = sistemas;
		exemplar2.emprestimo = new EmprestimoJpa();
		exemplar2.emprestimo.periodo = new PeriodoJpa();
		exemplar2.emprestimo.periodo.inicio = LocalDate.now();
		exemplar2.emprestimo.periodo.fim = LocalDate.now().plusDays(7);
		exemplar2.emprestimo.tomador = saulo;
		exemplarRepositorio.save(exemplar2);
	}

	@Transactional
	public void gerarRelatorioIneficiente() {
		for (var livro : livroJpaRepositorio.findAll()) {
			var total = livro.exemplares.size();

			var disponiveis = 0;
			for (var exemplar : livro.exemplares) {
				if (exemplar.emprestimo == null) {
					++disponiveis;
				}
			}

			System.out.print(livro.id);
			System.out.print(", ");
			System.out.print(livro.titulo);
			System.out.print(", ");
			System.out.print(livro.autores.get(0).nome);
			System.out.print(", ");
			System.out.print(disponiveis);
			System.out.print(", ");
			System.out.print(total);
			System.out.println();
		}
	}

	public void gerarRelatorioEficiente() {
		for (var resumo : livroJpaRepositorio.pesquisarResumosExpandidos()) {
			System.out.print(resumo.getLivro().getId());
			System.out.print(", ");
			System.out.print(resumo.getLivro().getTitulo());
			System.out.print(", ");
			System.out.print(resumo.getAutor().getNome());
			System.out.print(", ");
			System.out.print(resumo.getExemplaresDisponiveis());
			System.out.print(", ");
			System.out.print(resumo.getTotalExemplares());
			System.out.println();
		}
	}
}