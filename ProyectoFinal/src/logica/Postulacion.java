package logica;

import java.time.LocalDate;

public class Postulacion {

	private String id;
	private Candidato candidato;
	private Vacante vacante;
	private LocalDate fecha;
	private float porCoincidencia;
	private String estado;
	
	
	public Postulacion(String id, Candidato candidato, Vacante vacante, LocalDate fecha, float porCoincidencia,
			String estado) {
		super();
		this.id = id;
		this.candidato = candidato;
		this.vacante = vacante;
		this.fecha = fecha;
		this.porCoincidencia = porCoincidencia;
		this.estado = estado;
	}
	
	
	public void actualizarEstado(String s) {
		
	}
	

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Vacante getVacante() {
		return vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public float getPorCoincidencia() {
		return porCoincidencia;
	}

	public void setPorCoincidencia(float porCoincidencia) {
		this.porCoincidencia = porCoincidencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getId() {
		return id;
	}
}
