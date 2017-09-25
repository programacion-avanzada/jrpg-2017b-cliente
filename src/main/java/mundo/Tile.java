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

	public Tile(BufferedImage textura, int id, boolean esSolido) {
		this.textura = textura;
		this.id = id;
		tiles[id] = this;
		this.esSolido = esSolido;
	}

	public Tile(BufferedImage textura, int id, boolean esSolido, int ancho, int alto) {
		this.textura = textura;
		this.id = id;
		tiles[id] = this;
		this.ancho = ancho;
		this.alto = alto;
		this.esSolido = esSolido;
	}

	public void actualizar() {

	}

	public void graficar(Graphics g, int x, int y) {
		g.drawImage(textura, x, y, ANCHO, ALTO, null);
	}

	public void graficar(Graphics g, int x, int y, int width, int height) {
		g.drawImage(textura, x, y, width, height, null);
	}

	public void setSolido(boolean solidez) {
		esSolido = solidez;
	}

	public boolean esSolido() {
		return esSolido;
	}

	public int getId() {
		return id;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
