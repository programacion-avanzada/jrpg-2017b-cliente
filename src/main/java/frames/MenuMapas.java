package frames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import mensajeria.Comando;

import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Clase MenuMapas
 *
 */
public class MenuMapas extends JFrame {
    private static final int ALTO_MENU_MAPA = 300;
    private static final int ANCHO_MENU_MAPA = 450;
    private static final int Y_MENU_MAPA = 100;
    private static final int X_MENU_MAPA = 100;
    private static final int BORDER = 5;
    private static final int ALTO_PANE = 271;
    private static final int ANCHO_PANE = 444;
    private static final int ALTO_LB_ARIS = 23;
    private static final int ANCHO_LB_ARIS = 32;
    private static final int Y_LB_ARIS = 129;
    private static final int X_LB_ARIS = 204;
    private static final int ALTO_LB_AUBENOR = 23;
    private static final int ANCHO_LB_AUBENOR = 66;
    private static final int Y_LB_AUBENOR = 72;
    private static final int X_LB_AUBENOR = 191;
    private static final int ALTO_LB_EODRIN = 23;
    private static final int ANCHO_LB_EODRIN = 53;
    private static final int Y_LB_EODRIN = 192;
    private static final int X_LB_EODRIN = 198;
    private static final int TAM_TXT = 15;
    private static final int ALTO_BTN_AUBENOR = 23;
    private static final int ANCHO_BTN_AUBENOR = 143;
    private static final int Y_BTN_AUBENOR = 72;
    private static final int X_BTN_AUBENOR = 148;
    private static final int ALTO_BTN_EODRIN = 23;
    private static final int ANCHO_BTN_EODRIN = 143;
    private static final int Y_BTN_EODRIN = 192;
    private static final int X_BTN_ENDORIN = 148;
    private static final int ALTO_BTN_ARIS = 23;
    private static final int ANCHO_BTN_ARIS = 143;
    private static final int Y_BTN_ARIS = 130;
    private static final int X_BTN_ARIS = 148;
    private static final int ALTO_BACK = 271;
    private static final int ANCHO_BACK = 444;
    private static int numberMap = 0;
    private final JPanel contentPane;

    /**
     * Constructor por parametro de MenuMapas
     * @param cliente cliente sobre el cual crea el menu
     */
    public MenuMapas(final Cliente cliente) {
	addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    synchronized (cliente) {
			cliente.getPaquetePersonaje().setMapa(1);
			numberMap = 1;
			cliente.notify();
		    }
		    dispose();
		}
	    }
	});
	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
	setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
		new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
		"custom cursor"));

	// En caso de cerrar
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

	// Panel
	setTitle("WOME - Elegir Mapa");
	setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	setBounds(X_MENU_MAPA, Y_MENU_MAPA, ANCHO_MENU_MAPA, ALTO_MENU_MAPA);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	setLocationRelativeTo(null);
	setResizable(false);

	final JLayeredPane layeredPane = new JLayeredPane();
	layeredPane.setBounds(0, 0, ANCHO_PANE, ALTO_PANE);
	contentPane.add(layeredPane);

	// Mapa Aris
	final JLabel lblAris = new JLabel("Aris");
	lblAris.setBounds(X_LB_ARIS, Y_LB_ARIS, ANCHO_LB_ARIS, ALTO_LB_ARIS);
	layeredPane.add(lblAris, new Integer(2));
	lblAris.setForeground(Color.WHITE);
	lblAris.setFont(new Font("Tahoma", Font.PLAIN, TAM_TXT));

	// Mapa Aubenor
	final JLabel lblAubenor = new JLabel("Aubenor");
	lblAubenor.setBounds(X_LB_AUBENOR, Y_LB_AUBENOR, ANCHO_LB_AUBENOR, ALTO_LB_AUBENOR);
	layeredPane.add(lblAubenor, new Integer(2));
	lblAubenor.setForeground(Color.WHITE);
	lblAubenor.setFont(new Font("Tahoma", Font.PLAIN, TAM_TXT));

	// Mapa Eodrim
	final JLabel lblEodrim = new JLabel("Eodrim");
	lblEodrim.setBounds(X_LB_EODRIN, Y_LB_EODRIN, ANCHO_LB_EODRIN, ALTO_LB_EODRIN);
	layeredPane.add(lblEodrim, new Integer(2));
	lblEodrim.setForeground(Color.WHITE);
	lblEodrim.setFont(new Font("Tahoma", Font.PLAIN, TAM_TXT));

	final JButton btnAubenor = new JButton("");
	btnAubenor.setBounds(X_BTN_AUBENOR, Y_BTN_AUBENOR, ANCHO_BTN_AUBENOR, ALTO_BTN_AUBENOR);
	layeredPane.add(btnAubenor, new Integer(1));
	btnAubenor.setFocusable(false);
	btnAubenor.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/BotonMenu.png")));

	final JButton btnEodrim = new JButton("");
	btnEodrim.setBounds(X_BTN_ENDORIN, Y_BTN_EODRIN, ANCHO_BTN_EODRIN, ALTO_BTN_EODRIN);
	layeredPane.add(btnEodrim, new Integer(1));
	btnEodrim.setFocusable(false);
	btnEodrim.setEnabled(false);
	btnEodrim.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/BotonMenu.png")));
	btnEodrim.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		synchronized (cliente) {
		    cliente.getPaquetePersonaje().setMapa(2 + 1);
		    cliente.notify();
		}
		dispose();
	    }
	});

	btnEodrim.setEnabled(false);

	final JButton btnAris = new JButton("");
	btnAris.setBounds(X_BTN_ARIS, Y_BTN_ARIS, ANCHO_BTN_ARIS, ALTO_BTN_ARIS);
	layeredPane.add(btnAris, new Integer(1));
	btnAris.setFocusable(false);
	btnAris.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/BotonMenu.png")));
	btnAris.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		synchronized (cliente) {
		    cliente.getPaquetePersonaje().setMapa(2);
		    numberMap = 2;
		    cliente.notify();
		}
		dispose();
	    }
	});

	btnAris.setEnabled(true);

	final JLabel lblBackground = new JLabel("");
	lblBackground.setBounds(0, 0, ANCHO_BACK, ALTO_BACK);
	layeredPane.add(lblBackground, new Integer(0));
	lblBackground.setIcon(new ImageIcon(MenuMapas.class.getResource("/frames/menuBackground.jpg")));
	btnAubenor.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		synchronized (cliente) {
		    cliente.getPaquetePersonaje().setMapa(1);
		    numberMap = 1;
		    cliente.notify();
		}
		dispose();
	    }
	});
    }

    /**
     * @return the numberMap
     */
    public static int getNumberMap() {
        return numberMap;
    }
}
