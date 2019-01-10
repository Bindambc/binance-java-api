package com.binance.api.robo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.binance.api.client.domain.market.TickerPrice;

public class RoboConsulta extends Robo {
	private final int escalaMedia = 2;

	private TickerPrice valorMoeda;
	private List<String> precos;

	public RoboConsulta(String apiKey, String secrectKey, String moeda) {
		super(apiKey, secrectKey, moeda);
		precos = new ArrayList<>();
	}

	@Override
	public void run() {
		boolean start = true;
		while (start) {
			try {

				this.setValorMoeda(this.getClient().getPrice(this.getMoeda()));

				if (precos.size() < escalaMedia)
					precos.add(this.getClient().getPrice(this.getMoeda()).getPrice());
				else {
					precos.remove(0);
					precos.add(this.getClient().getPrice(this.getMoeda()).getPrice());
				}

				/*for (String a : precos)
					System.out.print(a + " ");
				System.out.println();
				 */
				System.out.println("Media: " + calculaMedia());
				System.out.println(this.getClient().getPrice(this.getMoeda()).getPrice());

				Thread.sleep(300);
			
			} catch (Exception e) {
				start = false;
				e.printStackTrace();
				System.err.println("Deu pau no robo de consulta: " + e.getMessage());
				System.out.println("Causa: " + e.getCause());
			}
		}
	}

	public TickerPrice getValorMoeda() {
		return valorMoeda;
	}

	public void setValorMoeda(TickerPrice valorMoeda) {
		this.valorMoeda = valorMoeda;
	}

	public BigDecimal calculaMedia() {
		BigDecimal valor = BigDecimal.ZERO;

		for (String a : precos)
			valor = valor.add(new BigDecimal(a));
		return valor.divide(new BigDecimal(precos.size()), 5, RoundingMode.HALF_DOWN);
	}

}
