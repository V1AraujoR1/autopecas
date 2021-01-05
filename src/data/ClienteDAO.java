package data;

import model.*;
import java.sql.*;
import java.util.List;

public class ClienteDAO extends Conexao {

    public ClienteDAO() throws Exception {

    }

    public boolean inserirCliente(Cliente cliente) throws Exception {
        int codigoCliente = 0;
        String query1 = "INSERT INTO Cliente (nome, logradouro, numero, bairro, cidade, estado, CEP) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst1 = getConexao().prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
        
        pst1.setString(1, cliente.getNome());
        pst1.setString(2, cliente.getLogradouro());
        pst1.setInt(3, cliente.getNumero());
        pst1.setString(4, cliente.getBairro());
        pst1.setString(5, cliente.getCidade());
        pst1.setString(6, cliente.getEstado());
        pst1.setString(7, cliente.getCEP());
        pst1.executeUpdate();
        
        ResultSet rs = pst1.getGeneratedKeys();
        if (rs.next()) {
            //Gerado o código de cliente na tabela genérica "Cliente" com sucesso.
            codigoCliente = rs.getInt(1);

            if (cliente instanceof ClienteFisico) {
                ClienteFisico fisico = (ClienteFisico) cliente;
                String query2 = "INSERT INTO ClienteFisico (codigoCliente, CPF, RG) VALUES (?, ?, ?)";
                PreparedStatement pst2 = getConexao().prepareStatement(query2);

                pst2.setInt(1, codigoCliente);
                pst2.setString(2, fisico.getCPF());
                pst2.setString(3, fisico.getRG());

                if (pst2.executeUpdate() > 0) {
                    return inserirTelefones(fisico.getTelefones(), codigoCliente);
                }
            }

            if (cliente instanceof ClienteJuridico) {
                ClienteJuridico juridico = (ClienteJuridico) cliente;
                String query2 = "INSERT INTO ClienteJuridico (codigoCliente, CNPJ, IE, razaoSocial) VALUES (?, ?, ?, ?)";
                PreparedStatement pst2 = getConexao().prepareStatement(query2);

                pst2.setInt(1, codigoCliente);
                pst2.setString(2, juridico.getCNPJ());
                pst2.setString(3, juridico.getInscricaoEstadual());
                pst2.setString(4, juridico.getRazaoSocial());

                if (pst2.executeUpdate() > 0) {
                    return inserirTelefones(juridico.getTelefones(), codigoCliente);
                }
            }
        }
        return false;
    }

    private boolean inserirTelefones(List<TelefoneCliente> telefones, int codigoCliente) throws Exception {
        for (int i = 0; i < telefones.size(); i++) {
            String query = "INSERT INTO TelefoneCliente (codigoCliente, telefone, tipo) VALUES (?, ?, ?)";
            PreparedStatement pst = getConexao().prepareStatement(query);
            pst.setInt(1, codigoCliente);
            pst.setString(2, telefones.get(i).getTelefone());
            pst.setString(3, telefones.get(i).getTipo());
            if (pst.executeUpdate() == 0) //Não deu certo.
                return false;
        }

        return true;
    }
    
    //Útil para quando se deseja pesquisar informações de cliente onde não se sabe qual o tipo. Por exemplo, na tela de Vendas, onde só precisa do nome e dos telefones do cliente aleatório.
    public Cliente pesquisarCliente(int codigoCliente) throws Exception {
        Cliente c = new Cliente();
        String query = "SELECT * FROM Cliente WHERE codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            c.setCodigoCliente(rs.getInt("codigoCliente"));
            c.setNome(rs.getString("nome"));
            c.setLogradouro(rs.getString("logradouro"));
            c.setNumero(rs.getInt("numero"));
            c.setBairro(rs.getString("bairro"));
            c.setCidade(rs.getString("cidade"));
            c.setEstado(rs.getString("estado"));
            c.setCEP(rs.getString("CEP"));

            String query2 = "SELECT * FROM TelefoneCliente WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst2 = getConexao().prepareStatement(query2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                TelefoneCliente objTelefone = new TelefoneCliente();
                objTelefone.setTelefone(rs2.getString("telefone"));
                objTelefone.setTipo(rs2.getString("tipo"));
                c.adicionarTelefone(objTelefone);
            }
        } else {
            throw new Exception("código de cliente inexistente");
        }
        
        return c;
    }

    public ClienteFisico pesquisarClienteFisico(int codigoCliente) throws Exception {
        ClienteFisico f = new ClienteFisico();
        String query = "SELECT * FROM Cliente, ClienteFisico WHERE Cliente.codigoCliente = " + codigoCliente + " AND ClienteFisico.codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            f.setCodigoCliente(rs.getInt("codigoCliente"));
            f.setNome(rs.getString("nome"));
            f.setLogradouro(rs.getString("logradouro"));
            f.setNumero(rs.getInt("numero"));
            f.setBairro(rs.getString("bairro"));
            f.setCidade(rs.getString("cidade"));
            f.setEstado(rs.getString("estado"));
            f.setCEP(rs.getString("CEP"));
            f.setCPF(rs.getString("CPF"));
            f.setRG(rs.getString("RG"));

            String query2 = "SELECT * FROM TelefoneCliente WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst2 = getConexao().prepareStatement(query2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                TelefoneCliente objTelefone = new TelefoneCliente();
                objTelefone.setTelefone(rs2.getString("telefone"));
                objTelefone.setTipo(rs2.getString("tipo"));
                f.adicionarTelefone(objTelefone);
            }
        } else {
            throw new Exception("código de cliente de outro tipo ou inexistente");
        }

        return f;
    }
    
    public ClienteJuridico pesquisarClienteJuridico(int codigoCliente) throws Exception {
        ClienteJuridico j = new ClienteJuridico();
        String query = "SELECT * FROM Cliente, ClienteJuridico WHERE Cliente.codigoCliente = " + codigoCliente + " AND ClienteJuridico.codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst = getConexao().prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            j.setCodigoCliente(rs.getInt("codigoCliente"));
            j.setNome(rs.getString("nome"));
            j.setLogradouro(rs.getString("logradouro"));
            j.setNumero(rs.getInt("numero"));
            j.setBairro(rs.getString("bairro"));
            j.setCidade(rs.getString("cidade"));
            j.setEstado(rs.getString("estado"));
            j.setCEP(rs.getString("CEP"));
            j.setCNPJ(rs.getString("CNPJ"));
            j.setInscricaoEstadual(rs.getString("IE"));
            j.setRazaoSocial(rs.getString("razaoSocial"));

            String query2 = "SELECT * FROM TelefoneCliente WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst2 = getConexao().prepareStatement(query2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                TelefoneCliente objTelefone = new TelefoneCliente();
                objTelefone.setTelefone(rs2.getString("telefone"));
                objTelefone.setTipo(rs2.getString("tipo"));
                j.adicionarTelefone(objTelefone);
            }
        } else {
            throw new Exception("código de cliente de outro tipo ou inexistente");
        }

        return j;
    }

    public boolean editarCliente(Cliente cliente, int codigoCliente) throws Exception {
        String query1 = "UPDATE Cliente SET nome = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, CEP = ? WHERE codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst1 = getConexao().prepareStatement(query1);

        pst1.setString(1, cliente.getNome());
        pst1.setString(2, cliente.getLogradouro());
        pst1.setInt(3, cliente.getNumero());
        pst1.setString(4, cliente.getBairro());
        pst1.setString(5, cliente.getCidade());
        pst1.setString(6, cliente.getEstado());
        pst1.setString(7, cliente.getCEP());
        pst1.executeUpdate();

        if (cliente instanceof ClienteFisico) {
            ClienteFisico fisico = (ClienteFisico) cliente;
            String query2 = "UPDATE ClienteFisico SET CPF = ?, RG = ? WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst2 = getConexao().prepareStatement(query2);

            pst2.setString(1, fisico.getCPF());
            pst2.setString(2, fisico.getRG());
            pst2.executeUpdate();
            
            //Deletando e inserindo os telefones novamente.
            String query3 = "DELETE FROM TelefoneCliente WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst3 = getConexao().prepareStatement(query3);
            pst3.executeUpdate();
            
            return inserirTelefones(fisico.getTelefones(), codigoCliente);
        }

        if (cliente instanceof ClienteJuridico) {
            ClienteJuridico juridico = (ClienteJuridico) cliente;
            String query2 = "UPDATE ClienteJuridico SET CNPJ = ?, InscricaoEstadual = ?, razaoSocial = ? WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst2 = getConexao().prepareStatement(query2);

            pst2.setString(1, juridico.getCNPJ());
            pst2.setString(2, juridico.getInscricaoEstadual());
            pst2.setString(3, juridico.getRazaoSocial());

            //Deletando e inserindo os telefones novamente.
            String query3 = "DELETE FROM TelefoneCliente WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst3 = getConexao().prepareStatement(query3);
            pst3.executeUpdate();
            
            return inserirTelefones(juridico.getTelefones(), codigoCliente);
        }

        return false;
    }

    /*
     * Observa-se que deletar registros não me parece certo. Até porque, podem ter havido compras, serviços, etc. vinculados à esse cliente. 
     * Funções estas que podem precisar de informação para busca futura. Talvez o ideal seria haver opção de "desabilitar cliente", retirando-o 
     * dos clientes pesquisáveis e colocando em uma lista de clientes desligados. Assim, preserva as informações mas inibe futuras edições e compras, até que se habilite novamente.
     */
    public boolean deletarCliente(int codigoCliente, int tipoCliente) throws Exception {
        if (tipoCliente == 1) {  //Cliente físico
            String query1 = "DELETE FROM ClienteFisico WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst1 = getConexao().prepareStatement(query1);
            pst1.execute();
            pst1.close();
        } else {    //Cliente jurídico
            String query1 = "DELETE FROM ClienteJuridico WHERE codigoCliente = " + codigoCliente + ";";
            PreparedStatement pst1 = getConexao().prepareStatement(query1);
            pst1.execute();
            pst1.close();
        }

        String query2 = "DELETE FROM TelefoneCliente WHERE codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst2 = getConexao().prepareStatement(query2);
        pst2.execute();
        pst2.close();
        
        String query3 = "DELETE FROM Cliente WHERE codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst3 = getConexao().prepareStatement(query3);
        pst3.execute();
        pst3.close();
        
        String query4 = "SELECT * FROM Cliente WHERE codigoCliente = " + codigoCliente + ";";
        PreparedStatement pst4 = getConexao().prepareStatement(query4);
        return pst4.execute();
    }
    
    
}
