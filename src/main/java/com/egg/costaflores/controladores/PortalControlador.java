package com.egg.costaflores.controladores;


	import java.util.Date;
	import java.util.List;
	import java.util.Optional;

	import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.access.prepost.PreAuthorize;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.ModelMap;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;

import com.egg.costaflores.service.ClienteService;
import com.egg.costaflores.service.DiaService;
import com.egg.costaflores.service.HerramientaService;
import com.egg.costaflores.service.TareaService;
import com.egg.costaflores.service.TrabajadorService;



import com.egg.costaflores.entidades.Dia;
import com.egg.costaflores.entidades.Herramienta;
import com.egg.costaflores.entidades.Tarea;
import com.egg.costaflores.entidades.Trabajador;
import com.egg.costaflores.errores.ErrorService;
import com.egg.costaflores.repository.DiaRepositorio;
import com.egg.costaflores.repository.HerramientaRepositorio;
import com.egg.costaflores.repository.TareaRepositorio;
import com.egg.costaflores.repository.TrabajadorRepositorio;






	@Controller
	@RequestMapping("/")

	public class PortalControlador {
	@Autowired
    private DiaRepositorio diaRepositorio;
	@Autowired
    private TrabajadorRepositorio trabajadorRepositorio;
	@Autowired
    private TareaRepositorio tareaRepositorio;
	@Autowired
    private HerramientaRepositorio herramientaRepositorio;
//	@Autowired
//    private DiaService diaService;
	@Autowired
    private ClienteService clienteService;
	@Autowired
    private DiaService diaService;
	@Autowired
    private TareaService tareaService;
	@Autowired
    private TrabajadorService trabajadorService;
	@Autowired
    private HerramientaService herramientaService;
	
	

	@GetMapping("/")
	public String principio() {
		return "registro.html";
	}

	@GetMapping("/registro")
	public String registro() {
		return "registro.html";
	}
	@GetMapping("/Listar-dias")
	public String listarDias(ModelMap modelo) {
		List<Dia> dia= diaRepositorio.findAll();
//		List<Trabajador> trabajadorr = trabajadorRepositorio.findAll();
//	     List<Tarea> tareaa = tareaRepositorio.findAll();
//	     List<Herramienta> herramientaa = herramientaRepositorio.findAll();
//	    modelo.put("trabajador", trabajadorr);
//	    modelo.put("tarea", tareaa);
//	    modelo.put("herramienta", herramientaa);
		modelo.put("dia",dia);
		return "Listar-dias.html";
	}
	@GetMapping("/crear-dia")
	public String crearLibro(ModelMap modelo) {		
		List<Trabajador> trabajadorr = trabajadorRepositorio.findAll();
	     List<Tarea> tareaa = tareaRepositorio.findAll();
	     List<Herramienta> herramientaa = herramientaRepositorio.findAll();
	    modelo.put("trabajador", trabajadorr);
	    modelo.put("tarea", tareaa);
	    modelo.put("herramienta", herramientaa);
		
		
		return "crear-dia.html";
	}
	@GetMapping("/index")
	public String inicio() {
		
		
		return "index.html";
		
	}
	@GetMapping("/crear-tarea")
	public String crearTarea() {
		return "crear-tarea.html";
	}
	@GetMapping("/crear-herramienta")
	public String crearHerramienta() {
		return "crear-herramienta.html";
	}
	@GetMapping("/crear-trabajador")
	public String crearTrabajador() {
		return "crear-trabajador.html";
	}
	@GetMapping("/Listar-Tareas")
	public String listarAutores(ModelMap modelo) {	
		
		List<Tarea> tarea= tareaRepositorio.findAll();	
		modelo.put("tarea",tarea);
		
		return "Listar-Tareas.html";
	}
	@GetMapping("/Listar-herramienta")
	public String listarHerramienta(ModelMap modelo) {	
		
		List<Herramienta> herramienta= herramientaRepositorio.findAll();	
		modelo.put("herramienta",herramienta);
		
		return "Listar-herramienta.html";
	}
	@GetMapping("/login")
	public String login(@RequestParam(required= false) String error,ModelMap model) {	
		if(error != null ){
			model.put("error", "Dni o clave son incorrectos");
		}
		return "login.html";
	}
	@PostMapping("/registro")
	public String crearCliente(ModelMap modelo,@RequestParam String documento,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String domicilio,@RequestParam String telefono, @RequestParam String clave) {
		
		try {
			clienteService.crearCliente(Long.parseLong(documento), nombre, apellido, domicilio, telefono, clave);
		} catch (ErrorService e) {
			
			modelo.put("error", e.getMessage());
			modelo.put("documento", documento);
			modelo.put("nombre", nombre);
			modelo.put("apellido", apellido);
			modelo.put("domicilio", domicilio);
			modelo.put("telefono", telefono);
			modelo.put("clave", clave);
			

			return "registro.html";
		}
	
		
		return "index.html";
		
	}
	
	@PostMapping("/crear-dia")
	public String agregarDia(ModelMap modelo, @RequestParam String trabajador, @RequestParam String fecha, @RequestParam String entrada, @RequestParam String salida,@RequestParam String tarea, @RequestParam String herramienta, @RequestParam String nota) {

	    try {
	    	Date w4=  diaService.convertirStringADate(fecha);
	    	Date w2=  diaService.convertirStringATime(entrada);
	    	Date w3=  diaService.convertirStringATime(salida);
	        diaService.agregarDia(w4 ,w2, w3, nota, tarea, trabajador, herramienta);
	    } catch (ErrorService e) {

	     List<Trabajador> trabajadorr = trabajadorRepositorio.findAll();
	     List<Tarea> tareaa = tareaRepositorio.findAll();
	     List<Herramienta> herramientaa = herramientaRepositorio.findAll();
	        modelo.put("trabajador", trabajadorr);
	        modelo.put("tarea", tareaa);
	        modelo.put("herramienta", herramientaa);
	        modelo.put("error", e.getMessage());
	        modelo.put("fecha", fecha);
	        modelo.put("nota", nota);
	        modelo.put("entrada", entrada);
	        modelo.put("salida", salida);
	        
	        return "crear-dia.html";
	    }
		
		
		return "index.html";
		
	}
	@PostMapping("/crear-tarea")
	public String registrarTarea(ModelMap modelo,@RequestParam String nombre) {
		
		try {
			tareaService.registrarTarea(nombre);
		} catch (ErrorService e) {
			modelo.put("error", e.getMessage());
			modelo.put("nombre", nombre);
		
			return "crear-tarea.html";
		}
		return "index.html";
	}
	@PostMapping("/crear-herramienta")
	public String registrarHerramienta(ModelMap modelo,@RequestParam String nombre) {
		
		try {
			herramientaService.registrarHerramienta(nombre);
		} catch (ErrorService e) {
			modelo.put("error", e.getMessage());
			modelo.put("nombre", nombre);
		
			return "crear-herramienta.html";
		}
		return "index.html";
	}
	@PostMapping("/crear-trabajador")
	public String registrartrabajador(ModelMap modelo,@RequestParam String nombre) {
		
		try {
			trabajadorService.registrarTrabajador(nombre);
		} catch (ErrorService e) {
			modelo.put("error", e.getMessage());
			modelo.put("nombre", nombre);
		
			return "crear-trabajador.html";
		}
		return "index.html";
	}

}
