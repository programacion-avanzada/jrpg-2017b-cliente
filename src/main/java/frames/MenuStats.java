package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;

/**
 *
 */
public class MenuStats extends JFrame {

    private static final int ALTO_BACK = 312;
    private static final int ANCHO_BACK = 363;
    private static final int Y_BACK = -11;
    private static final int Y_RESOLUTION = 350;
    private static final int X_RESOLUTION = 400;
    private static final int ALTO_BTN_VOLVER = 25;
    private static final int ANCHO_BTN_VOLVER = 97;
    private static final int Y_BTN_VOLVER = 245;
    private static final int X_BTN_VOLVER = 128;
    private static final int ALTO_MENU = 321;
    private static final int ANCHO_MENU = 346;
    private static final int BORDER = 5;
    private static final int ANCHO_LB_CANTITEM = 110;
    private static final int ANCHO_CANTITEM = 39;
    private static final int X_LB_CANTITEM = 118;
    private static final int Y_LB_CANTINTEM = 216;
    private static final int ANCHO3_BTN_GENERICO = 72;
    private static final int ANCHO2_BTN_GENERICO = 48;
    private static final int ANCHO1_BTN_GENERICO = 56;
    private static final int FIL7_BTN_GENERICO = 71;
    private static final int FIL6_BTN_GENERICO = 13;
    private static final int FIL5_BTN_GENERICO = 42;
    private static final int FIL4_BTN_GENERICO = 100;
    private static final int FIL3_BTN_GENERICO = 129;
    private static final int FIL2_BTN_GENERICO = 158;
    private static final int FIL1_BTN_GENERICO = 187;
    private static final int COL4_BTN_GENERICO = 12;
    private static final int COL3_BTN_GENERICO = 169;
    private static final int COL2_BTN_GENERICO = 80;
    private static final int COL1_BTN_GENERICO = 251;
    private static final int ANCHO_BTN_GENERICO = 77;
    private static final int ALTO_BTN_GENERICO = 16;
    private JPanel contentPane;
    private PaquetePersonaje paquetePersonaje;
    private final double mod = 1.5;

    /**
     * Create the frame.
     * @param cliente
     */
    public MenuStats(final Cliente cliente) {
	paquetePersonaje = cliente.getPaquetePersonaje();

	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setResizable(false);
	this.setBounds(FIL4_BTN_GENERICO, FIL4_BTN_GENERICO, ANCHO_MENU, ALTO_MENU);
	this.setLocationRelativeTo(null);
	this.setTitle("Estadísticas");

	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent e) {
		Pantalla.menuStats = null;
		dispose();
	    }
	});

	BufferedImage imagenFondo = null;
	try {
	    imagenFondo = ImageIO.read(new File("recursos//background.jpg"));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

	}

	JLabel lblNombre = new JLabel("Nombre");
	lblNombre.setForeground(Color.WHITE);
	lblNombre.setBounds(COL4_BTN_GENERICO, FIL6_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblNombre);

	JLabel lblCasta = new JLabel("Casta");
	lblCasta.setForeground(Color.WHITE);
	lblCasta.setBounds(COL4_BTN_GENERICO, FIL5_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblCasta);

	JLabel lblRaza = new JLabel("Raza");
	lblRaza.setForeground(Color.WHITE);
	lblRaza.setBounds(COL4_BTN_GENERICO, FIL7_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblRaza);

	JLabel lblNivel = new JLabel("Nivel");
	lblNivel.setForeground(Color.WHITE);
	lblNivel.setBounds(COL3_BTN_GENERICO, FIL6_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblNivel);

	JLabel lblExperiencia = new JLabel("Experiencia");
	lblExperiencia.setForeground(Color.WHITE);
	lblExperiencia.setBounds(COL3_BTN_GENERICO, FIL5_BTN_GENERICO, ANCHO3_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblExperiencia);

	JLabel lblEnergia = new JLabel("Energia");
	lblEnergia.setForeground(Color.WHITE);
	lblEnergia.setBounds(COL3_BTN_GENERICO, FIL4_BTN_GENERICO, ANCHO2_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblEnergia);

	JLabel lblSalud = new JLabel("Salud");
	lblSalud.setForeground(Color.WHITE);
	lblSalud.setBounds(COL4_BTN_GENERICO, FIL4_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblSalud);

	JLabel lblFuerza = new JLabel("Fuerza");
	lblFuerza.setForeground(Color.WHITE);
	lblFuerza.setBounds(COL4_BTN_GENERICO, FIL3_BTN_GENERICO, ANCHO2_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblFuerza);

	JLabel lblDestreza = new JLabel("Destreza");
	lblDestreza.setForeground(Color.WHITE);
	lblDestreza.setBounds(COL4_BTN_GENERICO, FIL2_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblDestreza);

	JLabel lblInteligencia = new JLabel("Inteligencia");
	lblInteligencia.setForeground(Color.WHITE);
	lblInteligencia.setBounds(COL4_BTN_GENERICO, FIL1_BTN_GENERICO, ANCHO3_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblInteligencia);

	JLabel lblAtaque = new JLabel("Ataque");
	lblAtaque.setForeground(Color.WHITE);
	lblAtaque.setBounds(COL3_BTN_GENERICO, FIL3_BTN_GENERICO, ANCHO2_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblAtaque);

	JLabel lblDefensa = new JLabel("Defensa");
	lblDefensa.setForeground(Color.WHITE);
	lblDefensa.setBounds(COL3_BTN_GENERICO, FIL2_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblDefensa);

	JLabel lblMagia = new JLabel("Magia");
	lblMagia.setForeground(Color.WHITE);
	lblMagia.setBounds(COL3_BTN_GENERICO, FIL1_BTN_GENERICO, ANCHO1_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lblMagia);

	JLabel lblCantidadDeItems = new JLabel("Cantidad de Items");
	lblCantidadDeItems.setForeground(Color.WHITE);
	lblCantidadDeItems.setBounds(COL4_BTN_GENERICO, Y_LB_CANTINTEM, ANCHO_LB_CANTITEM, ALTO_BTN_GENERICO);
	contentPane.add(lblCantidadDeItems);

	JLabel nmbPj = new JLabel(paquetePersonaje.getNombre());
	nmbPj.setForeground(Color.WHITE);
	nmbPj.setHorizontalAlignment(SwingConstants.RIGHT);
	nmbPj.setBounds(COL2_BTN_GENERICO, FIL6_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(nmbPj);

	JLabel cstPj = new JLabel(paquetePersonaje.getCasta());
	cstPj.setForeground(Color.WHITE);
	cstPj.setHorizontalAlignment(SwingConstants.RIGHT);
	cstPj.setBounds(COL2_BTN_GENERICO, FIL5_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(cstPj);

	JLabel rzPj = new JLabel(paquetePersonaje.getRaza());
	rzPj.setForeground(Color.WHITE);
	rzPj.setHorizontalAlignment(SwingConstants.RIGHT);
	rzPj.setBounds(COL2_BTN_GENERICO, FIL7_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(rzPj);

	JLabel saludPj = new JLabel(String.valueOf(paquetePersonaje.getSaludTope()));
	saludPj.setForeground(Color.WHITE);
	saludPj.setHorizontalAlignment(SwingConstants.RIGHT);
	saludPj.setBounds(COL2_BTN_GENERICO, FIL4_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(saludPj);

	JLabel fzaPj = new JLabel(String.valueOf(paquetePersonaje.getFuerza()));
	fzaPj.setForeground(Color.WHITE);
	fzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
	fzaPj.setBounds(COL2_BTN_GENERICO, FIL3_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(fzaPj);

	JLabel dstzaPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
	dstzaPj.setForeground(Color.WHITE);
	dstzaPj.setHorizontalAlignment(SwingConstants.RIGHT);
	dstzaPj.setBounds(COL2_BTN_GENERICO, FIL2_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(dstzaPj);

	JLabel intPj = new JLabel(String.valueOf(paquetePersonaje.getInteligencia()));
	intPj.setForeground(Color.WHITE);
	intPj.setHorizontalAlignment(SwingConstants.RIGHT);
	intPj.setBounds(COL2_BTN_GENERICO, FIL1_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(intPj);

	JLabel cantItem = new JLabel(String.valueOf(paquetePersonaje.getCantItems()));
	cantItem.setForeground(Color.WHITE);
	cantItem.setHorizontalAlignment(SwingConstants.RIGHT);
	cantItem.setBounds(X_LB_CANTITEM, Y_LB_CANTINTEM, ANCHO_CANTITEM, ALTO_BTN_GENERICO);
	contentPane.add(cantItem);

	JLabel lvPj = new JLabel(String.valueOf(paquetePersonaje.getNivel()));
	lvPj.setForeground(Color.WHITE);
	lvPj.setHorizontalAlignment(SwingConstants.RIGHT);
	lvPj.setBounds(COL1_BTN_GENERICO, FIL6_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(lvPj);

	JLabel xpPj = new JLabel(String.valueOf(paquetePersonaje.getExperiencia()));
	xpPj.setForeground(Color.WHITE);
	xpPj.setHorizontalAlignment(SwingConstants.RIGHT);
	xpPj.setBounds(COL1_BTN_GENERICO, FIL5_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(xpPj);

	JLabel energiaPj = new JLabel(String.valueOf(paquetePersonaje.getEnergiaTope()));
	energiaPj.setForeground(Color.WHITE);
	energiaPj.setHorizontalAlignment(SwingConstants.RIGHT);
	energiaPj.setBounds(COL1_BTN_GENERICO, FIL4_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(energiaPj);

	int ataquePj = calcularAtaque(paquetePersonaje.getFuerza());
	JLabel ataPj = new JLabel(String.valueOf(ataquePj));
	ataPj.setForeground(Color.WHITE);
	ataPj.setHorizontalAlignment(SwingConstants.RIGHT);
	ataPj.setBounds(COL1_BTN_GENERICO, FIL3_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(ataPj);

	JLabel defPj = new JLabel(String.valueOf(paquetePersonaje.getDestreza()));
	defPj.setForeground(Color.WHITE);
	defPj.setHorizontalAlignment(SwingConstants.RIGHT);
	defPj.setBounds(COL1_BTN_GENERICO, FIL2_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(defPj);

	int intePj = calcularMagia(paquetePersonaje.getInteligencia());
	JLabel magicPj = new JLabel(String.valueOf(intePj));
	magicPj.setForeground(Color.WHITE);
	magicPj.setHorizontalAlignment(SwingConstants.RIGHT);
	magicPj.setBounds(COL1_BTN_GENERICO, FIL1_BTN_GENERICO, ANCHO_BTN_GENERICO, ALTO_BTN_GENERICO);
	contentPane.add(magicPj);

	JButton btnVolver = new JButton("Volver");
	btnVolver.setIcon(new ImageIcon("recursos//volver.png"));
	btnVolver.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		Pantalla.menuStats = null;
		dispose();
	    }
	});
	btnVolver.setBounds(X_BTN_VOLVER, Y_BTN_VOLVER, ANCHO_BTN_VOLVER, ALTO_BTN_VOLVER);
	contentPane.add(btnVolver);
	JLabel background = new JLabel(
		new ImageIcon(imagenFondo.getScaledInstance(X_RESOLUTION, Y_RESOLUTION, Image.SCALE_DEFAULT)));
	background.setBounds(-COL4_BTN_GENERICO, Y_BACK, ANCHO_BACK, ALTO_BACK);
	contentPane.add(background);
    }

    /**
     * @param inteligencia inteligencia del personaje
     * @return inteligencia aumentada por la casta
     */
    private int calcularMagia(final int inteligencia) {
	return (int) (inteligencia * mod);
    }

    /**
     * @param fuerza fuerza del personaje
     * @return fuerza aumentada por la casta
     */
    private int calcularAtaque(final int fuerza) {
	return (int) (fuerza * mod);
    }
}
