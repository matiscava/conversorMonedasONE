

import java.awt.EventQueue;
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

public class Conversor {

	private JFrame frame;
	private JButton btnConvertir;
	private JComboBox comboBox;
	private JLabel label;
	private JTextField txtConvertir;	
	
	public final double DOLAR = 268.83;
	public final double EURO = 299.16;
	public final double LIBRA = 345.25;
	public final double SOL = 74.99;
	public final double YUAN = 37.41;
	
	public double valorInput = 0.00;
	
	public enum	Moneda {
		PESOS_DOLAR,
		PESOS_EURO,
		PESOS_LIBRA,
		PESOS_SOL,
		PESOS_YUAN,
		DOLAR_PESOS,
		EURO_PESOS,
		LIBRA_PESOS,
		SOL_PESOS,
		YUAN_PESOS
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */	
	public Conversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtConvertir = new JTextField();
		txtConvertir.setBounds(28, 11, 147, 22);
		frame.getContentPane().add(txtConvertir);
		txtConvertir.setColumns(10);
		
		comboBox = new JComboBox<Moneda>();
		comboBox.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		comboBox.setBounds(28, 48, 147, 22);
		frame.getContentPane().add(comboBox);
		
		label = new JLabel("00.00");
		label.setBounds(185, 11, 147, 22);
		frame.getContentPane().add(label);
		
		
		
		btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btnConvertir.setBounds(183, 48, 147, 23);
		frame.getContentPane().add(btnConvertir);
	}
	
	public void Convertir() {
		Moneda moneda = (Moneda)comboBox.getSelectedItem();
		switch(moneda) {
		 case PESOS_DOLAR:
			 ConvertirMoneda(1,DOLAR);
			 break;
		 case PESOS_EURO:
			 ConvertirMoneda(1,EURO);
			 break;
		 case PESOS_LIBRA:
			 ConvertirMoneda(1,LIBRA);
			 break;
		 case PESOS_SOL:
			 ConvertirMoneda(1,SOL);
			 break;
		 case PESOS_YUAN:
			 ConvertirMoneda(1,YUAN);
			 break;
		 case DOLAR_PESOS:
			 ConvertirMoneda(DOLAR,1);
			 break;
		 case EURO_PESOS:
			 ConvertirMoneda(EURO,1);
			 break;
		 case LIBRA_PESOS:
			 ConvertirMoneda(LIBRA,1);
			 break;
		 case SOL_PESOS:
			 ConvertirMoneda(SOL,1);
			 break;
		 case YUAN_PESOS:
			 ConvertirMoneda(YUAN,1);
			 break;
		 default:
			 throw new IllegalArgumentException("Valor inexistente: "+moneda);
		}
	}
	
	public void ConvertirMoneda(double base, double cambio) {
		double plata = Double.parseDouble( txtConvertir.getText() );
		double res = (plata * base ) / cambio;
		label.setText(Redondear(res));
	}
	
	public String Redondear(double value) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(value);
	}
}
