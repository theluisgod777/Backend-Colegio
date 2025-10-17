package co.com.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.com.bd.BaseDatos;
import co.com.model.Evaluacion;

public class EvaluacionesManager {
	
	public List<Evaluacion> getTodasLasEvaluaciones() {
		List<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT e.*, m.nombre as nombreMateria FROM Evaluaciones e LEFT JOIN Materias m ON e.idMateria = m.idMateria");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Evaluacion evaluacion = new Evaluacion();
				evaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
				evaluacion.setNombre(rs.getString("nombre"));
				evaluacion.setTipo(rs.getString("tipo"));
				evaluacion.setFecha(rs.getDate("fecha"));
				evaluacion.setIdMateria(rs.getInt("idMateria"));
				evaluacion.setNombreMateria(rs.getString("nombreMateria"));
				
				evaluaciones.add(evaluacion);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return evaluaciones;
	}
	
	public Evaluacion getEvaluacionById(int idEvaluacion) {
		Evaluacion evaluacion = null;
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT e.*, m.nombre as nombreMateria FROM Evaluaciones e LEFT JOIN Materias m ON e.idMateria = m.idMateria WHERE e.idEvaluacion = ?");
			ps.setInt(1, idEvaluacion);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				evaluacion = new Evaluacion();
				evaluacion.setIdEvaluacion(rs.getInt("idEvaluacion"));
				evaluacion.setNombre(rs.getString("nombre"));
				evaluacion.setTipo(rs.getString("tipo"));
				evaluacion.setFecha(rs.getDate("fecha"));
				evaluacion.setIdMateria(rs.getInt("idMateria"));
				evaluacion.setNombreMateria(rs.getString("nombreMateria"));
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return evaluacion;
	}
}
