package reto1Unidad2CRUD.gestionAlumnos.Controller;

import reto1Unidad2CRUD.gestionAlumnos.UI.VentanaAlumnos;

import reto1Unidad2CRUD.gestionAlumnos.Model.IModeloAlumnos;
import reto1Unidad2CRUD.gestionAlumnos.Model.ModeloAlumnoJDBC;

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
