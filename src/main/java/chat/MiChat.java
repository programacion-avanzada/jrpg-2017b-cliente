package chat;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import com.google.gson.Gson;

import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;

/**
 * Ventana del chat
 */
public class MiChat extends JFrame {

    private static final int ALTO_CHAT = 300;

    private static final int ANCHO_CHAT = 450;

    private static final int Y_CHAT = 100;

    private static final int X_CHAT = 100;

    private static final int ALTO_SCROLL = 201;

    private static final int ANCHO_SCROLL = 414;

    private static final int Y_SCROLL = 11;

    private static final int X_SCROLL = 10;

    private static final int ALTO_ENVIAR = 23;

    private static final int ANCHO_ENVIAR = 81;

    private static final int Y_ENVIAR = 225;

    private static final int X_ENVIAR = 334;

    private static final int ALTO_TEXTO = 27;

    private static final int ANCHO_TEXTO = 314;

    private static final int Y_TEXTO = 223;

    private static final int X_TEXTO = 10;

    private static final int COLUMNAS = 10;

    private static final int ALTO_BACK = 283;

    private static final int ANCHO_BACK = 480;

    private static final int X_BACK = -20;

    private static final int BORDE = 5;

    private JPanel contentPane;

    private JTextField texto;

    private JTextArea chat;

    private final Gson gson = new Gson();
    private final JLabel background = new JLabel(new ImageIcon("recursos//background.jpg"));

    private DefaultCaret caret;

    /**
     * Create the frame.
     * @param juego que llama al chat
     */
    public MiChat(final Juego juego) {
	setTitle("Mi Chat");

	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setBounds(X_CHAT, Y_CHAT, ANCHO_CHAT, ALTO_CHAT);
	setResizable(false);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(BORDE, BORDE, BORDE, BORDE));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane.setBounds(X_SCROLL, Y_SCROLL, ANCHO_SCROLL, ALTO_SCROLL);
	contentPane.add(scrollPane);

	chat = new JTextArea();
	chat.setEditable(false);
	scrollPane.setViewportView(chat);
	caret = (DefaultCaret) chat.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

	texto = new JTextField();
	this.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowOpened(final WindowEvent e) {
		texto.requestFocus();
	    }

	    @Override
	    public void windowClosing(final WindowEvent e) {
		if (getTitle() == "Sala") {
		    if (Pantalla.ventContac != null) {
			VentanaContactos.getBotonMc().setEnabled(true);
		    }
		}
		juego.getChatsActivos().remove(getTitle());
	    }
	});

	// SI TOCO ENTER
	texto.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (!texto.getText().equals("")) {
		    chat.append("Me: " + texto.getText() + "\n");

		    juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
		    juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
		    juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

		    // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
		    juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
		    // El user receptor en espacio indica que es para todos
		    if (getTitle() == "Sala") {
			juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
		    }

		    try {
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
		    } catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error al enviar mensaje");
		    }
		    texto.setText("");
		}
		texto.requestFocus();
	    }
	});

	// SI TOCO ENVIAR
	JButton enviar = new JButton("ENVIAR");
	enviar.setIcon(new ImageIcon("recursos//enviarButton.png"));
	enviar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (!texto.getText().equals("")) {
		    chat.append("Me: " + texto.getText() + "\n");

		    juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
		    juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
		    juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());

		    // MANDO EL COMANDO PARA QUE ENVIE EL MSJ
		    juego.getCliente().getPaqueteMensaje().setComando(Comando.TALK);
		    // El user receptor en espacio indica que es para todos
		    if (getTitle() == "Sala") {
			juego.getCliente().getPaqueteMensaje().setUserReceptor(null);
		    }

		    try {
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getCliente().getPaqueteMensaje()));
		    } catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error al enviar mensaje");

		    }
		    texto.setText("");
		}
		texto.requestFocus();
	    }
	});
	enviar.setBounds(X_ENVIAR, Y_ENVIAR, ANCHO_ENVIAR, ALTO_ENVIAR);
	contentPane.add(enviar);

	texto.setBounds(X_TEXTO, Y_TEXTO, ANCHO_TEXTO, ALTO_TEXTO);
	contentPane.add(texto);
	texto.setColumns(COLUMNAS);
	background.setBounds(X_BACK, 0, ANCHO_BACK, ALTO_BACK);
	contentPane.add(background);
    }

    /**
     * Retorna la referencia al jTextArea del chat
     * @return JtextArea
     */
    public JTextArea getChat() {
	return chat;
    }

    /**
     * @return JtextField que contiene el texto del chat
     */
    public JTextField getTexto() {
	return texto;
    }
}
