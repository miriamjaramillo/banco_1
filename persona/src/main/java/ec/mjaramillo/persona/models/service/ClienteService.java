package ec.mjaramillo.persona.models.service;

import java.util.List;

import ec.mjaramillo.persona.model.entity.Persona;
import ec.mjaramillo.persona.model.entity.persona.Cliente;

public interface ClienteService {
	public Persona agregarCliente(Cliente cliente);
	public Persona editarCliente(Long idCliente, Cliente cliente);
	public List<Persona> listarClientes();
	public Cliente obtenerClientePorId(Long id);
	public Cliente obtenerClientePorIdentificacion(String identificacion);
	public Persona eliminarPersona(Long idPersona);
}
