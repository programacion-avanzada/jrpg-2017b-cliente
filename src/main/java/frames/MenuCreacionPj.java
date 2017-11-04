package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cliente.Cliente;
import mensajeria.Comando;
import mensajeria.PaquetePersonaje;

/**
 * Menu para la creacion del personaje
 */
public class MenuCreacionPj extends JFrame {

    private static final int Y_LB_FUERZA = 100;
    private static final int X_LB_FUERZA = 33;
    private static final int ALTO_LAYER = 271;
    private static final int ANCHO_LAYER = 444;
    private static final int FUERZA_TXT = 13;
    private static final int TXT_LB_DESTREZA = 13;
    private static final int ALTO_JL_ENERGIA = 20;
    private static final int ANCHO_FUERZA = 22;
    private static final int Y_FUERZA = 102;
    private static final int X_FUERZA = 110;
    private static final int ANCHO_LB_DESTREZA = 60;
    private static final int Y_LB_DESTREZA = 126;
    private static final int X_LB_DESTREZA = 33;
    private static final int Y_LB_ALTO = 22;
    private static final int Y_LB_ANCHO = 66;
    private static final int Y_LB_INTELIGENCIA = 151;
    private static final int X_LB_INTELIGENCIA = 33;
    private static final int ANCHO_DESTREZA = 22;
    private static final int Y_DESTREZA = 127;
    private static final int X_DESTREZA = 110;
    private static final int INTELIGENCIA_TXT = 13;
    private static final int SALUD_TXT = 13;
    private static final int ANCHO_INTELIGENCIA = 22;
    private static final int Y_INTELIGENCIA = 156;
    private static final int X_INTELIGENCIA = 110;
    private static final int Y_LB_SALUD = 183;
    private static final int X_LB_SALUD = 33;
    private static final int ANCHO_SALUD = 22;
    private static final int Y_SALUD = 183;
    private static final int X_SALUD = 110;
    private static final int Y_LB_ENERGIA = 204;
    private static final int X_LB_ENERGIA = 33;
    private static final int TXT_LB_ENERGIA = 13;
    private static final int ANCHO_LB_ENERGIA = 22;
    private static final int Y_ENERGIA = 208;
    private static final int X_ENERGIA = 110;
    private static final int ANCHO_LB_TEXT = 60;
    private static final int Y_LB_TEXT = 125;
    private static final int X_LB_TEXT = 207;
    private static final int TXT_NOMBRE = 15;
    private static final int ALTO_JTXT_NOMBRE = 20;
    private static final int ANCHO_JTXT_NOMBRE = 122;
    private static final int Y_JTXT_NOMBRE = 122;
    private static final int X_JTXT_NOMBRE = 277;
    private static final int COLUMNAS = 10;
    private static final int ALTO_LB_ACEPTAR = 24;
    private static final int ANCHO_LB_ACEPTAR = 50;
    private static final int Y_LBL_ACEPTAR = 173;
    private static final int X_LBL_ACEPTAR = 280;
    private static final int X_LB_RAZA = 33;
    private static final int TXT_ACEPTAR = 15;
    private static final int ANCHO_BTN_ACEPTAR = 153;
    private static final int Y_BTN_ACEPTAR = 174;
    private static final int X_BTN_ACEPTAR = 230;
    private static final int TXT_RAZA = 15;
    private static final int X_LB_CASTA = 161;
    private static final int TXT_CASTA = 15;
    private static final int ALTO_CBX_CASTA = 20;
    private static final int ANCHO_CBX_CASTA = 76;
    private static final int Y_CBX_CASTA = 48;
    private static final int X_CBX_CASTA = 161;
    private static final int ALTO_CBX_RAZA = 20;
    private static final int ANCHO_CBX_RAZA = 76;
    private static final int Y_CBX_RAZA = 48;
    private static final int X_CBX_RAZA = 32;
    private static final int ALTO_BACK = 271;
    private static final int ANCHO_BACK = 444;
    private static final int ALTO_MENU = 300;
    private static final int ANCHO_MENU = 450;
    private static final int Y_MENU = 100;
    private static final int X_MENU = 100;
    private static final int COLUMNA_JL_GENERICO = 23;
    private static final int ANCHO_JL_GENERICO = 46;
    private static final int ALTO_JL_GENERICO = 14;
    private static final int BORDE = 5;
    private JPanel contentPane;
    private JTextField nombre;
    private JLabel destreza;
    private JLabel fuerza;
    private JLabel inteligencia;
    private JLabel salud;
    private JLabel energia;

    private JComboBox<String> cbxCasta;
    private JComboBox<String> cbxRaza;

    /**
     * Iniciador del frame de menu creacion personaje
     * @param cliente cliente que llamo a este metodo
     * @param personaje Paquete personaje donde se almacenara los datos
     * @param gson gson para poder enviar los datos seleccionados al servidor
     */
    public MenuCreacionPj(final Cliente cliente, final PaquetePersonaje personaje, final Gson gson) {
	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
	setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
		new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
		"custom cursor"));

	final String[] vecSalud = {"55", "50", "60" };
	final String[] vecEnergia = {"55", "60", "50" };
	final String[] vecFuerza = {"15", "10", "10" };
	final String[] vecDestreza = {"10", "10", "15" };
	final String[] vecInteligencia = {"10", "15", "10" };

	// En caso de cerrar
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent e) {
		personaje.setNombre(nombre.getText());
		if (nombre.getText().equals("")) {
		    personaje.setNombre("nameless");
		}
		personaje.setRaza((String) cbxRaza.getSelectedItem());
		personaje.setSaludTope(Integer.parseInt(vecSalud[cbxRaza.getSelectedIndex()]));
		personaje.setEnergiaTope(Integer.parseInt(vecEnergia[cbxRaza.getSelectedIndex()]));
		personaje.setCasta((String) cbxCasta.getSelectedItem());
		personaje.setFuerza(Integer.parseInt(vecFuerza[cbxCasta.getSelectedIndex()]));
		personaje.setDestreza(Integer.parseInt(vecDestreza[cbxCasta.getSelectedIndex()]));
		personaje.setInteligencia(Integer.parseInt(vecInteligencia[cbxCasta.getSelectedIndex()]));
		synchronized (cliente) {
		    cliente.notify();
		}
		dispose();
	    }
	});

	setTitle("WOME - Crear personaje");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setResizable(false);
	setBounds(X_MENU, Y_MENU, ANCHO_MENU, ALTO_MENU);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDE, BORDE, BORDE, BORDE));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	setLocationRelativeTo(null);

	JLayeredPane layeredPane = new JLayeredPane();
	layeredPane.setBounds(0, 0, ANCHO_LAYER, ALTO_LAYER);
	contentPane.add(layeredPane);

	JLabel lblNewLabel5 = new JLabel("Fuerza");
	lblNewLabel5.setBounds(X_LB_FUERZA, Y_LB_FUERZA, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	layeredPane.add(lblNewLabel5, new Integer(1));
	lblNewLabel5.setForeground(Color.WHITE);
	lblNewLabel5.setFont(new Font("Tahoma", Font.PLAIN, FUERZA_TXT));

	fuerza = new JLabel("15");
	fuerza.setBounds(X_FUERZA, Y_FUERZA, ANCHO_FUERZA, ALTO_JL_GENERICO);
	layeredPane.add(fuerza, new Integer(1));
	fuerza.setForeground(Color.GREEN);

	JLabel lblDestreza = new JLabel("Destreza");
	lblDestreza.setBounds(X_LB_DESTREZA, Y_LB_DESTREZA, ANCHO_LB_DESTREZA, ALTO_JL_GENERICO);
	layeredPane.add(lblDestreza, new Integer(1));
	lblDestreza.setForeground(Color.WHITE);
	lblDestreza.setFont(new Font("Tahoma", Font.PLAIN, TXT_LB_DESTREZA));

	destreza = new JLabel("10");
	destreza.setBounds(X_DESTREZA, Y_DESTREZA, ANCHO_DESTREZA, ALTO_JL_GENERICO);
	layeredPane.add(destreza, new Integer(1));
	destreza.setForeground(Color.GREEN);

	JLabel lblInteligencia = new JLabel("Inteligencia");
	lblInteligencia.setBounds(X_LB_INTELIGENCIA, Y_LB_INTELIGENCIA, Y_LB_ANCHO, Y_LB_ALTO);
	layeredPane.add(lblInteligencia, new Integer(1));
	lblInteligencia.setFont(new Font("Tahoma", Font.PLAIN, INTELIGENCIA_TXT));
	lblInteligencia.setForeground(Color.WHITE);

	inteligencia = new JLabel("10");
	inteligencia.setBounds(X_INTELIGENCIA, Y_INTELIGENCIA, ANCHO_INTELIGENCIA, ALTO_JL_GENERICO);
	layeredPane.add(inteligencia, new Integer(1));
	inteligencia.setForeground(Color.GREEN);

	JLabel lblSalud = new JLabel("Salud");
	lblSalud.setBounds(X_LB_SALUD, Y_LB_SALUD, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	layeredPane.add(lblSalud, new Integer(1));
	lblSalud.setFont(new Font("Tahoma", Font.PLAIN, SALUD_TXT));
	lblSalud.setForeground(Color.WHITE);

	salud = new JLabel("55");
	salud.setBounds(X_SALUD, Y_SALUD, ANCHO_SALUD, ALTO_JL_GENERICO);
	layeredPane.add(salud, new Integer(1));
	salud.setForeground(Color.GREEN);

	JLabel lblEnergia = new JLabel("Energia");
	lblEnergia.setBounds(X_LB_ENERGIA, Y_LB_ENERGIA, ANCHO_JL_GENERICO, ALTO_JL_ENERGIA);
	layeredPane.add(lblEnergia, new Integer(1));
	lblEnergia.setForeground(Color.WHITE);
	lblEnergia.setFont(new Font("Tahoma", Font.PLAIN, TXT_LB_ENERGIA));

	energia = new JLabel("55");
	energia.setBounds(X_ENERGIA, Y_ENERGIA, ANCHO_LB_ENERGIA, ALTO_JL_GENERICO);
	layeredPane.add(energia, new Integer(1));
	energia.setForeground(Color.GREEN);

	JLabel lblNewLabel4 = new JLabel("Nombre");
	lblNewLabel4.setBounds(X_LB_TEXT, Y_LB_TEXT, ANCHO_LB_TEXT, ALTO_JL_GENERICO);
	layeredPane.add(lblNewLabel4, new Integer(1));
	lblNewLabel4.setForeground(Color.WHITE);
	lblNewLabel4.setFont(new Font("Tahoma", Font.PLAIN, TXT_NOMBRE));

	nombre = new JTextField();
	nombre.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent arg0) {
		crearPj(cliente, personaje, gson, vecSalud, vecEnergia, vecFuerza, vecDestreza, vecInteligencia);
	    }
	});
	nombre.setBounds(X_JTXT_NOMBRE, Y_JTXT_NOMBRE, ANCHO_JTXT_NOMBRE, ALTO_JTXT_NOMBRE);
	layeredPane.add(nombre, new Integer(1));
	nombre.setColumns(COLUMNAS);

	JLabel lblAceptar = new JLabel("Aceptar");
	lblAceptar.setBounds(X_LBL_ACEPTAR, Y_LBL_ACEPTAR, ANCHO_LB_ACEPTAR, ALTO_LB_ACEPTAR);
	layeredPane.add(lblAceptar, new Integer(2));
	lblAceptar.setForeground(Color.WHITE);
	lblAceptar.setFont(new Font("Tahoma", Font.PLAIN, TXT_ACEPTAR));

	// En caso de apretar el boton aceptar
	JButton btnAceptar = new JButton("Aceptar");
	btnAceptar.setBounds(X_BTN_ACEPTAR, Y_BTN_ACEPTAR, ANCHO_BTN_ACEPTAR, COLUMNA_JL_GENERICO);
	layeredPane.add(btnAceptar, new Integer(1));
	btnAceptar.setFocusable(false);
	btnAceptar.setIcon(new ImageIcon(MenuCreacionPj.class.getResource("/frames/BotonMenu.png")));

	btnAceptar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		crearPj(cliente, personaje, gson, vecSalud, vecEnergia, vecFuerza, vecDestreza, vecInteligencia);

	    }

	});

	JLabel lblNewLabel = new JLabel("Raza");
	lblNewLabel.setBounds(X_LB_RAZA, COLUMNA_JL_GENERICO, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	layeredPane.add(lblNewLabel, new Integer(1));
	lblNewLabel.setForeground(Color.WHITE);
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, TXT_RAZA));

	JLabel lblCasta = new JLabel("Casta");
	lblCasta.setBounds(X_LB_CASTA, COLUMNA_JL_GENERICO, ANCHO_JL_GENERICO, ALTO_JL_GENERICO);
	layeredPane.add(lblCasta, new Integer(1));
	lblCasta.setForeground(Color.WHITE);
	lblCasta.setFont(new Font("Tahoma", Font.PLAIN, TXT_CASTA));

	cbxCasta = new JComboBox<>();
	cbxCasta.setBounds(X_CBX_CASTA, Y_CBX_CASTA, ANCHO_CBX_CASTA, ALTO_CBX_CASTA);
	layeredPane.add(cbxCasta, new Integer(1));
	cbxCasta.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		fuerza.setText(vecFuerza[cbxCasta.getSelectedIndex()]);
		destreza.setText(vecDestreza[cbxCasta.getSelectedIndex()]);
		inteligencia.setText(vecInteligencia[cbxCasta.getSelectedIndex()]);
	    }
	});
	cbxCasta.addItem("Guerrero");
	cbxCasta.addItem("Hechicero");
	cbxCasta.addItem("Asesino");

	cbxRaza = new JComboBox<>();
	cbxRaza.setBounds(X_CBX_RAZA, Y_CBX_RAZA, ANCHO_CBX_RAZA, ALTO_CBX_RAZA);
	layeredPane.add(cbxRaza, new Integer(1));
	cbxRaza.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		salud.setText(vecSalud[cbxRaza.getSelectedIndex()]);
		energia.setText(vecEnergia[cbxRaza.getSelectedIndex()]);
	    }
	});
	cbxRaza.addItem("Humano");
	cbxRaza.addItem("Elfo");
	cbxRaza.addItem("Orco");

	JLabel lblBackground = new JLabel("");
	lblBackground.setBounds(0, 0, ANCHO_BACK, ALTO_BACK);
	layeredPane.add(lblBackground, new Integer(0));
	lblBackground.setIcon(new ImageIcon(MenuCreacionPj.class.getResource("/frames/menuBackground.jpg")));
    }

    /**
     * Metodo que prepara el paquete personaje, setea el comando y se lo envia
     * al servidor. Segun el combo box seleccionado por el cliente se elige los
     * stats del personaje
     * @param cliente cliente que esta creando el personaje
     * @param personaje personaje creado
     * @param gson gson para comunicarse con el servidor
     * @param vecSalud vector con los valores de salud
     * @param vecEnergia vector con los valores de energia
     * @param vecFuerza vector con los valores de Fuerza
     * @param vecDestreza vector con los valores de Destreza
     * @param vecInteligencia vector con los valores de Inteligencia
     */
    protected void crearPj(final Cliente cliente, final PaquetePersonaje personaje, final Gson gson,
	    final String[] vecSalud, final String[] vecEnergia, final String[] vecFuerza, final String[] vecDestreza,
	    final String[] vecInteligencia) {

	personaje.setNombre(nombre.getText());
	if (nombre.getText().equals("")) {
	    personaje.setNombre("nameless");
	}
	personaje.setRaza((String) cbxRaza.getSelectedItem());
	personaje.setSaludTope(Integer.parseInt(vecSalud[cbxRaza.getSelectedIndex()]));
	personaje.setEnergiaTope(Integer.parseInt(vecEnergia[cbxRaza.getSelectedIndex()]));
	personaje.setCasta((String) cbxCasta.getSelectedItem());
	personaje.setFuerza(Integer.parseInt(vecFuerza[cbxCasta.getSelectedIndex()]));
	personaje.setDestreza(Integer.parseInt(vecDestreza[cbxCasta.getSelectedIndex()]));
	personaje.setInteligencia(Integer.parseInt(vecInteligencia[cbxCasta.getSelectedIndex()]));
	try {

	    // Le envio los datos al servidor
	    cliente.getPaquetePersonaje().setComando(Comando.CREACIONPJ);
	    cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
	    dispose();
	} catch (JsonSyntaxException | IOException esd) {
	    JOptionPane.showMessageDialog(null, "Error al crear personaje");

	}
    }

}
