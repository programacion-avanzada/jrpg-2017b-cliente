package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dominio.NonPlayableCharacter;
import recursos.Recursos;

/**
 *Clase EstadoDeNpc
 */
public class EstadoDeNpc {

    private static final int ALTO_BARRA_SALUD = 37;
    private static final int OFFSET_Y_BARRA_SALUD = 26;
    private static final int OFFSET_Y_BARRA_ENE = 42;
    private static final int ALTO_BARRA_GEN = 52;
    private static final int ANCHO_BARRA_GEN = 132;
    private static final int OFFSET_ALTO = 70;
    private static final int OFFSET_ANCHO = 59;
    private static final int OFFSET_X_BARRA = 80;
    private static final int TAM_FUENTE = 10;
    private static final int OFFSET_Y_IMAGEN = 9;
    private static final int OFFSET_X_IMAGEN = 10;
    private static final int ANCHOBARRA = 122;
    private static final int ALTOSALUD = 14;
    private static final int ALTOENERGIA = 14;
    // private static final int ALTOEXPERIENCIA = 6;
    private static final int ALTOMINIATURA = 64;
    private static final int ANCHOMINIATURA = 64;

    /**
     * @param g		parametro g
     * @param x		parametro x
     * @param y 	parametro y
     * @param npc	parametro npc
     * @param miniaturaPersonaje	parametro miniaturaPersonaje
     */
    public static void dibujarEstadoDeNpc(final Graphics g, final int x, final int y, final NonPlayableCharacter npc,
	    final BufferedImage miniaturaPersonaje) {

	int drawBarra = 0;
	g.drawImage(Recursos.getEstadoPersonaje(), x, y, null);

	g.drawImage(miniaturaPersonaje, x + OFFSET_X_IMAGEN, y + OFFSET_Y_IMAGEN, ANCHOMINIATURA, ALTOMINIATURA, null);

	if (npc.getSalud() == npc.getSaludTope()) {
	    drawBarra = ANCHOBARRA;
	} else {
	    drawBarra = (npc.getSalud() * ANCHOBARRA) / npc.getSaludTope();
	}

	g.setColor(Color.WHITE);
	g.setFont(new Font("Tahoma", Font.PLAIN, TAM_FUENTE));
	g.drawImage(Recursos.getBarraSalud(), x + OFFSET_X_BARRA, y + OFFSET_Y_BARRA_SALUD, drawBarra, ALTOSALUD, null);
	g.drawString(String.valueOf(npc.getSalud()) + " / " + String.valueOf(npc.getSaludTope()), x + ANCHO_BARRA_GEN,
		y + ALTO_BARRA_SALUD);

	if (npc.getEnergia() == npc.getEnergiaTope()) {
	    drawBarra = ANCHOBARRA;
	} else {
	    drawBarra = (npc.getEnergia() * ANCHOBARRA) / npc.getEnergiaTope();
	}

	g.drawImage(Recursos.getBarraEnergia(), x + OFFSET_X_BARRA, y + OFFSET_Y_BARRA_ENE, drawBarra, ALTOENERGIA,
		null);
	g.drawString(String.valueOf(npc.getEnergia()) + " / " + String.valueOf(npc.getEnergiaTope()),
		x + ANCHO_BARRA_GEN, y + ALTO_BARRA_GEN);

	g.setFont(new Font("Tahoma", Font.PLAIN, TAM_FUENTE));
	g.setColor(Color.GREEN);
	g.drawString(String.valueOf(npc.getNivel()), x + OFFSET_ANCHO, y + OFFSET_ALTO);
    }

    /*
     * public static void dibujarEstadoDePersonaje(Graphics g, int x, int y,
     * PaquetePersonaje personaje, BufferedImage miniaturaPersonaje){ int
     * drawBarra = 0; g.drawImage(Recursos.estadoPersonaje, x, y, null);
     * g.drawImage(miniaturaPersonaje, x + 10, y + 9, ANCHOMINIATURA,
     * ALTOMINIATURA, null); g.setColor(Color.WHITE); g.setFont(new
     * Font("Tahoma", Font.PLAIN, 10)); g.drawImage(Recursos.barraSalud, x + 80,
     * y + 26, ANCHOBARRA, ALTOSALUD, null);
     * g.drawString(String.valueOf(personaje.getSaludTope()) + " / " +
     * String.valueOf(personaje.getSaludTope()), x + 132, y + 37);
     * g.drawImage(Recursos.barraEnergia, x + 80, y + 42, ANCHOBARRA,
     * ALTOENERGIA, null);
     * g.drawString(String.valueOf(personaje.getEnergiaTope()) + " / " +
     * String.valueOf(personaje.getEnergiaTope()), x + 132, y + 52);
     * if(personaje.getExperiencia() ==
     * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) { drawBarra =
     * ANCHOBARRA; } else { drawBarra = (personaje.getExperiencia() *
     * ANCHOBARRA) / Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]; }
     * g.setFont(new Font("Tahoma", Font.PLAIN, 8));
     * g.drawImage(Recursos.barraExperiencia, x + 77, y + 65, drawBarra,
     * ALTOEXPERIENCIA, null);
     * g.drawString(String.valueOf(personaje.getExperiencia()) + " / " +
     * String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
     * x + 132, y + 70); g.setFont(new Font("Tahoma", Font.PLAIN, 10));
     * g.setColor(Color.GREEN);
     * g.drawString(String.valueOf(personaje.getNivel()), x + 59, y + 70); }
     */

}
