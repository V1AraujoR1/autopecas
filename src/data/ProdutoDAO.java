package data;

import model.*;
import java.sql.*;
import java.util.*;

public class ProdutoDAO extends Conexao {

    public ProdutoDAO() throws Exception {

    }

    public boolean inserirProduto(Produto produto) throws Exception {
        String query = "INSERT INTO Produto (nome, referencia, marca, pesquisa, descricao, multiplo, preco, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = getConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, produto.getNome());
        pst.setString(2, produto.getReferencia());
        pst.setString(3, produto.getMarca());
        pst.setString(4, produto.getPesquisa());
        pst.setString(5, produto.getDescricao());
        pst.setInt(6, produto.getMultiplo());
        pst.setFloat(7, produto.getPreco());
        pst.setInt(8, produto.getQuantidade());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();

        return rs.next();
    }

    public Produto pesquisarProduto(int codigoProduto) throws Exception {
        Produto p = new Produto();
        String query = "SELECT * FROM Produto WHERE codigoProduto = " + codigoProduto + ";";
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            p.setCodigoProduto(rs.getInt("codigoProduto"));
            p.setNome(rs.getString("nome"));
            p.setReferencia(rs.getString("referencia"));
            p.setMarca(rs.getString("marca"));
            p.setPesquisa(rs.getString("pesquisa"));
            p.setDescricao(rs.getString("descricao"));
            p.setMultiplo(rs.getInt("multiplo"));
            p.setPreco(rs.getFloat("preco"));
            p.setQuantidade(rs.getInt("quantidade"));
        } else {
            throw new Exception("código de produto inexistente");
        }

        return p;
    }

    public boolean editarProduto(Produto produto, int codigoProduto) throws Exception {
        String query = "UPDATE Produto SET nome = ?, referencia = ?, marca = ?, pesquisa = ?, descricao = ?, multiplo = ?, preco = ?, quantidade = ? WHERE codigoProduto = " + codigoProduto + ";";
        PreparedStatement pst = getConexao().prepareStatement(query);
        pst.setString(1, produto.getNome());
        pst.setString(2, produto.getReferencia());
        pst.setString(3, produto.getMarca());
        pst.setString(4, produto.getPesquisa());
        pst.setString(5, produto.getDescricao());
        pst.setInt(6, produto.getMultiplo());
        pst.setFloat(7, produto.getPreco());
        pst.setInt(8, produto.getQuantidade());
        pst.executeUpdate();

        return true;
    }

    public boolean deletarProduto(int codigoProduto) throws Exception {
        String query = "DELETE FROM Produto WHERE codigoProduto = " + codigoProduto + ";";
        PreparedStatement pst = getConexao().prepareStatement(query);
        pst.execute();
        pst.close();

        return true;
    }
    
    public ResultSet preencherResultSet() throws Exception {
        String query = "SELECT pesquisa AS \"Pesquisa\", nome AS \"Nome\", referencia AS \"Referência\", marca AS \"Marca\", quantidade AS \"Quantidade\", preco AS \"Preço\", multiplo AS \"Múltiplo\", codigoProduto AS \"Código\", descricao AS \"Descrição\" FROM Produto ORDER BY pesquisa;";
        
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        return rs;
    }
    
    public ResultSet preencherResultSet(ArrayList<String> filtros) throws Exception {
        String query = "SELECT pesquisa AS \"Pesquisa\", nome AS \"Nome\", referencia AS \"Referência\", marca AS \"Marca\", quantidade AS \"Quantidade\", preco AS \"Preço\", multiplo AS \"Múltiplo\", codigoProduto AS \"Código\", descricao AS \"Descrição\" FROM Produto ";
        
        //Apenas o filtro principal, porém sendo código de produto.
        if (!filtros.isEmpty() && "codigoProduto".equalsIgnoreCase(filtros.get(0))) {
            query += "WHERE codigoProduto = " + filtros.get(2) + " ORDER BY pesquisa;";
        } else {
            //Criando os coringas para consulta no Banco de Dados conforme o filtro principal do usuário pede.
            String coringaPrincipal1 = new String();
            String coringaPrincipal2 = new String();
            
            if("Inicia".equals(filtros.get(1))) {
                coringaPrincipal1 = "";
                coringaPrincipal2 = "%";
            }
            if("Termina".equals(filtros.get(1))) {
                coringaPrincipal1 = "%";
                coringaPrincipal2 = "";
            }
            if("Contém".equals(filtros.get(1))) {
                coringaPrincipal1 = "%";
                coringaPrincipal2 = "%";
            }
            if("Igual".equals(filtros.get(1))) {
                coringaPrincipal1 = "";
                coringaPrincipal2 = "";
            }
            
            //Apenas o filtro principal.
            if (!filtros.isEmpty() && filtros.size() <= 3) {
                query += "WHERE " + filtros.get(0) + " ILIKE '" + coringaPrincipal1 + filtros.get(2) + coringaPrincipal2 + "' ORDER BY pesquisa;";
            } else {
                //Âmbos os filtros principal e adicional.
                String coringaAdicional1 = new String();
                String coringaAdicional2 = new String();

                if ("Inicia".equals(filtros.get(4))) {
                    coringaAdicional1 = "";
                    coringaAdicional2 = "%";
                }
                if ("Termina".equals(filtros.get(4))) {
                    coringaAdicional1 = "%";
                    coringaAdicional2 = "";
                }
                if ("Contém".equals(filtros.get(4))) {
                    coringaAdicional1 = "%";
                    coringaAdicional2 = "%";
                }
                if ("Igual".equals(filtros.get(4))) {
                    coringaAdicional1 = "";
                    coringaAdicional2 = "";
                }

                query += "WHERE " + filtros.get(0) + " ILIKE '" + coringaPrincipal1 + filtros.get(2) + coringaPrincipal2 + "' AND " + filtros.get(3) + " ILIKE '" + coringaAdicional1 + filtros.get(5) + coringaAdicional2 + "' ORDER BY pesquisa;";
            }
        }
        
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        return rs;
    }

    public boolean atualizarEstoque(List<ItemPedido> itensPedido) throws Exception {
        for (int i = 0; i < itensPedido.size(); i++) {
            int q = 0;
            
            String search = "SELECT quantidade FROM Produto WHERE codigoProduto = ?";
            PreparedStatement pst1 = getConexao().prepareStatement(search);
            pst1.setInt(1, itensPedido.get(i).getProduto().getCodigoProduto());
            ResultSet rs = pst1.executeQuery();
            rs.next();
            q = rs.getInt(1);
            
            String query = "UPDATE Produto SET quantidade = ? WHERE codigoProduto = ?;";
            PreparedStatement pst2 = getConexao().prepareStatement(query);
            q -= itensPedido.get(i).getQuantidadePedida();
            pst2.setInt(1, q);
            pst2.setInt(2, itensPedido.get(i).getProduto().getCodigoProduto());
            
            if (pst2.executeUpdate() == 0) //Não deu certo.
            {
                return false;
            }
        }

        return true;
    }
}
