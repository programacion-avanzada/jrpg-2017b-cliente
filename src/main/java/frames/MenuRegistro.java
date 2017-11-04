package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cliente.Cliente;
import mensajeria.Comando;


/**
 * Clase del menu Registro
 *
 */
public class MenuRegistro extends JFrame {

    private static final int CANT_COL = 10;
    private static final int ALTO_MENU = 300;
    private static final int ANCHO_MENU = 450;
    private static final int Y_MENU = 100;
    private static final int X_MENU = 100;
    private static final int ALTO_PANE = 271;
    private static final int ANCHO_PANE = 444;
    private static final int ALTO_LB_USSER = 19;
    private static final int ANCHO_LB_USSER = 57;
    private static final int Y_LB_USSER = 70;
    private static final int X_LB_USSER = 113;
    private static final int ALTO_LB_PASS = 17;
    private static final int ANCHO_LB_PASS = 65;
    private static final int Y_LB_PASS = 121;
    private static final int X_LB_PASS = 113;
    private static final int ALTO_LB_REG = 23;
    private static final int ANCHO_LB_REG = 82;
    private static final int Y_LB_REG = 182;
    private static final int X_LB_REG = 186;
    private static final int TXT_TAM = 15;
    private static final int ALTO_BTN_REG = 23;
    private static final int ANCHO_BTN_REG = 153;
    private static final int Y_BTN_REG = 182;
    private static final int X_BTN_REG = 143;
    private static final int ALTO_TXT_PASS = 20;
    private static final int ANCHO_TXT_PASS = 118;
    private static final int Y_TXT_PASS = 120;
    private static final int X_TXT_PASS = 199;
    private static final int ALTO_TXT_USUARIO = 20;
    private static final int ANCHO_TXT_USUARIO = 118;
    private static final int Y_TXT_USUARIO = 69;
    private static final int X_TXT_USUARIO = 199;
    private static final int ALTO_BACK = 271;
    private static final int ANCHO_BACK = 444;
    private JTextField txtUsuario;
    private JPasswordField pwPassword;

    /**
     * Instantiates a new menu registro.
     * @param cliente the cliente
     */
    public MenuRegistro(final Cliente cliente) {
	setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
	setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
		new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
		"custom cursor"));

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

	setTitle("WOME - Registrarse");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setBounds(X_MENU, Y_MENU, ANCHO_MENU, ALTO_MENU);
	getContentPane().setLayout(null);
	setLocationRelativeTo(null);

	JLayeredPane layeredPane = new JLayeredPane();
	layeredPane.setBounds(0, 0, ANCHO_PANE, ALTO_PANE);
	getContentPane().add(layeredPane);

	JLabel lblUsuario = new JLabel("Usuario");
	lblUsuario.setBounds(X_LB_USSER, Y_LB_USSER, ANCHO_LB_USSER, ALTO_LB_USSER);
	layeredPane.add(lblUsuario, new Integer(1));
	lblUsuario.setForeground(Color.WHITE);
	lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, TXT_TAM));

	JLabel lblPassword = new JLabel("Password");
	lblPassword.setBounds(X_LB_PASS, Y_LB_PASS, ANCHO_LB_PASS, ALTO_LB_PASS);
	layeredPane.add(lblPassword, new Integer(1));
	lblPassword.setForeground(Color.WHITE);
	lblPassword.setFont(new Font("Tahoma", Font.PLAIN, TXT_TAM));

	JLabel lblRegistrarse = new JLabel("Registrarse");
	lblRegistrarse.setBounds(X_LB_REG, Y_LB_REG, ANCHO_LB_REG, ALTO_LB_REG);
	layeredPane.add(lblRegistrarse, new Integer(2));
	lblRegistrarse.setForeground(Color.WHITE);
	lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, TXT_TAM));

	JButton btnRegistrarse = new JButton("");
	btnRegistrarse.setBounds(X_BTN_REG, Y_BTN_REG, ANCHO_BTN_REG, ALTO_BTN_REG);
	layeredPane.add(btnRegistrarse, new Integer(1));
	btnRegistrarse.setFocusable(false);
	btnRegistrarse.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/BotonMenu.png")));

	pwPassword = new JPasswordField();
	pwPassword.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		logIn(cliente);
		dispose();
	    }
	});
	pwPassword.setBounds(X_TXT_PASS, Y_TXT_PASS, ANCHO_TXT_PASS, ALTO_TXT_PASS);
	layeredPane.add(pwPassword, new Integer(1));

	txtUsuario = new JTextField();
	txtUsuario.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		logIn(cliente);
		dispose();
	    }
	});
	txtUsuario.setBounds(X_TXT_USUARIO, Y_TXT_USUARIO, ANCHO_TXT_USUARIO, ALTO_TXT_USUARIO);
	layeredPane.add(txtUsuario, new Integer(1));
	txtUsuario.setColumns(CANT_COL);

	JLabel labelBackground = new JLabel("");
	labelBackground.setBounds(0, 0, ANCHO_BACK, ALTO_BACK);
	layeredPane.add(labelBackground, new Integer(0));
	labelBackground.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/menuBackground.jpg")));
	btnRegistrarse.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		logIn(cliente);
		dispose();
	    }
	});
    }

    /**
     * Gets the txt usuario.
     * @return the txt usuario
     */
    public JTextField gettxtUsuario() {
	return txtUsuario;
    }

    /**
     * Sets the txt usuario.
     * @param txtUsuarioBis the new txt usuario
     */
    public void settxtUsuario(final JTextField txtUsuarioBis) {
	this.txtUsuario = txtUsuarioBis;
    }

    /**
     * Gets the password field.
     * @return the password field
     */
    public JPasswordField getPasswordField() {
	return pwPassword;
    }

    /**
     * Sets the password field.
     * @param pwPasswordParam the new password field
     */
    public void setPasswordField(final JPasswordField pwPasswordParam) {
	this.pwPassword = pwPasswordParam;
    }

    /**
     * Log in.
     * @param cliente the cliente
     */
    private void logIn(final Cliente cliente) {
	synchronized (cliente) {
	    cliente.getPaqueteUsuario().setUsername(txtUsuario.getText());
	    cliente.getPaqueteUsuario().setPassword(String.valueOf(pwPassword.getPassword()));
	    cliente.setAccion(Comando.REGISTRO);
	    cliente.notify();
	}
    }
}