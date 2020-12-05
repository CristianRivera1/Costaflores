package com.egg.costaflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.costaflores.entidades.Trabajador;


@Repository
public interface TrabajadorRepositorio extends JpaRepository<Trabajador,String>{

    
    @Query("SELECT l FROM Trabajador l WHERE l.nombre = :nombre")	
	public List<Trabajador> buscarAutorPorNombre(@Param("nombre") String nombre);
    

	@Query("SELECT l FROM Trabajador l WHERE l.id = :id")
	public List<Trabajador> buscarAutorPorid(@Param("id") String id);
}