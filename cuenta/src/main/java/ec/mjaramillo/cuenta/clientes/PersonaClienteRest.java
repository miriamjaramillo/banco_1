package ec.mjaramillo.cuenta.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ec.mjaramillo.cuenta.model.entity.persona.Cliente;


@FeignClient(name="persona.mjaramilloapis.ec", url="localhost:8081")
public interface PersonaClienteRest {
	
	@GetMapping("/clientes/obtenerClientePorId/{idCliente}")
	public Cliente obtenerClientePorId(@PathVariable(value = "idCliente")  Long idCliente);
	
	@GetMapping("/clientes/obtenerClientePorIdentificacion/{identificacion}")
	public Cliente obtenerClientePorIdentificacion(@PathVariable(value = "identificacion")  String identificacion);
}
