package com.binance.api.robo;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;

public abstract class Robo extends Thread {

	private String nome;
	private BinanceApiClientFactory factory;
	private BinanceApiRestClient client;
	private String moeda;
	private String listenKey;
	private Account account;

	public Robo(String apiKey, String secrectKey, String moeda) {
		this.nome = "Robo_" + moeda;
		this.factory = BinanceApiClientFactory.newInstance(apiKey, secrectKey);
		this.client = this.factory.newRestClient();
		this.moeda = moeda;
		this.listenKey = client.startUserDataStream();
		this.account = client.getAccount();
	}

	@Override
	public abstract void run();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BinanceApiClientFactory getFactory() {
		return factory;
	}

	public void setFactory(BinanceApiClientFactory factory) {
		this.factory = factory;
	}

	public BinanceApiRestClient getClient() {
		return client;
	}

	public void setClient(BinanceApiRestClient client) {
		this.client = client;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public String getListenKey() {
		return listenKey;
	}

	public void setListenKey(String listenKey) {
		this.listenKey = listenKey;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
