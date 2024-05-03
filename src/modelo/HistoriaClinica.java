package modelo;

import java.time.LocalDate;

public class HistoriaClinica {
	
	private String Descripcion;
	private LocalDate Actual;
	

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public LocalDate getActual() {
		return Actual;
	}

	public void setActual(LocalDate actual) {
		Actual = actual;
	}
}
