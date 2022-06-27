package ec.mjaramillo.movimiento.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ec.mjaramillo.movimiento.clientes.CuentaClienteRest;
import ec.mjaramillo.movimiento.dto.ListaMovimientos;
import ec.mjaramillo.movimiento.exception.ResourceNotFoundException;
import ec.mjaramillo.movimiento.model.entity.Cuenta;
import ec.mjaramillo.movimiento.model.entity.Movimiento;
import ec.mjaramillo.movimiento.model.repository.MovimientoRepository;

@PropertySource("classpath:parametrizacion.properties")
@Primary
@Service("serviceMovimientoFeign")
public class MovimientoServiceFeign implements MovimientoService {
	
	@Autowired
	private Environment env;
	@Autowired
	private CuentaClienteRest cuentaFeign;
	@Autowired
	private MovimientoRepository movimientoRepository;

	@Override
	public Cuenta obtenerCuentaPorId(Long idCuenta) {
		return cuentaFeign.obtenerCuentaPorId(idCuenta);
	}

	@Override
	public List<Movimiento> obtenerMovimientosPorCuentaId(Long idCuenta) {
		Cuenta cuentaExiste = cuentaFeign.obtenerCuentaPorId(idCuenta);
		
		if (cuentaExiste != null) {
			List<Movimiento> movimientos = movimientoRepository.findMovimientosByMovimientosCuentasCuentaId(idCuenta);	
			return movimientos;
		}
		
		return null;	
	}
	
	@Override
	public Movimiento obtenerUltimoMovimientoPorCuentaId(Long idCuenta) {
		
		Movimiento ultimoMovimiento = null;
		Cuenta cuentaExiste = cuentaFeign.obtenerCuentaPorId(idCuenta);
		
		if (cuentaExiste != null) {
			ultimoMovimiento = movimientoRepository.getLastMovimientoCuenta(idCuenta);
			
			if (ultimoMovimiento == null) {
				//throw new ResourceNotFoundException("No existe movimientos para la cuenta numero:: " + idCuenta);
			}
			
			return ultimoMovimiento;
			
		}else {
			throw new ResourceNotFoundException("No existe la cuenta con id = " + idCuenta);
		}
		
	}

	@Override
	public Movimiento movimientoPorCuenta(String numCuenta, Movimiento movimiento) {    
		Cuenta cuentaCliente = cuentaFeign.obtenerCuentaPorNumero(numCuenta);
		
		Long idCuenta = cuentaCliente.getCuentaId();
		Cuenta cuentaExiste = cuentaFeign.obtenerCuentaPorId(idCuenta);
		Movimiento ultimoMovimiento = obtenerUltimoMovimientoPorCuentaId(idCuenta);
		int numMovimientos = movimientoRepository.obtenerTotalMovimientos(idCuenta);
		
		Set<Cuenta> setCuenta = new HashSet<Cuenta>();
		setCuenta.add(cuentaExiste);
		
		Movimiento nuevoMovimiento = new Movimiento(movimiento.getTipoMovimiento(), movimiento.getValor());

		if (ultimoMovimiento != null) {
			
			nuevoMovimiento.setSaldo(ultimoMovimiento.getSaldo());
			Double saldoMovimientoDiario = movimientoRepository.obtenerSaldoMovimientoDiario(cuentaExiste.getCuentaId(), "RET");
			
			saldoMovimientoDiario = (saldoMovimientoDiario == null) ? Double.valueOf(0) : saldoMovimientoDiario;
			
			if (movimiento.getTipoMovimiento().equals("RET")) {
				if (nuevoMovimiento.getValor() > ultimoMovimiento.getSaldo()) {
					throw new ResourceNotFoundException("No es posible realizar un RETIRO del numero de cuenta:: " + idCuenta + ", no tiene saldo disponible.");
				}else {
					if ((saldoMovimientoDiario+movimiento.getValor()) >= Double.valueOf(env.getProperty("limite_diario"))) {
						throw new ResourceNotFoundException("Cupo diario excedido.");
					}
				}
			}
			
			nuevoMovimiento.realizaMovimiento();
			nuevoMovimiento.getMovimientosCuentas().add(cuentaExiste);
			return movimientoRepository.save(nuevoMovimiento);
		}else {
			if (movimiento.getTipoMovimiento() != null && movimiento.getValor() != null && movimiento.getValor() > 0) {
				
				if (movimiento.getTipoMovimiento().equals("DEP")) {
					
					nuevoMovimiento.setSaldo(0.0);
					nuevoMovimiento.realizaMovimiento();
					
					//Actualiza el saldo inicial
					if (numMovimientos == 0) {
						cuentaExiste.setSaldoInicial(nuevoMovimiento.getSaldo());
					}
					
					nuevoMovimiento.getMovimientosCuentas().add(cuentaExiste);
					return movimientoRepository.save(nuevoMovimiento);
					
				} else {
					throw new ResourceNotFoundException("No es posible realizar un RETIRO del numero de cuenta:: " + idCuenta + ", no tiene saldo disponible.");
				}
			}else {
				throw new ResourceNotFoundException("Los valores ingresados son incorrectos, verifique los datos.");
			}
			
		}
	
	}

	@Override
	public List<ListaMovimientos> listarMovimientosPorFecha(Long personaId, Date fechaInicio, Date fechaFin) {
		List<Object[]> rows = Optional.ofNullable(movimientoRepository.rows(personaId, fechaInicio, fechaFin))
				.orElseThrow(() ->  
				new ResourceNotFoundException("No existe informacion con los datos ingresados.")
		);
		
		List<ListaMovimientos> result = new ArrayList<>(rows.size());
		
		for (Object[] row : rows) {
		    result.add(new ListaMovimientos((Date) row[0],
		                            (String) row[1],
		                            (String) row[2], 
		                            (Double) row[3], 
		                            (Boolean) row[4], 
		                            (String) row[5], 
		                            (Double) row[6], 
		                            (String) row[7], 
		                            (Double) row[8]));
		}
		
		return result;
	}

}
