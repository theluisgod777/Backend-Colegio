package co.com.manager;

import java.util.ArrayList;
import java.util.List;

import co.com.model.MenuItem;

public class MenuManager {
	
	public List<MenuItem> getMenuItems() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		// Crear items del menú
		MenuItem estudiantes = new MenuItem();
		estudiantes.setId(1);
		estudiantes.setNombre("Estudiantes");
		estudiantes.setRuta("adminEstudiantes");
		estudiantes.setIcono("fa-user-graduate");
		estudiantes.setActivo(true);
		menuItems.add(estudiantes);
		
		MenuItem grupos = new MenuItem();
		grupos.setId(2);
		grupos.setNombre("Grupos");
		grupos.setRuta("grupos");
		grupos.setIcono("fa-users");
		grupos.setActivo(true);
		menuItems.add(grupos);
		
		MenuItem materias = new MenuItem();
		materias.setId(3);
		materias.setNombre("Materias");
		materias.setRuta("materias");
		materias.setIcono("fa-book");
		materias.setActivo(true);
		menuItems.add(materias);
		
		MenuItem evaluaciones = new MenuItem();
		evaluaciones.setId(4);
		evaluaciones.setNombre("Evaluaciones");
		evaluaciones.setRuta("evaluaciones");
		evaluaciones.setIcono("fa-clipboard-check");
		evaluaciones.setActivo(true);
		menuItems.add(evaluaciones);
		
		MenuItem informes = new MenuItem();
		informes.setId(5);
		informes.setNombre("Informes");
		informes.setRuta("informes");
		informes.setIcono("fa-chart-bar");
		informes.setActivo(true);
		menuItems.add(informes);
		
		return menuItems;
	}
}
