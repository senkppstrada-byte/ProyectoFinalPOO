package visual;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logica.BolsaLaboral;
import logica.Candidato;

public class MenuCand extends JFrame {

    public MenuCand() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 480);
        setLocationRelativeTo(null);
        setTitle("Panel del Candidato");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnVacantes = new JMenu("Vacantes");
        menuBar.add(mnVacantes);

        JMenuItem mntmVer = new JMenuItem("Ver vacantes disponibles");
        mntmVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerVacantes v = new VerVacantes(false);
                v.setModal(true);
                v.setVisible(true);
            }
        });
        mnVacantes.add(mntmVer);

        JMenuItem mntmContrat = new JMenuItem("Mis contrataciones");
        mntmContrat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListPos l = new ListPos();
                l.setModal(true);
                l.setVisible(true);
            }
        });
        mnVacantes.add(mntmContrat);

        JMenu mnOpciones = new JMenu("Opciones");
        menuBar.add(mnOpciones);

        JMenuItem mntmCerrar = new JMenuItem("Cerrar sesión");
        mntmCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BolsaLaboral.getInstancia().setCuentalog(null);
                new DialogLogin().setVisible(true);
                dispose();
            }
        });
        mnOpciones.add(mntmCerrar);

        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(MenuCand.this, "¿Deseas salir del sistema?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        mnOpciones.add(mntmSalir);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Tema.FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setContentPane(panel);

        Candidato cand = BolsaLaboral.getInstancia()
                .buscarCandidatoPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
        String nombre = cand != null ? cand.getNombreCompleto() : "Candidato";

        JPanel caja = new JPanel(new BorderLayout(0, 8));
        caja.setBackground(Tema.PANEL);
        caja.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Tema.BORDE),
                BorderFactory.createEmptyBorder(24, 28, 24, 28)));

        JLabel bienvenida = new JLabel("Hola, " + nombre);
        bienvenida.setFont(Tema.TITULO);
        bienvenida.setForeground(Tema.TEXTO);

        JLabel ayuda = new JLabel(
                "<html>Puedes ver las vacantes disponibles. Las empresas te contactan si eres el candidato ideal."
                        + "<br>Revisa dónde te han contratado en 'Mis contrataciones'.</html>");
        ayuda.setFont(Tema.NORMAL);
        ayuda.setForeground(Tema.TEXTO_SUAVE);

        caja.add(bienvenida, BorderLayout.NORTH);
        caja.add(ayuda, BorderLayout.CENTER);
        panel.add(caja, BorderLayout.NORTH);

        JLabel pie = new JLabel("Es la empresa quien elige al trabajador ideal.", SwingConstants.CENTER);
        pie.setFont(Tema.NORMAL);
        pie.setForeground(Tema.TEXTO_SUAVE);
        panel.add(pie, BorderLayout.SOUTH);
    }
}
