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
import java.io.File;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class VLogin extends JFrame {

	File fontFile = new File("/resources/iosevka-aile-heavy.ttf");

	private JPanel contentPane;
	private JTextField txtCorreoElectronico;
	private JTextField txtContrasea;
	
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

		try {
			Font bigFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (Exception e) {
			System.err.println("Error cargando la tipografia Iosevka Aile Heavy");
		}

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
		fl_panelMarca.setVgap(180);
		fl_panelMarca.setHgap(40);
		panelMarca.setBackground(new Color(118,38,161));
		contentPane.add(panelMarca);
		
		JLabel iconMarca = new JLabel("");
		iconMarca.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_marca.png")));
		panelMarca.add(iconMarca);
		
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
		panelContenido.add(panelGreeter);
		panelGreeter.setLayout(new BoxLayout(panelGreeter, BoxLayout.X_AXIS));
		
		JLabel lblGreeter1 = new JLabel("<html>¡Inicia sesión en<br>nuestra nueva app!</html>");
		lblGreeter1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreeter1.setFont(new Font("Iosevka Aile", Font.BOLD, 38));
		panelGreeter.add(lblGreeter1);
		
		JSeparator separator_2 = new JSeparator();
		panelGreeter.add(separator_2);
		
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
		
		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setText("Correo Electronico");
		txtCorreoElectronico.setBorder(null);
		txtCorreoElectronico.setFont(new Font("Iosevka Aile", Font.PLAIN, 18));
		txtCorreoElectronico.setColumns(20);
		panelUser.add(txtCorreoElectronico);
		
		JSeparator separator = new JSeparator();
		panelLogin.add(separator);
		
		JPanel panelPass = new JPanel();
		panelPass.setBackground(Color.WHITE);
		panelLogin.add(panelPass);
		panelPass.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtContrasea = new JTextField();
		txtContrasea.setText("Contrase\u00F1a");
		txtContrasea.setBorder(null);
		txtContrasea.setFont(new Font("Iosevka Aile", Font.PLAIN, 18));
		txtContrasea.setColumns(20);
		panelPass.add(txtContrasea);
		
		JSeparator separator_1 = new JSeparator();
		panelLogin.add(separator_1);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		panelContenido.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		JPanel panelEntrar = new JPanel();
		panelEntrar.setBackground(Color.WHITE);
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
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(100);
		panel.setBackground(Color.WHITE);
		panelBotones.add(panel);
		
		JLabel lblRegister = new JLabel("¿Aún no te has registrado? ¡Registrate ya!");
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setForeground(new Color(69, 157, 213));
		lblRegister.setFont(new Font("Dialog", Font.BOLD, 17));
		lblRegister.setAlignmentX(0.5f);
		panel.add(lblRegister);
	}

}
