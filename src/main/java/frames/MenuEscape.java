package frames;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import estados.Estado;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Menu de escape, menu principal dentro del juego
 */
public class MenuEscape extends JFrame {

    private static final int ESCALA_Y = 350;
    private static final int ESCALA_X = 200;
    private static final int ALTO_MENU = 270;
    private static final int ANCHO_MENU = 180;
    private static final int Y_MENU = 100;
    private static final int X_MENU = 100;
    private static final int BORDER = 5;
    private static final int ALTO_BTN_STATS = 25;
    private static final int ANCHO_BTN_STATS = 125;
    private static final int Y_BTN_STATS = 13;
    private static final int X_BTN_STATS = 29;
    private static final int ALTO_BTN_ASIGNAR = 25;
    private static final int ANCHO_BTN_ASIGNAR = 125;
    private static final int Y_BTN_ASIGNAR = 66;
    private static final int X_BTN_ASIGNAR = 29;
    private static final int ALTO_BTN_INVT = 25;
    private static final int ANCHO_BTN_INVT = 125;
    private static final int Y_BTN_INVT = 121;
    private static final int X_BTN_INVT = 29;
    private static final int ALTO_BTN_DESC = 25;
    private static final int ANCHO_BTN_DESC = 125;
    private static final int Y_BTN_DESC = 175;
    private static final int X_BTN_DESC = 29;
    private static final int ALTO_BTN_VOLVER = 25;
    private static final int ANCHO_BTN_VOLVER = 125;
    private static final int Y_BTN_VOLVER = 227;
    private static final int X_BTN_VOLVER = 29;
    private static final int ALTO_BACK = 273;
    private static final int ANCHO_BACK = 186;
    private JPanel contentPane;
    private final Gson gson = new Gson();

    /**
     * Create the frame.
     * @param cliente cliente que ingresa al menu
     */
    public MenuEscape(final Cliente cliente) {
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setUndecorated(true);
	this.setResizable(false);
	this.setBounds(X_MENU, Y_MENU, ANCHO_MENU, ALTO_MENU);
	this.setLocationRelativeTo(null);

	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JButton verStats = new JButton("Estadísticas");
	verStats.setIcon(new ImageIcon("recursos//stats.png"));
	verStats.setToolTipText("Presiona S para ver estadísticas");
	verStats.setBounds(X_BTN_STATS, Y_BTN_STATS, ANCHO_BTN_STATS, ALTO_BTN_STATS);
	verStats.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
		Pantalla.menuEscp = null;
		if (Pantalla.menuStats == null) {
		    Pantalla.menuStats = new MenuStats(cliente);
		    Pantalla.menuStats.setVisible(true);
		}
	    }
	});
	contentPane.add(verStats);

	JButton asignarSkills = new JButton("Asignar Skills");
	asignarSkills.setIcon(new ImageIcon("recursos//asignar skills.png"));
	asignarSkills.setToolTipText("Presiona A para asignar skills");
	asignarSkills.setBounds(X_BTN_ASIGNAR, Y_BTN_ASIGNAR, ANCHO_BTN_ASIGNAR, ALTO_BTN_ASIGNAR);
	asignarSkills.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
		Pantalla.menuEscp = null;
		if (Pantalla.menuAsignar == null) {
		    Pantalla.menuAsignar = new MenuAsignarSkills(cliente);
		    Pantalla.menuAsignar.setVisible(true);
		}
	    }
	});
	contentPane.add(asignarSkills);

	JButton inventario = new JButton("Inventario");
	inventario.setIcon(new ImageIcon("recursos//inventario.png"));
	inventario.setToolTipText("Presiona I para abrir inventario");
	inventario.setBounds(X_BTN_INVT, Y_BTN_INVT, ANCHO_BTN_INVT, ALTO_BTN_INVT);
	inventario.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
		Pantalla.menuEscp = null;
		if (Estado.getEstado().esEstadoDeJuego()) {
		    if (Pantalla.menuInventario == null) {
			Pantalla.menuInventario = new MenuInventario(cliente);
			Pantalla.menuInventario.setVisible(true);
		    }
		}
	    }
	});
	contentPane.add(inventario);

	JButton desconectarse = new JButton("Desconectarse");
	desconectarse.setBounds(X_BTN_DESC, Y_BTN_DESC, ANCHO_BTN_DESC, ALTO_BTN_DESC);
	desconectarse.setIcon(new ImageIcon("recursos//desconectarse.png"));
	desconectarse.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		try {
		    Paquete p = new Paquete();
		    p.setComando(Comando.DESCONECTAR);
		    p.setIp(cliente.getMiIp());
		    cliente.getSalida().writeObject(gson.toJson(p));
		    cliente.getEntrada().close();
		    cliente.getSalida().close();
		    cliente.getSocket().close();
		    System.exit(0);
		} catch (IOException e1) {
		    JOptionPane.showMessageDialog(null, "Error al desconectar");

		}
	    }
	});
	contentPane.add(desconectarse);

	JButton volver = new JButton("Volver");
	volver.setIcon(new ImageIcon("recursos//volver.png"));
	volver.setBounds(X_BTN_VOLVER, Y_BTN_VOLVER, ANCHO_BTN_VOLVER, ALTO_BTN_VOLVER);
	volver.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		Pantalla.menuEscp = null;
		dispose();
	    }
	});
	contentPane.add(volver);

	BufferedImage imagenFondo = null;
	try {
	    imagenFondo = ImageIO.read(new File("recursos//fondo2.png"));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "No se pudo cargar el fondo");

	}
	JLabel background = new JLabel(
		new ImageIcon(imagenFondo.getScaledInstance(ESCALA_X, ESCALA_Y, Image.SCALE_DEFAULT)));
	background.setBounds(0, 0, ANCHO_BACK, ALTO_BACK);
	contentPane.add(background);
    }
}
