package ec.mjaramillo.movimiento.dto;

import java.util.Date;

public class ListaMovimientos {
	private Date fecha;
	private String nombre;
	private String numeroCuenta;
	private String tipoCuenta;
	private Double saldoInicial;
	private boolean estado;
	private Double movimiento;
	private Double saldoDisponible;
	private String tipoMovimiento;
	
	public ListaMovimientos() {
		// TODO Auto-generated constructor stub
	}
	
	public ListaMovimientos(Date fecha, String nombre, String numeroCuenta, Double saldoInicial, boolean estado, String tipoCuenta,
			Double movimiento, String tipoMovimiento, Double saldoDisponible) {
		super();
		this.fecha = fecha;
		this.nombre = nombre;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.movimiento = movimiento;
		this.saldoDisponible = saldoDisponible;
		this.tipoMovimiento = tipoMovimiento;
	}



	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Double getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(Double movimiento) {
		this.movimiento = movimiento;
	}
	public Double getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}
