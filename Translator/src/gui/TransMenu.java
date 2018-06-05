package gui;

import translate.OCRHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TransMenu {
    private JMenuBar menuBar;
    private TransTextArea textarea1;
    public TransMenu()  {
        menuBar = new JMenuBar();

        // 菜单栏
        JMenu menu1 = new JMenu("选择");

        JMenuItem jm101 = new JMenuItem("上传一张图片");
        JMenuItem jm102 = new JMenuItem("上传本地文本");
//

        jm101.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();

                // 选择文件路径
                if (file != null) {
                    if(file.isDirectory()){
                        System.out.println("文件夹:"+file.getAbsolutePath());
                    }else if(file.isFile()){
                        System.out.println("文件:"+file.getAbsolutePath());
                    }
                    String filePath = file.getAbsolutePath();
                    if(!filePath.substring(filePath.lastIndexOf(".") + 1).equalsIgnoreCase("jpg") &&
                            !filePath.substring(filePath.lastIndexOf(".") + 1).equalsIgnoreCase("png")&&
                            !filePath.substring(filePath.lastIndexOf(".") + 1).equalsIgnoreCase("bmp"))
                        try {
                            throw new Exception("请选择正确的图片格式！");
                        } catch (Exception e1) {
                            JPanel jPanel = new JPanel();
                            JOptionPane.showMessageDialog(jPanel, e1.getMessage(), "警告",JOptionPane.WARNING_MESSAGE);
                        }
                    String query = OCRHandler.OCRService(filePath);
                    textarea1 = TransFrame.getTextarea1();
                    textarea1.setText(query);
                }
                }

        });

        jm102.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();

                // 选择文件路径
                if(file != null) {
                    if (file.isDirectory()) {
                        System.out.println("文件夹:" + file.getAbsolutePath());
                    } else if (file.isFile()) {
                        System.out.println("文件:" + file.getAbsolutePath());
                    }
                    String filePath = file.getAbsolutePath();
                    if (!filePath.substring(filePath.lastIndexOf(".") + 1).equalsIgnoreCase("txt"))
                        try {
                            throw new Exception("请选择txt文本格式！");
                        } catch (Exception e1) {
                            JPanel jPanel = new JPanel();
                            JOptionPane.showMessageDialog(jPanel, e1.getMessage(), "警告", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                    String query = "";
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
                        String temp = null;
                        while ((temp = br.readLine()) != null) {
                            query += temp;
                        }
                        textarea1 = TransFrame.getTextarea1();
                        textarea1.setText(query);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        menu1.add(jm101);

        menu1.add(jm102);
//

        menuBar.add(menu1);



    }

    public JMenuBar getMenu() {
        return menuBar;
    }
}
