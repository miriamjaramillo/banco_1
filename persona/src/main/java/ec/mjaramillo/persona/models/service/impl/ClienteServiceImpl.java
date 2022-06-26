package ec.mjaramillo.persona.models.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.mjaramillo.persona.exception.ResourceNotFoundException;
import ec.mjaramillo.persona.model.entity.Persona;
import ec.mjaramillo.persona.model.entity.persona.Cliente;
import ec.mjaramillo.persona.models.repository.PersonaRepository;
import ec.mjaramillo.persona.models.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private PersonaRepository clienteRepository;

	@Override
	public Persona agregarCliente(Cliente cliente) {
		Persona existePersona = null;
		try {
			existePersona = clienteRepository.findOneByIdentificacion(cliente.getIdentificacion());
			if (existePersona != null) {
				return editarCliente(existePersona.getPersonaId(), cliente);
			}else {
				clienteRepository.save(cliente);
				return cliente;
			}
	
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Persona editarCliente(Long idCliente, Cliente cliente) {
		Persona personaExiste = obtenerClientePorId(idCliente);
		
		if (cliente.getDireccion() != null ) { personaExiste.setDireccion(cliente.getDireccion()); }
		if (cliente.getEdad() != null ) { personaExiste.setEdad(cliente.getEdad()); }
		if (cliente.getGenero() != null ) { personaExiste.setGenero(cliente.getGenero()); }
		if (cliente.getIdentificacion() != null ) { personaExiste.setIdentificacion(cliente.getIdentificacion()); }
		if (cliente.getNombre() != null ) { personaExiste.setNombre(cliente.getNombre()); }
		if (cliente.getTelefono() != null ) { personaExiste.setTelefono(cliente.getTelefono()); }
		
		clienteRepository.save(personaExiste);
		return personaExiste;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarClientes() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente obtenerClientePorId(Long id) {
		return (Cliente) clienteRepository.findById(id).orElseThrow(() ->  
			new ResourceNotFoundException("Persona no encontrada con este id : " + id)
		);
	}

	@Override
	public Persona eliminarPersona(Long idPersona) {
		Persona personaExiste = obtenerClientePorId(idPersona);
		clienteRepository.delete(personaExiste);
		return personaExiste;
	}

	@Override
	public Cliente obtenerClientePorIdentificacion(String identificacion) {
		return (Cliente) Optional.ofNullable(clienteRepository.findOneByIdentificacion(identificacion))
				.orElseThrow(() ->  
				new ResourceNotFoundException("Persona no encontrada con identificacion : " + identificacion)
		);
	}

}
