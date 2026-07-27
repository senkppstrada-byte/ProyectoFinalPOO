package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.CentroEmpleador;
import logica.Obrero;
import logica.Postulacion;
import logica.Profesional;
import logica.Tecnico;
import logica.Vacante;

public class SelCand extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private DefaultTableModel modelt; 
    private Object[] rowt;    
    private DefaultTableModel models; 
    private Object[] rows;   
    private Postulacion selectedt = null;
    private Postulacion selecteds = null;
    private JTable tbltop;            
    private JButton okButton;
    private JButton cancelButton;
    private CentroEmpleador emp = BolsaLaboral.getInstancia().buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
    private JPanel panelb;
    private JPanel panels;
    private JScrollPane spSels;
    private JButton btnbajar;
    private JButton btnsubir;
    private JTable tblSels;
    private ArrayList<Postulacion> listaTop = new ArrayList<>();
    private ArrayList<Postulacion> listaSels = new ArrayList<>();
    private Vacante vacanteActual;

    public SelCand(Vacante vac) {
        this.vacanteActual = vac;
        
        setTitle("Seleccionar candidato");
        setBounds(100, 100, 650, 450); 
        setLocationRelativeTo(null);
        
        fondomenu = new FondoMenu("/img/mant.png");
        fondomenu.setLayout(new BorderLayout());
        setContentPane(fondomenu); 

        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        fondomenu.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 5));
        
       
        JPanel panelt = new JPanel();
        panelt.setOpaque(false);
        contentPanel.add(panelt, BorderLayout.NORTH);
        panelt.setLayout(new BorderLayout(0, 0));
        
        JScrollPane spTop = new JScrollPane();
        panelt.add(spTop, BorderLayout.CENTER);
        
        String[] headerst = {"Código", "Nombre", "Provincia", "Perfil", "Match"};
        modelt = new DefaultTableModel();
        modelt.setColumnIdentifiers(headerst);
        
        tbltop = new JTable();
        tbltop.setModel(modelt);
        tbltop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tbltop.getSelectedRow();
                if (index >= 0 && index < listaTop.size()) {
                    btnbajar.setEnabled(true);
                    selectedt = listaTop.get(index);
                }
            }
        });
        
        spTop.setViewportView(tbltop);
        
        
        panelb = new JPanel();
        panelb.setOpaque(false);
        contentPanel.add(panelb, BorderLayout.CENTER);
        panelb.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        btnbajar = new JButton("Bajar");
        btnbajar.setEnabled(false);
        btnbajar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedt != null) {
                    listaTop.remove(selectedt);
                    listaSels.add(selectedt);
                    selectedt = null;
                    btnbajar.setEnabled(false);
                    loadtop();
                    loadSels();
                    okButton.setEnabled(!listaSels.isEmpty());
                }
            }
        });
        panelb.add(btnbajar);
        
        btnsubir = new JButton("Subir");
        btnsubir.setEnabled(false);
        btnsubir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selecteds != null) {
                    listaSels.remove(selecteds);
                    listaTop.add(selecteds);
                    selecteds = null;
                    btnsubir.setEnabled(false);
                    loadtop();
                    loadSels();
                    okButton.setEnabled(!listaSels.isEmpty());
                }
            }
        });
        panelb.add(btnsubir);
        
       
        panels = new JPanel();
        panels.setOpaque(false);
        contentPanel.add(panels, BorderLayout.SOUTH);
        panels.setLayout(new BorderLayout(0, 0));
        
        models = new DefaultTableModel();
        models.setColumnIdentifiers(headerst);
        
        tblSels = new JTable();
        tblSels.setModel(models);
        tblSels.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tblSels.getSelectedRow();
                if (index >= 0 && index < listaSels.size()) {
                    btnsubir.setEnabled(true);
                    selecteds = listaSels.get(index);
                }
            }
        });
        
        spSels = new JScrollPane();
        spSels.setViewportView(tblSels);
        panels.add(spSels, BorderLayout.CENTER);

        
        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        okButton = new JButton("Seleccionar");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selec();
            }
        });
        okButton.setEnabled(false);            
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

       
        if (vacanteActual != null) {
            listaTop = BolsaLaboral.getInstancia().conectarCandidatos(vacanteActual);
            loadtop();
        }
    }

    public void selec() {
        if (vacanteActual == null || listaSels.isEmpty()) return;

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Deseas confirmar la contratación de los candidatos seleccionados?",
            "Confirmar Selección",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            for (Postulacion p : listaSels) {
                p.setEstado("aceptada");
            }

            int contratados = listaSels.size();
            vacanteActual.setPlazasOcupadas(vacanteActual.getPlazasOcupadas() + contratados);

            if (vacanteActual.getPlazasOcupadas() >= vacanteActual.getPlazasTotales()) {
                vacanteActual.setEstado("cerrada");
                JOptionPane.showMessageDialog(this, "La vacante ha completado sus plazas y pasó a estar CERRADA.");
            } else {
                JOptionPane.showMessageDialog(this, "Selección aplicada con éxito.");
            }

            dispose();
        }
    }

    public void loadtop() {
        modelt.setRowCount(0);
        rowt = new Object[modelt.getColumnCount()];
        
        if (listaTop != null) {
            for (Postulacion p : listaTop) {
                rowt[0] = p.getId();
                rowt[1] = (p.getCandidato() != null) ? p.getCandidato().getNombreCompleto() : "N/A";
                rowt[2] = (p.getCandidato() != null) ? p.getCandidato().getProvincia() : "N/A";
                
                if (p.getCandidato() instanceof Tecnico) {
                    rowt[3] = "Tecnico";
                } else if (p.getCandidato() instanceof Profesional) {
                    rowt[3] = "Profesional";
                } else if (p.getCandidato() instanceof Obrero) {
                    rowt[3] = "Obrero";
                } else {
                    rowt[3] = "N/A";
                }
                
                rowt[4] = String.format("%.1f%%", p.getPorCoincidencia());
                modelt.addRow(rowt);
            }
        }
    }

    public void loadSels() {
        models.setRowCount(0);
        rows = new Object[models.getColumnCount()];
        
        if (listaSels != null) {
            for (Postulacion p : listaSels) {
                rows[0] = p.getId();
                rows[1] = (p.getCandidato() != null) ? p.getCandidato().getNombreCompleto() : "N/A";
                rows[2] = (p.getCandidato() != null) ? p.getCandidato().getProvincia() : "N/A";
                
                if (p.getCandidato() instanceof Tecnico) {
                    rows[3] = "Tecnico";
                } else if (p.getCandidato() instanceof Profesional) {
                    rows[3] = "Profesional";
                } else if (p.getCandidato() instanceof Obrero) {
                    rows[3] = "Obrero";
                } else {
                    rows[3] = "N/A";
                }
                
                rows[4] = String.format("%.1f%%", p.getPorCoincidencia());
                models.addRow(rows);
            }
        }
    }
}