package mensajeria;

import java.io.Serializable;

import estados.Estado;

/**
 * Clase que contine los datos empaquetados del NPC
 */
public class PaqueteNpc extends Paquete implements Serializable, Cloneable {
    // Por ahora tiene todo lo mismo que un personaje para poder tratarlos de
    // forma similar.

    private int idMapa;
    private int estado;
    private String casta;
    private String nombre;
    private String raza;
    private int id;
    private int nivel = 1; // Se usaría solo como un indicador del poder del npc

    // Por ahora la IA no usa estos stats, a futuro vemos que onda porque la que
    // hay ahora
    // es bastante cavernícola
    private int saludTope;
    private int energiaTope;
    private int fuerza;
    private int destreza;
    private int inteligencia;
    private int experienciaOtorga;

    // Constructor no útil por el momento.
    /**
     * @param id id del NPC
     * @param saludTope salud maxima del NPC
     * @param energiaTope energia maxima del NPC
     * @param fuerza fuerza del NPC
     * @param destreza destreza del NPC
     * @param inteligencia inteligencia del NPC
     * @param nivel nivel del NPC
     * @param experienciaOtorga experiencia que otorga ganar al NPC
     * @param nombre nombre del NPC
     * @param raza raza del NPC
     * @param casta casta del NPC
     */
    public PaqueteNpc(final int id, final int saludTope, final int energiaTope, final int fuerza, final int destreza,
	    final int inteligencia, final int nivel, final int experienciaOtorga, final String nombre,
	    final String raza, final String casta) {
	super();
	this.id = id;
	this.saludTope = saludTope;
	this.energiaTope = energiaTope;
	this.fuerza = fuerza;
	this.destreza = destreza;
	this.inteligencia = inteligencia;
	this.nivel = nivel;
	this.experienciaOtorga = experienciaOtorga;
	this.nombre = nombre;
	this.raza = raza;
	this.casta = casta;
	this.estado = Estado.getEstadoJuego();
    }

    /**
     * Constructor del paquete NPC
     * @param id id del NPC
     * @param nivel nivel del NPC
     * @param nombre nombre del NPC
     * @param raza raza del NPC
     * @param casta casta del NPC
     */
    public PaqueteNpc(final int id, final int nivel, final String nombre, final String raza, final String casta) {
	super();
	this.id = id;
	this.nivel = nivel;
	this.nombre = nombre;
	this.raza = raza;
	this.casta = casta;
	this.estado = Estado.getEstadoJuego();
    }

    /**
     * @return nombre del NPC
     */
    public String getNombre() {
	return nombre;
    }

    /**
     * @param nombre nombre del NPC
     */
    public void setNombre(final String nombre) {
	this.nombre = nombre;
    }

    /**
     * @return identificador del NPC
     */
    public int getId() {
	return id;
    }

    /**
     * @param id identificador del NPC
     */
    public void setId(final int id) {
	this.id = id;
    }

    /**
     * @return salud maxima del NPC
     */
    public int getSaludTope() {
	return saludTope;
    }

    /**
     * @param saludTope salud maxima del NPC
     */
    public void setSaludTope(final int saludTope) {
	this.saludTope = saludTope;
    }

    /**
     * @return energia maxima del NPC
     */
    public int getEnergiaTope() {
	return energiaTope;
    }

    /**
     * @param energiaTope energia maxima del NPC
     */
    public void setEnergiaTope(final int energiaTope) {
	this.energiaTope = energiaTope;
    }

    /**
     * @return fuerza del NPC
     */
    public int getFuerza() {
	return fuerza;
    }

    /**
     * @param fuerza fuerza del NPC
     */
    public void setFuerza(final int fuerza) {
	this.fuerza = fuerza;
    }

    /**
     * @return destreza del NPC
     */
    public int getDestreza() {
	return destreza;
    }

    /**
     * @param destreza destreza del NPC
     */
    public void setDestreza(final int destreza) {
	this.destreza = destreza;
    }

    /**
     * @return inteligencia del NPC
     */
    public int getInteligencia() {
	return inteligencia;
    }

    /**
     * @param inteligencia inteligencia del NPC
     */
    public void setInteligencia(final int inteligencia) {
	this.inteligencia = inteligencia;
    }

    /**
     * @return nivel del NPC
     */
    public int getNivel() {
	return nivel;
    }

    /**
     * @param nivel nivel del NPC
     */
    public void setNivel(final int nivel) {
	this.nivel = nivel;
    }

    /**
     * @return exp que otorga ganarle al NPC
     */
    public int getExperienciaOtorga() {
	return experienciaOtorga;
    }

    /**
     * @param experienciaOtorga exp que otorga ganarle al NPC
     */
    public void setExperienciaOtorga(final int experienciaOtorga) {
	this.experienciaOtorga = experienciaOtorga;
    }

    /**
     * @return mapa del NPC
     */
    public int getIdMapa() {
	return idMapa;
    }

    /**
     * @param idMapa mapa del NPC
     */
    public void setIdMapa(final int idMapa) {
	this.idMapa = idMapa;
    }

    /**
     * @return estado del NPC
     */
    public int getEstado() {
	return estado;
    }

    /**
     * @param estado estado del NPC
     */
    public void setEstado(final int estado) {
	this.estado = estado;
    }

    /**
     * @return casta del NPC
     */
    public String getCasta() {
	return casta;
    }

    /**
     * @param casta casta del NPC
     */
    public void setCasta(final String casta) {
	this.casta = casta;
    }

    /**
     * @return raza del NPC
     */
    public String getRaza() {
	return raza;
    }

    /**
     * @param raza raza del NPC
     */
    public void setRaza(final String raza) {
	this.raza = raza;
    }
}
