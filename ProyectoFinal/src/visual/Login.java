package visual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.BolsaLaboral;
import logica.Candidato;
import logica.CentroEmpleador;
import logica.CuentaUsuario;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;

    public Login() {
        setTitle("Iniciar sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(Tema.FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(28, 40, 24, 40));
        setContentPane(panel);

        JLabel titulo = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        titulo.setFont(Tema.TITULO);
        titulo.setForeground(Tema.TEXTO);
        panel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(0, 1, 0, 6));
        form.setBackground(Tema.FONDO);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(Tema.NORMAL);
        lblUsuario.setForeground(Tema.TEXTO_SUAVE);
        txtUsuario = new JTextField();

        JLabel lblClave = new JLabel("Clave");
        lblClave.setFont(Tema.NORMAL);
        lblClave.setForeground(Tema.TEXTO_SUAVE);
        txtClave = new JPasswordField();

        form.add(lblUsuario);
        form.add(txtUsuario);
        form.add(lblClave);
        form.add(txtClave);
        panel.add(form, BorderLayout.CENTER);

        JPanel sur = new JPanel(new GridLayout(0, 1, 0, 10));
        sur.setBackground(Tema.FONDO);

        JButton btnLogin = Tema.botonPrimario("Ingresar");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingresar();
            }
        });

        JButton btnVolver = Tema.botonSecundario("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DialogLogin().setVisible(true);
                dispose();
            }
        });

        sur.add(btnLogin);
        sur.add(btnVolver);
        panel.add(sur, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnLogin);
    }

    public void ingresar() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar usuario y clave.", "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        CuentaUsuario cuenta = buscarCuenta(usuario, clave);
        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BolsaLaboral.getInstancia().setCuentalog(cuenta);

        if (cuenta.getRol().equalsIgnoreCase("candidato")) {
            new MenuCand().setVisible(true);
            dispose();
        } else if (cuenta.getRol().equalsIgnoreCase("centro") || cuenta.getRol().equalsIgnoreCase("empleador")) {
            new MenuCentro().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "El rol de la cuenta no es válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
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
