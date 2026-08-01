package visual;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

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
import logica.Candidato;

public class SelCand extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private DefaultTableModel modelt;
    private Object[] rowt;
    private DefaultTableModel models;
    private Object[] rows;
    private Candidato selectedt = null;
    private Candidato selectedb = null;
    private JTable tbltop;
    private JButton okButton;
    private JButton cancelButton;
    private Vacante vacante;
    private CentroEmpleador emp = BolsaLaboral.getInstancia().buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
    private JPanel panelb;
    private JPanel panels;
    private JScrollPane spSels;
    private JButton btnbajar;
    private JButton btnsubir;
    private JTable tblSels;
    private ArrayList<Candidato> listTop = new ArrayList<Candidato>();
    private ArrayList<Candidato> listBot = new ArrayList<Candidato>();

    public SelCand(Vacante vac) {
        this.vacante = vac;

        setTitle("Seleccionar candidato");
        setBounds(100, 100, 781, 462);
        setLocationRelativeTo(null);

        fondomenu = new FondoMenu("/img/mant.png");
        fondomenu.setLayout(new BorderLayout());
        setContentPane(fondomenu);

        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        fondomenu.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panelt = new JPanel();
        panelt.setOpaque(false);
        contentPanel.add(panelt, BorderLayout.NORTH);
        panelt.setLayout(new BorderLayout(0, 0));

        JScrollPane spTop = new JScrollPane();
        panelt.add(spTop, BorderLayout.CENTER);

        String[] headerst = {"Codigo", "Nombre", "Provincia", "Perfil", "Match"};
        modelt = new DefaultTableModel();
        modelt.setColumnIdentifiers(headerst);

        tbltop = new JTable();
        tbltop.setModel(modelt);
        tbltop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tbltop.getSelectedRow();
                if (index >= 0 && index < listTop.size()) {
                    btnbajar.setEnabled(true);
                    selectedt = listTop.get(index);
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
                bajar();
            }
        });
        panelb.add(btnbajar);

        btnsubir = new JButton("Subir");
        btnsubir.setEnabled(false);
        btnsubir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                subir();
            }
        });
        panelb.add(btnsubir);

        panels = new JPanel();
        panels.setOpaque(false);
        contentPanel.add(panels, BorderLayout.SOUTH);
        panels.setLayout(new BorderLayout(0, 0));

        spSels = new JScrollPane();
        panels.add(spSels, BorderLayout.CENTER);

        String[] headerss = {"Codigo", "Nombre", "Provincia", "Perfil", "Match"};
        models = new DefaultTableModel();
        models.setColumnIdentifiers(headerss);

        tblSels = new JTable();
        tblSels.setModel(models);
        tblSels.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tblSels.getSelectedRow();
                if (index >= 0 && index < listBot.size()) {
                    btnsubir.setEnabled(true);
                    selectedb = listBot.get(index);
                }
            }
        });
        spSels.setViewportView(tblSels);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        {
            okButton = new JButton("Seleccionar");
            okButton.setEnabled(false);
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    seleccionar();
                }
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
        {
            cancelButton = new JButton("Cancelar");
            cancelButton.setActionCommand("Cancel");
            cancelButton.addActionListener(e -> dispose());
            buttonPane.add(cancelButton);
        }

        loadtop();
    }
    
    public void bajar() {
        if (selectedt != null && listTop.contains(selectedt)) {
            listBot.add(selectedt);
            listTop.remove(selectedt);
            selectedt = null;
            btnbajar.setEnabled(false);
            refrescar();
        }
    }
    
    public void subir() {
        if (selectedb != null && listBot.contains(selectedb)) {
            listTop.add(selectedb);
            listBot.remove(selectedb);
            selectedb = null;
            btnsubir.setEnabled(false);
            refrescar();
        }
    }
    
    public void seleccionar() {
        if (listBot.isEmpty()) {
            return;
        }
        int opcion = JOptionPane.showConfirmDialog(
            SelCand.this,
            "Estas seguro que deseas seleccionar a los candidatos elegidos?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (opcion == JOptionPane.YES_OPTION) {
            for (Postulacion p : listBot) {
                p.setEstado("seleccionada");
                vacante.ocuparPlaza();
            }
            JOptionPane.showMessageDialog(SelCand.this, "Candidatos seleccionados con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
    
    public void loadtop() {
        listTop.clear();
        listBot.clear();
        final BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        for (Candidato c : bolsa.getCandidatos()) {
            if (estaDisponible(c)) {
                float match = bolsa.calcMatch(vacante, c);
                if (match >= vacante.getCoincidenciaMinima()) {
                    listTop.add(c);
                }
            }
        }
        Collections.sort(listTop, new Comparator<Candidato>() {
            public int compare(Candidato a, Candidato b) {
                return Float.compare(bolsa.calcMatch(vacante, b), bolsa.calcMatch(vacante, a));
            }
        });
        refrescar();
    }
    
    public void refrescar() {
        modelt.setRowCount(0);
        rowt = new Object[modelt.getColumnCount()];
        for (Postulacion p : listTop) {
            llenarFila(rowt, p);
            modelt.addRow(rowt);
        }

        models.setRowCount(0);
        rows = new Object[models.getColumnCount()];
        for (Postulacion p : listBot) {
            llenarFila(rows, p);
            models.addRow(rows);
        }

        okButton.setEnabled(!listBot.isEmpty());
    }

    public void llenarFila(Object[] fila, Postulacion p) {
        fila[0] = p.getId();
        fila[1] = p.getCandidato().getNombreCompleto();
        fila[2] = p.getCandidato().getProvincia();
        if (p.getCandidato() instanceof Tecnico) {
            fila[3] = "Tecnico";
        } else if (p.getCandidato() instanceof Profesional) {
            fila[3] = "Profesional";
        } else if (p.getCandidato() instanceof Obrero) {
            fila[3] = "Obrero";
        } else {
            fila[3] = "";
        }
        fila[4] = String.format("%.1f%%", p.getPorCoincidencia());
    }
}