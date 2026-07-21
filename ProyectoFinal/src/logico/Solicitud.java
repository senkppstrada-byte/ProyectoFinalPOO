package logico;

import java.time.LocalDate;

public class Solicitud {
	

private String id;
private Persona candidato;
private Oferta oferta;
private LocalDate fecha;
private float porCoincidencia;
private String estado;

public Solicitud(String id, Persona candidato, Oferta oferta, LocalDate fecha, float porCoincidencia,
			String estado) {
		super();
		this.id = id;
		this.candidato = candidato;
		this.oferta = oferta;
		this.fecha = fecha;
		this.porCoincidencia = porCoincidencia;
		this.estado = estado;
	}
public Persona getCandidato() {
	return candidato;
}
public void setCandidato(Persona candidato) {
	this.candidato = candidato;
}
public Oferta getOferta() {
	return oferta;
}
public void setOferta(Oferta oferta) {
	this.oferta = oferta;
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
