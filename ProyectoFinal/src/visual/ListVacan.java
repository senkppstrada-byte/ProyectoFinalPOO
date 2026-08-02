package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.CentroEmpleador;
import logica.Vacante;

public class ListVacan extends JDialog {

    private DefaultTableModel model;
    private JTable tblVac;
    private Vacante selected = null;
    private JButton okButton;
    private CentroEmpleador emp = BolsaLaboral.getInstancia()
            .buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());

    public ListVacan() {
        setTitle("Lista de vacantes");
        setSize(720, 380);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(0, 10));
        contentPane.setBackground(Tema.FONDO);
        contentPane.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        setContentPane(contentPane);

        String[] headers = { "Código", "Puesto", "Perfil", "Provincia", "Plazas", "Estado" };
        model = new DefaultTableModel(headers, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        tblVac = new JTable(model);
        tblVac.setRowHeight(22);
        tblVac.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = tblVac.getSelectedRow();
                if (index >= 0) {
                    okButton.setEnabled(true);
                    selected = BolsaLaboral.getInstancia().buscarVacPorId(tblVac.getValueAt(index, 0).toString());
                }
            }
        });
        contentPane.add(new JScrollPane(tblVac), BorderLayout.CENTER);

        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setBackground(Tema.FONDO);

        okButton = Tema.botonPrimario("Ver candidatos");
        okButton.setEnabled(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    SelCand dialog = new SelCand(selected);
                    dialog.setModal(true);
                    dialog.setVisible(true);
                    loadVacantes();
                    okButton.setEnabled(false);
                    selected = null;
                }
            }
        });
        buttonPane.add(okButton);

        JButton cerrar = Tema.botonSecundario("Cerrar");
        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(cerrar);

        contentPane.add(buttonPane, BorderLayout.SOUTH);

        loadVacantes();
    }

    public void loadVacantes() {
    }
}
