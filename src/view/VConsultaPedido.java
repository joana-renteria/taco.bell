package view;

import java.awt.Color;
import java.awt.FlowLayout;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VConsultaPedido extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995247513951729787L;
	private final JPanel contentPanel = new JPanel();
	
	//Definir colores
	private static Color colorMoradoClaro = new Color(118, 38, 161);

	private JTable table;
	private JTable table_1;
	private JButton btnAtras;
	private VMenuCliente vMenuCliente;

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
	public VConsultaPedido(VMenuCliente vMC, Usuarie pUsuarie) {
		super(vMC,"Taco Bell", true);
		vMenuCliente = vMC;

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
		flowLayout_1.setVgap(28);
		flowLayout_1.setHgap(220);
		panelMenuFondo.setBackground(colorMoradoClaro);
		panelMenu.add(panelMenuFondo);
		
		btnAtras = new JButton("");
		panelMenuFondo.add(btnAtras);
		btnAtras.setBorder(null);
		btnAtras.setBackground(colorMoradoClaro);
		btnAtras.setIcon(new ImageIcon(VPedido.class.getResource("/resources/icon_atras.png")));
		btnAtras.addActionListener(this);
		
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
		panelContenido.setBounds(5, 126, 1169, 524);
		contentPanel.add(panelContenido);
		panelContenido.setLayout(null);
		
		JButton btnPedidosCurso = new JButton("<html>Pedidos<br>en curso</html>");
		btnPedidosCurso.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 28));
		btnPedidosCurso.setBorder(null);
		btnPedidosCurso.setForeground(Color.WHITE);
		btnPedidosCurso.setBackground(colorMoradoClaro);
		btnPedidosCurso.setBounds(65, 131, 178, 118);
		panelContenido.add(btnPedidosCurso);
		
		JButton btnPedidosAnteriores = new JButton("<html>Pedidos<br>anteriores</html>");
		btnPedidosAnteriores.setFont(new Font("Iosevka Aile Heavy", Font.PLAIN, 28));
		btnPedidosAnteriores.setBorder(null);
		btnPedidosAnteriores.setForeground(Color.WHITE);
		btnPedidosAnteriores.setBackground(colorMoradoClaro);
		btnPedidosAnteriores.setBounds(65, 275, 178, 118);
		panelContenido.add(btnPedidosAnteriores);
		
		JPanel panelListadoPedidos = new JPanel();
		panelListadoPedidos.setBounds(338, 26, 795, 227);
		panelContenido.add(panelListadoPedidos);
		
		table = new JTable();
		panelListadoPedidos.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		panelListadoPedidos.add(scrollPane);
		
		JPanel panelInfoPedido = new JPanel();
		panelInfoPedido.setBounds(338, 275, 795, 227);
		panelContenido.add(panelInfoPedido);
		
		table_1 = new JTable();
		panelInfoPedido.add(table_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelInfoPedido.add(scrollPane_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
			vMenuCliente.setVisible(true);
		}
		
	}
}
