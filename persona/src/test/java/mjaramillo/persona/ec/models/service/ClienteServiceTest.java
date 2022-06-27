package mjaramillo.persona.ec.models.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ec.mjaramillo.persona.model.entity.Persona;
import ec.mjaramillo.persona.model.entity.persona.Cliente;
import ec.mjaramillo.persona.models.repository.PersonaRepository;
import ec.mjaramillo.persona.models.service.impl.ClienteServiceImpl;

public class ClienteServiceTest {
	
	@Mock
	private PersonaRepository clienteRepository;
	
	@InjectMocks
	private ClienteServiceImpl clienteService;
	
	private Persona persona;
	
	@BeforeEach void setup() {
		MockitoAnnotations.initMocks(this);
		persona = new Cliente("1103472834", "Miriam Jaramillo", "Femenino", 
				35, "Saraguro", "0438293845", "Activa", "1234");
	}
	
	@Test
	void listarClientes() {
		List<Persona> list = new ArrayList<>();
		list.add(persona);
		when(clienteRepository.findAll()).thenReturn(list);
		assertNotNull(clienteService.listarClientes());
	}
	
}
