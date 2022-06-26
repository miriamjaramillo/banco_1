package ec.mjaramillo.movimiento.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.mjaramillo.movimiento.dto.ListaMovimientos;
import ec.mjaramillo.movimiento.model.entity.Movimiento;
import ec.mjaramillo.movimiento.model.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	@Qualifier("serviceMovimientoFeign")
	private MovimientoService movimientoService;
	
	@GetMapping("/listar/{idCuenta}/")
	public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuentaId(@PathVariable(value = "idCuenta") Long idCuenta) {
		List<Movimiento> listaMovimientos = movimientoService.obtenerMovimientosPorCuentaId(idCuenta);
		return new ResponseEntity<>(listaMovimientos, HttpStatus.OK);
	}
	
	@PostMapping("/crear/{numCuenta}")
	public ResponseEntity<?> agregarMovimiento(@PathVariable(value = "numCuenta") String numCuenta, @RequestBody Movimiento movimientoRequest) {
		Movimiento movimiento = movimientoService.movimientoPorCuenta(numCuenta, movimientoRequest);
		return new ResponseEntity<>(movimiento, HttpStatus.CREATED);
	}
	
	@GetMapping("/listarMovimientos/{personaId}/{fechaInicio}/{fechaFin}")
	public List<ListaMovimientos> listarMovimientosPorFecha(@PathVariable(value = "personaId") Long personaId,
			@PathVariable(value = "fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@PathVariable(value = "fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
		List<ListaMovimientos> listaMovimientos = movimientoService.listarMovimientosPorFecha(personaId, fechaInicio, fechaFin);
		return listaMovimientos;
	}
}
