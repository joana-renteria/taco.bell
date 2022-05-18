package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.factorias.UsuarieADFactory;
import resources.Fuentes;
import users.Cliente;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Cursor;

public class VRegister extends JDialog implements ActionListener, FocusListener {

	private static final long serialVersionUID = -8877946520751179327L;
	private JPanel contentPane;
	private JTextField txtCorreoElectronico;
	private JPasswordField txtContrasea1;
	private JPasswordField txtContrasea2;
	private JTextField txtNombre;
	private JButton btnEntrar;
	private JButton btnX;
	JCheckBox chckbxTerminos;
	private VLogin vL;
	private static Point point = new Point(0, 0);
	private JTextField txtApellido;

	public static void cargarTipografia() {
		Fuentes fe = new Fuentes();
		fe.cargarTipografia();
	}

	/**
	 * Create the frame.
	 */
	public VRegister(VLogin vLogin) {
		super(vLogin, "Taco Bell", true);
		vL = vLogin;

		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		iconMarca.setIcon(new ImageIcon(VRegister.class.getResource("/resources/icon_marca.png")));
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
				"<html><p text-align='center'>Registrate y disfruta<br> de nuestra comida</p></html>");
		lblGreeter1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreeter1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 38));
		panelGreeter.add(lblGreeter1);

		JSeparator separator_2 = new JSeparator();
		panelGreeter.add(separator_2);

		JPanel panelRegisterContainer = new JPanel();
		panelRegisterContainer.setBackground(Color.WHITE);
		panelRegisterContainer.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelContenido.add(panelRegisterContainer);
		panelRegisterContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 60));

		JPanel panelRegister = new JPanel();
		panelRegister.setBackground(Color.WHITE);
		panelRegisterContainer.add(panelRegister);
		panelRegister.setLayout(new BoxLayout(panelRegister, BoxLayout.Y_AXIS));

		JPanel panelNombre = new JPanel();
		panelNombre.setBackground(Color.WHITE);
		panelRegister.add(panelNombre);

		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setForeground(new Color(131, 132, 133));
		txtNombre.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtNombre.setFocusable(true);
		txtNombre.setColumns(14);
		txtNombre.setBorder(null);
		panelNombre.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setText("Apellido");
		txtApellido.setForeground(new Color(131, 132, 133));
		txtApellido.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtApellido.setFocusable(true);
		txtApellido.setColumns(14);
		txtApellido.setBorder(null);
		panelNombre.add(txtApellido);

		txtNombre.addFocusListener(this);
		txtApellido.addFocusListener(this);

		JSeparator separator_3 = new JSeparator();
		panelRegister.add(separator_3);

		JPanel panelUser = new JPanel();
		panelUser.setBackground(Color.WHITE);
		panelRegister.add(panelUser);

		txtCorreoElectronico = new JTextField();
		txtCorreoElectronico.setForeground(new Color(131, 132, 133));
		txtCorreoElectronico.setText("Correo Electronico");
		txtCorreoElectronico.setBorder(null);
		txtCorreoElectronico.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtCorreoElectronico.setColumns(28);
		panelUser.add(txtCorreoElectronico);
		txtCorreoElectronico.setFocusable(true);
		txtCorreoElectronico.addFocusListener(this);

		JSeparator separator = new JSeparator();
		panelRegister.add(separator);

		JPanel panelPass1 = new JPanel();
		panelPass1.setBackground(Color.WHITE);
		panelRegister.add(panelPass1);
		panelPass1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtContrasea1 = new JPasswordField();
		txtContrasea1.setForeground(new Color(131, 132, 133));
		txtContrasea1.setText("Contrase\u00F1a");
		txtContrasea1.setBorder(null);
		txtContrasea1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtContrasea1.setColumns(28);
		panelPass1.add(txtContrasea1);
		txtContrasea1.setEchoChar((char) 0);
		txtContrasea1.setFocusable(true);
		txtContrasea1.addFocusListener(this);

		JSeparator separator_1 = new JSeparator();
		panelRegister.add(separator_1);

		JPanel panelPass2 = new JPanel();
		panelPass2.setBackground(Color.WHITE);
		panelRegister.add(panelPass2);
		panelPass2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtContrasea2 = new JPasswordField();
		txtContrasea2.setText("Repite tu Contrase\u00F1a");
		txtContrasea2.setForeground(new Color(131, 132, 133));
		txtContrasea2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		txtContrasea2.setFocusable(true);
		txtContrasea2.setEchoChar((char)0);
		txtContrasea2.setColumns(28);
		txtContrasea2.setBorder(null);
		panelPass2.add(txtContrasea2);
		txtContrasea2.addFocusListener(this);

		JSeparator separator_3_1 = new JSeparator();
		panelRegister.add(separator_3_1);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		panelContenido.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

		JPanel panelEntrar = new JPanel();
		panelEntrar.setBackground(Color.WHITE);
		panelBotones.add(panelEntrar);

		btnEntrar = new JButton("Registrarse");
		btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntrar.setMargin(new Insets(20, 20, 20, 20));
		// Color c=new Color(1f,0f,0f,.5f );
		btnEntrar.setBackground(new Color(118, 38, 161));
		panelEntrar.add(btnEntrar);
		btnEntrar.setBorder(null);
		btnEntrar.setHorizontalAlignment(SwingConstants.RIGHT);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 20));
		btnEntrar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnEntrar.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelBotones.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblRegister = new JLabel(
				"<html>Tus datos no se utilizaran con fines lucrativos<br>Puedes darte de baja cuando quieras</html>");
		lblRegister.setBackground(Color.WHITE);
		lblRegister.setForeground(new Color(69, 157, 213));
		lblRegister.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 13));
		lblRegister.setAlignmentX(0.5f);
		panel.add(lblRegister);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panelBotones.add(panel_1);

		chckbxTerminos = new JCheckBox("Acepto los terminos y condiciones");
		chckbxTerminos.setBackground(Color.WHITE);
		chckbxTerminos.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 13));
		panel_1.add(chckbxTerminos);

		JPanel panelBotonesSuperiores = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotonesSuperiores.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(20);
		panelBotonesSuperiores.setBackground(Color.WHITE);
		panelBotonesSuperiores.setBounds(1105, 0, 106, 29);
		contentPane.add(panelBotonesSuperiores);

		btnX = new JButton("");
		btnX.setBackground(Color.WHITE);
		btnX.setIcon(new ImageIcon(VRegister.class.getResource("/resources/icon_x_inactive.png")));
		btnX.setBorder(null);
		panelBotonesSuperiores.add(btnX);
		btnX.addActionListener(this);
		btnX.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnX.setIcon(new ImageIcon(VRegister.class.getResource("/resources/icon_x_active.png")));
				// btnX.setBackground(new Color(255,0,0));
			}

			public void mouseExited(MouseEvent evt) {
				btnX.setIcon(new ImageIcon(VRegister.class.getResource("/resources/icon_x_inactive.png")));
				// btnX.setBackground(new Color(255,255,255));
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEntrar)) {
			Usuarie userAux;
			String myPass = String.valueOf(txtContrasea1.getPassword());
			String myPass2 = String.valueOf(txtContrasea2.getPassword());
			if (txtNombre.getText().isEmpty()
					|| txtCorreoElectronico.getText().isEmpty()
					|| myPass.isEmpty()
					|| myPass2.isEmpty()
					|| txtNombre.getText().equals("Nombre")
					|| txtNombre.getText().equals("Apellido")
					|| txtCorreoElectronico.getText().equals("Correo Electronico")
					|| myPass.equals("Contrase\u00F1a")
					|| myPass2.equals("Repite tu Contrase\u00F1a")
					|| !chckbxTerminos.isSelected()) {
				JOptionPane.showMessageDialog(this,
						"Llena todos los campos.",
						"Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (myPass.equals(myPass2)) {
				userAux = UsuarieADFactory.getAccessUsuaries().buscarCliente(txtCorreoElectronico.getText());
				if (userAux == null) {
					userAux = new Cliente(UsuarieADFactory.getAccessUsuaries().generateCodigo("CL"), myPass, txtNombre.getText(), txtApellido.getText(), txtCorreoElectronico.getText());
					UsuarieADFactory.getAccessUsuaries().addUsuarie(userAux);
					JOptionPane.showMessageDialog(this,
							"Usuario BIEEEEEEN.",
							"Ole Betih ole",
							JOptionPane.WARNING_MESSAGE);
					this.dispose();
					vL.setVisible(true);
					txtCorreoElectronico.setText("Correo Electronico");
					txtContrasea1.setText("Contrase\u00F1a");
					txtContrasea1.setEchoChar((char) 0);
					txtCorreoElectronico.setForeground(new Color(131, 132, 133));
					txtContrasea1.setForeground(new Color(131, 132, 133));
				} else {
					JOptionPane.showMessageDialog(this,
							"Usuario ya existente.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this,
							"Las contrase\u00F1as no coinciden.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
			}

			}
		}

		if (e.getSource().equals(btnX)) {
			this.dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		this.getRootPane().setDefaultButton(btnEntrar);
		String myPass = String.valueOf(txtContrasea1.getPassword());
		String myPass2 = String.valueOf(txtContrasea2.getPassword());
		if (e.getSource().equals(txtNombre) && txtNombre.getText().equals("Nombre")) {
			txtNombre.setText("");
			txtNombre.setForeground(new Color(0, 0, 0));
		}
		if (e.getSource().equals(txtApellido) && txtApellido.getText().equals("Apellido")) {
			txtApellido.setText("");
			txtApellido.setForeground(new Color(0, 0, 0));
		}
		if (e.getSource().equals(txtContrasea1) && myPass.equals("Contrase\u00F1a")) {
			txtContrasea1.setEchoChar('*');
			txtContrasea1.setText("");
			txtContrasea1.setForeground(new Color(0, 0, 0));
		}
		if (e.getSource().equals(txtContrasea2) && myPass2.equals("Repite tu Contrase\u00F1a")) {
			txtContrasea2.setEchoChar('*');
			txtContrasea2.setText("");
			txtContrasea2.setForeground(new Color(0, 0, 0));
		}
		if (e.getSource().equals(txtCorreoElectronico) && txtCorreoElectronico.getText().equals("Correo Electronico")) {
			txtCorreoElectronico.setText("");
			txtCorreoElectronico.setForeground(new Color(0, 0, 0));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		String myPass = String.valueOf(txtContrasea1.getPassword());
		String myPass2 = String.valueOf(txtContrasea2.getPassword());
		if (e.getSource().equals(txtNombre) && txtNombre.getText().isEmpty()) {
			txtNombre.setText("Nombre");
			txtNombre.setForeground(new Color(131, 132, 133));
		}
		if (e.getSource().equals(txtApellido) && txtApellido.getText().isEmpty()) {
			txtApellido.setText("Apellido");
			txtApellido.setForeground(new Color(131, 132, 133));
		}
		if (e.getSource().equals(txtContrasea1) && myPass.isEmpty()) {
			txtContrasea1.setEchoChar((char) 0);
			txtContrasea1.setText("Contrase\u00F1a");
			txtContrasea1.setForeground(new Color(131, 132, 133));
		}
		if (e.getSource().equals(txtContrasea2) && myPass2.isEmpty()) {
			txtContrasea2.setEchoChar((char) 0);
			txtContrasea2.setText("Repite tu Contrase\u00F1a");
			txtContrasea2.setForeground(new Color(131, 132, 133));
		}
		if (e.getSource().equals(txtCorreoElectronico) && txtCorreoElectronico.getText().isEmpty()) {
			txtCorreoElectronico.setText("Correo Electronico");
			txtCorreoElectronico.setForeground(new Color(131, 132, 133));
		}
	}

}
