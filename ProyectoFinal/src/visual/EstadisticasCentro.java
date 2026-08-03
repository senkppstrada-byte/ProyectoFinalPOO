  package visual;

  import java.awt.BorderLayout;
  import java.awt.FlowLayout;
  import java.awt.GridLayout;
  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;
  import java.util.ArrayList;

  import javax.swing.BorderFactory;
  import javax.swing.JButton;
  import javax.swing.JComboBox;
  import javax.swing.JDialog;
  import javax.swing.JLabel;
  import javax.swing.JPanel;
  import javax.swing.SwingConstants;

  import logica.BolsaLaboral;
  import logica.Candidato;
  import logica.CentroEmpleador;
  import logica.Vacante;

  public class EstadisticasCentro extends JDialog {

      private CentroEmpleador emp = BolsaLaboral.getInstancia()
              .buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());
      private JComboBox<String> cmbVacantes;
      private ArrayList<Vacante> misVacantes = new ArrayList<Vacante>();
      private JLabel lblMatch;

      public EstadisticasCentro() {
          setTitle("Estadísticas del centro");
          setSize(620, 560);
          setLocationRelativeTo(null);

          JPanel contentPane = new JPanel(new BorderLayout(0, 14));
          contentPane.setBackground(Tema.FONDO);
          contentPane.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
          setContentPane(contentPane);

          JLabel titulo = new JLabel("Estadísticas del centro");
          titulo.setFont(Tema.TITULO);
          titulo.setForeground(Tema.TEXTO);
          contentPane.add(titulo, BorderLayout.NORTH);

          JPanel centro = new JPanel(new BorderLayout(0, 14));
          centro.setBackground(Tema.FONDO);
          contentPane.add(centro, BorderLayout.CENTER);

          centro.add(construirKpis(), BorderLayout.NORTH);

          contentPane.add(construirSur(), BorderLayout.SOUTH);
      }

      private JPanel construirKpis() {
          int vacantesActivas = 0;
          int plazasTotales = 0;
          int plazasOcupadas = 0;
          if (emp != null) {
              for (Vacante v : BolsaLaboral.getInstancia().getVacantes()) {
                  if (v.getCentro() != null && v.getCentro().getId().equals(emp.getId())) {
                      if (v.getEstado().equalsIgnoreCase("activa")) {
                          vacantesActivas++;
                      }
                      plazasTotales += v.getPlazasTotales();
                      plazasOcupadas += v.getPlazasOcupadas();
                  }
              }
          }
          int disponibles = totalDisponibles();

          JPanel fila = new JPanel(new GridLayout(1, 3, 12, 0));
          fila.setBackground(Tema.FONDO);
          fila.add(tarjeta(String.valueOf(vacantesActivas), "Vacantes activas"));
          fila.add(tarjeta(plazasOcupadas + "/" + plazasTotales, "Plazas ocupadas"));
          fila.add(tarjeta(String.valueOf(disponibles), "Candidatos disponibles"));
          return fila;
      }

      private JPanel tarjeta(String numero, String texto) {
          JPanel t = new JPanel(new BorderLayout());
          t.setBackground(Tema.PANEL);
          t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Tema.BORDE),
                  BorderFactory.createEmptyBorder(14, 10, 14, 10)));
          JLabel n = new JLabel(numero, SwingConstants.CENTER);
          n.setFont(Tema.KPI);
          n.setForeground(Tema.PRIMARIO);
          JLabel c = new JLabel(texto, SwingConstants.CENTER);
          c.setFont(Tema.NORMAL);
          c.setForeground(Tema.TEXTO_SUAVE);
          t.add(n, BorderLayout.CENTER);
          t.add(c, BorderLayout.SOUTH);
          return t;
      }

      private JPanel construirSur() {
          JPanel sur = new JPanel(new BorderLayout(0, 10));
          sur.setBackground(Tema.FONDO);

          JPanel filaVac = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
          filaVac.setBackground(Tema.FONDO);
          JLabel lbl = new JLabel("Vacante:");
          lbl.setFont(Tema.NORMAL);
          lbl.setForeground(Tema.TEXTO);
          cmbVacantes = new JComboBox<String>();
          if (emp != null) {
              for (Vacante v : BolsaLaboral.getInstancia().getVacantes()) {
                  if (v.getCentro() != null && v.getCentro().getId().equals(emp.getId())) {
                      misVacantes.add(v);
                      cmbVacantes.addItem(v.getId() + " - " + v.getPuesto());
                  }
              }
          }
          cmbVacantes.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  actualizarMatch();
              }
          });
          filaVac.add(lbl);
          filaVac.add(cmbVacantes);

          lblMatch = new JLabel(" ");
          lblMatch.setFont(Tema.NORMAL);
          lblMatch.setForeground(Tema.TEXTO_SUAVE);

          JPanel cajaMatch = new JPanel(new BorderLayout());
          cajaMatch.setBackground(Tema.FONDO);
          cajaMatch.add(filaVac, BorderLayout.NORTH);
          cajaMatch.add(lblMatch, BorderLayout.CENTER);

          JButton cerrar = Tema.botonSecundario("Cerrar");
          cerrar.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  dispose();
              }
          });
          JPanel filaBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
          filaBtn.setBackground(Tema.FONDO);
          filaBtn.add(cerrar);

          sur.add(cajaMatch, BorderLayout.CENTER);
          sur.add(filaBtn, BorderLayout.SOUTH);

          actualizarMatch();
          return sur;
      }

      private void actualizarMatch() {
          int idx = cmbVacantes.getSelectedIndex();
          if (idx < 0 || idx >= misVacantes.size()) {
              lblMatch.setText("No hay vacantes para analizar.");
              return;
          }
          Vacante v = misVacantes.get(idx);
          BolsaLaboral bolsa = BolsaLaboral.getInstancia();
          float suma = 0;
          int cuenta = 0;
          int cumplen = 0;
          for (Candidato c : bolsa.getCandidatos()) {
              if (Util.estaDisponible(c)) {
                  float m = bolsa.calcMatch(v, c);
                  suma += m;
                  cuenta++;
                  if (m >= v.getCoincidenciaMinima()) {
                      cumplen++;
                  }
              }
          }
          float promedio = cuenta > 0 ? suma / cuenta : 0;
          lblMatch.setText(String.format("Match promedio de disponibles: %.1f%%   |   Cumplen el mínimo (%.0f%%): %d",
                  promedio, v.getCoincidenciaMinima(), cumplen));
      }

      private int totalDisponibles() {
          int n = 0;
          for (Candidato c : BolsaLaboral.getInstancia().getCandidatos()) {
              if (Util.estaDisponible(c)) {
                  n++;
              }
          }
          return n;
      }
  }