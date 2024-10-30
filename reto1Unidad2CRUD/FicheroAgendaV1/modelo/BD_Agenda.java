package reto1Unidad2CRUD.FicheroAgendaV1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BD_Agenda {
	
	private static String cadenaConexion =  "jdbc:mysql://localhost:3306/adat2";
	private static String user = "root";
	private static String pass = "Miprima2+";
	private static Connection conexion;
	
	
	public BD_Agenda()  {
		
		try {
			conexion = DriverManager.getConnection(cadenaConexion,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Contacto> leeContactos() {
		String sentenciaSQL ="select * from contactos;";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sentenciaSQL);
			ResultSet resultado = sentencia.executeQuery();
			
			if(resultado.isBeforeFirst()){
				Contacto contactoAux;
				ArrayList<Contacto> lista = new ArrayList<Contacto>();
				while(resultado.next()) {
					contactoAux = new Contacto(resultado.getString(1), resultado.getString(2));
					lista.add(contactoAux);
				}
				return lista;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public boolean escribeRegistro(Contacto contacto) {
		String sentenciaSQL = "INSERT INTO contactos (nombre,telefono)"
				+ "VALUES('"+contacto.getNombre()+"','"+contacto.getTeléfono()
				+"')";
		PreparedStatement sentencia;
		
		
		
		try {
			sentencia = conexion.prepareStatement(sentenciaSQL);
			ResultSet resultado = sentencia.executeQuery();
			return true;
		} catch (SQLException e) {
			//Esto se hace ya que , si da error, significa que el contacto ya existe entonces habrá que actualizarlo
			if(!contacto.teléfono.equals("")) {
			sentenciaSQL = "update contactos set telefono='"+contacto.teléfono+"' where nombre='"+contacto.nombre+"';";
			try {
				sentencia = conexion.prepareStatement(sentenciaSQL);
				sentencia.executeQuery();
				return true;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		return false;
	
		
	}
	public boolean borraRegistro(Contacto contacto){
		String sentenciaSQL = "delete from contactos where nombre ='"+contacto.nombre+"';";
		PreparedStatement sentencia ;
		
		
		try {
			
			sentencia = conexion.prepareStatement(sentenciaSQL);
			int rowsAffected = sentencia.executeUpdate();
			if(rowsAffected!=0)
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	
}
