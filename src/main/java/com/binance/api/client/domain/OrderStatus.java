package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Status de um pedido enviado.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderStatus {
  NEW, // NOVO
  PARTIALLY_FILLED, // PARCIALMENTE CHEIO
  FILLED, // PREENCHIDAS
  CANCELED, // CANCELADO
  PENDING_CANCEL, // CANCELAR PENDENTE
  REJECTED, // REJEITADO
  EXPIRED // EXPIRADO
}
