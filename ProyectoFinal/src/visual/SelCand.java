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
    private Postulacion selectedb = null;
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
    private ArrayList<Postulacion> listTop = new ArrayList<Postulacion>();
    private ArrayList<Postulacion> listBot = new ArrayList<Postulacion>();

    public SelCand(Vacante vac) {
        this.vacante = vac;

        setTitle("Seleccionar candidato");
        setBounds(100, 100, 600, 350);
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

        String[] headerst = {"Codigo", "Nombre", "Provincia", "Perfil"};
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

        String[] headerss = {"Codigo", "Nombre", "Provincia", "Perfil"};
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
}