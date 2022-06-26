package ec.mjaramillo.persona.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ec.mjaramillo.persona.model.entity.persona.Cliente;

@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name =  "cuenta_id", unique = true  , nullable = false)
	@TableGenerator(name="entidad_generadora",
	    table="id_gen",
	    pkColumnName="nombre",
	    valueColumnName="valor",
	    pkColumnValue="cuenta_id",
	    initialValue=0,
	    allocationSize=1
	)
	@GeneratedValue(strategy = GenerationType.TABLE,
			generator = "entidad_generadora")
	private Long cuentaId;
	
	@Column(name ="numero_cuenta", unique=true, nullable=false)
	private String numeroCuenta;
	
	@Column(name ="tipo_cuenta", nullable=false)
	private String tipoCuenta;
	
	@Column(name ="saldo_inicial", nullable=false)
	private Double saldoInicial;
	
	private Boolean estado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "persona_id",
			referencedColumnName = "persona_id",
			updatable = false, nullable = false)
	private Cliente cliente;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "movimientosCuentas")
	private Set<Movimiento> movimientos = new HashSet<>();
	
	public Cuenta() {
	}
	
	public Cuenta(String numeroCuenta, String tipoCuenta, Double saldoInical, Boolean estado, Cliente cliente) {
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInical;
		this.estado = estado;
		this.cliente = cliente;
	}
	
	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}