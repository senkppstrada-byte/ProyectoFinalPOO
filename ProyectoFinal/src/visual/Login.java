package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
import logica.Tecnico;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	
	public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bolsa de Trabajo - Ingreso");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(39, 39, 105, 14);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(39, 64, 191, 20);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        JLabel lblClave = new JLabel("Clave:");
        lblClave.setBounds(39, 98, 105, 14);
        panel.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setBounds(39, 128, 191, 20);
        panel.add(txtClave);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingresar();
            }
        });
        btnLogin.setBounds(39, 175, 105, 23);
        panel.add(btnLogin);

        getRootPane().setDefaultButton(btnLogin);
    }
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    sembrarDatos();
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void ingresar() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar usuario y clave.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        CuentaUsuario cuenta = buscarCuenta(usuario, clave);
        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BolsaLaboral.getInstancia().setCuentalog(cuenta);

        if (cuenta.getRol().equalsIgnoreCase("candidato")) {
            MenuCand menu = new MenuCand();
            menu.setVisible(true);
            dispose();
        } else if (cuenta.getRol().equalsIgnoreCase("centro") || cuenta.getRol().equalsIgnoreCase("empleador")) {
            MenuCentro menu = new MenuCentro();
            menu.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El rol de la cuenta no es valido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public CuentaUsuario buscarCuenta(String usuario, String clave) {
        BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        for (Candidato c : bolsa.getCandidatos()) {
            CuentaUsuario cu = c.getCuenta();
            if (cu != null && cu.getNombreUsuario().equals(usuario) && cu.getClave().equals(clave)) {
                return cu;
            }
        }
        for (CentroEmpleador ce : bolsa.getCentros()) {
            if (ce.getRep() != null) {
                CuentaUsuario cu = ce.getRep().getCuenta();
                if (cu != null && cu.getNombreUsuario().equals(usuario) && cu.getClave().equals(clave)) {
                    return cu;
                }
            }
        }
        return null;
    }
}
