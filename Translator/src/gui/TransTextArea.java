package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// 文本域
public class TransTextArea {

    private TransTextAreaMenu textarea;

    public TransTextArea(String s, int i) {

        if (i == 1)
            textarea = new TransTextAreaMenu(s,20, 20, i);
        else {
            textarea = new TransTextAreaMenu(s, 20, 20, i);
        }

        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        //给文本域添加事件
        textarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//当点击文本域时清除文字
                textarea.setForeground(Color.BLACK);
                if(textarea.getText().equals("请输入要翻译的内容..."))
                textarea.setText("");
            }
        });




    }



    public JTextArea getTextArea() {
        return textarea;
    }

    public void setText(String s) {
        textarea.setText(s);
    }

    public void setEdid(boolean flag) {
        textarea.setEditable(flag);
    }

    public String toString() {
        return textarea.getText().toString();
    }
}
