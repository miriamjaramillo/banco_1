package ec.mjaramillo.persona.model.entity.persona;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import ec.mjaramillo.persona.model.entity.Persona;


@Entity
@Table(name = "cliente")
public class Cliente extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String contrasena;
	private String estado;
	
	public Cliente() {	
	}
	
	public Cliente(String identificacion, String nombre, String genero, Integer edad, String direccion,
			String telefono, String estado, String contrasena) {
		super(identificacion, nombre, genero, edad, direccion, telefono);
		this.estado = estado;
		this.contrasena = contrasena;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
