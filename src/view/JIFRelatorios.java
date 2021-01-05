package view;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import data.*;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import utils.*;

public class JIFRelatorios extends javax.swing.JInternalFrame {

    Campos campos;
    Paineis paineis;
    RelatoriosDAO DAO;
    Conexao con;

    public JIFRelatorios() {
        initComponents();

        try {
            campos = new Campos();
            paineis = new Paineis();
            DAO = new RelatoriosDAO();
            con = new Conexao();
        } catch (Exception e) {
            System.out.println("Erro ao inicializar instância de " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpNorte = new javax.swing.JPanel();
        jpNada = new javax.swing.JPanel();
        ImageIcon iconBotaoFechar = new ImageIcon(getClass().getResource("/images/Comum/BotaoFechar.png"));
        Image botaoFechar = iconBotaoFechar.getImage();
        jpFechar = new javax.swing.JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(botaoFechar, 0, 0, getWidth(), getHeight(), this);
            }
        };
        jpCentro = new javax.swing.JPanel();
        ImageIcon iconBotaoCancelar = new ImageIcon(getClass().getResource("/images/JIFRelatorios/Clientes.png"));
        Image botaoCancelar = iconBotaoCancelar.getImage();
        jpBotaoCliente = new javax.swing.JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(botaoCancelar, 0, 0, getWidth(), getHeight(), this);
            }
        };
        jpSul = new javax.swing.JPanel();

        setBorder(null);
        setFrameIcon(null);

        jpNorte.setBackground(new java.awt.Color(255, 109, 0));
        jpNorte.setPreferredSize(new java.awt.Dimension(1102, 38));
        jpNorte.setLayout(new java.awt.GridBagLayout());

        jpNada.setBackground(new java.awt.Color(255, 109, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jpNorte.add(jpNada, gridBagConstraints);

        jpFechar.setBackground(new java.awt.Color(255, 0, 0));
        jpFechar.setPreferredSize(new java.awt.Dimension(38, 38));
        jpFechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpFecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpFecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpFecharMouseExited(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jpNorte.add(jpFechar, gridBagConstraints);

        getContentPane().add(jpNorte, java.awt.BorderLayout.NORTH);

        jpCentro.setBackground(new java.awt.Color(255, 209, 0));
        jpCentro.setLayout(new java.awt.GridBagLayout());

        jpBotaoCliente.setBackground(new java.awt.Color(41, 98, 255));
        jpBotaoCliente.setMaximumSize(new java.awt.Dimension(60, 60));
        jpBotaoCliente.setMinimumSize(new java.awt.Dimension(50, 50));
        jpBotaoCliente.setPreferredSize(new java.awt.Dimension(60, 60));
        jpBotaoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpBotaoClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpBotaoClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpBotaoClienteMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpBotaoClienteLayout = new javax.swing.GroupLayout(jpBotaoCliente);
        jpBotaoCliente.setLayout(jpBotaoClienteLayout);
        jpBotaoClienteLayout.setHorizontalGroup(
            jpBotaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        jpBotaoClienteLayout.setVerticalGroup(
            jpBotaoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jpCentro.add(jpBotaoCliente, new java.awt.GridBagConstraints());

        getContentPane().add(jpCentro, java.awt.BorderLayout.CENTER);

        jpSul.setBackground(new java.awt.Color(255, 109, 0));
        jpSul.setMinimumSize(new java.awt.Dimension(1152, 70));
        jpSul.setPreferredSize(new java.awt.Dimension(1152, 70));
        getContentPane().add(jpSul, java.awt.BorderLayout.SOUTH);

        setBounds(0, 0, 1152, 720);
    }// </editor-fold>//GEN-END:initComponents

    private void jpFecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFecharMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente fechar?", "Relatórios", 0) == 0) {
            dispose();
        }
    }//GEN-LAST:event_jpFecharMouseClicked

    private void jpFecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFecharMouseEntered
        jpFechar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(245, 124, 0), 2, false));
    }//GEN-LAST:event_jpFecharMouseEntered

    private void jpFecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpFecharMouseExited
        jpFechar.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
    }//GEN-LAST:event_jpFecharMouseExited

    private void jpBotaoClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBotaoClienteMouseClicked
        try {
            InputStream in = new FileInputStream(new File("C:\\Users\\Vinicius\\Documents\\Projeto Auto Pecas\\Linguagem de Programacao\\AutoPecas\\src\\reports\\clientes.jrxml"));
            JasperDesign jd = JRXmlLoader.load(in);
            String SQL = "SELECT * FROM Cliente ORDER BY nome";
            JRDesignQuery query = new JRDesignQuery();
            query.setText(SQL);
            jd.setQuery(query);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            JasperPrint jp = JasperFillManager.fillReport(jr, para, con.getConexao());
            JasperViewer.viewReport(jp, false);
            OutputStream os = new FileOutputStream(new File("C:\\Users\\Vinicius\\Documents\\Projeto Auto Pecas\\Linguagem de Programacao\\AutoPecas\\src\\reports"));
            JasperExportManager.exportReportToPdfStream(jp, os);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Relatórios - Botão Gerar", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jpBotaoClienteMouseClicked

    private void jpBotaoClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBotaoClienteMouseEntered
        paineis.componentSetLineBorderColor(jpBotaoCliente);
    }//GEN-LAST:event_jpBotaoClienteMouseEntered

    private void jpBotaoClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpBotaoClienteMouseExited
        paineis.componentSetEmptyBorder(jpBotaoCliente);
    }//GEN-LAST:event_jpBotaoClienteMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpBotaoCliente;
    private javax.swing.JPanel jpCentro;
    private javax.swing.JPanel jpFechar;
    private javax.swing.JPanel jpNada;
    private javax.swing.JPanel jpNorte;
    private javax.swing.JPanel jpSul;
    // End of variables declaration//GEN-END:variables
}
