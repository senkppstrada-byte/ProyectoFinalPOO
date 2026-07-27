package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.BolsaLaboral;
import logica.CentroEmpleador;
import logica.Vacante;

public class publicarVac extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private JTextField txtPuesto;
    private JTextField txtDescripcion;
    private JTextField txtSalMin;
    private JTextField txtSalMax;
    private JTextField txtProvincia;
    private JTextField txtCoincidencia;
    private JTextField txtPlazas;
    private JComboBox<String> cmbPerfil;
    private JCheckBox chkLicencia;
    private JCheckBox chkMudanza;
    private JButton okButton;
    private JButton cancelButton;
    private CentroEmpleador emp = BolsaLaboral.getInstancia().buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
} 