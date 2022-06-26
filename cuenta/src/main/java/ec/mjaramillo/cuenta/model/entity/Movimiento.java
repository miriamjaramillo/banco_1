package ec.mjaramillo.cuenta.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name =  "movimiento_id", unique = true  , nullable = false)
	@TableGenerator(name="entidad_generadora",
	    table="id_gen",
	    pkColumnName="nombre",
	    valueColumnName="valor",
	    pkColumnValue="movimiento_id",
	    initialValue=0,
	    allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE,
			generator = "entidad_generadora")
	private Long movimientoId;
	@Column(name ="fecha", nullable=false)
	private Date fecha;
	@Column(name ="tipo_movimiento", nullable=false)
	private String tipoMovimiento;
	private Double valor;
	private Double saldo;
	
	@ManyToMany
	@JoinTable(
			name = "cuenta_movimiento",
			joinColumns = @JoinColumn(name = "movimiento_id"),
			inverseJoinColumns = @JoinColumn(name = "cuenta_id")
	)
	@JsonIgnore
	private Set<Cuenta> movimientosCuentas = new HashSet<>();
	
	public Movimiento() {
	}
	
	public Movimiento(String tipoMovimiento, Double valor) {
		Calendar calendar = Calendar.getInstance();
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.fecha = calendar.getTime();		
	}
	
	public Long getMovimientoId() {
		return movimientoId;
	}
	public void setMovimientoId(Long movimientoId) {
		this.movimientoId = movimientoId;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Set<Cuenta> getMovimientosCuentas() {
		return movimientosCuentas;
	}

	public void setMovimientosCuentas(Set<Cuenta> movimientosCuentas) {
		this.movimientosCuentas = movimientosCuentas;
	}

	public void realizaMovimiento() {
		Double total = getTipoMovimiento().equals("DEP") ? (getSaldo() + getValor()) : (getSaldo() - getValor());
		this.setSaldo(total);
	}
}
