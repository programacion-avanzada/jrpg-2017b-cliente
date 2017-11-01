package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    private static final int TAM_TILES = 256;
    private static Tile[] tiles = new Tile[TAM_TILES];
    private static Tile[] aubenor;
    private static Tile[] aris;
    // es el piso de aubenor por defecto si queres llamarlo asi, es gris
    private static final int ARISBASE = 3;
    private static final int AUBENORBASE = 3;

    public static final int ANCHO = 64;
    public static final int ALTO = 32;

    private BufferedImage textura;
    private final int id;

    private boolean esSolido;

    private int ancho;
    private int alto;

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

    /**
     * @return the tiles
     */
    public static Tile[] getTiles() {
        return tiles;
    }

    /**
     * @return the arisbase
     */
    public static int getArisbase() {
        return ARISBASE;
    }

    /**
     * @return the aubenorbase
     */
    public static int getAubenorbase() {
        return AUBENORBASE;
    }

    /**
     * @return the aubenor
     */
    public static Tile[] getAubenor() {
        return aubenor;
    }

    /**
     * @return the aris
     */
    public static Tile[] getAris() {
        return aris;
    }

    /**
     * @param aris the aris to set
     */
    public static void setAris(Tile[] aris) {
        Tile.aris = aris;
    }

    /**
     * @param aubenor the aubenor to set
     */
    public static void setAubenor(Tile[] aubenor) {
        Tile.aubenor = aubenor;
    }

}
