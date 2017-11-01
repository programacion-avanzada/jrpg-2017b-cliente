package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import juego.Pantalla;
import mensajeria.Paquete;
import mensajeria.PaqueteNpc;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Menu que contiene la informacion del personaje
 */
public class MenuInfoPersonaje {

    private static final int GET_6 = 6;
    private static final int OFFSET_36 = 36;
    private static final int OFFSET_12 = 12;
    private static final int OFFSET_4 = 4;
    private static final int OFFSET_24 = 24;
    private static final int OFFSET_405 = 405;
    private static final int OFFSET_150 = 150;
    private static final int OFFSET_320 = 320;
    private static final int OFFSET_260 = 260;
    private static final int OFFSET_30 = 30;
    private static final int OFFSET_325 = 325;
    private static final int TAM_TXT5 = 62;
    private static final int TAM_TXT4 = 20;
    private static final int TAM_TXT3 = 18;
    private static final int TAM_TXT2 = 20;
    private static final int TAM_TXT1 = 14;
    private static final int OFFSET_240 = 240;
    private static final int OFFSET_18 = 18;
    private static final int OFFSET_330 = 330;
    private static final int OFFSET_230 = 230;
    private static final int OFFSET_310 = 310;
    private static final int OFFSET_290 = 290;
    private static final int OFFSET_270 = 270;
    private static final int OFFSET_250 = 250;
    private static final int OFFSET_25 = 25;
    private static final int OFFSET_200 = 200;
    private static final int OFFSET_380 = 380;
    private static final int OFFSET_50 = 50;
    private static final int OFFSET_15 = 15;
    private static final int OFFSET_20 = 20;
    private static final int OFFSET_128 = 128;
    private static final int OFFSET_70 = 70;
    private static final int OFFSET_100 = 100;
    private static final int ANCHOPERSONAJE = OFFSET_128;
    private static final BufferedImage MENU = Recursos.getMenuEnemigo();
    public static final int MENUBATALLAR = 0;
    public static final int MENUINFORMACION = 1;
    public static final int MENUSUBIRNIVEL = 2;
    public static final int MENUGANARBATALLA = 3;
    public static final int MENUPERDERBATALLA = OFFSET_4;
    public static final int MENUGANARITEM = 5;
    public static final int MENUCOMERCIAR = GET_6;
    private static final String[] LEYENDABOTON = {"Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar", "Aceptar",
	    "Comerciar" };

    private int x;
    private int y;
    private PaquetePersonaje personaje;
    private PaqueteNpc npc;
    private boolean esNPC;

    /**
     * Instantiates a new menu info personaje.
     * @param x the x
     * @param y the y
     * @param personaje the personaje
     */
    // A lo mejor más adelante conviene hacer un MenuInfoNpc
    public MenuInfoPersonaje(final int x, final int y, final Paquete personaje) {
	this.x = x;
	this.y = y;

	if (personaje instanceof PaquetePersonaje) {
	    this.personaje = (PaquetePersonaje) personaje;
	    esNPC = false;
	} else {
	    this.npc = (PaqueteNpc) personaje;
	    esNPC = true;
	}
    }

    /**
     * Graficar.
     * @param g the g
     * @param tipoMenu the tipo menu
     */
    public void graficar(final Graphics g, final int tipoMenu) {

	// dibujo el menu
	g.drawImage(MENU, x, y, null);

	if (!esNPC) {
	    // dibujo el personaje
	    g.drawImage(Recursos.getPersonaje().get(personaje.getRaza()).get(GET_6)[0],
		    x + MENU.getWidth() / 2 - ANCHOPERSONAJE / 2, y + OFFSET_70, OFFSET_128, OFFSET_128, null);

	    // muestro el nombre
	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Book Antiqua", 1, OFFSET_20));
	    Pantalla.centerString(g, new Rectangle(x, y + OFFSET_15, MENU.getWidth(), 0), personaje.getNombre());
	} else {
	    // dibujo el npc
	    g.drawImage(Recursos.getPersonaje().get(npc.getRaza()).get(GET_6)[0],
		    x + MENU.getWidth() / 2 - ANCHOPERSONAJE / 2, y + OFFSET_70, OFFSET_128, OFFSET_128, null);

	    // muestro el nombre
	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Book Antiqua", 1, OFFSET_20));
	    Pantalla.centerString(g, new Rectangle(x, y + OFFSET_15, MENU.getWidth(), 0), npc.getNombre());
	}

	// Grafico la leyenda segun el tipo de menu
	switch (tipoMenu) {
	case MENUBATALLAR:
	    graficarMenuInformacion(g);
	    break;
	case MENUINFORMACION:
	    graficarMenuInformacion(g);
	    break;
	case MENUSUBIRNIVEL:
	    graficarMenuSubirNivel(g);
	    break;
	case MENUGANARBATALLA:
	    graficarMenuGanarBatalla(g);
	    break;
	case MENUPERDERBATALLA:
	    graficarMenuPerderBatalla(g);
	    break;
	case MENUGANARITEM:
	    graficarMenuItem(g);
	    break;
	case MENUCOMERCIAR:
	    graficarMenuComerciar(g);
	    break;
	default:
	    break;
	}

	// muestro los botones
	g.setFont(new Font("Book Antiqua", 1, OFFSET_20));
	g.drawImage(Recursos.getBotonMenu(), x + OFFSET_50, y + OFFSET_380, OFFSET_200, OFFSET_25, null);
	g.setColor(Color.WHITE);
	Pantalla.centerString(g, new Rectangle(x + OFFSET_50, y + OFFSET_380, OFFSET_200, OFFSET_25),
		LEYENDABOTON[tipoMenu]);
    }

    /**
     * Graficar menu perder batalla.
     * @param g the g
     */
    private void graficarMenuPerderBatalla(final Graphics g) {

	// Informo que perdió la batalla
	g.setColor(Color.BLACK);
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), "¡Has sido derrotado!");

	g.setFont(new Font("Book Antiqua", 0, TAM_TXT1));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_250, MENU.getWidth(), 0), "¡No te rindas! Sigue luchando");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_270, MENU.getWidth(), 0), "contra los demás personajes");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_290, MENU.getWidth(), 0), "para aumentar tu nivel y");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_310, MENU.getWidth(), 0), "mejorar tus atributos.");
    }

    /**
     * Graficar menu ganar batalla.
     * @param g the g
     */
    private void graficarMenuGanarBatalla(final Graphics g) {

	// Informo que ganó la batalla
	g.setColor(Color.BLACK);
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), "¡Has derrotado");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_230, MENU.getWidth(), 0), "a tu enemigo!");

	g.setFont(new Font("Book Antiqua", 0, TAM_TXT1));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_270, MENU.getWidth(), 0),
		"¡Felicitaciones! Has derrotado");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_290, MENU.getWidth(), 0), "a tu oponente, sigue así");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_310, MENU.getWidth(), 0), "para lograr subir de nivel");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_330, MENU.getWidth(), 0), "y mejorar tus atributos.");

    }

    /**
     * Graficar menu subir nivel.
     * @param g the g
     */
    private void graficarMenuSubirNivel(final Graphics g) {

	// Informo que subió de nivel
	g.setColor(Color.BLACK);
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), "¡Has subido de nivel!");

	g.setFont(new Font("Book Antiqua", 0, OFFSET_18));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_240, MENU.getWidth(), 0), "¡Felicitaciones!");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_270, MENU.getWidth(), 0), "Nuevo Nivel");
	g.setFont(new Font("Book Antiqua", 1, TAM_TXT5));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_325, MENU.getWidth(), 0),
		String.valueOf(personaje.getNivel()));

    }

    /**
     * Graficar menu informacion.
     * @param g the g
     */
    public void graficarMenuInformacion(final Graphics g) {
	if (!esNPC) {
	    // muestro los nombres de los atributos
	    g.setColor(Color.BLACK);
	    Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), personaje.getRaza());
	    g.drawString("Casta: ", x + OFFSET_30, y + OFFSET_260);
	    g.drawString("Nivel: ", x + OFFSET_30, y + OFFSET_290);
	    g.drawString("Experiencia: ", x + OFFSET_30, y + OFFSET_320);

	    // muestro los atributos
	    g.setFont(new Font("Book Antiqua", 0, TAM_TXT2));
	    g.drawString(personaje.getCasta(), x + OFFSET_100, y + OFFSET_260);
	    g.drawString(personaje.getNivel() + " ", x + OFFSET_100, y + OFFSET_290);
	    g.drawString(personaje.getExperiencia() + " / " + Personaje.getTablaDeNiveles()[personaje.getNivel() + 1],
		    x + OFFSET_150, y + OFFSET_320);
	} else {
	    // muestro los nombres de los atributos
	    g.setColor(Color.BLACK);
	    Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), npc.getRaza());
	    g.drawString("Casta: ", x + OFFSET_30, y + OFFSET_260);
	    g.drawString("Nivel: ", x + OFFSET_30, y + OFFSET_290);

	    // muestro los atributos
	    g.setFont(new Font("Book Antiqua", 0, TAM_TXT2));
	    g.drawString(npc.getCasta(), x + OFFSET_100, y + OFFSET_260);
	    g.drawString(npc.getNivel() + " ", x + OFFSET_100, y + OFFSET_290);
	}
    }

    /**
     * Graficar menu item.
     * @param g the g
     */
    private void graficarMenuItem(final Graphics g) {

	// Informo que subió de nivel
	g.setColor(Color.BLACK);
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), "¡Aca iria algo!");

	g.setFont(new Font("Book Antiqua", 0, TAM_TXT3));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_240, MENU.getWidth(), 0), "¡Aca otra cosa!");
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_270, MENU.getWidth(), 0), "Nuevo Nivel");
	g.setFont(new Font("Book Antiqua", 1, TAM_TXT5));
	Pantalla.centerString(g, new Rectangle(x, y + OFFSET_325, MENU.getWidth(), 0),
		String.valueOf(personaje.getNivel()));

    }

    /**
     * Graficar menu comerciar.
     * @param g the g
     */
    private void graficarMenuComerciar(final Graphics g) {
	if (!esNPC) {
	    // muestro los nombres de los atributos
	    g.setColor(Color.BLACK);
	    Pantalla.centerString(g, new Rectangle(x, y + OFFSET_200, MENU.getWidth(), 0), personaje.getRaza());
	    g.drawString("Casta: ", x + OFFSET_30, y + OFFSET_260);
	    g.drawString("Nivel: ", x + OFFSET_30, y + OFFSET_290);
	    g.drawString("Experiencia: ", x + OFFSET_30, y + OFFSET_320);

	    // muestro los atributos
	    g.setFont(new Font("Book Antiqua", 0, TAM_TXT4));
	    g.drawString(personaje.getCasta(), x + OFFSET_100, y + OFFSET_260);
	    g.drawString(personaje.getNivel() + " ", x + OFFSET_100, y + OFFSET_290);
	    g.drawString(personaje.getExperiencia() + " / " + Personaje.getTablaDeNiveles()[personaje.getNivel() + 1],
		    x + OFFSET_150, y + OFFSET_320);
	}

    }

    /**
     * Click en boton.
     * @param mouseX the mouse X
     * @param mouseY the mouse Y
     * @return true, if successful
     */
    public boolean clickEnBoton(final int mouseX, final int mouseY) {
	if (mouseX >= x + OFFSET_50 && mouseX <= x + OFFSET_250 && mouseY >= y + OFFSET_380
		&& mouseY <= y + OFFSET_405) {
	    return true;
	}
	return false;
    }

    /**
     * Click en cerrar.
     * @param mouseX the mouse X
     * @param mouseY the mouse Y
     * @return true, if successful
     */
    public boolean clickEnCerrar(final int mouseX, final int mouseY) {
	if (mouseX >= x + MENU.getWidth() - OFFSET_24 && mouseX <= x + MENU.getWidth() + OFFSET_4
		&& mouseY >= y + OFFSET_12 && mouseY <= y + OFFSET_36) {
	    return true;
	}
	return false;
    }

    /**
     * Click en menu.
     * @param mouseX the mouse X
     * @param mouseY the mouse Y
     * @return true, if successful
     */
    public boolean clickEnMenu(final int mouseX, final int mouseY) {
	if (mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight()) {
	    return true;
	}
	return false;
    }

    /**
     * Es NPC.
     * @return true, if successful
     */
    public boolean esNPC() {
	return esNPC;
    }

    /**
     * Sets the es NPC.
     * @param esNPC the new es NPC
     */
    public void setEsNPC(final boolean esNPC) {
	this.esNPC = esNPC;
    }

    /**
     * Gets the personaje.
     * @return the personaje
     */
    public PaquetePersonaje getPersonaje() {
	return personaje;
    }

    /**
     * Sets the personaje.
     * @param personaje the new personaje
     */
    public void setPersonaje(final PaquetePersonaje personaje) {
	this.personaje = personaje;
    }

    /**
     * Gets the npc.
     * @return the npc
     */
    public PaqueteNpc getNpc() {
	return npc;
    }

    /**
     * Sets the npc.
     * @param npc the new npc
     */
    public void setNpc(final PaqueteNpc npc) {
	this.npc = npc;
    }
}
