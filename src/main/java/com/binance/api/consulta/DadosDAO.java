package com.binance.api.consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.binance.api.config.ConfigConnection;
import com.binance.api.model.Order;

public class DadosDAO {
	static Connection conexao = new ConfigConnection().conectar();

	public static Order findById(Long idOrder) {

		PreparedStatement sqlParametro = null;
		String sql;
		Order order = new Order();
		try {
			sql = "select * from `order` where id_order = ?";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setLong(1, idOrder);

			ResultSet resultado = sqlParametro.executeQuery();
			if (resultado.next()) {
				order.setIdOrder(resultado.getLong("id_order"));
				order.setTipo(resultado.getLong("tipo"));
				order.setValor(resultado.getBigDecimal("valor"));
				order.setQuantidade(resultado.getBigDecimal("quantidade"));
				order.setMoeda(resultado.getString("moeda"));
				order.setAtendida(resultado.getLong("atendida"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return order;
	}

	public static List<Order> listarOrdens() {
		PreparedStatement sqlParametro = null;
		String sql;
		List<Order> ordens = new ArrayList<>();

		try {
			sql = "select * from `order`;";
			sqlParametro = conexao.prepareStatement(sql);

			ResultSet resultado = sqlParametro.executeQuery();
			while (resultado.next()) {
				Order order = new Order();
				order.setIdOrder(resultado.getLong("id_order"));
				order.setTipo(resultado.getLong("tipo"));
				order.setValor(resultado.getBigDecimal("valor"));
				order.setQuantidade(resultado.getBigDecimal("quantidade"));
				order.setMoeda(resultado.getString("moeda"));
				order.setAtendida(resultado.getLong("atendida"));
				ordens.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return ordens;
	}

	public static List<Order> listarOrdensNaoAtendidas() {
		PreparedStatement sqlParametro = null;
		String sql;
		List<Order> ordens = new ArrayList<>();

		try {
			sql = "select * from `order` where atendida = 1;";
			sqlParametro = conexao.prepareStatement(sql);

			ResultSet resultado = sqlParametro.executeQuery();
			while (resultado.next()) {
				Order order = new Order();
				order.setIdOrder(resultado.getLong("id_order"));
				order.setTipo(resultado.getLong("tipo"));
				order.setValor(resultado.getBigDecimal("valor"));
				order.setQuantidade(resultado.getBigDecimal("quantidade"));
				order.setMoeda(resultado.getString("moeda"));
				order.setAtendida(resultado.getLong("atendida"));
				ordens.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return ordens;
	}

	public static void guardaCompraVenda(Order objeto) {

		PreparedStatement sqlParametro = null;
		String sql;
		try {

			sql = "insert into `order`(tipo, valor, quantidade, moeda) value(?, ?, ?, ?);";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setLong(1, objeto.getTipo().getValue());
			sqlParametro.setBigDecimal(2, objeto.getValor());
			sqlParametro.setBigDecimal(3, objeto.getQuantidade());

			sqlParametro.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void atendePedido(Long idOrder) {

		PreparedStatement sqlParametro = null;
		String sql;
		try {

			sql = "update `order` set (atendida) value(1) where id_order = ?;";
			sqlParametro = conexao.prepareStatement(sql);
			sqlParametro.setLong(1, idOrder);

			sqlParametro.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sqlParametro.close();
				conexao.close();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
