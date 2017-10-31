package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile[] aubenor;
    public static Tile[] aris;
    // es el piso de aubenor por defecto si queres llamarlo asi, es gris
    public static int arisBase = 3;
    public static int aubenorBase = 3;

    public static final int ANCHO = 64;
    public static final int ALTO = 32;

    protected BufferedImage textura;
    protected final int id;

    private boolean esSolido;

    protected int ancho;
    protected int alto;

    /**
     * Instantiates a new tile.
     * @param textura the textura
     * @param id the id
     * @param esSolido the es solido
     */
    public Tile(final BufferedImage textura, final int id, final boolean esSolido) {
	this.textura = textura;
	this.id = id;
	tiles[id] = this;
	this.esSolido = esSolido;
    }

    /**
     * Instantiates a new tile.
     * @param textura the textura
     * @param id the id
     * @param esSolido the es solido
     * @param ancho the ancho
     * @param alto the alto
     */
    public Tile(final BufferedImage textura, final int id, final boolean esSolido, final int ancho, final int alto) {
	this.textura = textura;
	this.id = id;
	tiles[id] = this;
	this.ancho = ancho;
	this.alto = alto;
	this.esSolido = esSolido;
    }

    /**
     * Actualizar.
     */
    public void actualizar() {

    }

    /**
     * Graficar.
     * @param g the g
     * @param x the x
     * @param y the y
     */
    public void graficar(final Graphics g, final int x, final int y) {
	g.drawImage(textura, x, y, ANCHO, ALTO, null);
    }

    /**
     * Graficar.
     * @param g the g
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public void graficar(final Graphics g, final int x, final int y, final int width, final int height) {
	g.drawImage(textura, x, y, width, height, null);
    }

    /**
     * Sets the solido.
     * @param solidez the new solido
     */
    public void setSolido(final boolean solidez) {
	esSolido = solidez;
    }

    /**
     * Es solido.
     * @return true, if successful
     */
    public boolean esSolido() {
	return esSolido;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * Gets the ancho.
     * @return the ancho
     */
    public int getAncho() {
	return ancho;
    }

    /**
     * Gets the alto.
     * @return the alto
     */
    public int getAlto() {
	return alto;
    }

}
