package co.com.ws.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import co.com.bd.BaseDatos;
import co.com.manager.MateriasManager;
import co.com.manager.GruposManager;
import co.com.manager.EvaluacionesManager;
import co.com.manager.InformesManager;
import co.com.manager.MenuManager;
import co.com.model.Materia;
import co.com.model.MateriaInput;
import co.com.model.Grupo;
import co.com.model.Evaluacion;
import co.com.model.Informe;
import co.com.model.MenuItem;

@Path("WsColegio")
public class WsColegio {
	
	@GET
    @Path("isNumeroParImpar")
    @Produces(MediaType.TEXT_PLAIN)
    public String isNumeroParImpar(@QueryParam("numero") Integer numero){
        String isParImpar = "";
        
        if (numero % 2 == 0) {
        	isParImpar = "Par";
        	
        } else {
        	isParImpar = "Impar";
        }
        
        return isParImpar;
        
    }
	
	@GET
    @Path("isNumeroParImparJSon")
    @Produces(MediaType.APPLICATION_JSON)
    public String isNumeroParImparJSon(@QueryParam("numero") Integer numero){
        boolean isPar = false;
        boolean isImpar = false;
        
        if (numero % 2 == 0) {
        	isPar = true;
        	
        } else {
        	isImpar = true;
        }
        
        
        JsonObject jsonRespuesta = new JsonObject();
        jsonRespuesta.addProperty("isPar", isPar);
        jsonRespuesta.addProperty("isImpar", isImpar);
        
        return jsonRespuesta.toString();
        
    }
	
	@GET
	@Path("/getMateriasSinBd")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasSinBd() {
		Gson gson = new Gson();
		List<Materia> materias = new ArrayList<Materia>();

		for (int i = 0; i < 3 ; i++) {
			
			Materia mat = new Materia();
			mat.setIdMateria(i);
			mat.setNombre("Materia " + i);
			
			materias.add(mat);
		}
	
		return gson.toJson(materias);
	}
	
	@GET
	@Path("/getMateriasSinBdManager")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasSinBdManager() {
		Gson gson = new Gson();
		MateriasManager materiasManager = new MateriasManager();
		List<Materia> listMaterias = materiasManager.getTodasLasMateriasSinBd();
	
		return gson.toJson(listMaterias);
		
	}
	
	@GET
	@Path("/getMaterias")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMaterias() {
		Gson gson = new Gson();
		List<Materia> materias = new ArrayList<Materia>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Materias");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setIdMateria(rs.getInt("idMateria"));
				mat.setNombre(rs.getString("nombre"));
				
				materias.add(mat);
			}
			
//			Materia mat = new Materia();
//			mat.setIdMateria(1);
//			mat.setNombre("Fisica");
//			
//			materias.add(mat);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(materias);
	}
	
	@POST
	@Path("/getMateriasById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasById(String idMateria) {
		Gson gson = new Gson();
		List<Materia> materias = new ArrayList<Materia>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Materias WHERE idMateria = ?");
			ps.setInt(1, Integer.valueOf(idMateria));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setIdMateria(rs.getInt("idMateria"));
				mat.setNombre(rs.getString("nombre"));
				
				materias.add(mat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return gson.toJson(materias);
	}
	
	@POST
	@Path("/getMateriasByIdJson")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasByIdJson(String materia) {
		Gson gson = new Gson();
		MateriaInput materiaInput = gson.fromJson(materia, MateriaInput.class);
		List<Materia> materias = new ArrayList<Materia>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Materias WHERE idMateria = ?");
			ps.setInt(1, materiaInput.getIdMateria());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setIdMateria(rs.getInt("idMateria"));
				mat.setNombre(rs.getString("nombre"));
				
				materias.add(mat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return gson.toJson(materias);
	}
	
	@GET
	@Path("/getGrupos")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getGrupos() {
		Gson gson = new Gson();
		GruposManager gruposManager = new GruposManager();
		List<Grupo> grupos = gruposManager.getTodosLosGrupos();
		
		return gson.toJson(grupos);
	}
	
	@POST
	@Path("/getGrupoById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getGrupoById(String idGrupo) {
		Gson gson = new Gson();
		GruposManager gruposManager = new GruposManager();
		Grupo grupo = gruposManager.getGrupoById(Integer.valueOf(idGrupo));
		
		return gson.toJson(grupo);
	}
	
	@GET
	@Path("/getEvaluaciones")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getEvaluaciones() {
		Gson gson = new Gson();
		EvaluacionesManager evaluacionesManager = new EvaluacionesManager();
		List<Evaluacion> evaluaciones = evaluacionesManager.getTodasLasEvaluaciones();
		
		return gson.toJson(evaluaciones);
	}
	
	@POST
	@Path("/getEvaluacionById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getEvaluacionById(String idEvaluacion) {
		Gson gson = new Gson();
		EvaluacionesManager evaluacionesManager = new EvaluacionesManager();
		Evaluacion evaluacion = evaluacionesManager.getEvaluacionById(Integer.valueOf(idEvaluacion));
		
		return gson.toJson(evaluacion);
	}
	
	@GET
	@Path("/getInformes")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getInformes() {
		Gson gson = new Gson();
		InformesManager informesManager = new InformesManager();
		List<Informe> informes = informesManager.getTodosLosInformes();
		
		return gson.toJson(informes);
	}
	
	@POST
	@Path("/getInformeById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getInformeById(String idInforme) {
		Gson gson = new Gson();
		InformesManager informesManager = new InformesManager();
		Informe informe = informesManager.getInformeById(Integer.valueOf(idInforme));
		
		return gson.toJson(informe);
	}
	
	@GET
	@Path("/getMenuItems")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMenuItems() {
		Gson gson = new Gson();
		MenuManager menuManager = new MenuManager();
		List<MenuItem> menuItems = menuManager.getMenuItems();
		
		return gson.toJson(menuItems);
	}
}
