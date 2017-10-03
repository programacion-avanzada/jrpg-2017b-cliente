package mensajeria;

import java.io.Serializable;

public class PaqueteNpc extends Paquete implements Serializable, Cloneable 
{
	// Por ahora tiene todo lo mismo que un personaje para poder tratarlos de forma similar.
	
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int id;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel = 1; // Se usaría solo como un indicio del poder del npc
	private int experienciaOtorga;
	
	public PaqueteNpc(int id, int saludTope, int energiaTope, int fuerza, int destreza, int inteligencia, int nivel,
			int experienciaOtorga, String nombre, String raza, String casta)
	{
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
	}
	
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getSaludTope()
	{
		return saludTope;
	}
	
	public void setSaludTope(int saludTope)
	{
		this.saludTope = saludTope;
	}
	
	public int getEnergiaTope()
	{
		return energiaTope;
	}
	
	public void setEnergiaTope(int energiaTope)
	{
		this.energiaTope = energiaTope;
	}
	
	public int getFuerza()
	{
		return fuerza;
	}
	
	public void setFuerza(int fuerza)
	{
		this.fuerza = fuerza;
	}
	
	public int getDestreza()
	{
		return destreza;
	}
	
	public void setDestreza(int destreza)
	{
		this.destreza = destreza;
	}
	
	public int getInteligencia()
	{
		return inteligencia;
	}
	
	public void setInteligencia(int inteligencia)
	{
		this.inteligencia = inteligencia;
	}
	
	public int getNivel()
	{
		return nivel;
	}
	
	public void setNivel(int nivel)
	{
		this.nivel = nivel;
	}
	
	public int getExperienciaOtorga()
	{
		return experienciaOtorga;
	}
	
	public void setExperienciaOtorga(int experienciaOtorga)
	{
		this.experienciaOtorga = experienciaOtorga;
	}
	
	public int getIdMapa()
	{
		return idMapa;
	}

	public void setIdMapa(int idMapa)
	{
		this.idMapa = idMapa;
	}

	public int getEstado()
	{
		return estado;
	}

	public void setEstado(int estado)
	{
		this.estado = estado;
	}

	public String getCasta()
	{
		return casta;
	}

	public void setCasta(String casta)
	{
		this.casta = casta;
	}

	public String getRaza()
	{
		return raza;
	}

	public void setRaza(String raza)
	{
		this.raza = raza;
	}
}
