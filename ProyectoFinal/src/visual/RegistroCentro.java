package visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.BolsaLaboral;
import logica.Candidato;
import logica.CentroEmpleador;
import logica.CuentaUsuario;
import logica.Representante;

public class RegistroCentro extends JDialog {

	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JTextField txtCorreo;
	private JTextField txtRepNombre;
	private JTextField txtRepCedula;
	private JTextField txtNombreComercial;
	private JTextField txtTipoCentro;
	private JTextField txtDireccion;

	public RegistroCentro() {
		setTitle("Registro de Centro");
		setBounds(100, 100, 460, 420);
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