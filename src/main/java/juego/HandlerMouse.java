package juego;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The Class HandlerMouse.
 */
public class HandlerMouse implements MouseListener {

    /** The pos mouse. */
    private int posMouse[];
    
    /** The pos mouse recorrido. */
    private int posMouseRecorrido[];
    
    /** The nuevo recorrido. */
    private boolean nuevoRecorrido;
    
    /** The nuevo click. */
    private boolean nuevoClick;

    /**
     * Instantiates a new handler mouse.
     */
    public HandlerMouse() {
	posMouse = new int[2];
	posMouseRecorrido = new int[2];
    }


    @Override
    public void mouseClicked(final MouseEvent e) {

    }


    @Override
    public void mouseEntered(final MouseEvent arg0) {

    }


    @Override
    public void mouseExited(final MouseEvent arg0) {

    }


    @Override
    public void mousePressed(final MouseEvent arg0) {

    }


    @Override
    public void mouseReleased(final MouseEvent e) {
	// System.out.println("MOUSE RELEASED");
	if (e.getButton() == MouseEvent.BUTTON1) {
	    posMouse[0] = e.getX();
	    posMouse[1] = e.getY();
	    nuevoClick = true;
	} else if (e.getButton() == MouseEvent.BUTTON3) {
	    posMouseRecorrido[0] = e.getX();
	    posMouseRecorrido[1] = e.getY();
	    nuevoRecorrido = true;
	}
    }

    /**
     * Gets the pos mouse.
     * @return the pos mouse
     */
    public int[] getPosMouse() {
	return posMouse;
    }

    /**
     * Gets the pos mouse recorrido.
     * @return the pos mouse recorrido
     */
    public int[] getPosMouseRecorrido() {
	return posMouseRecorrido;
    }

    /**
     * Gets the nuevo recorrido.
     * @return the nuevo recorrido
     */
    public boolean getNuevoRecorrido() {
	return nuevoRecorrido;
    }

    /**
     * Sets the nuevo recorrido.
     * @param b the new nuevo recorrido
     */
    public void setNuevoRecorrido(final boolean b) {
	nuevoRecorrido = b;
    }

    /**
     * Gets the nuevo click.
     * @return the nuevo click
     */
    public boolean getNuevoClick() {
	return nuevoClick;
    }

    /**
     * Sets the nuevo click.
     * @param b the new nuevo click
     */
    public void setNuevoClick(final boolean b) {
	nuevoClick = b;
    }
}
