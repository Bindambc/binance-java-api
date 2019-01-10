package com.binance.api.client;

import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.DepositAddress;
import com.binance.api.client.domain.account.DepositHistory;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.Trade;
import com.binance.api.client.domain.account.TradeHistoryItem;
import com.binance.api.client.domain.account.WithdrawHistory;
import com.binance.api.client.domain.account.WithdrawResult;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.CancelOrderResponse;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.domain.general.Asset;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.ServerTime;
import com.binance.api.client.domain.market.AggTrade;
import com.binance.api.client.domain.market.BookTicker;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.OrderBook;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.domain.market.TickerStatistics;

import java.util.List;

/**
 * Binance API façade, supporting asynchronous/non-blocking access Binance's REST API.
 */
public interface BinanceApiAsyncRestClient {

  // General endpoints

  /**
   * Teste a conectividade com a API Rest
   */
  void ping(BinanceApiCallback<Void> callback);

  /**
   * Verifique a hora do servidor.
   */
  void getServerTime(BinanceApiCallback<ServerTime> callback);

  /**
   * Regras atuais de negocia��o de c�mbio e informa��es sobre s�mbolos
   */
  void getExchangeInfo(BinanceApiCallback<ExchangeInfo> callback);

  /**
   * TODOS os ativos suportados e se podem ou n�o ser retirados
   */
  void getAllAssets(BinanceApiCallback<List<Asset>> callback);

  // Market Data endpoints

  /**
   * Obter livro de pedidos de um s�mbolo (ass�ncrono)
   *
   * @param s�mbolo ticker de s�mbolo (e.g. ETHBTC)
   * @param limite de profundidade do livro de pedidos (max 100)
   * @param chamar de volta o retorno de chamada que lida com a resposta
   */
  void getOrderBook(String symbol, Integer limit, BinanceApiCallback<OrderBook> callback);

  /**
   * Obter transa��es recentes (at� o �ltimo 500). Peso: 1
   *
   * @param s�mbolo ticker de s�mbolo (e.g. ETHBTC)
   * @param limite dos �ltimos neg�cios (Default 500; max 1000.)
   * @param chamar de volta o retorno de chamada que lida com a resposta
   */
  void getTrades(String symbol, Integer limit, BinanceApiCallback<List<TradeHistoryItem>> callback);

  /**
   * Obter com�rcios mais antigos. Peso: 5
   *
   * @param symbol ticker symbol (por exemplo, ETHBTC)
���* @param limite das �ltimas negocia��es (Padr�o 500; m�x. 1000.)
���* @param fromId TradeId para buscar. Padr�o recebe as negocia��es mais recentes.
���* @param callback o retorno de chamada que lida com a resposta
   */
  void getHistoricalTrades(String symbol, Integer limit, Long fromId, BinanceApiCallback<List<TradeHistoryItem>> callback);

  /**
   * Obtenha negocia��es compactadas e agregadas. Negocia��es que s�o preenchidas no momento, da mesma ordem, com
���* o mesmo pre�o ter� a quantidade agregada.
���*
���* Se ambos <code> startTime </ code> e <code> endTime </ code> forem enviados, <code> limit </ code> n�o dever�
���* ser enviado E a dist�ncia entre <code> startTime </ code> e <code> endTime </ code> deve ser menor que 24 horas.
���*
���* s�mbolo de s�mbolo @param para agregar (obrigat�rio)
���* @param fromId ID para obter transa��es agregadas de INCLUSIVE (opcional)
���* @param limit Padr�o 500; max 1000 (opcional)
���* @param startTime Timestamp em ms para obter transa��es agregadas de INCLUSIVE (opcional).
���* @param endTime Timestamp em ms para obter transa��es agregadas at� INCLUSIVE (opcional).
���* @param callback o retorno de chamada que lida com a resposta
���* @retornar uma lista de negocia��es agregadas para o s�mbolo fornecido
   */
  void getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime, BinanceApiCallback<List<AggTrade>> callback);

  /**
   *	Retornar as negocia��es agregadas mais recentes para o <code>symbol</ code>
���*
���* @see #getAggTrades (String, String, Inteiro, Longo, Longo, BinanceApiCallback)
   */
  void getAggTrades(String symbol, BinanceApiCallback<List<AggTrade>> callback);

  /**
   * Kline / barras de velas para um s�mbolo. Klines s�o identificados exclusivamente por seu tempo aberto.
���*
���* s�mbolo de s�mbolo @param para agregar (obrigat�rio)
���* Intervalo de candlestick do intervalo @param (obrigat�rio)
���* @param limit Padr�o 500; max 1000 (opcional)
���* @param startTime Timestamp em ms para obter barras de velas de INCLUSIVE (opcional).
���* @param endTime Timestamp em ms para obter barras de casti�al at� INCLUSIVE (opcional).
���* @param callback o retorno de chamada que manipula a resposta contendo uma barra de velas para o s�mbolo e o intervalo fornecidos
   */
  void getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime, BinanceApiCallback<List<Candlestick>> callback);

  /**
   * Kline / barras de velas para um s�mbolo. Klines s�o identificados exclusivamente por seu tempo aberto.
���*
���* @see #getCandlestickBars (String, CandlestickInterval, BinanceApiCallback)
   */
  void getCandlestickBars(String symbol, CandlestickInterval interval, BinanceApiCallback<List<Candlestick>> callback);

  /**
   * Obtenha estat�sticas de altera��o de pre�o de 24 horas (ass�ncrono).
���*
���* s�mbolo @param symbol ticker (por exemplo, ETHBTC)
���* @param callback o retorno de chamada que lida com a resposta
   */
  void get24HrPriceStatistics(String symbol, BinanceApiCallback<TickerStatistics> callback);
  
  /**
   * Obtenha estat�sticas de altera��o de pre�o de 24 horas para todos os s�mbolos (ass�ncronos).
���*
���* @param callback o retorno de chamada que lida com a resposta
   */
   void getAll24HrPriceStatistics(BinanceApiCallback<List<TickerStatistics>> callback);

  /**
   * Obtenha o pre�o mais recente para todos os s�mbolos (ass�ncronos).
���*
���* @param callback o retorno de chamada que lida com a resposta
   */
  void getAllPrices(BinanceApiCallback<List<TickerPrice>> callback);
  
  /**
 * Obtenha o pre�o mais recente para o <code>symbol</code> (ass�ncrono).
���*
���* s�mbolo @param symbol ticker (por exemplo, ETHBTC)
���* @param callback o retorno de chamada que lida com a resposta
   */
   void getPrice(String symbol , BinanceApiCallback<TickerPrice> callback);

  /**
   * Obtenha o melhor pre�o / qty no livro de ofertas para todos os s�mbolos (ass�ncronos).
���*
���* @param callback o retorno de chamada que lida com a resposta
   */
  void getBookTickers(BinanceApiCallback<List<BookTicker>> callback);

  // Account endpoints

  /**
   * Envie um novo pedido (ass�ncrono)
���*
���* @param solicite o novo pedido para enviar.
���* @param callback o retorno de chamada que lida com a resposta
   */
  void newOrder(NewOrder order, BinanceApiCallback<NewOrderResponse> callback);

  /**
   * Teste a cria��o de novas encomendas e assinatura / recvWindow long. Cria e valida um novo pedido, mas n�o o envia para o mecanismo de correspond�ncia.
���*
���* @param pede a nova ordem TEST para enviar.
���* @param callback o retorno de chamada que lida com a resposta
   */
  void newOrderTest(NewOrder order, BinanceApiCallback<Void> callback);

  /**
   * Verifique o status de um pedido (ass�ncrono).
���*
���Par�metros de solicita��o de status do pedido @param orderStatusRequest
���* @param callback o retorno de chamada que lida com a resposta
   */
  void getOrderStatus(OrderStatusRequest orderStatusRequest, BinanceApiCallback<Order> callback);

  /**
   * Cancelar um pedido ativo (ass�ncrono).
���*
���Par�metros de solicita��o de status do pedido @param cancelOrderRequest
���* @param callback o retorno de chamada que lida com a resposta
   */
  void cancelOrder(CancelOrderRequest cancelOrderRequest, BinanceApiCallback<CancelOrderResponse> callback);

  /**
  * Obter todos os pedidos em aberto em um s�mbolo (ass�ncrono).
���*
���Par�metros de solicita��o de pedido @param orderRequest
���* @param callback o retorno de chamada que lida com a resposta
   */
  void getOpenOrders(OrderRequest orderRequest, BinanceApiCallback<List<Order>> callback);

  /**
  * Obter todos os pedidos de conta; ativo, cancelado ou preenchido.
���*
���* @param Par�metros de solicita��o de pedido orderRequest
���* @param callback o retorno de chamada que lida com a resposta
   */
  void getAllOrders(AllOrdersRequest orderRequest, BinanceApiCallback<List<Order>> callback);

  /**
   * Obter informa��es da conta atual (ass�ncrono).
   */
  void getAccount(Long recvWindow, Long timestamp, BinanceApiCallback<Account> callback);

  /**
   * Obter informa��es da conta atual usando os par�metros padr�o (ass�ncrono).
   */
  void getAccount(BinanceApiCallback<Account> callback);

  /**
   * Obter negocia��es para uma conta e s�mbolo espec�ficos.
���*
���* @param s�mbolo de s�mbolo para obter negocia��es de
���* @param limite padr�o 500; max 1000
���* @param fromId TradeId para buscar. Padr�o recebe as negocia��es mais recentes.
���* @param callback o retorno de chamada que lida com a resposta com uma lista de negocia��es
   */
  void getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp, BinanceApiCallback<List<Trade>> callback);

  /**
  * Obter negocia��es para uma conta e s�mbolo espec�ficos.
���*
���* @param s�mbolo de s�mbolo para obter negocia��es de
���* @param limite padr�o 500; max 1000
���* @param callback o retorno de chamada que lida com a resposta com uma lista de negocia��es
   */
  void getMyTrades(String symbol, Integer limit, BinanceApiCallback<List<Trade>> callback);

  /**
   * Obter negocia��es para uma conta e s�mbolo espec�ficos.
���*
���* @param s�mbolo de s�mbolo para obter negocia��es de
���* @param callback o retorno de chamada que lida com a resposta com uma lista de negocia��es
   */
  void getMyTrades(String symbol, BinanceApiCallback<List<Trade>> callback);

  /**
   * Envie uma solicita��o de retirada.
���*
���* A op��o Ativar retiradas deve estar ativa nas configura��es da API.
���*
���* @param asset asset symbol para retirar
���* @param endere�o de endere�o param para retirar a
���* @param quantidade de quantia de param para retirar
���* @param nome do nome do parame / apelido do endere�o
���* @param addressTag Identificador de endere�o secund�rio para moedas como XRP, XMR etc.
   */
  void withdraw(String asset, String address, String amount, String name, String addressTag, BinanceApiCallback<WithdrawResult> callback);

  /**
   * Buscar o hist�rico de dep�sitos da conta.
���*
���* @param callback o retorno de chamada que manipula a resposta e retorna o hist�rico de dep�sito
   */
  void getDepositHistory(String asset, BinanceApiCallback<DepositHistory> callback);

  /**
   * Buscar o hist�rico de retirada da conta.
���*
���* @param callback o retorno de chamada que manipula a resposta e retorna o hist�rico de retirada
   */
  void getWithdrawHistory(String asset, BinanceApiCallback<WithdrawHistory> callback);

  /**
   * Buscar endere�o de dep�sito.
���*
���* @param callback o retorno de chamada que manipula a resposta e retorna o endere�o de dep�sito
   */
   void getDepositAddress(String asset, BinanceApiCallback<DepositAddress> callback);

  // User stream endpoints

  /**
  * Inicie um novo fluxo de dados do usu�rio.
���*
���* @param callback o retorno de chamada que lida com a resposta que cont�m um listenKey
   */
  void startUserDataStream(BinanceApiCallback<ListenKey> callback);

  /**
   * PING um fluxo de dados do usu�rio para evitar um tempo limite.
���*
���* @param listenKey tecla de escuta que identifica um fluxo de dados
���* @param callback o retorno de chamada que lida com a resposta que cont�m um listenKey
   */
  void keepAliveUserDataStream(String listenKey, BinanceApiCallback<Void> callback);

  /**
   * Feche um novo fluxo de dados do usu�rio.
���*
���* @param listenKey tecla de escuta que identifica um fluxo de dados
���* @param callback o retorno de chamada que lida com a resposta que cont�m um listenKey
   */
  void closeUserDataStream(String listenKey, BinanceApiCallback<Void> callback);
}