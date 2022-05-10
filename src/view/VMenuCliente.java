package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.Usuarie;

import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JButton;
import java.awt.Cursor;

public class VMenuCliente extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = -8955757198366800331L;
	private static Point point = new Point(0, 0);

	// Definir colores
	private static Color colorMoradoOscuro = new Color(73, 44, 89);
	private static Color colorAzulOscuro = new Color(98, 14, 184);
	private static Color colorVerdeClaro = new Color(64, 180, 89);
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			cargarTipografia();
			VMenuCliente dialog = new VMenuCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public static void cargarTipografia() {
		Fuentes fe = new Fuentes();
		fe.cargarTipografia();
	}

	/**
	 * Create the dialog.
	 */
	public VMenuCliente(Usuarie pCliente) {
		cargarTipografia();
		this.setUndecorated(true);
		setBounds(100, 100, 1185, 686);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(5, 30, 578, 651);
		panelLeft.setBackground(new Color(118, 38, 161));
		contentPanel.add(panelLeft);
		panelLeft.setLayout(null);
		
		JPanel panelPedidoNuevo = new JPanel();
		panelPedidoNuevo.setBackground(new Color(118, 38, 161));
		panelPedidoNuevo.setBounds(0, 0, 577, 651);
		panelLeft.add(panelPedidoNuevo);
		panelPedidoNuevo.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 220));
		
		JPanel panelContenido1 = new JPanel();
		panelContenido1.setBackground(new Color(118, 38, 161));
		panelPedidoNuevo.add(panelContenido1);
		panelContenido1.setLayout(new BoxLayout(panelContenido1, BoxLayout.Y_AXIS));
		
		JPanel panelIcono1 = new JPanel();
		panelIcono1.setBackground(new Color(118, 38, 161));
		panelContenido1.add(panelIcono1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_nuevo_pedido.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panelIcono1.add(lblNewLabel_4);
		
		JLabel lblPedidoNuevo = new JLabel("Realizar nuevo pedido");
		lblPedidoNuevo.setForeground(Color.WHITE);
		lblPedidoNuevo.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		lblPedidoNuevo.setAlignmentY(1.0f);
		lblPedidoNuevo.setAlignmentX(0.5f);
		panelContenido1.add(lblPedidoNuevo);
		
		JButton btnNuevoPedido = new JButton("Realizar Pedido Nuevo");
		btnNuevoPedido.setOpaque(false);
		btnNuevoPedido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevoPedido.setBorder(null);
		btnNuevoPedido.setBounds(0, 0, 578, 651);
		panelLeft.add(btnNuevoPedido);
		
		JPanel panelRight = new JPanel();
		panelRight.setBounds(592, 30, 587, 651);
		panelRight.setBackground(Color.WHITE);
		contentPanel.add(panelRight);
		panelRight.setLayout(null);
		
		JPanel panelConsultaPedidos = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelConsultaPedidos.getLayout();
		flowLayout.setVgap(70);
		panelConsultaPedidos.setBackground(colorMoradoOscuro);
		panelConsultaPedidos.setBounds(0, 0, 587, 313);
		panelRight.add(panelConsultaPedidos);
		
		JPanel panelContenido2 = new JPanel();
		panelContenido2.setBackground(colorMoradoOscuro);
		panelConsultaPedidos.add(panelContenido2);
		panelContenido2.setLayout(new BoxLayout(panelContenido2, BoxLayout.Y_AXIS));
		
		JPanel panelIcono2 = new JPanel();
		panelIcono2.setBackground(colorMoradoOscuro);
		panelContenido2.add(panelIcono2);
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_consulta_pedidos.png")));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelIcono2.add(lblNewLabel_4_1);
		
		JLabel lblConsultaPedidos = new JLabel("Consultar pedido");
		lblConsultaPedidos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblConsultaPedidos.setForeground(Color.WHITE);
		lblConsultaPedidos.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 28));
		panelContenido2.add(lblConsultaPedidos);
		
		JPanel panelDatos = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelDatos.getLayout();
		flowLayout_1.setVgap(110);
		panelDatos.setBackground(colorAzulOscuro);
		panelDatos.setBounds(0, 323, 311, 328);
		panelRight.add(panelDatos);
		
		JPanel panelContenido3 = new JPanel();
		panelContenido3.setBackground(colorAzulOscuro);
		panelDatos.add(panelContenido3);
		panelContenido3.setLayout(new BoxLayout(panelContenido3, BoxLayout.Y_AXIS));
		
		JPanel panelIcono3 = new JPanel();
		panelIcono3.setBackground(colorAzulOscuro);
		panelContenido3.add(panelIcono3);
		
		JLabel lblNewLabel_4_2 = new JLabel("");
		lblNewLabel_4_2.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_datos.png")));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		panelIcono3.add(lblNewLabel_4_2);
		
		JLabel lblDatos = new JLabel("Cambiar y revisar tus datos");
		lblDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 18));
		panelContenido3.add(lblDatos);
		
		JPanel panelSalir = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelSalir.getLayout();
		flowLayout_2.setVgap(100);
		panelSalir.setBackground(colorVerdeClaro);
		panelSalir.setBounds(321, 324, 266, 327);
		panelRight.add(panelSalir);
		
		JPanel panelContenido4 = new JPanel();
		panelContenido4.setBackground(colorVerdeClaro);
		panelSalir.add(panelContenido4);
		panelContenido4.setLayout(new BoxLayout(panelContenido4, BoxLayout.Y_AXIS));
		
		JPanel panelIcono4 = new JPanel();
		panelIcono4.setBackground(colorVerdeClaro);
		panelContenido4.add(panelIcono4);
		
		JLabel lblNewLabel_4_3 = new JLabel("");
		lblNewLabel_4_3.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_salir.png")));
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		panelIcono4.add(lblNewLabel_4_3);
		
		JLabel lblSalir = new JLabel("Salir");
		lblSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 32));
		panelContenido4.add(lblSalir);
		
		JButton btnConsulta = new JButton("Consultar Pedidos");
		btnConsulta.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		btnConsulta.setBounds(0, 0, 587, 313);
		panelRight.add(btnConsulta);
		
		JButton btnDatos = new JButton("Cambiar y revisar tus datos");
		btnDatos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDatos.setOpaque(false);
		btnDatos.setBounds(0, 324, 311, 327);
		panelRight.add(btnDatos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalir.setOpaque(false);
		btnSalir.setBounds(321, 324, 266, 327);
		panelRight.add(btnSalir);
		
		JPanel panelBotonesSuperiores = new JPanel();
		FlowLayout fl_panelBotonesSuperiores = (FlowLayout) panelBotonesSuperiores.getLayout();
		fl_panelBotonesSuperiores.setHgap(20);
		fl_panelBotonesSuperiores.setAlignment(FlowLayout.RIGHT);
		panelBotonesSuperiores.setBounds(1061, 0, 124, 29);
		contentPanel.add(panelBotonesSuperiores);
		
		JButton btnMinimizar = new JButton("");
		btnMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMinimizar.setBorder(null);
		btnMinimizar.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_minimizar_inactive.png")));
		panelBotonesSuperiores.add(btnMinimizar);
		
		JButton btnX = new JButton("");
		btnX.setBorder(null);
		btnX.setIcon(new ImageIcon(VMenuCliente.class.getResource("/resources/icon_x_inactive.png")));
		panelBotonesSuperiores.add(btnX);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		panelSuperior.setBounds(0, 0, 1061, 29);
		contentPanel.add(panelSuperior);

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
		// TODO Auto-generated method stub
		
	}
}
