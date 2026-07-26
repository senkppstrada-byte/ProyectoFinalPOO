package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

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
import logica.Postulacion;
import logica.Vacante;

public class PostaVac extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private static DefaultTableModel model;
    private static Object[] row;
    private Vacante selected = null;
    private static JTable tblVac;
    private JButton okButton;
    private JButton cancelButton;
    private Candidato cand = BolsaLaboral.getInstancia().buscarCandidatoPorCuenta(BolsaLaboral.getInstancia().getCuentalog());

    public PostaVac() {
        setTitle("Postularse a vacante");
        setBounds(100, 100, 600, 350); 
        setLocationRelativeTo(null);
        
        fondomenu = new FondoMenu("/img/mant.png");
        fondomenu.setLayout(new BorderLayout());
        setContentPane(fondomenu); 

        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        fondomenu.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        
        String[] headers = {"Código", "Empresa", "Puesto", "Descripción", "Rango Salarial", "Provincia", "Perfil Buscado"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        
        tblVac = new JTable();
        tblVac.setModel(model);
        tblVac.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tblVac.getSelectedRow();
                if (index >= 0) {
                    okButton.setEnabled(true);
                    selected = BolsaLaboral.getInstancia().buscarVacPorId(tblVac.getValueAt(index, 0).toString());
                }
            }
        });
        
      
        scrollPane.setViewportView(tblVac);

        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        {
           
            okButton = new JButton("Postularse");
            okButton.setEnabled(false);            
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selected != null && cand != null) {
                        int opcion = JOptionPane.showConfirmDialog(
                            PostaVac.this, 
                            "Estás seguro que deseas postularte a la vacante?",  
                            "Confirmar", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE
                        );
                        
                        if (opcion == JOptionPane.YES_OPTION) {
                            float por = BolsaLaboral.getInstancia().calcMatch(selected, cand);
                            Postulacion pos = new Postulacion("P-" + BolsaLaboral.generadorIdPos, cand, selected, LocalDate.now(), por, "activa");
                            BolsaLaboral.getInstancia().publicarPostulacion(pos);
                            BolsaLaboral.generadorIdPos++;
                            JOptionPane.showMessageDialog(PostaVac.this, "Postulación realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            dispose(); 
                        }
                    } else if (cand == null) {
                        JOptionPane.showMessageDialog(PostaVac.this, "Error: No se encontró la información del candidato logueado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
        
        loadVacantes();
    }

    public static void loadVacantes() {
        model.setRowCount(0);
        row = new Object[model.getColumnCount()];
        
        for (Vacante v : BolsaLaboral.getInstancia().getVacantes()) {
            if (v.getEstado().equalsIgnoreCase("activa")) {
                row[0] = v.getId();
                row[1] = v.getCentro().getNombreComercial();
                row[2] = v.getPuesto();
                row[3] = v.getDescripcion();
                row[4] = v.getSalarioMin() + " - " + v.getSalarioMax();
                row[5] = v.getProvincia();
                row[6] = v.getPerfilRequerido();
                model.addRow(row);
            }
        }
    }
}