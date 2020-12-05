package com.egg.costaflores.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.costaflores.entidades.Dia;



@Repository
public interface DiaRepositorio extends JpaRepository<Dia,Date>{

@Query("SELECT l FROM Dia l WHERE l.Fecha = :Fecha")	
	public Dia buscarprestamosporId(@Param("Fecha") Date fecha);
 
}
