package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.MadreDeTodo;
import dominio.NonPlayableCharacter;
import dominio.Orco;
import dominio.Peleable;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteNPC;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoBatalla extends Estado {

	private Mundo mundo;
	private Personaje personaje;
	private MadreDeTodo enemigo;
	private int[] posMouse;
	private PaquetePersonaje paquetePersonaje;
	private Paquete paqueteEnemigo;
	private PaqueteAtacar paqueteAtacar;
	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	private boolean miTurno;

	private boolean haySpellSeleccionada;
	private boolean seRealizoAccion;

	private Gson gson = new Gson();

	private BufferedImage miniaturaPersonaje;
	private BufferedImage miniaturaEnemigo;

	private MenuBatalla menuBatalla;
	
	private boolean enemigoEsNPC;

	public EstadoBatalla(Juego juego, PaqueteBatalla paqueteBatalla) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteBatalla.isMiTurno();

		paquetePersonaje = juego.getPersonajesConectados().get(paqueteBatalla.getId());
		if(paqueteBatalla.enemigoEsNPC())
		{
			this.enemigoEsNPC = true;
			paqueteEnemigo = (PaqueteNPC)juego.getNpcsSpawneados().get(paqueteBatalla.getIdEnemigo());
			crearPersonajeYNPC();
			miniaturaEnemigo = Recursos.enemigoNPC;
		}
		else
		{
			paqueteEnemigo = (PaquetePersonaje)juego.getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());
			crearPersonajes();
			miniaturaEnemigo = Recursos.personaje.get(((Personaje)enemigo).getNombreRaza()).get(5)[0];
		}		

		menuBatalla = new MenuBatalla(miTurno, personaje);

		miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];

		paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
		paqueteFinalizarBatalla.setId(personaje.getId());
		paqueteFinalizarBatalla.setIdEnemigo(enemigo.getId());

		// por defecto batalla perdida
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuPerderBatalla);

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
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadRaza1((Peleable)enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadRaza2((Peleable)enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadCasta1((Peleable)enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadCasta2((Peleable)enemigo);
						}
						haySpellSeleccionada = true;
					}

					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
						if(personaje.puedeAtacar()){
							seRealizoAccion = true;
							personaje.habilidadCasta3((Peleable)enemigo);
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
					if (!enemigo.estaVivo()) {
						juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuGanarBatalla);
						if(personaje.ganarExperiencia(enemigo.getNivel() * 40)){
							juego.getPersonaje().setNivel(personaje.getNivel());
							juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.menuSubirNivel);
						}
						paqueteFinalizarBatalla.setGanadorBatalla(juego.getPersonaje().getId());
						finalizarBatalla();
						Estado.setEstado(juego.getEstadoJuego());
						
					} else {
						if(enemigoEsNPC)
						{
							paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), ((PaqueteNPC)paqueteEnemigo).getId(), personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), 100, personaje.getDefensa(), enemigo.getDefensa(), personaje.getCasta().getProbabilidadEvitarDaño(), 0);
							enemigo.atacar(personaje); // En este punto, el atacante ya le pego al NPC. Por lo tanto, mando al NPC a atacarlo a él. //
							paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), ((PaquetePersonaje)paqueteEnemigo).getId(), personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), ((Personaje)enemigo).getEnergia(), personaje.getDefensa(), ((Personaje)enemigo).getDefensa(), personaje.getCasta().getProbabilidadEvitarDaño(), ((Personaje)enemigo).getCasta().getProbabilidadEvitarDaño());
						}
						else
						{
							paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), ((PaquetePersonaje)paqueteEnemigo).getId(), personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), ((Personaje)enemigo).getEnergia(), personaje.getDefensa(), ((Personaje)enemigo).getDefensa(), personaje.getCasta().getProbabilidadEvitarDaño(), ((Personaje)enemigo).getCasta().getProbabilidadEvitarDaño());
							miTurno = false;
						}
						
						enviarAtaque(paqueteAtacar);
						menuBatalla.setHabilitado(false);
					}
				} else if(haySpellSeleccionada && !seRealizoAccion){
					JOptionPane.showMessageDialog(null, "No posees la energía suficiente para realizar esta habilidad.");
				}

				juego.getHandlerMouse().setNuevoClick(false);
			}
		}

	}


	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
		mundo.graficar(g);

		if(enemigoEsNPC)
		{
			g.drawImage(Recursos.enemigoNPC, 550, 75, 256, 256, null);
		}
		else
		{
			g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
		}

		mundo.graficarObstaculos(g);
		menuBatalla.graficar(g);

		g.setColor(Color.GREEN);

		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);
		

	}

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
			personaje = (Personaje) Class.forName("dominio" + "." + paquetePersonaje.getRaza()).getConstructor(String.class, Integer.TYPE, 
					Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).
					newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
							experiencia, nivel, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}
		



		nombre = ((PaquetePersonaje)paqueteEnemigo).getNombre();
		salud = ((PaquetePersonaje)paqueteEnemigo).getSaludTope();
		energia = ((PaquetePersonaje)paqueteEnemigo).getEnergiaTope();
		fuerza = ((PaquetePersonaje)paqueteEnemigo).getFuerza();
		destreza = ((PaquetePersonaje)paqueteEnemigo).getDestreza();
		inteligencia = ((PaquetePersonaje)paqueteEnemigo).getInteligencia();
		experiencia = ((PaquetePersonaje)paqueteEnemigo).getExperiencia();
		nivel = ((PaquetePersonaje)paqueteEnemigo).getNivel();
		id = ((PaquetePersonaje)paqueteEnemigo).getId();

		casta = null;
		if (((PaquetePersonaje)paqueteEnemigo).getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else if (((PaquetePersonaje)paqueteEnemigo).getCasta().equals("Hechicero")) {
			casta = new Hechicero();
		} else if (((PaquetePersonaje)paqueteEnemigo).getCasta().equals("Asesino")) {
			casta = new Asesino();
		}

		if (((PaquetePersonaje)paqueteEnemigo).getRaza().equals("Humano")) {
			enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		} else if (((PaquetePersonaje)paqueteEnemigo).getRaza().equals("Orco")) {
			enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		} else if (((PaquetePersonaje)paqueteEnemigo).getRaza().equals("Elfo")) {
			enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
					experiencia, nivel, id);
		}
	}
	
	private void crearPersonajeYNPC()
	{
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
			personaje = (Personaje) Class.forName("dominio" + "." + paquetePersonaje.getRaza()).getConstructor(String.class, Integer.TYPE, 
					Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Casta.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).
					newInstance(nombre, salud, energia, fuerza, destreza, inteligencia, casta,
							experiencia, nivel, id);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			JOptionPane.showMessageDialog(null, "Error al crear la batalla");
		}
		
		nombre = ((PaqueteNPC)paqueteEnemigo).getNombre();
		nivel = ((PaqueteNPC)paqueteEnemigo).getNivel();
		
		enemigo = new NonPlayableCharacter(nombre, nivel, NonPlayableCharacter.DIFICULTADALEATORIA);		
	}

	public void enviarAtaque(PaqueteAtacar paqueteAtacar) {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteAtacar));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
		}
	}

	private void finalizarBatalla() {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));

			paquetePersonaje.setSaludTope(personaje.getSaludTope());
			paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
			paquetePersonaje.setNivel(personaje.getNivel());
			paquetePersonaje.setExperiencia(personaje.getExperiencia());
			paquetePersonaje.setDestreza(personaje.getDestreza());
			paquetePersonaje.setFuerza(personaje.getFuerza());
			paquetePersonaje.setInteligencia(personaje.getInteligencia());
			paquetePersonaje.removerBonus();
			
			if(enemigoEsNPC)
			{
				((PaqueteNPC)paqueteEnemigo).setSaludTope(enemigo.getSaludTope());
				((PaqueteNPC)paqueteEnemigo).setEnergiaTope(enemigo.getEnergiaTope());
				((PaqueteNPC)paqueteEnemigo).setNivel(enemigo.getNivel());
				((PaqueteNPC)paqueteEnemigo).setFuerza(enemigo.getFuerza());
			}
			else
			{
				((PaquetePersonaje)paqueteEnemigo).setSaludTope(enemigo.getSaludTope());
				((PaquetePersonaje)paqueteEnemigo).setEnergiaTope(enemigo.getEnergiaTope());
				((PaquetePersonaje)paqueteEnemigo).setNivel(enemigo.getNivel());
				((PaquetePersonaje)paqueteEnemigo).setExperiencia(((Personaje)enemigo).getExperiencia());
				((PaquetePersonaje)paqueteEnemigo).setDestreza(((Personaje)enemigo).getDestreza());
				((PaquetePersonaje)paqueteEnemigo).setFuerza(((Personaje)enemigo).getFuerza());
				((PaquetePersonaje)paqueteEnemigo).setInteligencia(((Personaje)enemigo).getInteligencia());
				((PaquetePersonaje)paqueteEnemigo).removerBonus();
			}


			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);

			juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigo));
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
		}
	}

	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	public Paquete getPaqueteEnemigo() {
		return paqueteEnemigo;
	}

	public void setMiTurno(boolean b) {
		miTurno = b;
		menuBatalla.setHabilitado(b);
		juego.getHandlerMouse().setNuevoClick(false);
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public Peleable getEnemigo() {
		return enemigo;
	}
	
	@Override
	public boolean esEstadoDeJuego() {
		return false;
	}
}