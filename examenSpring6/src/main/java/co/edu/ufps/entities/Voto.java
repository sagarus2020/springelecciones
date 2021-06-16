package co.edu.ufps.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the voto database table.
 * 
 */
@Entity
@NamedQuery(name="Voto.findAll", query="SELECT v FROM Voto v")
public class Voto implements Serializable {
	public Voto(String enlace, Timestamp fechacreacion, Timestamp fechavoto, String uuid) {
		super();
		this.enlace = enlace;
		this.fechacreacion = fechacreacion;
		this.fechavoto = fechavoto;
		this.uuid = uuid;

	}

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String enlace;

	private Timestamp fechacreacion;

	private Timestamp fechavoto;

	private String uuid;

	//bi-directional many-to-one association to Candidato
	@ManyToOne
	@JoinColumn(name="candidato")
	private Candidato candidatoBean;

	//bi-directional many-to-one association to Estamento
	@ManyToOne
	@JoinColumn(name="estamento")
	private Estamento estamentoBean;

	//bi-directional many-to-one association to Votante
	@ManyToOne
	@JoinColumn(name="votante")
	private Votante votanteBean;

	public Voto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnlace() {
		return this.enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Timestamp getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechavoto() {
		return this.fechavoto;
	}

	public void setFechavoto(Timestamp fechavoto) {
		this.fechavoto = fechavoto;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Candidato getCandidatoBean() {
		return this.candidatoBean;
	}

	public void setCandidatoBean(Candidato candidatoBean) {
		this.candidatoBean = candidatoBean;
	}

	public Estamento getEstamentoBean() {
		return this.estamentoBean;
	}

	public void setEstamentoBean(Estamento estamentoBean) {
		this.estamentoBean = estamentoBean;
	}

	public Votante getVotanteBean() {
		return this.votanteBean;
	}

	public void setVotanteBean(Votante votanteBean) {
		this.votanteBean = votanteBean;
	}

}