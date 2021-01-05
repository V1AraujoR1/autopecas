package data;

import java.sql.*;

public class Conexao {
    private Connection conexao;
    
    public Connection getConexao() {
        return conexao;
    }
    
    public Conexao() throws ClassNotFoundException, SQLException {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/autopecas";
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, "postgres", "postgres");
    }
}