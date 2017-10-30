package recursos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.imageio.ImageIO;

import frames.MenuCarga;
import frames.MenuMapas;
import mundo.Tile;

/**
 * Clase que contiene los recursos y los metodos de como obtenerlos
 */
public class Recursos {

    private static int eLEMENTOS = 65;
    private static int aNCHOBARRA = 345;

    private static int aNCHO; // Ancho del frame a obtener
    private static int aLTO; // Alto del frame a obtener

    // Inicio Personajes
    // Hash de imagenes para los personajes (humano, ogro, elfo)
    private static Map<String, LinkedList<BufferedImage[]>> personaje = new HashMap<>();

    private static SpriteSheet spriteHumano;
    private static LinkedList<BufferedImage[]> humano = new LinkedList<>();
    private static BufferedImage[] humanoIzq;
    private static BufferedImage[] humanoArribaIzq;
    private static BufferedImage[] humanoArriba;
    private static BufferedImage[] humanoArribaDer;
    private static BufferedImage[] humanoDer;
    private static BufferedImage[] humanoAbajoDer;
    private static BufferedImage[] humanoAbajo;
    private static BufferedImage[] humanoAbajoIzq;

    private static SpriteSheet spriteOgro;
    private static LinkedList<BufferedImage[]> orco = new LinkedList<>();
    private static BufferedImage[] orcoIzq;
    private static BufferedImage[] orcoArribaIzq;
    private static BufferedImage[] orcoArriba;
    private static BufferedImage[] orcoArribaDer;
    private static BufferedImage[] orcoDer;
    private static BufferedImage[] orcoAbajoDer;
    private static BufferedImage[] orcoAbajo;
    private static BufferedImage[] orcoAbajoIzq;

    private static SpriteSheet spriteElfo;
    private static LinkedList<BufferedImage[]> elfo = new LinkedList<>();
    private static BufferedImage[] elfoIzq;
    private static BufferedImage[] elfoArribaIzq;
    private static BufferedImage[] elfoArriba;
    private static BufferedImage[] elfoArribaDer;
    private static BufferedImage[] elfoDer;
    private static BufferedImage[] elfoAbajoDer;
    private static BufferedImage[] elfoAbajo;
    private static BufferedImage[] elfoAbajoIzq;
    // Fin Personajes

    // Entorno
    // private static SpriteSheet trees;
    // private static BufferedImage cesped;
    // private static BufferedImage roca;
    private static BufferedImage background;
    private static BufferedImage marco;
    private static BufferedImage botonMenu;
    private static BufferedImage menuEnemigo;
    // private static BufferedImage greenTree;
    // private static BufferedImage nievePiso1;
    // private static BufferedImage iceBlock;
    // Fin Entorno

    // Batalla
    private static BufferedImage barraSpells;
    private static BufferedImage estadoPersonaje;
    private static BufferedImage barraSalud;
    private static BufferedImage barraEnergia;
    private static BufferedImage barraExperiencia;
    private static BufferedImage menuBatalla;
    private static BufferedImage menuBatallaDeshabilitado;
    private static BufferedImage noItem;
    private static BufferedImage mochila;
    private static BufferedImage menu;
    private static BufferedImage chat;
    private static Map<String, BufferedImage> habilidades = new HashMap<>();
    // Fin Batalla

    // Se cargan todos los recursos del juego una sola vez al inicio

    /**
     * Carga de recursos
     * 
     * @param menuCarga
     * @throws NumberFormatException
     * @throws IOException
     */
    public static void cargar(final MenuCarga menuCarga) throws IOException {

	int elementosCargados = 0;

	aNCHO = 256;
	aLTO = 256;

	noItem = ImageIO.read(new File("recursos//noItem.png"));
	mochila = ImageIO.read(new File("recursos//mochila.png"));
	menu = ImageIO.read(new File("recursos//menu.png"));
	chat = ImageIO.read(new File("recursos//chat.png"));

	// Inicio humano
	spriteHumano = new SpriteSheet(CargadorImagen.cargarImagen("/Humano.png"));

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	humanoIzq = new BufferedImage[4];
	humanoArribaIzq = new BufferedImage[4];
	humanoArriba = new BufferedImage[4];
	humanoArribaDer = new BufferedImage[4];
	humanoDer = new BufferedImage[4];
	humanoAbajoDer = new BufferedImage[4];
	humanoAbajo = new BufferedImage[4];
	humanoAbajoIzq = new BufferedImage[4];

	for (int i = 0; i < 4; i++) {
	    humanoIzq[i] = spriteHumano.getTile(aNCHO * i, 0, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoArribaIzq[i] = spriteHumano.getTile(aNCHO * i, aLTO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoArriba[i] = spriteHumano.getTile(aNCHO * i, aLTO * 2, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoArribaDer[i] = spriteHumano.getTile(aNCHO * i, aLTO * 3, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoDer[i] = spriteHumano.getTile(aNCHO * i, aLTO * 4, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoAbajoDer[i] = spriteHumano.getTile(aNCHO * i, aLTO * 5, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoAbajo[i] = spriteHumano.getTile(aNCHO * i, aLTO * 6, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    humanoAbajoIzq[i] = spriteHumano.getTile(aNCHO * i, aLTO * 7, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	humano.add(humanoIzq);
	humano.add(humanoArribaIzq);
	humano.add(humanoArriba);
	humano.add(humanoArribaDer);
	humano.add(humanoDer);
	humano.add(humanoAbajoDer);
	humano.add(humanoAbajo);
	humano.add(humanoAbajoIzq);
	// Fin humano

	// Inicio Ogro
	spriteOgro = new SpriteSheet(CargadorImagen.cargarImagen("/Ogro.png"));

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	orcoIzq = new BufferedImage[4];
	orcoArribaIzq = new BufferedImage[4];
	orcoArriba = new BufferedImage[4];
	orcoArribaDer = new BufferedImage[4];
	orcoDer = new BufferedImage[4];
	orcoAbajoDer = new BufferedImage[4];
	orcoAbajo = new BufferedImage[4];
	orcoAbajoIzq = new BufferedImage[4];

	for (int i = 0; i < 4; i++) {
	    orcoIzq[i] = spriteOgro.getTile(aNCHO * i, 0, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoArribaIzq[i] = spriteOgro.getTile(aNCHO * i, aLTO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoArriba[i] = spriteOgro.getTile(aNCHO * i, aLTO * 2, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoArribaDer[i] = spriteOgro.getTile(aNCHO * i, aLTO * 3, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoDer[i] = spriteOgro.getTile(aNCHO * i, aLTO * 4, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoAbajoDer[i] = spriteOgro.getTile(aNCHO * i, aLTO * 5, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoAbajo[i] = spriteOgro.getTile(aNCHO * i, aLTO * 6, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    orcoAbajoIzq[i] = spriteOgro.getTile(aNCHO * i, aLTO * 7, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	orco.add(orcoIzq);
	orco.add(orcoArribaIzq);
	orco.add(orcoArriba);
	orco.add(orcoArribaDer);
	orco.add(orcoDer);
	orco.add(orcoAbajoDer);
	orco.add(orcoAbajo);
	orco.add(orcoAbajoIzq);

	// Fin Ogro

	// Inicio Elfo
	spriteElfo = new SpriteSheet(CargadorImagen.cargarImagen("/elfo2.png"));

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	elfoIzq = new BufferedImage[4];
	elfoArribaIzq = new BufferedImage[4];
	elfoArriba = new BufferedImage[4];
	elfoArribaDer = new BufferedImage[4];
	elfoDer = new BufferedImage[4];
	elfoAbajoDer = new BufferedImage[4];
	elfoAbajo = new BufferedImage[4];
	elfoAbajoIzq = new BufferedImage[4];

	for (int i = 0; i < 4; i++) {
	    elfoIzq[i] = spriteElfo.getTile(aNCHO * i, 0, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoArribaIzq[i] = spriteElfo.getTile(aNCHO * i, aLTO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoArriba[i] = spriteElfo.getTile(aNCHO * i, aLTO * 2, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoArribaDer[i] = spriteElfo.getTile(aNCHO * i, aLTO * 3, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoDer[i] = spriteElfo.getTile(aNCHO * i, aLTO * 4, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoAbajoDer[i] = spriteElfo.getTile(aNCHO * i, aLTO * 5, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoAbajo[i] = spriteElfo.getTile(aNCHO * i, aLTO * 6, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < 4; i++) {
	    elfoAbajoIzq[i] = spriteElfo.getTile(aNCHO * i, aLTO * 7, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	elfo.add(elfoIzq);
	elfo.add(elfoArribaIzq);
	elfo.add(elfoArriba);
	elfo.add(elfoArribaDer);
	elfo.add(elfoDer);
	elfo.add(elfoAbajoDer);
	elfo.add(elfoAbajo);
	elfo.add(elfoAbajoIzq);

	// Fin Elfo

	// Agrego los pj al hash
	personaje.put("Humano", humano);
	personaje.put("Orco", orco);
	personaje.put("Elfo", elfo);

	// Inicio Entorno
	// cesped = CargadorImagen.cargarImagen("/Cesped.png");
	// actualizarBarraDeCarga(++elementosCargados, menuCarga);
	// roca = CargadorImagen.cargarImagen("/rock.png");
	// actualizarBarraDeCarga(++elementosCargados, menuCarga);
	background = CargadorImagen.cargarImagen("/background.jpg");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	marco = CargadorImagen.cargarImagen("/marco.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	botonMenu = CargadorImagen.cargarImagen("/botonMenu.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	menuEnemigo = CargadorImagen.cargarImagen("/MenuEnemigo.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	// trees = new SpriteSheet(CargadorImagen.cargarImagen("/trees.png"));
	// actualizarBarraDeCarga(++elementosCargados, menuCarga);
	// greenTree = trees.getTile(0, 0, 42, 50);
	// actualizarBarraDeCarga(++elementosCargados, menuCarga);
	// nievePiso1 = CargadorImagen.cargarImagen("/nieve piso.png");
	// actualizarBarraDeCarga(++elementosCargados, menuCarga);
	// iceBlock = CargadorImagen.cargarImagen("/nieve cubo.png");

	// Mapa
	if (MenuMapas.numberMap == 1) {
	    SpriteSheet mapaAubenor = new SpriteSheet(CargadorImagen.cargarImagen("/Aubenor.png"));
	    Tile.aubenor = new Tile[81];
	    boolean[][] solidezAubenor = { {true, true, false, true, false, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, false, false, false, false, false, false, false, true, true },
		    {false, false, false, false, false, false, false, false, true, true },
		    {false, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true } };
	    for (int y = 0; y < 8; y++) {
		for (int x = 0; x < 10; x++) {
		    Tile.aubenor[y * 10 + x + 1] = new Tile(mapaAubenor.getTile(x * 64, y * 64, 64, 64), y * 10 + x + 1,
			    solidezAubenor[y][x], 64, 64);
		}
	    }
	} else {
	    SpriteSheet mapaAris = new SpriteSheet(CargadorImagen.cargarImagen("/Aris.png"));
	    Tile.aris = new Tile[81];
	    boolean[][] solidezAris = { {true, false, false, false, false, false, false, true, true, true },
		    {false, false, false, false, false, false, false, false, true, true },
		    {false, false, false, false, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {false, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true } };
	    for (int y = 0; y < 8; y++) {
		for (int x = 0; x < 10; x++) {
		    Tile.aris[y * 10 + x + 1] = new Tile(mapaAris.getTile(x * 64, y * 64, 64, 64), y * 10 + x + 1,
			    solidezAris[y][x], 64, 64);
		}
	    }
	}

	// Fin Entorno

	// Inicio Batalla
	barraSpells = CargadorImagen.cargarImagen("/BarraSpells.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	estadoPersonaje = CargadorImagen.cargarImagen("/EstadoPersonaje.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	barraSalud = CargadorImagen.cargarImagen("/BarraDeSalud.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	barraEnergia = CargadorImagen.cargarImagen("/BarraDeEnergia.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	barraExperiencia = CargadorImagen.cargarImagen("/BarraDeExperiencia.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Golpe Level", CargadorImagen.cargarImagen("/Golpe Level.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Ataque Bosque", CargadorImagen.cargarImagen("/Ataque Bosque.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Golpe Defensa", CargadorImagen.cargarImagen("/Golpe Defensa.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Mordisco de Vida", CargadorImagen.cargarImagen("/Mordisco de Vida.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Incentivar", CargadorImagen.cargarImagen("/Incentivar.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Golpe Fatal", CargadorImagen.cargarImagen("/Golpe Fatal.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Ataque Doble", CargadorImagen.cargarImagen("/Ataque Doble.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Aumentar Defensa", CargadorImagen.cargarImagen("/Aumentar Defensa.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Ignorar Defensa", CargadorImagen.cargarImagen("/Ignorar Defensa.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Bola de Fuego", CargadorImagen.cargarImagen("/Bola de Fuego.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Curar Aliado", CargadorImagen.cargarImagen("/Curar Aliado.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Robar Energia y Salud", CargadorImagen.cargarImagen("/Robar Energia y Salud.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Golpe Critico", CargadorImagen.cargarImagen("/Golpe Critico.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Aumentar Evasion", CargadorImagen.cargarImagen("/Aumentar Evasion.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Robar", CargadorImagen.cargarImagen("/Robar.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	habilidades.put("Ser Energizado", CargadorImagen.cargarImagen("/Ser Energizado.png"));
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	menuBatalla = CargadorImagen.cargarImagen("/MenuBatalla.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	menuBatallaDeshabilitado = CargadorImagen.cargarImagen("/MenuBatallaDeshabilitado.png");
	actualizarBarraDeCarga(++elementosCargados, menuCarga);
	// Fin Batalla
    }

    /**
     * Actualiza la barra de carga
     * @param elementosCargados
     * @param menuCarga
     */
    private static void actualizarBarraDeCarga(int elementosCargados, MenuCarga menuCarga) {
	menuCarga.setBarraCargando(elementosCargados * aNCHOBARRA / eLEMENTOS);
    }

    /**
     * @return the orco
     */
    public static LinkedList<BufferedImage[]> getOrco() {
	return orco;
    }

    /**
     * @return the background
     */
    public static BufferedImage getBackground() {
	return background;
    }

    /**
     * @return the marco
     */
    public static BufferedImage getMarco() {
	return marco;
    }

    /**
     * @return the botonMenu
     */
    public static BufferedImage getBotonMenu() {
	return botonMenu;
    }

    /**
     * @return the menuEnemigo
     */
    public static BufferedImage getMenuEnemigo() {
	return menuEnemigo;
    }

    /**
     * @return the estadoPersonaje
     */
    public static BufferedImage getEstadoPersonaje() {
	return estadoPersonaje;
    }

    /**
     * @return the barraSalud
     */
    public static BufferedImage getBarraSalud() {
	return barraSalud;
    }

    /**
     * @return the barraEnergia
     */
    public static BufferedImage getBarraEnergia() {
	return barraEnergia;
    }

    /**
     * @return the barraExperiencia
     */
    public static BufferedImage getBarraExperiencia() {
	return barraExperiencia;
    }

    /**
     * @return the menuBatalla
     */
    public static BufferedImage getMenuBatalla() {
	return menuBatalla;
    }

    /**
     * @return the menuBatallaDeshabilitado
     */
    public static BufferedImage getMenuBatallaDeshabilitado() {
	return menuBatallaDeshabilitado;
    }

    /**
     * @return the noItem
     */
    public static BufferedImage getNoItem() {
	return noItem;
    }

    /**
     * @return the mochila
     */
    public static BufferedImage getMochila() {
	return mochila;
    }

    /**
     * @return the menu
     */
    public static BufferedImage getMenu() {
	return menu;
    }

    /**
     * @return the chat
     */
    public static BufferedImage getChat() {
	return chat;
    }

    /**
     * @return the barraSpells
     */
    public static BufferedImage getBarraSpells() {
	return barraSpells;
    }

    /**
     * @return the habilidades
     */
    public static Map<String, BufferedImage> getHabilidades() {
	return habilidades;
    }

    /**
     * @return the personaje
     */
    public static Map<String, LinkedList<BufferedImage[]>> getPersonaje() {
        return personaje;
    }
}

