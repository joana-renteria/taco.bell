package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.factorias.MenuADFactory;
import controller.factorias.ProductoADFactory;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import datos.Producto;
import exceptions.GestorExcepciones;
import resources.fuentes.Fuentes;
import users.Usuarie;
import datos.Menu;

public class VPedido extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378271115951639896L;

	private final JPanel contentPanel = new JPanel();

	private JTable tablaProductos;
	private JButton btnAtras;
	private JDialog vMenuCliente;
	private int menu = 0;
	private static JButton btnX;
	private static JButton btnMenus;
	private static JButton btnAperitivos;
	private static JButton btnBebida;
	private static Point point = new Point(0, 0);

	//private float descuento = 0;
	private float precioTotal = 0;

	private ArrayList<Menu> carritoMenus;
	private ArrayList<Producto> carritoProductos;

	// Definir colores
	private static Color colorMoradoClaro = new Color(118, 38, 161);
	private static Color colorAzulClaro = new Color(21, 131, 170);
	private static Color colorVerdeClaro = new Color(30, 180, 132);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			cargarTipografia();
			VPedido dialog = new VPedido(null,null);
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
	 * Carga de selecciones para los distintos menus
	 *
	 * <p> Necesario para cargar los distintos productos/menus disponibles segun pestaña elegida.
	 *
	 * @param menu	indica la pestaña seleccionada
	 * @return	tabla con los productos disponibles
	 * @throws GestorExcepciones 
	 */
	private JTable cargarDatos(int menu) throws GestorExcepciones {

		List<NombrePrecio<String,Float>> nombrePrecio = null;
    
		// menus
		if(menu == 0) {
			Collection<Menu> menues = MenuADFactory.getAccessMenu().listarMenus().values();
			nombrePrecio = menues.stream()
					.map(p -> 
					new NombrePrecio(p.getNombre(), p.getPrecio()))
					.collect(Collectors.toList());
		}
		else {
			Collection<Producto> productos = ProductoADFactory.getAccessProductos().listarProductos().values();
			// comida
			if(menu == 1) {
				nombrePrecio = productos.stream()
						.filter(p -> p.getTipo().equals("Comida"))
						.map(p -> 
							new NombrePrecio(p.getNombre(), p.getPrecio()))
						.collect(Collectors.toList());
			}
			// aperitivos
			else if(menu == 2) {
				nombrePrecio = productos.stream()
						.filter(p -> p.getTipo().equals("Aperitivos"))
						.map(p -> 
							new NombrePrecio(p.getNombre(), p.getPrecio()))
						.collect(Collectors.toList());
			}
			// bebidas
			else if(menu == 3) {
				nombrePrecio = productos.stream()
						.filter(p -> p.getTipo().equals("Bebida"))
						.map(p -> 
							new NombrePrecio(p.getNombre(), p.getPrecio()))
						.collect(Collectors.toList());
			}
			// error
			else throw new GestorExcepciones(011);
		}

		Object[][] listado = new Object[nombrePrecio.size()][2];
		for(int i = 0; i < nombrePrecio.size(); i++) {
			listado[i][0] = nombrePrecio.get(i).getFst();
			listado[i][1] = nombrePrecio.get(i).getSnd();
		}
		String[] titulo = {""};

		return new JTable(listado, titulo);	
	}

	/**
	 * Actualizar pedido actual segun listas
	 *
	 * <p> Lee la listas de productos y menus y devuelve una tabla con el listado.
	 *
	 * @return	tabla con listado de pedidos
	 */
	private JTable actualizarPedido() {
		Object[][] listadoCarrito = null;
		String[] titulo = null;

		float precioProductos = (float) carritoProductos.stream()
				.mapToDouble(p -> p.getPrecio())
				.sum();
		float precioMenus = (float) carritoMenus.stream()
				.mapToDouble(p -> p.getPrecio())
				.sum();

		int listSize = carritoMenus.size() + carritoProductos.size();
		int menuSize = carritoMenus.size();
		listadoCarrito = new Object[listSize][2];

		for(int i = 0; i < listSize; i++) {
			if(i > menuSize) {
				listadoCarrito[i][0] = carritoMenus.get(i).getNombre();
				listadoCarrito[i][1] = carritoMenus.get(i).getPrecio();
			} else {
				listadoCarrito[i][0] = carritoProductos.get(i-menuSize).getNombre();
				listadoCarrito[i][1] = carritoProductos.get(i-menuSize).getPrecio();
			}
		}

		precioTotal = precioMenus + precioProductos;

		return null; //provisional
	}

	/**
	 * Create the dialog.
	 */
	public VPedido(JDialog vMC, Usuarie pUsuarie) {
		super(vMC,"Taco Bell",true);
		setUndecorated(true);
		vMenuCliente = vMC;

		setBounds(100, 100, 1185, 685);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(240, 240, 240));
		panelMenu.setBounds(5, 32, 1174, 98);
		panelMenu.setAlignmentY(Component.TOP_ALIGNMENT);
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		contentPanel.add(panelMenu);

		JPanel panelMenuFondo = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelMenuFondo.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(180);
		panelMenuFondo.setBackground(colorMoradoClaro);
		panelMenu.add(panelMenuFondo);

		JPanel panelVolver = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panelVolver.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(0);
		panelVolver.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelVolver);

		btnAtras = new JButton("");
		btnAtras.setBorder(null);
		btnAtras.setBackground(colorMoradoClaro);
		btnAtras.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_atras.png")));
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

		JPanel panelMenus = new JPanel();
		panelMenus.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelMenus);
		panelMenus.setLayout(new BoxLayout(panelMenus, BoxLayout.Y_AXIS));

		JButton btnMenus = new JButton("");
		btnMenus.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_menus.png")));

		btnMenus.setForeground(Color.WHITE);
		btnMenus.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnMenus.setBorder(null);
		btnMenus.setBackground(colorMoradoClaro);
		btnMenus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelMenus.add(btnMenus);
		btnMenus.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnMenus.setBackground(new Color(98, 18, 141));
			}
			public void mouseExited(MouseEvent evt) {
				btnMenus.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelComida = new JPanel();
		panelComida.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelComida);
		panelComida.setLayout(new BoxLayout(panelComida, BoxLayout.Y_AXIS));

		JButton btnComida = new JButton("");
		btnComida.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_comida.png")));
		btnComida.setForeground(Color.WHITE);
		btnComida.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnComida.setBorder(null);
		btnComida.setBackground(colorMoradoClaro);
		btnComida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelComida.add(btnComida);
		btnComida.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnComida.setBackground(new Color(98, 18, 141));
			}
			public void mouseExited(MouseEvent evt) {
				btnComida.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelAperitivos = new JPanel();
		panelAperitivos.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelAperitivos);
		panelAperitivos.setLayout(new BoxLayout(panelAperitivos, BoxLayout.Y_AXIS));

		JButton btnAperitivos = new JButton("");
		btnAperitivos.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_aperitivos.png")));

		btnAperitivos.setForeground(Color.WHITE);
		btnAperitivos.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnAperitivos.setBorder(null);
		btnAperitivos.setBackground(colorMoradoClaro);
		btnAperitivos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAperitivos.add(btnAperitivos);
		btnAperitivos.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnAperitivos.setBackground(new Color(98, 18, 141));
			}
			public void mouseExited(MouseEvent evt) {
				btnAperitivos.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelBebida = new JPanel();
		panelBebida.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelBebida);
		panelBebida.setLayout(new BoxLayout(panelBebida, BoxLayout.Y_AXIS));

		JButton btnBebida = new JButton("");
		btnBebida.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_bebidas.png")));
		btnBebida.setForeground(Color.WHITE);
		btnBebida.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnBebida.setBorder(null);
		btnBebida.setBackground(colorMoradoClaro);
		btnBebida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBebida.add(btnBebida);
		btnBebida.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnBebida.setBackground(new Color(98, 18, 141));
			}
			public void mouseExited(MouseEvent evt) {
				btnBebida.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelProductos = new JPanel();
		panelProductos.setBounds(347, 141, 832, 514);
		contentPanel.add(panelProductos);
		panelProductos.setLayout(new GridLayout(0, 1, 0, 0));

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(335, 141, 2, 514);
		contentPanel.add(separator);

		JPanel panelPedido = new JPanel();
		panelPedido.setLayout(null);
		panelPedido.setBounds(5, 118, 320, 537);
		contentPanel.add(panelPedido);

		JButton btnPedir = new JButton("TOTAL: " + precioTotal + " \u20AC");
		btnPedir.setForeground(Color.WHITE);
		btnPedir.setBackground(colorVerdeClaro);
		btnPedir.setBorder(null);
		btnPedir.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 32));
		btnPedir.setBounds(10, 466, 300, 60);
		btnPedir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelPedido.add(btnPedir);
		btnPedir.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnPedir.setBackground(new Color(10, 160, 112));
			}
			public void mouseExited(MouseEvent evt) {
				btnPedir.setBackground(colorVerdeClaro);
			}
		});

		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBounds(10, 26, 300, 340);
		panelPedido.add(panelSeleccion);
		panelSeleccion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelDescuento = new JPanel();
		panelDescuento.setBounds(10, 377, 300, 78);
		panelPedido.add(panelDescuento);
		panelDescuento.setLayout(null);

		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescuento.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		lblDescuento.setForeground(colorAzulClaro);
		lblDescuento.setBounds(10, 11, 146, 56);
		panelDescuento.add(lblDescuento);

		JLabel lblDescuentoCantidad = new JLabel("0");
		lblDescuentoCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescuentoCantidad.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		lblDescuentoCantidad.setForeground(colorAzulClaro);
		lblDescuentoCantidad.setBounds(197, 11, 93, 56);
		panelDescuento.add(lblDescuentoCantidad);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panel_4.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		panel_4.setBounds(1017, 0, 162, 32);
		contentPanel.add(panel_4);

		btnX = new JButton("");
		btnX.setSelectedIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_x_active.png")));
		btnX.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_x_inactive.png")));
		btnX.setBackground(Color.WHITE);
		btnX.setBorder(null);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_4.add(btnX);
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
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			vMenuCliente.setVisible(true);
		}
		if (e.getSource().equals(btnX)) {
			this.dispose();
			vMenuCliente.dispose();
		}
	}
}
