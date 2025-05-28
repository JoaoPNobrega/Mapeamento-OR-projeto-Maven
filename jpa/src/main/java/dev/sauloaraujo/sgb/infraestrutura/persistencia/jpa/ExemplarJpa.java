package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EXEMPLAR")
@AttributeOverrides({
		@AttributeOverride(name = "emprestimo.periodo.inicio", column = @Column(name = "EMPRESTIMO_PERIODO_INICIO")),
		@AttributeOverride(name = "emprestimo.periodo.fim", column = @Column(name = "EMPRESTIMO_PERIODO_FIM")) })
class ExemplarJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne()
	LivroJpa livro;

	@Embedded
	EmprestimoJpa emprestimo;
}

@Embeddable
class EmprestimoJpa {
	@Embedded
	PeriodoJpa periodo;

	@ManyToOne
	@JoinColumn(name = "EMPRESTIMO_TOMADOR_ID")
	SocioJpa tomador;
}

@Embeddable
class PeriodoJpa {
	LocalDate inicio;
	LocalDate fim;
}

interface ExemplarJpaRepositorio extends JpaRepository<ExemplarJpa, String> {
}