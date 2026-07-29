package logica;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

public class BolsaLaboral implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static int generadorIdCuenta = 1;
	public static int generadorIdCand = 1;
	public static int generadorIdCent = 1;
	public static int generadorIdVac = 1;
	public static int generadorIdPos = 1;
	private ArrayList<CentroEmpleador> centros;
	private ArrayList<Candidato> candidatos;
	private ArrayList<Vacante> vacantes;
	private ArrayList<Postulacion> postulaciones;
	private static BolsaLaboral bolsa = null;
	private CuentaUsuario cuentalog;
	
	public static BolsaLaboral getInstancia() {
		if(bolsa == null) {
			bolsa = new BolsaLaboral();
		}
		return bolsa;
	}
	
	protected static void setInstancia(BolsaLaboral b) {
		bolsa = b;
	}

	private BolsaLaboral() {
		centros = new ArrayList<CentroEmpleador>();
		candidatos = new ArrayList<Candidato>();
		vacantes = new ArrayList<Vacante>();
		postulaciones = new ArrayList<Postulacion>();
		cuentalog = null;
	}
	
	public float calcMatch(Vacante vac, Candidato can) {
		float porcentaje = 0.0f;
		if (vac.getProvincia().equalsIgnoreCase(can.getProvincia())){
			porcentaje += 10;
		}
		if (vac.isRequiereLicencia() && can.isTieneLicencia()) {
			porcentaje += 10;
		}
		if (vac.isRequiereMudanza() && can.isDisponibleMudarse()) {
			porcentaje += 15;
		}
		
		if ((can.getAspiracionSalarial() >= vac.getSalarioMin()) && (can.getAspiracionSalarial() <= vac.getSalarioMax())) {
			porcentaje += 10;
		}
		
		if (vac.getPerfilRequerido().equalsIgnoreCase("tecnico") && can instanceof Tecnico) {
			porcentaje += 10;
			Tecnico tec = (Tecnico) can;
			porcentaje += (2 * tec.getAniosExperiencia());
		}
		else if (vac.getPerfilRequerido().equalsIgnoreCase("profesional") && can instanceof Profesional) {
			porcentaje += 10;
			Profesional pro = (Profesional) can;
			if (pro.getTituloUniversitario() != null && !pro.getTituloUniversitario().isEmpty()) {
				porcentaje += 15;
			}
		}
		else if (vac.getPerfilRequerido().equalsIgnoreCase("obrero") && can instanceof Obrero) {
			porcentaje += 10;
			Obrero ob = (Obrero) can;
			if (ob.getDestrezas() != null) {
			    float puntosDestrezas = 2.0f * ob.getDestrezas().size();
			    
			    if (puntosDestrezas > 20.0f) {
			        puntosDestrezas = 20.0f;
			    }
			    
			    porcentaje += puntosDestrezas;
			}
		}
		
		if (porcentaje > 100.0f) {
	        porcentaje = 100.0f;
	    }
		
		return porcentaje;
	}
	
	public ArrayList<Postulacion> conectarCandidatos(Vacante vac) {
		
		ArrayList<Postulacion> cands = new ArrayList<>();
	    if (vac == null) {
	    	return cands;
	    }
	    Postulacion max1 = null;
	    Postulacion max2 = null;
	    Postulacion max3 = null;
	    
	    for (Postulacion p : postulaciones) {
	        if (p.getVacante() != null && p.getVacante().getId().equals(vac.getId())) {
	            if (p.getPorCoincidencia() >= vac.getCoincidenciaMinima()) {
	                if (max1 == null || p.getPorCoincidencia() > max1.getPorCoincidencia()) {
	                    max1 = p;
	                }
	            }
	        }
	    }

	    for (Postulacion p : postulaciones) {
	        if (p.getVacante() != null && p.getVacante().getId().equals(vac.getId())) {
	            if (p.getPorCoincidencia() >= vac.getCoincidenciaMinima() && p != max1) {
	                if (max2 == null || p.getPorCoincidencia() > max2.getPorCoincidencia()) {
	                    max2 = p;
	                }
	            }
	        }
	    }

	    for (Postulacion p : postulaciones) {
	        if (p.getVacante() != null && p.getVacante().getId().equals(vac.getId())) {
	            if (p.getPorCoincidencia() >= vac.getCoincidenciaMinima() && p != max1 && p != max2) {
	                if (max3 == null || p.getPorCoincidencia() > max3.getPorCoincidencia()) {
	                    max3 = p;
	                }
	            }
	        }
	    }

	    if (max1 != null) cands.add(max1);
	    if (max2 != null) cands.add(max2);
	    if (max3 != null) cands.add(max3);

	    return cands;
 	}
	
	public ArrayList<CentroEmpleador> getCentros() {
		return centros;
	}

	public void setCentros(ArrayList<CentroEmpleador> centros) {
		this.centros = centros;
	}

	public ArrayList<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(ArrayList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public ArrayList<Vacante> getVacantes() {
		return vacantes;
	}

	public void setVacantes(ArrayList<Vacante> vacantes) {
		this.vacantes = vacantes;
	}

	public ArrayList<Postulacion> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(ArrayList<Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public void registrarCandidato(Candidato candidato) {
		candidatos.add(candidato);
	}

	public void registrarCentro(CentroEmpleador centro) {
		centros.add(centro);
	}

	public void publicarVacante(Vacante vac) {
		vacantes.add(vac);
	}
	
	public Candidato buscarCandidato(String ced) {
		Candidato cand = null;
		int i = 0;
		boolean enc = false;
		while (!enc && i<candidatos.size()) {
			if(candidatos.get(i).getCedula().equalsIgnoreCase(ced)) {
				cand = candidatos.get(i);
				enc = true;
			}
			i++;
		}
		return cand;
	}
	public void publicarPostulacion(Postulacion p1) {
		postulaciones.add(p1);
	}
	

	public Candidato buscarCandidatoPorCuenta(CuentaUsuario cuenta) {
	    for (Candidato c : candidatos) {
	        if (c.getCuenta() != null && c.getCuenta().getId().equals(cuenta.getId())) {
	            return c; 
	        }
	    }
	    return null;
	}
	
	public CentroEmpleador buscarCentroPorCuenta(CuentaUsuario cuenta) {
	    for (CentroEmpleador c : centros) {
	        if (c.getRep().getCuenta() != null && c.getRep().getCuenta().getId().equals(cuenta.getId())) {
	            return c; 
	        }
	    }
	    return null;
	}

	public Vacante buscarVacPorId(String string) {
		for (Vacante v : vacantes) {
	        if (v.getId().equals(string)) {
	            return v; 
	        }
	    }
	    return null;
	}

	public CuentaUsuario getCuentalog() {
		return cuentalog;
	}

	public void setCuentalog(CuentaUsuario cuentalog) {
		this.cuentalog = cuentalog;
	}

	public Postulacion buscarPosPorId(String string) {
		for (Postulacion p : postulaciones) {
	        if (p.getId().equals(string)) {
	            return p; 
	        }
	    }
	    return null;
	}
	
	public void saveDatos() {
	    
	    try (FileOutputStream fos = new FileOutputStream("bolsa_laboral.dat");
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
	        
	        oos.writeObject(this); 
	        System.out.println("Datos guardados correctamente.");

	    } catch (IOException e) {
	        System.err.println("Error al guardar los datos: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public static void cargarDatos() {
	    File archivo = new File("bolsa_laboral.dat");
	    
	    if (!archivo.exists()) {
	        System.out.println("No se encontro archivo existente. Se iniciará una sesion limpia.");
	        return;
	    }

	    try (FileInputStream fis = new FileInputStream(archivo);
	         ObjectInputStream ois = new ObjectInputStream(fis)) {
	        
	        bolsa = (BolsaLaboral) ois.readObject();
	        System.out.println("Datos cargados correctamente.");

	    } catch (IOException | ClassNotFoundException e) {
	        System.err.println("Error al cargar los datos: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
}
