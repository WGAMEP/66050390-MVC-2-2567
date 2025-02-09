package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class view {
    JFrame frame = new JFrame();
    JTextField Id = new JTextField(); //กล่องข้อความinput
    JLabel nId = new JLabel("ID"); //ข้อความกำกับป้าย
    JButton check = new JButton("Check");//ปุ่มตรวจสอบ
    JButton repairButton = new JButton("Repair");//ปุ่มแซ่มแซม จะขึ้นมาตอนค่าความทนทานต่ำกว่าที่กำหนด(ผมต้องลากเมาส์ผ่านถึงจะขึ้น)
    JLabel durabilityLabel = new JLabel("Durability: "); //แสดงค่าทนทาน
    JLabel resultLabel = new JLabel(""); // ป้ายแสดงผลลัพธ์
    JLabel showid = new JLabel("ID: ");// Id ของitem
    JLabel showtype = new JLabel("Type: ");// โชว์ประเภทของ
    JLabel Idfail = new JLabel();// ข้อความเมื่อกรอกId ผิดหรือไม่พบ
    

    public view(){
        frame.setSize(600, 400);
        frame.setLayout(null);
        //กำหนดขนาดแต่ละข้อความ
        Id.setBounds(200, 40, 200, 50);
        nId.setBounds(200, 10, 20, 30);
        check.setBounds(250, 130, 100, 30);
        durabilityLabel.setBounds(250, 270, 150, 30);
        showid.setBounds(250, 230, 150, 30);
        showtype.setBounds(250, 250, 150, 30);
        resultLabel.setBounds(250, 200, 300, 30); // ตำแหน่งของป้ายผลลัพธ์
        repairButton.setBounds(250, 170, 100, 30); // Add Repair button
        Idfail.setBounds(250, 200, 150, 30);
         
        //เพิ่มเข้าfram
        frame.add(Id);
        frame.add(nId);
        frame.add(check);
        frame.add(durabilityLabel);
        frame.add(resultLabel);
        frame.add(showid);
        frame.add(showtype);
        frame.add(Idfail);
        //เปิด frame กับ หยุดโปรแกรมเมื่อปิด
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getid(){
        return Id.getText();    //รับค่าจากข้อความ
    }
    public void updatesufficient(String t){
        Idfail.setText(t); //อัพพเดตคำ
    }
    public void updateIdfail(){
        Idfail.setText("Invalid ID"); 
    }
    public void updateIdfailEmpty(){
        Idfail.setText(""); //ค่าว่าง
    }

    //ให้class controller ใช้ action ได้
    public void addActionListener(ActionListener e){
        check.addActionListener(e); 
    }

    public void updateDurabilityLabel(int durability) {
        durabilityLabel.setText("Durability: " + durability);
    }

    public void addRepairListener(ActionListener e) {
        repairButton.addActionListener(e); 
    }

    public void updateResultLabel(String result) {
        resultLabel.setText(result);    
    }
    public void updateIdlabel(String id){
        showid.setText("ID: " +id);     
    }
    public void updateType(String type){
        showtype.setText("Type: " +type);
    }
    public void addRepairListener(){
        frame.add(repairButton);
    }
}
