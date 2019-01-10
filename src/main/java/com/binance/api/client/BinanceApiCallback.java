package com.binance.api.client;

/**
 * BinanceApiCallback é uma interface funcional usada junto com o BinanceApiAsyncClient para fornecer um cliente REST sem bloqueio.
 *
 * @param <T> o tipo de retorno do retorno de chamada
 */
@FunctionalInterface
public interface BinanceApiCallback<T> {

    /**
     * Chamado sempre que uma resposta retorna da API do Binance.
     *
     * @param response o objeto de resposta esperado
     */
    void onResponse(T response);

    /**
     * Chamado sempre que ocorrer um erro.
     *
     * @param causa a causa da falha
     */
    default void onFailure(Throwable cause) {}
}