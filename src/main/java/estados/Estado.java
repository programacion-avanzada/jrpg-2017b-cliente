package estados;

import java.awt.Graphics;

import juego.Juego;

/**
 * Clase abstracta de Estado
 * Solo implementa el constructor
 * los demas metodos son solo la firma.
 */
public abstract class Estado {

    private static Estado estadoActual = null;
    private static final int ESTADO_BATALLA_NPC = 3;
    // Tipo de estados
    public static int estadoOffline = 0;
    public static int estadoJuego = 1;
    public static int estadoBatalla = 2;
    public static int estadoBatallaNpc = ESTADO_BATALLA_NPC;

    protected Juego juego;

    /**
     * Instantiates a new estado.
     * @param juego the juego
     */
    public Estado(final Juego juego) {
	this.juego = juego;
    }

    /**
     * Actualizar.
     */
    public abstract void actualizar();

    /**
     * Graficar.
     * @param g the g
     */
    public abstract void graficar(Graphics g);

    /**
     * Sets the estado.
     * @param estado the new estado
     */
    public static void setEstado(final Estado estado) {
	estadoActual = estado;
    }

    /**
     * Gets the estado.
     * @return the estado
     */
    public static Estado getEstado() {
	return estadoActual;
    }

    /**
     * Es estado de juego.
     * @return true, if successful
     */
    public abstract boolean esEstadoDeJuego();

    /**
     * @return the estadoOffline
     */
    public static int getEstadoOffline() {
        return estadoOffline;
    }

    /**
     * @param estadoOffline the estadoOffline to set
     */
    public static void setEstadoOffline(final int estadoOffline) {
        Estado.estadoOffline = estadoOffline;
    }

    /**
     * @return the estadoJuego
     */
    public static int getEstadoJuego() {
        return estadoJuego;
    }

    /**
     * @param estadoJuego the estadoJuego to set
     */
    public static void setEstadoJuego(final int estadoJuego) {
        Estado.estadoJuego = estadoJuego;
    }

    /**
     * @return the estadoBatalla
     */
    public static int getEstadoBatalla() {
        return estadoBatalla;
    }

    /**
     * @param estadoBatalla the estadoBatalla to set
     */
    public static void setEstadoBatalla(final int estadoBatalla) {
        Estado.estadoBatalla = estadoBatalla;
    }

    /**
     * @return the estadoBatallaNpc
     */
    public static int getEstadoBatallaNpc() {
        return estadoBatallaNpc;
    }

    /**
     * @param estadoBatallaNpc the estadoBatallaNpc to set
     */
    public static void setEstadoBatallaNpc(final int estadoBatallaNpc) {
        Estado.estadoBatallaNpc = estadoBatallaNpc;
    }
}
