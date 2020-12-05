package com.egg.costaflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.costaflores.entidades.Herramienta;
import com.egg.costaflores.entidades.Trabajador;


@Repository
public interface HerramientaRepositorio extends JpaRepository<Herramienta,String>{

    
    @Query("SELECT l FROM Herramienta l WHERE l.nombre = :nombre")	
	public List<Herramienta> buscarAutorPorNombre(@Param("nombre") String nombre);
    

	@Query("SELECT l FROM Herramienta l WHERE l.id = :id")
	public List<Herramienta> buscarAutorPorid(@Param("id") String id);
}