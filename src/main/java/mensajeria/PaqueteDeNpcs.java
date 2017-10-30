package mensajeria;

import java.io.Serializable;
import java.util.Map;

public class PaqueteDeNpcs extends Paquete implements Serializable, Cloneable {
    private Map<Integer, PaqueteMovimiento> ubicacionNpcs;
    private Map<Integer, PaqueteNpc> paquetesNpcs;

    public PaqueteDeNpcs() {
	this.ubicacionNpcs = null;
	this.paquetesNpcs = null;
    }

    public PaqueteDeNpcs(Map<Integer, PaqueteNpc> paquetesNpcs, Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
	this.ubicacionNpcs = ubicacionNpcs;
	this.paquetesNpcs = paquetesNpcs;
    }

    public Map<Integer, PaqueteMovimiento> getUbicacionNpcs() {
	return ubicacionNpcs;
    }

    public void setUbicacionNpcs(Map<Integer, PaqueteMovimiento> ubicacionNpcs) {
	this.ubicacionNpcs = ubicacionNpcs;
    }

    public Map<Integer, PaqueteNpc> getPaquetesNpcs() {
	return paquetesNpcs;
    }

    public void setPaquetesNpcs(Map<Integer, PaqueteNpc> paquetesNpcs) {
	this.paquetesNpcs = paquetesNpcs;
    }

    @Override
    public Object clone() {
	Object obj = null;
	obj = super.clone();
	return obj;
    }
}
