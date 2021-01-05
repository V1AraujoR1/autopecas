package data;

import java.sql.*;

public class ItemPedidoDAO extends Conexao {

    public ItemPedidoDAO() throws Exception {

    }

    public ResultSet preencherResultSet(int codigoPedido) throws Exception {
        String query = "SELECT ItemPedido.codigoProduto AS \"Código\", nome AS \"Nome\", referencia AS \"Referência\", marca AS \"Marca\", quantidadePedida AS \"Quantidade\", valorUnitario AS \"Valor Unitário\" FROM ItemPedido, Pedido, Produto WHERE ItemPedido.codigoPedido = Pedido.codigoPedido AND ItemPedido.codigoProduto = Produto.codigoProduto AND Pedido.codigoPedido = " + codigoPedido + " ORDER BY nome;";

        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        return rs;
    }
}
