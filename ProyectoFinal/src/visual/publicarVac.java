package visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import logica.BolsaLaboral;
import logica.CentroEmpleador;
import logica.Vacante;

public class publicarVac extends JDialog {

    private JTextField txtPuesto;
    private JTextField txtDescripcion;
    private JSpinner spnSalMin;
    private JSpinner spnSalMax;
    private JTextField txtProvincia;
    private JSpinner spnCoincidencia;
    private JSpinner spnPlazas;
    private JComboBox<String> cmbPerfil;
    private JCheckBox chkLicencia;
    private JCheckBox chkMudanza;
    private CentroEmpleador emp = BolsaLaboral.getInstancia()
            .buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());

    public publicarVac() {
        setTitle("Publicar vacante");
        setSize(480, 480);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(0, 12));
        contentPane.setBackground(Tema.FONDO);
        contentPane.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        setContentPane(contentPane);

        JLabel titulo = new JLabel("Publicar vacante");
        titulo.setFont(Tema.SUBTITULO);
        titulo.setForeground(Tema.TEXTO);
        contentPane.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBackground(Tema.FONDO);
        contentPane.add(form, BorderLayout.CENTER);

        form.add(new JLabel("Puesto:"));
        txtPuesto = new JTextField();
        form.add(txtPuesto);

        form.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        form.add(txtDescripcion);

        form.add(new JLabel("Salario mínimo:"));
        spnSalMin = new JSpinner(new SpinnerNumberModel(10000.0, 0.0, 1000000.0, 500.0));
        form.add(spnSalMin);

        form.add(new JLabel("Salario máximo:"));
        spnSalMax = new JSpinner(new SpinnerNumberModel(20000.0, 0.0, 1000000.0, 500.0));
        form.add(spnSalMax);

        form.add(new JLabel("Provincia:"));
        txtProvincia = new JTextField();
        form.add(txtProvincia);

        form.add(new JLabel("Perfil requerido:"));
        cmbPerfil = new JComboBox<String>(new String[] { "Tecnico", "Profesional", "Obrero" });
        form.add(cmbPerfil);

        form.add(new JLabel("Coincidencia mínima:"));
        spnCoincidencia = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100.0, 5.0));
        form.add(spnCoincidencia);

        form.add(new JLabel("Plazas totales:"));
        spnPlazas = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        form.add(spnPlazas);

        form.add(new JLabel("Requiere licencia:"));
        chkLicencia = new JCheckBox();
        chkLicencia.setBackground(Tema.FONDO);
        form.add(chkLicencia);

        form.add(new JLabel("Requiere mudanza:"));
        chkMudanza = new JCheckBox();
        chkMudanza.setBackground(Tema.FONDO);
        form.add(chkMudanza);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Tema.FONDO);

        JButton btnPublicar = Tema.botonPrimario("Publicar");
        btnPublicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                publicar();
            }
        });
        panelBotones.add(btnPublicar);

        JButton btnCancelar = Tema.botonSecundario("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelBotones.add(btnCancelar);

        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    public void publicar() {
    }s
}
