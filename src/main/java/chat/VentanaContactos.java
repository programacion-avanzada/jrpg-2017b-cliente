package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import juego.Juego;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;

/**
 * Class VentanaContactos.
 */
public class VentanaContactos extends JFrame {

    private JPanel contentPane;

    private DefaultListModel<String> modelo = new DefaultListModel<String>();

    private static JList<String> list = new JList<String>();

    private static JButton botonMc;

    private JLabel background;

    /**
     * Instantiates a new ventana contactos.
     * @param juego the juego
     */
    public VentanaContactos(final Juego juego) {
	setResizable(false);
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 327, 273);
	setLocationRelativeTo(null);
	setTitle("Usuarios");

	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 11, 299, 188);
	contentPane.add(scrollPane);

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(final WindowEvent arg0) {
		Pantalla.ventContac = null;
		dispose();
	    }
	});

	botonMc = new JButton("Multichat");
	botonMc.setIcon(new ImageIcon("recursos//multichatButton.png"));
	botonMc.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
		if (modelo.size() != 0) {
		    if (!juego.getChatsActivos().containsKey("Sala")) {
			MiChat chat = new MiChat(juego);
			juego.getChatsActivos().put("Sala", chat);
			chat.setTitle("Sala");
			chat.setVisible(true);
			botonMc.setEnabled(false);
		    }
		}
	    }
	});
	botonMc.setBounds(119, 208, 89, 23);
	contentPane.add(botonMc);

	// Cargo la lista de contactos
	actualizarLista(juego);
	// Pregunto si la ventana sala esta abierta y cancelo el boton multichat
	if (juego.getChatsActivos().containsKey("Sala")) {
	    botonMc.setEnabled(false);
	} else {
	    botonMc.setEnabled(true);
	}

	list.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(final MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
		    if (list.getSelectedValue() != null) {
			if (!juego.getChatsActivos().containsKey(list.getSelectedValue())) {
			    if (juego.getCliente() != null) {
				MiChat chat = new MiChat(juego);
				juego.getChatsActivos().put(list.getSelectedValue(), chat);
				chat.setTitle(list.getSelectedValue());
				chat.setVisible(true);
			    }
			}
		    }
		}
	    }
	});

	list.setModel(modelo);
	scrollPane.setViewportView(list);

	background = new JLabel(new ImageIcon("recursos//background.jpg"));
	background.setBounds(-16, 0, 352, 254);
	contentPane.add(background);
    }

    /**
     * Actualizar lista.
     * @param juego the juego
     */
    private void actualizarLista(final Juego juego) {
	if (juego.getCliente() != null) {
	    synchronized (juego.getCliente()) {
		modelo.removeAllElements();
		if (juego.getPersonajesConectados() != null) {
		    for (Map.Entry<Integer, PaquetePersonaje> personaje : juego.getPersonajesConectados().entrySet()) {
			modelo.addElement(personaje.getValue().getNombre());
		    }
		    modelo.removeElement(juego.getPersonaje().getNombre());
		    list.setModel(modelo);
		}
	    }
	}
    }

    /**
     * Gets the list.
     * @return the list
     */
    public static JList<String> getList() {
	return list;
    }

    /**
     * Gets the boton mc.
     * @return the boton mc
     */
    public static JButton getBotonMc() {
	return botonMc;
    }
}
