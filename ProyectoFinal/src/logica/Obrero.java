package logica;

import java.util.ArrayList;

public class Obrero extends Candidato {

	private ArrayList<String> destrezas;

	public Obrero(String id, String cedula, String nombreCompleto, String genero, String provincia,
			float aspiracionSalarial, boolean tieneLicencia, boolean disponibleMudarse, CuentaUsuario cuenta,
			ArrayList<String> destrezas) {
		super(id, cedula, nombreCompleto, genero, provincia, aspiracionSalarial, tieneLicencia, disponibleMudarse,
				cuenta);
		if (destrezas == null) {
			this.destrezas = new ArrayList<String>();
		} else {
			this.destrezas = destrezas;
		}
	}

	public ArrayList<String> getDestrezas() {
		return destrezas;
	}

	public void setDestrezas(ArrayList<String> destrezas) {
		this.destrezas = destrezas;
	}

	
}
