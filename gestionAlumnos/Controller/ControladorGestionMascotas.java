package gestionAlumnos.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gestionAlumnos.Model.IModeloAlumnos;
import gestionAlumnos.Model.IModeloMascotas;
import gestionAlumnos.Model.Mascota; 
import gestionAlumnos.Model.ModeloAlumnoJDBC;
import gestionAlumnos.Model.ModeloMascotaJDBC;
import gestionAlumnos.UI.VentanaMascotas;

public class ControladorGestionMascotas implements ActionListener, ListSelectionListener {

    private IModeloMascotas model; 
    private VentanaMascotas view;  

    public ControladorGestionMascotas(IModeloMascotas model, VentanaMascotas view) {
        this.model = model;
        this.view = view;
        anadirListeners(this);

        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }

    private void anadirListeners(ControladorGestionMascotas controladorGestionMascotas) {
        view.btnCargarTodos.addActionListener(controladorGestionMascotas);
        view.btnCrear.addActionListener(controladorGestionMascotas);
        view.btnEliminar.addActionListener(controladorGestionMascotas);
        view.btnBuscar.addActionListener(controladorGestionMascotas);

        view.jListaMascotas.addListSelectionListener(controladorGestionMascotas);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        System.out.println("Estoy en actionPerformed con la opción " + actionCommand);

        switch (actionCommand) {
            case "Cargar Todos":
                cargarMascotas();
                break;
            case "Crear Nueva":
                guardarMascota();
                break;
            case "Eliminar":
                eliminarMascota();
                break;
            case "Buscar por DNI":
                buscarMascota();
                break;
        }
    }
    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { 
            int selectedIndex = view.jListaMascotas.getSelectedIndex();
            if (selectedIndex >= 0) {
                String selectedMascota = view.modeloLista.getElementAt(selectedIndex);
            }
        }
    }

    private void cargarMascotas() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        List<Mascota> lista = model.getAll();
        if (lista != null) {
            view.modeloLista.clear();
            for (Mascota mascota : lista) {
                view.modeloLista.addElement(mascota.toString()); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay mascotas guardadas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarMascota() {
        String dni = view.textFieldDNI.getText();
        String nombre = view.textFieldNombre.getText();
        String especie = view.textFieldEspecie.getText();
        String fechaAdquisicionString = view.textFieldFechaAdquisicion.getText();

        Date fechaAdquisicion = null;
        try {
            fechaAdquisicion = new SimpleDateFormat("yyyy-MM-dd").parse(fechaAdquisicionString); 
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!model.dniExists(dni)) {
            JOptionPane.showMessageDialog(null, "El DNI no existe en la tabla de alumnos", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        Mascota mascota = new Mascota();
        mascota.setDNI(dni);
        mascota.setNombre(nombre);
        mascota.setEspecie(especie);
        mascota.setFechaAdquisicion(fechaAdquisicion); 

        boolean guardado = model.crear(mascota);
        if (guardado) {
            JOptionPane.showMessageDialog(null, "Se ha insertado correctamente la mascota", "Inserción correcta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un problema en la inserción", "Error inserción", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void eliminarMascota() {
        String id = view.textFieldID.getText();
        boolean eliminado = model.eliminarMascota(id); 
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente la mascota", "Eliminación correcta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ha habido un problema en la eliminación", "Error eliminación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarMascota() {
        String dni = view.textFieldDNI.getText();
        List<Mascota> mascotasBuscadas = model.getMascotasPorDNI(dni); 

        view.modeloLista.clear(); 

        if (!mascotasBuscadas.isEmpty()) {
            for (Mascota mascota : mascotasBuscadas) {
                view.modeloLista.addElement(mascota.toString()); 
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe mascotas con ese DNI", "Error búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

}

