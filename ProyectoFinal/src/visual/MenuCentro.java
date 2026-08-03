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
import logica.CentroEmpleador;

public class MenuCentro extends JFrame {

    public MenuCentro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 520);
        setLocationRelativeTo(null);
        setTitle("Panel del Centro");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnVacantes = new JMenu("Vacantes");
        menuBar.add(mnVacantes);

        JMenuItem mntmPublicar = new JMenuItem("Publicar vacante");
        mntmPublicar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                publicarVac pub = new publicarVac();
                pub.setModal(true);
                pub.setVisible(true);
            }
        });
        mnVacantes.add(mntmPublicar);

        JMenuItem mntmLista = new JMenuItem("Lista de vacantes");
        mntmLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListVacan lista = new ListVacan();
                lista.setModal(true);
                lista.setVisible(true);
            }
        });
        mnVacantes.add(mntmLista);



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
                int op = JOptionPane.showConfirmDialog(MenuCentro.this, "¿Deseas salir del sistema?", "Confirmar",
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

        CentroEmpleador emp = BolsaLaboral.getInstancia()
                .buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
        String nombre = emp != null ? emp.getNombreComercial() : "Centro";

        JPanel caja = new JPanel();
        caja.setLayout(new BorderLayout(0, 8));
        caja.setBackground(Tema.PANEL);
        caja.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Tema.BORDE),
                BorderFactory.createEmptyBorder(24, 28, 24, 28)));

        JLabel bienvenida = new JLabel("Bienvenido, " + nombre);
        bienvenida.setFont(Tema.TITULO);
        bienvenida.setForeground(Tema.TEXTO);

        JLabel ayuda = new JLabel(
                "<html>Usa el menu Vacantes para publicar ofertas y ver los candidatos ideales por match."
                        + "<br>En Estadísticas revisas los indicadores de tu centro.</html>");
        ayuda.setFont(Tema.NORMAL);
        ayuda.setForeground(Tema.TEXTO_SUAVE);

        caja.add(bienvenida, BorderLayout.NORTH);
        caja.add(ayuda, BorderLayout.CENTER);

        panel.add(caja, BorderLayout.NORTH);

        JLabel pie = new JLabel("La empresa elige al candidato ideal de los disponibles.", SwingConstants.CENTER);
        pie.setFont(Tema.NORMAL);
        pie.setForeground(Tema.TEXTO_SUAVE);
        panel.add(pie, BorderLayout.SOUTH);
    }
}
