package gestionAlumnos.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMascotas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextField textFieldID;       
    public JTextField textFieldDNI;     
    public JTextField textFieldNombre;      
    public JTextField textFieldEspecie;    
    public JTextField textFieldFechaAdquisicion; 
    public JButton btnCargarTodos;        
    public JButton btnCrear;                
    public JButton btnEliminar;              
    public JButton btnBuscar;                
    public JButton btnVolver;
    private JScrollPane scrollPane;
    public JList<String> jListaMascotas;   
    public DefaultListModel<String> modeloLista; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaMascotas frame = new VentanaMascotas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaMascotas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        modeloLista = new DefaultListModel<>();
        jListaMascotas = new JList<String>(modeloLista);
        jListaMascotas.setVisibleRowCount(-1);
        jListaMascotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListaMascotas.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setViewportView(jListaMascotas);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.EAST);
        panel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel lblID = new JLabel("ID Mascota (solo para eliminar)");
        panel.add(lblID);
        
        textFieldID = new JTextField();
        panel.add(textFieldID);
        textFieldID.setColumns(10);
        
        JLabel lblDNI = new JLabel("DNI Alumno");
        panel.add(lblDNI);
        
        textFieldDNI = new JTextField();
        panel.add(textFieldDNI);
        textFieldDNI.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre Mascota");
        panel.add(lblNombre);
        
        textFieldNombre = new JTextField();
        panel.add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblEspecie = new JLabel("Especie");
        panel.add(lblEspecie);
        
        textFieldEspecie = new JTextField();
        panel.add(textFieldEspecie);
        textFieldEspecie.setColumns(10);
        
        JLabel lblFecha = new JLabel("Fecha adquisicion");
        panel.add(lblFecha);
        
        textFieldFechaAdquisicion = new JTextField();
        panel.add(textFieldFechaAdquisicion);
        textFieldFechaAdquisicion.setColumns(10);
        
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        
        btnCargarTodos = new JButton("Cargar Todos");
        panel_1.add(btnCargarTodos);
        
        btnCrear = new JButton("Crear Nueva");
        btnCrear.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(btnCrear);
        
        btnBuscar = new JButton("Buscar por DNI");
        btnBuscar.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(btnBuscar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(btnEliminar);
        
        btnVolver = new JButton("Volver");
        btnVolver.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_1.add(btnVolver);
        
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(){

                    @Override

                    public void dispose(){
                        this.setVisible(true);

                        super.dispose();
                    }
                };
                dispose();
            }

        });
    }
}

