package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Menu de inicio
 * Arranca la ejecucion del cliente
 * por este frame
 */
public class MenuInicio extends JFrame {

    private static final int ALTO_MENU = 300;
    private static final int ANCHO_MENU = 450;
    private static final int Y_MENU = 100;
    private static final int X_MENU = 100;
    private static final int ALTO_LOGO = 90;
    private static final int ANCHO_LOGO = 216;
    private static final int Y_LOGO = 39;
    private static final int X_LOGO = 109;
    private static final int ALTO_PANE = 271;
    private static final int ANCHO_PANE = 444;
    private static final int ALTO_BTN_JUGAR = 23;
    private static final int ANCHO_BTN_JUGAR = 82;
    private static final int Y_BTN_JUGAR = 162;
    private static final int X_BTN_JUGAR = 205;
    private static final int ALTO_BTN_SALIR = 23;
    private static final int ANCHO_BTN_SALIR = 91;
    private static final int Y_BTN_SALIR = 202;
    private static final int X_BTN_SALIR = 210;
    private static final int BORDE = 5;
    private static final int TAM_TEXTO = 15;
    private static final int ALTO_BTN_REG = 23;
    private static final int ANCHO_BTN_REG = 191;
    private static final int Y_BTN_REG = 162;
    private static final int X_BTN_REG = 127;
    private static final int ALTO_BTN_INICIO = 23;
    private static final int ANCHO_BTN_INICIO = 191;
    private static final int Y_BTN_INICIO = 202;
    private static final int X_BTN_INICIO = 127;
    private static final int Y_BACK = 271;
    private static final int X_BACK = 444;
    private JPanel contentPane;

    /**
     * Iniciador del menu inicio
     */
    public MenuInicio() {
	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    Cliente cliente = new Cliente();
		    cliente.start();
		    dispose();
		}
	    }
	});
	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
	setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
		new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
		"custom cursor"));

	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	// Propiedades de la ventana
	setTitle("WOME - World Of the Middle Earth");
	setBounds(X_MENU, Y_MENU, ANCHO_MENU, ALTO_MENU);
	setLocationRelativeTo(null);
	setResizable(false);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDE, BORDE, BORDE, BORDE));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JLabel lblLogo = new JLabel("");
	lblLogo.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/WOME.png")));
	lblLogo.setBounds(X_LOGO, Y_LOGO, ANCHO_LOGO, ALTO_LOGO);
	contentPane.add(lblLogo);

	JLayeredPane layeredPane = new JLayeredPane();
	layeredPane.setBounds(0, 0, ANCHO_PANE, ALTO_PANE);
	contentPane.add(layeredPane);

	// Boton Jugar
	JLabel lblRegistrarse = new JLabel("Jugar");
	lblRegistrarse.setBounds(X_BTN_JUGAR, Y_BTN_JUGAR, ANCHO_BTN_JUGAR, ALTO_BTN_JUGAR);
	layeredPane.add(lblRegistrarse, new Integer(2));
	lblRegistrarse.setForeground(Color.WHITE);
	lblRegistrarse.setEnabled(true);
	lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO));
	lblRegistrarse.setBackground(Color.WHITE);

	// Boton Salir
	JLabel lblIniciarSesion = new JLabel("Salir");
	lblIniciarSesion.setBounds(X_BTN_SALIR, Y_BTN_SALIR, ANCHO_BTN_SALIR, ALTO_BTN_SALIR);
	layeredPane.add(lblIniciarSesion, new Integer(2));
	lblIniciarSesion.setForeground(Color.WHITE);
	lblIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO));

	JButton btnRegistrar = new JButton("Jugar");
	btnRegistrar.setBounds(X_BTN_REG, Y_BTN_REG, ANCHO_BTN_REG, ALTO_BTN_REG);
	layeredPane.add(btnRegistrar, new Integer(1));
	btnRegistrar.setFocusable(false);
	btnRegistrar.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
	btnRegistrar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		Cliente cliente = new Cliente();
		cliente.start();
		dispose();
	    }
	});

	JButton btnIniciarSesion = new JButton("Salir");
	btnIniciarSesion.setBounds(X_BTN_INICIO, Y_BTN_INICIO, ANCHO_BTN_INICIO, ALTO_BTN_INICIO);
	layeredPane.add(btnIniciarSesion, new Integer(1));
	btnIniciarSesion.setFocusable(false);
	btnIniciarSesion.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/BotonMenu.png")));
	btnIniciarSesion.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
	    }
	});

	JLabel lblBackground = new JLabel("");

	lblBackground.setBounds(0, 0, X_BACK, Y_BACK);
	lblBackground.setIcon(new ImageIcon(MenuJugar.class.getResource("/frames/menuBackground.jpg")));
	layeredPane.add(lblBackground, new Integer(0));
    }

    /**
     * @param args argumentos del inicio de ejecucion
     */
    public static void main(final String[] args) {
	new MenuInicio().setVisible(true);
    }

}
