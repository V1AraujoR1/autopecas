package data;

import java.sql.*;

public class RelatoriosDAO extends Conexao {

    public RelatoriosDAO() throws Exception {

    }

    public ResultSet preencherResultSet() throws Exception {
        String query = "SELECT dataHora, valorTotal FROM Pedido ORDER BY dataHora;";
        
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        return rs;
    }
}
