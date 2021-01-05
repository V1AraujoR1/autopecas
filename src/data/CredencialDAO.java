package data;

import model.Credencial;
import java.sql.*;

public class CredencialDAO extends Conexao {

    public CredencialDAO() throws Exception {

    }

    public boolean verificarCredenciais(Credencial credencial) throws Exception {
        String query = "SELECT * FROM Credencial WHERE usuario = ? AND senha = ?";
        PreparedStatement pst = getConexao().prepareStatement(query);

        //Preenchendo os placeholders, em seus respectivos indíces
        pst.setString(1, credencial.getUsuario());
        pst.setString(2, credencial.getSenha());
        ResultSet rs = pst.executeQuery();

        //Retornando true ou false, caso as credenciais fornecidas existam no Banco de Dados
        return rs.next();
    }
}
