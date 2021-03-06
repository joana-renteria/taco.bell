package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import controller.factorias.EstablecimientoADFactory;
import controller.factorias.PedidoADFactory;
import controller.factorias.UsuarieADFactory;
import datos.Pedido;
import exceptions.GestorExcepciones;
import resources.fuentes.Fuentes;
import users.Usuarie;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Point;
import java.awt.Cursor;

public class VConsultaPedido extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995247513951729787L;
	private final JPanel contentPanel = new JPanel();
	private Point point = new Point(0, 0);

	// Definir colores
	private static Color colorVerdeClaro = new Color(30, 180, 132);
	private Color colorMoradoClaro = new Color(118, 38, 161);

	private String titulosPedido[] = { "Pedido", "Fecha", "Repartidor",
			"Establecimiento", "Menu" };
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAtras;
	private JButton btnPedidosCurso;
	private JButton btnPedidosAnteriores;
	private JButton btnX;
	private JDialog vMenuCliente;
	private Usuarie pUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			cargarTipografia();
			VMenuCliente vMenuCliente = null;
			VConsultaPedido dialog = new VConsultaPedido(vMenuCliente, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cargarTipografia() {
		Fuentes fe = new Fuentes();
		fe.cargarTipografia();
	}

	/**
	 * Create the dialog.
	 */
	public VConsultaPedido(JDialog vMC, Usuarie pUsuarie) {
		super(vMC, "Taco Bell", true);
		vMenuCliente = vMC;
		pUser = pUsuarie;

		setBounds(100, 100, 1200, 700);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		this.setUndecorated(true);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(240, 240, 240));
		panelMenu.setBounds(5, 30, 1174, 109);
		panelMenu.setAlignmentY(Component.TOP_ALIGNMENT);
		FlowLayout flowLayout = (FlowLayout) panelMenu.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		contentPanel.add(panelMenu);

		JPanel panelMenuFondo = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelMenuFondo.getLayout();
		flowLayout_1.setVgap(28);
		flowLayout_1.setHgap(220);
		panelMenuFondo.setBackground(colorMoradoClaro);
		panelMenu.add(panelMenuFondo);

		btnAtras = new JButton("");
		panelMenuFondo.add(btnAtras);
		btnAtras.setBorder(null);
		btnAtras.setBackground(colorMoradoClaro);
		btnAtras.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_atras.png")));
		btnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAtras.addActionListener(this);
		btnAtras.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnAtras.setBackground(new Color(98, 18, 141));
			}

			public void mouseExited(MouseEvent evt) {
				btnAtras.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelVolver = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panelVolver.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(0);
		panelVolver.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelVolver);

		JLabel lblNewLabel = new JLabel("Consulta de pedidos");
		panelMenuFondo.add(lblNewLabel);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 42));

		JPanel panelMenus = new JPanel();
		panelMenus.setBackground(colorMoradoClaro);
		panelMenuFondo.add(panelMenus);
		panelMenus.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelContenido = new JPanel();
		panelContenido.setBounds(5, 139, 1169, 511);
		contentPanel.add(panelContenido);
		panelContenido.setLayout(null);

		btnPedidosCurso = new JButton("<html>Pedidos<br>en curso</html>");
		btnPedidosCurso.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 28));
		btnPedidosCurso.setBorder(null);
		btnPedidosCurso.setForeground(Color.WHITE);
		btnPedidosCurso.setBackground(colorMoradoClaro);
		btnPedidosCurso.setBounds(65, 131, 178, 118);
		btnPedidosCurso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelContenido.add(btnPedidosCurso);
		btnPedidosCurso.addActionListener(this);
		btnPedidosCurso.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnPedidosCurso.setBackground(new Color(98, 18, 141));
			}

			public void mouseExited(MouseEvent evt) {
				btnPedidosCurso.setBackground(colorMoradoClaro);
			}
		});

		btnPedidosAnteriores = new JButton("<html>Pedidos<br>anteriores</html>");
		btnPedidosAnteriores.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 28));
		btnPedidosAnteriores.setBorder(null);
		btnPedidosAnteriores.setForeground(Color.WHITE);
		btnPedidosAnteriores.setBackground(colorMoradoClaro);
		btnPedidosAnteriores.setBounds(65, 275, 178, 118);
		btnPedidosAnteriores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelContenido.add(btnPedidosAnteriores);
		btnPedidosAnteriores.addActionListener(this);
		btnPedidosAnteriores.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btnPedidosAnteriores.setBackground(new Color(98, 18, 141));
			}

			public void mouseExited(MouseEvent evt) {
				btnPedidosAnteriores.setBackground(colorMoradoClaro);
			}
		});

		JPanel panelListadoPedidos = new JPanel();
		// panelListadoPedidos.setSize(1000,1000);
		panelListadoPedidos.setBounds(26, 26, 1500, 1000);
		panelContenido.add(panelListadoPedidos);
		panelListadoPedidos.setBorder(null);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(919, 0, 260, 45);
		contentPanel.add(panel);

		btnX = new JButton("");
		btnX.setBackground(Color.WHITE);
		btnX.setBorder(null);
		btnX.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_x_inactive.png")));
		btnX.setFocusable(false);
		btnX.addActionListener(this);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(btnX);
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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 209, 900, 400);
		scrollPane.setBorder(null);
		panelListadoPedidos.add(scrollPane);

		table = actualizarTabla(1);
		refreshTabla();
	}

	public String[][] tablaPedidos(int x) {
		try {
			List<Pedido> listaPedidos = PedidoADFactory.getAccessPedido().listarPedidos().values().stream()
					.filter(p -> p.getCodCle().equals(pUser.getCodUsr()))
					.collect(Collectors.toList());
			String matrizTabla[][] = new String[listaPedidos.size()][5];
			if (listaPedidos.size() > 0) {
				if (x == 1) {
					for (int i = 0; i < listaPedidos.size(); i++) {

						matrizTabla[i][0] = listaPedidos.get(i).getCodPed();
						matrizTabla[i][1] = listaPedidos.get(i).getFechaPed().toString();
						matrizTabla[i][2] = UsuarieADFactory.getAccessUsuaries()
								.buscarUsuarie(listaPedidos.get(i).getCodRep()).getNombre();
						matrizTabla[i][3] = EstablecimientoADFactory.getAccessEstablecimiento()
								.buscarEstablecimientoPorCodigo(listaPedidos.get(i).getCodEst()).getNombre();
						matrizTabla[i][4] = listaPedidos.get(i).getMenu().getNombre();
					}
				} else {
					matrizTabla[0][0] = listaPedidos.get(listaPedidos.size() - 1).getCodPed();
					matrizTabla[0][1] = listaPedidos.get(listaPedidos.size() - 1).getFechaPed().toString();
					matrizTabla[0][2] = UsuarieADFactory.getAccessUsuaries()
							.buscarUsuarie(listaPedidos.get(listaPedidos.size() - 1).getCodRep()).getNombre();
					matrizTabla[0][3] = EstablecimientoADFactory.getAccessEstablecimiento()
							.buscarEstablecimientoPorCodigo(listaPedidos.get(listaPedidos.size() - 1).getCodEst())
							.getNombre();
					matrizTabla[0][4] = listaPedidos.get(listaPedidos.size() - 1).getMenu().getNombre();
				}
				return matrizTabla;
			}
		} catch (GestorExcepciones e) {
			JOptionPane.showMessageDialog(this,
							e.getMsg(),
							"Warning",
							JOptionPane.WARNING_MESSAGE);	
		}
		return null;
	}

	private JTable actualizarTabla(int x) {
		return new JTable(tablaPedidos(x), titulosPedido);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			dispose();
			vMenuCliente.setVisible(true);
		}
		if (e.getSource().equals(btnX)) {
			this.dispose();
			vMenuCliente.dispose();
		}
		if (e.getSource().equals(btnPedidosAnteriores)) {
			table = actualizarTabla(1);
			refreshTabla();
		}
		if (e.getSource().equals(btnPedidosCurso)) {
			table = actualizarTabla(0);
			refreshTabla();
		}
	}
}
