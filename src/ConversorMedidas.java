import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class ConversorMedidas extends JFrame {
	
	private JButton btnConvertir;
	private JComboBox<Medida> comboBox;
	private JComboBox<Medida> comboBoxDos;
	private JLabel label;
	private JTextField txtConvertir;	
	private JButton btnSalir;
	
	public enum Medida{
		MILIMETRO,
		CENTIMETRO,
		DECIMETRO,
		METRO,
		KILOMETRO,
		MILLA,
		YARDA,
		PIE,
		PULGADA
	}
	
	public final double MILIMETRO = 1000;
	public final double CENTIMETRO = 100;
	public final double DECIMETRO = 10;
	public final double METRO = 1;
	public final double KILOMETRO = 0.001;
	public final double MILLA = 0.0006213715;
	public final double YARDA = 1.09361;
	public final double PIE = 3.28084;
	public final double PULGADA = 39.3701;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorMedidas window = new ConversorMedidas();
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
	public ConversorMedidas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Conversor de Medidas");
		setBounds(100, 100, 447, 194);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtConvertir = new JTextField();
		txtConvertir.setBounds(10, 11, 165, 22);
		getContentPane().add(txtConvertir);
		txtConvertir.setColumns(10);
		txtConvertir.setText("");
		
		comboBox = new JComboBox<Medida>();
		comboBox.setModel(new DefaultComboBoxModel<>(Medida.values()));
		comboBox.setBounds(40, 44, 176, 22);
		getContentPane().add(comboBox);
		
		comboBoxDos = new JComboBox<Medida>();
		comboBoxDos.setModel(new DefaultComboBoxModel<>(Medida.values()));
		comboBoxDos.setBounds(250, 44, 174, 22);
		getContentPane().add(comboBoxDos);
		
		label = new JLabel("---Realice su conversión---");
		label.setBounds(185, 11, 239, 22);
		getContentPane().add(label);
		
		btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConvertirMedida();
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
	
	public void ConvertirMedida() {
		double medidaBase = gerMedida(comboBox.getSelectedIndex());
		double medidaCambio= gerMedida(comboBoxDos.getSelectedIndex());
		RealizarConversion(medidaBase,medidaCambio);
	}
	
	public double gerMedida( int index ) {
		switch(index) {
		 case 0:
			 return MILIMETRO;
		 case 1:
			 return CENTIMETRO;
		 case 2:
			 return DECIMETRO;
		 case 3:
			 return METRO;
		 case 4:
			 return KILOMETRO;
		 case 5:
			 return MILLA;	
		 case 6:
			 return YARDA;
		 case 7:
			 return PIE;
		 case 8:
			 return PULGADA;
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
		} else {
			double medida = Double.parseDouble(txtConvertir.getText());
			double res = (medida * cambio) / base;
			String unidad = "";
			if(cambio == MILIMETRO) {
				unidad = "milimetros";
			} else if (cambio == CENTIMETRO) {
				unidad = "centimetros";
			} else if (cambio == DECIMETRO) {
				unidad = "decimetros";
			} else if (cambio == METRO) {
				unidad = "metros";
			} else if (cambio == KILOMETRO) {
				unidad = "kilometros";
			} else if (cambio == MILLA) {
				unidad = "millas";
			} else if (cambio == YARDA) {
				unidad = "yardas";
			} else if (cambio == PIE) {
				unidad = "pies";
			} else if (cambio == PULGADA) {
				unidad = "pulgadas";
			}
			label.setText(Redondear(res)+" "+unidad);
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
