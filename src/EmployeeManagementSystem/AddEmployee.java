package employee.management.system;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;
import java.awt.event.*;

public class AddEmployee extends JFrame implements ActionListener {
    
    Random ran = new Random();
    int number = ran.nextInt(999999);
    
    JTextField tfname, tffname, tfaddress, tfsalary, tfdesignation, tfphone, tfaadhar, tfemail;
    JDateChooser dcdob;
    JComboBox<String> cbeducation;
    JLabel lblempId;
    JButton add, back;

    AddEmployee() {
        // Window settings
        setTitle("Add Employee Details");
        setSize(750, 600);
        setLocation(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#F8F8F8"));  // Light background color
        
        setLayout(null); // Retain layout style

        // Heading
        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(200, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setForeground(Color.decode("#333333"));  // Darker text color
        add(heading);

        // Name
        addLabelAndField("Name:", 50, 150, tfname = new JTextField(), 200, 150);
        
        // Father's Name
        addLabelAndField("Father's Name:", 400, 150, tffname = new JTextField(), 550, 150);

        // Date of Birth
        addLabelAndField("Date of Birth:", 50, 200, dcdob = new JDateChooser(), 200, 200);

        // Salary
        addLabelAndField("Salary:", 400, 200, tfsalary = new JTextField(), 550, 200);

        // Address
        addLabelAndField("Address:", 50, 250, tfaddress = new JTextField(), 200, 250);

        // Phone
        addLabelAndField("Phone:", 400, 250, tfphone = new JTextField(), 550, 250);

        // Email
        addLabelAndField("Email:", 50, 300, tfemail = new JTextField(), 200, 300);

        // Education (Dropdown)
        JLabel labeleducation = new JLabel("Education:");
        labeleducation.setBounds(400, 300, 150, 30);
        labeleducation.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labeleducation);

        String courses[] = {"BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTECH", "MSC"};
        cbeducation = new JComboBox<>(courses);
        cbeducation.setBounds(550, 300, 150, 30);
        cbeducation.setFont(new Font("Arial", Font.PLAIN, 16));
        cbeducation.setBackground(Color.WHITE);
        add(cbeducation);

        // Designation
        addLabelAndField("Designation:", 50, 350, tfdesignation = new JTextField(), 200, 350);

        // Aadhar Number
        addLabelAndField("Aadhar Number:", 400, 350, tfaadhar = new JTextField(), 550, 350);

        // Employee ID
        JLabel labelempId = new JLabel("Employee ID:");
        labelempId.setBounds(50, 400, 150, 30);
        labelempId.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelempId);

        lblempId = new JLabel("" + number);
        lblempId.setBounds(200, 400, 150, 30);
        lblempId.setFont(new Font("Arial", Font.PLAIN, 18));
        add(lblempId);

        // Add Details Button
        add = new JButton("Add Details");
        add.setBounds(200, 500, 150, 40);
        add.setFont(new Font("Arial", Font.BOLD, 18));
        add.setBackground(Color.decode("#4CAF50"));  // Green color for the button
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        // Back Button
        back = new JButton("Back");
        back.setBounds(400, 500, 150, 40);
        back.setFont(new Font("Arial", Font.BOLD, 18));
        back.setBackground(Color.decode("#F44336"));  // Red color for the button
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    // Utility method to add labels and fields with better styling and positioning
    private void addLabelAndField(String labelText, int labelX, int labelY, Component field, int fieldX, int fieldY) {
        JLabel label = new JLabel(labelText);
        label.setBounds(labelX, labelY, 150, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        add(label);
        field.setBounds(fieldX, fieldY, 150, 30);
        if (field instanceof JTextField) {
            ((JTextField) field).setFont(new Font("Arial", Font.PLAIN, 16));
        }
        add(field);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) cbeducation.getSelectedItem();
            String designation = tfdesignation.getText();
            String aadhar = tfaadhar.getText();
            String empId = lblempId.getText();
            
            try {
                Conn conn = new Conn();
                String query = "insert into employee values('"+name+"' , '"+fname+"' , '"+dob+"' , '"+salary+"' , '"+address+"' , '"+phone+"' , '"+email+"' , '"+education+"' , '"+designation+"' , '"+aadhar+"' , '"+empId+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
