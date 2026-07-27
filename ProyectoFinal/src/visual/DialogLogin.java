package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLogin extends JFrame {

	private final JPanel contentPane = new JPanel();
	private FondoMenu fondomenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogLogin frame = new DialogLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DialogLogin() {
		setTitle("Bienvenido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		fondomenu = new FondoMenu("/img/mant.png");
		fondomenu.setLayout(new BorderLayout());
		setContentPane(fondomenu);

		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondomenu.add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciarSesion.setBounds(55, 104, 137, 49);
		contentPane.add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarse.setBounds(241, 104, 137, 49);
		contentPane.add(btnRegistrarse);
		
		JLabel lblTitulo = new JLabel("Proyecto Bolsa de Empleo ");
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitulo.setBounds(79, 13, 301, 49);
		contentPane.add(lblTitulo);
	}
}