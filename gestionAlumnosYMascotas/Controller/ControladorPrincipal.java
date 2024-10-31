package gestionAlumnosYMascotas.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionAlumnosYMascotas.Model.IModeloAlumnos;
import gestionAlumnosYMascotas.Model.IModeloMascotas;
import gestionAlumnosYMascotas.UI.VentanaAlumnos;
import gestionAlumnosYMascotas.UI.VentanaMascotas;
import gestionAlumnosYMascotas.UI.VentanaPrincipal; 

public class ControladorPrincipal implements ActionListener {
    
    private VentanaPrincipal view;
    private IModeloMascotas model;
    private IModeloAlumnos model2;

    public ControladorPrincipal(IModeloMascotas model,IModeloAlumnos model2) {
        this.model = model;
        this.model2 = model2;
 
        this.view = new VentanaPrincipal();
        
        this.view.btnMascotas.addActionListener(this);
        this.view.btnInstituto.addActionListener(this);
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnMascotas) {
            abrirMenuMascotas();
        } else if (e.getSource() == view.btnInstituto) {
            abrirMenuInstituto();
        }
    }

    private void abrirMenuMascotas() {
        VentanaMascotas ventanaMascotas = new VentanaMascotas(); 
        new ControladorGestionMascotas(model, ventanaMascotas); 
        view.dispose(); 
    }

    private void abrirMenuInstituto() {
    	VentanaAlumnos ventanaAlumnos = new VentanaAlumnos(); 
        new ControladorGestionAlumnos(model2,ventanaAlumnos); 
        view.dispose();
    }
}
