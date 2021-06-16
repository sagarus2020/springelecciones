package co.edu.ufps.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import co.edu.ufps.entities.Candidato;
import co.edu.ufps.entities.Eleccion;
import co.edu.ufps.entities.Estamento;
import co.edu.ufps.entities.Tipodocumento;
import co.edu.ufps.entities.Votante;
import co.edu.ufps.entities.Voto;
import co.edu.ufps.dao.VotoDao;
import co.edu.ufps.dao.CandidatoDao;
import co.edu.ufps.dao.EleccionDao;
import co.edu.ufps.dao.EstamentoDao;
import co.edu.ufps.dao.TipodocumentoDao;
import co.edu.ufps.dao.VotanteDao;


import co.edu.ufps.util.ServicioEmail;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servlet implementation class IndexServices
 */
@RestController
@RequestMapping("/index")
public class IndexServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	CandidatoDao candidatoDao;
	@Autowired
	EleccionDao eleccionDao;
	@Autowired
	VotanteDao votanteDao;
	@Autowired
	TipodocumentoDao tipodocumentoDao;
	@Autowired
	EstamentoDao estamentoDao;
	@Autowired
	VotoDao votoDao;
	String host = "http://localhost:8080/";
    /**
     * Default constructor. 
     */
    public IndexServices() {
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		try {
			switch(action){
		
			case "/savearCandidato":
				savearCandidato(request,response);
				break;
			
			case "/savearVotante":
				savearVotante(request,response);
				break;
			case "/formularioValidacion":
				showValidarVotante(request,response);
				break;
			case "/validarVotante":
				validarVotante(request,response);
				break;
			case "/registrarVoto":
				registrarVoto(request,response);
				break;
			default:
				showInscripcionCandidato(request,response);
				break;
			}
		}catch(SQLException e)
		{
			throw new ServletException(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    @GetMapping("/inscripcionCandidato")
	private void showInscripcionCandidato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Eleccion> elecciones = (List<Eleccion>) eleccionDao.findAll();
		request.setAttribute("elecciones", elecciones);
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/inscripcionCandidatos.jsp");
		dispatcher.forward(request, response); 
	}
	
	private void savearCandidato(HttpServletRequest request, HttpServletResponse response)throws ServletException, SQLException, IOException {
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String eleccionId = request.getParameter("eleccionId");
		Integer eleccion = Integer.valueOf(eleccionId);
		Candidato c = new Candidato(documento,nombre,apellido,eleccion);
		this.candidatoDao.save(c);
		response.sendRedirect("inscripcionCandidato");
	}
	@GetMapping("/inscripcionVotante") 
	private void showInscripcionVotante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Tipodocumento> tipodocumentos = (List<Tipodocumento>) tipodocumentoDao.findAll();
		request.setAttribute("tipodocumentos",tipodocumentos);
		
		List<Eleccion> elecciones = (List<Eleccion>) eleccionDao.findAll();
		request.setAttribute("elecciones", elecciones);
		
		List<Estamento> estamentos = estamentoDao.findByEleccionBean(3);
		request.setAttribute("estamentos", estamentos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/inscripcionVotante.jsp");
		dispatcher.forward(request, response); 
	}
	
	private void savearVotante(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
		// TODO Auto-generated method stub
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		Votante v = new Votante(nombre,email,documento);

		String eleccionId = request.getParameter("eleccionId");
		Eleccion e = this.eleccionDao.findById(Integer.valueOf(eleccionId)).get();
		
		String tipoDocumentoId = request.getParameter("tipoDocumentoId");
		Tipodocumento t = this.tipodocumentoDao.findById(Integer.valueOf(tipoDocumentoId)).get();
		
		String estamendoId = request.getParameter("estamentoId");
		int idEstamento = Integer.parseInt(estamendoId);
		Estamento estamento = this.estamentoDao.findById(idEstamento).get();
		
		v.setEleccionBean(e);
		v.setTipodocumentoBean(t);
		request.setAttribute("votante", v);
		this.votanteDao.save(v);
		

		
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        
   
		ServicioEmail servicioEmail = new ServicioEmail("ejemplo.email.ufps@gmail.com", "nfrbdxklxggkgoko");
		//http://localhost:8080/eleccionesUniversitariasElectronicas/formularioValidacion?
		String link = host + request.getContextPath() + "/" +  "formularioValidacion" + "?nombre=" + nombre + "&email=" + 
		email + "&id=" + v.getId() + "&eleccionCargo=" + e.getCargo() + "&fechaInicio=" + e.getFechainicio().toString() + "&fechaFin=" + 
	    e.getFechafin().toString() + "&estamentoId=" + estamento.getId().intValue() +"&estamento=" + estamento.getDescripcion();
		
		System.out.println(link);
		
		servicioEmail.enviarEmail(email, e.getNombre(), "link para la validar y escoger su voto: " + link);
		
		response.sendRedirect("inscripcionVotante");
		
	}
	
	private void showValidarVotante(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		Votante v = new Votante(id,nombre,email);
		
		String eleccionCargo = request.getParameter("eleccionCargo");
		Timestamp fechaInicio =Timestamp.valueOf(request.getParameter("fechaInicio"));
		Timestamp fechaFin = Timestamp.valueOf(request.getParameter("fechaFin"));
		Eleccion e = new Eleccion(eleccionCargo,fechaInicio,fechaFin);
		
		Integer estamentoId = Integer.parseInt(request.getParameter("estamentoId"));
		String estamentoDescripcion = request.getParameter("estamento");
		Estamento est = new Estamento(estamentoId, estamentoDescripcion);
		
		List<Tipodocumento> tipoDocumentos = (List<Tipodocumento>) tipodocumentoDao.findAll();
		
		request.setAttribute("tipoDocumentos",tipoDocumentos);
		request.setAttribute("votante", v);
		request.setAttribute("eleccion", e);
		request.setAttribute("estamento", est);

		RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/validacionVoto.jsp");
		dispatcher.forward(request, response);
	}

	private void validarVotante(HttpServletRequest request, HttpServletResponse response)  throws ServletException, SQLException, IOException
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String documento = request.getParameter("documento");
		Integer tipoDocumento = Integer.parseInt(request.getParameter("documentoId"));
		Votante v = this.votanteDao.findById(id).get();
		
		Integer estamentoId = Integer.parseInt(request.getParameter("estamentoId"));
		String estamentoDescripcion = request.getParameter("estamentoDescripcion");
		Estamento est = new Estamento(estamentoId, estamentoDescripcion);
		
		String eleccionCargo = request.getParameter("eleccionCargo");
		Timestamp fechaInicio =Timestamp.valueOf(request.getParameter("fechaInicio"));
		Timestamp fechaFin = Timestamp.valueOf(request.getParameter("fechaFin"));
		Eleccion e = new Eleccion(eleccionCargo,fechaInicio,fechaFin);
		
		List<Candidato> candidatos = (List<Candidato>) this.candidatoDao.findAll();
		
		
		if(v.getDocumento().equals(documento) && v.getTipodocumentoBean().getId().intValue()==tipoDocumento.intValue())
		{
			request.setAttribute("votante", v);
			request.setAttribute("estamento", est);
			request.setAttribute("eleccion", e);
			request.setAttribute("candidatos", candidatos);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/elegirCandidato.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("datosErroneos", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/validacionVoto.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void registrarVoto(HttpServletRequest request, HttpServletResponse response)  throws ServletException, SQLException, IOException{
		String estamentoId = request.getParameter("estamentoId");
		Estamento e = this.estamentoDao.findById(Integer.valueOf(estamentoId)).get();
		
		String candidatoId = request.getParameter("candidatoId");
		Candidato c = this.candidatoDao.findById(Integer.valueOf(candidatoId)).get();
		
		String votanteId = request.getParameter("votanteId");
		Votante t = this.votanteDao.findById(Integer.valueOf(votanteId)).get();
		
		Timestamp creacion = Timestamp.valueOf("");
		Timestamp voto = Timestamp.valueOf(request.getParameter("fechacreacion"));
		Voto v = new Voto();
		
		v.setEstamentoBean(e);
		v.setVotanteBean(t);
		v.setCandidatoBean(c);
		this.votoDao.save(v);
		response.sendRedirect("inscripcionVotante");
		
		
		
	}
	
	private void showCandidatos(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
				/*List<Estamento> estamentos = estamentoDao.findAll();
				request.setAttribute("estamentos",estamentos);
				
				List<Candidato> candidatos = candidatoDao.findAll();
				request.setAttribute("candidatos", candidatos);
				
				String votanteId = request.getParameter("votanteId");
				VotanteEntity t = this.votanteDao.findById(Integer.valueOf(votanteId));*/
		
		// TODO Auto-generated method stub
				String estamentoId = request.getParameter("estamentoId");
				Estamento e = this.estamentoDao.findById(Integer.valueOf(estamentoId)).get();
				
				String candidatoId = request.getParameter("candidatoId");
				Candidato c = this.candidatoDao.findById(Integer.valueOf(candidatoId)).get();
				
				String votanteId = request.getParameter("votanteId");
				Votante t = this.votanteDao.findById(Integer.valueOf(votanteId)).get();
				
				String uuid = request.getParameter("uuid");
				String enlace = request.getParameter("enlace");
				
				Timestamp creacion = Timestamp.valueOf("2020-12-11 08:10:05");
				Timestamp voto = Timestamp.valueOf("2020-12-11 08:10:05");
				Voto v = new Voto(enlace,creacion,voto,uuid);
				
				v.setEstamentoBean(e);
				v.setVotanteBean(t);
				v.setCandidatoBean(c);
				v.setFechacreacion(creacion);
				v.setFechavoto(voto);
				this.votoDao.save(v);
				
				//response.sendRedirect("inscripcionVotante");
	}
}
