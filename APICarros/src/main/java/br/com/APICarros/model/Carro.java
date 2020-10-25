package br.com.APICarros.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Table;

@Entity
@javax.persistence.Table
public class Carro {
	
	private Long id;
	private BigDecimal valor;
	private String marca;
	private String modelo;
	private int anoModelo;
	private String combustivel;
	private String codigoFipe;
	private String mesReferencia;
	private int tipoVeiculo;
	private String siglaCombustivel;
	
	
	public Carro(Long id, BigDecimal valor, String marca, String modelo, int anoModelo, String combustivel, String codigoFipe,
			String mesReferencia, int tipoVeiculo, String siglaCombustivel) {
		
		this.id = id;
		this.valor = valor;
		this.marca = marca;
		this.modelo = modelo;
		this.anoModelo = anoModelo;
		this.combustivel = combustivel;
		this.codigoFipe = codigoFipe;
		this.mesReferencia = mesReferencia;
		this.tipoVeiculo = tipoVeiculo;
		this.siglaCombustivel = siglaCombustivel;
	}

	public Carro() {}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getAnoModelo() {
		return anoModelo;
	}


	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}


	public String getCombustivel() {
		return combustivel;
	}


	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}


	@Column(name = "codigo_fipe")
	public String getCodigoFipe() {
		return codigoFipe;
	}


	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}

	@Column(name = "mes_referencia")
	public String getMesReferencia() {
		return mesReferencia;
	}


	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	@Column(name = "tipo_veiculo")
	public int getTipoVeiculo() {
		return tipoVeiculo;
	}


	public void setTipoVeiculo(int tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	@Column(name = "sigla_combustivel")
	public String getSiglaCombustivel() {
		return siglaCombustivel;
	}


	public void setSiglaCombustivel(String siglaCombustivel) {
		this.siglaCombustivel = siglaCombustivel;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
