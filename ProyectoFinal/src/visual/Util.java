package visual;

import logica.BolsaLaboral;
import logica.Candidato;
import logica.CentroEmpleador;
import logica.Obrero;
import logica.Postulacion;
import logica.Profesional;
import logica.Tecnico;

public class Util {

    public static boolean estaDisponible(Candidato c) {
        for (Postulacion p : BolsaLaboral.getInstancia().getPostulaciones()) {
            if (p.getCandidato() != null && p.getCandidato().getId().equals(c.getId())
                    && p.getEstado().equalsIgnoreCase("seleccionada")) {
                return false;
            }
        }
        return true;
    }

    public static String perfil(Candidato c) {
        if (c instanceof Tecnico) {
            return "Tecnico";
        }
        if (c instanceof Profesional) {
            return "Profesional";
        }
        if (c instanceof Obrero) {
            return "Obrero";
        }
        return "";
    }

    public static boolean usuarioExiste(String usuario) {
        BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        for (Candidato c : bolsa.getCandidatos()) {
            if (c.getCuenta() != null && c.getCuenta().getNombreUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
        }
        for (CentroEmpleador ce : bolsa.getCentros()) {
            if (ce.getRep() != null && ce.getRep().getCuenta() != null
                    && ce.getRep().getCuenta().getNombreUsuario().equalsIgnoreCase(usuario)) {
                return true;
            }
        }
        return false;
    }
}