package com.binance.api.run;

import com.binance.api.robo.RoboBasico;
import com.binance.api.robo.RoboConsulta;

// TODO Implementado inicialmente com métodos REST. Alterar para websocket
public class RobotApplication {

	final static String API_KEY = "iUhYxKKkvU4ThN6Fy7J4vCqngPdpd1XWIa2nmlmLCLEB9IjmySsGPZXXgf17atM8";
	final static String SECRECT_KEY = "8oaYDPmh4q5F2H62QLpZRFKXzhFSt2YtWTKxYDlJV4cfv8I5r7fN7HtbWCQ1nfno";
	final static String[] MOEDAS = { "ETHUSDT" };

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Rodando...");

		
		
		for (String moeda : MOEDAS) {
			RoboBasico robo = new RoboBasico(API_KEY, SECRECT_KEY, moeda);
			robo.start();
		}


	}

}
