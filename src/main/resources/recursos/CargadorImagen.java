package recursos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * 	
 * Clase que carga las imagenes de los recursos
 *
 */
public class CargadorImagen {

	/**
	 * Constructor de la clase
	 * @param path ruta de la imagen a cargar
	 * @return	devuelve la imgaen cargada
	 */
    public static BufferedImage cargarImagen(final String path) {
	try {
	    return ImageIO.read(CargadorImagen.class.getResource(path));
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Error al cargar el archivo " + path);
	}

	return null;
    }
}
