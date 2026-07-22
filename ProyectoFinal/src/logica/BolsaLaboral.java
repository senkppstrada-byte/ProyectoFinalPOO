package logica;

import java.util.ArrayList;

public class BolsaLaboral {

	private ArrayList<CentroEmpleador> centros;
	private ArrayList<Candidato> candidatos;
	private ArrayList<Vacante> vacantes;
	private ArrayList<Postulacion> postulaciones;

	public BolsaLaboral() {
		centros = new ArrayList<CentroEmpleador>();
		candidatos = new ArrayList<Candidato>();
		vacantes = new ArrayList<Vacante>();
		postulaciones = new ArrayList<Postulacion>();
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

}
