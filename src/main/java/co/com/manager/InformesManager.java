package co.com.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.com.bd.BaseDatos;
import co.com.model.Informe;

public class InformesManager {
	
	public List<Informe> getTodosLosInformes() {
		List<Informe> informes = new ArrayList<Informe>();
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Informes");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Informe informe = new Informe();
				informe.setIdInforme(rs.getInt("idInforme"));
				informe.setTitulo(rs.getString("titulo"));
				informe.setTipo(rs.getString("tipo"));
				informe.setFechaGeneracion(rs.getDate("fechaGeneracion"));
				informe.setDescripcion(rs.getString("descripcion"));
				
				informes.add(informe);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return informes;
	}
	
	public Informe getInformeById(int idInforme) {
		Informe informe = null;
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT * FROM Informes WHERE idInforme = ?");
			ps.setInt(1, idInforme);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				informe = new Informe();
				informe.setIdInforme(rs.getInt("idInforme"));
				informe.setTitulo(rs.getString("titulo"));
				informe.setTipo(rs.getString("tipo"));
				informe.setFechaGeneracion(rs.getDate("fechaGeneracion"));
				informe.setDescripcion(rs.getString("descripcion"));
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return informe;
	}
}
