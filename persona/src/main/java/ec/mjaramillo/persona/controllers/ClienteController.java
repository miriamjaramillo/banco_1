package ec.mjaramillo.persona.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.mjaramillo.persona.model.entity.Persona;
import ec.mjaramillo.persona.model.entity.persona.Cliente;
import ec.mjaramillo.persona.models.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@ApiOperation(value = "Permite crear o editar una persona", notes = "Verifica la existencia de la cedula de identidad", tags = { "Persona" })
	@PostMapping("/crear")
	public ResponseEntity<?> agregarCliente(@RequestBody Cliente clienteRequest) {
		Persona persona = clienteService.agregarCliente(clienteRequest);
		return new ResponseEntity<>(persona,HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Lista las personas", notes = "Listado de todas las personas existentes en la base de datos", tags = { "Persona" })
	@GetMapping("/listarClientes")
	public List<Persona> obtenerClientes() {
		return clienteService.listarClientes();
	}
	
	@ApiOperation(value = "Devuelve un cliente dado el id", notes = "Devuelve un cliente dado el id", tags = { "Cliente" })
	@GetMapping("/obtenerClientePorId/{idCliente}")
	public Cliente obtenerClientePorId(@PathVariable(value = "idCliente")  Long idCliente) {
		Cliente cliente =  clienteService.obtenerClientePorId(idCliente);		
		return cliente;
	}
	
	@ApiOperation(value = "Actualiza una persona por id", notes = "Actualiza una persona por id", tags = { "Persona" })
	@PutMapping("/actualizar/{idCliente}")
	public ResponseEntity<?> actualizarCliente(@PathVariable(value = "idCliente") Long idCliente, @RequestBody Cliente cliente){
		Persona clienteEditar = clienteService.editarCliente(idCliente, cliente);
		return ResponseEntity.ok(clienteEditar);
	}
	
	@ApiOperation(value = "Elimina una persona dado el id", notes = "Elimina una persona dado el id", tags = { "Persona" })
	@DeleteMapping("/eliminar/{idPersona}")
	public Map<String, Boolean> eliminarCliente(@PathVariable(value = "idPersona") Long idPersona) {
		clienteService.eliminarPersona(idPersona);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Estado eliminacion: ", Boolean.TRUE);
		return response;
	}
	
	@ApiOperation(value = "Devuelve un cliente dado por su identificacion", notes = "Devuelve un cliente por su identificacion", tags = { "Cliente" })
	@GetMapping("/obtenerClientePorIdentificacion/{identificacion}")
	public Cliente obtenerClientePorIdentificacion(@PathVariable(value = "identificacion")  String identificacion) {
		Cliente cliente =  clienteService.obtenerClientePorIdentificacion(identificacion);		
		return cliente;
	}
}
