package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.BolsaLaboral;
import logica.Candidato;
import logica.Postulacion;
import logica.Vacante;

public class SelCand extends JDialog {

    private DefaultTableModel modelt;
    private DefaultTableModel models;
    private JTable tbltop;
    private JTable tblSels;
    private Candidato selectedt = null;
    private Candidato selectedb = null;
    private JButton btnbajar;
    private JButton btnsubir;
    private JButton okButton;
    private Vacante vacante;
    private ArrayList<Candidato> listTop = new ArrayList<Candidato>();
    private ArrayList<Candidato> listBot = new ArrayList<Candidato>();

    public SelCand(Vacante vac) {
        this.vacante = vac;

        setTitle("Seleccionar candidato - " + vac.getPuesto());
        setSize(760, 480);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(0, 8));
        contentPane.setBackground(Tema.FONDO);
        contentPane.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(contentPane);

        JLabel info = new JLabel("Candidatos disponibles (ordenados por match). Baja a los que quieras contratar.");
        info.setFont(Tema.NORMAL);
        info.setForeground(Tema.TEXTO_SUAVE);
        contentPane.add(info, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 8));
        centro.setBackground(Tema.FONDO);

        String[] headers = { "Código", "Nombre", "Provincia", "Perfil", "Match" };
        modelt = new DefaultTableModel(headers, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tbltop = new JTable(modelt);
        tbltop.setRowHeight(22);
        tbltop.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = tbltop.getSelectedRow();
                if (index >= 0 && index < listTop.size()) {
                    btnbajar.setEnabled(true);
                    selectedt = listTop.get(index);
                }
            }
        });

        models = new DefaultTableModel(headers, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblSels = new JTable(models);
        tblSels.setRowHeight(22);
        tblSels.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = tblSels.getSelectedRow();
                if (index >= 0 && index < listBot.size()) {
                    btnsubir.setEnabled(true);
                    selectedb = listBot.get(index);
                }
            }
        });

        JPanel arriba = new JPanel(new BorderLayout(0, 4));
        arriba.setBackground(Tema.FONDO);
        JLabel lblTop = new JLabel("Disponibles");
        lblTop.setFont(Tema.SUBTITULO);
        lblTop.setForeground(Tema.TEXTO);
        arriba.add(lblTop, BorderLayout.NORTH);
        arriba.add(new JScrollPane(tbltop), BorderLayout.CENTER);

        JPanel abajo = new JPanel(new BorderLayout(0, 4));
        abajo.setBackground(Tema.FONDO);
        JLabel lblBot = new JLabel("Elegidos para contratar");
        lblBot.setFont(Tema.SUBTITULO);
        lblBot.setForeground(Tema.TEXTO);
        abajo.add(lblBot, BorderLayout.NORTH);
        abajo.add(new JScrollPane(tblSels), BorderLayout.CENTER);

        centro.add(arriba);
        centro.add(abajo);
        contentPane.add(centro, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.setBackground(Tema.FONDO);

        btnbajar = Tema.botonSecundario("Bajar");
        btnbajar.setEnabled(false);
        btnbajar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bajar();
            }
        });
        botones.add(btnbajar);

        btnsubir = Tema.botonSecundario("Subir");
        btnsubir.setEnabled(false);
        btnsubir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                subir();
            }
        });
        botones.add(btnsubir);

        okButton = Tema.botonPrimario("Contratar seleccionados");
        okButton.setEnabled(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionar();
            }
        });
        botones.add(okButton);

        JButton cerrar = Tema.botonSecundario("Cerrar");
        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        botones.add(cerrar);

        contentPane.add(botones, BorderLayout.SOUTH);

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
        int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas contratar a los candidatos elegidos?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            BolsaLaboral bolsa = BolsaLaboral.getInstancia();
            for (Candidato c : listBot) {
                if (!vacante.hayPlazasDisponibles()) {
                    break;
                }
                float match = bolsa.calcMatch(vacante, c);
                Postulacion p = new Postulacion("P-" + BolsaLaboral.generadorIdPos, c, vacante, LocalDate.now(), match,
                        "seleccionada");
                bolsa.publicarPostulacion(p);
                BolsaLaboral.generadorIdPos++;
                vacante.ocuparPlaza();
            }
            bolsa.saveDatos();
            JOptionPane.showMessageDialog(this, "Candidatos contratados con éxito.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    public void loadtop() {
    	listTop.clear();
        listBot.clear();
        final BolsaLaboral bolsa = BolsaLaboral.getInstancia();
        for (Candidato c : bolsa.getCandidatos()) {
            if (Util.estaDisponible(c)) {
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
        for (Candidato c : listTop) {
            modelt.addRow(fila(c));
        }
        models.setRowCount(0);
        for (Candidato c : listBot) {
            models.addRow(fila(c));
        }
        okButton.setEnabled(!listBot.isEmpty());
    }

    private Object[] fila(Candidato c) {
        float match = BolsaLaboral.getInstancia().calcMatch(vacante, c);
        return new Object[] { c.getId(), c.getNombreCompleto(), c.getProvincia(), Util.perfil(c),
                String.format("%.1f%%", match) };
    }
}
