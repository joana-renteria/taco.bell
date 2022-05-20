package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import controller.factorias.MenuADFactory;
import controller.factorias.PedidoADFactory;
import controller.factorias.UsuarieADFactory;
import datos.Pedido;
import exceptions.GestorExcepciones;
import users.Auxiliar;
import users.Cliente;
import users.Repartidor;
import users.Trabajador;

import users.Usuarie;
import resources.Fuentes;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Point;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VGestion extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9192805958774398607L;

	private final JPanel contentPanel = new JPanel();

	// Definir colores
	private static Color colorMoradoClaro = new Color(118, 38, 181);
	private static Color colorRojoClaro = new Color(215, 7, 81);
	private static Color colorVerdeClaro = new Color(30, 180, 132);
	private static Color colorAzulClaro = new Color(21, 101, 170);

	// private static Point point = new Point(0, 0);
	private static JButton btnX;
	private static JButton btnAtras;
	private static JButton btnModificar;
	private static JButton btnNuevo;
	private static JButton btnEliminar;
	private static JButton btnClientes;
	private static JButton btnPedidos;
	private static JButton btnPersonal;
	private static VLogin vLogin;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panelProductos;
	private String titulosCliente[] = { "Código", "Nombre", "Apellido", "Correo" };
	private String titulosPedido[] = { "Código Pedido", "Fecha", "Código cliente", "Código repartidor",
			"Código establecimiento", "Menu" };
	private String titulosTrabajadores[] = { "Código Trabajador", "Nombre", "Apellido", "Código Establecimiento",
			"Horario", "Sueldo", "Clase" };
	private static Point point = new Point(0, 0);
	private JTextField textCodigo;
	private JTextField textCampo1;
	private JTextField textCampo2;
	private JTextField textCampo3;
	private JTextField textCampo4;
	private JTextField textCampo5;
	private JTextField textCampo6;
	private JLabel lblCampo1;
	private JLabel lblCampo2;
	private JLabel lblCampo3;
	private JLabel lblCampo4;
	private JLabel lblCampo5;
	private JLabel lblCampo6;
	private JSeparator separatorCampo1;
	private JSeparator separatorCampo2;
	private JSeparator separatorCampo3;
	private JSeparator separatorCampo4;
	private JSeparator separatorCampo5;
	private JSeparator separatorCampo6;
	private boolean modificarCambio = false;
	private boolean nuevoCambio = true;
	private int tablaActual = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			cargarTipografia();
			VGestion dialog = new VGestion(null, null);
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
	public VGestion(VLogin vL, Usuarie pUsuarie) {
		super(vL, "Taco Bell", true);
		vLogin = vL;

		setBounds(100, 100, 1185, 660);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.setUndecorated(true);

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
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(120);
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
		btnAtras.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_atras.png")));
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

		JLabel lblNewLabel = new JLabel("Gestiones");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelTitulo.add(lblNewLabel);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		FlowLayout flowLayout_7 = (FlowLayout) panel_5.getLayout();
		flowLayout_7.setHgap(1);
		flowLayout_7.setVgap(40);
		panelMenuFondo.add(panel_5);

		JPanel panelClientes = new JPanel();
		panelClientes.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelClientes);
		panelClientes.setLayout(new BoxLayout(panelClientes, BoxLayout.Y_AXIS));

		btnClientes = new JButton("");
		btnClientes.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_users.png")));
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnClientes.setBorder(null);
		btnClientes.setBackground(new Color(118, 38, 181));
		panelClientes.add(btnClientes);
		btnClientes.addActionListener(this);

		JPanel panelPedidos = new JPanel();
		panelPedidos.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelPedidos);
		panelPedidos.setLayout(new BoxLayout(panelPedidos, BoxLayout.Y_AXIS));

		btnPedidos = new JButton("");
		btnPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPedidos.setBorder(null);

		btnPedidos.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_pedidos.png")));
		btnPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPedidos.setHorizontalAlignment(SwingConstants.LEFT);
		btnPedidos.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnPedidos.setBackground(colorMoradoClaro);

		btnPedidos.setForeground(Color.WHITE);
		panelPedidos.add(btnPedidos);

		btnPedidos.addActionListener(this);

		btnPedidos.addActionListener(this);
		btnPedidos.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnPedidos.setBackground(new Color(98, 18, 141));
			}

			public void mouseExited(MouseEvent evt) {
				btnPedidos.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelPersonal = new JPanel();
		panelPersonal.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelPersonal);
		panelPersonal.setLayout(new BoxLayout(panelPersonal, BoxLayout.Y_AXIS));

		btnPersonal = new JButton("");
		btnPersonal.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_personal.png")));
		btnPersonal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPersonal.setHorizontalAlignment(SwingConstants.LEFT);
		btnPersonal.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnPersonal.setBackground(colorMoradoClaro);
		btnPersonal.setBorder(null);
		btnPersonal.setForeground(Color.WHITE);
		panelPersonal.add(btnPersonal);
		btnPersonal.addActionListener(this);

		btnPersonal.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnPersonal.setBackground(new Color(98, 18, 141));
			}

			public void mouseExited(MouseEvent evt) {
				btnPersonal.setBackground(colorMoradoClaro);
			}
		});

		panelProductos = new JPanel();
		panelProductos.setBounds(426, 129, 753, 526);
		contentPanel.add(panelProductos);
		panelProductos.setLayout(new GridLayout(0, 1, 0, 0));

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
				btnModificar.setBackground(new Color(10, 180, 112));
			}

			public void mouseExited(MouseEvent evt) {
				btnModificar.setBackground(colorVerdeClaro);
			}
		});

		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBounds(10, 11, 379, 355);
		panelPedido.add(panelSeleccion);
		panelSeleccion.setLayout(null);

		JPanel panelCodigo = new JPanel();
		panelCodigo.setBorder(null);
		panelCodigo.setBounds(99, 11, 197, 35);
		panelSeleccion.add(panelCodigo);
		panelCodigo.setLayout(new BoxLayout(panelCodigo, BoxLayout.Y_AXIS));

		textCodigo = new JTextField();
		textCodigo.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 20));
		textCodigo.setBorder(null);
		panelCodigo.add(textCodigo);
		textCodigo.setColumns(10);
		textCodigo.setEditable(false);

		JSeparator separatorCodigo = new JSeparator();
		panelCodigo.add(separatorCodigo);

		JPanel panelCampo1 = new JPanel();
		panelCampo1.setBorder(null);
		panelCampo1.setBounds(10, 121, 159, 35);
		panelSeleccion.add(panelCampo1);
		panelCampo1.setLayout(new BoxLayout(panelCampo1, BoxLayout.Y_AXIS));

		textCampo1 = new JTextField();
		textCampo1.setBorder(null);
		textCampo1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 14));
		panelCampo1.add(textCampo1);
		textCampo1.setEditable(false);
		textCampo1.setColumns(10);

		separatorCampo1 = new JSeparator();
		panelCampo1.add(separatorCampo1);

		JPanel panelCampo3 = new JPanel();
		panelCampo3.setBorder(null);
		panelCampo3.setBounds(10, 210, 159, 35);
		panelSeleccion.add(panelCampo3);
		panelCampo3.setLayout(new BoxLayout(panelCampo3, BoxLayout.Y_AXIS));

		textCampo3 = new JTextField();
		textCampo3.setBorder(null);
		textCampo3.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 14));
		textCampo3.setColumns(10);
		textCampo3.setEditable(false);
		panelCampo3.add(textCampo3);

		separatorCampo3 = new JSeparator();
		panelCampo3.add(separatorCampo3);

		JPanel panelCampo5 = new JPanel();
		panelCampo5.setBorder(null);
		panelCampo5.setBounds(10, 297, 159, 35);
		panelSeleccion.add(panelCampo5);
		panelCampo5.setLayout(new BoxLayout(panelCampo5, BoxLayout.Y_AXIS));

		textCampo5 = new JTextField();
		textCampo5.setBorder(null);
		textCampo5.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 14));
		textCampo5.setColumns(10);
		textCampo5.setEditable(false);
		panelCampo5.add(textCampo5);

		separatorCampo5 = new JSeparator();
		panelCampo5.add(separatorCampo5);

		JPanel panelCampo2 = new JPanel();
		panelCampo2.setBorder(null);
		panelCampo2.setBounds(210, 121, 159, 35);
		panelSeleccion.add(panelCampo2);
		panelCampo2.setLayout(new BoxLayout(panelCampo2, BoxLayout.Y_AXIS));

		textCampo2 = new JTextField();
		textCampo2.setBorder(null);
		textCampo2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 14));
		textCampo2.setColumns(10);
		textCampo2.setEditable(false);
		panelCampo2.add(textCampo2);

		separatorCampo2 = new JSeparator();
		panelCampo2.add(separatorCampo2);

		JPanel panelCampo4 = new JPanel();
		panelCampo4.setBorder(null);
		panelCampo4.setBounds(210, 210, 159, 35);
		panelSeleccion.add(panelCampo4);
		panelCampo4.setLayout(new BoxLayout(panelCampo4, BoxLayout.Y_AXIS));

		textCampo4 = new JTextField();
		textCampo4.setBorder(null);
		textCampo4.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 14));
		textCampo4.setColumns(10);
		textCampo4.setEditable(false);
		panelCampo4.add(textCampo4);

		separatorCampo4 = new JSeparator();
		panelCampo4.add(separatorCampo4);

		JPanel panelCampo6 = new JPanel();
		panelCampo6.setBorder(null);
		panelCampo6.setBounds(210, 297, 159, 35);
		panelSeleccion.add(panelCampo6);
		panelCampo6.setLayout(new BoxLayout(panelCampo6, BoxLayout.Y_AXIS));

		textCampo6 = new JTextField();
		textCampo6.setBorder(null);
		textCampo6.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 14));
		textCampo6.setColumns(10);
		textCampo6.setEditable(false);
		panelCampo6.add(textCampo6);

		separatorCampo6 = new JSeparator();
		panelCampo6.add(separatorCampo6);

		lblCampo1 = new JLabel("Nombre");
		lblCampo1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		lblCampo1.setBounds(10, 86, 159, 35);
		panelSeleccion.add(lblCampo1);

		lblCampo2 = new JLabel("<html>C\u00F3digo<br>Establecimiento</html>");
		lblCampo2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		lblCampo2.setBounds(210, 69, 159, 48);
		panelSeleccion.add(lblCampo2);

		lblCampo3 = new JLabel("Apellido");
		lblCampo3.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		lblCampo3.setBounds(10, 178, 159, 35);
		panelSeleccion.add(lblCampo3);

		lblCampo4 = new JLabel("Menu");
		lblCampo4.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		lblCampo4.setBounds(210, 178, 159, 35);
		panelSeleccion.add(lblCampo4);

		lblCampo5 = new JLabel("Correo");
		lblCampo5.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		lblCampo5.setBounds(10, 263, 159, 35);
		panelSeleccion.add(lblCampo5);

		lblCampo6 = new JLabel("Clase");
		lblCampo6.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		lblCampo6.setBounds(210, 263, 159, 35);
		panelSeleccion.add(lblCampo6);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 377, 379, 78);
		panelPedido.add(panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelBotones.add(panel);
		panel.setLayout(null);

		btnNuevo = new JButton("NUEVO");
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevo.setBounds(0, 0, 179, 78);
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 28));
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(colorAzulClaro);
		panel.add(btnNuevo);
		btnNuevo.addActionListener(this);
		btnNuevo.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnNuevo.setBackground(new Color(1, 81, 150));
			}

			public void mouseExited(MouseEvent evt) {
				btnNuevo.setBackground(colorAzulClaro);
			}
		});

		JPanel panel_4 = new JPanel();
		panel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelBotones.add(panel_4);
		panel_4.setLayout(null);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(0, 0, 189, 78);
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
		btnX.setSelectedIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_x_active.png")));
		btnX.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_x_inactive.png")));
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
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 209, 583, 121);
		panelProductos.add(scrollPane);

		table = actualizarTabla(0);
		table.setSelectionBackground(colorMoradoClaro);
		table.setSelectionForeground(Color.WHITE);
		table.setRowMargin(0);
		table.setRowHeight(25);
		table.setShowVerticalLines(false);
		table.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(colorVerdeClaro);
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 15));
		tableHeader.setBorder(null);
		tableHeader.setEnabled(false);
	}

	private JTable actualizarTabla(int x) {
		switch (x) {
			case 1:
				textCampo2.setVisible(true);
				textCampo4.setVisible(true);
				textCampo6.setVisible(false);
				separatorCampo2.setVisible(true);
				separatorCampo4.setVisible(true);
				separatorCampo6.setVisible(false);
				lblCampo2.setVisible(true);
				lblCampo4.setVisible(true);
				lblCampo6.setVisible(false);
				return new JTable(tablaPedidos(), titulosPedido);
			case 2:
				textCampo2.setVisible(true);
				textCampo4.setVisible(true);
				textCampo6.setVisible(true);
				separatorCampo2.setVisible(true);
				separatorCampo4.setVisible(true);
				separatorCampo6.setVisible(true);
				lblCampo2.setVisible(true);
				lblCampo4.setVisible(true);
				lblCampo6.setVisible(true);
				return new JTable(tablaTrabajadores(), titulosTrabajadores);
			default:
				textCampo2.setVisible(false);
				textCampo4.setVisible(false);
				textCampo6.setVisible(false);
				separatorCampo2.setVisible(false);
				separatorCampo4.setVisible(false);
				separatorCampo6.setVisible(false);
				lblCampo2.setVisible(false);
				lblCampo4.setVisible(false);
				lblCampo6.setVisible(false);
				return new JTable(tablaClientes(), titulosCliente);
		}

	}

	private void refreshTabla() {
		table.setSelectionBackground(colorMoradoClaro);
		table.setSelectionForeground(Color.WHITE);
		table.setRowMargin(0);
		table.setRowHeight(25);
		table.setShowVerticalLines(false);
		table.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(colorVerdeClaro);
		tableHeader.setForeground(Color.WHITE);
		tableHeader.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 15));
		tableHeader.setBorder(null);
		tableHeader.setEnabled(false);
	}

	public String[][] tablaClientes() {
		List<Usuarie> listaCliente;
		try {
			Collection<Usuarie> listaUser = UsuarieADFactory.getAccessUsuaries().listarUsuaries().values();
			listaCliente = listaUser.stream()
					.filter(p -> p.getClass().toString().contains("Cliente"))
					.sorted((u1, u2) -> u1.compareTo(u2))
					.collect(Collectors.toList());
			String matrizTabla[][] = new String[listaCliente.size()][4];
			if (listaCliente.size() > 0) {
				for (int i = 0; i < listaCliente.size(); i++) {
					matrizTabla[i][0] = listaCliente.get(i).getCodUsr();
					matrizTabla[i][1] = listaCliente.get(i).getNombre();
					matrizTabla[i][2] = listaCliente.get(i).getApellido();
					matrizTabla[i][3] = ((Cliente) listaCliente.get(i)).getCorreoLogin();
				}
			}
			return matrizTabla;
		} catch (GestorExcepciones ex) {
			JOptionPane.showMessageDialog(this,
					ex.getMsg(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public String[][] tablaPedidos() {
		try {
			List<Pedido> listaPedidos = PedidoADFactory.getAccessPedido().listarPedidos().values().stream()
					.collect(Collectors.toList());
			String matrizTabla[][] = new String[listaPedidos.size()][6];
			if (listaPedidos.size() > 0) {

				for (int i = 0; i < listaPedidos.size(); i++) {

					matrizTabla[i][0] = listaPedidos.get(i).getCodPed();
					matrizTabla[i][1] = listaPedidos.get(i).getFechaPed().toString();
					matrizTabla[i][2] = listaPedidos.get(i).getCodCle();
					matrizTabla[i][3] = listaPedidos.get(i).getCodRep();
					matrizTabla[i][4] = listaPedidos.get(i).getCodEst();
					matrizTabla[i][5] = listaPedidos.get(i).getMenu().getNombre();
				}
			}
			return matrizTabla;
		} catch (GestorExcepciones ex) {
			JOptionPane.showMessageDialog(this,
					ex.getMsg(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public String[][] tablaTrabajadores() {
		try {
			List<Usuarie> listaTrabajadores;
			Collection<Usuarie> listaUser = UsuarieADFactory.getAccessUsuaries().listarUsuaries().values();
			listaTrabajadores = listaUser.stream()
					.filter(p -> p instanceof Trabajador)
					.collect(Collectors.toList());
			String matrizTabla[][] = new String[listaTrabajadores.size()][8];

			if (listaTrabajadores.size() > 0) {

				for (int i = 0; i < listaTrabajadores.size(); i++) {

					matrizTabla[i][0] = listaTrabajadores.get(i).getCodUsr();
					matrizTabla[i][1] = listaTrabajadores.get(i).getNombre();
					matrizTabla[i][2] = listaTrabajadores.get(i).getApellido();
					matrizTabla[i][3] = ((Trabajador) listaTrabajadores.get(i)).getCodEst();
					matrizTabla[i][4] = ((Trabajador) listaTrabajadores.get(i)).getHorario();
					matrizTabla[i][5] = String.valueOf(((Trabajador) listaTrabajadores.get(i)).getSueldo());
					matrizTabla[i][6] = ((Trabajador) listaTrabajadores.get(i)).getClass().toString()
							.contains("Auxiliar")
									? "Auxiliar"
									: "Repartidor";
				}
			}
			return matrizTabla;
		} catch (GestorExcepciones ex) {
			JOptionPane.showMessageDialog(this,
					ex.getMsg(),
					"Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	public void setTextDisable() {
		textCampo1.setEditable(false);
		textCampo2.setEditable(false);
		textCampo3.setEditable(false);
		textCampo4.setEditable(false);
		textCampo5.setEditable(false);
		textCampo6.setEditable(false);
	}

	public void setTextEnable() {
		textCampo1.setEditable(true);
		textCampo2.setEditable(true);
		textCampo3.setEditable(true);
		textCampo4.setEditable(true);
		textCampo5.setEditable(true);
		textCampo6.setEditable(true);
	}

	public void setTextEmpty() {
		textCodigo.setText("");
		textCampo1.setText("");
		textCampo2.setText("");
		textCampo3.setText("");
		textCampo4.setText("");
		textCampo5.setText("");
		textCampo6.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			vLogin.setVisible(true);
		}
		if (e.getSource().equals(btnClientes)) {
			table = actualizarTabla(0);
			refreshTabla();
			tablaActual = 0;
			lblCampo1.setText("Nombre");
			lblCampo3.setText("Apellido");
			lblCampo5.setText("Correo");
			modificarCambio = false;
			nuevoCambio = true;
			setTextDisable();
			setTextEmpty();
			btnModificar.setText("MODIFICAR");
		}
		if (e.getSource().equals(btnPedidos)) {
			table = actualizarTabla(1);
			refreshTabla();
			tablaActual = 1;
			lblCampo1.setText("Fecha");
			lblCampo3.setText("Cod Cli");
			lblCampo5.setText("Cod Rep");
			lblCampo2.setText("Cod Est");
			lblCampo4.setText("Menu");
			modificarCambio = false;
			nuevoCambio = true;
			setTextDisable();
			setTextEmpty();
			btnModificar.setText("MODIFICAR");
		}
		if (e.getSource().equals(btnPersonal)) {
			table = actualizarTabla(2);
			refreshTabla();
			tablaActual = 2;
			lblCampo1.setText("Nombre");
			textCampo1.setEditable(true);
			lblCampo3.setText("Apellido");
			lblCampo5.setText("Cod Est");
			lblCampo2.setText("Horario");
			lblCampo4.setText("Sueldo");
			lblCampo6.setText("Vehículo/Puesto");
			modificarCambio = false;
			nuevoCambio = true;
			setTextDisable();
			setTextEmpty();
			btnModificar.setText("MODIFICAR");
		}
		if (e.getSource().equals(btnEliminar)) {
			int row = table.getSelectedRow();
			if (table.getSelectedRowCount() == 1) {
				String codAux = (String) table.getValueAt(row, 0);
				if (JOptionPane.showConfirmDialog(this,
						"¿Estás seguro que quieres borrar el elemento? Esta acción es irreversible.", "ATENCIÓN",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == 0) {
					try {
						if (codAux.contains("CL")) {
							UsuarieADFactory.getAccessUsuaries().borrarUsuarie(codAux);
							table = actualizarTabla(0);
						}
						if (codAux.contains("PE")) {
							PedidoADFactory.getAccessPedido().borrarPedido(codAux);
							table = actualizarTabla(1);
						}
						if (codAux.contains("AU") || codAux.contains("RE")) {
							UsuarieADFactory.getAccessUsuaries().borrarUsuarie(codAux);
							table = actualizarTabla(2);
						}
					} catch (GestorExcepciones ex) {
						JOptionPane.showMessageDialog(this,
								ex.getMsg(),
								"Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					refreshTabla();
				}
			} else {
				if (table.getSelectedRowCount() == 0)
					JOptionPane.showMessageDialog(this,
							"Ninguna seleccionada.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,
							"Muchas seleccionadas.",
							"Warning",
							JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource().equals(btnNuevo)) {
			if (nuevoCambio) {
				btnNuevo.setBackground(new Color(1, 81, 150));
				setTextEnable();
				switch (tablaActual) {
					case 0:
						setTextDisable();
						JOptionPane.showMessageDialog(this,
								"No se pueden crear clientes.",
								"No se pueden crear clientes",
								JOptionPane.WARNING_MESSAGE);
						break;
					case 1:
						try {
							textCampo1.setText(LocalDate.now().toString());
							nuevoCambio = !nuevoCambio;
							textCodigo.setText(PedidoADFactory.getAccessPedido().generateCodigo());
							textCampo1.setEditable(false);
						} catch (GestorExcepciones ex) {
							JOptionPane.showMessageDialog(this,
									ex.getMsg(),
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						}
						break;
					case 2:
						try {
							nuevoCambio = !nuevoCambio;
							Object[] options = { "AUXILIAR", "REPARTIDOR" };
							if (JOptionPane.showOptionDialog(null, "¿Qué quiere añadir?", "Elija",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
									null, options, options[0]) == 0) {
								textCodigo.setText(UsuarieADFactory.getAccessUsuaries().generateCodigo("AU"));
							} else {
								textCodigo.setText(UsuarieADFactory.getAccessUsuaries().generateCodigo("RE"));
							}
						} catch (GestorExcepciones ex) {
							JOptionPane.showMessageDialog(this,
									ex.getMsg(),
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						}
						break;
				}
			} else {
				switch (tablaActual) {
					case 1:
						try {
							if (textCampo3.getText().isEmpty() || textCampo5.getText().isEmpty()
									|| textCampo2.getText().isEmpty() || textCampo4.getText().isEmpty()) {
								JOptionPane.showMessageDialog(this,
										"Rellene todos los campos.",
										"Campos vacíos",
										JOptionPane.WARNING_MESSAGE);
							} else {
								PedidoADFactory.getAccessPedido()
										.grabarPedido(new Pedido(textCodigo.getText(), LocalDate.now(),
												textCampo3.getText(), textCampo5.getText(), textCampo2.getText(),
												MenuADFactory.getAccessMenu()
														.buscarMenuPorCodigo(textCampo4.getText())));
								nuevoCambio = !nuevoCambio;
								setTextEmpty();
								setTextDisable();
							}
						} catch (GestorExcepciones ex) {
							JOptionPane.showMessageDialog(this,
									ex.getMsg(),
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						}
						break;
					case 2:
						if (textCampo1.getText().isEmpty() || textCampo3.getText().isEmpty()
								|| textCampo5.getText().isEmpty() || textCampo2.getText().isEmpty()
								|| textCampo4.getText().isEmpty() || textCampo6.getText().isEmpty()) {
							JOptionPane.showMessageDialog(this,
									"Rellene todos los campos.",
									"Campos vacíos",
									JOptionPane.WARNING_MESSAGE);
						} else {
							try {
								if (textCodigo.getText().contains("AU"))
									UsuarieADFactory.getAccessUsuaries()
											.addUsuarie(new Auxiliar(textCodigo.getText(), "abcd*1234",
													textCampo1.getText(), textCampo3.getText(), textCampo5.getText(),
													textCampo2.getText(), Float.parseFloat(textCampo4.getText()),
													textCampo6.getText()));
								else
									UsuarieADFactory.getAccessUsuaries()
											.addUsuarie(new Repartidor(textCodigo.getText(), "abcd*1234",
													textCampo1.getText(), textCampo3.getText(), textCampo5.getText(),
													textCampo2.getText(), Float.parseFloat(textCampo4.getText()),
													textCampo6.getText()));
								nuevoCambio = !nuevoCambio;
								setTextEmpty();
								setTextDisable();
							} catch (GestorExcepciones ex) {
								JOptionPane.showMessageDialog(this,
										ex.getMsg(),
										"Warning",
										JOptionPane.WARNING_MESSAGE);
							}
							break;
						}
						table = actualizarTabla(tablaActual);
						refreshTabla();
						btnNuevo.setBackground(colorAzulClaro);
				}
			}
			if (e.getSource().equals(btnModificar)) {
				if (modificarCambio) { // Confirmar modificación
					switch (tablaActual) {
						case 0: // Cliente
							if (textCampo1.getText().isEmpty() || textCampo3.getText().isEmpty()
									|| textCampo5.getText().isEmpty()) {
								JOptionPane.showMessageDialog(this,
										"Rellene todos los campos.",
										"Campos vacíos",
										JOptionPane.WARNING_MESSAGE);
							} else {
								try {
									if (JOptionPane.showConfirmDialog(this,
											"¿Estás seguro que quieres modificar el cliente? Esta acción es irreversible.",
											"ATENCIÓN",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE) == 0) {
										UsuarieADFactory.getAccessUsuaries()
												.modificarUsuarie(new Cliente(textCodigo.getText(),
														UsuarieADFactory.getAccessUsuaries()
																.buscarUsuarie(textCodigo.getText())
																.getPasswd(),
														textCampo1.getText(), textCampo3.getText(),
														textCampo5.getText()));
									}
									btnModificar.setBackground(colorVerdeClaro);
									btnModificar.setText("MODIFICAR");
									modificarCambio = !modificarCambio;
									setTextDisable();
									setTextEmpty();
								} catch (GestorExcepciones ex) {
									JOptionPane.showMessageDialog(this,
											ex.getMsg(),
											"Warning",
											JOptionPane.WARNING_MESSAGE);
								}
							}
							break;
						case 1:
							if (textCampo1.getText().isEmpty() || textCampo3.getText().isEmpty()
									|| textCampo5.getText().isEmpty() || textCampo2.getText().isEmpty()
									|| textCampo4.getText().isEmpty()) {
								JOptionPane.showMessageDialog(this,
										"Rellene todos los campos.",
										"Campos vacíos",
										JOptionPane.WARNING_MESSAGE);
							} else {
								try {
									if (JOptionPane.showConfirmDialog(this,
											"¿Estás seguro que quieres modificar el pedido? Esta acción es irreversible.",
											"ATENCIÓN",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE) == 0) {
										PedidoADFactory.getAccessPedido()
												.modificarPedido(new Pedido(textCodigo.getText(),
														LocalDate.now(), textCampo3.getText(), textCampo5.getText(),
														textCampo2.getText(),
														MenuADFactory.getAccessMenu()
																.buscarMenuPorCodigo(textCampo4.getText())));
									}

									btnModificar.setBackground(colorVerdeClaro);
									btnModificar.setText("MODIFICAR");
									modificarCambio = !modificarCambio;
									setTextDisable();
									setTextEmpty();
								} catch (GestorExcepciones ex) {
									JOptionPane.showMessageDialog(this,
											ex.getMsg(),
											"Warning",
											JOptionPane.WARNING_MESSAGE);
								}
							}
							break;
						case 2:
							if (textCampo1.getText().isEmpty() || textCampo3.getText().isEmpty()
									|| textCampo5.getText().isEmpty() || textCampo2.getText().isEmpty()
									|| textCampo4.getText().isEmpty() || textCampo6.getText().isEmpty()) {
								JOptionPane.showMessageDialog(this,
										"Rellene todos los campos.",
										"Campos vacíos",
										JOptionPane.WARNING_MESSAGE);
							} else {
								try {
									if (JOptionPane.showConfirmDialog(this,
											"¿Estás seguro que quieres modificar el trabajador? Esta acción es irreversible.",
											"ATENCIÓN",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE) == 0) {
										if (textCodigo.getText().contains("AU"))
											UsuarieADFactory.getAccessUsuaries()
													.modificarUsuarie(new Auxiliar(textCodigo.getText(),
															UsuarieADFactory.getAccessUsuaries()
																	.buscarUsuarie(textCodigo.getText()).getPasswd(),
															textCampo1.getText(), textCampo3.getText(),
															textCampo5.getText(), textCampo2.getText(),
															Float.parseFloat(textCampo4.getText()),
															textCampo6.getText()));
										else
											UsuarieADFactory.getAccessUsuaries()
													.modificarUsuarie(new Repartidor(textCodigo.getText(),
															UsuarieADFactory.getAccessUsuaries()
																	.buscarUsuarie(textCodigo.getText()).getPasswd(),
															textCampo1.getText(), textCampo3.getText(),
															textCampo5.getText(), textCampo2.getText(),
															Float.parseFloat(textCampo4.getText()),
															textCampo6.getText()));
									}

									btnModificar.setBackground(colorVerdeClaro);
									btnModificar.setText("MODIFICAR");
									modificarCambio = !modificarCambio;
									setTextDisable();
									setTextEmpty();
								} catch (GestorExcepciones ex) {
									JOptionPane.showMessageDialog(this,
											ex.getMsg(),
											"Warning",
											JOptionPane.WARNING_MESSAGE);
								}
							}
							break;
					}
					table = actualizarTabla(tablaActual);
					refreshTabla();
				} else { // Preparar modificación
					int row = table.getSelectedRow();
					if (table.getSelectedRowCount() == 1) {
						textCodigo.setText((String) table.getValueAt(row, 0));
						btnModificar.setBackground(new Color(0, 150, 82));
						textCampo1.setText((String) table.getValueAt(row, 1));
						textCampo3.setText((String) table.getValueAt(row, 2));
						textCampo5.setText((String) table.getValueAt(row, 3));
						if (tablaActual == 1) {
							textCampo2.setText((String) table.getValueAt(row, 4));
							textCampo4.setText((String) table.getValueAt(row, 5));
						}
						try {
							if (tablaActual == 2) {
								textCampo2.setText((String) table.getValueAt(row, 4));
								textCampo4.setText((String) table.getValueAt(row, 5));
								if (textCodigo.getText().contains("AU"))
									textCampo6.setText(((Auxiliar) UsuarieADFactory.getAccessUsuaries()
											.buscarUsuarie(textCodigo.getText())).getPuesto());
								else
									textCampo6.setText(((Repartidor) UsuarieADFactory.getAccessUsuaries()
											.buscarUsuarie(textCodigo.getText())).getCodVehiculo());
							}
						} catch (GestorExcepciones ex) {
							JOptionPane.showMessageDialog(this,
									ex.getMsg(),
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						}
						setTextEnable();
						btnModificar.setText("GUARDAR CAMBIOS");
						modificarCambio = !modificarCambio;
					} else {
						if (table.getSelectedRowCount() == 0)
							JOptionPane.showMessageDialog(this,
									"Ninguna seleccionada.",
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						else
							JOptionPane.showMessageDialog(this,
									"Muchas seleccionadas.",
									"Warning",
									JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			if (e.getSource().equals(btnX)) {
				this.dispose();
				vLogin.dispose();
			}
		}
	}
} 