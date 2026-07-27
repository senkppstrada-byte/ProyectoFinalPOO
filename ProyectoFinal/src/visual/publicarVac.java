package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.BolsaLaboral;
import logica.CentroEmpleador;
import logica.Vacante;

public class publicarVac extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FondoMenu fondomenu;
	private JTextField txtPuesto;
	private JTextField txtDescripcion;
	private JTextField txtSalMin;
	private JTextField txtSalMax;
	private JTextField txtProvincia;
	private JTextField txtCoincidencia;
	private JTextField txtPlazas;
	private JComboBox<String> cmbPerfil;
	private JCheckBox chkLicencia;
	private JCheckBox chkMudanza;
	private JButton okButton;
	private JButton cancelButton;
	private CentroEmpleador emp = BolsaLaboral.getInstancia().buscarCentroPorCuenta(BolsaLaboral.getInstancia().getCuentalog());

	public publicarVac() {
		setTitle("Publicar vacante");
		setBounds(100, 100, 520, 420);
		setLocationRelativeTo(null);

		fondomenu = new FondoMenu("/img/mant.png");
		fondomenu.setLayout(new BorderLayout());
		setContentPane(fondomenu);

		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		fondomenu.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 8, 8));
		
        contentPanel.add(new JLabel("Puesto:"));
        txtPuesto = new JTextField();
        contentPanel.add(txtPuesto);

        contentPanel.add(new JLabel("Descripcion:"));
        txtDescripcion = new JTextField();
        contentPanel.add(txtDescripcion);

        contentPanel.add(new JLabel("Salario minimo:"));
        txtSalMin = new JTextField();
        contentPanel.add(txtSalMin);

        contentPanel.add(new JLabel("Salario maximo:"));
        txtSalMax = new JTextField();
        contentPanel.add(txtSalMax);

        contentPanel.add(new JLabel("Provincia:"));
        txtProvincia = new JTextField();
        contentPanel.add(txtProvincia);

        contentPanel.add(new JLabel("Perfil requerido:"));
        cmbPerfil = new JComboBox<String>();
        cmbPerfil.addItem("Tecnico");
        cmbPerfil.addItem("Profesional");
        cmbPerfil.addItem("Obrero");
        contentPanel.add(cmbPerfil);

        contentPanel.add(new JLabel("Coincidencia minima:"));
        txtCoincidencia = new JTextField();
        contentPanel.add(txtCoincidencia);

        contentPanel.add(new JLabel("Plazas totales:"));
        txtPlazas = new JTextField();
        contentPanel.add(txtPlazas);

        contentPanel.add(new JLabel("Requiere licencia:"));
        chkLicencia = new JCheckBox();
        chkLicencia.setOpaque(false);
        contentPanel.add(chkLicencia);

        contentPanel.add(new JLabel("Requiere mudanza:"));
        chkMudanza = new JCheckBox();
        chkMudanza.setOpaque(false);
        contentPanel.add(chkMudanza);
        
        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        fondomenu.add(buttonPane, BorderLayout.SOUTH);

        {
            okButton = new JButton("Publicar");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    publicar();
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
	}
	
	
	
    public void publicar() {
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Error: No se encontro la informacion del centro logueado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtPuesto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes indicar el puesto de la vacante.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        float salMin;
        float salMax;
        float coincidencia;
        int plazas;
        try {
            salMin = Float.parseFloat(txtSalMin.getText().trim());
            salMax = Float.parseFloat(txtSalMax.getText().trim());
            coincidencia = Float.parseFloat(txtCoincidencia.getText().trim());
            plazas = Integer.parseInt(txtPlazas.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario, coincidencia y plazas deben ser numeros validos.", "Datos invalidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (salMin > salMax) {
            JOptionPane.showMessageDialog(this, "El salario minimo no puede ser mayor que el maximo.", "Datos invalidos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (plazas <= 0) {
            JOptionPane.showMessageDialog(this, "Las plazas totales deben ser mayores que cero.", "Datos invalidos", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
}	

