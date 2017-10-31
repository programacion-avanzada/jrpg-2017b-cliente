package mundo;

public class Grafo {

    private int cantidadDeNodos;
    private int cantidadDeNodosTotal;
    private Nodo[] nodos;

    /**
     * Instantiates a new grafo.
     * @param cantidadDeNodosTotal the cantidad de nodos total
     */
    public Grafo(final int cantidadDeNodosTotal) {
	cantidadDeNodos = 0;
	nodos = new Nodo[cantidadDeNodosTotal];
	this.cantidadDeNodosTotal = cantidadDeNodosTotal;
    }

    /**
     * Agregar nodo.
     * @param nodo the nodo
     */
    public void agregarNodo(final Nodo nodo) {
	nodos[cantidadDeNodos++] = nodo;
    }

    /**
     * Agregar adyacentes.
     * @param nodoUno the nodo uno
     * @param nodoDos the nodo dos
     */
    public void agregarAdyacentes(final Nodo nodoUno, final Nodo nodoDos) {
	nodoUno.agregarAdyacente(nodoDos);
    }

    /**
     * Obtener nodos.
     * @return the nodo[]
     */
    public Nodo[] obtenerNodos() {
	return nodos;
    }

    /**
     * Obtener cantidad de nodos.
     * @return the int
     */
    public int obtenerCantidadDeNodos() {
	return cantidadDeNodos;
    }

    /**
     * Obtener cantidad de nodos total.
     * @return the int
     */
    public int obtenerCantidadDeNodosTotal() {
	return cantidadDeNodosTotal;
    }

}
