package visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logica.BolsaLaboral;
import logica.Candidato;
import logica.CentroEmpleador;
import logica.CuentaUsuario;
import logica.Obrero;
import logica.Profesional;
import logica.Tecnico;

public class RegistroCandidato extends JDialog {

	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JTextField txtCorreo;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JComboBox<String> cmbGenero;
	private JTextField txtProvincia;
	private JSpinner spnAspiracion;
	private JCheckBox chkLicencia;
	private JCheckBox chkMudanza;
	private JComboBox<String> cmbPerfil;
	private JTextField txtArea;
	private JSpinner spnExperiencia;
	private JTextField txtTitulo;
	private JTextField txtDestrezas;

	public RegistroCandidato() {
		setTitle("Registro de Candidato");
		setBounds(100, 100, 460, 620);
		setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel form = new JPanel();
		form.setLayout(new GridLayout(0, 2, 6, 6));
		contentPane.add(form, BorderLayout.CENTER);

		construirFormulario(form);

		JPanel panelBotones = new JPanel();
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		panelBotones.add(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelBotones.add(btnCancelar);

		contentPane.add(panelBotones, BorderLayout.SOUTH);
	}

	private void construirFormulario(JPanel form) {
	}

	public void registrar() {
	}

	private boolean usuarioExiste(String usuario) {
		return false;
	}
}