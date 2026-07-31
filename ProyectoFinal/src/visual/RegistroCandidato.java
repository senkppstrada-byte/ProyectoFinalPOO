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
		form.add(new JLabel("Usuario:"));
		txtUsuario = new JTextField();
		form.add(txtUsuario);

		form.add(new JLabel("Clave:"));
		txtClave = new JPasswordField();
		form.add(txtClave);

		form.add(new JLabel("Correo:"));
		txtCorreo = new JTextField();
		form.add(txtCorreo);

		form.add(new JLabel("Nombre completo:"));
		txtNombre = new JTextField();
		form.add(txtNombre);

		form.add(new JLabel("Cedula:"));
		txtCedula = new JTextField();
		form.add(txtCedula);

		form.add(new JLabel("Genero:"));
		cmbGenero = new JComboBox<String>(new String[] { "M", "F" });
		form.add(cmbGenero);

		form.add(new JLabel("Provincia:"));
		txtProvincia = new JTextField();
		form.add(txtProvincia);

		form.add(new JLabel("Aspiracion salarial:"));
		spnAspiracion = new JSpinner(new SpinnerNumberModel(15000.0, 0.0, 1000000.0, 500.0));
		form.add(spnAspiracion);

		form.add(new JLabel("Licencia de conducir:"));
		chkLicencia = new JCheckBox();
		form.add(chkLicencia);

		form.add(new JLabel("Disponible a mudarse:"));
		chkMudanza = new JCheckBox();
		form.add(chkMudanza);

		form.add(new JLabel("Perfil:"));
		cmbPerfil = new JComboBox<String>(new String[] { "Tecnico", "Profesional", "Obrero" });
		form.add(cmbPerfil);

		form.add(new JLabel("Area (Tecnico):"));
		txtArea = new JTextField();
		form.add(txtArea);

		form.add(new JLabel("Anios experiencia (Tecnico):"));
		spnExperiencia = new JSpinner(new SpinnerNumberModel(0, 0, 60, 1));
		form.add(spnExperiencia);

		form.add(new JLabel("Titulo (Profesional):"));
		txtTitulo = new JTextField();
		form.add(txtTitulo);

		form.add(new JLabel("Destrezas (Obrero, separadas por coma):"));
		txtDestrezas = new JTextField();
		form.add(txtDestrezas);
	}

	public void registrar() {
		String usuario = txtUsuario.getText().trim();
		String clave = new String(txtClave.getPassword()).trim();
		String correo = txtCorreo.getText().trim();
		String nombre = txtNombre.getText().trim();
		String cedula = txtCedula.getText().trim();
		String genero = (String) cmbGenero.getSelectedItem();
		String provincia = txtProvincia.getText().trim();
		String perfil = (String) cmbPerfil.getSelectedItem();

		if (usuario.isEmpty() || clave.isEmpty() || correo.isEmpty() || nombre.isEmpty() || cedula.isEmpty()
				|| provincia.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Completa todos los campos obligatorios.", "Faltan datos",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (usuarioExiste(usuario)) {
			JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya existe.", "Usuario ocupado",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		BolsaLaboral bolsa = BolsaLaboral.getInstancia();
		float aspiracion = ((Number) spnAspiracion.getValue()).floatValue();
		boolean licencia = chkLicencia.isSelected();
		boolean mudanza = chkMudanza.isSelected();

		CuentaUsuario cuenta = new CuentaUsuario("CU-" + BolsaLaboral.generadorIdCuenta, correo, usuario, clave,
				"candidato");
		BolsaLaboral.generadorIdCuenta++;

		String idCand = "CAN-" + BolsaLaboral.generadorIdCand;
		Candidato candidato;
		if (perfil.equalsIgnoreCase("Tecnico")) {
			String area = txtArea.getText().trim();
			int experiencia = ((Number) spnExperiencia.getValue()).intValue();
			candidato = new Tecnico(idCand, cedula, nombre, genero, provincia, aspiracion, licencia, mudanza, cuenta,
					area, experiencia);
		} else if (perfil.equalsIgnoreCase("Profesional")) {
			String titulo = txtTitulo.getText().trim();
			candidato = new Profesional(idCand, cedula, nombre, genero, provincia, aspiracion, licencia, mudanza, cuenta,
					titulo);
		} else {
			ArrayList<String> destrezas = new ArrayList<String>();
			String texto = txtDestrezas.getText().trim();
			if (!texto.isEmpty()) {
				String[] partes = texto.split(",");
				for (String p : partes) {
					destrezas.add(p.trim());
				}
			}
			candidato = new Obrero(idCand, cedula, nombre, genero, provincia, aspiracion, licencia, mudanza, cuenta,
					destrezas);
		}

		bolsa.registrarCandidato(candidato);
		BolsaLaboral.generadorIdCand++;
		bolsa.saveDatos();

		JOptionPane.showMessageDialog(this, "Candidato registrado. Ya puedes iniciar sesion.", "Listo",
				JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}

	private boolean usuarioExiste(String usuario) {
		return false;
	}
}