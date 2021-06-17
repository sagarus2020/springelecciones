package co.ufps.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ConexionMySQL <T> {
	private EntityManagerFactory emf;
	private static EntityManager em;
	private Class<T> c;
	
	private ConexionMySQL() {
		this.em=this.getEm();
	}
	
	public ConexionMySQL(Class<T> c) {
		this.em = this.getEm();
		this.c=c;
	}
	
	public EntityManager getEm()
	{
		if(this.em==null) {
			this.emf = Persistence.createEntityManagerFactory("ExamenFinal");
			this.em = emf.createEntityManager();
		}
		return this.em;
	}
	
	public void setC(Class<T> c)
	{
		this.c=c;
	}
	
	public <E> T find(E id)
	{
		T object = (T) em.find(c, id);
		return object;
	}
	
	public List<T> list()
	{
		TypedQuery<T> consulta = this.em.createNamedQuery(c.getSimpleName() + ".findall",c);
		List<T> lista = (List<T>) consulta.getResultList();
		return lista;
	}
	
	public void insert(T o)
	{
		try {
			em.getTransaction().begin();
			em.persist(o);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public void update(T o)
	{
		try {
			em.getTransaction().begin();
			em.merge(o);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
		}
	}
	
	public void delete(T o)
	{
		try {
			em.getTransaction().begin();
			em.remove(o);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
}
