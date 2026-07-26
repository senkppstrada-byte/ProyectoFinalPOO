package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.Vacante;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PostaVac extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private static JTable tblClie;
	private static DefaultTableModel model;
	private static Object[] row;
	private Vacante selected = null;
	private JTable tblVac;
	private JButton okButton;
	private JButton cancelButton;

    public PostaVac() {
    	setTitle("Postularse a vacante");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        fondomenu = new FondoMenu("/img/mant.png");
        fondomenu.setLayout(new BorderLayout());
        setContentPane(fondomenu); 

        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        fondomenu.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);
        
        String[] headers = {"Código", "Empresa", "puesto", "Descripcion", "Rango salarial", "Provincia", "Perfil buscado"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		
        tblVac = new JTable();
        tblVac.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = tblVac.getSelectedRow();
				if (index >= 0) {
					okButton.setEnabled(true);
					okButton.setEnabled(true);
					
					selected = BolsaLaboral.getInstancia().buscarVacPorId(tblVac.getValueAt(index, 0).toString());
				}
        	}
        });
        tblVac.setModel(model);
        
        panel.add(tblVac);

        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        {
            JButton okButton = new JButton("Postularse");
            okButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            	}
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
        {
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.setActionCommand("Cancel");
            cancelButton.addActionListener(e -> dispose());
            buttonPane.add(cancelButton);
        }
    }
    public static void loadVacantes() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (Vacante v : BolsaLaboral.getInstancia().getVacantes()) {
			if (v.getEstado().equals("activa")) {
				row[0] = v.getId();
			row[1] = v.getCentro().getNombreComercial();
			row[2] = v.getPuesto();
			row[3] = v.getDescripcion();
			row[4] = v.getSalarioMin() + "-" + v.getSalarioMax();
			row[5] = v.getProvincia();
			row[6] = v.getPerfilRequerido();
			model.addRow(row);
			}
			
		}
	}
}