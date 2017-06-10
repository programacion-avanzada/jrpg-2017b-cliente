package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import mensajeria.Comando;

public class MenuAsignarSkills extends JFrame {

	private JPanel contentPane;
	private int puntosAsignarInicial = 10;
	private int puntosFuerzaInicial = 0;
	private int puntosDestrezaInicial = 0;
	private int puntosInteligenciaInicial = 0;
	private int puntosAsignar = puntosAsignarInicial;
	private int puntosFuerza = puntosFuerzaInicial;
	private int puntosDestreza = puntosDestrezaInicial;
	private int puntosInteligencia = puntosInteligenciaInicial;
	private final Gson gson = new Gson();

	/**
	 * Create the frame.
	 */
	public MenuAsignarSkills(Cliente cliente) {
		puntosAsignarInicial = 3;
//		puntosAsignarInicial = cliente.getPaquetePersonaje().getPtsAsignar();
		puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();
		puntosAsignar = puntosAsignarInicial;
		puntosFuerza = puntosFuerzaInicial;
		puntosDestreza = puntosDestrezaInicial;
		puntosInteligencia = puntosInteligenciaInicial;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
		setTitle("Asignar");
		setBounds(100, 100, 298, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JLabel labelFuerza = new JLabel("");
		labelFuerza.setForeground(Color.WHITE);
		labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		labelFuerza.setBounds(50, 101, 56, 16);
		labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
		contentPane.add(labelFuerza);
		
		JLabel labelDestreza = new JLabel("");
		labelDestreza.setForeground(Color.WHITE);
		labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		labelDestreza.setBounds(50, 159, 56, 16);
		labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
		contentPane.add(labelDestreza);
		
		JLabel labelInteligencia = new JLabel("");
		labelInteligencia.setForeground(Color.WHITE);
		labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		labelInteligencia.setBounds(50, 217, 56, 16);
		labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
		contentPane.add(labelInteligencia);
		
		JLabel labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(39, 41, 83, 26);
		labelPuntos.setText(String.valueOf(puntosAsignarInicial));
		contentPane.add(labelPuntos);
		
		JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
		lblCantidadDePuntos.setForeground(Color.WHITE);
		lblCantidadDePuntos.setBounds(12, 13, 177, 29);
		contentPane.add(lblCantidadDePuntos);
		
		JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteligencia.setBounds(39, 188, 83, 16);
		contentPane.add(lblInteligencia);
		
		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestreza.setBounds(50, 130, 56, 16);
		contentPane.add(lblDestreza);
		
		JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuerza.setBounds(50, 72, 56, 16);
		contentPane.add(lblFuerza);
		
		JButton buttonConfirm = new JButton("Confirmar");
		ImageIcon icono_confirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(icono_confirm);
		buttonConfirm.setEnabled(false);
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				puntosAsignarInicial = puntosAsignar;
				int bonusF = puntosFuerza-puntosFuerzaInicial;
				int bonusD = puntosDestreza-puntosDestrezaInicial;
				int bonusI = puntosInteligencia-puntosInteligenciaInicial;
				cliente.getPaquetePersonaje().useBonus(0, 0, bonusF, bonusD, bonusI);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
				try {
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"Se han actualizado tus atributos.");
				dispose();
			}
		});
		buttonConfirm.setBounds(176, 112, 97, 25);
		contentPane.add(buttonConfirm);
		
		JButton buttonCancel = new JButton("Cancelar");
		ImageIcon icono_c = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(icono_c);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonCancel.setBounds(176, 146, 97, 25);
		contentPane.add(buttonCancel);
		
		JButton buttonMinus = new JButton("");
		JButton buttonMinus1 = new JButton("");
		JButton buttonMinus2 = new JButton("");
		JButton buttonMore = new JButton("");
		JButton buttonMore1 = new JButton("");
		JButton buttonMore2 = new JButton("");
		buttonMinus.setEnabled(false);
		buttonMinus1.setEnabled(false);
		buttonMinus2.setEnabled(false);
		
		ImageIcon icono_1 = new ImageIcon("recursos//botonMenoss.png");
		buttonMinus.setIcon(icono_1);
		buttonMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosFuerza > puntosFuerzaInicial){
					puntosFuerza--;
					if(puntosAsignar == 0){
						if(puntosInteligencia != 200){
							buttonMore2.setEnabled(true);
						}
						if(puntosDestreza != 200){
							buttonMore1.setEnabled(true);
						}
					} else {
							buttonMore.setEnabled(true);
							buttonMore1.setEnabled(true);
							buttonMore2.setEnabled(true);
					}
					puntosAsignar++;
					if(puntosAsignar == puntosAsignarInicial){
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					if(puntosFuerza == puntosFuerzaInicial){
						buttonMinus.setEnabled(false);
						buttonMore.setEnabled(true);
					} else if(puntosFuerza >= puntosFuerzaInicial) {
						buttonMore.setEnabled(true);
					}
				}
			}
		});
		buttonMinus.setBounds(12, 92, 34, 25);
		contentPane.add(buttonMinus);
		
		buttonMinus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosDestreza > puntosDestrezaInicial){
					puntosDestreza--;
					if(puntosAsignar == 0){
						if(puntosInteligencia != 200){
							buttonMore2.setEnabled(true);
						}
						if(puntosFuerza != 200){
							buttonMore.setEnabled(true);
						}
					} else {
							buttonMore.setEnabled(true);
							buttonMore1.setEnabled(true);
							buttonMore2.setEnabled(true);
					}
					puntosAsignar++;
					if(puntosAsignar == puntosAsignarInicial){
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					if(puntosDestreza == puntosDestrezaInicial){
						buttonMinus1.setEnabled(false);
						buttonMore1.setEnabled(true);
					} else if(puntosDestreza >= puntosDestrezaInicial) {
						buttonMore1.setEnabled(true);
					}
				}
			}
		});
		buttonMinus1.setIcon(icono_1);
		buttonMinus1.setBounds(12, 159, 34, 25);
		contentPane.add(buttonMinus1);
		
		buttonMinus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosInteligencia > puntosInteligenciaInicial){
					puntosInteligencia--;
					if(puntosAsignar == 0){
						if(puntosFuerza != 200){
							buttonMore.setEnabled(true);
						}
						if(puntosDestreza != 200){
							buttonMore1.setEnabled(true);
						}
					} else {
							buttonMore.setEnabled(true);
							buttonMore1.setEnabled(true);
							buttonMore2.setEnabled(true);
					}
					puntosAsignar++;
					if(puntosAsignar == puntosAsignarInicial){
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));
					if(puntosInteligencia == puntosInteligenciaInicial){
						buttonMinus2.setEnabled(false);
						buttonMore2.setEnabled(true);
					} else if(puntosInteligencia >= puntosInteligenciaInicial) {
						buttonMore2.setEnabled(true);
					}
				}
			}
		});
		buttonMinus2.setIcon(icono_1);
		buttonMinus2.setBounds(12, 217, 34, 25);
		contentPane.add(buttonMinus2);
		
		buttonMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosAsignar != 0 && !labelFuerza.getText().equals("200")){
					puntosFuerza++;
					puntosAsignar--;
					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					buttonMinus.setEnabled(true);
					if(puntosAsignar == 0){
							buttonMore.setEnabled(false);
							buttonMore1.setEnabled(false);
							buttonMore2.setEnabled(false);
					}
				}
				if(puntosAsignar == 0 || labelFuerza.getText().equals("200")){
					buttonMore.setEnabled(false);
				}
			}
		});
		ImageIcon icono_2 = new ImageIcon("recursos//botonMass.png");
		buttonMore.setIcon(icono_2);
		buttonMore.setBounds(118, 92, 34, 25);
		contentPane.add(buttonMore);
		
		
		buttonMore1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosAsignar != 0 && !labelDestreza.getText().equals("200")){
					puntosDestreza++;
					puntosAsignar--;
					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					buttonMinus1.setEnabled(true);
					if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
					}
					if(puntosAsignar == 0 || labelDestreza.getText().equals("200")){
						buttonMore1.setEnabled(false);
					}
				}
			}
		});
		buttonMore1.setIcon(icono_2);
		buttonMore1.setBounds(118, 159, 34, 25);
		contentPane.add(buttonMore1);
		
		buttonMore2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(puntosAsignar != 0 && !labelInteligencia.getText().equals("200")){
					puntosInteligencia++;
					puntosAsignar--;
					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));					
					buttonMinus2.setEnabled(true);
					if(puntosAsignar == 0){
						buttonMore.setEnabled(false);
						buttonMore1.setEnabled(false);
						buttonMore2.setEnabled(false);
					}
					if(puntosAsignar == 0 || labelInteligencia.getText().equals("200")){
						buttonMore2.setEnabled(false);
					}
				}
			}
		});
		buttonMore2.setIcon(icono_2);
		buttonMore2.setBounds(118, 217, 34, 25);
		contentPane.add(buttonMore2);
		
		JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg")); 
		imageLabel.setBounds(0, 0, 298, 294);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);
	}
}
