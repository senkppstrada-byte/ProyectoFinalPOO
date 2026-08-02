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
import logica.Candidato;
import logica.Postulacion;

public class ListPos extends JDialog {

    private DefaultTableModel model;
    private JTable tblPos;
    private Candidato cand = BolsaLaboral.getInstancia()
            .buscarCandidatoPorCuenta(BolsaLaboral.getInstancia().getCuentalog());

    public ListPos() {
        setTitle("Mis contrataciones");
        setSize(680, 360);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(0, 10));
        contentPane.setBackground(Tema.FONDO);
        contentPane.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        setContentPane(contentPane);

        JLabel info = new JLabel("Vacantes en las que una empresa te ha contratado.");
        info.setFont(Tema.NORMAL);
        info.setForeground(Tema.TEXTO_SUAVE);
        contentPane.add(info, BorderLayout.NORTH);

        String[] headers = { "Código", "Empresa", "Puesto", "Fecha", "Estado" };
        model = new DefaultTableModel(headers, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblPos = new JTable(model);
        tblPos.setRowHeight(22);
        contentPane.add(new JScrollPane(tblPos), BorderLayout.CENTER);

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

        loadPostulaciones();
    }

    public void loadPostulaciones() {
    }
}
