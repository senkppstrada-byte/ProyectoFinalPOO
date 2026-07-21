package logico;

import java.util.ArrayList;
public class Bolsa {
	
	private ArrayList<Empresa> lasEmpresas;
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Solicitud> lasSolicitudes;
	private static Bolsa bolsa = null;
	
	private Bolsa() {
		lasEmpresas = new ArrayList<>();
		lasPersonas = new ArrayList<>();
		lasOfertas = new ArrayList<>();
		lasSolicitudes = new ArrayList<>();
	}
	
	public static Bolsa getInstancia() {
		if(bolsa == null) {
			bolsa = new Bolsa();
		}
		return bolsa;
	}
	
	public ArrayList<Persona> conectarCandidatos(Oferta of) {
		ArrayList<Persona> cands = null;
		return cands;
 	}
	public ArrayList<Empresa> getLasEmpresas() {
		return lasEmpresas;
	}
	public void setLasEmpresas(ArrayList<Empresa> lasEmpresas) {
		this.lasEmpresas = lasEmpresas;
	}
	public ArrayList<Persona> getLasPersonas() {
		return lasPersonas;
	}
	public void setLasPersonas(ArrayList<Persona> lasPersonas) {
		this.lasPersonas = lasPersonas;
	}
	public ArrayList<Oferta> getLasOfertas() {
		return lasOfertas;
	}
	public void setLasOfertas(ArrayList<Oferta> lasOfertas) {
		this.lasOfertas = lasOfertas;
	}
	public ArrayList<Solicitud> getLasSolicitudes() {
		return lasSolicitudes;
	}
	public void setLasSolicitudes(ArrayList<Solicitud> lasSolicitudes) {
		this.lasSolicitudes = lasSolicitudes;
	}
	
	
	
	
	
	
}
