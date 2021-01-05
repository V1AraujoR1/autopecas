package utils;

import java.awt.Component;
import javax.swing.JPanel;

public class Paineis {
    public void componentsSetVisible(JPanel p, boolean b) {
        Component[] comp = p.getComponents();
        JPanel jp = null;

        for (Component c : comp) {
            if (c instanceof JPanel) {
                jp = (JPanel) c;
                jp.setVisible(b);
            }
        }
    }
    
    public void componentSetLineBorderColor(JPanel p) {
        p.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(245, 124, 0), 2, false));
    }

    public void componentSetEmptyBorder(JPanel p) {
        p.setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
    }
}
