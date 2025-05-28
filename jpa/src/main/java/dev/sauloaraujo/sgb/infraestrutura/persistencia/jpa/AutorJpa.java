package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorResumo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUTOR")
class AutorJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String nome;

	@ManyToMany(mappedBy = "autores")
	Set<LivroJpa> livros;

	@Override
	public String toString() {
		return nome;
	}
}

interface AutorJpaRepositorio extends JpaRepository<AutorJpa, Integer> {
	List<AutorResumo> findAutorResumoByOrderByNome();
}