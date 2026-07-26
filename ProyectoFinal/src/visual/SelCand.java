package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
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
import logica.Candidato;
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

    public SelCand() {
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
        
        String[] headerst = {"Código", "Nombre", "Provincia", "Perfil"};
        modelt = new DefaultTableModel();
        modelt.setColumnIdentifiers(headerst);
        
        tbltop = new JTable();
        tbltop.setModel(modelt);
        tbltop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tbltop.getSelectedRow();
                if (index >= 0) {
                    btnbajar.setEnabled(true);
                    selectedt = BolsaLaboral.getInstancia().buscarPosPorId(tbltop.getValueAt(index, 0).toString());
                }
            }
        });
        
        spTop.setViewportView(tbltop);
        
        panelb = new JPanel();
        contentPanel.add(panelb, BorderLayout.CENTER);
        panelb.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        btnbajar = new JButton("Bajar");
        btnbajar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        panelb.add(btnbajar);
        
        btnsubir = new JButton("Subir");
        panelb.add(btnsubir);
        
        panels = new JPanel();
        contentPanel.add(panels, BorderLayout.SOUTH);
        panels.setLayout(new BorderLayout(0, 0));
        
        spSels = new JScrollPane();
        panels.add(spSels);
        
        tblSels = new JTable();
        panels.add(tblSels, BorderLayout.NORTH);

        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        {
            okButton = new JButton("Seleccionar");
            okButton.setEnabled(false);            
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
        
    }
    public void selec() {
    	
    }
    public void loadtop() {
        modelt.setRowCount(0);
        rowt = new Object[modelt.getColumnCount()];
        ArrayList<Postulacion> posts = null;
       for (Postulacion p : posts) {
    	   		rowt[0] = p.getId();
                rowt[1] = p.getCandidato().getNombreCompleto();
                rowt[2] = p.getCandidato().getProvincia();
                if (p.getCandidato() instanceof Tecnico) {
                	rowt[3] = "Tecnico";
                }
                else if (p.getCandidato() instanceof Profesional) {
                	rowt[3] = "Profesional";
                }
                else if (p.getCandidato() instanceof Obrero) {
                	rowt[3] = "Obrero";
                }
             
                modelt.addRow(rowt);
       }
                
            }
        
    }