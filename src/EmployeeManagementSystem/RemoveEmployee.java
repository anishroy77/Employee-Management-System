package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice cEmpId;
    JButton delete, back;
    JLabel lblname, lblphone, lblemail;

    RemoveEmployee() {
        // Set the background and layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Remove Employee");
        heading.setBounds(250, 20, 300, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(new Color(150, 50, 50));  // Dark red
        add(heading);

        // Employee ID Label and Choice Field
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 80, 150, 30);
        labelempId.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelempId);

        cEmpId = new Choice();
        cEmpId.setBounds(200, 80, 200, 30);
        cEmpId.setFont(new Font("Arial", Font.PLAIN, 16));
        add(cEmpId);

        // Fetch employee IDs
        try {
            Conn c = new Conn();
            String query = "select * from employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Labels and dynamic text for employee details
        lblname = new JLabel();
        lblphone = new JLabel();
        lblemail = new JLabel();

        addLabelAndField("Name", 50, 130, lblname);
        addLabelAndField("Phone", 50, 180, lblphone);
        addLabelAndField("Email", 50, 230, lblemail);

        // Fetch employee data when the choice is changed
        fetchEmployeeData();

        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                fetchEmployeeData();
            }
        });

        // Delete button
        delete = createButton("Delete", 80, 300, 150, 40, new Color(200, 50, 50), Color.WHITE);
        add(delete);

        // Back button
        back = createButton("Back", 260, 300, 150, 40, Color.GRAY, Color.WHITE);
        add(back);

        // Employee image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 50, 500, 300);
        add(image);

        // Frame settings
        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    // Fetch employee data from the database based on selected Employee ID
    private void fetchEmployeeData() {
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId = '" + cEmpId.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                lblphone.setText(rs.getString("phone"));
                lblemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to create a button with consistent styling
    private JButton createButton(String text, int x, int y, int width, int height, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    // Helper method to add labels and fields in the UI
    private void addLabelAndField(String labelText, int x, int y, JLabel fieldLabel) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        add(label);

        fieldLabel.setBounds(x + 150, y, 200, 30);
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(fieldLabel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "delete from employee where empId = '" + cEmpId.getSelectedItem() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
