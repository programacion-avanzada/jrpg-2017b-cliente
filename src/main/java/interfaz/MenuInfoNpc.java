package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import juego.Pantalla;
import mensajeria.PaqueteNpc;
import recursos.Recursos;

/**
 *Clase MenuInfoNpc
 */
public class MenuInfoNpc {

    private static final int OFFSET_Y_CERRAR_MAX = 36;
    private static final int OFFSET_Y_CERRAR = 12;
    private static final int OFFSET_MAX_CERRAR = 4;
    private static final int OFFSET_X_CERRAR = 24;
    private static final int MOUSE_MAX_Y = 405;
    private static final int MOUSE_MAX_X = 250;
    private static final int GET_6 = 6;
    private static final int OFFSET_Y_NOMBRE = 15;
    private static final int OFFSET_Y_PERSONAJE = 70;
    private static final int ALTO_PERSONAJE = 128;
    private static final int ANCHO_PERSONAJE = 128;
    private static final int OFFSET_Y_ATRIBUTOS = 290;
    private static final int OFFSET_X_ATRIB = 100;
    private static final int OFFSET_Y_MENU = 260;
    private static final int OFFSET_X_MENU = 30;
    private static final int ALTO_IMAGEN = 25;
    private static final int ANCHO_IMAGEN = 200;
    private static final int OFFSET_X = 50;
    private static final int OFFSET_Y = 380;
    private static final int TAM_FUENTE = 20;
    private static final int ANCHOPERSONAJE = 128;
    private static final BufferedImage MENU = Recursos.getMenuEnemigo();
    public static final int MENUBATALLAR = 0;
    public static final int MENUINFORMACION = 1;
    public static final int MENUSUBIRNIVEL = 2;
    public static final int MENUGANARBATALLA = 3;
    public static final int MENUPERDERBATALLA = 4;
    public static final int MENUGANARITEM = 5;
    public static final int MENUCOMERCIAR = 6;
    private static final String[] LEYENDABOTON = {"Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar",
	    "Comerciar" };

    private int x;
    private int y;
    private PaqueteNpc npc;

    /**
     * @param x parametro x
     * @param y	parametro y
     * @param npc	parametro npc
     */
    public MenuInfoNpc(final int x, final int y, final PaqueteNpc npc) {
	this.x = x;
	this.y = y;
	this.npc = npc;
    }

    /**
     * @param g parametro g
     * @param tipoMenu	parametro tipoMenu
     */
    public void graficar(final Graphics g, final int tipoMenu) {

	// dibujo el menu
	g.drawImage(MENU, x, y, null);

	// dibujo el personaje
	g.drawImage(Recursos.getPersonaje().get(npc.getRaza()).get(GET_6)[0],
		x + MENU.getWidth() / 2 - ANCHOPERSONAJE / 2, y + OFFSET_Y_PERSONAJE, ANCHO_PERSONAJE, ALTO_PERSONAJE,
		null);

	// muestro el nombre
	g.setColor(Color.WHITE);
	g.setFont(new Font("Book Antiqua", 1, TAM_FUENTE));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_Y_NOMBRE, MENU.getWidth(), 0), npc.getNombre());

	// Grafico la leyenda segun el tipo de menu
	switch (tipoMenu) {
	case MENUBATALLAR:
	    graficarMenuInformacion(g);
	    break;
	case MENUINFORMACION:
	    graficarMenuInformacion(g);
	    break;
	default:
	    break;
	/*
	 * case menuComerciar: graficarMenuComerciar(g); break;
	 */
	}

	// muestro los botones
	g.setFont(new Font("Book Antiqua", 1, TAM_FUENTE));
	g.drawImage(Recursos.getBotonMenu(), x + OFFSET_X, y + OFFSET_Y, ANCHO_IMAGEN, ALTO_IMAGEN, null);
	g.setColor(Color.WHITE);
	Pantalla.centerString(g, new Rectangle(x + OFFSET_X, y + OFFSET_Y, ANCHO_IMAGEN, ALTO_IMAGEN),
		LEYENDABOTON[tipoMenu]);
    }

    /**
     * @param g parametro g
     */
    public void graficarMenuInformacion(final Graphics g) {

	// muestro los nombres de los atributos
	g.setColor(Color.BLACK);
	Pantalla.centerString(g, new Rectangle(x, y + ANCHO_IMAGEN, MENU.getWidth(), 0), npc.getRaza());
	g.drawString("Casta: ", x + OFFSET_X_MENU, y + OFFSET_Y_MENU);
	g.drawString("Nivel: ", x + OFFSET_X_MENU, y + OFFSET_Y_ATRIBUTOS);

	// muestro los atributos
	g.setFont(new Font("Book Antiqua", 0, TAM_FUENTE));
	g.drawString(npc.getCasta(), x + OFFSET_X_ATRIB, y + OFFSET_Y_MENU);
	g.drawString(npc.getNivel() + " ", x + OFFSET_X_ATRIB, y + OFFSET_Y_ATRIBUTOS);

    }

    /*
     * private void graficarMenuComerciar(Graphics g){ // muestro los nombres de
     * los atributos g.setColor(Color.BLACK); Pantalla.centerString(g, new
     * Rectangle(x, y + 200, menu.getWidth(), 0), personaje.getRaza());
     * g.drawString("Casta: ", x + 30, y + 260); g.drawString("Nivel: ", x + 30,
     * y + 290); g.drawString("Experiencia: ", x + 30, y + 320); // muestro los
     * atributos g.setFont(new Font("Book Antiqua", 0, 20));
     * g.drawString(personaje.getCasta(), x + 100, y + 260);
     * g.drawString(personaje.getNivel() + " ", x + 100, y + 290);
     * g.drawString(personaje.getExperiencia() + " / " +
     * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1], x + 150, y +
     * 320); }
     */

    public boolean clickEnBoton(final int mouseX, final int mouseY) {
	return (mouseX >= x + OFFSET_X && mouseX <= x + MOUSE_MAX_X && mouseY >= y + OFFSET_Y
		&& mouseY <= y + MOUSE_MAX_Y);
    }

    public boolean clickEnCerrar(final int mouseX, final int mouseY) {
	return (mouseX >= x + MENU.getWidth() - OFFSET_X_CERRAR && mouseX <= x + MENU.getWidth() + OFFSET_MAX_CERRAR
		&& mouseY >= y + OFFSET_Y_CERRAR && mouseY <= y + OFFSET_Y_CERRAR_MAX);
    }

    public boolean clickEnMenu(final int mouseX, final int mouseY) {
	return (mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight());
    }
}
