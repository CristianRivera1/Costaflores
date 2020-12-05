package com.egg.costaflores.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.costaflores.entidades.Trabajador;
import com.egg.costaflores.errores.ErrorService;
import com.egg.costaflores.repository.TrabajadorRepositorio;

@Service
public class TrabajadorService {
	
	
		@Autowired
		private TrabajadorRepositorio trabajadorRepositorio;
		
		@Transactional
		public void registrarTrabajador(String nombre) throws ErrorService {

			Optional<Trabajador> respuesta = trabajadorRepositorio.findById(nombre);
			
			
		if (respuesta.isPresent()) {
			throw new ErrorService("Ya hay un autor con el mismo nombre y apellido");
		}else {

			validar(nombre);
			
			Trabajador trabajador = new Trabajador();
			
			trabajador.setNombre(nombre);
			
			trabajadorRepositorio.save(trabajador);}
		}
		@Transactional
		public void modificarTrabajador(String id,String nombre) throws ErrorService{
			
			validar(nombre);
			
			Optional<Trabajador> respuesta = trabajadorRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Trabajador trabajador = respuesta.get();
			trabajador.setNombre(nombre);
			
			trabajadorRepositorio.save(trabajador);
			
			}else {
				throw new ErrorService("No se encontro el autor solicitado");
			}
			
		
		}
		
		@Transactional
		public void eliminarTrabajador(String id) throws ErrorService {
			
	        Optional<Trabajador> respuesta = trabajadorRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Trabajador trabajador = respuesta.get();
			
			trabajadorRepositorio.delete(trabajador);
		
			
			}else {
				throw new ErrorService("No se encontro el autor solicitado");
			}
		}
		
		public void validar(String nombre) throws ErrorService{
			
	         if(nombre == null || nombre.isEmpty()) {
				
				throw new ErrorService("El nombre del autor no puede ser nulo.");
			}
			
		}
		
	}


