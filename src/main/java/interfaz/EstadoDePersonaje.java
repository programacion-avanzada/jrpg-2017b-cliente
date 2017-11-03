package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Clase Estado De Personaje
 */
public class EstadoDePersonaje {

	private static final int OFFSET_Y_BARRA_ENE = 52;
	private static final int TAM_TEXTO1 = 8;
	private static final int Y_OFFSET_BARRA_GEN = 37;
	private static final int OFFSET_MINIATURA_Y = 9;
	private static final int OFFSET_BARRA_Y_EXP = 65;
	private static final int OFFSET_Y_BARRA_ENER = 42;
	private static final int OFFSET_Y_BARRA = 26;
	private static final int OFFSET_Y_BARRA1 = 70;
	private static final int OFFSET_X_BARRA1 = 59;
	private static final int OFFSET_BARRA_EXP_X = 77;
	private static final int X_OFFSET_BARRA_GEN = 132;
	private static final int OFFSET_X_BARRA = 80;
	private static final int TAM_TEXTO = 10;
	private static final int OFFSET_MINIATURA_X = 10;
	private static final int ANCHOBARRA = 122;
	private static final int ALTOSALUD = 14;
	private static final int ALTOENERGIA = 14;
	private static final int ALTOEXPERIENCIA = 6;
	private static final int ALTOMINIATURA = 64;
	private static final int ANCHOMINIATURA = 64;

	/**
	 * @param g parametro g
	 * @param x parametro x
	 * @param y parametro y
	 * @param personaje parametro personaje
	 * @param miniaturaPersonaje parametro miniatura Personaje
	 */
	public static void dibujarEstadoDePersonaje(final Graphics g, final int x,
			final int y, final Personaje personaje,
			final BufferedImage miniaturaPersonaje) {

		int drawBarra = 0;

		g.drawImage(Recursos.getEstadoPersonaje(), x, y, null);

		g.drawImage(miniaturaPersonaje, x + OFFSET_MINIATURA_X,
				y + OFFSET_MINIATURA_Y, ANCHOMINIATURA, ALTOMINIATURA,
				null);

		if (personaje.getSalud() == personaje.getSaludTope()) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getSalud() * ANCHOBARRA) / personaje.getSaludTope();
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO));
		g.drawImage(Recursos.getBarraSalud(), x + OFFSET_X_BARRA,
				y + OFFSET_Y_BARRA, drawBarra, ALTOSALUD, null);
		g.drawString(String.valueOf(personaje.getSalud()) + " / "
				+ String.valueOf(personaje.getSaludTope()),
				x + X_OFFSET_BARRA_GEN, y + Y_OFFSET_BARRA_GEN);

		if (personaje.getEnergia() == personaje.getEnergiaTope()) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getEnergia() * ANCHOBARRA) /
					personaje.getEnergiaTope();
		}

		g.drawImage(Recursos.getBarraEnergia(), x + OFFSET_X_BARRA,
				y + OFFSET_Y_BARRA_ENER, drawBarra, ALTOENERGIA,
				null);
		g.drawString(String.valueOf(personaje.getEnergia()) + " / "
				+ String.valueOf(personaje.getEnergiaTope()),
				x + X_OFFSET_BARRA_GEN, y + OFFSET_Y_BARRA_ENE);

		if (personaje.getExperiencia() ==
				Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
					/ Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
		}

		g.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO1));
		g.drawImage(Recursos.getBarraExperiencia(), x + OFFSET_BARRA_EXP_X,
				y + OFFSET_BARRA_Y_EXP, drawBarra,
				ALTOEXPERIENCIA, null);
		g.drawString(
				String.valueOf(personaje.getExperiencia()) + " / "
				+ String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
				x + X_OFFSET_BARRA_GEN, y + OFFSET_Y_BARRA1);
		g.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO));
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(personaje.getNivel()),
				x + OFFSET_X_BARRA1, y + OFFSET_Y_BARRA1);

	}

	/**
	 * Dibuja el estado del personaje en el mapa
	 *
	 * @param g parametro g
	 * @param x parametro x
	 * @param y parametro y
	 * @param personaje parametro personaje
	 * @param miniaturaPersonaje parametro miniatura Personaje
	 */
	public static void dibujarEstadoDePersonaje(final Graphics g,
			final int x, final int y,
			final PaquetePersonaje personaje,
			final BufferedImage miniaturaPersonaje) {

		int drawBarra = 0;
		g.drawImage(Recursos.getEstadoPersonaje(), x, y, null);
		g.drawImage(miniaturaPersonaje, x + TAM_TEXTO, y +
				OFFSET_MINIATURA_Y, ANCHOMINIATURA, ALTOMINIATURA, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO));
		g.drawImage(Recursos.getBarraSalud(), x + OFFSET_X_BARRA, y +
				OFFSET_Y_BARRA, ANCHOBARRA, ALTOSALUD, null);
		g.drawString(String.valueOf(personaje.getSaludTope()) + " / " +
				String.valueOf(personaje.getSaludTope()),
				x + X_OFFSET_BARRA_GEN, y + Y_OFFSET_BARRA_GEN);

		g.drawImage(Recursos.getBarraEnergia(), x + OFFSET_X_BARRA, y +
				OFFSET_Y_BARRA_ENER, ANCHOBARRA, ALTOENERGIA,
				null);
		g.drawString(String.valueOf(personaje.getEnergiaTope()) +
				" / " + String.valueOf(personaje.getEnergiaTope()),
				x + X_OFFSET_BARRA_GEN, y + OFFSET_Y_BARRA_ENE);

		if (personaje.getExperiencia() ==
				Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]) {
			drawBarra = ANCHOBARRA;
		} else {
			drawBarra = (personaje.getExperiencia() * ANCHOBARRA)
					/ Personaje.getTablaDeNiveles()[personaje.getNivel() + 1];
		}
		g.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO1));
		g.drawImage(Recursos.getBarraExperiencia(), x + OFFSET_BARRA_EXP_X,
				y + OFFSET_BARRA_Y_EXP, drawBarra,
				ALTOEXPERIENCIA, null);
		g.drawString(
				String.valueOf(personaje.getExperiencia()) + " / "
				+ String.valueOf(Personaje.getTablaDeNiveles()[personaje.getNivel() + 1]),
				x + X_OFFSET_BARRA_GEN, y + OFFSET_Y_BARRA1);
		g.setFont(new Font("Tahoma", Font.PLAIN, TAM_TEXTO));
		g.setColor(Color.GREEN);
		g.drawString(String.valueOf(personaje.getNivel()),
				x + OFFSET_X_BARRA1, y + OFFSET_Y_BARRA1);
	}
}
