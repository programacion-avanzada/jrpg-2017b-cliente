package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Casta;
import dominio.NonPlayableCharacter;
import dominio.Personaje;
import interfaz.EstadoDeNpc;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaqueteNpc;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoBatallaNpc extends Estado {

    private Mundo mundo;
    private Personaje personaje;
    private NonPlayableCharacter enemigo;

    private int[] posMouse;
    private PaquetePersonaje paquetePersonaje;
    private PaqueteNpc paqueteEnemigo;
    private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
    private boolean miTurno;
    private Timer timer;

    private boolean haySpellSeleccionada;
    private boolean seRealizoAccion;

    private Gson gson = new Gson();

    private BufferedImage miniaturaPersonaje;
    private BufferedImage miniaturaEnemigo;

    private MenuBatalla menuBatalla;

    /**
     * Instantiates a new estado batalla npc.
     * @param juego the juego
     * @param paqueteBatalla the paquete batalla
     */
    public EstadoBatallaNpc(final Juego juego, final PaqueteBatalla paqueteBatalla) {
	// El paquete de batalla va a tener la id del jugador y la id del npc
	// pero negativa

	super(juego);
	mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
	miTurno = true;

	paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
	paqueteEnemigo = juego.getPaquetesNpcs().get(paqueteBatalla.getIdEnemigo() * -1);

	crearPersonajes();

	menuBatalla = new MenuBatalla(miTurno, personaje);

	miniaturaEnemigo = Recursos.getPersonaje().get(paqueteEnemigo.getRaza()).get(5)[0];
	miniaturaPersonaje = Recursos.getPersonaje().get(personaje.getNombreRaza()).get(5)[0];

	paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
	paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
	paqueteFinalizarBatalla.setIdEnemigo(paqueteEnemigo.getId() * -1);

	timer = new Timer();

	// por defecto batalla perdida
	juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENUPERDERBATALLA);

	// limpio la accion del mouse
	juego.getHandlerMouse().setNuevoClick(false);

    }

    @Override
    public void actualizar() {

	juego.getCamara().setxOffset(-350);
	juego.getCamara().setyOffset(150);

	seRealizoAccion = false;
	haySpellSeleccionada = false;

	if (miTurno) {

	    if (juego.getHandlerMouse().getNuevoClick()) {
		posMouse = juego.getHandlerMouse().getPosMouse();

		if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    personaje.habilidadRaza1(enemigo);
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    personaje.habilidadRaza2(enemigo);
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    personaje.habilidadCasta1(enemigo);
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    personaje.habilidadCasta2(enemigo);
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
			if (personaje.puedeAtacar()) {
			    seRealizoAccion = true;
			    personaje.habilidadCasta3(enemigo);
			}
			haySpellSeleccionada = true;
		    }

		    if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 6) {
			seRealizoAccion = true;
			personaje.serEnergizado(10);
			haySpellSeleccionada = true;
		    }
		}

		if (haySpellSeleccionada && seRealizoAccion) {
		    if (!enemigo.estaVivo()) // EL NPC MUERE
		    {
			juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
				MenuInfoPersonaje.MENUGANARBATALLA);
			if (personaje.ganarExperiencia(enemigo.getNivel() * 40)) {
			    juego.getPersonaje().setNivel(personaje.getNivel());
			    juego.getPersonaje().setPuntosSkill(personaje.getPuntosSkill() + 3);
			    juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
				    MenuInfoPersonaje.MENUSUBIRNIVEL);
			}

			paqueteFinalizarBatalla.setGanadorBatalla(juego.getPersonaje().getId());

			juego.getPersonaje().setEstado(Estado.estadoJuego);
			finalizarBatalla();
			Estado.setEstado(juego.getEstadoJuego());

		    } else {
			// BATALLAR VS NPC
			setMiTurno(false);
			timer.schedule(new TimerTask() {

			    @Override
			    public void run() {
				enemigo.jugarTurno(personaje);
				if (!personaje.estaVivo()) // EL PERSONAJE MUERE
				{
				    juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
					    MenuInfoPersonaje.MENUPERDERBATALLA);
				    paqueteFinalizarBatalla.setGanadorBatalla(paqueteEnemigo.getId());

				    juego.getPersonaje().setEstado(Estado.estadoJuego);
				    finalizarBatalla();
				    Estado.setEstado(juego.getEstadoJuego());
				}

				setMiTurno(true);
			    }
			}, 2 * 1000);

		    }
		} else if (haySpellSeleccionada && !seRealizoAccion) {
		    JOptionPane.showMessageDialog(null,
			    "No posees la energía suficiente para realizar esta habilidad.");
		}

		juego.getHandlerMouse().setNuevoClick(false);
	    }
	}

    }

    @Override
    public void graficar(final Graphics g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
	mundo.graficar(g);

	g.drawImage(Recursos.getPersonaje().get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
	g.drawImage(Recursos.getPersonaje().get(paqueteEnemigo.getRaza()).get(7)[0], 550, 75, 256, 256, null);

	mundo.graficarObstaculos(g);
	menuBatalla.graficar(g);

	g.setColor(Color.GREEN);

	EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
	EstadoDeNpc.dibujarEstadoDeNpc(g, 550, 5, enemigo, miniaturaEnemigo);
    }

    /**
     * Crear personajes.
     */
    private void crearPersonajes() {
	String nombre = paquetePersonaje.getNombre();
	int salud = paquetePersonaje.getSaludTope();
	int energia = paquetePersonaje.getEnergiaTope();
	int fuerza = paquetePersonaje.getFuerza();
	int destreza = paquetePersonaje.getDestreza();
	int inteligencia = paquetePersonaje.getInteligencia();
	int experiencia = paquetePersonaje.getExperiencia();
	int nivel = paquetePersonaje.getNivel();
	int id = paquetePersonaje.getId();

	Casta casta = null;
	try {
	    casta = (Casta) Class.forName("dominio" + "." + paquetePersonaje.getCasta()).newInstance();
	    personaje = (Personaje) Class.forName("dominio" + "." + paquetePersonaje.getRaza())
		    .getConstructor(String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			    Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)
		    .newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    JOptionPane.showMessageDialog(null, "Error al crear la batalla");
	}

	try {
	    System.out.println("dominio" + ".Npc" + paqueteEnemigo.getCasta());
	    enemigo = (NonPlayableCharacter) Class.forName("dominio" + ".Npc" + paqueteEnemigo.getCasta())
		    .getConstructor(String.class, Integer.TYPE)
		    .newInstance(paqueteEnemigo.getNombre(), paqueteEnemigo.getNivel());
	} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	    JOptionPane.showMessageDialog(null, "Error al crear la batalla");
	}
    }

    /**
     * Finalizar batalla.
     */
    private void finalizarBatalla() {
	try {
	    juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));

	    // si gana el humano
	    if (paqueteFinalizarBatalla.getGanadorBatalla() == paquetePersonaje.getId()) {
		paquetePersonaje.setSaludTope(personaje.getSaludTope());
		paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
		paquetePersonaje.setNivel(personaje.getNivel());
		paquetePersonaje.setExperiencia(personaje.getExperiencia());
		paquetePersonaje.setDestreza(personaje.getDestreza());
		paquetePersonaje.setFuerza(personaje.getFuerza());
		paquetePersonaje.setInteligencia(personaje.getInteligencia());

		paquetePersonaje.setPuntosSkill(personaje.getPuntosSkill());
		paquetePersonaje.removerBonus();

		paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
		juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
	    }

	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
	}
    }

    /**
     * Gets the paquete personaje.
     * @return the paquete personaje
     */
    public PaquetePersonaje getPaquetePersonaje() {
	return paquetePersonaje;
    }

    /**
     * Gets the paquete enemigo.
     * @return the paquete enemigo
     */
    public PaqueteNpc getPaqueteEnemigo() {
	return paqueteEnemigo;
    }

    /**
     * Sets the mi turno.
     * @param b the new mi turno
     */
    public void setMiTurno(final boolean b) {
	miTurno = b;
	menuBatalla.setHabilitado(b);
	juego.getHandlerMouse().setNuevoClick(false);
    }

    /**
     * Gets the personaje.
     * @return the personaje
     */
    public Personaje getPersonaje() {
	return personaje;
    }

    /**
     * Gets the enemigo.
     * @return the enemigo
     */
    public NonPlayableCharacter getEnemigo() {
	return enemigo;
    }

    @Override
    public boolean esEstadoDeJuego() {
	return false;
    }
}