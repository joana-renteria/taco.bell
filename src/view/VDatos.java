package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.factorias.UsuarieADFactory;
import resources.fuentes.Fuentes;
import users.Cliente;
import users.Usuarie;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class VDatos extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5346279688602835531L;

	private final JPanel contentPanel = new JPanel();
	private static Point point = new Point(0, 0);

	// Definir colores
	private static Color colorMoradoClaro = new Color(118, 38, 161);
	private static Color colorRojoClaro = new Color(215, 7, 81);
	private static Color colorVerdeClaro = new Color(30, 180, 132);
	// private static Color colorAzulClaro = new Color(21, 101, 170);

	// private static Point point = new Point(0, 0);
	private static JButton btnX;
	private static JButton btnAtras;
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private static JDialog vMC;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCorreo;
	private JPasswordField textContra1;
	private JPasswordField textContra2;
	private Usuarie pUser;
	private JList<String> listaDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			cargarTipografia();
			Cliente pCliente = new Cliente("1", "2", "3", "4", "5");
			VDatos dialog = new VDatos(null, pCliente);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga la tipografia Iosevka Aile Heavy
	 *
	 */
	private static void cargarTipografia() {
		Fuentes fe = new Fuentes();
		fe.cargarTipografia();
	}

	/**
	 * Create the dialog.
	 */
	public VDatos(JDialog VMenuCliente, Usuarie pUsuarie) {
		super(VMenuCliente, "Taco Bell", true);
		vMC = VMenuCliente;
		pUser = pUsuarie;
		setUndecorated(true);

		setBounds(100, 100, 1185, 660);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.setUndecorated(true);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(240, 240, 240));
		panelMenu.setBounds(5, 28, 1174, 100);
		panelMenu.setAlignmentY(Component.TOP_ALIGNMENT);
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		contentPanel.add(panelMenu);

		JPanel panelMenuFondo = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelMenuFondo.getLayout();
		flowLayout_1.setVgap(30);
		flowLayout_1.setHgap(235);
		panelMenuFondo.setBackground(colorMoradoClaro);
		panelMenu.add(panelMenuFondo);

		JPanel panelVolver = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panelVolver.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(0);
		panelVolver.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelVolver);

		btnAtras = new JButton("");
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.setBorder(null);
		btnAtras.setBackground(colorMoradoClaro);
		btnAtras.setIcon(new ImageIcon(VDatos.class.getResource("/resources/icon_atras.png")));
		panelVolver.add(btnAtras);
		btnAtras.addActionListener(this);
		btnAtras.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnAtras.setBackground(new Color(98, 18, 141));
			}

			public void mouseExited(MouseEvent evt) {
				btnAtras.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelTitulo);
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Tus datos");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelTitulo.add(lblNewLabel);

		JPanel panelClientes = new JPanel();
		panelClientes.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelClientes);
		panelClientes.setLayout(new BoxLayout(panelClientes, BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		panel_1.setBackground(colorMoradoClaro);
		panelClientes.add(panel_1);

		JPanel panelPedidos = new JPanel();
		panelPedidos.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelPedidos);
		panelPedidos.setLayout(new BoxLayout(panelPedidos, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(0);
		panel_2.setBackground(colorMoradoClaro);
		panelPedidos.add(panel_2);

		JPanel panelPersonal = new JPanel();
		panelPersonal.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelPersonal);
		panelPersonal.setLayout(new BoxLayout(panelPersonal, BoxLayout.Y_AXIS));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_3.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setHgap(0);
		panel_3.setBackground(colorMoradoClaro);
		panelPersonal.add(panel_3);

		JPanel panelProductos = new JPanel();
		panelProductos.setBounds(426, 129, 753, 526);
		contentPanel.add(panelProductos);
		panelProductos.setLayout(null);

		JPanel panelCodigo = new JPanel();
		panelCodigo.setBounds(248, 23, 274, 77);
		panelProductos.add(panelCodigo);
		panelCodigo.setLayout(new BoxLayout(panelCodigo, BoxLayout.Y_AXIS));

		textCodigo = new JTextField();
		textCodigo.setBorder(null);
		textCodigo.setFont(new Font("Dialog", Font.PLAIN, 32));
		panelCodigo.add(textCodigo);
		textCodigo.setColumns(10);
		textCodigo.setEditable(false);
		textCodigo.setText(pUsuarie.getCodUsr());

		JSeparator separatorCodigo = new JSeparator();
		panelCodigo.add(separatorCodigo);

		JPanel panelNombre = new JPanel();
		panelNombre.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelNombre.setBounds(30, 151, 205, 44);
		panelProductos.add(panelNombre);
		panelNombre.setLayout(null);

		textNombre = new JTextField();
		textNombre.setBounds(0, 0, 205, 54);
		textNombre.setBorder(null);
		textNombre.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelNombre.add(textNombre);
		textNombre.setColumns(10);
		textNombre.setText(pUsuarie.getNombre());

		JPanel panelApellido = new JPanel();
		panelApellido.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelApellido.setBounds(30, 276, 205, 44);
		panelProductos.add(panelApellido);
		panelApellido.setLayout(new BoxLayout(panelApellido, BoxLayout.X_AXIS));

		textApellido = new JTextField();
		panelApellido.add(textApellido);
		textApellido.setColumns(10);
		textApellido.setBorder(null);
		textApellido.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		textApellido.setText(pUsuarie.getApellido());

		JPanel panelCorreo = new JPanel();
		panelCorreo.setLayout(null);
		panelCorreo.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelCorreo.setBounds(30, 400, 205, 44);
		panelProductos.add(panelCorreo);

		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBorder(null);
		textCorreo.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		textCorreo.setBounds(0, 0, 205, 54);
		panelCorreo.add(textCorreo);
		textCorreo.setText(((Cliente) pUsuarie).getCorreoLogin());

		JPanel panelContra1 = new JPanel();
		panelContra1.setLayout(null);
		panelContra1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelContra1.setBounds(464, 151, 205, 44);
		panelProductos.add(panelContra1);

		textContra1 = new JPasswordField();
		textContra1.setColumns(10);
		textContra1.setBorder(null);
		textContra1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		textContra1.setBounds(0, 0, 205, 54);
		panelContra1.add(textContra1);

		JPanel panelContra2 = new JPanel();
		panelContra2.setLayout(null);
		panelContra2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelContra2.setBounds(464, 276, 205, 44);
		panelProductos.add(panelContra2);

		textContra2 = new JPasswordField();
		textContra2.setColumns(10);
		textContra2.setBorder(null);
		textContra2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		textContra2.setBounds(0, 0, 205, 54);
		panelContra2.add(textContra2);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 22));
		lblNombre.setBounds(30, 108, 200, 37);
		panelProductos.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 22));
		lblApellido.setBounds(30, 235, 200, 37);
		panelProductos.add(lblApellido);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 22));
		lblCorreo.setBounds(30, 357, 200, 37);
		panelProductos.add(lblCorreo);

		JLabel lblContra1 = new JLabel("Contrase\u00F1a");
		lblContra1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 22));
		lblContra1.setBounds(464, 108, 200, 37);
		panelProductos.add(lblContra1);

		JLabel lblContra2 = new JLabel("Repite contrase\u00F1a");
		lblContra2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 22));
		lblContra2.setBounds(464, 233, 200, 37);
		panelProductos.add(lblContra2);

		JLabel lblRecordatorio = new JLabel(
				"<html>- Recuerde que su informaci\u00F3n es protegida por la empresa y se podr\u00E1 dar de baja en cualquier momento.</html>");
		lblRecordatorio.setFont(new Font("Iosevka Aile Heavy", Font.ITALIC, 12));
		lblRecordatorio.setBounds(386, 400, 315, 67);
		panelProductos.add(lblRecordatorio);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(414, 133, 2, 522);
		contentPanel.add(separator);

		JPanel panelPedido = new JPanel();
		panelPedido.setLayout(null);
		panelPedido.setBounds(5, 118, 399, 537);
		contentPanel.add(panelPedido);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(colorVerdeClaro);
		btnModificar.setBorder(null);
		btnModificar.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 32));
		btnModificar.setBounds(10, 466, 379, 60);
		panelPedido.add(btnModificar);
		btnModificar.addActionListener(this);
		btnModificar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnModificar.setBackground(new Color(10, 160, 112));
			}

			public void mouseExited(MouseEvent evt) {
				btnModificar.setBackground(colorVerdeClaro);
			}
		});

		JPanel panelMetaDatos = new JPanel();
		panelMetaDatos.setBounds(10, 11, 379, 355);
		panelPedido.add(panelMetaDatos);
		panelMetaDatos.setLayout(new BoxLayout(panelMetaDatos, BoxLayout.X_AXIS));

		String[] data = { "Código          | " + pUsuarie.getCodUsr(), "Nombre        | " + pUsuarie.getNombre(),
				"Apellido        | " + pUsuarie.getApellido(), "Contraseña | ********",
				"Correo          | " + ((Cliente) pUsuarie).getCorreoLogin() };
		listaDatos = new JList<String>(data);
		panelMetaDatos.add(listaDatos);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 377, 379, 78);
		panelPedido.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelBotones.add(panel_4);
		panel_4.setLayout(null);

		btnEliminar = new JButton("ELIMINAR DATOS");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(0, 0, 379, 78);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 28));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(colorRojoClaro);
		panel_4.add(btnEliminar);
		btnEliminar.addActionListener(this);
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnEliminar.setBackground(new Color(195, 0, 61));
			}

			public void mouseExited(MouseEvent evt) {
				btnEliminar.setBackground(colorRojoClaro);
			}
		});

		JPanel panelBotonesVentana = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelBotonesVentana.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelBotonesVentana.setBounds(1072, 0, 107, 29);
		contentPanel.add(panelBotonesVentana);

		btnX = new JButton("");
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnX.setBackground(SystemColor.menu);
		btnX.setBorder(null);
		btnX.setSelectedIcon(new ImageIcon(VDatos.class.getResource("/resources/icon_x_active.png")));
		btnX.setIcon(new ImageIcon(VDatos.class.getResource("/resources/icon_x_inactive.png")));
		panelBotonesVentana.add(btnX);
		btnX.addActionListener(this);
		btnX.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnX.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_x_active.png")));
			}

			public void mouseExited(MouseEvent evt) {
				btnX.setIcon(new ImageIcon(VLogin.class.getResource("/resources/icon_x_inactive.png")));
			}
		});

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnX)) {
			this.dispose();
		}
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			vMC.setVisible(true);
		}
		if (e.getSource().equals(btnEliminar)) {
			if (JOptionPane.showConfirmDialog(this,
					"¿Estás seguro que quieres borrar sus datos? Esta acción es irreversible.", "ATENCIÓN",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == 0) {
				UsuarieADFactory.getAccessUsuaries().borrarUsuarie(pUser.getCodUsr());
				VMenuCliente.volverLogin();
			} else {
				JOptionPane.showMessageDialog(this,
						"Abortado.",
						"Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource().equals(btnModificar)) {
			String myPass = new String(textContra1.getPassword());
			String myPass2 = new String(textContra2.getPassword());
			if (textNombre.getText().isEmpty() || textApellido.getText().isEmpty() || textCorreo.getText().isEmpty()
					|| myPass.isEmpty() || myPass2.isEmpty()) {
				JOptionPane.showMessageDialog(this,
						"Rellene todos los campos.",
						"Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (myPass.equals(myPass2)) {
					if (JOptionPane.showConfirmDialog(this,
							"¿Estás seguro que quieres modificar sus datos? Esta acción es irreversible.", "ATENCIÓN",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == 0) {
						pUser.setNombre(textNombre.getText());
						pUser.setApellido(textApellido.getText());
						pUser.setPasswd(myPass);
						((Cliente) pUser).setCorreoLogin(textCorreo.getText());
						UsuarieADFactory.getAccessUsuaries().modificarUsuarie(pUser);
						JOptionPane.showMessageDialog(this,
								"Modificado satisfactoriamente.",
								"Todo correcto",
								JOptionPane.WARNING_MESSAGE);
						String[] data = { "Código          | " + pUser.getCodUsr(),
								"Nombre        | " + pUser.getNombre(),
								"Apellido        | " + pUser.getApellido(), "Contraseña | ********",
								"Correo          | " + ((Cliente) pUser).getCorreoLogin() };
						listaDatos.setListData(data);
					} else {
						JOptionPane.showMessageDialog(this,
								"Abortado.",
								"Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"Las contraseñas no coinciden.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
}
