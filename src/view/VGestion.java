package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.Usuarie;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Point;
import java.awt.Cursor;

public class VGestion extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9192805958774398607L;

	private final JPanel contentPanel = new JPanel();
	
	// Definir colores
	private static Color colorMoradoClaro = new Color(118, 38, 161);
	private static Color colorRojoClaro = new Color(215, 7, 81);
	private static Color colorVerdeClaro = new Color(30, 180, 132);
	private static Color colorAzulClaro = new Color(21, 101, 170);

	//private static Point point = new Point(0, 0);
  private static JButton btnX;
	private static JButton btnAtras;
	private static JButton btnPedir;
	private static JButton btnNuevo;
	private static JButton btnEliminar;
	private static JButton btnClientes;
	private static JButton btnPedidos;
	private static JButton btnPersonal;
	private static VLogin vLogin;
	private static Point point = new Point(0, 0);
	
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
		super(vL, "Taco Bell",true);
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
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnClientes.setBorder(null);
		btnClientes.setBackground(new Color(118, 38, 161));
		panelClientes.add(btnClientes);
		
		JPanel panelPedidos = new JPanel();
		panelPedidos.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelPedidos);
		panelPedidos.setLayout(new BoxLayout(panelPedidos, BoxLayout.Y_AXIS));
		

		btnPedidos = new JButton("");
		
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
		
		JButton btnPersonal = new JButton("");
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
		
		JPanel panelProductos = new JPanel();
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
		
		btnPedir = new JButton("MODIFICAR");
		btnPedir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPedir.setForeground(Color.WHITE);
		btnPedir.setBackground(colorVerdeClaro);
		btnPedir.setBorder(null);
		btnPedir.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 32));
		btnPedir.setBounds(10, 466, 379, 60);
		panelPedido.add(btnPedir);
		btnPedir.addActionListener(this);
		btnPedir.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnPedir.setBackground(new Color(10, 160, 112));
			}
			public void mouseExited(MouseEvent evt) {
				btnPedir.setBackground(colorVerdeClaro);
			}
		});
		
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBounds(10, 11, 379, 355);
		panelPedido.add(panelSeleccion);
		panelSeleccion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			vLogin.setVisible(true);
		}
		if (e.getSource().equals(btnClientes)) {
			// TODO
		}
		if (e.getSource().equals(btnPedidos)) {
			// TODO
		}
		if (e.getSource().equals(btnPersonal)) {
			// TODO
		}
		if (e.getSource().equals(btnEliminar)) {
			// TODO
		}
		if (e.getSource().equals(btnNuevo)) {
			// TODO
		}
		if (e.getSource().equals(btnPedir)) {
			// TODO
		}
		if (e.getSource().equals(btnX)) {
			this.dispose();
			vLogin.dispose();
		}
	}
}