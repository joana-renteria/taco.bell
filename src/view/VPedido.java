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
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import datos.Producto;
import exceptions.GestorExcepciones;
import datos.Menu;

public class VPedido extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3378271115951639896L;

	private final JPanel contentPanel = new JPanel();
	
	private JTable tablaProductos;
	private int menu = 0;
	
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
			VPedido dialog = new VPedido();
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
			List<Menu> menues = MenuADFactory.getAccessMenu().listarMenus();
			nombrePrecio = menues.stream()
					.map(p -> 
						new NombrePrecio(p.getNombre(), p.getPrecio()))
				.collect(Collectors.toList());
		}
		// comida
		else if(menu == 1) {
			List<Producto> comidas = ProductoADFactory.getAccessProductos().listarProducto("Comida");
			nombrePrecio = comidas.stream()
					.map(p -> 
						new NombrePrecio(p.getNombre(), p.getPrecio()))
					.collect(Collectors.toList());
		}
		// aperitivos
		else if(menu == 2) {
			List<Producto> aperitivos = ProductoADFactory.getAccessProductos().listarProducto("Aperitivo");
			nombrePrecio = aperitivos.stream()
					.map(p -> 
						new NombrePrecio(p.getNombre(), p.getPrecio()))
					.collect(Collectors.toList());
		}
		// bebidas
		else if(menu == 3) {
			List<Producto> bebidas = ProductoADFactory.getAccessProductos().listarProducto("Bebida");
			nombrePrecio = bebidas.stream()
					.map(p -> 
						new NombrePrecio(p.getNombre(), p.getPrecio()))
					.collect(Collectors.toList());
		}
		// error
		else throw new GestorExcepciones(011);

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
	public VPedido() {
		setBounds(100, 100, 1200, 700);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(240, 240, 240));
		panelMenu.setBounds(5, 5, 1174, 110);
		panelMenu.setAlignmentY(Component.TOP_ALIGNMENT);
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		contentPanel.add(panelMenu);
		
		JPanel panelMenuFondo = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelMenuFondo.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(140);
		panelMenuFondo.setBackground(colorMoradoClaro);
		panelMenu.add(panelMenuFondo);
		
		JPanel panelVolver = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panelVolver.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(0);
		panelVolver.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelVolver);
		
		JButton btnAtras = new JButton("");
		btnAtras.setBorder(null);
		btnAtras.setBackground(colorMoradoClaro);
		btnAtras.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_atras.png")));
		panelVolver.add(btnAtras);

		JPanel panelMenus = new JPanel();
		panelMenus.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelMenus);
		panelMenus.setLayout(new BoxLayout(panelMenus, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		panel.setBackground(colorMoradoClaro);
		panelMenus.add(panel);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_menus.png")));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Menus");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelMenus.add(lblNewLabel);
		
		JPanel panelComida = new JPanel();
		panelComida.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelComida);
		panelComida.setLayout(new BoxLayout(panelComida, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		panel_1.setBackground(colorMoradoClaro);
		panelComida.add(panel_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_comida.png")));
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Comida");
		lblNewLabel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelComida.add(lblNewLabel_1);
		
		JPanel panelAperitivos = new JPanel();
		panelAperitivos.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelAperitivos);
		panelAperitivos.setLayout(new BoxLayout(panelAperitivos, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(0);
		panel_2.setBackground(colorMoradoClaro);
		panelAperitivos.add(panel_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_aperitivos.png")));
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_2 = new JLabel("Aperitivos");
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelAperitivos.add(lblNewLabel_2);
		
		JPanel panelBebida = new JPanel();
		panelBebida.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelBebida);
		panelBebida.setLayout(new BoxLayout(panelBebida, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_3.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setHgap(0);
		panel_3.setBackground(colorMoradoClaro);
		panelBebida.add(panel_3);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_bebidas.png")));
		panel_3.add(lblNewLabel_7);
		
		JLabel lblNewLabel_3 = new JLabel("Bebida");
		lblNewLabel_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelBebida.add(lblNewLabel_3);
		
		JPanel panelProductos = new JPanel();
		panelProductos.setBounds(347, 118, 832, 537);
		contentPanel.add(panelProductos);
		panelProductos.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(335, 118, 2, 537);
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
		panelPedido.add(btnPedir);
		
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setBounds(10, 11, 300, 355);
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
	}
}
