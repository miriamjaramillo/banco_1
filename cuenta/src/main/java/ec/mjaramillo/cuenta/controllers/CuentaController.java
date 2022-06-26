package ec.mjaramillo.cuenta.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ec.mjaramillo.cuenta.model.entity.Cuenta;
import ec.mjaramillo.cuenta.model.service.CuentaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
	
	@Autowired
	@Qualifier("serviceCuentaFeign")
	private CuentaService cuentaService;
	
	@ApiOperation(value = "Lista todas las cuentas", notes = "Lista todas las cuentas", tags = { "Reportes" })
	@GetMapping("/listarCuentas")
	public List<Cuenta> obtenerCuentas() {
		return cuentaService.listarCuentas();
	}
	
	@ApiOperation(value = "Permite crear una cuenta", notes = "Verifica la existencia del numero de cuenta", tags = { "Cuenta" })
	@RequestMapping(value = "/crear/{idPersona}", method = RequestMethod.POST)
    public ResponseEntity<?> agregarCuentaPersona(@PathVariable(value = "idPersona") Long idPersona, @RequestBody Cuenta cuentaRequest){
        Cuenta cuentaCliente = cuentaService.agregarCuenta(cuentaRequest, idPersona);
        return new ResponseEntity<>(cuentaCliente,HttpStatus.CREATED);
    }
	
	@ApiOperation(value = "Permite editar una cuenta", notes = "Verifica que el numero de cuenta no este atada a otra persona", tags = { "Cuenta" })
	@PutMapping("/actualizar/{idCuenta}")
	public ResponseEntity<?> actualizarCuenta(@PathVariable(value = "idCuenta") Long idCuenta, @RequestBody Cuenta cuentaRequest){
		Cuenta cuentaEditar = cuentaService.editarCuenta(idCuenta, cuentaRequest);
		return ResponseEntity.ok(cuentaEditar);
	}
	
	@ApiOperation(value = "Permite eliminar una cuenta dado su id", notes = "Permite eliminar una cuenta segun el id de la cuenta", tags = { "Cuenta" })
	@DeleteMapping("/eliminar/{idCuenta}")
	public Map<String, Boolean> eliminarCuenta(@PathVariable(value = "idCuenta") Long idCuenta) {
		Map<String, Boolean> response = new HashMap<>();
		cuentaService.eliminarCuenta(idCuenta);
		response.put("Cuenta eliminada", Boolean.TRUE);
		return response;
	}
	
	@ApiOperation(value = "Obtener la cuenta por id", notes = "Devuelve los datos de una cuenta por su id", tags = { "Cuenta" })
	@GetMapping("/obtenerCuentaPorId/{idCuenta}")
	public ResponseEntity<?> obtenerCuentaPorId(@PathVariable(value = "idCuenta") Long idCuenta) {
		Cuenta cuenta = cuentaService.obtenerCuentaPorId(idCuenta);
		return ResponseEntity.ok().body(cuenta);
	}
	
	@ApiOperation(value = "Obtener la cuenta por id", notes = "Devuelve los datos de una cuenta por su id", tags = { "Cuenta" })
	@GetMapping("/obtenerCuentaPorNumeroCuenta/{numeroCuenta}")
	public ResponseEntity<?> obtenerCuentaPorNumero(@PathVariable(value = "numeroCuenta") String numeroCuenta) {
		Cuenta cuenta = cuentaService.obtenerCuentaPorNumero(numeroCuenta);
		return ResponseEntity.ok().body(cuenta);
	}
	
}
