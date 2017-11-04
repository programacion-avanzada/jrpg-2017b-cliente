package recursos;

import java.awt.image.BufferedImage;


/**
 * clase SpriteSheet
 *
 */
public class SpriteSheet {

    private final BufferedImage sprite;

    /**
     * Contructor por parametro de la clase
     * @param sprite
     */
    public SpriteSheet(final BufferedImage sprite) {
	this.sprite = sprite;
    }

    /**
     * De vuelve el tile del sprite
     * @param x posicion x
     * @param y posicion y
     * @param ancho ancho del sprite
     * @param alto alto del sprite
     * @return devuelve el sprite
     */
    public BufferedImage getTile(final int x, final int y, final int ancho, final int alto) {
	return sprite.getSubimage(x, y, ancho, alto);
    }
}