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
import logica.ClienteBackup;

public class MenuAdmin extends JFrame {

    public MenuAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 520);
        setLocationRelativeTo(null);
        setTitle("Panel del Centro");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnUtilidad = new JMenu("Utilidad");
        menuBar.add(mnUtilidad);

        JMenuItem mntmBackup = new JMenuItem("Crear respaldo");
        mntmBackup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	boolean exito = ClienteBackup.enviarBackup();

                if (exito) {
                    JOptionPane.showMessageDialog(null, 
                        "Respaldo realizado", 
                        "Backup Exitoso", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "No se pudo conectar con el servidor de respaldo", 
                        "Error de Conexión", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mnUtilidad.add(mntmBackup);

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
                int op = JOptionPane.showConfirmDialog(MenuAdmin.this, "¿Deseas salir del sistema?", "Confirmar",
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
    }
}
