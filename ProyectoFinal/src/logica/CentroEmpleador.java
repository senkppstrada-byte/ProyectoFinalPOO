package logica;
import java.io.Serializable;

public class CentroEmpleador implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombreComercial;
	private String tipoCentro;
	private String direccion;
	private float indDesemp;
	private Representante rep;
	
	
	public CentroEmpleador(String id, String nombreComercial, String tipoCentro, String direccion, float indDesemp,
			Representante rep) {
		super();
		this.id = id;
		this.nombreComercial = nombreComercial;
		this.tipoCentro = tipoCentro;
		this.direccion = direccion;
		this.indDesemp = indDesemp;
		this.rep = rep;
	}
	
	
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getTipoCentro() {
		return tipoCentro;
	}
	public void setTipoCentro(String tipoCentro) {
		this.tipoCentro = tipoCentro;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public float getIndDesemp() {
		return indDesemp;
	}
	public void setIndDesemp(float indDesemp) {
		this.indDesemp = indDesemp;
	}
	public Representante getRep() {
		return rep;
	}
	public void setRep(Representante rep) {
		this.rep = rep;
	}
	public String getId() {
		return id;
	}
	
}
