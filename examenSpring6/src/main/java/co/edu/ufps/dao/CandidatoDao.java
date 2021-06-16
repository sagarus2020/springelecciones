package co.edu.ufps.dao;

import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.data.repository.CrudRepository;

import co.edu.ufps.entities.Candidato;

public interface CandidatoDao extends CrudRepository<Candidato,Integer>{
		
}
