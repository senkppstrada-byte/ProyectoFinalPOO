package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.CentroEmpleador;
import logica.Obrero;
import logica.Postulacion;
import logica.Profesional;
import logica.Tecnico;
import logica.Vacante;

public class SelCand extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private DefaultTableModel modelt;
    private Object[] rowt;
    private DefaultTableModel models;
    private Object[] rows;
    private Postulacion selectedt = null;
    private Postulacion selectedb = null;
    private JTable tbltop;
    private JButton okButton;
    private JButton cancelButton;
    private Vacante vacante;
    private CentroEmpleador emp = BolsaLaboral.getInstancia().buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
    private JPanel panelb;
    private JPanel panels;
    private JScrollPane spSels;
    private JButton btnbajar;
    private JButton btnsubir;
    private JTable tblSels;
    private ArrayList<Postulacion> listTop = new ArrayList<Postulacion>();
    private ArrayList<Postulacion> listBot = new ArrayList<Postulacion>();

    

}