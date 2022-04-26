package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Rectangle;

public class VLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLogin frame = new VLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panelMarca = new JPanel();
		FlowLayout fl_panelMarca = (FlowLayout) panelMarca.getLayout();
		fl_panelMarca.setHgap(230);
		panelMarca.setBackground(new Color(118,38,161));
		contentPane.add(panelMarca);
		
		JPanel panelContenidoContainer = new JPanel();
		panelContenidoContainer.setBackground(Color.WHITE);
		contentPane.add(panelContenidoContainer);
		panelContenidoContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		
		JPanel panelContenido = new JPanel();
		panelContenido.setBackground(Color.WHITE);
		panelContenidoContainer.add(panelContenido);
		panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
		
		JPanel panelGreeter = new JPanel();
		panelGreeter.setBackground(Color.WHITE);
		FlowLayout fl_panelGreeter = (FlowLayout) panelGreeter.getLayout();
		fl_panelGreeter.setVgap(80);
		panelContenido.add(panelGreeter);
		
		JLabel lblGreeter1 = new JLabel("<html>¡Inicia sesión en<br>nuestra nueva app!</html>");
		lblGreeter1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreeter1.setFont(new Font("Iosevka Aile", Font.BOLD, 38));
		panelGreeter.add(lblGreeter1);
		
		JPanel panelLoginContainer = new JPanel();
		panelLoginContainer.setBackground(Color.WHITE);
		panelLoginContainer.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelContenido.add(panelLoginContainer);
		panelLoginContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 60));
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		panelLoginContainer.add(panelLogin);
		panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
		
		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelLogin.add(panelUser);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setFont(new Font("Iosevka Aile", Font.PLAIN, 18));
		textField.setColumns(20);
		panelUser.add(textField);
		
		JPanel panelPass = new JPanel();
		panelPass.setBackground(Color.WHITE);
		panelLogin.add(panelPass);
		panelPass.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField_1 = new JTextField();
		textField_1.setBorder(null);
		textField_1.setFont(new Font("Iosevka Aile", Font.PLAIN, 18));
		textField_1.setColumns(20);
		panelPass.add(textField_1);
		
		JPanel panelBotones = new JPanel();
		panelContenido.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		JPanel panelEntrar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelEntrar.getLayout();
		panelBotones.add(panelEntrar);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setMargin(new Insets(20, 20, 20, 20));
		btnEntrar.setBackground(new Color(118,38,161));
		panelEntrar.add(btnEntrar);
		btnEntrar.setBorder(null);
		btnEntrar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Iosevka Aile", Font.BOLD, 16));
		btnEntrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		JLabel lblRegister = new JLabel("¿Aún no te has registrado? ¡Registrate ya!");
		lblRegister.setForeground(new Color(69, 157, 213));
		lblRegister.setFont(new Font("Iosevka Aile", Font.BOLD, 17));
		panelBotones.add(lblRegister);
	}

}
