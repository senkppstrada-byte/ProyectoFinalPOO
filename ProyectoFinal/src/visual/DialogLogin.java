package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logica.BolsaLaboral;

public class DialogLogin extends JFrame {

    public static void main(String[] args) {
        BolsaLaboral.cargarDatos();
        BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        BolsaLaboral.generadorIdCand = bolsa.getCandidatos().size() + 1;
        BolsaLaboral.generadorIdCent = bolsa.getCentros().size() + 1;
        BolsaLaboral.generadorIdCuenta = bolsa.getCandidatos().size() + bolsa.getCentros().size() + 1;
        BolsaLaboral.generadorIdVac = bolsa.getVacantes().size() + 1;
        BolsaLaboral.generadorIdPos = bolsa.getPostulaciones().size() + 1;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DialogLogin().setVisible(true);
            }
        });
    }

    public DialogLogin() {
        setTitle("Bolsa de Empleo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 360);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(0, 24));
        panel.setBackground(Tema.FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 24, 40));
        setContentPane(panel);

        JPanel cab = new JPanel(new GridLayout(0, 1, 0, 6));
        cab.setBackground(Tema.FONDO);
        JLabel titulo = new JLabel("Bolsa de Empleo", SwingConstants.CENTER);
        titulo.setFont(Tema.TITULO);
        titulo.setForeground(Tema.TEXTO);
        JLabel sub = new JLabel("Sistema de selección de personal", SwingConstants.CENTER);
        sub.setFont(Tema.NORMAL);
        sub.setForeground(Tema.TEXTO_SUAVE);
        cab.add(titulo);
        cab.add(sub);
        panel.add(cab, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(0, 1, 0, 12));
        botones.setBackground(Tema.FONDO);

        JButton btnLogin = Tema.botonPrimario("Iniciar sesión");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

        JButton btnReg = Tema.botonSecundario("Registrarse");
        btnReg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });

        JButton btnInvitado = Tema.botonSecundario("Entrar como invitado");
        btnInvitado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerVacantes v = new VerVacantes(true);
                v.setModal(true);
                v.setVisible(true);
            }
        });

        botones.add(btnLogin);
        botones.add(btnReg);
        botones.add(btnInvitado);
        panel.add(botones, BorderLayout.CENTER);

        JLabel pie = new JLabel("Proyecto No 6 - Ubicación Laboral", SwingConstants.CENTER);
        pie.setFont(Tema.NORMAL);
        pie.setForeground(Tema.TEXTO_SUAVE);
        panel.add(pie, BorderLayout.SOUTH);
    }

    private void abrirRegistro() {
        Object[] opciones = { "Candidato", "Centro" };
        int tipo = JOptionPane.showOptionDialog(this, "¿Qué deseas registrar?", "Registro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (tipo == 0) {
            RegistroCandidato r = new RegistroCandidato();
            r.setModal(true);
            r.setVisible(true);
        } else if (tipo == 1) {
            RegistroCentro r = new RegistroCentro();
            r.setModal(true);
            r.setVisible(true);
        }
    }
}
