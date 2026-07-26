package logica;

import java.util.ArrayList;

public class BolsaLaboral {
	public static int generadorIdCuenta = 1;
	public static int generadorIdCand = 1;
	public static int generadorIdCent = 1;
	public static int generadorIdVac = 1;
	public static int generadorIdPos = 1;
	private ArrayList<CentroEmpleador> centros;
	private ArrayList<Candidato> candidatos;
	private ArrayList<Vacante> vacantes;
	private ArrayList<Postulacion> postulaciones;
	private static BolsaLaboral bolsa = null;
	
	public static BolsaLaboral getInstancia() {
		if(bolsa == null) {
			bolsa = new BolsaLaboral();
		}
		return bolsa;
	}

	public BolsaLaboral() {
		centros = new ArrayList<CentroEmpleador>();
		candidatos = new ArrayList<Candidato>();
		vacantes = new ArrayList<Vacante>();
		postulaciones = new ArrayList<Postulacion>();
	}
	
	public ArrayList<Candidato> conectarCandidatos(Vacante vac) {
		ArrayList<Candidato> cands = null;
		
		return cands;
 	}
	
	public ArrayList<CentroEmpleador> getCentros() {
		return centros;
	}

	public void setCentros(ArrayList<CentroEmpleador> centros) {
		this.centros = centros;
	}

	public ArrayList<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(ArrayList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public ArrayList<Vacante> getVacantes() {
		return vacantes;
	}

	public void setVacantes(ArrayList<Vacante> vacantes) {
		this.vacantes = vacantes;
	}

	public ArrayList<Postulacion> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(ArrayList<Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public void registrarCandidato(Candidato candidato) {
		candidatos.add(candidato);
	}

	public void registrarCentro(CentroEmpleador centro) {
		centros.add(centro);
	}

	public void publicarVacante(Vacante vac) {
		vacantes.add(vac);
	}
	
	public Candidato buscarCandidato(String ced) {
		Candidato cand = null;
		int i = 0;
		boolean enc = false;
		while (!enc && i<candidatos.size()) {
			if(candidatos.get(i).getCedula().equalsIgnoreCase(ced)) {
				cand = candidatos.get(i);
				enc = true;
			}
			i++;
		}
		return cand;
	}
	public void publicarPostulacion(Postulacion p1) {
		postulaciones.add(p1);
	}
	

	public Candidato buscarCandidatoPorCuenta(CuentaUsuario cuenta) {
	    for (Candidato c : candidatos) {
	        if (c.getCuenta() != null && c.getCuenta().getId().equals(cuenta.getId())) {
	            return c; 
	        }
	    }
	    return null;
	}

	public Vacante buscarVacPorId(String string) {
		for (Vacante v : vacantes) {
	        if (v.getId().equals(string)) {
	            return v; 
	        }
	    }
	    return null;
	}
}
