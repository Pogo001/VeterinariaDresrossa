package modelo;

import java.io.Serializable;


public class Usuario implements Serializable {

	private String nombre;
	int id;
	private String username;
	private String password;
	private String rol;
	private String rutaFotoPerfil; 
	// Constructor
	public Usuario(String nombre, int id, String username, String password, String rol, String rutaFotoPerfil) {
		this.nombre = nombre;
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.rutaFotoPerfil = rutaFotoPerfil;

	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRutaFotoPerfil() {
		return rutaFotoPerfil;
	}

	public void setRutaFotoPerfil(String rutaFotoPerfil) {
		this.rutaFotoPerfil = rutaFotoPerfil;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String toString() {
		return "Usuario [username= "  + username + ", rol= "  + rol + ", Contraseña=  ]" + password;
	}



	
}