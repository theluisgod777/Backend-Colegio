package co.com.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.com.bd.BaseDatos;
import co.com.model.Grupo;

public class GruposManager {
	
	public List<Grupo> getTodosLosGrupos() {
		List<Grupo> grupos = new ArrayList<Grupo>();
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Grupos");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Grupo grupo = new Grupo();
				grupo.setIdGrupo(rs.getInt("idGrupo"));
				grupo.setNombre(rs.getString("nombre"));
				grupo.setGrado(rs.getString("grado"));
				grupo.setNumeroEstudiantes(rs.getInt("numeroEstudiantes"));
				
				grupos.add(grupo);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grupos;
	}
	
	public Grupo getGrupoById(int idGrupo) {
		Grupo grupo = null;
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Grupos WHERE idGrupo = ?");
			ps.setInt(1, idGrupo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				grupo = new Grupo();
				grupo.setIdGrupo(rs.getInt("idGrupo"));
				grupo.setNombre(rs.getString("nombre"));
				grupo.setGrado(rs.getString("grado"));
				grupo.setNumeroEstudiantes(rs.getInt("numeroEstudiantes"));
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grupo;
	}
}
