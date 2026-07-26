package logica;

public class Profesional extends Candidato {

	private String tituloUniversitario;

	public Profesional(String id, String cedula, String nombreCompleto, String genero, String provincia,
			float aspiracionSalarial, boolean tieneLicencia, boolean disponibleMudarse, CuentaUsuario cuenta,
			String tituloUniversitario) {
		super(id, cedula, nombreCompleto, genero, provincia, aspiracionSalarial, tieneLicencia, disponibleMudarse,
				cuenta);
		this.tituloUniversitario = tituloUniversitario;
	}

	public String getTituloUniversitario() {
		return tituloUniversitario;
	}

	public void setTituloUniversitario(String tituloUniversitario) {
		this.tituloUniversitario = tituloUniversitario;
	}

	@Override
	public int evaluarRequisitos(Vacante vacante) {
		int puntos = 0;

		if (vacante.getPerfilRequerido().equalsIgnoreCase("PROFESIONAL")) {
			puntos = puntos + 20;
		} else {
			puntos = puntos + 5;
		}

		if (tituloUniversitario != null && !tituloUniversitario.trim().isEmpty()) {
			puntos = puntos + 10;
		}

		return puntos;
	}

	@Override
	public String toString() {
		return "PROFESIONAL " + super.toString() + " | Titulo: " + tituloUniversitario;
	}
}
