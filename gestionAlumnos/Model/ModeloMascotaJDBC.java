package gestionAlumnos.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloMascotaJDBC implements IModeloMascotas {

	private static String cadenaConexion =  "jdbc:mysql://localhost:3306/adat2";
	private static String user = "dam2";
	private static String pass = "asdf.1234";
    private static Connection conexion;

    public ModeloMascotaJDBC() {
        try {
            conexion = DriverManager.getConnection(cadenaConexion, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    
    public boolean dniExists(String dni) {
        String query = "SELECT COUNT(*) FROM instituto WHERE dni = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    @Override
    public List<Mascota> getAll() {
        String sentenciaSQL = "SELECT * FROM mascotas;";
        List<Mascota> listaMascotas = new ArrayList<>();
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Mascota mascotaAux = new Mascota();
                mascotaAux.setId(resultado.getString("id"));
                mascotaAux.setDNI(resultado.getString("dni_alumno")); 
                mascotaAux.setNombre(resultado.getString("nombre"));
                mascotaAux.setEspecie(resultado.getString("especie"));
                java.sql.Date fechaSQL = resultado.getDate("fecha_adquisicion");
                mascotaAux.setFechaAdquisicion(fechaSQL);
                listaMascotas.add(mascotaAux);
            }
            return listaMascotas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Mascota> getMascotasPorDNI(String dni) {
        List<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM mascotas WHERE dni_alumno = ?"; 

        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setId(rs.getString("id"));
                mascota.setDNI(rs.getString("dni_alumno"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                java.sql.Date fechaSQL = rs.getDate("fecha_adquisicion");
                mascota.setFechaAdquisicion(fechaSQL);
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mascotas;
    }

    @Override
    public boolean crear(Mascota mascota) {
        String sentenciaSQL = "INSERT INTO mascotas (dni_alumno, nombre, especie, fecha_adquisicion) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, mascota.getDNI());
            sentencia.setString(2, mascota.getNombre());
            sentencia.setString(3, mascota.getEspecie());
            sentencia.setDate(4, new java.sql.Date(mascota.getFechaAdquisicion().getTime()));
            sentencia.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean eliminarMascota(String id) {
        String sentenciaSQL = "DELETE FROM mascotas WHERE id = ?;";
        try {
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, id);
            int rowsAffected = sentencia.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

