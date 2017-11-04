package frames;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import juego.Pantalla;
import mensajeria.Comando;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Menu para modificar/asignar puntos desbloqueados
 * por el personaje al subir de nivel
 */
public class MenuAsignarSkills extends JFrame {

    private static final int PUNTOS_INICIAL_PLUS = 15;
    private static final int PUNTOS_INICIAL = 10;
    private static final int MAX_PUNTOS = 200;
    private static final int BORDE = 5;
    private static final int ALTO_MENU = 300;
    private static final int ANCHO_MENU = 450;
    private static final int ALTO_IMAGEN = 294;
    private static final int ANCHO_IMAGEN = 298;
    private static final int X_MENUASIGNAR = 100;
    private static final int Y_LABELDESTREZA = 159;
    private static final int Y_LABELINTELIGENCIA = 217;
    private static final int ALTO_LABELPUNTOS = 26;
    private static final int Y_LABELPUNTOS = 41;
    private static final int ALTO_BOTONMENOS = 29;
    private static final int ANCHO_BOTONMENOS = 177;
    private static final int Y_BOTONMENOS = 13;
    private static final int ANCHO_LBLINTELIGANCIA = 83;
    private static final int Y_LBLINTELIGENCIA = 188;
    private static final int X_ATRIBUTOS1 = 39;
    private static final int Y_LBLDESTREZA = 130;
    private static final int Y_LBLFUERZA = 72;
    private static final int ALTO_ATRIBUTOS = 16;
    private static final int ANCHO_ATRIBUTOS = 56;
    private static final int X_ATRIBUTOS2 = 50;
    private static final int ANCHO_BOTONRESTART = 112;
    private static final int ANCHO_BOTONCANCELRESTART = 97;
    private static final int Y_BOTONCANCEL = 146;
    private static final int X_BOTONCANCELRESTART = 176;
    private static final int Y_BOTONMENOS1 = 92;
    private static final int Y_BOTONMENOS2 = 159;
    private static final int Y_BOTONMENOS3 = 217;
    private static final int X_BOTONMENOS = 12;
    private static final int Y_BOTONMAS1 = 92;
    private static final int Y_BOTONMAS2 = 159;
    private static final int Y_BOTONMAS3 = 217;
    private static final int X_BOTONMAS = 118;
    private static final int ALTO_BOTONMASMENOS = 25;
    private static final int ANCHO_BOTONMASMENOS = 34;
    private static final int ALTO_IMAGEBACK = 259;
    private static final int ANCHO_IMAGEBACK = 282;
    private static final int Y_IMAGEBACJ = BORDE;
    private static final int X_IMAGEBACK = BORDE;
    private static final int TAMLETRA_BOTONRESTART = PUNTOS_INICIAL_PLUS;
    private static final int ALTO_BOTONRESTART = ALTO_BOTONMASMENOS;
    private static final int Y_BOTONCONFIRMAR = ANCHO_BOTONCANCELRESTART;
    private static final int Y_BOTONRESTART = 78;
    private static final int X_BOTONRESTART = X_BOTONCANCELRESTART;
    private static final int ALTO_LABELFUERZA = ALTO_ATRIBUTOS;
    private static final int ANCHO_LABELFUERZA = ANCHO_ATRIBUTOS;
    private static final int Y_LABELFUERZA = 101;
    private static final int X_LABELFUERZA = X_ATRIBUTOS2;
    private JPanel contentPane;
    private int puntosAsignarInicial = PUNTOS_INICIAL;
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
     * @param cliente cliente que se obtendra los puntos
     */
    public MenuAsignarSkills(final Cliente cliente) {
	puntosAsignarInicial = cliente.getPaquetePersonaje().getPuntosSkill();
	puntosFuerzaInicial = cliente.getPaquetePersonaje().getFuerza();
	puntosDestrezaInicial = cliente.getPaquetePersonaje().getDestreza();
	puntosInteligenciaInicial = cliente.getPaquetePersonaje().getInteligencia();
	puntosAsignar = puntosAsignarInicial;
	puntosFuerza = puntosFuerzaInicial;
	puntosDestreza = puntosDestrezaInicial;
	puntosInteligencia = puntosInteligenciaInicial;

	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setBounds(X_MENUASIGNAR, X_MENUASIGNAR, ANCHO_MENU, ALTO_MENU);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDE, BORDE, BORDE, BORDE));
	setContentPane(contentPane);

	setIconImage(Toolkit.getDefaultToolkit().getImage("recursos//1up.png"));
	setTitle("Asignar");
	setBounds(X_MENUASIGNAR, X_MENUASIGNAR, ANCHO_IMAGEN, ALTO_IMAGEN);

	getContentPane().setLayout(null);
	setVisible(true);
	setLocationRelativeTo(null);
	setResizable(false);
	setLocationRelativeTo(null);

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent arg0) {
		cerrarMenuAsignarSkills();
	    }
	});
	contentPane.setLayout(null);

	final JLabel labelFuerza = new JLabel("");
	labelFuerza.setForeground(Color.WHITE);
	labelFuerza.setHorizontalAlignment(SwingConstants.CENTER);
	labelFuerza.setBounds(X_LABELFUERZA, Y_LABELFUERZA, ANCHO_LABELFUERZA, ALTO_LABELFUERZA);
	labelFuerza.setText(String.valueOf(puntosFuerzaInicial));
	contentPane.add(labelFuerza);

	final JLabel labelDestreza = new JLabel("");
	labelDestreza.setForeground(Color.WHITE);
	labelDestreza.setHorizontalAlignment(SwingConstants.CENTER);
	labelDestreza.setBounds(X_ATRIBUTOS2, Y_LABELDESTREZA, ANCHO_ATRIBUTOS, ALTO_ATRIBUTOS);
	labelDestreza.setText(String.valueOf(puntosDestrezaInicial));
	contentPane.add(labelDestreza);

	final JLabel labelInteligencia = new JLabel("");
	labelInteligencia.setForeground(Color.WHITE);
	labelInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
	labelInteligencia.setBounds(X_ATRIBUTOS2, Y_LABELINTELIGENCIA, ANCHO_ATRIBUTOS, ALTO_ATRIBUTOS);
	labelInteligencia.setText(String.valueOf(puntosInteligenciaInicial));
	contentPane.add(labelInteligencia);

	final JLabel labelPuntos = new JLabel("");
	labelPuntos.setForeground(Color.WHITE);
	labelPuntos.setHorizontalAlignment(SwingConstants.CENTER);
	labelPuntos.setBounds(X_ATRIBUTOS1, Y_LABELPUNTOS, ANCHO_LBLINTELIGANCIA, ALTO_LABELPUNTOS);
	labelPuntos.setText(String.valueOf(puntosAsignarInicial));
	contentPane.add(labelPuntos);

	final JLabel lblCantidadDePuntos = new JLabel("Cantidad de Puntos a Asignar");
	lblCantidadDePuntos.setForeground(Color.WHITE);
	lblCantidadDePuntos.setBounds(X_BOTONMENOS, Y_BOTONMENOS, ANCHO_BOTONMENOS, ALTO_BOTONMENOS);
	contentPane.add(lblCantidadDePuntos);

	final JLabel lblInteligencia = new JLabel("Inteligencia");
	lblInteligencia.setForeground(Color.WHITE);
	lblInteligencia.setHorizontalAlignment(SwingConstants.CENTER);
	lblInteligencia.setBounds(X_ATRIBUTOS1, Y_LBLINTELIGENCIA, ANCHO_LBLINTELIGANCIA, ALTO_ATRIBUTOS);
	contentPane.add(lblInteligencia);

	JLabel lblDestreza = new JLabel("Destreza");
	lblDestreza.setForeground(Color.WHITE);
	lblDestreza.setHorizontalAlignment(SwingConstants.CENTER);
	lblDestreza.setBounds(X_ATRIBUTOS2, Y_LBLDESTREZA, ANCHO_ATRIBUTOS, ALTO_ATRIBUTOS);
	contentPane.add(lblDestreza);

	final JLabel lblFuerza = new JLabel("Fuerza");
	lblFuerza.setForeground(Color.WHITE);
	lblFuerza.setHorizontalAlignment(SwingConstants.CENTER);
	lblFuerza.setBounds(X_ATRIBUTOS2, Y_LBLFUERZA, ANCHO_ATRIBUTOS, ALTO_ATRIBUTOS);
	contentPane.add(lblFuerza);

	final JButton buttonConfirm = new JButton("Confirmar");
	ImageIcon iconoConfirm = new ImageIcon("recursos//botonConfirmar.png");
	buttonConfirm.setIcon(iconoConfirm);
	buttonConfirm.setEnabled(false);
	buttonConfirm.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		puntosAsignarInicial = puntosAsignar;
		int bonusF = puntosFuerza - puntosFuerzaInicial;
		int bonusD = puntosDestreza - puntosDestrezaInicial;
		int bonusI = puntosInteligencia - puntosInteligenciaInicial;
		cliente.getPaquetePersonaje().useBonus(0, 0, bonusF, bonusD, bonusI);
		cliente.getPaquetePersonaje().removerBonus();
		cliente.getPaquetePersonaje().setPuntosSkill(puntosAsignar);
		cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARPERSONAJELV);
		try {
		    cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
		} catch (IOException e1) {
		    JOptionPane.showMessageDialog(null, "Error al actualizar stats");

		}
		JOptionPane.showMessageDialog(null, "Se han actualizado tus atributos.");
		cerrarMenuAsignarSkills();
	    }
	});
	buttonConfirm.setBounds(X_BOTONCANCELRESTART, Y_BOTONCONFIRMAR, ANCHO_BOTONCANCELRESTART, ALTO_BOTONMASMENOS);
	contentPane.add(buttonConfirm);

	final JButton buttonCancel = new JButton("Cancelar");
	ImageIcon iconoCancelar = new ImageIcon("recursos//botonCancelar.png");
	buttonCancel.setIcon(iconoCancelar);
	buttonCancel.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		cerrarMenuAsignarSkills();
	    }
	});
	buttonCancel.setBounds(X_BOTONCANCELRESTART, Y_BOTONCANCEL, ANCHO_BOTONCANCELRESTART, ALTO_BOTONMASMENOS);
	contentPane.add(buttonCancel);

	final JButton buttonMinus = new JButton("");
	final JButton buttonMinus1 = new JButton("");
	final JButton buttonMinus2 = new JButton("");
	final JButton buttonMore = new JButton("");
	final JButton buttonMore1 = new JButton("");
	final JButton buttonMore2 = new JButton("");
	buttonMinus.setEnabled(false);
	buttonMinus1.setEnabled(false);
	buttonMinus2.setEnabled(false);

	if (puntosAsignar == 0) {
	    buttonMore.setEnabled(false);
	    buttonMore1.setEnabled(false);
	    buttonMore2.setEnabled(false);
	}

	ImageIcon icono1 = new ImageIcon("recursos//botonMenoss.png");
	buttonMinus.setBounds(X_BOTONMENOS, Y_BOTONMENOS1, ANCHO_BOTONMASMENOS, ALTO_BOTONMASMENOS);
	contentPane.add(buttonMinus);

	buttonMinus.setIcon(icono1);
	buttonMinus.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {

		if (puntosFuerza > getMinusFuerza(cliente)) {
		    puntosFuerza--;
		    if (puntosAsignar == 0) {
			if (puntosInteligencia != MAX_PUNTOS) {
			    buttonMore2.setEnabled(true);
			}
			if (puntosDestreza != MAX_PUNTOS) {
			    buttonMore1.setEnabled(true);
			}
		    } else {
			buttonMore.setEnabled(true);
			buttonMore1.setEnabled(true);
			buttonMore2.setEnabled(true);
		    }
		    puntosAsignar++;
		    if (puntosAsignar == puntosAsignarInicial) {
			buttonConfirm.setEnabled(false);
		    }
		    labelPuntos.setText(String.valueOf(puntosAsignar));
		    labelFuerza.setText(String.valueOf(puntosFuerza));
		    if (puntosFuerza == puntosFuerzaInicial) {
			buttonMinus.setEnabled(false);
			buttonMore.setEnabled(true);
		    } else if (puntosFuerza >= puntosFuerzaInicial) {
			buttonMore.setEnabled(true);
		    }
		}
	    }
	});

	buttonMinus1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (puntosDestreza > getMinusDestreza(cliente)) {
		    puntosDestreza--;
		    if (puntosAsignar == 0) {
			if (puntosInteligencia != MAX_PUNTOS) {
			    buttonMore2.setEnabled(true);
			}
			if (puntosFuerza != MAX_PUNTOS) {
			    buttonMore.setEnabled(true);
			}
		    } else {
			buttonMore.setEnabled(true);
			buttonMore1.setEnabled(true);
			buttonMore2.setEnabled(true);
		    }
		    puntosAsignar++;
		    if (puntosAsignar == puntosAsignarInicial) {
			buttonConfirm.setEnabled(false);
		    }
		    labelPuntos.setText(String.valueOf(puntosAsignar));
		    labelDestreza.setText(String.valueOf(puntosDestreza));
		    if (puntosDestreza == puntosDestrezaInicial) {
			buttonMinus1.setEnabled(false);
			buttonMore1.setEnabled(true);
		    } else if (puntosDestreza >= puntosDestrezaInicial) {
			buttonMore1.setEnabled(true);
		    }
		}
	    }
	});
	buttonMinus1.setIcon(icono1);
	buttonMinus1.setBounds(X_BOTONMENOS, Y_BOTONMENOS2, ANCHO_BOTONMASMENOS, ALTO_BOTONMASMENOS);
	contentPane.add(buttonMinus1);

	buttonMinus2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (puntosInteligencia > getMinusInteligencia(cliente)) {
		    puntosInteligencia--;
		    if (puntosAsignar == 0) {
			if (puntosFuerza != MAX_PUNTOS) {
			    buttonMore.setEnabled(true);
			}
			if (puntosDestreza != MAX_PUNTOS) {
			    buttonMore1.setEnabled(true);
			}
		    } else {
			buttonMore.setEnabled(true);
			buttonMore1.setEnabled(true);
			buttonMore2.setEnabled(true);
		    }
		    puntosAsignar++;
		    if (puntosAsignar == puntosAsignarInicial) {
			buttonConfirm.setEnabled(false);
		    }
		    labelPuntos.setText(String.valueOf(puntosAsignar));
		    labelInteligencia.setText(String.valueOf(puntosInteligencia));
		    if (puntosInteligencia == puntosInteligenciaInicial) {
			buttonMinus2.setEnabled(false);
			buttonMore2.setEnabled(true);
		    } else if (puntosInteligencia >= puntosInteligenciaInicial) {
			buttonMore2.setEnabled(true);
		    }
		}
	    }
	});
	buttonMinus2.setIcon(icono1);
	buttonMinus2.setBounds(X_BOTONMENOS, Y_BOTONMENOS3, ANCHO_BOTONMASMENOS, ALTO_BOTONMASMENOS);
	contentPane.add(buttonMinus2);

	buttonMore.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (puntosAsignar != 0 && !labelFuerza.getText().equals("200")) {
		    puntosFuerza++;
		    puntosAsignar--;
		    buttonConfirm.setEnabled(true);
		    labelPuntos.setText(String.valueOf(puntosAsignar));
		    labelFuerza.setText(String.valueOf(puntosFuerza));
		    buttonMinus.setEnabled(true);
		    if (puntosAsignar == 0) {
			buttonMore.setEnabled(false);
			buttonMore1.setEnabled(false);
			buttonMore2.setEnabled(false);
		    }
		}
		if (puntosAsignar == 0 || labelFuerza.getText().equals("200")) {
		    buttonMore.setEnabled(false);
		}
	    }
	});
	ImageIcon icono2 = new ImageIcon("recursos//botonMass.png");
	buttonMore.setIcon(icono2);
	buttonMore.setBounds(X_BOTONMAS, Y_BOTONMAS1, ANCHO_BOTONMASMENOS, ALTO_BOTONMASMENOS);
	contentPane.add(buttonMore);

	buttonMore1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (puntosAsignar != 0 && !labelDestreza.getText().equals("200")) {
		    puntosDestreza++;
		    puntosAsignar--;
		    buttonConfirm.setEnabled(true);
		    labelPuntos.setText(String.valueOf(puntosAsignar));
		    labelDestreza.setText(String.valueOf(puntosDestreza));
		    buttonMinus1.setEnabled(true);
		    if (puntosAsignar == 0) {
			buttonMore.setEnabled(false);
			buttonMore1.setEnabled(false);
			buttonMore2.setEnabled(false);
		    }
		    if (puntosAsignar == 0 || labelDestreza.getText().equals("200")) {
			buttonMore1.setEnabled(false);
		    }
		}
	    }
	});
	buttonMore1.setIcon(icono2);
	buttonMore1.setBounds(X_BOTONMAS, Y_BOTONMAS2, ANCHO_BOTONMASMENOS, ALTO_BOTONMASMENOS);
	contentPane.add(buttonMore1);

	buttonMore2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (puntosAsignar != 0 && !labelInteligencia.getText().equals("200")) {
		    puntosInteligencia++;
		    puntosAsignar--;
		    buttonConfirm.setEnabled(true);
		    labelPuntos.setText(String.valueOf(puntosAsignar));
		    labelInteligencia.setText(String.valueOf(puntosInteligencia));
		    buttonMinus2.setEnabled(true);
		    if (puntosAsignar == 0) {
			buttonMore.setEnabled(false);
			buttonMore1.setEnabled(false);
			buttonMore2.setEnabled(false);
		    }
		    if (puntosAsignar == 0 || labelInteligencia.getText().equals("200")) {
			buttonMore2.setEnabled(false);
		    }
		}
	    }
	});
	buttonMore2.setIcon(icono2);
	buttonMore2.setBounds(X_BOTONMAS, Y_BOTONMAS3, ANCHO_BOTONMASMENOS, ALTO_BOTONMASMENOS);
	contentPane.add(buttonMore2);

	final JLabel imageLabel = new JLabel(new ImageIcon("recursos//background.jpg"));
	imageLabel.setBounds(X_IMAGEBACK, Y_IMAGEBACJ, ANCHO_IMAGEBACK, ALTO_IMAGEBACK);
	imageLabel.setVisible(true);
	contentPane.add(imageLabel);

	// BOTON RESET DE PUNTOS SKILLS
	JButton buttonRestart = new JButton("Reiniciar");
	ImageIcon iconoRestart = new ImageIcon("recursos//botonMenu.png");
	buttonRestart.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent paramActionEvent) {
		// Puntos skills disponibles
		puntosAsignar = (cliente.getPaquetePersonaje().getNivel() - 1) * (1 + 1 + 1);
		labelPuntos.setText(String.valueOf(puntosAsignar));

		// Puntos de fuerza base (bonus y items)
		puntosFuerza = getMinusFuerza(cliente);
		labelFuerza.setText(String.valueOf(puntosFuerza));

		// Puntos de destreza base (bonus y items)
		puntosDestreza = getMinusDestreza(cliente);
		labelDestreza.setText(String.valueOf(puntosDestreza));

		// Puntos de inteligencia base (bonus y items)
		puntosInteligencia = getMinusInteligencia(cliente);
		labelInteligencia.setText(String.valueOf(puntosInteligencia));

		if (puntosAsignar != 0) {
		    buttonMore.setEnabled(true);
		    buttonMore1.setEnabled(true);
		    buttonMore2.setEnabled(true);
		}

		buttonMinus.setEnabled(false);
		buttonMinus1.setEnabled(false);
		buttonMinus2.setEnabled(false);

		buttonConfirm.setEnabled(true);

	    }
	});
	buttonRestart.setHorizontalTextPosition(SwingConstants.CENTER);
	buttonRestart.setHorizontalTextPosition(SwingConstants.CENTER);
	buttonRestart.setFont(new Font("Arial", Font.PLAIN, TAMLETRA_BOTONRESTART));
	buttonRestart.setForeground(Color.WHITE);
	buttonRestart.setIcon(iconoRestart);
	buttonRestart.setBounds(X_BOTONRESTART, Y_BOTONRESTART, ANCHO_BOTONRESTART, ALTO_BOTONRESTART);
	contentPane.add(buttonRestart);

    }

    /**
     * @param cliente cliente que se obtendra los puntos
     * @return bonus de fuerza total de los items incluyendo el bonus por casta
     */
    private int getMinusFuerza(final Cliente cliente) {
	int fueIni = PUNTOS_INICIAL;
	if (cliente.getPaquetePersonaje().getCasta().equals("Guerrero")) {
	    fueIni = PUNTOS_INICIAL_PLUS;
	}
	return fueIni + cliente.getPaquetePersonaje().getFuerzaItems();
    }

    /**
     * @param cliente cliente que se obtendra los puntos
     * @return bonus de inteligencia total de los items incluyendo el bonus por
     *         casta
     */
    private int getMinusInteligencia(final Cliente cliente) {
	int intIni = PUNTOS_INICIAL;
	if (cliente.getPaquetePersonaje().getCasta().equals("Hechicero")) {
	    intIni = PUNTOS_INICIAL_PLUS;
	}
	return (intIni + cliente.getPaquetePersonaje().getInteligenciaItem());
    }

    /**
     * @param cliente cliente que se obtendra los puntos
     * @return bonus de destreza total de los items incluyendo el bonus por
     *         casta
     */
    private int getMinusDestreza(final Cliente cliente) {
	int intDes = PUNTOS_INICIAL;
	if (cliente.getPaquetePersonaje().getCasta().equals("Asesino")) {
	    intDes = PUNTOS_INICIAL_PLUS;
	}
	return intDes + cliente.getPaquetePersonaje().getDestrezaItem();
    }

    /**
     * Cierra la pantalla del menu
     */
    private void cerrarMenuAsignarSkills() {
	Pantalla.menuAsignar = null;
	dispose();
    }
}
