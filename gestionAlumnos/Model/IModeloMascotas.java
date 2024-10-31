package gestionAlumnos.Model;

import java.util.List;

public interface IModeloMascotas {
	
	public boolean crear(Mascota mascota);
	
	public List<Mascota> getAll();
    
	public boolean eliminarMascota(String dni);
    
	public List<Mascota> getMascotasPorDNI(String dni);

	public boolean dniExists(String dni);
	
    
}
