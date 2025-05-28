package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SOCIO")
class SocioJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String nome;
	String email;

	@Override
	public String toString() {
		return nome;
	}
}

interface SocioJpaRepositorio extends JpaRepository<SocioJpa, Integer> {
}