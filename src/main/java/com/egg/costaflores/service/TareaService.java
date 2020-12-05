package com.egg.costaflores.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.costaflores.entidades.Tarea;
import com.egg.costaflores.entidades.Trabajador;
import com.egg.costaflores.errores.ErrorService;
import com.egg.costaflores.repository.TareaRepositorio;
import com.egg.costaflores.repository.TrabajadorRepositorio;

@Service
public class TareaService {
	
	
		@Autowired
		private TareaRepositorio tareaRepositorio;
		
		@Transactional
		public void registrarTarea(String nombre) throws ErrorService {

			Optional<Tarea> respuesta = tareaRepositorio.findById(nombre);
			
			
		if (respuesta.isPresent()) {
			throw new ErrorService("Ya hay una tarea con el mismo nombre");
		}else {

			validar(nombre);
			
			Tarea tarea = new Tarea();
			
			tarea.setNombre(nombre);
			
			tareaRepositorio.save(tarea);}
		}
		@Transactional
		public void modificarTarea(String id,String nombre) throws ErrorService{
			
			validar(nombre);
			
			Optional<Tarea> respuesta = tareaRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Tarea tarea = respuesta.get();
			tarea.setNombre(nombre);
			
			tareaRepositorio.save(tarea);
			
			}else {
				throw new ErrorService("No se encontro el autor solicitado");
			}
			
		
		}
		
		@Transactional
		public void eliminarTarea(String id) throws ErrorService {
			
	        Optional<Tarea> respuesta = tareaRepositorio.findById(id);
			
			if(respuesta.isPresent()) {
			
			Tarea tarea = respuesta.get();
			
			tareaRepositorio.delete(tarea);
		
			
			}else {
				throw new ErrorService("No se encontro la tarea solicitada");
			}
		}
		
		public void validar(String nombre) throws ErrorService{
			
	         if(nombre == null || nombre.isEmpty()) {
				
				throw new ErrorService("La tarea no puede ser nula.");
			}
			
		}
		
	}


