package hibernatejpa.co.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hibernatejpa.co.entities.Book;

public class MainTest {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Book b= new Book();
				
		b.setTitle("jpa api");
		b.setIsbn("3213-3213");
		em.persist(b);
		
		em.getTransaction().commit();
		
	}

}
