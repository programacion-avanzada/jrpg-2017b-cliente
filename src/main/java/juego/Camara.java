package juego;

import entidades.Entidad;

/**
 * The Class Camara.
 */
public class Camara {

    /** The juego. */
    private Juego juego;

    /** The y offset. */
    private float yOffset;

    /** The x offset. */
    private float xOffset;

    /**
     * Instantiates a new camara.
     * @param juego the juego
     * @param xOffset the x offset
     * @param yOffset the y offset
     */
    public Camara(final Juego juego, final float xOffset, final float yOffset) {
	this.juego = juego;
	this.xOffset = xOffset;
	this.yOffset = yOffset;
    }

    /**
     * Centrar.
     * @param e the e
     */
    public void centrar(final Entidad e) {
	xOffset = e.getX() - juego.getAncho() / 2 + e.getAncho() / 2;
	yOffset = e.getY() - juego.getAlto() / 2 + e.getAlto() / 2;
    }

    /**
     * Mover.
     * @param dx the dx
     * @param dy the dy
     */
    public void mover(final float dx, final float dy) {
	xOffset += dx;
	yOffset += dy;
    }

    /**
     * Gets the y offset.
     * @return the y offset
     */
    public float getyOffset() {
	return yOffset;
    }

    /**
     * Sets the y offset.
     * @param yOffset the new y offset
     */
    public void setyOffset(final float yOffset) {
	this.yOffset = yOffset;
    }

    /**
     * Gets the x offset.
     * @return the x offset
     */
    public float getxOffset() {
	return xOffset;
    }

    /**
     * Sets the x offset.
     * @param xOffset the new x offset
     */
    public void setxOffset(final float xOffset) {
	this.xOffset = xOffset;
    }
}
