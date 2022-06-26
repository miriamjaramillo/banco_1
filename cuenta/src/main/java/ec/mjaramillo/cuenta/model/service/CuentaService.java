package ec.mjaramillo.cuenta.model.service;

import java.util.List;

import ec.mjaramillo.cuenta.model.entity.Cuenta;
import ec.mjaramillo.cuenta.model.entity.Persona;

public interface CuentaService {
	public Persona obtenerClientePorId(Long idPersona);
	
	public Cuenta agregarCuenta(Cuenta cuenta, Long idPersona);
	public List<Cuenta> listarCuentas();
	public Cuenta obtenerCuentaPorId(Long idCuenta);
	public Cuenta obtenerCuentaPorNumero(String numeroCuenta);
	public Cuenta editarCuenta(Long idCuenta, Cuenta cuenta);
	public List<Cuenta> obtenerCuentasPorCliente(Persona persona);
	public Cuenta eliminarCuenta(Long idCuenta);
}
