import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Registered_Students_Report extends JFrame implements ActionListener {
    Label ltitle;
    Button butback;
    BufferedReader br;
    String students_data[][];
    String s[];
    JTable t;
    JScrollPane jp;
    JFrame f = new JFrame();
    TextArea ta = new TextArea();

    Registered_Students_Report() {
        setSize(1920, 1080);
        setLayout(null);
        setVisible(true);
        setTitle("Registered Students Report.");
        setBackground(Color.GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ltitle = new Label("Registered Students Information Report.");

        butback = new Button("Close");
        butback.addActionListener(this);

        Font f1 = new Font("arial black", Font.BOLD, 34);
        Font f2 = new Font("times new roman", Font.BOLD, 28);

        ltitle.setFont(f1);
        ltitle.setForeground(Color.RED);
        butback.setFont(f2);

        try {
            int i = 0, n = 0;
            br = new BufferedReader(new FileReader("Student_Reg.txt"));
            String stud;
            while ((stud = br.readLine()) != null) {
                n++;
            }
            br = new BufferedReader(new FileReader("Student_Reg.txt"));

            students_data = new String[n][12];
            while ((stud = br.readLine()) != null) {
                s = stud.split("\\$");
                students_data[i][0] = s[0];
                students_data[i][1] = s[1];
                students_data[i][2] = s[2];
                students_data[i][3] = s[3];
                students_data[i][4] = s[4];
                students_data[i][5] = s[5];
                students_data[i][6] = s[6];
                students_data[i][7] = s[7];
                students_data[i][8] = s[8];
                students_data[i][9] = s[9];
                students_data[i][10] = s[10];
                students_data[i][11] = s[11];
                i++;
            }
            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Problems during Reading the file.");
        }
        String columns[] = { "Students PRN", "Students Name", "Batch Year", "Course", "Division", "Roll No.",
                "Permanent Address", "Gender", "Mobile Number", "E-Mail ID", "Hobbies", "Expertise Area" };
        t = new JTable(students_data, columns);
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                f.setVisible(true);
                f.setSize(500, 520);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                int row = t.rowAtPoint(e.getPoint());
                ta.setText(
                        "Selected Students Detail" +
                                "\n----------------------------------------------------" +
                               "\nStudents PRN : " +students_data[row][0] +
                               "\nStudents Name : " +students_data[row][1] +
                                "\nBatch Year : "+students_data[row][2] +
                               "\nCourse : " +students_data[row][3] +
                                 "\nDivision : "+students_data[row][4] +
                                 "\nRoll No. : "+students_data[row][5] +
                                "\nPermanent Address : "+students_data[row][6] +
                               "\nGender : "+students_data[row][7] +
                                "\nMobile Number : " +students_data[row][8] +
                                "\nE-Mail ID : " +students_data[row][9] +
                                "\nHobbies : "+students_data[row][10] +
                                "\nExpertise Area : "+students_data[row][11]);

                ta.setFont(f2);
                f.add(ta);
                

            }
        });
        jp = new JScrollPane(t);

        add(ltitle);
        add(jp);
        add(butback);

        ltitle.setBounds(400, 30, 800, 40);
        jp.setBounds(40, 100, 1460, 550);
        butback.setBounds(700, 700, 100, 40);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == butback)
            dispose();
            f.dispose();

    }

}
