package hibernatejpa.co.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hibernatejpa.co.entities.Book;



	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;


	public class MainApp {

	    public static void main(String[] args) {
	        Book libro = new Book();
	    
	        libro.setId(1);
	        libro.setTitle("Tet");
	        libro.setIsbn("Tet");
	        
	        //Hibernate API to save this objects to DB
	        //Session factory is created only ONCE
	        SessionFactory sessionFactory = new Configuration().configure("/hibernatejpa/src/persistence.xml").buildSessionFactory();
	        Session session = sessionFactory.openSession();
	        
	        //create transaction
	        session.beginTransaction();
	        session.save(libro);
	        
	        //end the transaction
	        session.getTransaction().commit();
	        
	        //Closing the session
	        session.close();
	        
	        
	    }

	}