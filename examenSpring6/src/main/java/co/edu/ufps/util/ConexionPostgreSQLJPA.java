package co.edu.ufps.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionPostgreSQLJPA {
	
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	private static ConexionPostgreSQLJPA conexion;
	
	private ConexionPostgreSQLJPA() {
		if(this.em==null) {
			this.emf = Persistence.createEntityManagerFactory("eleccionesUniversitariasElectronicas");
			this.em = emf.createEntityManager();
		}
	}
	
	public static ConexionPostgreSQLJPA getConexion()
	{
		if(conexion == null) {
			conexion = new ConexionPostgreSQLJPA();
		}
		return conexion;
	}
	
	public EntityManager getEm()
	{
		return this.em;
	}

	public void cerrarConexio()
	{
		this.em.close();
		this.emf.close();
	}
}
