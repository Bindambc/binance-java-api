package com.binance.api.robo;

import java.math.BigDecimal;
import java.util.List;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.TradeHistoryItem;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerStatistics;

public class RoboBasico extends Robo {
	private final String API_KEY_CONSULTA = "iK0Xo72SsvLD9biQjcxttexmhx7xTh2s3NIt8CoWt8eVycn7UzA8WE0ucB6eX0WC";
	private final String SECRECT_KEY_CONSULTA = "kh4K5oUAmQlhTdvu0VVyoyy6xsdfiXsR80YxAmatMBd34peEAO3LJw8g62LXlbDi";

	RoboConsulta consulta;

	public RoboBasico(String apiKey, String secrectKey, String moeda) {
		super(apiKey, secrectKey, moeda);
		consulta = new RoboConsulta(API_KEY_CONSULTA, SECRECT_KEY_CONSULTA, moeda);
		consulta.start();
	}

	@Override
	public void run() {
		try {
			// Apenas para esperar o robo de consulta iniciar
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.err.println(this.getNome() + " Iniciou!");
		
		

		while (true) {
			try {

//				TickerStatistics tickerStatistics = client.get24HrPriceStatistics(moeda);
//				System.out.println(tickerStatistics);
//
//				System.out.println("Preço Maximo: " + tickerStatistics.getHighPrice());
//				System.out.println("Preço Mínimo: " + tickerStatistics.getLowPrice());
//
//				List<TradeHistoryItem> getTrades = client.getTrades(moeda, 100);
//
//				if (!getTrades.isEmpty()) {
//
//					BigDecimal menorPreco = new BigDecimal(getTrades.get(0).getPrice());
//					TradeHistoryItem melhorItem = getTrades.get(0);
//					for (TradeHistoryItem item : getTrades) {
//						if (new BigDecimal(item.getPrice()).compareTo(menorPreco) < 0) {
//							melhorItem = item;
//						}
//						System.out.println(item);
//					}
//					System.out.println("melhor item: " + melhorItem);
//				}

				// NOVOS TESTES
//				System.out.println(client.getCandlestickBars(moeda, CandlestickInterval.ONE_MINUTE)); //top

//				System.out.println(client.getPrice(moeda));

//				client.newOrder(NewOrder.marketBuy(moeda, "1")); //Compra
//				client.newOrder(NewOrder.marketSell(moeda, "1")); //Venda

				// TESTE
//				client.newOrderTest(NewOrder.marketBuy(moeda, "1")); //Compra
//				client.newOrderTest(NewOrder.marketSell(moeda, "1")); //Venda

//				System.out.println(this.getClient().getAccount()); // Busca contas com saldo. está retornando vazio pois
																	// n tem
				// nada
				// Exemplo: initializeAssetBalanceCacheAndStreamSession

//				this.getClient().keepAliveUserDataStream(this.getListenKey());
				
//				System.out.println(consulta.getValorMoeda().getPrice());
				Thread.sleep(500);

			} catch (Exception e) {
				System.err.println(this.getNome() + " Deu pau");
				e.printStackTrace();
			}

		}
	}

	public void m1() {
	}
	
}
