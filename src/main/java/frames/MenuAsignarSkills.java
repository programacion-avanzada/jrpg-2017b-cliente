package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import juego.Pantalla;
import mensajeria.Comando;

public class MenuAsignarSkills extends JFrame {

	private JPanel contentPane;
	private final Gson gson = new Gson();

	protected static final int MAX = 200;

	private int puntosNoAsignados;
	private int puntosAsignadosFuerza;
	private int puntosAsignadosDestreza;
	private int puntosAsignadosInteligencia;

	private int puntosFuerzaMinima;
	private int puntosDestrezaMinima;
	private int puntosInteligenciaMinima;

	private int puntosAsignarInicial;
	private int puntosFuerzaInicial;
	private int puntosDestrezaInicial;
	private int puntosInteligenciaInicial;

	private int puntosAsignar;
	private int puntosFuerza;
	private int puntosDestreza;
	private int puntosInteligencia;

	/**
	 * Crea la ventana.
	 */
	public MenuAsignarSkills(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0), "custom cursor"));

		// Inicializo variables
		puntosNoAsignados = cliente.getPaquetePersonaje().getPuntosNoAsignados();
		puntosAsignadosFuerza = cliente.getPaquetePersonaje().getPuntosAsignadosFuerza();
		puntosAsignadosDestreza = cliente.getPaquetePersonaje().getPuntosAsignadosDestreza();
		puntosAsignadosInteligencia = cliente.getPaquetePersonaje().getPuntosAsignadosInteligencia();

		puntosAsignarInicial = puntosNoAsignados;
		puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
		puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
		puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();

		puntosFuerzaMinima = puntosFuerzaInicial - puntosAsignadosFuerza;
		puntosDestrezaMinima = puntosDestrezaInicial - puntosAsignadosDestreza;
		puntosInteligenciaMinima = puntosInteligenciaInicial - puntosAsignadosInteligencia;

		puntosAsignar = puntosAsignarInicial;
		puntosFuerza = puntosFuerzaInicial;
		puntosDestreza = puntosDestrezaInicial;
		puntosInteligencia = puntosInteligenciaInicial;

		// Crea la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Asignar Skills");
		setBounds(100, 100, 298, 294);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});

		// Puntos de fuerza
		final JLabel labelFuerza = new JLabel("");
		labelFuerza.setForeground(Color.WHITE);
		labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		labelFuerza.setBounds(50, 101, 56, 16);
		labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
		contentPane.add(labelFuerza);

		final JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setForeground(Color.WHITE);
		lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuerza.setBounds(50, 72, 56, 16);
		contentPane.add(lblFuerza);

		// Puntos de destreza
		final JLabel labelDestreza = new JLabel("");
		labelDestreza.setForeground(Color.WHITE);
		labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		labelDestreza.setBounds(50, 159, 56, 16);
		labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
		contentPane.add(labelDestreza);

		JLabel lblDestreza = new JLabel("Destreza");
		lblDestreza.setForeground(Color.WHITE);
		lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestreza.setBounds(50, 130, 56, 16);
		contentPane.add(lblDestreza);

		// Puntos de inteligencia
		final JLabel labelInteligencia = new JLabel("");
		labelInteligencia.setForeground(Color.WHITE);
		labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		labelInteligencia.setBounds(50, 217, 56, 16);
		labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
		contentPane.add(labelInteligencia);

		final JLabel lblInteligencia = new JLabel("Inteligencia");
		lblInteligencia.setForeground(Color.WHITE);
		lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInteligencia.setBounds(39, 188, 83, 16);
		contentPane.add(lblInteligencia);

		// Puntos para asignar
		final JLabel labelPuntos = new JLabel("");
		labelPuntos.setForeground(Color.WHITE);
		labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		labelPuntos.setBounds(39, 41, 83, 26);
		labelPuntos.setText(String.valueOf(puntosAsignarInicial));
		contentPane.add(labelPuntos);

		final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
		lblCantidadDePuntos.setForeground(Color.WHITE);
		lblCantidadDePuntos.setBounds(12, 13, 177, 29);
		contentPane.add(lblCantidadDePuntos);

		// Botón para resetear las habilidades
		final JButton buttonReset = new JButton("Reset");
		ImageIcon icono_Reset = new ImageIcon("recursos//botonReset.png");
		buttonReset.setIcon(icono_Reset);
		buttonReset.setEnabled(false);
		buttonReset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				puntosFuerza = puntosFuerzaMinima;
				puntosDestreza = puntosDestrezaMinima;
				puntosInteligencia = puntosInteligenciaMinima;

				int difFuerza = puntosFuerza - puntosFuerzaInicial;
				int difDestreza = puntosDestreza - puntosDestrezaInicial;
				int difInteligencia = puntosInteligencia - puntosInteligenciaInicial;
				cliente.getPaquetePersonaje().setPuntosNoAsignados(puntosNoAsignados - difFuerza - difDestreza - difInteligencia);
				cliente.getPaquetePersonaje().setPuntosAsignadosFuerza(puntosAsignadosFuerza + difFuerza);
				cliente.getPaquetePersonaje().setPuntosAsignadosDestreza(puntosAsignadosDestreza + difDestreza);
				cliente.getPaquetePersonaje().setPuntosAsignadosInteligencia(puntosAsignadosInteligencia + difInteligencia);
				cliente.getPaquetePersonaje().useBonus(0, 0, difFuerza, difDestreza, difInteligencia);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);

				puntosAsignarInicial = puntosAsignar;

				try {
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar las estadísticas.");
				}

				JOptionPane.showMessageDialog(null, "Se han actualizado los atributos.");
				dispose();

			}
		});
		buttonReset.setBounds(176, 112, 97, 25);
		contentPane.add(buttonReset);

		// Botón para confirmar
		final JButton buttonConfirm = new JButton("Confirmar");
		ImageIcon icono_confirm = new ImageIcon("recursos//botonConfirmar.png");
		buttonConfirm.setIcon(icono_confirm);
		buttonConfirm.setEnabled(false);
		buttonReset.setEnabled(true);
		buttonConfirm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int difFuerza = puntosFuerza - puntosFuerzaInicial;
				int difDestreza = puntosDestreza - puntosDestrezaInicial;
				int difInteligencia = puntosInteligencia - puntosInteligenciaInicial;
				cliente.getPaquetePersonaje().setPuntosNoAsignados(puntosNoAsignados - difFuerza - difDestreza - difInteligencia);
				cliente.getPaquetePersonaje().setPuntosAsignadosFuerza(puntosAsignadosFuerza + difFuerza);
				cliente.getPaquetePersonaje().setPuntosAsignadosDestreza(puntosAsignadosDestreza + difDestreza);
				cliente.getPaquetePersonaje().setPuntosAsignadosInteligencia(puntosAsignadosInteligencia + difInteligencia);
				cliente.getPaquetePersonaje().useBonus(0, 0, difFuerza, difDestreza, difInteligencia);
				cliente.getPaquetePersonaje().removerBonus();
				cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);

				puntosAsignarInicial = puntosAsignar;

				try {
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar las estadísticas.");
				}

				JOptionPane.showMessageDialog(null, "Se han actualizado los atributos.");
				dispose();
			}
		});
		buttonConfirm.setBounds(176, 78, 97, 25);
		contentPane.add(buttonConfirm);

		// Botón para cancelar
		final JButton buttonCancel = new JButton("Cancelar");
		ImageIcon icono_c = new ImageIcon("recursos//botonCancelar.png");
		buttonCancel.setIcon(icono_c);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pantalla.menuAsignar = null;
				dispose();
			}
		});
		buttonCancel.setBounds(176, 146, 97, 25);
		contentPane.add(buttonCancel);

		// Botones para modificar atributos
		final JButton buttonMenos1 = new JButton("");
		final JButton buttonMenos2 = new JButton("");
		final JButton buttonMenos3 = new JButton("");
		final JButton buttonMas1 = new JButton("");
		final JButton buttonMas2 = new JButton("");
		final JButton buttonMas3 = new JButton("");

		// Desactivar botones que no hacen falta
		if (puntosAsignadosFuerza == 0 && puntosAsignadosDestreza == 0 && puntosAsignadosInteligencia == 0)
		{
			buttonMenos1.setEnabled(false);
			buttonMenos2.setEnabled(false);
			buttonMenos3.setEnabled(false);
			buttonReset.setEnabled(false);
		}
		
		if (puntosFuerza == puntosFuerzaMinima || puntosFuerza == puntosFuerzaInicial) {
			buttonMenos1.setEnabled(false);
		}

		if (puntosDestreza == puntosDestrezaMinima || puntosDestreza == puntosDestrezaInicial) {
			buttonMenos2.setEnabled(false);
		}

		if (puntosInteligencia == puntosInteligenciaMinima || puntosInteligencia == puntosInteligenciaInicial) {
			buttonMenos3.setEnabled(false);
		}

		if (puntosAsignar == 0) {
			buttonMas1.setEnabled(false);
			buttonMas2.setEnabled(false);
			buttonMas3.setEnabled(false);
		}

		if (puntosFuerza >= MAX) {
			buttonMas1.setEnabled(false);
		}

		if (puntosDestreza >= MAX) {
			buttonMas2.setEnabled(false);
		}

		if (puntosInteligencia >= MAX) {
			buttonMas3.setEnabled(false);
		}

		// Restar fuerza
		ImageIcon icono_1 = new ImageIcon("recursos//botonMenoss.png");
		buttonMenos1.setIcon(icono_1);
		buttonMenos1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntosFuerza > puntosFuerzaInicial) {
					puntosFuerza--;
					if (puntosAsignar == 0) {
						if (puntosInteligencia < MAX) {
							buttonMas3.setEnabled(true);
						}
						if (puntosDestreza < MAX) {
							buttonMas2.setEnabled(true);
						}
					} else {
						buttonMas1.setEnabled(true);
						buttonMas2.setEnabled(true);
						buttonMas3.setEnabled(true);
					}
					puntosAsignar++;
					if (puntosAsignar == puntosAsignarInicial) {
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					if (puntosFuerza == puntosFuerzaInicial) {
						buttonMenos1.setEnabled(false);
						buttonMas1.setEnabled(true);
					} else if (puntosFuerza >= puntosFuerzaInicial) {
						buttonMas1.setEnabled(true);
					}
				}
			}
		});
		buttonMenos1.setBounds(12, 92, 34, 25);
		contentPane.add(buttonMenos1);

		// Restar destreza
		buttonMenos2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntosDestreza > puntosDestrezaInicial) {
					puntosDestreza--;
					if (puntosAsignar == 0) {
						if (puntosInteligencia < MAX) {
							buttonMas3.setEnabled(true);
						}
						if (puntosFuerza < MAX) {
							buttonMas1.setEnabled(true);
						}
					} else {
						buttonMas1.setEnabled(true);
						buttonMas2.setEnabled(true);
						buttonMas3.setEnabled(true);
					}
					puntosAsignar++;
					if (puntosAsignar == puntosAsignarInicial) {
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					if (puntosDestreza == puntosDestrezaInicial) {
						buttonMenos2.setEnabled(false);
						buttonMas2.setEnabled(true);
					} else if (puntosDestreza >= puntosDestrezaInicial) {
						buttonMas2.setEnabled(true);
					}
				}
			}
		});
		buttonMenos2.setIcon(icono_1);
		buttonMenos2.setBounds(12, 159, 34, 25);
		contentPane.add(buttonMenos2);

		// Restar inteligencia
		buttonMenos3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntosInteligencia > puntosInteligenciaInicial) {
					puntosInteligencia--;
					if (puntosAsignar == 0) {
						if (puntosFuerza < MAX) {
							buttonMas1.setEnabled(true);
						}
						if (puntosDestreza < MAX) {
							buttonMas2.setEnabled(true);
						}
					} else {
						buttonMas1.setEnabled(true);
						buttonMas2.setEnabled(true);
						buttonMas3.setEnabled(true);
					}
					puntosAsignar++;
					if (puntosAsignar == puntosAsignarInicial) {
						buttonConfirm.setEnabled(false);
					}
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));
					if (puntosInteligencia == puntosInteligenciaInicial) {
						buttonMenos3.setEnabled(false);
						buttonMas3.setEnabled(true);
					} else if (puntosInteligencia >= puntosInteligenciaInicial) {
						buttonMas3.setEnabled(true);
					}
				}
			}
		});
		buttonMenos3.setIcon(icono_1);
		buttonMenos3.setBounds(12, 217, 34, 25);
		contentPane.add(buttonMenos3);

		// Aumentar fuerza
		buttonMas1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntosAsignar != 0 && !labelFuerza.getText().equals(Integer.toString(MAX))) {
					puntosFuerza++;
					puntosAsignar--;

					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelFuerza.setText(String.valueOf(puntosFuerza));
					buttonMenos1.setEnabled(true);
					if (puntosAsignar == 0) {
						buttonMas1.setEnabled(false);
						buttonMas2.setEnabled(false);
						buttonMas3.setEnabled(false);
					}
				}
				if (puntosAsignar == 0 || labelFuerza.getText().equals(Integer.toString(MAX))) {
					buttonMas1.setEnabled(false);
				}
			}
		});
		ImageIcon icono_2 = new ImageIcon("recursos//botonMass.png");
		buttonMas1.setIcon(icono_2);
		buttonMas1.setBounds(118, 92, 34, 25);
		contentPane.add(buttonMas1);

		// Aumentar destreza
		buttonMas2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntosAsignar != 0 && !labelDestreza.getText().equals(Integer.toString(MAX))) {
					puntosDestreza++;
					puntosAsignar--;

					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelDestreza.setText(String.valueOf(puntosDestreza));
					buttonMenos2.setEnabled(true);
					if (puntosAsignar == 0) {
						buttonMas1.setEnabled(false);
						buttonMas2.setEnabled(false);
						buttonMas3.setEnabled(false);
					}
					if (puntosAsignar == 0 || labelDestreza.getText().equals(Integer.toString(MAX))) {
						buttonMas2.setEnabled(false);
					}
				}
			}
		});
		buttonMas2.setIcon(icono_2);
		buttonMas2.setBounds(118, 159, 34, 25);
		contentPane.add(buttonMas2);

		// Aumentar inteligencia
		buttonMas3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puntosAsignar != 0 && !labelInteligencia.getText().equals(Integer.toString(MAX))) {
					puntosInteligencia++;
					puntosAsignar--;

					buttonConfirm.setEnabled(true);
					labelPuntos.setText(String.valueOf(puntosAsignar));
					labelInteligencia.setText(String.valueOf(puntosInteligencia));
					buttonMenos3.setEnabled(true);
					if (puntosAsignar == 0) {
						buttonMas1.setEnabled(false);
						buttonMas2.setEnabled(false);
						buttonMas3.setEnabled(false);
					}
					if (puntosAsignar == 0 || labelInteligencia.getText().equals(Integer.toString(MAX))) {
						buttonMas3.setEnabled(false);
					}
				}
			}
		});
		buttonMas3.setIcon(icono_2);
		buttonMas3.setBounds(118, 217, 34, 25);
		contentPane.add(buttonMas3);

		// Imagen de fondo
		final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg"));
		imageLabel.setBounds(0, 0, 298, 294);
		imageLabel.setVisible(true);
		contentPane.add(imageLabel);
	}
}
