package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TransJComboBox {
    JComboBox jComboBox;
    String JComboBoxName = "汉语";
    public TransJComboBox(String s) {
        jComboBox = new JComboBox();
        jComboBox.addItem(s);
//        if (s.compareTo("auto") == 0)
//            jComboBox.addItem("英语");
//        else
//            //jComboBox.addItem("汉语");
        jComboBox.addItem("英语");
        jComboBox.addItem("日语");
        jComboBox.addItem("法语");
        jComboBox.addItem("西班牙语");


        ActionListener actionListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof JComboBox){
                    @SuppressWarnings({ "unchecked", "rawtypes" })
                    JComboBox<String> comboBox = (JComboBox)e.getSource();
                    String fontName = comboBox.getSelectedItem().toString();
                    JComboBoxName = fontName;
                }
            }
        };
        jComboBox.addActionListener(actionListener);

    }
    public void addItem(String s) {
        jComboBox.addItem(s);
    }

    public JComboBox getBox() {
        return jComboBox;
    }

    public String getJComboBoxName() {
        return JComboBoxName;
    }
}