package modelo;

public class OrdenMedica {
		
	private String Antibioticos;
	private String Conducto;
	
	public OrdenMedica(String Antibioticos,String Conducto) {
		
		this.Antibioticos = Antibioticos;
		this.Conducto = Conducto;
		
	}

	public String getAntibioticos() {
		return Antibioticos;
	}

	public void setAntibioticos(String antibioticos) {
		Antibioticos = antibioticos;
	}

	public String getConducto() {
		return Conducto;
	}

	public void setConducto(String conducto) {
		Conducto = conducto;
	}
	
}
