package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Utilitarias {

    /**
     * Archivo A string.
     * @param path the path
     * @return the string
     */
    public static String archivoAString(final String path) {
	StringBuilder builder = new StringBuilder();

	try {
	    BufferedReader br = new BufferedReader(new FileReader(path));
	    String linea;

	    while ((linea = br.readLine()) != null) {
		builder.append(linea + System.lineSeparator());
	    }

	    br.close();
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Fallo al intentar cargar el mapa " + path);
	}

	return builder.toString();
    }

    /**
     * Parses the int.
     * @param numero the numero
     * @return the int
     */
    public static int parseInt(final String numero) {
	try {
	    return Integer.parseInt(numero);
	} catch (NumberFormatException e) {

	    return 0;
	}
    }

}
