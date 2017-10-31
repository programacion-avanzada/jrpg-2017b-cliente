package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import juego.Pantalla;
import mensajeria.PaqueteNpc;
import recursos.Recursos;

public class MenuInfoNpc {

    private static final int ANCHOPERSONAJE = 128;
    private static final BufferedImage menu = Recursos.getMenuEnemigo();
    public static final int MENUBATALLAR = 0;
    public static final int MENUINFORMACION = 1;
    public static final int MENUSUBIRNIVEL = 2;
    public static final int MENUGANARBATALLA = 3;
    public static final int MENUPERDERBATALLA = 4;
    public static final int MENUGANARITEM = 5;
    public static final int MENUCOMERCIAR = 6;
    private static final String[] leyendaBoton = { "Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar",
	    "Comerciar" };

    private int x;
    private int y;
    private PaqueteNpc npc;

    public MenuInfoNpc(final int x, final int y, final PaqueteNpc npc) {
	this.x = x;
	this.y = y;
	this.npc = npc;
    }

    public void graficar(final Graphics g, final int tipoMenu) {

	// dibujo el menu
	g.drawImage(menu, x, y, null);

	// dibujo el personaje
	g.drawImage(Recursos.getPersonaje().get(npc.getRaza()).get(6)[0], x + menu.getWidth() / 2 - ANCHOPERSONAJE / 2,
		y + 70, 128, 128, null);

	// muestro el nombre
	g.setColor(Color.WHITE);
	g.setFont(new Font("Book Antiqua", 1, 20));
	Pantalla.centerString(g, new Rectangle(x, y + 15, menu.getWidth(), 0), npc.getNombre());

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
	g.setFont(new Font("Book Antiqua", 1, 20));
	g.drawImage(Recursos.getBotonMenu(), x + 50, y + 380, 200, 25, null);
	g.setColor(Color.WHITE);
	Pantalla.centerString(g, new Rectangle(x + 50, y + 380, 200, 25), leyendaBoton[tipoMenu]);
    }

    public void graficarMenuInformacion(final Graphics g) {

	// muestro los nombres de los atributos
	g.setColor(Color.BLACK);
	Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0), npc.getRaza());
	g.drawString("Casta: ", x + 30, y + 260);
	g.drawString("Nivel: ", x + 30, y + 290);

	// muestro los atributos
	g.setFont(new Font("Book Antiqua", 0, 20));
	g.drawString(npc.getCasta(), x + 100, y + 260);
	g.drawString(npc.getNivel() + " ", x + 100, y + 290);

    }

    /*
     * private void graficarMenuComerciar(Graphics g){
     * 
     * // muestro los nombres de los atributos g.setColor(Color.BLACK);
     * Pantalla.centerString(g, new Rectangle(x, y + 200, menu.getWidth(), 0),
     * personaje.getRaza()); g.drawString("Casta: ", x + 30, y + 260);
     * g.drawString("Nivel: ", x + 30, y + 290); g.drawString("Experiencia: ", x
     * + 30, y + 320);
     * 
     * // muestro los atributos g.setFont(new Font("Book Antiqua", 0, 20));
     * g.drawString(personaje.getCasta(), x + 100, y + 260);
     * g.drawString(personaje.getNivel() + " ", x + 100, y + 290);
     * g.drawString(personaje.getExperiencia() + " / " +
     * Personaje.getTablaDeNiveles()[personaje.getNivel() + 1], x + 150, y +
     * 320);
     * 
     * }
     */

    public boolean clickEnBoton(final int mouseX, final int mouseY) {
	return (mouseX >= x + 50 && mouseX <= x + 250 && mouseY >= y + 380 && mouseY <= y + 405);
    }

    public boolean clickEnCerrar(final int mouseX, final int mouseY) {
	return (mouseX >= x + menu.getWidth() - 24 && mouseX <= x + menu.getWidth() + 4 && mouseY >= y + 12
		&& mouseY <= y + 36);
    }

    public boolean clickEnMenu(final int mouseX, final int mouseY) {
	return (mouseX >= x && mouseX <= x + menu.getWidth() && mouseY >= y && mouseY <= y + menu.getHeight());
    }
}
