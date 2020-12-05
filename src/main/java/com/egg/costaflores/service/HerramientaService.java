package com.egg.costaflores.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.costaflores.entidades.Herramienta;
import com.egg.costaflores.entidades.Trabajador;
import com.egg.costaflores.errores.ErrorService;
import com.egg.costaflores.repository.HerramientaRepositorio;
import com.egg.costaflores.repository.TrabajadorRepositorio;

@Service
public class HerramientaService {
	
	
		@Autowired
		private HerramientaRepositorio herramientaRepositorio;
		
		@Transactional
		public void registrarHerramienta(String nombre) throws ErrorService {

			Optional<Herramienta> respuesta = herramientaRepositorio.findById(nombre);
			
			
		if (respuesta.isPresent()) {
			throw new ErrorService("Ya hay una herramienta igual");
		}else {

			validar(nombre);
			
			Herramienta herramienta = new Herramienta();
			
			herramienta.setNombre(nombre);
			
			herramientaRepositorio.save(herramienta);}
		}
		@Transactional
		public void modificarHerramienta(String id,String nombre) throws ErrorService{
			
			validar(nombre);
			
			Optional<Herramienta> respuesta = herramientaRepositorio.findById(nombre);
			
			if(respuesta.isPresent()) {
			
			Herramienta herramienta = respuesta.get();
			herramienta.setNombre(nombre);
			
			herramientaRepositorio.save(herramienta);
			
			}else {
				throw new ErrorService("No se encontro el autor solicitado");
			}
			
		
		}
		
		@Transactional
		public void eliminarHerramienta(String id) throws ErrorService {
			
	        Optional<Herramienta> respuesta = herramientaRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Herramienta herramienta = respuesta.get();
			
			herramientaRepositorio.delete(herramienta);
		
			
			}else {
				throw new ErrorService("No se encontro la herramienta solicitado");
			}
		}
		
		public void validar(String nombre) throws ErrorService{
			
	         if(nombre == null || nombre.isEmpty()) {
				
				throw new ErrorService("El nombre del autor no puede ser nulo.");
			}
			
		}
		
	}


