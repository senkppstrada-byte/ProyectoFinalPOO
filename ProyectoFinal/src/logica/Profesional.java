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

}
