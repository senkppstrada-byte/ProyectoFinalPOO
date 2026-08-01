package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.Vacante;

public class VerVacantes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private FondoMenu fondomenu;
    private DefaultTableModel model;
    private Object[] row;
    private JTable tblVac;

    public VerVacantes() {
        setTitle("Vacantes disponibles");
        setBounds(100, 100, 640, 360);
        setLocationRelativeTo(null);

        fondomenu = new FondoMenu("/img/mant.png");
        fondomenu.setLayout(new BorderLayout());
        setContentPane(fondomenu);

        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        fondomenu.add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        String[] headers = {"Codigo", "Empresa", "Puesto", "Descripcion", "Rango Salarial", "Provincia", "Perfil Buscado"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(headers);

        tblVac = new JTable();
        tblVac.setModel(model);
        scrollPane.setViewportView(tblVac);

        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dispose());
        buttonPane.add(cerrar);

        loadVacantes();
    }

    public void loadVacantes() {
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