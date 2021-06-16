package co.edu.ufps.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.ufps.entities.Eleccion;

public interface EleccionDao extends CrudRepository<Eleccion,Integer>{

	
}
