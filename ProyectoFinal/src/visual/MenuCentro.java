package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuCentro extends JFrame {

    private FondoMenu contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuCentro frame = new MenuCentro();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuCentro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setTitle("Sistema de Bolsa de Trabajo");
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        setJMenuBar(menuBar);
        
        JMenu mnVacantes = new JMenu("Vacantes");
        mnVacantes.setBackground(Color.LIGHT_GRAY);
        menuBar.add(mnVacantes);
        
        JMenuItem mntmPubvacante = new JMenuItem("Publicar vacante");
        mnVacantes.add(mntmPubvacante);
        
        JMenuItem mntmListVac = new JMenuItem("Lista de vacantes");
        mnVacantes.add(mntmListVac);
        
        JMenu mnOpciones = new JMenu("Opciones");
        mnOpciones.setBackground(Color.LIGHT_GRAY);
        mnOpciones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        menuBar.add(mnOpciones);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int opcion = JOptionPane.showConfirmDialog(MenuCentro.this, "¿Estás seguro de que deseas salir del sistema?",  "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	        if (opcion == JOptionPane.YES_OPTION) {
    	            System.exit(0); 
    	        }
        	}
        });
        mnOpciones.add(mntmNewMenuItem);
        
        JMenuItem mntmCerrarsesion = new JMenuItem("Cerrar sesion");
        mntmCerrarsesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        mnOpciones.add(mntmCerrarsesion);

        contentPane = new FondoMenu("/img/1.png");
        contentPane.setLayout(new BorderLayout()); 
        
        setContentPane(contentPane);
    }
}