package data;

import javax.swing.JOptionPane;

public class TesteConexao {

    public static void main(String[] args) {
        try {
            Conexao c = new Conexao();
            JOptionPane.showMessageDialog(null, "Conexão bem sucedida.", "Sucesso", 1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexão mal sucedida.", "Erro", 0);
        }
    }
}
