package frames;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Cliente;
import inventario.Inventario;
import juego.Pantalla;
import mensajeria.Comando;

public class MenuInventario extends JFrame {
	private JButton cancelar = new JButton("Exit");

	public MenuInventario(final Cliente cliente) {
		// Se inicializa ícono y cursor
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/frames/IconoWome.png"));
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0), "custom cursor"));

		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Gson gson = new Gson();
					cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARINVENTARIO);
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al actualizar inventario");
				}
				Pantalla.menuInventario = null;
				dispose();
			}
		});
		this.setTitle("Inventario");
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			this.setLayout(new BorderLayout());
			this.add(new Inventario(cliente.getPaquetePersonaje()));
			this.add(cancelar, BorderLayout.AFTER_LAST_LINE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falló al iniciar el inventario");

		}
		this.setBounds(600, 600, 600, 600);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setLocation(900, 140);
		this.setResizable(false);
		this.setVisible(true);
	}
}