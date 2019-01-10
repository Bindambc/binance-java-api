package com.binance.api.model;

import java.math.BigDecimal;
import java.util.List;

import com.binance.api.consulta.DadosDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Order {
	private Long idOrder;
	private ETipo tipo;
	private BigDecimal valor;
	private BigDecimal quantidade;
	private String moeda;
	private ESimNao atendida;

	public Order findById(Long idOrder) {
		return DadosDAO.findById(idOrder);
	}

	public List<Order> listarOrdens() {
		return DadosDAO.listarOrdens();
	}

	public List<Order> listarOrdensNaoAtendidas() {
		return DadosDAO.listarOrdensNaoAtendidas();
	}

	public void insert() {
		DadosDAO.guardaCompraVenda(this);
	}

	public void atendePedido() {
		DadosDAO.atendePedido(this.idOrder);
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public void setTipo(ETipo tipo) {
		this.tipo = tipo;
	}

	public ETipo getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo.setValue(tipo);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public ESimNao getAtendida() {
		return atendida;
	}

	public void setAtendida(ESimNao atendida) {
		this.atendida = atendida;
	}
	public void setAtendida(Long atendida) {
		this.atendida.setValue(atendida);
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public enum ETipo {
		COMPRA(1L), VENDA(2L);

		private Long value;

		ETipo(Long value) {
			this.value = value;
		}

		public Long getValue() {
			return value;
		}

		public void setValue(Long value) {
			this.value = value;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public enum ESimNao {
		NAO(0L), SIM(1L);

		private Long value;

		ESimNao(Long value) {
			this.value = value;
		}

		public Long getValue() {
			return value;
		}

		public void setValue(Long value) {
			this.value = value;
		}
	}

}
