package frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

import javax.swing.ImageIcon;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Menu de carga del juego
 */
public class MenuCarga extends JFrame {

    private static final int ALTO_BARRA = 27;
    private static final int ALTO_TITULO = 300;
    private static final int ANCHO_TITULO = 450;
    private static final int Y_TITULO = 100;
    private static final int X_TITULO = 100;
    private static final int BORDE = 5;
    private static final int ALTO_BARRACARGANDO = 27;
    private static final int ANCHO_BARRACARGANDO = 0;
    private static final int Y_BARRACARGANDO = 160;
    private static final int X_BARRACARGANDO = 52;
    private static final int ALTO_LBLBARRACARGA = 40;
    private static final int ANCHO_LBLBARRACARGA = 355;
    private static final int Y_LBLBARRACARGA = 154;
    private static final int X_LBLBARRACARGA = 47;
    private static final int ALTO_LBLLOGO = 90;
    private static final int ANCHO_LBLLOGO = 216;
    private static final int Y_LBLLOGO = 39;
    private static final int X_LBLLOGO = 109;
    private static final int ALTO_LBLBACK = 271;
    private static final int ANCHO_LBLBACK = 444;
    private static final int Y_LBLBACK = 0;
    private static final int X_LBLBACK = 0;
    private JPanel contentPane;
    private JLabel barraCargando;

    /**
     * @param cliente cliente que llamo al menu carga
     */
    public MenuCarga(final Cliente cliente) {
	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
	setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
		new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
		"custom cursor"));

	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	// En caso de cerrar la ventana
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent e) {
		synchronized (cliente) {
		    cliente.setAccion(Comando.SALIR);
		    cliente.notify();
		}
		dispose();
	    }
	});

	// Propiedades de la ventana
	setTitle("WOME - World Of the Middle Earth");
	setBounds(X_TITULO, Y_TITULO, ANCHO_TITULO, ALTO_TITULO);
	setLocationRelativeTo(null);
	setResizable(false);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDE, BORDE, BORDE, BORDE));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	barraCargando = new JLabel("");
	barraCargando.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/Barra.png")));
	barraCargando.setBounds(X_BARRACARGANDO, Y_BARRACARGANDO, ANCHO_BARRACARGANDO, ALTO_BARRACARGANDO);
	contentPane.add(barraCargando);

	JLabel lblBarraCarga = new JLabel("");
	lblBarraCarga.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/BarraCarga.png")));
	lblBarraCarga.setBounds(X_LBLBARRACARGA, Y_LBLBARRACARGA, ANCHO_LBLBARRACARGA, ALTO_LBLBARRACARGA);
	contentPane.add(lblBarraCarga);

	JLabel lblLogo = new JLabel("");
	lblLogo.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/WOME.png")));
	lblLogo.setBounds(X_LBLLOGO, Y_LBLLOGO, ANCHO_LBLLOGO, ALTO_LBLLOGO);
	contentPane.add(lblLogo);

	JLabel lblBackground = new JLabel("");
	lblBackground.setBounds(X_LBLBACK, Y_LBLBACK, ANCHO_LBLBACK, ALTO_LBLBACK);
	contentPane.add(lblBackground);
	lblBackground.setIcon(new ImageIcon(MenuCarga.class.getResource("/frames/menuBackground.jpg")));
    }

    /**
     * Setea el tamaño de la barra
     * @param ancho ancho de la barra
     */
    public void setBarraCargando(final int ancho) {
	barraCargando.setSize(ancho, ALTO_BARRA);
    }
}
