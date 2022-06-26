package ec.mjaramillo.movimiento.model.service;

import java.util.Date;
import java.util.List;

import ec.mjaramillo.movimiento.dto.ListaMovimientos;
import ec.mjaramillo.movimiento.model.entity.Cuenta;
import ec.mjaramillo.movimiento.model.entity.Movimiento;

public interface MovimientoService {
	
	public Cuenta obtenerCuentaPorId(Long idCuenta);
	public List<Movimiento> obtenerMovimientosPorCuentaId(Long idCuenta);
	public Movimiento obtenerUltimoMovimientoPorCuentaId(Long idCuenta);
	public Movimiento movimientoPorCuenta(String numCuenta, Movimiento movimiento);
	public List<ListaMovimientos> listarMovimientosPorFecha(Long personaId, Date fechaInicio, Date fechaFin);
}
