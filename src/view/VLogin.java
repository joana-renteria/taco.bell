package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.factorias.UsuarieADFactory;
import users.Usuarie;
import resources.Fuentes;

import java.awt.event.ActionEvent;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Point;
import java.awt.Frame;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class VLogin extends JFrame implements ActionListener, FocusListener {

	private static final long serialVersionUID = -8877946520751179327L;
	private JPanel contentPane;
	private JTextField txtCorreoElectronico;
	private JPasswordField txtContrasea;
	private JButton btnEntrar;
	private JButton btnX;
	private JButton btnMinimizar;
	private JButton btnRegister;
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
		contentPane.setLayout(null);

		JPanel panelMarca = new JPanel();
		panelMarca.setBounds(5, 30, 537, 665);
		FlowLayout fl_panelMarca = (FlowLayout) panelMarca.getLayout();
		fl_panelMarca.setVgap(180);
		fl_panelMarca.setHgap(40);
		panelMarca.setBackground(new Color(118, 38, 161));
		contentPane.add(panelMarca);

		JLabel iconMarca = new JLabel("");
		iconMarca.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_marca.png")));
		panelMarca.add(iconMarca);

		JPanel panelContenidoContainer = new JPanel();
		panelContenidoContainer.setBounds(542, 30, 652, 665);
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
		txtCorreoElectronico.addFocusListener(this);

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
		txtContrasea.addFocusListener(this);

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
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.addActionListener(this);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(100);
		panel.setBackground(Color.WHITE);
		panelBotones.add(panel);

		btnRegister = new JButton("\u00BFA\u00FAn no te has registrado? \u00A1Registrate ya!");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setForeground(new Color(69, 157, 213));
		btnRegister.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 17));
		btnRegister.setAlignmentX(0.5f);
		btnRegister.setBorder(null);
		panel.add(btnRegister);
		btnRegister.addActionListener(this);
		
		JPanel panelBotonesSuperiores = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotonesSuperiores.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(20);
		panelBotonesSuperiores.setBackground(Color.WHITE);
		panelBotonesSuperiores.setBounds(1105, 0, 106, 29);
		contentPane.add(panelBotonesSuperiores);
		
		btnMinimizar = new JButton("");
		btnMinimizar.setBackground(Color.WHITE);
		btnMinimizar.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_minimizar_inactive.png")));
		btnMinimizar.setBorder(null);
		panelBotonesSuperiores.add(btnMinimizar);
		btnMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMinimizar.setFocusable(true);
		btnMinimizar.addActionListener(this);
		btnMinimizar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnMinimizar.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_minimizar_active.png")));
			}
			public void mouseExited(MouseEvent evt) {
				btnMinimizar.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_minimizar_inactive.png")));
			}
		});
		
		btnX = new JButton("");
		btnX.setBackground(Color.WHITE);
		btnX.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_x_inactive.png")));
		btnX.setBorder(null);
		panelBotonesSuperiores.add(btnX);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnX.setFocusable(false);
		btnX.addActionListener(this);
		btnX.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnX.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_x_active.png")));
			}
			public void mouseExited(MouseEvent evt) {
				btnX.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_x_inactive.png")));
			}
		});

		btnEntrar.requestFocusInWindow();
		txtCorreoElectronico.setFocusable(true);
		txtContrasea.setFocusable(true);
		this.getRootPane().setDefaultButton(btnEntrar);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(btnEntrar)) {
			Usuarie userAux;
			String myPass = String.valueOf(txtContrasea.getPassword());
			if (txtCorreoElectronico.getText().isEmpty() || myPass.isEmpty()
					|| txtCorreoElectronico.equals("Correo Electronico") || myPass.equals("Contrase\u00F1a")) {
				JOptionPane.showMessageDialog(this,
						"WARNING.",
						"Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				userAux = UsuarieADFactory.getAccessUsuaries().buscarCliente(txtCorreoElectronico.getText());
				if (userAux == null) {
					userAux = UsuarieADFactory.getAccessUsuaries().buscarUsuarie(txtCorreoElectronico.getText());
					if (userAux != null && userAux.getPasswd().equals(myPass)) {
						VGestion vG = new VGestion(this, userAux);
						this.dispose();
						vG.setVisible(true);
						txtCorreoElectronico.setText("Correo Electronico");
						txtContrasea.setText("Contrase\u00F1a");
						txtContrasea.setEchoChar((char) 0);
						txtCorreoElectronico.setForeground(new Color(131, 132, 133));
						txtContrasea.setForeground(new Color(131, 132, 133));
					} else { // TODO Error correcto
						JOptionPane.showMessageDialog(this,
								"Usuario o contraseña MAAAAAAL.",
								"Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					if (userAux.getPasswd().equals(myPass)) {
						VMenuCliente vMC = new VMenuCliente(this, userAux);
						this.dispose();
						vMC.setVisible(true);
						txtCorreoElectronico.setText("Correo Electronico");
						txtContrasea.setText("Contrase\u00F1a");
						txtContrasea.setEchoChar((char) 0);
						txtCorreoElectronico.setForeground(new Color(131, 132, 133));
						txtContrasea.setForeground(new Color(131, 132, 133));
					} else // TODO Error correcto
						JOptionPane.showMessageDialog(this,
								"Usuario o contraseña MAAAAAAL.",
								"Warning",
								JOptionPane.WARNING_MESSAGE);
				}

			}
		}
		if (e.getSource().equals(btnX)) {
			this.dispose();
		}
		if (e.getSource().equals(btnMinimizar)) {
			this.setState(Frame.ICONIFIED);
		}
		if (e.getSource().equals(btnRegister)) {
			VRegister vR = new VRegister(this);
			this.dispose();
			vR.setVisible(true);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		this.getRootPane().setDefaultButton(btnEntrar);
		String myPass = String.valueOf(txtContrasea.getPassword());
		if (e.getSource().equals(txtContrasea) && myPass.equals("Contrase\u00F1a")) {
			txtContrasea.setEchoChar('*');
					txtContrasea.setText("");
					txtContrasea.setForeground(new Color(0,0,0));
		}
		if (e.getSource().equals(txtCorreoElectronico) && txtCorreoElectronico.getText().equals("Correo Electronico")) {
			txtCorreoElectronico.setText("");
			txtCorreoElectronico.setForeground(new Color(0,0,0));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		String myPass = String.valueOf(txtContrasea.getPassword());
		if (e.getSource().equals(txtContrasea) && myPass.isEmpty()) {
			txtContrasea.setEchoChar((char) 0);
			txtContrasea.setText("Contrase\u00F1a");
			txtContrasea.setForeground(new Color(131,132,133));
		}
		if (e.getSource().equals(txtCorreoElectronico) && txtCorreoElectronico.getText().isEmpty()) {
			txtCorreoElectronico.setText("Correo Electronico");
			txtCorreoElectronico.setForeground(new Color(131,132,133));
		}
	}
}
