package logica;

public class Vacante {

	private String id;
	private CentroEmpleador centro;
	private String puesto;
	private String descripcion;
	private float salarioMin;
	private float salarioMax;
	private String provincia;
	private boolean requiereLicencia;
	private boolean requiereMudanza;
	private String perfilRequerido;
	private float coincidenciaMinima;
	private int plazasTotales;
	private int plazasOcupadas;
	private String estado;

	public Vacante(String id, CentroEmpleador centro, String puesto, String descripcion, float salarioMin,
			float salarioMax, String provincia, boolean requiereLicencia, boolean requiereMudanza,
			String perfilRequerido, float coincidenciaMinima, int plazasTotales) {
		this.id = id;
		this.centro = centro;
		this.puesto = puesto;
		this.descripcion = descripcion;
		this.salarioMin = salarioMin;
		this.salarioMax = salarioMax;
		this.provincia = provincia;
		this.requiereLicencia = requiereLicencia;
		this.requiereMudanza = requiereMudanza;
		this.perfilRequerido = perfilRequerido;
		this.coincidenciaMinima = coincidenciaMinima;
		this.plazasTotales = plazasTotales;
		this.plazasOcupadas = 0;
		this.estado = "ABIERTA";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CentroEmpleador getCentro() {
		return centro;
	}

	public void setCentro(CentroEmpleador centro) {
		this.centro = centro;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getSalarioMin() {
		return salarioMin;
	}

	public void setSalarioMin(float salarioMin) {
		this.salarioMin = salarioMin;
	}

	public float getSalarioMax() {
		return salarioMax;
	}

	public void setSalarioMax(float salarioMax) {
		this.salarioMax = salarioMax;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public boolean isRequiereLicencia() {
		return requiereLicencia;
	}

	public void setRequiereLicencia(boolean requiereLicencia) {
		this.requiereLicencia = requiereLicencia;
	}

	public boolean isRequiereMudanza() {
		return requiereMudanza;
	}

	public void setRequiereMudanza(boolean requiereMudanza) {
		this.requiereMudanza = requiereMudanza;
	}

	public String getPerfilRequerido() {
		return perfilRequerido;
	}

	public void setPerfilRequerido(String perfilRequerido) {
		this.perfilRequerido = perfilRequerido;
	}

	public float getCoincidenciaMinima() {
		return coincidenciaMinima;
	}

	public void setCoincidenciaMinima(float coincidenciaMinima) {
		this.coincidenciaMinima = coincidenciaMinima;
	}

	public int getPlazasTotales() {
		return plazasTotales;
	}

	public void setPlazasTotales(int plazasTotales) {
		this.plazasTotales = plazasTotales;
	}

	public int getPlazasOcupadas() {
		return plazasOcupadas;
	}

	public void setPlazasOcupadas(int plazasOcupadas) {
		this.plazasOcupadas = plazasOcupadas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean hayPlazasDisponibles() {
		return plazasOcupadas < plazasTotales;
	}
	
	public void ocuparPlaza() {
		if (hayPlazasDisponibles()) {
			plazasOcupadas = plazasOcupadas + 1;
			if (!hayPlazasDisponibles()) {
				estado = "CERRADA";
			}
		}
	}
	
}
