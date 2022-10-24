package modelo;


public class Palabra {
	private String cadena;
	private String cadenaRed;
	private String cadenaBlue;
	private String cadenaGreen;

	public Palabra(String c) {
		super();
		this.cadena = c;
		crearCadenaColor();
	}
	private void crearCadenaColor() {
		this.cadenaRed = "<font face=\"Tahoma\" color=\"red\">"+this.cadena+"</font><br>";
		this.cadenaBlue = "<font face=\"Tahoma\" color=\"blue\">"+this.cadena+"</font><br>";
		this.cadenaGreen = "<font face=\"Tahoma\" color=\"green\">"+this.cadena+"</font><br>";
	}
	public boolean comparar(Palabra p){
		boolean res = false;
		if(cadena.equalsIgnoreCase(p.getCadena())){
			res= true;
		}
		return res;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	public String getCadenaRed() {
		return cadenaRed;
	}
	public String getCadenaBlue() {
		return cadenaBlue;
	}
	public String getCadenaGreen() {
		return cadenaGreen;
	}
}
