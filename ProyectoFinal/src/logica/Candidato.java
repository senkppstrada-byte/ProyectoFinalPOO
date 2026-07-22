package logica;

public class Candidato {

	protected String id;
	protected String cedula;
	protected String nombreCompleto;
	protected String genero;
	protected String provincia;
	protected float aspiracionSalarial;
	protected boolean tieneLicencia;
	protected boolean disponibleMudarse;
	protected CuentaUsuario cuenta;

	public Candidato(String id, String cedula, String nombreCompleto, String genero, String provincia,
			float aspiracionSalarial, boolean tieneLicencia, boolean disponibleMudarse, CuentaUsuario cuenta) {
		this.id = id;
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.genero = genero;
		this.provincia = provincia;
		this.aspiracionSalarial = aspiracionSalarial;
		this.tieneLicencia = tieneLicencia;
		this.disponibleMudarse = disponibleMudarse;
		this.cuenta = cuenta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public float getAspiracionSalarial() {
		return aspiracionSalarial;
	}

	public void setAspiracionSalarial(float aspiracionSalarial) {
		this.aspiracionSalarial = aspiracionSalarial;
	}

	public boolean isTieneLicencia() {
		return tieneLicencia;
	}

	public void setTieneLicencia(boolean tieneLicencia) {
		this.tieneLicencia = tieneLicencia;
	}

	public boolean isDisponibleMudarse() {
		return disponibleMudarse;
	}

	public void setDisponibleMudarse(boolean disponibleMudarse) {
		this.disponibleMudarse = disponibleMudarse;
	}

	public CuentaUsuario getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaUsuario cuenta) {
		this.cuenta = cuenta;
	}
}
