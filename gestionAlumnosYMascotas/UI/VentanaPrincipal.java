package gestionAlumnosYMascotas.UI;

import javax.swing.*;

import gestionAlumnosYMascotas.Controller.ControladorGestionAlumnos;
import gestionAlumnosYMascotas.Controller.ControladorGestionMascotas;
import gestionAlumnosYMascotas.Model.IModeloAlumnos;
import gestionAlumnosYMascotas.Model.IModeloMascotas;
import gestionAlumnosYMascotas.Model.ModeloAlumnoJDBC;
import gestionAlumnosYMascotas.Model.ModeloMascotaJDBC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    
    public JButton btnMascotas;
    public JButton btnInstituto;

    public VentanaPrincipal() {
        setTitle("Menú Principal");
        setLayout(new FlowLayout());
        
        btnMascotas = new JButton("Abrir Menú Mascotas");
        btnInstituto = new JButton("Abrir Menú Instituto");
        
        add(btnMascotas);
        add(btnInstituto);
        
        btnMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaMascotas ventanaMascotas = new VentanaMascotas(){

                    @Override

                    public void dispose(){
                        this.setVisible(true);

                        super.dispose();
                    }
                };
                
                IModeloMascotas modelo = new ModeloMascotaJDBC();
                ControladorGestionMascotas con = new ControladorGestionMascotas(modelo,ventanaMascotas);
                
                dispose();
            }
        });
        
        btnInstituto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaAlumnos ventanaAlumnos = new VentanaAlumnos(){

                    @Override

                    public void dispose(){
                        this.setVisible(true);

                        super.dispose();
                    }
                };
                
                IModeloAlumnos modelo = new ModeloAlumnoJDBC();
                ControladorGestionAlumnos con = new ControladorGestionAlumnos(modelo,ventanaAlumnos);
                
                dispose();
            }
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}

