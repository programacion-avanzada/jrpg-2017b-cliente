package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comandos.ComandosEscucha;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;

/**
 * La clase EscuchaMensajes tiene como función esuchar los mensajes que se
 * enviaran al servidor.
 */
public class EscuchaMensajes extends Thread {

    private Juego juego;
    private Cliente cliente;
    private ObjectInputStream entrada;
    private final Gson gson = new Gson();

    private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
    private Map<Integer, PaquetePersonaje> personajesConectados;

    /**
     * Constructor de EsuchaMensaje
     * @param juego juego del que se escucha el mensaje
     */
    public EscuchaMensajes(final Juego juego) {
	this.juego = juego;
	cliente = juego.getCliente();
	entrada = cliente.getEntrada();
    }

    @Override
    public void run() {

	try {

	    Paquete paquete;

	    ComandosEscucha comand;
	    // ubicacionPersonajes.put(juego.getUbicacionPersonaje().getIdPersonaje(),
	    // juego.getUbicacionPersonaje());

	    juego.setPersonajesConectados(personajesConectados);
	    juego.setUbicacionPersonajes(ubicacionPersonajes);

	    while (true) {
		String objetoLeido = (String) entrada.readObject();

		paquete = gson.fromJson(objetoLeido, Paquete.class);
		comand = (ComandosEscucha) paquete.getObjeto(Comando.NOMBREPAQUETE);
		comand.setJuego(juego);
		comand.setCadena(objetoLeido);
		comand.ejecutar();

	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor (EscuchaMensajes)");
	}
    }
    /**
     * Pide la ubicacion de los personajes
     * @return devuelve el mapa con la ubicacion de los personajes
     */

    /**
     * Pide los personajes conectados
     * @return devuelve el mapa con los personajes conectados
     */

}