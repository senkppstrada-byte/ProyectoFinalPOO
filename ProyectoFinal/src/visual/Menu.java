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

public class Menu extends JFrame {

    private FondoMenu contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Menu frame = new Menu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setTitle("Sistema de Bolsa de Trabajo");
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("New menu");
        menuBar.add(mnNewMenu);
        
        JMenu mnNewMenu_1 = new JMenu("New menu");
        menuBar.add(mnNewMenu_1);
        
        JMenu mnNewMenu_2 = new JMenu("New menu");
        menuBar.add(mnNewMenu_2);

        contentPane = new FondoMenu("/img/1.png");
        contentPane.setLayout(new BorderLayout()); // Usamos BorderLayout para estructurar componentes
        
        setContentPane(contentPane);
    }
}