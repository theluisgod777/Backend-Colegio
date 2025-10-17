package co.com.model;

public class Grupo {
	private int idGrupo;
	private String nombre;
	private String grado;
	private int numeroEstudiantes;
	
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public int getNumeroEstudiantes() {
		return numeroEstudiantes;
	}
	public void setNumeroEstudiantes(int numeroEstudiantes) {
		this.numeroEstudiantes = numeroEstudiantes;
	}
}
