package co.edu.ufps.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the candidato database table.
 * 
 */
@Entity
@NamedQuery(name="Candidato.findAll", query="SELECT c FROM Candidato c")
public class Candidato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String apellido;

	private String documento;

	private String nombre;

	private Integer numero;

	//bi-directional many-to-one association to Eleccion
	@ManyToOne
	@JoinColumn(name="eleccion")
	private Eleccion eleccionBean;

	//bi-directional many-to-one association to Voto
	@OneToMany(mappedBy="candidatoBean")
	private List<Voto> votos;

	public Candidato() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Eleccion getEleccionBean() {
		return this.eleccionBean;
	}

	public void setEleccionBean(Eleccion eleccionBean) {
		this.eleccionBean = eleccionBean;
	}

	public List<Voto> getVotos() {
		return this.votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
	
	public Candidato(String apellido, String documento, String nombre, Integer numero) {
		super();
		this.apellido = apellido;
		this.documento = documento;
		this.nombre = nombre;
		this.numero = numero;
	}

	public Voto addVoto(Voto voto) {
		getVotos().add(voto);
		voto.setCandidatoBean(this);

		return voto;
	}

	public Voto removeVoto(Voto voto) {
		getVotos().remove(voto);
		voto.setCandidatoBean(null);

		return voto;
	}

}