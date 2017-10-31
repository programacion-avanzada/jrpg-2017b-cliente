package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import entidades.Entidad;
import interfaz.EstadoDePersonaje;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.NpcManager;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteDeNpcs;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoJuego extends Estado {

    private Entidad entidadPersonaje;
    private PaquetePersonaje paquetePersonaje;
    private Mundo mundo;
    private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
    private Map<Integer, PaquetePersonaje> personajesConectados;
    private boolean haySolicitud;
    private int tipoSolicitud;

    // NPCs
    private NpcManager npcManager;

    private final Gson gson = new Gson();

    private BufferedImage miniaturaPersonaje;

    MenuInfoPersonaje menuEnemigo;

    // Mundos
    private static final int aubenor = 1;
    private static final int aris = 2;
    private static final int eodrim = 3;
    private Map<Integer, String> mundos;

    /**
     * Instantiates a new estado juego.
     * @param juego the juego
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public EstadoJuego(final Juego juego) throws IOException {
	super(juego);
	// Inicializo el map de mundos
	this.inicializarMundos();

	mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
	paquetePersonaje = juego.getPersonaje();
	entidadPersonaje = new Entidad(juego, mundo, 64, 64, juego.getPersonaje().getNombre(), 0, 0,
		Recursos.getPersonaje().get(juego.getPersonaje().getRaza()), 150);
	// ente = new Entidad(juego, mundo, 256, 256, "Lucas Videla", 128, 128,
	// Recursos.personaje.get("Orco"), 15);
	miniaturaPersonaje = Recursos.getPersonaje().get(paquetePersonaje.getRaza()).get(5)[0];

	// Inicializo NpcManager
	npcManager = new NpcManager(juego, mundo);
	juego.setNpcManager(npcManager);

	// Si soy el primero en entrar al server, genero los npcs en mi cliente
	// y los envío al server
	// si no, traigo los npcs del server.
	if (juego.getPaquetesNpcs() == null) {
	    npcManager.spawnInicial(100);
	    PaqueteDeNpcs paqueteDeNpcs = new PaqueteDeNpcs(juego.getPaquetesNpcs(), juego.getUbicacionNpcs());
	    paqueteDeNpcs.setComando(Comando.ACTUALIZARNPCS);
	    juego.getCliente().getSalida().writeObject(gson.toJson(paqueteDeNpcs));
	} else {
	    npcManager.actualizar();
	}

	try {
	    // Le envio al servidor que me conecte al mapa y mi posicion
	    juego.getPersonaje().setComando(Comando.CONEXION);
	    juego.getPersonaje().setEstado(Estado.estadoJuego);
	    juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje(), PaquetePersonaje.class));
	    juego.getCliente().getSalida()
		    .writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor al ingresar al mundo");
	}
    }

    @Override
    public void actualizar() {
	mundo.actualizar();
	entidadPersonaje.actualizar();
    }

    @Override
    public void graficar(final Graphics g) {
	g.drawImage(Recursos.getBackground(), 0, 0, juego.getAncho(), juego.getAlto(), null);
	mundo.graficar(g);
	// entidadPersonaje.graficar(g);
	juego.getNpcManager().graficarNpcs(g);
	graficarPersonajes(g);
	mundo.graficarObstaculos(g);
	entidadPersonaje.graficarNombre(g);
	juego.getNpcManager().graficarNombresNpcs(g);

	g.drawImage(Recursos.getMarco(), 0, 0, juego.getAncho(), juego.getAlto(), null);
	EstadoDePersonaje.dibujarEstadoDePersonaje(g, 5, 5, paquetePersonaje, miniaturaPersonaje);
	g.drawImage(Recursos.getMochila(), 738, 545, 59, 52, null);
	g.drawImage(Recursos.getMenu(), 3, 562, 102, 35, null);
	g.drawImage(Recursos.getChat(), 3, 524, 102, 35, null);
	if (haySolicitud) {
	    menuEnemigo.graficar(g, tipoSolicitud);
	}

    }

    /**
     * Graficar personajes.
     * @param g the g
     */
    public void graficarPersonajes(final Graphics g) {

	if (juego.getPersonajesConectados() != null) {
	    personajesConectados = new HashMap(juego.getPersonajesConectados());
	    ubicacionPersonajes = new HashMap(juego.getUbicacionPersonajes());
	    Iterator<Integer> it = personajesConectados.keySet().iterator();
	    int key;
	    PaqueteMovimiento actual;

	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
	    while (it.hasNext()) {
		key = it.next();
		actual = ubicacionPersonajes.get(key);

		if (actual != null && actual.getIdPersonaje() != juego.getPersonaje().getId()
			&& personajesConectados.get(actual.getIdPersonaje()).getEstado() == Estado.estadoJuego) {
		    Pantalla.centerString(g,
			    new Rectangle((int) (actual.getPosX() - juego.getCamara().getxOffset() + 32),
				    (int) (actual.getPosY() - juego.getCamara().getyOffset() - 20), 0, 10),
			    personajesConectados.get(actual.getIdPersonaje()).getNombre());
		    g.drawImage(
			    Recursos.getPersonaje().get(personajesConectados.get(actual.getIdPersonaje()).getRaza())
				    .get(actual.getDireccion())[actual.getFrame()],
			    (int) (actual.getPosX() - juego.getCamara().getxOffset()),
			    (int) (actual.getPosY() - juego.getCamara().getyOffset()), 64, 64, null);
		}
	    }
	}
    }

    /**
     * Gets the personaje.
     * @return the personaje
     */
    public Entidad getPersonaje() {
	return entidadPersonaje;
    }

    /**
     * Inicializar mundos.
     */
    private void inicializarMundos() {
	mundos = new HashMap<Integer, String>();
	mundos.put(aubenor, "Aubenor");
	mundos.put(aris, "Aris");
	mundos.put(eodrim, "Eodrim");
    }

    /**
     * Gets the mundo.
     * @return the mundo
     */
    private String getMundo() {
	return mundos.get(juego.getPersonaje().getMapa());
    }

    /**
     * Setea el hay solicitud.
     * @param b the b
     * @param enemigo the enemigo
     * @param tipoSolicitud the tipo solicitud
     */
    public void setHaySolicitud(final boolean b, final Paquete enemigo, final int tipoSolicitud) {
	haySolicitud = b;
	// menu que mostrara al enemigo
	menuEnemigo = new MenuInfoPersonaje(300, 50, enemigo);
	this.tipoSolicitud = tipoSolicitud;
    }

    /**
     * Gets the hay solicitud.
     * @return the hay solicitud
     */
    public boolean getHaySolicitud() {
	return haySolicitud;
    }

    /**
     * Actualizar personaje.
     */
    public void actualizarPersonaje() {
	paquetePersonaje = juego.getPersonaje();
    }

    /**
     * Gets the menu enemigo.
     * @return the menu enemigo
     */
    public MenuInfoPersonaje getMenuEnemigo() {
	return menuEnemigo;
    }

    /**
     * Gets the tipo solicitud.
     * @return the tipo solicitud
     */
    public int getTipoSolicitud() {
	return tipoSolicitud;
    }

    @Override
    public boolean esEstadoDeJuego() {
	return true;
    }

}
