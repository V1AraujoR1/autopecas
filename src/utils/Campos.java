package utils;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class Campos {
    
    static MaskFormatter objMascara;
    static JButton jb = null;
    static JLabel jl = null;
    static JFormattedTextField jft = null;
    static JTextField jtf = null;
    static JComboBox jcb = null;
    static JPasswordField jpf = null;
    static JTable jt = null;
    static Component[] comp;

    public void componentsSetVisible(JPanel p, boolean b) {
        comp = p.getComponents();

        for (Component c : comp) {
            if (c instanceof JLabel) {
                jl = (JLabel) c;
                jl.setVisible(b);
            }

            if (c instanceof JFormattedTextField) {
                jft = (JFormattedTextField) c;
                jft.setVisible(b);
            }

            if (c instanceof JTextField) {
                jtf = (JTextField) c;
                jtf.setVisible(b);
            }

            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setVisible(b);
            }

            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setVisible(b);
            }

            if (c instanceof JTable) {
                jt = (JTable) c;
                jt.setVisible(b);
            }
        }
    }

    public void componentsSetEditable(JPanel p, boolean b) {
        comp = p.getComponents();

        for (Component c : comp) {
            if (c instanceof JButton) {
                jb = (JButton) c;
                jb.setEnabled(b);
            }

            if (c instanceof JLabel) {
                jl = (JLabel) c;
                jl.setEnabled(b);
            }

            if (c instanceof JFormattedTextField) {
                jft = (JFormattedTextField) c;
                jft.setEditable(b);
            }

            if (c instanceof JTextField) {
                jtf = (JTextField) c;
                jtf.setEditable(b);
            }

            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setEnabled(b);
            }

            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setEditable(b);
            }

            if (c instanceof JTable) {
                jt = (JTable) c;
                jt.setEnabled(b);
            }
        }
    }

    public void componentsClearText(JPanel p) {
        comp = p.getComponents();

        for (Component c : comp) {
            if (c instanceof JFormattedTextField) {
                jft = (JFormattedTextField) c;
                jft.setText("");
            }

            if (c instanceof JTextField) {
                jtf = (JTextField) c;
                jtf.setText("");
            }

            if (c instanceof JComboBox) {
                jcb = (JComboBox) c;
                jcb.setSelectedIndex(0);
            }

            if (c instanceof JPasswordField) {
                jpf = (JPasswordField) c;
                jpf.setText("");
            }
        }
    }

    public static void mascararCep(JFormattedTextField jft) throws Exception {
        objMascara = new MaskFormatter("#####-###");
        objMascara.setValidCharacters("1234567890"); // caracteres válidos
        objMascara.setPlaceholderCharacter(' '); // preenchimento            
        objMascara.install(jft);
    }

}
