package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.Candidato;
import logica.CentroEmpleador;
import logica.ClienteBackup;
import logica.Vacante;

public class MenuAdmin extends JFrame {

    public MenuAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);
        setTitle("Panel del Administrador");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnUtilidad = new JMenu("Utilidad");
        menuBar.add(mnUtilidad);

        JMenuItem mntmBackup = new JMenuItem("Crear respaldo");
        mntmBackup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean exito = ClienteBackup.enviarBackup();
                if (exito) {
                    JOptionPane.showMessageDialog(MenuAdmin.this, "Respaldo realizado", "Backup exitoso",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MenuAdmin.this, "No se pudo conectar con el servidor de respaldo",
                            "Error de conexión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mnUtilidad.add(mntmBackup);
        
        JMenuItem mntmCargar = new JMenuItem("Cargar respaldo");
        mntmCargar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// 1. Abrir un selector de archivos apuntando a la carpeta de backups
                JFileChooser chooser = new JFileChooser("backups");
                chooser.setDialogTitle("Selecciona el archivo de respaldo");
                
                int seleccion = chooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivoBackup = chooser.getSelectedFile();
                    
                    try (FileInputStream fis = new FileInputStream(archivoBackup);
                         ObjectInputStream ois = new ObjectInputStream(fis)) {
                        
                       
                        BolsaLaboral bolsaBackup = (BolsaLaboral) ois.readObject();
                        
                        
                        BolsaLaboral.setInstancia(bolsaBackup);
                        
                        
                        BolsaLaboral.getInstancia().saveDatos(); 

                        JOptionPane.showMessageDialog(null, 
                            "Backup cargado.", 
                            "Éxito", 
                            JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, 
                            "Error al cargar el archivo de backup: " + ex.getMessage(), 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
        	}
        });
        mnUtilidad.add(mntmCargar);

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

        JPanel contentPane = new JPanel(new BorderLayout(0, 10));
        contentPane.setBackground(Tema.FONDO);
        contentPane.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        setContentPane(contentPane);

        JLabel titulo = new JLabel("Panel del Administrador");
        titulo.setFont(Tema.TITULO);
        titulo.setForeground(Tema.TEXTO);
        contentPane.add(titulo, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Candidatos", construirTablaCandidatos());
        tabs.addTab("Centros", construirTablaCentros());
        tabs.addTab("Gráficas", construirGraficas());
        contentPane.add(tabs, BorderLayout.CENTER);
    }

    private JScrollPane construirTablaCandidatos() {
        String[] cols = { "ID", "Nombre", "Cédula", "Perfil", "Provincia", "Aspiración", "Disponible" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        for (Candidato c : BolsaLaboral.getInstancia().getCandidatos()) {
            String disp = Util.estaDisponible(c) ? "Sí" : "No";
            model.addRow(new Object[] { c.getId(), c.getNombreCompleto(), c.getCedula(), Util.perfil(c),
                    c.getProvincia(), c.getAspiracionSalarial(), disp });
        }
        JTable tabla = new JTable(model);
        tabla.setRowHeight(22);
        return new JScrollPane(tabla);
    }

    private JScrollPane construirTablaCentros() {
        String[] cols = { "ID", "Nombre comercial", "Tipo", "Representante", "Cédula rep", "Vacantes" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        for (CentroEmpleador ce : BolsaLaboral.getInstancia().getCentros()) {
            String repNombre = ce.getRep() != null ? ce.getRep().getNombreCompleto() : "";
            String repCedula = ce.getRep() != null ? ce.getRep().getCedula() : "";
            model.addRow(new Object[] { ce.getId(), ce.getNombreComercial(), ce.getTipoCentro(), repNombre, repCedula,
                    vacantesDeCentro(ce) });
        }
        JTable tabla = new JTable(model);
        tabla.setRowHeight(22);
        return new JScrollPane(tabla);
    }

    private int vacantesDeCentro(CentroEmpleador ce) {
        int n = 0;
        for (Vacante v : BolsaLaboral.getInstancia().getVacantes()) {
            if (v.getCentro() != null && v.getCentro().getId().equals(ce.getId())) {
                n++;
            }
        }
        return n;
    }

    private JPanel construirGraficas() {
        JPanel panel = new JPanel(new BorderLayout(0, 14));
        panel.setBackground(Tema.FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        int totalCand = bolsa.getCandidatos().size();
        int totalCent = bolsa.getCentros().size();
        int totalVac = bolsa.getVacantes().size();
        int disp = 0;
        for (Candidato c : bolsa.getCandidatos()) {
            if (Util.estaDisponible(c)) {
                disp++;
            }
        }

        JPanel fila = new JPanel(new GridLayout(1, 4, 12, 0));
        fila.setBackground(Tema.FONDO);
        fila.add(tarjeta(String.valueOf(totalCand), "Candidatos"));
        fila.add(tarjeta(String.valueOf(totalCent), "Centros"));
        fila.add(tarjeta(String.valueOf(totalVac), "Vacantes"));
        fila.add(tarjeta(String.valueOf(disp), "Disponibles"));
        panel.add(fila, BorderLayout.NORTH);

        int[] porPerfil = candidatosPorPerfil();
        GraficoBarras grafico = new GraficoBarras(new String[] { "Tecnico", "Profesional", "Obrero" }, porPerfil);
        grafico.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Tema.BORDE),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JPanel caja = new JPanel(new BorderLayout(0, 6));
        caja.setBackground(Tema.FONDO);
        JLabel lblG = new JLabel("Candidatos por perfil");
        lblG.setFont(Tema.SUBTITULO);
        lblG.setForeground(Tema.TEXTO);
        caja.add(lblG, BorderLayout.NORTH);
        caja.add(grafico, BorderLayout.CENTER);
        panel.add(caja, BorderLayout.CENTER);

        return panel;
    }

    private JPanel tarjeta(String numero, String texto) {
        JPanel t = new JPanel(new BorderLayout());
        t.setBackground(Tema.PANEL);
        t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Tema.BORDE),
                BorderFactory.createEmptyBorder(14, 10, 14, 10)));
        JLabel n = new JLabel(numero, SwingConstants.CENTER);
        n.setFont(Tema.KPI);
        n.setForeground(Tema.PRIMARIO);
        JLabel c = new JLabel(texto, SwingConstants.CENTER);
        c.setFont(Tema.NORMAL);
        c.setForeground(Tema.TEXTO_SUAVE);
        t.add(n, BorderLayout.CENTER);
        t.add(c, BorderLayout.SOUTH);
        return t;
    }

    private int[] candidatosPorPerfil() {
        int tec = 0;
        int pro = 0;
        int obr = 0;
        for (Candidato c : BolsaLaboral.getInstancia().getCandidatos()) {
            String p = Util.perfil(c);
            if (p.equals("Tecnico")) {
                tec++;
            } else if (p.equals("Profesional")) {
                pro++;
            } else if (p.equals("Obrero")) {
                obr++;
            }
        }
        return new int[] { tec, pro, obr };
    }

    private class GraficoBarras extends JPanel {

        private String[] etiquetas;
        private int[] valores;

        private GraficoBarras(String[] etiquetas, int[] valores) {
            this.etiquetas = etiquetas;
            this.valores = valores;
            setBackground(Tema.PANEL);
            setPreferredSize(new Dimension(400, 220));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int margen = 30;
            int base = h - margen;
            int maximo = 1;
            for (int v : valores) {
                if (v > maximo) {
                    maximo = v;
                }
            }

            g2.setColor(Tema.BORDE);
            g2.drawLine(margen, base, w - margen, base);

            int n = valores.length;
            int ancho = (w - 2 * margen) / (n * 2);
            FontMetrics fm = g2.getFontMetrics(Tema.NORMAL);
            g2.setFont(Tema.NORMAL);

            for (int i = 0; i < n; i++) {
                int x = margen + ancho + (i * 2 * ancho) - ancho / 2;
                int altura = (int) ((base - margen) * (valores[i] / (float) maximo));
                int y = base - altura;

                g2.setColor(Tema.PRIMARIO);
                g2.fillRect(x, y, ancho, altura);

                g2.setColor(Tema.TEXTO);
                String val = String.valueOf(valores[i]);
                g2.drawString(val, x + ancho / 2 - fm.stringWidth(val) / 2, y - 4);

                g2.setColor(Tema.TEXTO_SUAVE);
                String et = etiquetas[i];
                g2.drawString(et, x + ancho / 2 - fm.stringWidth(et) / 2, base + 18);
            }
        }
    }
}