package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import juego.Juego;
import mensajeria.Comando;

public class MiChat extends JFrame {

	private JPanel contentPane;
	private JTextField texto;
	private JTextArea chat;
	private Juego juego;
	
	/**
	 * Create the frame. 
	 */
	public MiChat(final Juego juego) {
		this.juego = juego;
		setTitle("Mi Chat");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 414, 201);
		contentPane.add(scrollPane);
		
		chat = new JTextArea();
		chat.setEditable(false);
		scrollPane.setViewportView(chat);
		
		texto = new JTextField();
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				texto.requestFocus();
			}
		});
		
		//SI TOCO ENTER
		texto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!texto.getText().equals("")) {
					chat.append("Me: " + texto.getText() + "\n");
					
					// MANDO EL COMANDO PARA QUE ENVIE EL MSJ
					if(getTitle() != "Sala"){
						juego.getCliente().setAccion(Comando.TALK);
					} else {
						juego.getCliente().setAccion(Comando.CHATALL);
					}
					
					juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
					juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
					juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());
					
					synchronized (juego.getCliente()) {
						juego.getCliente().notify();
					}
					texto.setText("");
				}
				texto.requestFocus();
			}
		});
		
		//SI TOCO ENVIAR
		JButton enviar = new JButton("ENVIAR");
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!texto.getText().equals("")) {
					chat.append("Me: " + texto.getText() + "\n");
					
					// MANDO EL COMANDO PARA QUE ENVIE EL MSJ
					if(getTitle() != "Sala"){
						juego.getCliente().setAccion(Comando.TALK);
					} else {
						juego.getCliente().setAccion(Comando.CHATALL);
					}
					
					juego.getCliente().getPaqueteMensaje().setUserEmisor(juego.getPersonaje().getNombre());
					juego.getCliente().getPaqueteMensaje().setUserReceptor(getTitle());
					juego.getCliente().getPaqueteMensaje().setMensaje(texto.getText());
					
					synchronized (juego.getCliente()) {
						juego.getCliente().notify();
					}
					texto.setText("");
				}
				texto.requestFocus();
			}
		});
		enviar.setBounds(334, 225, 81, 23);
		contentPane.add(enviar);
		
		//SI CIERRO VENTANA
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				mostrarVentanaConfirmacion();
			}
		});
		texto.setBounds(10, 223, 314, 27);
		contentPane.add(texto);
		texto.setColumns(10);
	}
	
	private void mostrarVentanaConfirmacion() {
		int res = JOptionPane.showConfirmDialog(this, "¿Desea salir de la sesión de chat?", "Confirmación", JOptionPane.YES_NO_OPTION);
		if(res == JOptionPane.YES_OPTION) {
			juego.getChatsActivos().remove(getTitle());
			if(!juego.getChatsActivos().containsKey("Sala")) {
				VentanaContactos.getBotonMc().setEnabled(true);
			}
			dispose();
		}
	}
	
	public JTextArea getChat() {
		return chat;
	}

	public JTextField getTexto() {
		return texto;
	}
}
