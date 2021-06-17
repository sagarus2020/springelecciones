package co.edu.ufps.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Eleccion;
import co.edu.ufps.entities.Estamento;



@Repository
public interface EstamentoDao extends CrudRepository<Estamento,Integer>{

	
	
	 List<Estamento> findByEleccionBean(int eleccionBean);

}
