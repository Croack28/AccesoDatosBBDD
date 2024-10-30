package reto1Unidad2CRUD.gestionAlumnos.Model;

import java.util.List;

public interface IModeloAlumnos {
	
	public List<String> getAll();
	
	public Alumno getAlumnoPorDNI(String DNI);

	public int modificarAlumno(Alumno alumno);
	
	public boolean eliminarAlumno(String  DNI);
	
	public boolean crear(Alumno alumno);
	

}
