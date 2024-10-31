package gestionAlumnos.Controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gestionAlumnos.Model.Alumno;
import gestionAlumnos.Model.IModeloAlumnos;
import gestionAlumnos.UI.VentanaAlumnos;

public class ControladorGestionAlumnos  implements ActionListener, ListSelectionListener {

	private IModeloAlumnos model;
	private VentanaAlumnos view;

	public ControladorGestionAlumnos(IModeloAlumnos model, VentanaAlumnos view) {
		 this.model = model;
        this.view = view;
        anadirListeners(this);
        
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
	}

	private void anadirListeners(ControladorGestionAlumnos controladorGestionAlumnos) {
		view.btnCargarTodos.addActionListener(controladorGestionAlumnos);
        view.btnCrear.addActionListener(controladorGestionAlumnos);
        view.btnModificar.addActionListener(controladorGestionAlumnos);
        view.btnEliminar.addActionListener(controladorGestionAlumnos);  
        view.btnBuscar.addActionListener(controladorGestionAlumnos);
        
        view.jListaAlumnos.addListSelectionListener(controladorGestionAlumnos);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent event) {
	   	 String actionCommand = event.getActionCommand();
	
	     System.out.println("estoy en actionPerformed con la opcion "+actionCommand);

	     switch (actionCommand) {
	     	case "Cargar Todos":
	     		DefaultListModel<String> modelo = new DefaultListModel<String>();
	     		List<String> lista = cargarTodos();
	     		if(lista!=null) {
	     			view.modeloLista.clear();
	     			for(String alumno: lista) {
	     				view.modeloLista.addElement("Alumno:  "+alumno);
	     			}
		     		
	     		}
	     		else {
	     			JOptionPane.showMessageDialog(null, "No hay alumnos guardados","Error ", 0);
	     		}
	     		
	     		break;
	     	case "Crear Nuevo":
	     		Boolean guardado = guardarAlumno();
	     		if(guardado) {
	     			JOptionPane.showMessageDialog(null, "Se ha insertado correctamente el alumno","Insercion correcta", 1);
	     		}
	     		else {
	     			JOptionPane.showMessageDialog(null, "Ha habido un problema en la insercion","Error insercion", 0);
	     		}
	     
	     		break;
	     	case "Eliminar":
	     		Boolean eliminado =eliminarAlumno();
	     		if(eliminado) {
	     			JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el alumno","Eliminacion correcta", 1);
	     		}
	     		else
	     			JOptionPane.showMessageDialog(null, "Ha habido un problema en la eliminacion, no existe un alumno con ese dni","Error eliminacion", 0);
	     		break;
	     	case "Buscar por dni":
	     		Alumno buscado =buscarAlumno();
	     		if(buscado!=null) {
	     		view.modeloLista.clear();
	     		view.modeloLista.addElement(buscado.toString());
	     		}
	     		else {
	     			view.modeloLista.clear();
	     			JOptionPane.showMessageDialog(null, "Ha habido un problema en la busqueda, no existe un alumno con ese dni","Error busqueda", 0);
	     		}
		     		break;
	     	case "Modificar":
	     		int modificado = modificarAlumno();
	     		if(modificado==0) {
	     			JOptionPane.showMessageDialog(null, "Se ha modificado correctamente el alumno","Insercion correcta", 1);
	     		}
	     		else if (modificado==1) {
	     			JOptionPane.showMessageDialog(null, "Ha habido un problema en la modificacion, no existe un alumno con ese dni","Error busqueda", 0);
	     		}
	     		else {
	     			JOptionPane.showMessageDialog(null, "Ha habido un problema en la modificacion, no ha modificado nada","Error busqueda", 0);
	     		}
	     
	     		break;
	     		
	     }
	     
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO implementar el pinchar de una lista
	    System.out.println("estoy en valueChanged");
		if (!e.getValueIsAdjusting()) {//This line prevents double events

     		//TODO
			// view.jListaAlumnos

	    }

		
	
	}
	
	public List<String> cargarTodos() {
		return model.getAll();
	}
	
	
	public int modificarAlumno() {
		String dni = view.textFieldDNI.getText();
		String nombre = view.textFieldNombre.getText();
		String apellidos = view.textFieldApellidos.getText();
		String cp = view.textFieldCP.getText();
		
		Alumno alumno = new Alumno();
		alumno.setDNI(dni);
		alumno.setNombre(nombre);
		alumno.setApellidos(apellidos);
		alumno.setCP(cp);
		
		
		return model.modificarAlumno(alumno);
	}
	
	public Alumno buscarAlumno() {
		String dni = view.textFieldDNI.getText();
		
		return model.getAlumnoPorDNI(dni);
	}
	
	private boolean guardarAlumno() {
		String dni = view.textFieldDNI.getText();
		String nombre = view.textFieldNombre.getText();
		String apellidos = view.textFieldApellidos.getText();
		String cp = view.textFieldCP.getText();
		
		Alumno alumno = new Alumno();
		alumno.setDNI(dni);
		alumno.setNombre(nombre);
		alumno.setApellidos(apellidos);
		alumno.setCP(cp);
		
		return model.crear(alumno);
		
	}
	
	private boolean eliminarAlumno() {
		String dni = view.textFieldDNI.getText();
		
		return model.eliminarAlumno(dni);
	}

	private void limpiarCampos() {
		view.textFieldDNI.setText("");
        view.textFieldNombre.setText("");
        view.textFieldApellidos.setText("");
        view.textFieldCP.setText("");
		
	}
	
	private void cargarAlumno(Alumno alumno) {
        if (alumno == null) {
        	limpiarCampos();
        }

        view.textFieldDNI.setText(alumno.getDNI());
        view.textFieldNombre.setText(alumno.getNombre());
        view.textFieldApellidos.setText(alumno.getApellidos());
        view.textFieldCP.setText(alumno.getCP());
    }

}