package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class ListPos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private DefaultTableModel model;
    private Object[] row;
    private Postulacion selected = null;
    private JTable tblPos;
    private JButton okButton;
    private JButton cancelButton;
    private Candidato cand = BolsaLaboral.getInstancia().buscarCandidatoPorCuenta(BolsaLaboral.getInstancia().getCuentalog());

    public ListPos() {
        setTitle("Lista de postulaciones");
        setBounds(100, 100, 650, 350); 
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
        
        String[] headers = {"Código", "Empresa", "Puesto", "Fecha", "Estado"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);
        
        tblPos = new JTable();
        tblPos.setModel(model);
        tblPos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = tblPos.getSelectedRow();
                if (index >= 0) {
                    okButton.setEnabled(true);
                    selected = BolsaLaboral.getInstancia().buscarPosPorId(tblPos.getValueAt(index, 0).toString());
                }
            }
        });
        
        scrollPane.setViewportView(tblPos);

        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        {
            okButton = new JButton("Cambiar Estado");
            okButton.setEnabled(false);            
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selected != null && cand != null) {
                        
                        String nuevoEstado = selected.getEstado().equalsIgnoreCase("activa") ? "inactiva" : "activa";
                        
                        int opcion = JOptionPane.showConfirmDialog(
                            ListPos.this, 
                            "Estas seguro que deseas cambiar el estado de la postulacion?",  
                            "Confirmar", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE
                        );
                        
                        if (opcion == JOptionPane.YES_OPTION) {
                            selected.setEstado(nuevoEstado);
                            
                            JOptionPane.showMessageDialog(ListPos.this, "Cambio realizado con exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            
                            loadPostulaciones();
                            okButton.setEnabled(false);
                            selected = null;
                        }
                    } else if (cand == null) {
                        JOptionPane.showMessageDialog(ListPos.this, "Error: No se encontró la información del candidato logueado.", "Error", JOptionPane.ERROR_MESSAGE);
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
        
        loadPostulaciones();
    }

    public void loadPostulaciones() {
        model.setRowCount(0);
        row = new Object[model.getColumnCount()];
        
        if (cand != null) {
            for (Postulacion p : BolsaLaboral.getInstancia().getPostulaciones()) {
                if (p.getCandidato() != null && p.getCandidato().getId().equalsIgnoreCase(cand.getId())) {
                    row[0] = p.getId();
                    row[1] = p.getVacante().getCentro().getNombreComercial();
                    row[2] = p.getVacante().getPuesto();
                    row[3] = p.getFecha();
                    row[4] = p.getEstado();
                    model.addRow(row);
                }
            }
        }
    }
}