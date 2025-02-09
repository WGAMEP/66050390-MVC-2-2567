package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.*;

public class controller {
    private model m;
    private view v;

    public controller(view v, model m) {
        this.v = v;
        this.m = m;

        // เพิ่ม ActionListener สำหรับปุ่ม Check
        v.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String itemId = v.getid();
                v.updateResultLabel("");//ให้ข้อความก่อนหน้า หาย(Durability repaired by 25!)
                // ตรวจสอบความยาว ID และว่ามีการเริ่มต้นด้วย "0" หรือไม่
                if (itemId.length() != 6 || itemId.startsWith("0")) {
                    System.out.println("Invalid ID");
                    v.updateIdfail();
                    return;
                }

                // โหลดข้อมูลของไอเทมตาม ID
                if (m.loadItemData(itemId)) {
                    v.updateIdfailEmpty();
                    System.out.println("Item found: " + itemId);
                    System.out.println("Type: " + m.getType());
                    System.out.println("Durability: " + m.getDurability());
                } else {
                    v.updateIdfail();
                    System.out.println("Item not found.");
                    return;
                }

                // ตรวจสอบความทนทานตามประเภทชุด
                if (!m.checkDurability()) {
                    v.updateResultLabel("Durability is insufficient for this set.");
                    // System.out.println("Durability is insufficient for this set.");
                    // m.repairDurability();
                    System.out.println("Durability after repair: " + m.getDurability());
                    v.updateDurabilityLabel(m.getDurability()); // อัปเดตค่าความทนทานใน View
                    v.updateIdlabel(m.getId());
                    v.updateType(m.getType());
                    v.addRepairListener(); //เพิ่มปุ่ม Repair เมื่อ ทนทานต่ำ
                } else {
                    v.updateDurabilityLabel(m.getDurability()); // อัปเดตค่าความทนทานใน View
                    v.updateIdlabel(m.getId());
                    v.updateType(m.getType());
                    v.updatesufficient("sufficient");
                    System.out.println("Durability is sufficient.\n");
                }
            }
        });
        // เพิ่ม ActionListener สำหรับปุ่ม Repair (ปุ่มจะขึ้นเมื่อค่าทนทานต่ำกว่าเกณฑ์ แต่ของผมต้องเอาเมาส์ไปบริเวณใต้ปุ่มcheckถึงจะขึ้น)
        v.addRepairListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v.updatesufficient("");
                // Repair the durability of the item
                if (m.getDurability() < 100) {
                    m.repairDurability();
                    v.updateDurabilityLabel(m.getDurability()); // อัพเดตความทนทานหลังซ่อมแซม
                    v.updateResultLabel("Durability repaired by 25!");
                    System.out.println("Durability after repair: " + m.getDurability());
                } else {
                    v.updateResultLabel("Durability is already at maximum!");
                }
            }
        });
    }
}
