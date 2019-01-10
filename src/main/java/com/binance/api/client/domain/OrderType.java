package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Tipo de pedido para enviar ao sistema.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderType {
  LIMIT, // LIMITE
  MARKET, // MERCADO
  STOP_LOSS, // PARAR A PERDA DE
  STOP_LOSS_LIMIT, // PARAR O LIMITE DE PERDA
  TAKE_PROFIT, // OBTER LUCROS
  TAKE_PROFIT_LIMIT, // TOME O LIMITE DO LUCRO
  LIMIT_MAKER // LIMITADOR
}
