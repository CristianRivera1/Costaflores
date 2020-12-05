package com.egg.costaflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.costaflores.entidades.Tarea;
import com.egg.costaflores.entidades.Trabajador;


@Repository
public interface TareaRepositorio extends JpaRepository<Tarea,String>{

    
    @Query("SELECT l FROM Tarea l WHERE l.nombre = :nombre")	
	public List<Tarea> buscarAutorPorNombre(@Param("nombre") String nombre);
    

	@Query("SELECT l FROM Tarea l WHERE l.id = :id")
	public List<Tarea> buscarAutorPorid(@Param("id") String id);
}