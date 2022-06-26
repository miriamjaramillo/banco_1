package ec.mjaramillo.movimiento.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.mjaramillo.movimiento.model.entity.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{

	public List<Movimiento> findMovimientosByMovimientosCuentasCuentaId(Long idCuenta);
	
	@Query("SELECT m FROM Movimiento m JOIN m.movimientosCuentas c WHERE c.cuentaId = ?1 and m.movimientoId = (SELECT max(m.movimientoId) FROM Movimiento m) ORDER BY m.fecha DESC")
	public Movimiento getLastMovimientoCuenta(Long idCuenta);
	
	@Query(value = "select sum(m.saldo) from movimiento m inner join cuenta_movimiento cm on m.movimiento_id = cm.movimiento_id "
			+ " inner join cuenta c on c.cuenta_id = cm.cuenta_id"
			+ " where to_date(cast(m.fecha as text), 'YYYY-MM-DD') >= current_date and m.tipo_movimiento = :tipoMovimiento"
			+ " and c.cuenta_id = :cuentaId group by c.cuenta_id", nativeQuery = true)
	public Double obtenerSaldoMovimientoDiario(
			  @Param("cuentaId") Long cuentaId, @Param("tipoMovimiento") String tipoMovimiento);
	
	@Query(value="select count(cm.cuenta_id) from cuenta_movimiento cm" + 
			" inner join cuenta c on cm.cuenta_id = c.cuenta_id" + 
			" where c.cuenta_id = :cuentaId", nativeQuery = true)
	public int obtenerTotalMovimientos(@Param("cuentaId") Long cuentaId);
	
	@Query(value="select m.fecha, c.nombre, ca.numero_cuenta, ca.saldo_inicial, ca.estado, ca.tipo_cuenta, m.valor, m.tipo_movimiento, m.saldo from cliente c " + 
			"inner join cuenta ca on c.persona_id = ca.persona_id " + 
			"inner join cuenta_movimiento cm on cm.cuenta_id = ca.cuenta_id " + 
			"inner join movimiento m on cm.movimiento_id = m.movimiento_id " + 
			"where c.persona_id = :personaId and m.fecha between :fechaInicio and :fechaFin " + 
			"order by m.fecha desc", nativeQuery = true)
	public List<Object[]> rows(@Param("personaId") Long personaId, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
	
}
