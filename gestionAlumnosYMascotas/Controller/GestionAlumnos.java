package gestionAlumnosYMascotas.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import gestionAlumnosYMascotas.UI.VentanaPrincipal;

public class GestionAlumnos {

	public static void main(String[] args) {
		 try {
			 VentanaPrincipal ventana = new VentanaPrincipal();
			 crearTablasSiNoExisten();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void crearTablasSiNoExisten() {
		String cadenaConexion =  "jdbc:mysql://localhost:3306/adat2";
		String user = "dam2";
		String pass = "asdf.1234";
		Connection conexion;
		
		
		try {
            conexion = DriverManager.getConnection(cadenaConexion, user, pass);
            String sql = "CREATE TABLE IF NOT EXISTS instituto (" +
                    "dni VARCHAR(10) NOT NULL, " +
                    "nombre VARCHAR(50) NOT NULL, " +
                    "apellidos VARCHAR(100) NOT NULL, " +
                    "cp VARCHAR(5), " +
                    "PRIMARY KEY (dni))";
            
            String sql2 = "CREATE TABLE IF NOT EXISTS mascotas (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(50) NOT NULL, " +
                    "especie VARCHAR(50) NOT NULL, " +
                    "dni_alumno VARCHAR(10), " +
                    "fecha_adquisicion DATE NOT NULL, " +
                    "FOREIGN KEY (dni_alumno) REFERENCES instituto(dni))";
            
            Statement stmt = conexion.createStatement();
            stmt.execute(sql);
       
      
            Statement stmt2 = conexion.createStatement(); 
            stmt2.execute(sql2);
       
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
