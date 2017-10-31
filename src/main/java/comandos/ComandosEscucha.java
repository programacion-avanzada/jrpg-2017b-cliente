package comandos;

import juego.Juego;
import mensajeria.Comando;

public abstract class ComandosEscucha extends Comando {
    protected Juego juego;

    /**
     * Sets the juego.
     * @param juego the new juego
     */
    public void setJuego(Juego juego) {
	this.juego = juego;
    }

}
