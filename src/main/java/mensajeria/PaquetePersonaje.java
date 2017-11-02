package mensajeria;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dominio.Item;
import estados.Estado;
import sun.text.resources.cldr.es.FormatData_es_EC;

/**
 * Clase PaquetePersonaje
 */
public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel = 1;
	private int experiencia;
	private int puntosSkill = 0;
	private ArrayList<Item> items = new ArrayList<Item>();

	/**
	 * Setea el estado
	 *
	 * @throws IOException lanza excepcion
	 */
	public PaquetePersonaje() throws IOException {
		estado = Estado.getEstadoOffline();
	}

	/**
	 * Retorna el estado
	 *
	 * @return estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Setea el estado
	 *
	 * @param estado parametro estado
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Retorna el mapa
	 *
	 * @return idMapa
	 */
	public int getMapa() {
		return idMapa;
	}

	/**
	 * Setea el mapa
	 *
	 * @param mapa parametro mapa
	 */
	public void setMapa(int mapa) {
		idMapa = mapa;
	}

	/**
	 * Retorna el nivel
	 *
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Setea el nivel
	 *
	 * @param nivel parametro nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Retorna la experiencia
	 *
	 * @return experiencia
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Setea la experiencia
	 *
	 * @param experiencia parametro experiencia
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * Retorna el id
	 *
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setea el id
	 *
	 * @param id parametro id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna la casta
	 *
	 * @return casta
	 */
	public String getCasta() {
		return casta;
	}

	/**
	 * setea la casta
	 *
	 * @param casta parametro casta
	 */
	public void setCasta(String casta) {
		this.casta = casta;
	}

	/**
	 * Retorna el nombre
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setea el nombre
	 *
	 * @param nombre parametro nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna la raza
	 *
	 * @return raza
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Setea la raza
	 *
	 * @param raza parametro raza
	 */
	public void setRaza(String raza) {
		this.raza = raza;
	}

	/**
	 * Retorna la salud tope
	 *
	 * @return saludTope
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Setea la salud tope
	 *
	 * @param saludTope parametro saludoTope
	 */
	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * Retorna la energia tope
	 *
	 * @return energiaTope
	 */
	public int getEnergiaTope() {
		return energiaTope;
	}

	/**
	 * Setea la energia tope
	 *
	 * @param energiaTope parametro energia tope
	 */
	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}

	/**
	 * Retorna la fuerza
	 *
	 * @return fuerza
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Setea la fuerza
	 *
	 * @param fuerza parametro fuerza
	 */
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	/**
	 * Retorna la destreza
	 *
	 * @return destreza
	 */
	public int getDestreza() {
		return destreza;
	}

	/**
	 * Setea la destreza
	 *
	 * @param destreza parametro destreza
	 */
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	/**
	 * Retorna la inteligencia
	 *
	 * @return inteligencia
	 */
	public int getInteligencia() {
		return inteligencia;
	}

	/**
	 * Setea la inteligencia
	 *
	 * @param inteligencia parametro inteligencia
	 */
	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mensajeria.Paquete#clone()
	 */
	@Override
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	/**
	 * AÒade item
	 *
	 * @param i parametro item
	 */
	public final void anadirItem(Item i) {
		items.add(i);
	}

	/**
	 * Elimina item
	 *
	 * @param i parametro item
	 */
	public final void removerItem(Item i) {
		items.remove(i);
	}

	/**
	 * Retorna los items
	 *
	 * @return array list de items
	 */
	public ArrayList<Item> getItems() {
		return new ArrayList<Item>(items);
	}

	/**
	 * Setea los items
	 *
	 * @param items parametro items
	 */
	public final void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * Retorna el id del item
	 *
	 * @param index parametro index
	 * @return items.get(index).getIdItem
	 */
	public final int getItemID(int index) {
		return items.get(index).getIdItem();
	}

	/**
	 * Subrutina de AÒade item
	 *
	 * @param idItem parametro idItem
	 * @param nombreI parametro nombre
	 * @param wearLocation parametro wearLocation
	 * @param bonusSalud parametro bonusSalud
	 * @param bonusEnergia parametro bonusEnergia
	 * @param bonusAtaque parametro bonusAtaque
	 * @param bonusDefensa parametro bonusDefensa
	 * @param bonusMagia parametro bonusMagia
	 * @param foto parametro foto
	 * @param fotoEquipado parametro fotoEquipado
	 */
	public final void anadirItem(int idItem, String nombre, int wearLocation, int bonusSalud, int bonusEnergia,
			int bonusAtaque, int bonusDefensa, int bonusMagia, String foto, String fotoEquipado) {
		try {
			items.add(new Item(idItem, nombre, wearLocation, bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa,
					bonusMagia, foto, fotoEquipado));
			useBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fall√≥ al a√±adir item");

		}
	}

	/**
	 * Remueve bonus
	 */
	public final void removerBonus() {
		// Intente usar un iterator y por alguna raz√≥n no andaba..
		int i = 0;
		while (i < items.size()) {
			sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
			i++;
		}
	}

	/**
	 * @return bonus de fuerza total de los items que tiene el personaje
	 */
	public final int getFuerzaItems() {
		int fuerzaStat = 0;

		for (Item item : items) {
			fuerzaStat += item.getBonusFuerza();
		}
		return fuerzaStat;
	}

	/**
	 * @return bonus de destreza total de los items que tiene el personaje
	 */
	public final int getDestrezaItem() {
		int destrezaItem = 0;

		for (Item item : items) {
			destrezaItem += item.getBonusDestreza();
		}
		return destrezaItem;
	}

	/**
	 * @return bonus de inteligencia total de los items que tiene el personaje
	 */
	public final int getInteligenciaItem() {
		int inteligenciaItem = 0;

		for (Item item : items) {
			inteligenciaItem += item.getBonusInteligencia();
		}
		return inteligenciaItem;
	}

	/**
	 * Retira bonus
	 * 
	 * @param bonusSalud parametro bonusSalud
	 * @param bonusEnergia parametro bonusEnergia
	 * @param bonusAtaque parametro bonusAtaque
	 * @param bonusDefensa parametro bonusDefensa
	 * @param bonusMagia parametro bonusMagia
	 */
	public final void sacarBonus(int bonusSalud, int bonusEnergia, int bonusAtaque, int bonusDefensa, int bonusMagia) {
		saludTope -= bonusSalud;
		energiaTope -= bonusEnergia;
		fuerza -= bonusAtaque;
		destreza -= bonusDefensa;
		inteligencia -= bonusMagia;
	}

	public final void ponerBonus() {
		// Intente usar un iterator y por alguna raz√≥n no andaba..
		int i = 0;
		while (i < items.size()) {
			useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
			i++;
		}
	}

	/**
	 * Ssar Bonus
	 * 
	 * @param bonusSalud parametro bonusSalud
	 * @param bonusEnergia parametro bonusEnergia
	 * @param bonusAtaque parametro bonusAtaque
	 * @param bonusDefensa parametro bonusDefensa
	 * @param bonusMagia parametro bonusMagia
	 */
	public void useBonus(int bonusSalud, int bonusEnergia, int bonusAtaque, int bonusDefensa, int bonusMagia) {
		saludTope += bonusSalud;
		energiaTope += bonusEnergia;
		fuerza += bonusAtaque;
		destreza += bonusDefensa;
		inteligencia += bonusMagia;
	}

	/**
	 * @return cantidad de items
	 */
	public int getCantItems() {
		return items.size();
	}

	/**
	 * AÒade items
	 *
	 * @param idItem parametro idItem
	 */
	public void anadirItem(int idItem) {
		try {
			items.add(new Item(idItem, null, 0, 0, 0, 0, 0, 0, null, null));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fall√≥ al a√±adir item");
		}

	}

	/**
	 * Iterator
	 *
	 * @return items.iterator
	 */
	public Iterator<Item> getIterator() {
		return items.iterator();
	}

	/**
	 * Remueve ultimo item
	 */
	public void removerUltimoItem() {
		items.remove(items.size() - 1);

	}

	/**
	 * Retorna nuevo item
	 *
	 * @return nuevo item
	 */
	public boolean nuevoItem() {
		return items.get(items.size() - 1).getNombre() == null;
	}

	/**
	 * @param cantItems cantidad de items
	 */
	public void ponerBonus(int cantItems) {
		int i = 0;
		while (i < cantItems) {
			useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
			i++;
			// **SI HAS LLEGADO HASTA AC¡ TE HACE FALTA M¡S CAFE**//
		}
	}

	/**
	 * Saca ultimo items
	 */
	public void sacarUltimoItem() {
		int i = items.size() - 1;
		if (i >= 0) {
			sacarBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
		}
	}

	/**
	 * Pone ultimo item
	 */
	public void ponerUltimoItem() {
		int i = items.size() - 1;
		if (i >= 0) {
			useBonus(items.get(i).getBonusSalud(), items.get(i).getBonusEnergia(), items.get(i).getBonusFuerza(),
					items.get(i).getBonusDestreza(), items.get(i).getBonusInteligencia());
		}
	}

	/**
	 * Elimina items
	 */
	public void eliminarItems() {
		items.removeAll(items);
	}

	/**
	 * Actualiza Trueque
	 * 
	 * @param items items
	 */
	public void actualizarTrueque(ArrayList<Item> items) {
		this.items.removeAll(this.items);
		for (Item item : items) {
			this.items.add(item);
		}
	}

	/**
	 * Retorna puntos Skill
	 * 
	 * @return puntos Skill
	 */
	public int getPuntosSkill() {
		return puntosSkill;
	}

	/**
	 * Setea puntos Skill
	 * 
	 * @param puntosSkill puntos Skill
	 */
	public void setPuntosSkill(int puntosSkill) {
		this.puntosSkill = puntosSkill;
	}
}
/*
 * POR FIN 
 * http://www.topito.com/wp-content/uploads/2013/01/code-21.gif
 */
