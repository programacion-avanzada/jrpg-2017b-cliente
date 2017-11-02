package mundo;

public class Nodo {

    private static final int CANT_NODOS = 8;
    private int x;
    private int y;
    private int indice;
    private int cantidadDeAdyacentes;
    private Nodo[] nodosAdyacentes;

    /**
     * Instantiates a new nodo.
     * @param indice the indice
     * @param x the x
     * @param y the y
     */
    public Nodo(final int indice, final int x, final int y) {
	this.x = x;
	this.y = y;
	this.indice = indice;
	cantidadDeAdyacentes = 0;
	nodosAdyacentes = new Nodo[CANT_NODOS];
    }

    /**
     * Obtener X.
     * @return the int
     */
    public int obtenerX() {
	return x;
    }

    /**
     * Obtener Y.
     * @return the int
     */
    public int obtenerY() {
	return y;
    }

    /**
     * Obtener indice.
     * @return the int
     */
    public int obtenerIndice() {
	return indice;
    }

    /**
     * Obtener nodos adyacentes.
     * @return the nodo[]
     */
    public Nodo[] obtenerNodosAdyacentes() {
	return nodosAdyacentes;
    }

    /**
     * Agregar adyacente.
     * @param nodo the nodo
     */
    public void agregarAdyacente(final Nodo nodo) {
	nodosAdyacentes[cantidadDeAdyacentes++] = nodo;
    }

    /**
     * Obtener cantidad de adyacentes.
     * @return the int
     */
    public int obtenerCantidadDeAdyacentes() {
	return cantidadDeAdyacentes;
    }
}
