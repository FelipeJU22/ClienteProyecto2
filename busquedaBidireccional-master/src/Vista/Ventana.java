package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField txtPalabra;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JTextPane textPane;
	private JTextArea textArea;
	private JTextPane textPane_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Ventana frame = new Ventana();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Busqueda Bidireccional de Palabra");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 774, 49);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 774, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPalabra = new JLabel("Palabra:");
		lblPalabra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPalabra.setBounds(10, 11, 60, 14);
		panel.add(lblPalabra);
		
		txtPalabra = new JTextField();
		txtPalabra.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPalabra.setBounds(74, 8, 100, 20);
		panel.add(txtPalabra);
		txtPalabra.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(203, 8, 89, 23);
		panel.add(btnBuscar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(675, 8, 89, 23);
		panel.add(btnLimpiar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 188, 774, 161);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEscribaUnTexto = new JLabel("Escriba un texto para buscar:");
		lblEscribaUnTexto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEscribaUnTexto.setBounds(10, 11, 381, 14);
		panel_1.add(lblEscribaUnTexto);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 36, 754, 114);
		panel_1.add(textArea);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setBounds(41, 360, 700, 200);
		
		panel_2.setLayout(null);
		
		JLabel lblArbolRaizmeta = new JLabel("Arbol Raiz-Meta");
		lblArbolRaizmeta.setForeground(Color.RED);
		lblArbolRaizmeta.setBounds(99, 11, 104, 18);
		panel_2.add(lblArbolRaizmeta);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		panel_2.add(textPane);
		textPane.setEnabled(false);
		textPane.setContentType("text/html");
		textPane.setBounds(10, 40, 264, 149);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setEnabled(false);
		textPane_1.setContentType("text/html");
		textPane_1.setBounds(426, 40, 264, 149);
		panel_2.add(textPane_1);
		
		JLabel lblArbolMetaraiz = new JLabel("Arbol Meta-Raiz");
		lblArbolMetaraiz.setForeground(Color.BLUE);
		lblArbolMetaraiz.setBounds(518, 11, 104, 18);
		panel_2.add(lblArbolMetaraiz);
	}

	public JTextField getTxtPalabra() {
		return txtPalabra;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JTextPane getTextPane_1() {
		return textPane_1;
	}
}
