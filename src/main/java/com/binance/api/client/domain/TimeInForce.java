package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Tempo em vigor para indicar quanto tempo um pedido permanecerá ativo antes de ser executado ou expirar.
 *
 * GTC (Good-Til-Canceled) as ordens são efetivas até que sejam executadas ou canceladas.
 * IOC (Immediate or Cancel) as ordens preenchem todo ou parte de um pedido imediatamente e cancelam a parte restante do pedido.
 * FOK (Fill or Kill) os pedidos preenchem todos em sua totalidade, caso contrário, todo o pedido será cancelado.
 *
 * @see <a href="http://www.investopedia.com/terms/t/timeinforce.asp">http://www.investopedia.com/terms/t/timeinforce.asp</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum TimeInForce {
  GTC, // Bom até cancelado
  IOC, // Imediato ou Cancelar
  FOK // Preencha ou Mate
}
