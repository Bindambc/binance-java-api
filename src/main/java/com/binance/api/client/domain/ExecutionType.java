package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Ordenar tipo de execu��o.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ExecutionType {
  NEW, // NOVO
  CANCELED, // CANCELADO
  REPLACED, // SUBSTITU�DO
  REJECTED, // REJEITADO
  TRADE, // COM�RCIO
  EXPIRED // EXPIRADO
}