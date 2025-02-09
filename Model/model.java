package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class model {
    private String id;
    private String type;
    private int durability;
    private String csv = "Model/suit_data.csv"; // ที่ตั้งของไฟล์ CSV

    public model(){
        this.id = "";
        this.type = "";
        this.durability = 100;
    }

    // ฟังก์ชันโหลดข้อมูลของไอเทมจาก CSV
    public boolean loadItemData(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line;
            boolean firstLine = true; // ข้ามหัวcollum
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // ข้ามหัวcollum
                    continue;
                }
                String[] values = line.split(",");
                // นำข้อมูล csv id,type,durability มาใส่
                this.id = values[0];//id
                String type = values[1];//type
                int durability = Integer.parseInt(values[2]);//durability
    
                //เก็บข้อมูล
                if (this.id.equals(id)) {
                    this.type = type;
                    this.durability = durability;
                    return true; // หาitemเจอ
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // คืนค่า false ถ้าไม่พบ ID
    }

    // ฟังก์ชันตรวจสอบความทนทาน
    public boolean checkDurability() {
        if (type.equals("Powerful")) {
            return durability >= 70; // ชุด Power ต้องมีความทนทานไม่น้อยกว่า 70
        } else if (type.equals("Stealth")) {
            return durability >= 50; // ชุด Stealth ต้องมีความทนทานไม่น้อยกว่า 50
        } else if (type.equals("Identity")) {
            // ชุด Identity ต้องไม่ลงท้ายด้วยเลข 3 หรือ 7
            String durabilityStr = Integer.toString(durability);
            char lastChar = durabilityStr.charAt(durabilityStr.length() - 1);
            return lastChar != '3' && lastChar != '7';
        }
        return false; // ถ้าไม่ตรงกับชุดที่กำหนด
    }

    // ฟังก์ชันซ่อมแซมความทนทาน
    public void repairDurability() {
        if (durability < 75) {
            durability = Math.min(durability + 25, 100); // เพิ่ม 25 สูงสุดไม่เกิน 100
        }
        else if(durability >= 75){
            durability = 100; //สามารถอัพค่าเพิ่มได้จนเต็ม100
        }
    }

    // Getter ต่างๆ
    public int getDurability() {
        return durability;
    }

    public String getType() {
        return type;
    }

    public String getId(){
        return id;
    }
}
