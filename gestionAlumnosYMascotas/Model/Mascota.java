package gestionAlumnosYMascotas.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Mascota {
    private String id;
    private String dni;
    private String nombre;
    private String especie;
    private Date fechaAdquisicion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaAdquisicionStr = (fechaAdquisicion != null) ? dateFormat.format(fechaAdquisicion) : "Fecha no disponible";

        return String.format("ID: %s, DNI: %s, Nombre: %s( %s ), Fecha de Adquisición: %s",id, dni, nombre, especie, fechaAdquisicionStr);
    }
}


