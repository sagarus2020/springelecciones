package hibernatejpa.co.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="libros")
public class Book{


		@Id
		@GeneratedValue
		private int id;
		
		@Column(name="titulo", nullable=false, length=100)		
		private String title;
		
		@Column(name="isbn")		
		private String isbn;
		

	
}
