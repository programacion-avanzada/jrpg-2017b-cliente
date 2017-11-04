package comandos;

import java.util.Map;

import javax.swing.DefaultListModel;

import chat.VentanaContactos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaquetePersonaje;


/**
 * Clase conexion
 *
 */
public class Conexion extends ComandosEscucha {

    @Override
    public void ejecutar() {
	final PaqueteDePersonajes pdp = gson.fromJson(cadenaLeida, PaqueteDePersonajes.class);
	juego.setPersonajesConectados(pdp.getPersonajes());
	actualizarLista(pdp);
    }

    /**
     * Actualizar lista.
     * @param pdp the pdp
     */
    private void actualizarLista(final PaqueteDePersonajes pdp) {
	final DefaultListModel<String> modelo = new DefaultListModel<String>();
	VentanaContactos.getList().removeAll();
	for (final Map.Entry<Integer, PaquetePersonaje> personaje : pdp.getPersonajes().entrySet()) {
	    modelo.addElement(personaje.getValue().getNombre());
	}
	modelo.removeElement(juego.getPersonaje().getNombre());
	VentanaContactos.getList().setModel(modelo);
    }
}
