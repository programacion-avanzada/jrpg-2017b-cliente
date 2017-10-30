package comandos;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;

public class InicioSesion extends ComandosCliente {

    @Override
    public void ejecutar() {
	Paquete paquete = (Paquete) gson.fromJson(cadenaLeida, Paquete.class);
	if (paquete.getMensaje().equals(Paquete.msjExito)) {

	    // El usuario ya inicio sesi�n
	    cliente.getPaqueteUsuario().setInicioSesion(true);

	    // Recibo el paquete personaje con los datos
	    cliente.setPaquetePersonaje(gson.fromJson(cadenaLeida, PaquetePersonaje.class));

	} else {
	    if (paquete.getMensaje().equals(Paquete.msjFracaso))
		JOptionPane.showMessageDialog(null, "Error al iniciar sesión." + " Revise el usuario y la contraseña");

	    // El usuario no pudo iniciar sesión
	    cliente.getPaqueteUsuario().setInicioSesion(false);
	}

    }

}
