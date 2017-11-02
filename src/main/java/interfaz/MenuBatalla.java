package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import dominio.Personaje;
import juego.Pantalla;
import recursos.Recursos;

public class MenuBatalla {

    private static final int ALTO_IMAGEN = 20;
    private static final int Y_OFFSET_HABILIDAD_GEN2 = 168;
    private static final int Y_OFFSET_HABILIDAD_GEN1 = 94;
    private static final int X_OFFSET_HABILIDAD_GEN3 = 442;
    private static final int X_OFFSET_HABILIDAD_GEN2 = 268;
    private static final int X_OFFSET_HABILIDAD_GEN1 = 95;
    private static final int TAM_FUENTE = 14;
    private static final int CINCO = 5;
    private static final int CUATRO = 4;
    private static final int TRES = 3;
    private static final int X_OFFSET_3 = 394;
    private static final int X_OFFSET_2 = 221;
    private static final int Y_OFFSET_2 = 146;
    private static final int Y_OFFSET_1 = 72;
    private static final int X_OFFSET_1 = 48;
    private static final int ANCHO_BOTON = 40;
    private static final int X = 100;
    private static final int Y = 380;
    private static final int ANCHOBOTON = ANCHO_BOTON;
    private static final int[][] BOTONES = {{X + X_OFFSET_1, Y + Y_OFFSET_1 }, {X + X_OFFSET_1, Y + Y_OFFSET_2 },
	    {X + X_OFFSET_2, Y + Y_OFFSET_1 }, {X + X_OFFSET_2, Y + Y_OFFSET_2 }, {X + X_OFFSET_3, Y + Y_OFFSET_1 },
	    {X + X_OFFSET_3, Y + Y_OFFSET_2 } };
    private boolean habilitado;
    private Personaje personaje;

    public MenuBatalla(final boolean habilitado, final Personaje personaje) {
	this.habilitado = habilitado;
	this.personaje = personaje;
    }

    public void graficar(final Graphics g) {

	if (habilitado) {
	    g.drawImage(Recursos.getMenuBatalla(), X, Y, null);
	} else {
	    g.drawImage(Recursos.getMenuBatallaDeshabilitado(), X, Y, null);
	}

	// Dibujo los botones
	g.drawImage(Recursos.getHabilidades().get(personaje.getHabilidadesRaza()[0]), BOTONES[0][0], BOTONES[0][1],
		ANCHOBOTON, ANCHOBOTON, null);
	g.drawImage(Recursos.getHabilidades().get(personaje.getHabilidadesRaza()[1]), BOTONES[1][0], BOTONES[1][1],
		ANCHOBOTON, ANCHOBOTON, null);
	g.drawImage(Recursos.getHabilidades().get(personaje.getHabilidadesCasta()[0]), BOTONES[2][0], BOTONES[2][1],
		ANCHOBOTON, ANCHOBOTON, null);
	g.drawImage(Recursos.getHabilidades().get(personaje.getHabilidadesCasta()[1]), BOTONES[TRES][0],
		BOTONES[TRES][1], ANCHOBOTON, ANCHOBOTON, null);
	g.drawImage(Recursos.getHabilidades().get(personaje.getHabilidadesCasta()[2]), BOTONES[CUATRO][0],
		BOTONES[CUATRO][1], ANCHOBOTON, ANCHOBOTON, null);
	g.drawImage(Recursos.getHabilidades().get("Ser Energizado"), BOTONES[CINCO][0], BOTONES[CINCO][1], ANCHOBOTON,
		ANCHOBOTON, null);

	// Dibujo las leyendas
	g.setFont(new Font("Book Antiqua", 1, TAM_FUENTE));
	g.drawString(personaje.getHabilidadesRaza()[0], X + X_OFFSET_HABILIDAD_GEN1, Y + Y_OFFSET_HABILIDAD_GEN1);
	g.drawString(personaje.getHabilidadesRaza()[1], X + X_OFFSET_HABILIDAD_GEN1, Y + Y_OFFSET_HABILIDAD_GEN2);
	g.drawString(personaje.getHabilidadesCasta()[0], X + X_OFFSET_HABILIDAD_GEN2, Y + Y_OFFSET_HABILIDAD_GEN1);
	g.drawString(personaje.getHabilidadesCasta()[1], X + X_OFFSET_HABILIDAD_GEN2, Y + Y_OFFSET_HABILIDAD_GEN2);
	g.drawString(personaje.getHabilidadesCasta()[2], X + X_OFFSET_HABILIDAD_GEN3, Y + Y_OFFSET_HABILIDAD_GEN1);
	g.drawString("Ser energizado", X + X_OFFSET_HABILIDAD_GEN3, Y + Y_OFFSET_HABILIDAD_GEN2);

	// Dibujo el turno de quien es
	g.setColor(Color.WHITE);
	if (habilitado) {
	    Pantalla.centerString(g, new Rectangle(X, Y + CINCO, Recursos.getMenuBatalla().getWidth(), ALTO_IMAGEN),
		    "Mi Turno");
	} else {
	    Pantalla.centerString(g, new Rectangle(X, Y + CINCO, Recursos.getMenuBatalla().getWidth(), ALTO_IMAGEN),
		    "Turno Rival");
	}

    }

    public int getBotonClickeado(final int mouseX, final int mouseY) {
	if (!habilitado) {
	    return 0;
	}
	for (int i = 0; i < BOTONES.length; i++) {
	    if (mouseX >= BOTONES[i][0] && mouseX <= BOTONES[i][0] + ANCHOBOTON && mouseY >= BOTONES[i][1]
		    && mouseY <= BOTONES[i][1] + ANCHOBOTON) {
		return i + 1;
	    }
	}
	return 0;
    }

    public boolean clickEnMenu(final int mouseX, final int mouseY) {
	if (mouseX >= X && mouseX <= X + Recursos.getMenuBatalla().getWidth() && mouseY >= Y
		&& mouseY <= Y + Recursos.getMenuBatalla().getHeight()) {
	    return habilitado;
	}
	return false;
    }

    public void setHabilitado(final boolean b) {
	habilitado = b;
    }
}
