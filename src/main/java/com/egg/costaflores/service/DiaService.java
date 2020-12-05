package com.egg.costaflores.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.costaflores.entidades.Cliente;
import com.egg.costaflores.entidades.Dia;
import com.egg.costaflores.entidades.Herramienta;
import com.egg.costaflores.entidades.Tarea;
import com.egg.costaflores.entidades.Trabajador;
import com.egg.costaflores.errores.ErrorService;
import com.egg.costaflores.repository.ClienteRepositorio;
import com.egg.costaflores.repository.DiaRepositorio;
import com.egg.costaflores.repository.HerramientaRepositorio;
import com.egg.costaflores.repository.TareaRepositorio;
import com.egg.costaflores.repository.TrabajadorRepositorio;




@Service
public class DiaService {
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private DiaRepositorio diaRepositorio;
	@Autowired
	private TareaRepositorio tareaRepositorio;
	@Autowired
	private TrabajadorRepositorio trabajadorRepositorio;
	@Autowired
	private HerramientaRepositorio herramientaRepositorio;
	
	@Transactional
    public void agregarDia(Date fecha,Date entrada,Date salida,String nota, String idTarea,String idTrabajador,String idHerramienta ) throws ErrorService {
        

       Tarea tarea = tareaRepositorio.getOne(idTarea);       
       Trabajador trabajador = trabajadorRepositorio.getOne(idTrabajador);
       Herramienta herramienta = herramientaRepositorio.getOne(idHerramienta);

       validar(entrada, salida, tarea, trabajador, herramienta);
       if(nota==null) {
    	   nota="-";
    	   
       }
        Dia dia = new Dia();
//        Date fecha = new Date();
		 
        dia.setFecha(fecha);
        dia.setEntrada(entrada);
        dia.setSalida(salida);
        dia.setTarea(tarea);
        dia.setNota(nota);
        dia.setTrabajador(trabajador);
        dia.setHerramienta(herramienta);

        diaRepositorio.save(dia);

    }

    @Transactional
    public void modificarDia(Date fecha,Date entrada,Date salida,String nota, String idTarea,String idTrabajador,String idherramienta ) throws ErrorService {
    	 Tarea tarea = tareaRepositorio.getOne(idTarea);       
         Trabajador trabajador = trabajadorRepositorio.getOne(idTrabajador);
         Herramienta herramienta = herramientaRepositorio.getOne(idherramienta);
        
        validar( entrada, salida, tarea, trabajador,herramienta);
        
        Optional<Dia> respuesta = diaRepositorio.findById(fecha);

        if (respuesta.isPresent()) {

            Dia dia = respuesta.get();

           
            dia.setFecha(fecha);
            dia.setEntrada(entrada);
            dia.setSalida(salida);
            dia.setTarea(tarea);
            dia.setNota(nota);
            dia.setTrabajador(trabajador);
            dia.setHerramienta(herramienta);

            diaRepositorio.save(dia);
        } else {
            throw new ErrorService("No existe un dia con el identificador solicitado");
        }

    }

    @Transactional
	public Dia buscarLibroPorFecha(Date fecha) throws ErrorService {
	     
    
		Optional<Dia> respuesta = diaRepositorio.findById(fecha);
		
		if(respuesta.isPresent()) {
			
			Dia dia  = respuesta.get();
			
			return dia;
		}else {
			throw new ErrorService("No existe dia con esa fecha");
		}
		
	}
    @Transactional
    public void eliminarLibro( Date fecha) throws ErrorService {

        Optional<Dia> respuesta = diaRepositorio.findById(fecha);

        if (respuesta.isPresent()) {

            Dia dia = respuesta.get();

            if (dia!=null) {

                diaRepositorio.delete(dia);

            }

        } else {
            throw new ErrorService("No existe un dia con el identificador solicitado");
        }

    }
    public Date convertirStringADate(String fecha) {

        try {
            DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
            Date convertido = fechaHora.parse(fecha);
            return convertido;
        } catch (java.text.ParseException ex) {
            Logger.getLogger(DiaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Date convertirStringATime(String str) {

        try {
        	DateFormat formatter = new SimpleDateFormat("hh:mm");
            Date convertido = formatter.parse(str);
            return convertido;
        } catch (java.text.ParseException ex) {
            Logger.getLogger(DiaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void validar(Date entrada,Date salida, Tarea idTarea,Trabajador idTrabajador,Herramienta herramientaa) throws ErrorService {

      

        if (entrada == null ) {
            throw new ErrorService("la fecha de entrada  no debe estar nulo.");
        }

        

        if (idTarea == null) {
            throw new ErrorService("no existe tarea ");
        }

        if (idTrabajador==null ) {
            throw new ErrorService("no existe trabajador");
        }
        
        if (herramientaa==null ) {
            throw new ErrorService("no existe cliente");
        }
       
    }
}
