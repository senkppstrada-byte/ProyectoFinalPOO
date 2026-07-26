package logica;

public class Tecnico extends Candidato {

	private String areaEspecialidad;
	private int aniosExperiencia;

	public Tecnico(String id, String cedula, String nombreCompleto, String genero, String provincia,
			float aspiracionSalarial, boolean tieneLicencia, boolean disponibleMudarse, CuentaUsuario cuenta,
			String areaEspecialidad, int aniosExperiencia) {
		super(id, cedula, nombreCompleto, genero, provincia, aspiracionSalarial, tieneLicencia, disponibleMudarse,
				cuenta);
		this.areaEspecialidad = areaEspecialidad;
		this.aniosExperiencia = aniosExperiencia;
	}

	public String getAreaEspecialidad() {
		return areaEspecialidad;
	}

	public void setAreaEspecialidad(String areaEspecialidad) {
		this.areaEspecialidad = areaEspecialidad;
	}

	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}

}
