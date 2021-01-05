package data;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO extends Conexao {
    
    public PedidoDAO() throws Exception {

    }
    
    public boolean efetuarPedido(Pedido pedido) throws Exception {
        int codigoPedido = 0;
        String query = "INSERT INTO Pedido (codigoCliente, formaPagamento, valorTotal, dataHora) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = getConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        pst.setInt(1, pedido.getCliente().getCodigoCliente());
        pst.setString(2, pedido.getFormaPagamento());
        pst.setFloat(3, pedido.getValorTotal());
        pst.setTimestamp(4, pedido.getDataHora());
        pst.executeUpdate();
        
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            //Gerado o código do pedido na tabela "Pedido" com sucesso.
            codigoPedido = rs.getInt(1);
            return inserirItensPedido(pedido.getItensPedido(), codigoPedido);
        }
        return false;
    }
    
    private boolean inserirItensPedido(List<ItemPedido> itensPedido, int codigoPedido) throws Exception {
        for (int i = 0; i < itensPedido.size(); i++) {
            String query = "INSERT INTO ItemPedido (codigoPedido, codigoCliente, codigoProduto, quantidadePedida, valorUnitario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = getConexao().prepareStatement(query);
            
            pst.setInt(1, codigoPedido);
            pst.setInt(2, itensPedido.get(i).getCliente().getCodigoCliente());
            pst.setInt(3, itensPedido.get(i).getProduto().getCodigoProduto());
            pst.setInt(4, itensPedido.get(i).getQuantidadePedida());
            pst.setFloat(5, itensPedido.get(i).getValorUnitario());
            if (pst.executeUpdate() == 0) //Não deu certo.
                return false;
        }

        return true;
    }
    
    public ResultSet preencherResultSet() throws Exception {
        String query = "SELECT codigoPedido AS \"Número do Pedido\", formaPagamento AS \"Forma de Pagamento\", valorTotal AS \"Valor Total\", dataHora AS \"Data/Hora\", nome AS \"Nome do Cliente\", Pedido.codigoCliente AS \"Código do Cliente\" FROM Pedido, Cliente WHERE Pedido.codigoCliente = Cliente.codigoCliente;";
        
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        return rs;
    }
    
    public ResultSet preencherResultSet(ArrayList<String> filtros) throws Exception {
        String query = "SELECT codigoPedido AS \"Número do Pedido\", formaPagamento AS \"Forma de Pagamento\", valorTotal AS \"Valor Total\", dataHora AS \"Data/Hora\", nome AS \"Nome do Cliente\", Pedido.codigoCliente AS \"Código do Cliente\" FROM Pedido, Cliente WHERE Pedido.codigoCliente = Cliente.codigoCliente ";
        
        //Apenas o código do cliente
        if (filtros.size() == 1) {
            query += "AND Pedido.codigoCliente = " + filtros.get(0) + " ORDER BY dataHora;";
        } else {
            //Apenas as datas iniciais e finais
            if (filtros.size() == 2) {
                query += "AND dataHora >= '" + filtros.get(0) + "  00:00:00' AND dataHora <= '" + filtros.get(1) + " 23:59:59' ORDER BY dataHora;";
            } else {
                //Tudo
                if (filtros.size() == 3) {
                    query += "AND dataHora >= '" + filtros.get(0) + "  00:00:00' AND dataHora <= '" + filtros.get(1) + " 23:59:59' AND Pedido.codigoCliente = " + filtros.get(2) + " ORDER BY dataHora;";
                }
            }
        }

        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        return rs;
    }
}