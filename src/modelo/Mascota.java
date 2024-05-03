package modelo;

public class Mascota {

	private String nombre;
	private String raza;
	private int edad;
	private int Coddueño;
	private String tipo;
	private String Hc;
	

	

	public String getHc() {
		return Hc;
	}
	public void setHc(String hc) {
		Hc = hc;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCoddueño() {
		return Coddueño;
	}
	public int setCoddueño(int coddueño) {
		return Coddueño = coddueño;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	 public void setHc(HistoriaClinica hC2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
