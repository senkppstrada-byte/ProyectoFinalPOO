package logico;

public class Oferta {

	
	private String id;
	private Empresa empresa;
	private String titulo;
	private String descripcion;
	private float salarioMin;
	private float salarioMax;
	private boolean licenciaNece;
	private boolean dispMudar;
	private String tipoCandidato;
	private float minCoincidencia;
	private int cantPuestos;
	private int puestosTomados;
	private String estado;
	
	
	public Oferta(String id, Empresa empresa, String titulo, String descripcion, float salarioMin, float salarioMax,
			boolean licenciaNece, boolean dispMudar, String tipoCandidato, float minCoincidencia, int cantPuestos,
			int puestosTomados, String estado) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.salarioMin = salarioMin;
		this.salarioMax = salarioMax;
		this.licenciaNece = licenciaNece;
		this.dispMudar = dispMudar;
		this.tipoCandidato = tipoCandidato;
		this.minCoincidencia = minCoincidencia;
		this.cantPuestos = cantPuestos;
		this.puestosTomados = puestosTomados;
		this.estado = estado;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public boolean isLicenciaNece() {
		return licenciaNece;
	}
	public void setLicenciaNece(boolean licenciaNece) {
		this.licenciaNece = licenciaNece;
	}
	public boolean isDispMudar() {
		return dispMudar;
	}
	public void setDispMudar(boolean dispMudar) {
		this.dispMudar = dispMudar;
	}
	public String getTipoCandidato() {
		return tipoCandidato;
	}
	public void setTipoCandidato(String tipoCandidato) {
		this.tipoCandidato = tipoCandidato;
	}
	public float getMinCoincidencia() {
		return minCoincidencia;
	}
	public void setMinCoincidencia(float minCoincidencia) {
		this.minCoincidencia = minCoincidencia;
	}
	public int getCantPuestos() {
		return cantPuestos;
	}
	public void setCantPuestos(int cantPuestos) {
		this.cantPuestos = cantPuestos;
	}
	public int getPuestosTomados() {
		return puestosTomados;
	}
	public void setPuestosTomados(int puestosTomados) {
		this.puestosTomados = puestosTomados;
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
