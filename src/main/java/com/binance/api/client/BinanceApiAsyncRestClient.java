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
 * Binance API faÃ§ade, supporting asynchronous/non-blocking access Binance's REST API.
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
   * Regras atuais de negociação de câmbio e informações sobre símbolos
   */
  void getExchangeInfo(BinanceApiCallback<ExchangeInfo> callback);

  /**
   * TODOS os ativos suportados e se podem ou não ser retirados
   */
  void getAllAssets(BinanceApiCallback<List<Asset>> callback);

  // Market Data endpoints

  /**
   * Obter livro de pedidos de um símbolo (assíncrono)
   *
   * @param símbolo ticker de símbolo (e.g. ETHBTC)
   * @param limite de profundidade do livro de pedidos (max 100)
   * @param chamar de volta o retorno de chamada que lida com a resposta
   */
  void getOrderBook(String symbol, Integer limit, BinanceApiCallback<OrderBook> callback);

  /**
   * Obter transações recentes (até o último 500). Peso: 1
   *
   * @param símbolo ticker de símbolo (e.g. ETHBTC)
   * @param limite dos últimos negócios (Default 500; max 1000.)
   * @param chamar de volta o retorno de chamada que lida com a resposta
   */
  void getTrades(String symbol, Integer limit, BinanceApiCallback<List<TradeHistoryItem>> callback);

  /**
   * Obter comércios mais antigos. Peso: 5
   *
   * @param symbol ticker symbol (por exemplo, ETHBTC)
   * @param limite das últimas negociações (Padrão 500; máx. 1000.)
   * @param fromId TradeId para buscar. Padrão recebe as negociações mais recentes.
   * @param callback o retorno de chamada que lida com a resposta
   */
  void getHistoricalTrades(String symbol, Integer limit, Long fromId, BinanceApiCallback<List<TradeHistoryItem>> callback);

  /**
   * Obtenha negociações compactadas e agregadas. Negociações que são preenchidas no momento, da mesma ordem, com
   * o mesmo preço terá a quantidade agregada.
   *
   * Se ambos <code> startTime </ code> e <code> endTime </ code> forem enviados, <code> limit </ code> não deverá
   * ser enviado E a distância entre <code> startTime </ code> e <code> endTime </ code> deve ser menor que 24 horas.
   *
   * símbolo de símbolo @param para agregar (obrigatório)
   * @param fromId ID para obter transações agregadas de INCLUSIVE (opcional)
   * @param limit Padrão 500; max 1000 (opcional)
   * @param startTime Timestamp em ms para obter transações agregadas de INCLUSIVE (opcional).
   * @param endTime Timestamp em ms para obter transações agregadas até INCLUSIVE (opcional).
   * @param callback o retorno de chamada que lida com a resposta
   * @retornar uma lista de negociações agregadas para o símbolo fornecido
   */
  void getAggTrades(String symbol, String fromId, Integer limit, Long startTime, Long endTime, BinanceApiCallback<List<AggTrade>> callback);

  /**
   *	Retornar as negociações agregadas mais recentes para o <code>symbol</ code>
   *
   * @see #getAggTrades (String, String, Inteiro, Longo, Longo, BinanceApiCallback)
   */
  void getAggTrades(String symbol, BinanceApiCallback<List<AggTrade>> callback);

  /**
   * Kline / barras de velas para um símbolo. Klines são identificados exclusivamente por seu tempo aberto.
   *
   * símbolo de símbolo @param para agregar (obrigatório)
   * Intervalo de candlestick do intervalo @param (obrigatório)
   * @param limit Padrão 500; max 1000 (opcional)
   * @param startTime Timestamp em ms para obter barras de velas de INCLUSIVE (opcional).
   * @param endTime Timestamp em ms para obter barras de castiçal até INCLUSIVE (opcional).
   * @param callback o retorno de chamada que manipula a resposta contendo uma barra de velas para o símbolo e o intervalo fornecidos
   */
  void getCandlestickBars(String symbol, CandlestickInterval interval, Integer limit, Long startTime, Long endTime, BinanceApiCallback<List<Candlestick>> callback);

  /**
   * Kline / barras de velas para um símbolo. Klines são identificados exclusivamente por seu tempo aberto.
   *
   * @see #getCandlestickBars (String, CandlestickInterval, BinanceApiCallback)
   */
  void getCandlestickBars(String symbol, CandlestickInterval interval, BinanceApiCallback<List<Candlestick>> callback);

  /**
   * Obtenha estatísticas de alteração de preço de 24 horas (assíncrono).
   *
   * símbolo @param symbol ticker (por exemplo, ETHBTC)
   * @param callback o retorno de chamada que lida com a resposta
   */
  void get24HrPriceStatistics(String symbol, BinanceApiCallback<TickerStatistics> callback);
  
  /**
   * Obtenha estatísticas de alteração de preço de 24 horas para todos os símbolos (assíncronos).
   *
   * @param callback o retorno de chamada que lida com a resposta
   */
   void getAll24HrPriceStatistics(BinanceApiCallback<List<TickerStatistics>> callback);

  /**
   * Obtenha o preço mais recente para todos os símbolos (assíncronos).
   *
   * @param callback o retorno de chamada que lida com a resposta
   */
  void getAllPrices(BinanceApiCallback<List<TickerPrice>> callback);
  
  /**
 * Obtenha o preço mais recente para o <code>symbol</code> (assíncrono).
   *
   * símbolo @param symbol ticker (por exemplo, ETHBTC)
   * @param callback o retorno de chamada que lida com a resposta
   */
   void getPrice(String symbol , BinanceApiCallback<TickerPrice> callback);

  /**
   * Obtenha o melhor preço / qty no livro de ofertas para todos os símbolos (assíncronos).
   *
   * @param callback o retorno de chamada que lida com a resposta
   */
  void getBookTickers(BinanceApiCallback<List<BookTicker>> callback);

  // Account endpoints

  /**
   * Envie um novo pedido (assíncrono)
   *
   * @param solicite o novo pedido para enviar.
   * @param callback o retorno de chamada que lida com a resposta
   */
  void newOrder(NewOrder order, BinanceApiCallback<NewOrderResponse> callback);

  /**
   * Teste a criação de novas encomendas e assinatura / recvWindow long. Cria e valida um novo pedido, mas não o envia para o mecanismo de correspondência.
   *
   * @param pede a nova ordem TEST para enviar.
   * @param callback o retorno de chamada que lida com a resposta
   */
  void newOrderTest(NewOrder order, BinanceApiCallback<Void> callback);

  /**
   * Verifique o status de um pedido (assíncrono).
   *
   Parâmetros de solicitação de status do pedido @param orderStatusRequest
   * @param callback o retorno de chamada que lida com a resposta
   */
  void getOrderStatus(OrderStatusRequest orderStatusRequest, BinanceApiCallback<Order> callback);

  /**
   * Cancelar um pedido ativo (assíncrono).
   *
   Parâmetros de solicitação de status do pedido @param cancelOrderRequest
   * @param callback o retorno de chamada que lida com a resposta
   */
  void cancelOrder(CancelOrderRequest cancelOrderRequest, BinanceApiCallback<CancelOrderResponse> callback);

  /**
  * Obter todos os pedidos em aberto em um símbolo (assíncrono).
   *
   Parâmetros de solicitação de pedido @param orderRequest
   * @param callback o retorno de chamada que lida com a resposta
   */
  void getOpenOrders(OrderRequest orderRequest, BinanceApiCallback<List<Order>> callback);

  /**
  * Obter todos os pedidos de conta; ativo, cancelado ou preenchido.
   *
   * @param Parâmetros de solicitação de pedido orderRequest
   * @param callback o retorno de chamada que lida com a resposta
   */
  void getAllOrders(AllOrdersRequest orderRequest, BinanceApiCallback<List<Order>> callback);

  /**
   * Obter informações da conta atual (assíncrono).
   */
  void getAccount(Long recvWindow, Long timestamp, BinanceApiCallback<Account> callback);

  /**
   * Obter informações da conta atual usando os parâmetros padrão (assíncrono).
   */
  void getAccount(BinanceApiCallback<Account> callback);

  /**
   * Obter negociações para uma conta e símbolo específicos.
   *
   * @param símbolo de símbolo para obter negociações de
   * @param limite padrão 500; max 1000
   * @param fromId TradeId para buscar. Padrão recebe as negociações mais recentes.
   * @param callback o retorno de chamada que lida com a resposta com uma lista de negociações
   */
  void getMyTrades(String symbol, Integer limit, Long fromId, Long recvWindow, Long timestamp, BinanceApiCallback<List<Trade>> callback);

  /**
  * Obter negociações para uma conta e símbolo específicos.
   *
   * @param símbolo de símbolo para obter negociações de
   * @param limite padrão 500; max 1000
   * @param callback o retorno de chamada que lida com a resposta com uma lista de negociações
   */
  void getMyTrades(String symbol, Integer limit, BinanceApiCallback<List<Trade>> callback);

  /**
   * Obter negociações para uma conta e símbolo específicos.
   *
   * @param símbolo de símbolo para obter negociações de
   * @param callback o retorno de chamada que lida com a resposta com uma lista de negociações
   */
  void getMyTrades(String symbol, BinanceApiCallback<List<Trade>> callback);

  /**
   * Envie uma solicitação de retirada.
   *
   * A opção Ativar retiradas deve estar ativa nas configurações da API.
   *
   * @param asset asset symbol para retirar
   * @param endereço de endereço param para retirar a
   * @param quantidade de quantia de param para retirar
   * @param nome do nome do parame / apelido do endereço
   * @param addressTag Identificador de endereço secundário para moedas como XRP, XMR etc.
   */
  void withdraw(String asset, String address, String amount, String name, String addressTag, BinanceApiCallback<WithdrawResult> callback);

  /**
   * Buscar o histórico de depósitos da conta.
   *
   * @param callback o retorno de chamada que manipula a resposta e retorna o histórico de depósito
   */
  void getDepositHistory(String asset, BinanceApiCallback<DepositHistory> callback);

  /**
   * Buscar o histórico de retirada da conta.
   *
   * @param callback o retorno de chamada que manipula a resposta e retorna o histórico de retirada
   */
  void getWithdrawHistory(String asset, BinanceApiCallback<WithdrawHistory> callback);

  /**
   * Buscar endereço de depósito.
   *
   * @param callback o retorno de chamada que manipula a resposta e retorna o endereço de depósito
   */
   void getDepositAddress(String asset, BinanceApiCallback<DepositAddress> callback);

  // User stream endpoints

  /**
  * Inicie um novo fluxo de dados do usuário.
   *
   * @param callback o retorno de chamada que lida com a resposta que contém um listenKey
   */
  void startUserDataStream(BinanceApiCallback<ListenKey> callback);

  /**
   * PING um fluxo de dados do usuário para evitar um tempo limite.
   *
   * @param listenKey tecla de escuta que identifica um fluxo de dados
   * @param callback o retorno de chamada que lida com a resposta que contém um listenKey
   */
  void keepAliveUserDataStream(String listenKey, BinanceApiCallback<Void> callback);

  /**
   * Feche um novo fluxo de dados do usuário.
   *
   * @param listenKey tecla de escuta que identifica um fluxo de dados
   * @param callback o retorno de chamada que lida com a resposta que contém um listenKey
   */
  void closeUserDataStream(String listenKey, BinanceApiCallback<Void> callback);
}