package logica;

public class Representante {

	
	private String id;
	private String cedula;
	private String nombreCompleto;
	private CuentaUsuario cuenta;
	
	public Representante(String id, String cedula, String nombreCompleto, CuentaUsuario cuenta) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.cuenta = cuenta;
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
	public CuentaUsuario getCuenta() {
		return cuenta;
	}
	public void setCuenta(CuentaUsuario cuenta) {
		this.cuenta = cuenta;
	}
	public String getId() {
		return id;
	}
	
	
	
	
}
