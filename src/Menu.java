import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class Menu extends JFrame{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Menu");
		setBounds(100, 100, 450, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lbl = new JLabel("Selecciona una opción:");
		lbl.setBounds(41, 11, 216, 24);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().add(lbl);
		
		JComboBox comboMenu = new JComboBox();
		comboMenu.setModel(new DefaultComboBoxModel(new String[] {"Conversor de Monedas", "Conversor de Medidas"}));
		comboMenu.setBounds(41, 34, 358, 24);
		comboMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(comboMenu);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = comboMenu.getSelectedIndex();
				switch(index) {
					case 0:
						ConversorMonedas conversorMonedas = new ConversorMonedas();
						conversorMonedas.setVisible(true);
						dispose();
						break;
					case 1:
						ConversorMedidas conversorMedidas= new ConversorMedidas();
						conversorMedidas.setVisible(true);
						dispose();
						break;
				}
			}
		});
		btnSiguiente.setBounds(98, 69, 89, 23);
		getContentPane().add(btnSiguiente);
		
		JButton btnCerrar = new JButton("Salir");
		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(238, 69, 89, 23);
		getContentPane().add(btnCerrar);
	}
}
