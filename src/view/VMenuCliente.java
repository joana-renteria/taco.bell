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
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		contentPanel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(74, 44, 89));
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(175);
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Consultar pedido");
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setVgap(175);
		panel_2.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.YELLOW);
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(100);
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Cambiar y revisar tus datos");
		panel_5.add(lblNewLabel_2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.PINK);
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(100);
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_3 = new JLabel("Salir");
		panel_6.add(lblNewLabel_3);
	}

}
