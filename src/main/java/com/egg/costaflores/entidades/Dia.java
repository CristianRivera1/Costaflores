package com.egg.costaflores.entidades;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
public class Dia {
	@Id
	@Temporal(TemporalType.DATE)
	private Date Fecha;
	@Temporal(TemporalType.TIME)
	private Date Entrada;
	@Temporal(TemporalType.TIME)
	private Date Salida;
	private String Nota;	
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Trabajador Trabajador;
	@ManyToOne
	private Tarea tarea;
	@ManyToOne
    private Herramienta herramienta;
	
	public Herramienta getHerramienta() {
		return herramienta;
	}
	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public Date getEntrada() {
		return Entrada;
	}
	public void setEntrada(Date entrada) {
		Entrada = entrada;
	}
	public Date getSalida() {
		return Salida;
	}
	public void setSalida(Date salida) {
		Salida = salida;
	}
	public String getNota() {
		return Nota;
	}
	public void setNota(String nota) {
		Nota = nota;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Trabajador getTrabajador() {
		return Trabajador;
	}
	public void setTrabajador(Trabajador trabajador) {
		Trabajador = trabajador;
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
		
		
		
}
