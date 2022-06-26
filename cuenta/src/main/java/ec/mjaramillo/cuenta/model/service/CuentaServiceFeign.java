package ec.mjaramillo.cuenta.model.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.mjaramillo.cuenta.clientes.PersonaClienteRest;
import ec.mjaramillo.cuenta.exception.ResourceNotFoundException;
import ec.mjaramillo.cuenta.model.entity.Cuenta;
import ec.mjaramillo.cuenta.model.entity.Persona;
import ec.mjaramillo.cuenta.model.entity.persona.Cliente;
import ec.mjaramillo.cuenta.model.repository.CuentaRepository;

@Primary
@Service("serviceCuentaFeign")
public class CuentaServiceFeign implements CuentaService {
	
	private final PersonaClienteRest personaFeign;
	private final CuentaRepository cuentaRepository;
	
	@Autowired
    public CuentaServiceFeign(CuentaRepository cuentaRepository, PersonaClienteRest personaFeign) {
        this.cuentaRepository = cuentaRepository;
        this.personaFeign = personaFeign;
    }
	
	@Override
	public Persona obtenerClientePorId(Long idCliente) {
		return personaFeign.obtenerClientePorId(idCliente);
	}

	@Override
	public Cuenta agregarCuenta(Cuenta cuenta, Long idCliente) {
		Cliente existeCliente = personaFeign.obtenerClientePorId(idCliente);
		
		Cuenta existeCuenta = Optional.ofNullable(cuentaRepository.findOneByNumeroCuenta(cuenta.getNumeroCuenta())).orElse(null);
		
		if (null != existeCliente) {	
			if(existeCuenta != null) {
				throw new ResourceNotFoundException("Cuenta numero " + cuenta.getNumeroCuenta() + " ya asignada");
			}else {
				cuenta.setCliente(existeCliente);
				return cuentaRepository.save(cuenta);
			}
		}
		
		return null;
	}

	@Override
	public List<Cuenta> listarCuentas() {
		return StreamSupport
                .stream(cuentaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
	}

	@Override
	public Cuenta eliminarCuenta(Long idCuenta) {
		Cuenta cuenta = obtenerCuentaPorId(idCuenta);
		cuentaRepository.delete(cuenta);
		return cuenta;
	}

	@Override
	public Cuenta editarCuenta(Long id, Cuenta cuenta) {
		
		Cuenta existeCuenta = obtenerCuentaPorId(id);		
		Cuenta validaNumCuenta = Optional.ofNullable(cuentaRepository.findOneByNumeroCuenta(cuenta.getNumeroCuenta())).orElse(null);
		
		if(validaNumCuenta != null && (existeCuenta.getCliente().getPersonaId() != validaNumCuenta.getCliente().getPersonaId())) {	
			throw new ResourceNotFoundException("El numero de cuenta ya se encuentra asignado a otra persona.");
		}else {
			existeCuenta.setEstado(cuenta.getEstado());
			existeCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
			existeCuenta.setCliente(cuenta.getCliente());
			existeCuenta.setSaldoInicial(cuenta.getSaldoInicial());
			existeCuenta.setTipoCuenta(cuenta.getTipoCuenta());
			cuentaRepository.save(existeCuenta);
		}
		
		return existeCuenta;
	}

	@Override
	public List<Cuenta> obtenerCuentasPorCliente(Persona persona) {
		return null;
	}

	@Override
	public Cuenta obtenerCuentaPorId(Long id) {
		return cuentaRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Cuenta no encontrada con el id : " + id)
		);
	}

	@Override
	public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
		return (Cuenta) Optional.ofNullable(cuentaRepository.findOneByNumeroCuenta(numeroCuenta))
				.orElseThrow(() ->  
				new ResourceNotFoundException("Cuenta no encontrada con el numero : " + numeroCuenta)
		);
	}
}
