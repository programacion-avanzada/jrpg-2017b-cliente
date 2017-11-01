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

    private static final int TAM_FILAS_SOLIDEZ = 10;
    private static final int TAM_COLUMNAS_SOLIDEZ = 8;
    private static final int ALTO_TILE = 64;
    private static final int ANCHO_TILE = 64;
    private static final int TAM_SOLIDEZ_MAPA = 81;
    private static final int SPRITE_ABAJO_IZQ = 7;
    private static final int SPRITE_ABAJO = 6;
    private static final int SPRITE_ABAJO_DER = 5;
    private static final int SPRITE_DER = 4;
    private static final int SPRITE_ARRIBA_DER = 3;
    private static final int SPRITE_ARRIBA = 2;
    private static int eLEMENTOS = 65;
    private static int aNCHOBARRA = 345;
    private static final int TAM_BUFFER_IMAGE = SPRITE_DER;

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
     * @param menuCarga ventana del Menu de carga
     * @throws NumberFormatException Exception por el formato de numero
     * @throws IOException del cargar imagen
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

	humanoIzq = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoArribaIzq = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoArriba = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoArribaDer = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoDer = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoAbajoDer = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoAbajo = new BufferedImage[TAM_BUFFER_IMAGE];
	humanoAbajoIzq = new BufferedImage[TAM_BUFFER_IMAGE];

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoIzq[i] = spriteHumano.getTile(aNCHO * i, 0, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoArribaIzq[i] = spriteHumano.getTile(aNCHO * i, aLTO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoArriba[i] = spriteHumano.getTile(aNCHO * i, aLTO * SPRITE_ARRIBA, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoArribaDer[i] = spriteHumano.getTile(aNCHO * i, aLTO * SPRITE_ARRIBA_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoDer[i] = spriteHumano.getTile(aNCHO * i, aLTO * SPRITE_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoAbajoDer[i] = spriteHumano.getTile(aNCHO * i, aLTO * SPRITE_ABAJO_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoAbajo[i] = spriteHumano.getTile(aNCHO * i, aLTO * SPRITE_ABAJO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    humanoAbajoIzq[i] = spriteHumano.getTile(aNCHO * i, aLTO * SPRITE_ABAJO_IZQ, aNCHO, aLTO);
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

	orcoIzq = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoArribaIzq = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoArriba = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoArribaDer = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoDer = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoAbajoDer = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoAbajo = new BufferedImage[TAM_BUFFER_IMAGE];
	orcoAbajoIzq = new BufferedImage[TAM_BUFFER_IMAGE];

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoIzq[i] = spriteOgro.getTile(aNCHO * i, 0, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoArribaIzq[i] = spriteOgro.getTile(aNCHO * i, aLTO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoArriba[i] = spriteOgro.getTile(aNCHO * i, aLTO * SPRITE_ARRIBA, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoArribaDer[i] = spriteOgro.getTile(aNCHO * i, aLTO * SPRITE_ARRIBA_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoDer[i] = spriteOgro.getTile(aNCHO * i, aLTO * SPRITE_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoAbajoDer[i] = spriteOgro.getTile(aNCHO * i, aLTO * SPRITE_ABAJO_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoAbajo[i] = spriteOgro.getTile(aNCHO * i, aLTO * SPRITE_ABAJO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    orcoAbajoIzq[i] = spriteOgro.getTile(aNCHO * i, aLTO * SPRITE_ABAJO_IZQ, aNCHO, aLTO);
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

	elfoIzq = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoArribaIzq = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoArriba = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoArribaDer = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoDer = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoAbajoDer = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoAbajo = new BufferedImage[TAM_BUFFER_IMAGE];
	elfoAbajoIzq = new BufferedImage[TAM_BUFFER_IMAGE];

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoIzq[i] = spriteElfo.getTile(aNCHO * i, 0, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoArribaIzq[i] = spriteElfo.getTile(aNCHO * i, aLTO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoArriba[i] = spriteElfo.getTile(aNCHO * i, aLTO * SPRITE_ARRIBA, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoArribaDer[i] = spriteElfo.getTile(aNCHO * i, aLTO * SPRITE_ARRIBA_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoDer[i] = spriteElfo.getTile(aNCHO * i, aLTO * SPRITE_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoAbajoDer[i] = spriteElfo.getTile(aNCHO * i, aLTO * SPRITE_ABAJO_DER, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoAbajo[i] = spriteElfo.getTile(aNCHO * i, aLTO * SPRITE_ABAJO, aNCHO, aLTO);
	}

	actualizarBarraDeCarga(++elementosCargados, menuCarga);

	for (int i = 0; i < TAM_BUFFER_IMAGE; i++) {
	    elfoAbajoIzq[i] = spriteElfo.getTile(aNCHO * i, aLTO * SPRITE_ABAJO_IZQ, aNCHO, aLTO);
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
	if (MenuMapas.getNumberMap() == 1) {
	    SpriteSheet mapaAubenor = new SpriteSheet(CargadorImagen.cargarImagen("/Aubenor.png"));
	    Tile.setAubenor(new Tile[TAM_SOLIDEZ_MAPA]);
	    boolean[][] solidezAubenor = { {true, true, false, true, false, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, false, false, false, false, false, false, false, true, true },
		    {false, false, false, false, false, false, false, false, true, true },
		    {false, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true } };
	    for (int y = 0; y < TAM_COLUMNAS_SOLIDEZ; y++) {
		for (int x = 0; x < TAM_FILAS_SOLIDEZ; x++) {
		    Tile.getAubenor()[y * TAM_FILAS_SOLIDEZ + x + 1] = new Tile(
			    mapaAubenor.getTile(x * ANCHO_TILE, y * ALTO_TILE, ANCHO_TILE, ALTO_TILE),
			    y * TAM_FILAS_SOLIDEZ + x + 1, solidezAubenor[y][x], ANCHO_TILE, 64);
		}
	    }
	} else {
	    SpriteSheet mapaAris = new SpriteSheet(CargadorImagen.cargarImagen("/Aris.png"));
	    Tile.setAris(new Tile[TAM_SOLIDEZ_MAPA]);
	    boolean[][] solidezAris = { {true, false, false, false, false, false, false, true, true, true },
		    {false, false, false, false, false, false, false, false, true, true },
		    {false, false, false, false, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {false, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true },
		    {true, true, true, true, true, true, true, true, true, true } };
	    for (int y = 0; y < TAM_COLUMNAS_SOLIDEZ; y++) {
		for (int x = 0; x < TAM_FILAS_SOLIDEZ; x++) {
		    Tile.getAris()[y * TAM_FILAS_SOLIDEZ + x + 1] = new Tile(
			    mapaAris.getTile(x * ANCHO_TILE, y * ALTO_TILE, ANCHO_TILE, ALTO_TILE),
			    y * TAM_FILAS_SOLIDEZ + x + 1, solidezAris[y][x], ANCHO_TILE, ALTO_TILE);
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
    private static void actualizarBarraDeCarga(final int elementosCargados, final MenuCarga menuCarga) {
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
