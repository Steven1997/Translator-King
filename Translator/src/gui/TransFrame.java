package gui;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import translate.TransService;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class TransFrame extends JFrame{
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20180508000155375";
    private static final String SECURITY_KEY = "kXhYz4Ud8yRgGDubeQwt";
    private static TransTextArea textarea1;
    private TransTextArea textarea2;
    private TransJComboBox comboBox2;
    private TransMenu menuBar;
    private TransService api;
    private TransJComboBox comboBox1;
    private static Map<String,String> mp;

    static{
        mp = new HashMap<String,String>();
        mp.put("汉语","zh");
        mp.put("英语","en");
        mp.put("法语","fra");
        mp.put("日语","jp");
        mp.put("西班牙语","spa");
    }

    public TransFrame(int JPWidth, int JPHeight) {
        JPanel jp1, jp2;
        JSplitPane jsp;
        api = new TransService(APP_ID, SECURITY_KEY);


        textarea1 = new TransTextArea("请输入要翻译的内容...", 1);
        textarea1.getTextArea().setForeground(Color.gray);
        textarea2 = new TransTextArea("", 2);
        textarea2.setEdid(false);

        // 滚轮
        JScrollPane jScrollPane1 = new JScrollPane(textarea1.getTextArea());
        JScrollPane jScrollPane2 = new JScrollPane(textarea2.getTextArea());
        jScrollPane1.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jp1 = new JPanel();
        jp1.add(jScrollPane1);
        jp1.setFocusable(true);
        jp2 = new JPanel();
        jp2.add(jScrollPane2);
        jp2.setBorder(new EmptyBorder(5,5,5,5));
        comboBox2 = new TransJComboBox("汉语");
        jp2.add(comboBox2.getBox());

        // 翻译按键
        Font f = new Font("宋体",Font.BOLD,10);
        final JButton button = new JButton("翻译");
        button.setFont(f);
        button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        button.setBounds(42, 70, 20, 25);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textS = textarea1.toString();
                String query = "";
                query = textS;
                String fontName = comboBox2.getJComboBoxName();
                String result = api.getTransResult(query, "auto", mp.get(fontName));
                String finalResult = TransService.parseJson(result).
                        replaceAll("\\\\n","\n").
                        replaceAll("\\\\r","\r").
                        replaceAll("\\\\t","\t");

                textarea2.setText(finalResult);

            }
        });
        jp1.add(button);
        jp1.setBorder(new EmptyBorder(5,5,5,5));

        comboBox1 = new TransJComboBox("自动检测");


        jp1.add(comboBox1.getBox());

        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp1, jp2);

        menuBar = new TransMenu();

        this.setJMenuBar(menuBar.getMenu());

        this.add(jsp);
        this.setResizable(false);
        this.setTitle("Translator王");
        this.setBounds(300, 200, 500, 560);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //String value = textarea.getText().trim();
        jsp.setDividerLocation(0.5);// 在1/2处进行拆分
        jsp.setEnabled(false);
    }

    public static TransTextArea getTextarea1() {
        return textarea1;
    }


}




