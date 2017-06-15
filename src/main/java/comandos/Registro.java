package comandos;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.JsonSyntaxException;

import frames.MenuCreacionPj;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class Registro extends ComandosCliente {

	@Override
	public void ejecutar() {
		synchronized (this) {
			
		
			Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
			if (paquete.getMensaje().equals(Paquete.msjExito)) {

				// Abro el menu para la creaci�n del personaje
				MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(cliente, cliente.getPaquetePersonaje());

				menuCreacionPJ.setVisible(true);
				// Espero a que el usuario cree el personaje

				// Recibo el paquete personaje con los datos (la id incluida)

				try {
					wait();

					// Le envio los datos al servidor
					cliente.getPaquetePersonaje().setComando(Comando.CREACIONPJ);
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
					JOptionPane.showMessageDialog(null, "Registro exitoso.");
					cliente.setPaquetePersonaje(gson.fromJson((String) cliente.getEntrada().readObject(), PaquetePersonaje.class));
				} catch (JsonSyntaxException | ClassNotFoundException | IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Indico que el usuario ya inicio sesion
				cliente.getPaqueteUsuario().setInicioSesion(true);

			} else {
				if (paquete.getMensaje().equals(Paquete.msjFracaso)) {
					JOptionPane.showMessageDialog(null, "No se pudo registrar.");
				}
				// El usuario no pudo iniciar sesión
				cliente.getPaqueteUsuario().setInicioSesion(false);
			}

		}
	}

}
