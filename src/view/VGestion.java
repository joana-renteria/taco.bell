package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import controller.factorias.PedidoADFactory;
import controller.factorias.UsuarieADFactory;
import datos.Pedido;
import users.Cliente;
import users.Trabajador;
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
import javax.swing.JTable;

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
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panelProductos;
	private String titulosCliente[] = {"Código","Nombre","Apellido","Correo"};
	private String titulosPedido[] = {"Código Pedido","Fecha","Código cliente","Código repartidor","Código establecimiento","Menu"};
	private String titulosTrabajadores[] = {"Código Trabajador","Nombre","Apellido","Código Establecimiento","Horario","Sueldo","Clase"};
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
		btnClientes.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_users.png")));
		btnClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnClientes.setForeground(Color.WHITE);
		btnClientes.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnClientes.setBorder(null);
		btnClientes.setBackground(new Color(118, 38, 161));
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
			return new JTable(tablaPedidos(),titulosPedido);
			case 2:
			return new JTable(tablaTrabajadores(),titulosTrabajadores);
			default:
			return new JTable(tablaClientes(),titulosCliente);
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
		tableHeader.setFont(new Font("Iosevka ", Font.BOLD, 15));
		tableHeader.setBorder(null);
		tableHeader.setEnabled(false);
	}

	public String[][] tablaClientes() {
		List<Usuarie> listaCliente;
		Collection<Usuarie> listaUser = UsuarieADFactory.getAccessUsuaries().listarUsuaries().values();
		listaCliente = listaUser.stream()
				.filter(p -> p.getClass().toString().contains("Cliente"))
				.collect(Collectors.toList());
		String matrizTabla[][] = new String [listaCliente.size()][4];
		if (listaCliente.size() > 0) {
			for (int i = 0; i < listaCliente.size(); i++) {
				matrizTabla[i][0] = listaCliente.get(i).getCodUsr();
				matrizTabla[i][1] = listaCliente.get(i).getNombre();
				matrizTabla[i][2] = listaCliente.get(i).getApellido();
				matrizTabla[i][3] = ((Cliente)listaCliente.get(i)).getCorreoLogin();
			}
		}
		return matrizTabla;
	}
	public String[][] tablaPedidos() {
		List<Pedido> listaPedidos = PedidoADFactory.getAccessPedido().listarPedidos().values().stream().collect(Collectors.toList());
		String matrizTabla[][] = new String [listaPedidos.size()][6];
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
	}
	public String[][] tablaTrabajadores() {
		List<Usuarie> listaTrabajadores;
		Collection<Usuarie> listaUser = UsuarieADFactory.getAccessUsuaries().listarUsuaries().values();
		listaTrabajadores = listaUser.stream()
				.filter(p -> p instanceof Trabajador)
				.collect(Collectors.toList());
		String matrizTabla[][] = new String [listaTrabajadores.size()][8];
		
		if (listaTrabajadores.size() > 0) {
			
			for (int i = 0; i < listaTrabajadores.size(); i++) {
				
				matrizTabla[i][0] = listaTrabajadores.get(i).getCodUsr();
				matrizTabla[i][1] = listaTrabajadores.get(i).getNombre();
				matrizTabla[i][2] = listaTrabajadores.get(i).getApellido();
				matrizTabla[i][3] = ((Trabajador)listaTrabajadores.get(i)).getCodEst();
				matrizTabla[i][4] = ((Trabajador)listaTrabajadores.get(i)).getHorario();
				matrizTabla[i][6] = String.valueOf(((Trabajador)listaTrabajadores.get(i)).getSueldo());
				matrizTabla[i][7] = ((Trabajador)listaTrabajadores.get(i)).getClass().toString().contains("Auxiliar")?"Auxiliar":"Repartidor";	
			}
		}
		return matrizTabla;
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
		}
		if (e.getSource().equals(btnPedidos)) {
			table = actualizarTabla(1);
			refreshTabla();
		}
		if (e.getSource().equals(btnPersonal)) {
			table = actualizarTabla(2);
			refreshTabla();
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