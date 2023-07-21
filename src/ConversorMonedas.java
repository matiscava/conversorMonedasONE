

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConversorMonedas extends JFrame{

	private JButton btnConvertir;
	private JComboBox<Moneda> comboBox;
	private JComboBox<Moneda> comboBoxDos;
	private JLabel label;
	private JTextField txtConvertir;	
	
	public final double DOLAR = 268.83;
	public final double EURO = 299.16;
	public final double LIBRA = 345.25;
	public final double SOL = 74.99;
	public final double YUAN = 37.41;
	
	public double valorInput = 0.00;
	private JButton btnSalir;
	
	public enum	Moneda {
		PESOS,
		DOLARES,
		EUROS,
		LIBRAS,
		SOLES,
		YUANES
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorMonedas window = new ConversorMonedas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */	
	public ConversorMonedas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Conversor de Monedas");
		setBounds(100, 100, 447, 194);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtConvertir = new JTextField();
		txtConvertir.setBounds(10, 11, 165, 22);
		getContentPane().add(txtConvertir);
		txtConvertir.setColumns(10);
		txtConvertir.setText("");
		
		comboBox = new JComboBox<Moneda>();
		comboBox.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		comboBox.setBounds(40, 44, 176, 22);
		getContentPane().add(comboBox);
		
		comboBoxDos = new JComboBox<Moneda>();
		comboBoxDos.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		comboBoxDos.setBounds(250, 44, 174, 22);
		getContentPane().add(comboBoxDos);
		
		label = new JLabel("---Realice su conversión---");
		label.setBounds(185, 11, 239, 22);
		getContentPane().add(label);
		
		
		
		btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConvertirMoneda();
			}
		});
		btnConvertir.setBounds(10, 88, 414, 23);
		getContentPane().add(btnConvertir);
		
		JButton btnMenu = new JButton("Menú");
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMenu.setBounds(10, 121, 206, 23);
		getContentPane().add(btnMenu);
		
		btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(226, 122, 198, 23);
		getContentPane().add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("De:");
		lblNewLabel.setBounds(20, 48, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A:");
		lblNewLabel_1.setBounds(226, 48, 46, 14);
		getContentPane().add(lblNewLabel_1);
	
	}
	
	public void ConvertirMoneda() {
		double monedaBase = getMoneda(comboBox.getSelectedIndex());
		double monedaCambio = getMoneda(comboBoxDos.getSelectedIndex());
		RealizarConversion(monedaBase, monedaCambio);
	}
	
	public double getMoneda(int index) {
		switch(index) {
		 case 0:
			 return 1;
		 case 1:
			 return DOLAR;
		 case 2:
			 return EURO;
		 case 3:
			 return LIBRA;
		 case 4:
			 return SOL;
		 case 5:
			 return YUAN;			 
		 default:
			 throw new IllegalArgumentException("Valor inexistente: "+index);
		}
	}
	
	public void RealizarConversion(double base, double cambio) {
		if(	txtConvertir.getText().isEmpty() ) {
			label.setText("Debe ingresar un valor");
			label.setForeground(Color.RED);
		} else if(!esNumero(txtConvertir.getText())) {
			label.setText("Debe ingresar un valor númerico");
			label.setForeground(Color.RED);
		}else {			
			double plata = Double.parseDouble( txtConvertir.getText() );
			double res = (plata * base ) / cambio;
			String moneda = "";
			if(cambio == 1) {
				moneda = "pesos";
			} else if(cambio == DOLAR) {
				moneda = "dolares";
			}else if(cambio == EURO) {
				moneda = "euros";
			} else if(cambio == LIBRA) {
					moneda = "libras";
			}else if(cambio == SOL) {
				moneda = "soles";
			}else if(cambio == YUAN) {
				moneda = "yuanes";
			} 
		label.setText("Obtuviste: $"+Redondear(res)+" "+moneda);
		label.setForeground(Color.BLACK);
		}
	}
	
	public boolean esNumero(String s) {
		try {
			Double.parseDouble(s);
		} catch(NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	public String Redondear(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(value);
	}
}
