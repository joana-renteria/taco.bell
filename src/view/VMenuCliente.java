package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;

public class VMenuCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VMenuCliente dialog = new VMenuCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VMenuCliente() {
		setBounds(100, 100, 1200, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(118, 38, 161));
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 350));
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Realizar nuevo pedido");
		panel_7.add(lblNewLabel);
		lblNewLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Consultar pedido");
		lblNewLabel_1.setBounds(79, 107, 122, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cambiar y revisar tus datos");
		lblNewLabel_2.setBounds(-25, 452, 195, 15);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Salir");
		lblNewLabel_3.setBounds(169, 452, 32, 15);
		panel_1.add(lblNewLabel_3);
	}

}
