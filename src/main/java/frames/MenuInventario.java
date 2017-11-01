package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.google.gson.Gson;

import cliente.Cliente;
import inventario.Inventario;
import juego.Pantalla;
import mensajeria.Comando;

/**
 * Menu inventario para ver los items
 * que el personaje tiene
 */
public class MenuInventario extends JFrame {
    private static final int Y_LOCACION = 140;
    private static final int X_LOCACION = 900;
    private static final int TAMANIO = 600;
    private JButton cancelar = new JButton("Exit");

    /**
     * @param cliente cliente que ingresa al menu Inventario
     */
    public MenuInventario(final Cliente cliente) {
	cancelar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(final ActionEvent e) {
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
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	try {
	    this.setLayout(new BorderLayout());
	    this.add(new Inventario(cliente.getPaquetePersonaje()));
	    this.add(cancelar, BorderLayout.AFTER_LAST_LINE);
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Falló al iniciar el inventario");

	}
	this.setBounds(TAMANIO, TAMANIO, TAMANIO, TAMANIO);
	this.pack();
	this.setLocationRelativeTo(null);
	this.setLocation(X_LOCACION, Y_LOCACION);
	this.setResizable(false);
	this.setVisible(true);
    }
}