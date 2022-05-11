package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;

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
	private static JButton btnNewButton;
	private static JButton btnEliminar; 
	private static VLogin vLogin;
	
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
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		panel_1.setBackground(colorMoradoClaro);
		panelClientes.add(panel_1);
		
		JButton btnClientes = new JButton("");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setBorder(null);
		btnClientes.setBackground(colorMoradoClaro);
		btnClientes.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_users.png")));
		panel_1.add(btnClientes);
		
		JButton btnNewButton_2 = new JButton("Clientes");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnNewButton_2.setBackground(colorMoradoClaro);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setForeground(Color.WHITE);
		panelClientes.add(btnNewButton_2);
		
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
		
		JButton btnPedidos = new JButton("");
		btnPedidos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPedidos.setBorder(null);
		btnPedidos.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_pedidos.png")));
		btnPedidos.setBackground(colorMoradoClaro);
		panel_2.add(btnPedidos);
		
		JButton btnNewButton_3 = new JButton("Pedidos");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnNewButton_3.setBackground(colorMoradoClaro);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setForeground(Color.WHITE);
		panelPedidos.add(btnNewButton_3);
		
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
		
		JButton btnPersonal = new JButton("");
		btnPersonal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPersonal.setBorder(null);
		btnPersonal.setBackground(colorMoradoClaro);
		btnPersonal.setIcon(new ImageIcon(VGestion.class.getResource("/resources/icon_personal.png")));
		panel_3.add(btnPersonal);
		
		JButton btnNewButton_4 = new JButton("Personal");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 24));
		btnNewButton_4.setBackground(colorMoradoClaro);
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setForeground(Color.WHITE);
		panelPersonal.add(btnNewButton_4);
		
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
		
		btnNewButton = new JButton("NUEVO");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(0, 0, 179, 78);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 28));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(colorAzulClaro);
		panel.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelBotones.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("ELIMINAR");
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(0, 0, 189, 78);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Iosevka Aile Heavy", Font.BOLD, 28));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(colorRojoClaro);
		panel_4.add(btnEliminar);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			vLogin.setVisible(true);
		}
		if (e.getSource().equals(btnEliminar)) {
			// TODO
		}
		if (e.getSource().equals(btnNewButton)) {
			// TODO
		}
		if (e.getSource().equals(btnPedir)) {
			// TODO
		}
	}
}
