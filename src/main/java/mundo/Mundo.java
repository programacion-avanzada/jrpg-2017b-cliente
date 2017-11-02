package mundo;

import java.awt.Graphics;

import estados.Estado;
import juego.Juego;

/**
 * Clase Mundo Encargada de cargar los recursos del mundo a la pantalla. Pasar
 * las posiciones entre 2D a Isometricas y biceversa Caputrar las posiciones del
 * click en el mapa
 */
public class Mundo {
    private static final int AUBENOR_MAP = 3;
    private static final int ARIS_MAP = 2;
    private static final int DOS = 2;
    private static final int ALTO_TILE = 64;
    private static final int ANCHO_TILE = 64;
    private static final int MINIMO_Y = 60;
    private static final int MINIMO_X = 30;
    private static final int DESPLAZAMIENTO_CAMARA = 32;
    private Juego juego;
    private int ancho;
    private int alto;
    private int spawnX;
    private int spawnY;
    private int xOffset;
    private int yOffset;

    private float[] iso = new float[DOS];
    private int[][] tiles;
    private int[][] tilesInv;

    private int xMinimo;
    private int xMaximo;
    private int yMinimo;
    private int yMaximo;

    private Grafo grafoDeTilesNoSolidos;

    /**
     * Instantiates a new mundo.
     * @param juego the juego
     * @param pathMap the path map
     * @param pathObstac the path obstac
     */
    public Mundo(final Juego juego, final String pathMap, final String pathObstac) {
	this.juego = juego;
	cargarMundo(pathMap, pathObstac);
	mundoAGrafo();
    }

    /**
     * Actualizar.
     */
    public void actualizar() {

    }

    /**
     * Metodo para graficar el mapa
     * @param g the g
     */
    public void graficar(final Graphics g) {
	xOffset = juego.getEstadoJuego().getPersonaje().getxOffset();
	yOffset = juego.getEstadoJuego().getPersonaje().getYOffset();

	xMinimo = (int) (juego.getCamara().getxOffset() - xOffset - MINIMO_X);
	xMaximo = xMinimo + juego.getAncho() + xOffset + MINIMO_X;
	yMinimo = (int) juego.getCamara().getyOffset() + yOffset - MINIMO_Y;
	yMaximo = yMinimo + juego.getAlto() + yOffset + MINIMO_Y;

	// Grafico el el tile base
	for (int i = 0; i < alto; i++) {
	    for (int j = 0; j < ancho; j++) {
		iso = dosDaIso(j, i);
		if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)) {
		    int map = juego.getPersonaje().getMapa();
		    if (map == 1) {
			Tile.getAubenor()[Tile.getAubenorbase()].graficar(g,
				(int) (iso[0] - juego.getCamara().getxOffset()),
				(int) (iso[1] - juego.getCamara().getyOffset() - DESPLAZAMIENTO_CAMARA), ANCHO_TILE,
				ALTO_TILE);
		    } else if (map == ARIS_MAP) {
			Tile.getAris()[Tile.getArisbase()].graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
				(int) (iso[1] - juego.getCamara().getyOffset() - DESPLAZAMIENTO_CAMARA), ANCHO_TILE,
				ALTO_TILE);
		    } else if (map == AUBENOR_MAP) {
			Tile.getAubenor()[Tile.getAubenorbase()].graficar(g,
				(int) (iso[0] - juego.getCamara().getxOffset()),
				(int) (iso[1] - juego.getCamara().getyOffset() - DESPLAZAMIENTO_CAMARA), ANCHO_TILE,
				ALTO_TILE);
		    }
		    if (!getTile(j, i).esSolido()) {
			getTile(j, i).graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
				(int) (iso[1] - juego.getCamara().getyOffset() - DESPLAZAMIENTO_CAMARA), ANCHO_TILE,
				ALTO_TILE);
		    }
		}
	    }
	}
    }

    /**
     * Graficar obstaculos.
     * @param g the g
     */
    public void graficarObstaculos(final Graphics g) {
	Tile obst;
	for (int i = 0; i < alto; i++) {
	    for (int j = 0; j < ancho; j++) {
		iso = dosDaIso(j, i);
		// Grafico al personaje
		if (Estado.getEstado() == juego.getEstadoJuego()) {
		    if (Mundo.mouseATile(juego.getUbicacionPersonaje().getPosX(),
			    juego.getUbicacionPersonaje().getPosY())[0] == j
			    && Mundo.mouseATile(juego.getUbicacionPersonaje().getPosX(),
				    juego.getUbicacionPersonaje().getPosY())[1] == i) {
			juego.getEstadoJuego().getPersonaje().graficar(g);
		    }
		}

		// Grafico los obstaculos
		if ((iso[0] >= xMinimo && iso[0] <= xMaximo) && (iso[1] >= yMinimo && iso[1] <= yMaximo)
			&& getTile(j, i).esSolido()) {
		    obst = getTile(j, i);
		    obst.graficar(g, (int) (iso[0] - juego.getCamara().getxOffset()),
			    (int) (iso[1] - juego.getCamara().getyOffset() - obst.getAlto() / DOS), obst.getAncho(),
			    obst.getAlto());
		}
	    }
	}
    }

    /**
     * Gets the tile.
     * @param x the x
     * @param y the y
     * @return the tile
     */
    public Tile getTile(final int x, final int y) {
	Tile t = Tile.getTiles()[tiles[x][y]];
	if (t == null) {
	    int map = juego.getPersonaje().getMapa();
	    if (map == 1) {
		return Tile.getAris()[Tile.getAubenorbase()];
	    } else if (map == ARIS_MAP) {
		return Tile.getAris()[Tile.getArisbase()];
	    } else if (map == AUBENOR_MAP) {
		return Tile.getAubenor()[Tile.getAubenorbase()];
	    }
	}
	return t;
    }

    /**
     * Cargar mundo.
     * @param pathMapa the path mapa
     * @param pathObstaculos the path obstaculos
     */
    private void cargarMundo(final String pathMapa, final String pathObstaculos) {
	String archivo = Utilitarias.archivoAString(pathMapa);
	String[] tokens = archivo.split("\\s+");
	ancho = Utilitarias.parseInt(tokens[0]);
	alto = Utilitarias.parseInt(tokens[1]);
	spawnX = Utilitarias.parseInt(tokens[DOS]);
	spawnY = Utilitarias.parseInt(tokens[2 + 1]);

	tiles = new int[ancho][alto];
	tilesInv = new int[alto][ancho];

	for (int y = 0; y < alto; y++) {
	    for (int x = 0; x < ancho; x++) {

		tiles[x][y] = Utilitarias.parseInt(tokens[(x + y * ancho + 2 + 2)]);
		tilesInv[y][x] = tiles[x][y];
	    }
	}

    }

    /**
     * Mundo A grafo. Se transforma el mundo cargado a un grafo compuesto por
     * Tile
     */
    private void mundoAGrafo() {
	// Creo una matriz de nodos
	Nodo[][] nodos = new Nodo[ancho][alto];
	int indice = 0;
	// Lleno la matriz con los nodos
	for (int y = 0; y < alto; y++) {
	    for (int x = 0; x < ancho; x++) {
		nodos[y][x] = new Nodo(indice++, x, y);
	    }
	}
	// Variables finales
	int xFinal = ancho;
	int yFinal = alto;
	// Uno cada nodo con sus adyacentes
	for (int x = 0; x < yFinal; x++) {
	    for (int y = 0; y < xFinal; y++) {
		if (!Tile.getTiles()[tilesInv[x][y]].esSolido()) {
		    // Si no es la ultima fila y el tile de abajo es no solido,
		    // lo uno
		    if (y < yFinal - 1 && !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
			nodos[x][y].agregarAdyacente(nodos[x][y + 1]);
			nodos[x][y + 1].agregarAdyacente(nodos[x][y]);
		    }
		    // Si no es la ultima columna
		    if (x < xFinal - 1) {
			// Si el de arriba a la derecha no es un tile solido
			// Y ademas el de arriba ni el de la derecha lo son, lo
			// uno
			// Tiene que ser a partir de la segunda fila
			if (y > 0 && !Tile.getTiles()[tilesInv[x + 1][y - 1]].esSolido()
				&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
				&& !Tile.getTiles()[tilesInv[x][y - 1]].esSolido()) {
			    nodos[x][y].agregarAdyacente(nodos[x + 1][y - 1]);
			    nodos[x + 1][y - 1].agregarAdyacente(nodos[x][y]);
			}
			// Si el de la derecha no es un tile solido lo uno
			if (!Tile.getTiles()[tilesInv[x + 1][y]].esSolido()) {
			    nodos[x][y].agregarAdyacente(nodos[x + 1][y]);
			    nodos[x + 1][y].agregarAdyacente(nodos[x][y]);
			}
			// Si el de abajo a la derecha no es un tile solido
			// Y ademas el de abajo ni el de la derecha lo son, lo
			// uno
			// Debe ser antes de la ultima fila
			if (y < yFinal - 1 && !Tile.getTiles()[tilesInv[x + 1][y + 1]].esSolido()
				&& !Tile.getTiles()[tilesInv[x + 1][y]].esSolido()
				&& !Tile.getTiles()[tilesInv[x][y + 1]].esSolido()) {
			    nodos[x][y].agregarAdyacente(nodos[x + 1][y + 1]);
			    nodos[x + 1][y + 1].agregarAdyacente(nodos[x][y]);
			}
		    }
		}
	    }
	}
	// Creo un grafo para almacenar solo los tiles no solidos
	grafoDeTilesNoSolidos = new Grafo(ancho * alto);
	indice = 0;
	// Paso la matriz a un array
	for (int i = 0; i < ancho; i++) {
	    for (int j = 0; j < alto; j++) {
		grafoDeTilesNoSolidos.agregarNodo(nodos[i][j]);
	    }
	}
    }

    /**
     * Obtener grafo de tiles no solidos.
     * @return grafo
     */
    public Grafo obtenerGrafoDeTilesNoSolidos() {
	return grafoDeTilesNoSolidos;
    }

    /**
     * Obtener ancho.
     * @return ancho
     */
    public int obtenerAncho() {
	return ancho;
    }

    /**
     * Obtener alto.
     * @return alto
     */
    public int obtenerAlto() {
	return alto;
    }

    /**
     * Transforma isometrica a 2D
     * @param x pos en x isometrica
     * @param y pos en y isometrica
     * @return float[] de las coordenadas en 2D
     */
    public static float[] isoA2D(final float x, final float y) {
	float[] dosD = new float[DOS];

	dosD[0] = (x / (Tile.ANCHO / DOS) + y / (Tile.ALTO / DOS)) / DOS;
	dosD[1] = (y / (Tile.ALTO / DOS) - (x / (Tile.ANCHO / DOS))) / DOS;

	return dosD;
    }

    /**
     * Transforma las posiciones en 2D a Isometricas
     * @param x pos en x 2D
     * @param y pos en y 2D
     * @return float[] coordenadas en Isometricas
     */
    public static float[] dosDaIso(final float x, final float y) {
	float[] iso = new float[DOS];

	iso[0] = (x - y) * (Tile.ANCHO / DOS);
	iso[1] = (x + y) * (Tile.ALTO / DOS);

	return iso;
    }

    /**
     * Capturar la posicion del mouse y obtener el Tile que se clickeo
     * @param x pos en X del click
     * @param y pos en Y del click
     * @return int[] tile calculado
     */
    public static int[] mouseATile(final float x, final float y) {
	int[] tile = new int[DOS];

	tile[0] = (int) Math.floor((y / Tile.ALTO) + (x / Tile.ANCHO)) + 1;
	tile[1] = (int) Math.floor((-x / Tile.ANCHO) + (y / Tile.ALTO)) + 1;

	return tile;
    }
}