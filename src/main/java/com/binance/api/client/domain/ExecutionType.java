package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Ordenar tipo de execução.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ExecutionType {
  NEW, // NOVO
  CANCELED, // CANCELADO
  REPLACED, // SUBSTITUÍDO
  REJECTED, // REJEITADO
  TRADE, // COMÉRCIO
  EXPIRED // EXPIRADO
}