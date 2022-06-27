package ec.mjaramillo.movimiento.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ec.mjaramillo.movimiento.model.entity.Cuenta;

@FeignClient(name="cuenta.mjaramilloapis.ec")
public interface CuentaClienteRest {

	@GetMapping("/cuentas/obtenerCuentaPorId/{idCuenta}")
	public Cuenta obtenerCuentaPorId(@PathVariable(value = "idCuenta") Long idCuenta);
	
	@GetMapping("/cuentas/obtenerCuentaPorNumeroCuenta/{numeroCuenta}")
	public Cuenta obtenerCuentaPorNumero(@PathVariable(value = "numeroCuenta") String numeroCuenta);
}
