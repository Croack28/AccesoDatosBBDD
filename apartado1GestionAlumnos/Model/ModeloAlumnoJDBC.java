package reto1Unidad2CRUD.apartado1GestionAlumnos.Model;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloAlumnoJDBC implements IModeloAlumnos {

	private static String cadenaConexion =  "jdbc:mysql://localhost:3306/adat";
	private static String user = "dam2";
	private static String pass = "asdf.1234";
	private static Connection conexion;
	
	
	public ModeloAlumnoJDBC()  {
		
		try {
			conexion = DriverManager.getConnection(cadenaConexion,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> getAll() {
		String sentenciaSQL ="select * from instituto;";
		PreparedStatement sentencia;
		try {
			sentencia = conexion.prepareStatement(sentenciaSQL);
			ResultSet resultado = sentencia.executeQuery();
			
			 if (resultado.isBeforeFirst()) {
				 List<String> listaAlumnos = new ArrayList<String>();
				 Alumno alumnoAux ;
				 
				 while(resultado.next()) {
					 alumnoAux=new Alumno();
					 alumnoAux.setDNI(resultado.getString(1));
					 alumnoAux.setNombre(resultado.getString(2));
					 alumnoAux.setApellidos(resultado.getString(3));
					 alumnoAux.setCP(resultado.getString(4));
					 listaAlumnos.add(alumnoAux.toString());
				
			}
				 return listaAlumnos;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Alumno getAlumnoPorDNI(String DNI) {
		String sentenciaSQL ="select * from instituto where dni like '"+DNI+"';";
		System.out.println(sentenciaSQL);
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
			ResultSet resultado = sentencia.executeQuery();
			 if (resultado.isBeforeFirst()) { // Si no hay ninguna fila antes de la primera, el ResultSet está vacío
	             
	            
			Alumno alumno = new Alumno();
			while(resultado.next()) {
				alumno.setDNI(resultado.getString(1));
				alumno.setNombre(resultado.getString(2));
				alumno.setApellidos(resultado.getString(3));
				alumno.setCP(resultado.getString(4));
			}
			System.out.println(alumno);
			//if(alumno.toString())
			return alumno;
			 }
			 else {
				 return null;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int modificarAlumno(Alumno alumno) {
		// SET nombre = ?, apellidos = ? 
		String set = "";
		
		if(!alumno.getNombre().equals("")) {
			set += " nombre = '"+alumno.getNombre()+"',";
		}
		if(!alumno.getApellidos().equals("")) {
			set += " apellidos = '"+alumno.getApellidos()+"',";
		}
		if(!alumno.getCP().equals("")) {
			set += " cp = '"+alumno.getCP()+"',";
		}
		
		if (!set.equals("")  && set .endsWith(",")) {
			StringBuilder sb = new StringBuilder(set);
            sb.deleteCharAt(sb.length() - 1); // Elimina el último carácter (la coma)
            set = sb.toString();
		}
		
		String sentenciaSQL ="update instituto set"+set+" where dni like '"+alumno.getDNI()+"';";
		System.out.println(sentenciaSQL);
		
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
			 int rowsAffected = sentencia.executeUpdate();
			 if(rowsAffected==0) {
				 return 1;
			 }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 2;
		}
		
		return 0;
	}

	@Override
	public boolean eliminarAlumno(String DNI) {
		String sentenciaSQL ="delete from instituto where dni like '"+DNI+"';";
		System.out.println(sentenciaSQL);
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
			 int rowsAffected = sentencia.executeUpdate();
			 if(rowsAffected!=0)
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean crear(Alumno alumno) {
		String sentenciaSQL = "INSERT INTO instituto (dni, nombre, apellidos,cp)"
				+ "VALUES('"+alumno.getDNI()+"','"+alumno.getNombre()
				+"','"+alumno.getApellidos()+"','"+alumno.getCP()+"')";
		System.out.println(sentenciaSQL);
		try {
			PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
			sentencia.executeQuery();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return false;
	}
	
	

}