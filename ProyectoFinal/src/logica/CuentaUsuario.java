package logica;
import java.io.Serializable;

public class CuentaUsuario implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String id;
	private String correo;
	private String nombreUsuario;
	private String clave;
	private String rol;
	
	public CuentaUsuario(String id, String correo, String nombreUsuario, String clave, String rol) {
		super();
		this.id = id;
		this.correo = correo;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.rol = rol;
	}

	public boolean validarCredenciales(String cor, String nom, String cl, String r) {
		if ((correo.equals(cor)) && (nombreUsuario.equals(nom)) && (clave.equals(cl)) && (rol.equals(r))) {
			return true;
		}
		return false;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getId() {
		return id;
	}
}
