package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.factorias.UsuarieADFactory;
import users.Usuarie;

import java.awt.event.ActionEvent;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class VLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8877946520751179327L;
	private JPanel contentPane;
	private JTextField txtCorreoElectronico;
	private JPasswordField txtContrasea;
	private JButton btnEntrar;
	private static Point point = new Point(0, 0);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cargarTipografia();
					VLogin frame = new VLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void cargarTipografia() {
		Fuentes fe = new Fuentes();
		fe.cargarTipografia();
	}

	/**
	 * Create the frame.
	 */
	public VLogin() {

		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});

		JPanel panelMarca = new JPanel();
		FlowLayout fl_panelMarca = (FlowLayout) panelMarca.getLayout();
		fl_panelMarca.setVgap(180);
		fl_panelMarca.setHgap(40);
		panelMarca.setBackground(new Color(118, 38, 161));
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

		JLabel lblGreeter1 = new JLabel(
				"<html><p text-align='center'>\u00A1Inicia sesi\u00F3n en<br>nuestra nueva app!</p></html>");
		lblGreeter1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreeter1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 38));
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
		txtCorreoElectronico.setForeground(new Color(131,132,133));
		txtCorreoElectronico.setText("Correo Electronico");
		txtCorreoElectronico.setBorder(null);
		txtCorreoElectronico.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtCorreoElectronico.setColumns(20);
		panelUser.add(txtCorreoElectronico);
		txtCorreoElectronico.setFocusable(false);

		txtCorreoElectronico.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtCorreoElectronico.setFocusable(true);
				txtCorreoElectronico.requestFocus();
				String myPass = String.valueOf(txtContrasea.getPassword());
				if (txtCorreoElectronico.getText().equals("Correo Electronico")) {
					txtCorreoElectronico.setText("");
					txtCorreoElectronico.setForeground(new Color(0,0,0));
				}
				if (myPass.isEmpty()) {
					txtContrasea.setEchoChar((char) 0);
					txtContrasea.setText("Contrase\u00F1a");
					txtContrasea.setForeground(new Color(131,132,133));
				}
			}
		});

		JSeparator separator = new JSeparator();
		panelLogin.add(separator);

		JPanel panelPass = new JPanel();
		panelPass.setBackground(Color.WHITE);
		panelLogin.add(panelPass);
		panelPass.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtContrasea = new JPasswordField();
		txtContrasea.setForeground(new Color(131,132,133));
		txtContrasea.setText("Contrase\u00F1a");
		txtContrasea.setBorder(null);
		txtContrasea.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtContrasea.setColumns(20);
		panelPass.add(txtContrasea);
		txtContrasea.setEchoChar((char) 0);
		txtContrasea.setFocusable(false);

		txtContrasea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				txtContrasea.setFocusable(true);
				txtContrasea.requestFocus();
				String myPass = String.valueOf(txtContrasea.getPassword());
				if (myPass.equals("Contrase\u00F1a")) {
					txtContrasea.setEchoChar('*');
					txtContrasea.setText("");
					txtContrasea.setForeground(new Color(0,0,0));
				}
				if (txtCorreoElectronico.getText().isEmpty()) {
					txtCorreoElectronico.setText("Correo Electronico");
					txtCorreoElectronico.setForeground(new Color(131,132,133));
				}
			}
		});

		JSeparator separator_1 = new JSeparator();
		panelLogin.add(separator_1);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		panelContenido.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

		JPanel panelEntrar = new JPanel();
		panelEntrar.setBackground(Color.WHITE);
		panelBotones.add(panelEntrar);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setMargin(new Insets(20, 20, 20, 20));
		//Color c=new Color(1f,0f,0f,.5f );
		btnEntrar.setBackground(new Color(118, 38, 161));
		panelEntrar.add(btnEntrar);
		btnEntrar.setBorder(null);
		btnEntrar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 20));
		btnEntrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnEntrar.addActionListener(this);
		btnEntrar.setOpaque(false);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(100);
		panel.setBackground(Color.WHITE);
		panelBotones.add(panel);

		JLabel lblRegister = new JLabel("\u00BFA\u00FAn no te has registrado? \u00A1Registrate ya!");
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setForeground(new Color(69, 157, 213));
		lblRegister.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 17));
		lblRegister.setAlignmentX(0.5f);
		panel.add(lblRegister);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEntrar)) {
			Usuarie userAux;
			String myPass = String.valueOf(txtContrasea.getPassword());
			if (txtCorreoElectronico.getText().isEmpty() || myPass.isEmpty() || txtCorreoElectronico.equals("Correo Electronico") || myPass.equals("Contrase\u00F1a")) {
				JOptionPane.showMessageDialog(this,
						"WARNING.",
						"Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				userAux = UsuarieADFactory.getAccessUsuaries().buscarCliente(txtCorreoElectronico.getText());
				if (userAux == null) {
					userAux = UsuarieADFactory.getAccessUsuaries().buscarUsuarie(txtCorreoElectronico.getText());
					if (userAux != null && userAux.getPasswd().equals(myPass)) {
						this.dispose(); // TODO Llamar a ventana admin
					}
					else { // TODO Error correcto
						JOptionPane.showMessageDialog(this,
						"Usuario o contraseña MAAAAAAL.",
						"Warning",
						JOptionPane.WARNING_MESSAGE);
					}
				} else { 
					if (userAux.getPasswd().equals(myPass)) {
						System.out.println("Cliente");
						VMenuCliente vMC = new VMenuCliente(this, userAux);
						this.dispose();
						vMC.setVisible(true);
					}
					else // TODO Error correcto
						JOptionPane.showMessageDialog(this,
							"Usuario o contraseña MAAAAAAL.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}
				
			}
		}
	}

}
