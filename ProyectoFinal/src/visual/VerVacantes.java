package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.Vacante;

public class VerVacantes extends JDialog {

    private DefaultTableModel model;
    private JTable tblVac;

    public VerVacantes(boolean invitado) {
        setTitle("Vacantes disponibles");
        setSize(720, 380);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(0, 10));
        contentPane.setBackground(Tema.FONDO);
        contentPane.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        setContentPane(contentPane);

        String aviso = invitado
                ? "Modo invitado: puedes mirar las vacantes. Regístrate para que las empresas te consideren."
                : "Estas son las vacantes activas. Las empresas eligen a los candidatos ideales.";
        JLabel info = new JLabel(aviso);
        info.setFont(Tema.NORMAL);
        info.setForeground(Tema.TEXTO_SUAVE);
        contentPane.add(info, BorderLayout.NORTH);

        String[] headers = { "Código", "Empresa", "Puesto", "Descripción", "Salario", "Provincia", "Perfil" };
        model = new DefaultTableModel(headers, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblVac = new JTable(model);
        tblVac.setRowHeight(22);
        contentPane.add(new JScrollPane(tblVac), BorderLayout.CENTER);

        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.setBackground(Tema.FONDO);
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
        model.setRowCount(0);
        for (Vacante v : BolsaLaboral.getInstancia().getVacantes()) {
            if (v.getEstado().equalsIgnoreCase("activa")) {
                Object[] fila = { v.getId(), v.getCentro().getNombreComercial(), v.getPuesto(), v.getDescripcion(),
                        v.getSalarioMin() + " - " + v.getSalarioMax(), v.getProvincia(), v.getPerfilRequerido() };
                model.addRow(fila);
            }
        }
    }
}
