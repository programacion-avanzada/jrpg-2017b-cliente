package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import chat.VentanaContactos;
import estados.Estado;
import estados.EstadoBatallaNpc;
import frames.MenuEscape;
import frames.MenuInventario;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteComerciar;
import mensajeria.PaqueteMovimiento;
import mundo.Grafo;
import mundo.Mundo;
import mundo.Nodo;
import recursos.Recursos;

/**
 * Clase Entidad
 */
public class Entidad {

    private static final int OFFSET_Y_GRAFICAR = 4;

    private static final int ALTO_PANTALLA = 10;

    private static final int DOSCERO = 20;

    private static final int MOVER_ABAJO_IZQ = 7;

    private static final int MOVER_ABAJO_DER = 5;

    private static final int MOVER_DER = 4;

    private static final int MOVER_ARRIBA_DER = 3;

    private static final int MOVER_IZQ = 0;

    private static final int MOVER_ARRIBA_IZQ = 1;

    private static final double VALOR_SUMAR_DIJKSTRA = 1.5;

    private static final int TAM_NOMBRE_LETRA = 15;

    private static final int MAX1_MOUSE_COMERCIAR = 29;

    private static final int MAX0_MOUSE_COMERCIAR = 71;

    private static final int MIN0_MOUSE_COMERCIAR = 44;

    private static final int MAX1_MOUSE_CONTACTOS = 559;

    private static final int MIN1_MOUSE_CONTACTOS = 524;

    private static final int MAX0_MOUSE_CONTACTOS = 105;

    private static final int MIN0_MOUSE_CONTACTOS = 3;

    private static final int MAX1_MOUSE_SALIR = 597;

    private static final int MIN1_MOUSE_SALIR = 562;

    private static final int MAX0_MOUSE_SALIR = 105;

    private static final int MIN0_MOUSE_SALIR = 3;

    private static final int MAX1_MOUSE_MENUINVENTARIO = 597;

    private static final int MIN1_MOUSE_MENUINVENTARIO = 545;

    private static final int MAX0_MOUSE_MENUINVENTARIO = 797;

    private static final int MIN0_MOUSE_MENUINVENTARIO = 738;

    private static final int SEIS = 6;

    private static final int MOVER_ABAJO = SEIS;

    private static final int DOS = 2;

    private static final int MOVER_ARRIBA = DOS;

    private static final int SEISCUATRO = 64;

    private static final int TRESDOS = 32;

    Juego juego;

    // Tamaño de la entidad
    private int ancho;
    private int alto;

    // Posiciones
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float xInicio;
    private float yInicio;
    private float xFinal;
    private float yFinal;
    private int xOffset;
    private int yOffset;
    private int drawX;
    private int drawY;
    private int[] posMouseRecorrido;
    private int[] posMouse;

    // Movimiento Actual
    private static final int HORIZONTALDER = 4;
    private static final int HORIZONTALIZQ = 0;
    private static final int VERTICALSUP = 2;
    private static final int VERTICALINF = 6;
    private static final int DIAGONALINFIZQ = 7;
    private static final int DIAGONALINFDER = 5;
    private static final int DIAGONALSUPDER = 3;
    private static final int DIAGONALSUPIZQ = 1;
    private int movimientoHacia = SEIS;
    private boolean enMovimiento;

    private Map<Integer, Animacion> animaciones;

    // Animaciones
    private final Animacion moverIzq;
    private final Animacion moverArribaIzq;
    private final Animacion moverArriba;
    private final Animacion moverArribaDer;
    private final Animacion moverDer;
    private final Animacion moverAbajoDer;
    private final Animacion moverAbajo;
    private final Animacion moverAbajoIzq;

    private final Gson gson = new Gson();
    private int intervaloEnvio = 0;

    // pila de movimiento
    private PilaDeTiles pilaMovimiento;
    private int[] tileActual;
    private int[] tileFinal;
    private int[] tileMoverme;

    private Mundo mundo;
    private String nombre;
    private int[] tilePersonajes;
    private int[] tileNpc;
    private int idEnemigo;

    // Ubicacion para abrir comerciar.
    private float xComercio;
    private float yComercio;
    private float[] comercio;

    /**
     * Constructor de la clase Entidad
     * @param juego juego con el que se instancia Entidad
     * @param mundo mundo con el que se instancia Entidad
     * @param ancho ancho
     * @param alto alto
     * @param nombre nombre de la entidad
     * @param spawnX tile X donde spawnea
     * @param spawnY tile Y donde spawnea
     * @param animaciones animaciones de la entidad
     * @param velAnimacion velocidad de animacion de la entidad
     */
    public Entidad(final Juego juego, final Mundo mundo, final int ancho, final int alto, final String nombre,
	    final float spawnX, final float spawnY, final LinkedList<BufferedImage[]> animaciones,
	    final int velAnimacion) {
	this.juego = juego;
	this.ancho = ancho;
	this.alto = alto;
	this.nombre = nombre;
	this.mundo = mundo;
	xOffset = ancho / DOS;
	yOffset = alto / DOS;

	x = (int) (spawnX / SEISCUATRO) * SEISCUATRO;
	y = (int) (spawnY / TRESDOS) * TRESDOS;
	// x = spawnX;
	// y = spawnY;

	moverIzq = new Animacion(velAnimacion, animaciones.get(MOVER_IZQ));
	moverArribaIzq = new Animacion(velAnimacion, animaciones.get(MOVER_ARRIBA_IZQ));
	moverArriba = new Animacion(velAnimacion, animaciones.get(MOVER_ARRIBA));
	moverArribaDer = new Animacion(velAnimacion, animaciones.get(MOVER_ARRIBA_DER));
	moverDer = new Animacion(velAnimacion, animaciones.get(MOVER_DER));
	moverAbajoDer = new Animacion(velAnimacion, animaciones.get(MOVER_ABAJO_DER));
	moverAbajo = new Animacion(velAnimacion, animaciones.get(MOVER_ABAJO));
	moverAbajoIzq = new Animacion(velAnimacion, animaciones.get(MOVER_ABAJO_IZQ));

	this.inicializarMapaAnimaciones();

	// Informo mi posicion actual
	juego.getUbicacionPersonaje().setPosX(x);
	juego.getUbicacionPersonaje().setPosY(y);
	juego.getUbicacionPersonaje().setDireccion(getDireccion());
	juego.getUbicacionPersonaje().setFrame(getFrame());
    }

    /**
     * Actualiza el personaje
     */
    public void actualizar() {

	if (enMovimiento) {
	    moverIzq.actualizar();
	    moverArribaIzq.actualizar();
	    moverArriba.actualizar();
	    moverArribaDer.actualizar();
	    moverDer.actualizar();
	    moverAbajoDer.actualizar();
	    moverAbajo.actualizar();
	    moverAbajoIzq.actualizar();
	} else {
	    moverIzq.reset();
	    moverArribaIzq.reset();
	    moverArriba.reset();
	    moverArribaDer.reset();
	    moverDer.reset();
	    moverAbajoDer.reset();
	    moverAbajo.reset();
	    moverAbajoIzq.reset();
	}

	getEntrada();
	mover();

	juego.getCamara().centrar(this);
    }

    /**
     * Devuelve la entrada
     */
    public void getEntrada() {
	posMouseRecorrido = juego.getHandlerMouse().getPosMouseRecorrido();
	posMouse = juego.getHandlerMouse().getPosMouse();
	if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= MIN0_MOUSE_MENUINVENTARIO
		&& posMouse[0] <= MAX0_MOUSE_MENUINVENTARIO && posMouse[1] >= MIN1_MOUSE_MENUINVENTARIO
		&& posMouse[1] <= MAX1_MOUSE_MENUINVENTARIO) {
	    if (Pantalla.menuInventario == null) {
		Pantalla.menuInventario = new MenuInventario(juego.getCliente());
		Pantalla.menuInventario.setVisible(true);
	    }
	    juego.getHandlerMouse().setNuevoClick(false);
	}
	if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= MIN0_MOUSE_SALIR
		&& posMouse[0] <= MAX0_MOUSE_SALIR && posMouse[1] >= MIN1_MOUSE_SALIR
		&& posMouse[1] <= MAX1_MOUSE_SALIR) {
	    if (Pantalla.menuEscp == null) {
		Pantalla.menuEscp = new MenuEscape(juego.getCliente());
		Pantalla.menuEscp.setVisible(true);
	    }
	    juego.getHandlerMouse().setNuevoClick(false);
	}
	if (juego.getHandlerMouse().getNuevoClick() && posMouse[0] >= MIN0_MOUSE_CONTACTOS
		&& posMouse[0] <= MAX0_MOUSE_CONTACTOS && posMouse[1] >= MIN1_MOUSE_CONTACTOS
		&& posMouse[1] <= MAX1_MOUSE_CONTACTOS) {
	    if (Pantalla.ventContac == null) {
		Pantalla.ventContac = new VentanaContactos(juego);
		Pantalla.ventContac.setVisible(true);
	    }
	    juego.getHandlerMouse().setNuevoClick(false);
	}
	// Tomo el click izquierdo
	if (juego.getHandlerMouse().getNuevoClick()) {

	    if (juego.getEstadoJuego().getHaySolicitud()) {

		if (juego.getEstadoJuego().getMenuEnemigo().clickEnMenu(posMouse[0], posMouse[1])
			&& !juego.getEstadoJuego().getMenuEnemigo().esNPC()) {
		    if (juego.getEstadoJuego().getMenuEnemigo().clickEnBoton(posMouse[0], posMouse[1])) {
			// Pregunto si menuBatallar o menuComerciar, sino no me
			// interesa hacer esto
			if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUBATALLAR
				|| juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUCOMERCIAR) {
			    // Guardo las poss con el que quiero comerciar
			    xComercio = juego.getUbicacionPersonajes().get(idEnemigo).getPosX();
			    yComercio = juego.getUbicacionPersonajes().get(idEnemigo).getPosY();
			    comercio = Mundo.isoA2D(xComercio, yComercio);
			}
			// pregunto si el menu emergente es de tipo batalla
			if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUBATALLAR) {
			    // ME FIJO SI CON EL QUE QUIERO BATALLAR ESTA EN LA
			    // ZONA DE COMERCIO
			    if (!((int) comercio[0] >= MIN0_MOUSE_COMERCIAR && (int) comercio[0] <= MAX0_MOUSE_COMERCIAR
				    && (int) comercio[1] >= 0 && (int) comercio[1] <= MAX1_MOUSE_COMERCIAR)) {
				juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
				PaqueteBatalla pBatalla = new PaqueteBatalla();

				pBatalla.setId(juego.getPersonaje().getId());
				pBatalla.setIdEnemigo(idEnemigo);

				juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);

				try {
				    juego.getCliente().getSalida().writeObject(gson.toJson(pBatalla));
				} catch (IOException e) {
				    JOptionPane.showMessageDialog(null, "Fallo la conexión " + "con el servidor");
				}
			    } else {
				JOptionPane.showMessageDialog(null,
					"El otro usuario se encuentra " + "dentro de la zona de comercio");
			    }
			} else {
			    // PREGUNTO SI EL MENU EMERGENTE ES DE TIPO COMERCIO
			    if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUCOMERCIAR) {
				if ((int) comercio[0] >= MIN0_MOUSE_COMERCIAR
					&& (int) comercio[0] <= MAX0_MOUSE_COMERCIAR && (int) comercio[1] >= 0
					&& (int) comercio[1] <= MAX1_MOUSE_COMERCIAR) {
				    if (juego.getCliente().getM1() == null) {
					juego.getCliente().setPaqueteComercio(new PaqueteComerciar());
					juego.getCliente().getPaqueteComercio().setId(juego.getPersonaje().getId());
					juego.getCliente().getPaqueteComercio().setIdEnemigo(idEnemigo);

					try {
					    juego.getCliente().getSalida()
						    .writeObject(gson.toJson(juego.getCliente().getPaqueteComercio()));
					} catch (IOException e) {
					    JOptionPane.showMessageDialog(null,
						    "Fallo la conexión " + "con el servidor");
					}
				    } else {
					JOptionPane.showMessageDialog(null, "Ya te encuentras comerciando!");
				    }
				} else {
				    JOptionPane.showMessageDialog(null,
					    "El otro usuario no se encuentra " + "dentro de la zona de comercio");
				}
			    }
			}
			juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);

		    } else if (juego.getEstadoJuego().getMenuEnemigo().clickEnCerrar(posMouse[0], posMouse[1])) {
			juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
		    }
		} else if (juego.getEstadoJuego().getMenuEnemigo().clickEnBoton(posMouse[0], posMouse[1])
			&& juego.getEstadoJuego().getMenuEnemigo().esNPC()) {
		    if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUBATALLAR) {
			PaqueteBatalla pBatalla = new PaqueteBatalla();

			pBatalla.setId(juego.getPersonaje().getId());
			pBatalla.setIdEnemigo(juego.getEstadoJuego().getMenuEnemigo().getNpc().getId() * -1);

			juego.getPersonaje().setEstado(Estado.getEstadoBatallaNpc());
			Estado.setEstado(null);
			juego.setEstadoBatallaNpc(new EstadoBatallaNpc(juego, pBatalla));
			Estado.setEstado(juego.getEstadoBatallaNpc());

			try {
			    juego.getCliente().getSalida().writeObject(gson.toJson(pBatalla));
			} catch (IOException e) {
			    JOptionPane.showMessageDialog(null, "Fallo la conexión " + "con el servidor");
			}
		    }
		} else {
		    juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
		}
	    } else {
		{
		    // Me fijo si hizo click en alguno de los personajes
		    Iterator<Integer> it = juego.getUbicacionPersonajes().keySet().iterator();
		    int key;
		    int[] tileMoverme = Mundo.mouseATile(posMouse[0] + juego.getCamara().getxOffset() - xOffset,
			    posMouse[1] + juego.getCamara().getyOffset() - yOffset);
		    PaqueteMovimiento actual;

		    while (it.hasNext()) {
			key = it.next();
			actual = juego.getUbicacionPersonajes().get(key);
			tilePersonajes = Mundo.mouseATile(actual.getPosX(), actual.getPosY());

			if (actual != null && actual.getIdPersonaje() != juego.getPersonaje().getId()
				&& juego.getPersonajesConectados().get(actual.getIdPersonaje()) != null
				&& juego.getPersonajesConectados().get(actual.getIdPersonaje())
					.getEstado() == Estado.getEstadoJuego()) {

			    if (tileMoverme[0] == tilePersonajes[0] && tileMoverme[1] == tilePersonajes[1]) {
				idEnemigo = actual.getIdPersonaje();
				float xY[] = Mundo.isoA2D(x, y);

				// ESTA ESTE PARA NO MOVERME HASTA EL LUGAR.
				if (xY[0] >= MIN0_MOUSE_COMERCIAR && xY[0] <= MAX0_MOUSE_COMERCIAR && xY[1] >= 0
					&& xY[1] <= MAX1_MOUSE_COMERCIAR) {
				    // SI ESTOY DENTRO DE LA ZONA DE COMERCIO
				    // SETEO QUE SE ABRA EL MENU
				    // DE COMERCIO
				    juego.getEstadoJuego().setHaySolicitud(true,
					    juego.getPersonajesConectados().get(idEnemigo),
					    MenuInfoPersonaje.MENUCOMERCIAR);
				} else {
				    // SI ESTOY DENTRO DE LA ZONA DE BATALLA
				    // SETEO QUE SE ABRA EL MENU
				    // DE BATALLA
				    juego.getEstadoJuego().setHaySolicitud(true,
					    juego.getPersonajesConectados().get(idEnemigo),
					    MenuInfoPersonaje.MENUBATALLAR);

				}
				// juego.getHandlerMouse().setNuevoClick(false);
			    }
			}
		    }
		}

		// Vuelvo a preguntar si hay solicitud
		// Si hay es porque hizo click en un personaje y salgo.
		// Si no, me fijo si hizo click en un NPC
		// Quedó medio desprolijo, si llegamos con tiempo a la entrega
		// deberíamos acomodarlo.
		if (!juego.getEstadoJuego().getHaySolicitud()) {
		    Iterator<Integer> it = juego.getNpcManager().getEntidadesNpcs().keySet().iterator();
		    int key;
		    int[] tileMoverme = Mundo.mouseATile(posMouse[0] + juego.getCamara().getxOffset() - xOffset,
			    posMouse[1] + juego.getCamara().getyOffset() - yOffset);
		    Entidad actual;

		    while (it.hasNext()) {
			key = it.next();
			actual = juego.getNpcManager().getEntidadesNpcs().get(key);
			tileNpc = Mundo.mouseATile(actual.getX(), actual.getY());

			if (actual != null) {
			    if (tileMoverme[0] == tileNpc[0] && tileMoverme[1] == tileNpc[1] && juego.getPaquetesNpcs()
				    .get(actual.getIdEnemigo()).getEstado() == Estado.getEstadoJuego()) {
				juego.getEstadoJuego().setHaySolicitud(true,
					juego.getPaquetesNpcs().get(actual.getIdEnemigo()),
					MenuInfoPersonaje.MENUBATALLAR);

				/*
				 * PaqueteBatalla pBatalla = new
				 * PaqueteBatalla();
				 * pBatalla.setId(juego.getPersonaje().getId());
				 * pBatalla.setIdEnemigo(actual.idEnemigo * -1);
				 * juego.getPersonaje().setEstado(Estado.
				 * estadoBatallaNpc); Estado.setEstado(null);
				 * juego.setEstadoBatallaNpc(new
				 * EstadoBatallaNpc(juego, pBatalla));
				 * Estado.setEstado(juego.getEstadoBatallaNpc())
				 * ; try {
				 * juego.getCliente().getSalida().writeObject(
				 * gson.toJson (pBatalla)); } catch (IOException
				 * e) { JOptionPane.showMessageDialog(null,
				 * "Fallo la conexión " + "con el servidor"); }
				 */
			    }
			}
		    }
		}
	    }

	    juego.getHandlerMouse().setNuevoClick(false);
	}

	if (juego.getHandlerMouse().getNuevoRecorrido() && !juego.getEstadoJuego().getHaySolicitud()) {

	    tileMoverme = Mundo.mouseATile(posMouseRecorrido[0] + juego.getCamara().getxOffset() - xOffset,
		    posMouseRecorrido[1] + juego.getCamara().getyOffset() - yOffset);

	    juego.getHandlerMouse().setNuevoRecorrido(false);

	    pilaMovimiento = null;

	    juego.getEstadoJuego().setHaySolicitud(false, null, MenuInfoPersonaje.MENUBATALLAR);
	}

	if (!enMovimiento && tileMoverme != null) {

	    enMovimiento = false;

	    xInicio = x;
	    yInicio = y;

	    tileActual = Mundo.mouseATile(x, y);

	    if (tileMoverme[0] < 0 || tileMoverme[1] < 0 || tileMoverme[0] >= mundo.obtenerAncho()
		    || tileMoverme[1] >= mundo.obtenerAlto()) {
		enMovimiento = false;
		juego.getHandlerMouse().setNuevoRecorrido(false);
		pilaMovimiento = null;
		tileMoverme = null;
		return;
	    }

	    if (tileMoverme[0] == tileActual[0] && tileMoverme[1] == tileActual[1]
		    || mundo.getTile(tileMoverme[0], tileMoverme[1]).esSolido()) {
		tileMoverme = null;
		enMovimiento = false;
		juego.getHandlerMouse().setNuevoRecorrido(false);
		pilaMovimiento = null;
		return;
	    }

	    if (pilaMovimiento == null) {
		pilaMovimiento = caminoMasCorto(tileActual[0], tileActual[1], tileMoverme[0], tileMoverme[1]);
	    }
	    // Me muevo al primero de la pila
	    NodoDePila nodoActualTile = pilaMovimiento.pop();

	    if (nodoActualTile == null) {
		enMovimiento = false;
		juego.getHandlerMouse().setNuevoRecorrido(false);
		pilaMovimiento = null;
		tileMoverme = null;
		return;
	    }

	    tileFinal = new int[MOVER_ARRIBA];
	    tileFinal[0] = nodoActualTile.obtenerX();
	    tileFinal[1] = nodoActualTile.obtenerY();

	    xFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[0];
	    yFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[1];

	    if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] - 1) {
		movimientoHacia = VERTICALSUP;
	    }
	    if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] + 1) {
		movimientoHacia = VERTICALINF;
	    }
	    if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] + 1) {
		movimientoHacia = HORIZONTALIZQ;
	    }
	    if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] - 1) {
		movimientoHacia = HORIZONTALDER;
	    }
	    if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1]) {
		movimientoHacia = DIAGONALSUPIZQ;
	    }
	    if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1]) {
		movimientoHacia = DIAGONALINFDER;
	    }
	    if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] - 1) {
		movimientoHacia = DIAGONALSUPDER;
	    }
	    if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] + 1) {
		movimientoHacia = DIAGONALINFIZQ;
	    }
	    enMovimiento = true;
	}
    }

    /**
     * Mueve el personaje
     */
    public void mover() {

	dx = 0;
	dy = 0;

	double paso = 1;

	if (enMovimiento && !(x == xFinal && y == yFinal - TRESDOS)) {
	    if (movimientoHacia == VERTICALSUP) {
		dy -= paso;
	    } else if (movimientoHacia == VERTICALINF) {
		dy += paso;
	    } else if (movimientoHacia == HORIZONTALDER) {
		dx += paso;
	    } else if (movimientoHacia == HORIZONTALIZQ) {
		dx -= paso;
	    } else if (movimientoHacia == DIAGONALINFDER) {
		dx += paso;
		dy += paso / MOVER_ARRIBA;
	    } else if (movimientoHacia == DIAGONALINFIZQ) {
		dx -= paso;
		dy += paso / MOVER_ARRIBA;
	    } else if (movimientoHacia == DIAGONALSUPDER) {
		dx += paso;
		dy -= paso / MOVER_ARRIBA;
	    } else if (movimientoHacia == DIAGONALSUPIZQ) {
		dx -= paso;
		dy -= paso / MOVER_ARRIBA;
	    }

	    x += dx;
	    y += dy;

	    // Le envio la posicion
	    if (intervaloEnvio == MOVER_ARRIBA) {
		enviarPosicion();
		intervaloEnvio = 0;
	    }
	    intervaloEnvio++;

	    if (x == xFinal && y == yFinal - TRESDOS) {
		enMovimiento = false;
	    }
	}
    }

    /**
     * Grafica el frame del personaje
     * @param g objeto que se graficara
     */
    public void graficar(final Graphics g) {
	drawX = (int) (x - juego.getCamara().getxOffset());
	drawY = (int) (y - juego.getCamara().getyOffset());
	g.drawImage(getFrameAnimacionActual(), drawX, drawY + OFFSET_Y_GRAFICAR, ancho, alto, null);
    }

    /**
     * Grafica el nombre
     * @param g objeto que se graficara
     */
    public void graficarNombre(final Graphics g) {
	g.setColor(Color.WHITE);
	g.setFont(new Font("Book Antiqua", Font.BOLD, TAM_NOMBRE_LETRA));
	Pantalla.centerString(g, new java.awt.Rectangle(drawX + TRESDOS, drawY - DOSCERO, 0, ALTO_PANTALLA), nombre);
    }

    /**
     * Obtiene el frameActual de la entidad
     * @return Retorna el gramde de la animacion actual
     */
    private BufferedImage getFrameAnimacionActual() {
	Animacion animacionActual = animaciones.get(movimientoHacia);

	if (animacionActual != null) {
	    return animacionActual.getFrameActual();
	} else {
	    return Recursos.getOrco().get(MOVER_ABAJO)[0];
	}
    }

    /**
     * Metodo que inicializa el mapa de animaciones
     */
    private void inicializarMapaAnimaciones() {
	animaciones = new HashMap<Integer, Animacion>();
	animaciones.put(HORIZONTALIZQ, moverIzq);
	animaciones.put(HORIZONTALDER, moverDer);
	animaciones.put(VERTICALSUP, moverArriba);
	animaciones.put(VERTICALINF, moverAbajo);
	animaciones.put(DIAGONALINFIZQ, moverAbajoIzq);
	animaciones.put(DIAGONALINFDER, moverAbajoDer);
	animaciones.put(DIAGONALSUPIZQ, moverArribaIzq);
	animaciones.put(DIAGONALSUPDER, moverArribaDer);
    }

    /**
     * Pide la direccion donde va
     * @return devuelve el movimiento hacia donde va
     */
    public int getDireccion() {
	return movimientoHacia;
    }

    /**
     * Setea la dirección hacia donde va
     * @param dir int dirección
     */
    public void setDireccion(final int dir) {
	this.movimientoHacia = dir;
    }

    /**
     * Obtiene el frame donde esta el personaje
     * @return Retorna el framde la animacion
     */
    private int getFrame() {
	Animacion animacion = animaciones.get(movimientoHacia);

	if (animacion != null) {
	    return animacion.getFrame();
	} else {
	    return 0;
	}
    }

    /**
     * Envia la posicion de la entidad
     */
    private void enviarPosicion() {
	juego.getUbicacionPersonaje().setPosX(x);
	juego.getUbicacionPersonaje().setPosY(y);
	juego.getUbicacionPersonaje().setDireccion(getDireccion());
	juego.getUbicacionPersonaje().setFrame(getFrame());
	try {
	    juego.getCliente().getSalida()
		    .writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
	}
    }

    /**
     * Busca el camino más corto a recorrer para llegar a una posición
     * @param xInicialParam ubicacion en X inicial
     * @param yInicialParam ubicacion en Y inicial
     * @param xFinalParam ubicacion en X final
     * @param yFinalParam ubicacion en Y final
     * @return la pila de tiles a recorrer
     */
    private PilaDeTiles caminoMasCorto(final int xInicialParam, final int yInicialParam, final int xFinalParam,
	    final int yFinalParam) {
	Grafo grafoLibres = mundo.obtenerGrafoDeTilesNoSolidos();
	// Transformo las coordenadas iniciales y finales en indices
	int nodoInicial = (yInicialParam - grafoLibres.obtenerNodos()[0].obtenerY())
		* (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xInicialParam
		- grafoLibres.obtenerNodos()[0].obtenerX();

	int nodoFinal = (yFinalParam - grafoLibres.obtenerNodos()[0].obtenerY())
		* (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xFinalParam
		- grafoLibres.obtenerNodos()[0].obtenerX();

	// Hago todo
	double[] vecCostos = new double[grafoLibres.obtenerCantidadDeNodosTotal()];
	int[] vecPredecesores = new int[grafoLibres.obtenerCantidadDeNodosTotal()];
	boolean[] conjSolucion = new boolean[grafoLibres.obtenerCantidadDeNodosTotal()];
	int cantSolucion = 0;
	// Lleno la matriz de costos de numeros grandes
	for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
	    vecCostos[i] = Double.MAX_VALUE;
	}
	// Adyacentes al nodo inicial
	conjSolucion[nodoInicial] = true;
	cantSolucion++;
	vecCostos[nodoInicial] = 0;
	Nodo[] adyacentes = grafoLibres.obtenerNodos()[nodoInicial].obtenerNodosAdyacentes();
	for (int i = 0; i < grafoLibres.obtenerNodos()[nodoInicial].obtenerCantidadDeAdyacentes(); i++) {
	    if (estanEnDiagonal(grafoLibres.obtenerNodos()[nodoInicial],
		    grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
		vecCostos[adyacentes[i].obtenerIndice()] = VALOR_SUMAR_DIJKSTRA;
	    } else {
		vecCostos[adyacentes[i].obtenerIndice()] = 1;
	    }
	    vecPredecesores[adyacentes[i].obtenerIndice()] = nodoInicial;
	}
	// Aplico Dijkstra
	while (cantSolucion < grafoLibres.obtenerCantidadDeNodosTotal()) {
	    // Elijo W perteneciente al conjunto restante tal que el costo de W
	    // sea minimo
	    double minimo = Double.MAX_VALUE;
	    int indiceMinimo = 0;
	    Nodo nodoW = null;
	    for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
		if (!conjSolucion[i] && vecCostos[i] < minimo) {
		    nodoW = grafoLibres.obtenerNodos()[i];
		    minimo = vecCostos[i];
		    indiceMinimo = i;
		}
	    }
	    // Pongo a W en el conj solucion
	    conjSolucion[indiceMinimo] = true;
	    cantSolucion++;
	    // Por cada nodo I adyacente a W del conj restante
	    // Le sumo 1 al costo de ir hasta W y luego ir hasta su adyacente
	    adyacentes = grafoLibres.obtenerNodos()[indiceMinimo].obtenerNodosAdyacentes();
	    for (int i = 0; i < grafoLibres.obtenerNodos()[indiceMinimo].obtenerCantidadDeAdyacentes(); i++) {
		double valorASumar = 1;
		if (estanEnDiagonal(grafoLibres.obtenerNodos()[indiceMinimo],
			grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
		    valorASumar = VALOR_SUMAR_DIJKSTRA;
		}
		if (vecCostos[indiceMinimo] + valorASumar < vecCostos[adyacentes[i].obtenerIndice()]) {
		    vecCostos[adyacentes[i].obtenerIndice()] = vecCostos[indiceMinimo] + valorASumar;
		    vecPredecesores[adyacentes[i].obtenerIndice()] = indiceMinimo;
		}
	    }
	}
	// Creo el vector de nodos hasta donde quiere llegar
	PilaDeTiles camino = new PilaDeTiles();
	while (nodoFinal != nodoInicial) {
	    camino.push(new NodoDePila(grafoLibres.obtenerNodos()[nodoFinal].obtenerX(),
		    grafoLibres.obtenerNodos()[nodoFinal].obtenerY()));
	    nodoFinal = vecPredecesores[nodoFinal];
	}

	return camino;
    }

    /**
     * Pregunta si las entidades están en diagonal
     * @param nodoUno entidad 1
     * @param nodoDos entidad 2
     * @return true or false
     */
    private boolean estanEnDiagonal(final Nodo nodoUno, final Nodo nodoDos) {
	return (nodoUno.obtenerX() != nodoDos.obtenerX() && nodoUno.obtenerY() != nodoDos.obtenerY());

    }

    /**
     * Pide el valor de X
     * @return devuelve la ubicación en X
     */
    public float getX() {
	return x;
    }

    /**
     * Setea el valor de X
     * @param x valor nuevo de la ubicación en X
     */
    public void setX(final float x) {
	this.x = x;
    }

    /**
     * Pide el valor de Y
     * @return devuelve la ubicación en Y
     */
    public float getY() {
	return y;
    }

    /**
     * Setea el valor de Y
     * @param y valor nuevo de la ubicación en Y
     */
    public void setY(final float y) {
	this.y = y;
    }

    /**
     * Pide el ancho
     * @return devuelve el ancho
     */
    public int getAncho() {
	return ancho;
    }

    /**
     * Setea el ancho
     * @param ancho nuevo ancho a setear
     */
    public void setAncho(final int ancho) {
	this.ancho = ancho;
    }

    /**
     * Pide el alto
     * @return devuelve el alto
     */
    public int getAlto() {
	return alto;
    }

    /**
     * Setea el alto
     * @param alto nuevo alto a setear
     */
    public void setAlto(final int alto) {
	this.alto = alto;
    }

    /**
     * Pide el offset de X
     * @return devuelve el offset de X
     */
    public int getxOffset() {
	return xOffset;
    }

    /**
     * Pide el offset de Y
     * @return devuelve el offset de Y
     */
    public int getYOffset() {
	return yOffset;
    }

    /**
     * @return id del enemigo
     */
    public int getIdEnemigo() {
	return idEnemigo;
    }

    /**
     * @param idEnemigo id del enemigo
     */
    public void setIdEnemigo(final int idEnemigo) {
	this.idEnemigo = idEnemigo;
    }
}
