package reto1Unidad2CRUD.apartado1GestionAlumnos.Controller;

import reto1Unidad2CRUD.apartado1GestionAlumnos.UI.VentanaAlumnos;

import reto1Unidad2CRUD.apartado1GestionAlumnos.Model.IModeloAlumnos;
import reto1Unidad2CRUD.apartado1GestionAlumnos.Model.ModeloAlumnoJDBC;

public class GestionAlumnos {

	public static void main(String[] args) {
		 try {
        	VentanaAlumnos view = new VentanaAlumnos();
        	IModeloAlumnos model =new ModeloAlumnoJDBC();
        	ControladorGestionAlumnos controller = new ControladorGestionAlumnos(model, view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
