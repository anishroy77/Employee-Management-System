package employee.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {
    
    JTextField tfeducation, tffname, tfaddress, tfphone, tfemail, tfsalary, tfdesignation;
    JLabel lblname, lbldob, lblaadhar, lblempId;
    JButton add, back;
    String empId;
    
    UpdateEmployee(String empId) {
        this.empId = empId;

        // Set the background and layout
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(300, 30, 500, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(new Color(50, 50, 150));  // Soft blue
        add(heading);

        // Labels and Text Fields
        lblname = new JLabel();
        lbldob = new JLabel();
        lblaadhar = new JLabel();
        lblempId = new JLabel();

        addLabelAndField("Name", 50, 100, lblname, false);
        addLabelAndField("Father's Name", 400, 100, tffname = new JTextField(), true);
        addLabelAndField("Date of Birth", 50, 150, lbldob, false);
        addLabelAndField("Salary", 400, 150, tfsalary = new JTextField(), true);
        addLabelAndField("Address", 50, 200, tfaddress = new JTextField(), true);
        addLabelAndField("Phone", 400, 200, tfphone = new JTextField(), true);
        addLabelAndField("Email", 50, 250, tfemail = new JTextField(), true);
        addLabelAndField("Highest Education", 400, 250, tfeducation = new JTextField(), true);
        addLabelAndField("Designation", 50, 300, tfdesignation = new JTextField(), true);
        addLabelAndField("Aadhar Number", 400, 300, lblaadhar, false);
        addLabelAndField("Employee ID", 50, 350, lblempId, false);

        // Fetching employee details from the database
        fetchEmployeeData();

        // Buttons
        add = createButton("Update Details", 200, 450, 150, 40, new Color(50, 150, 50), Color.WHITE);
        back = createButton("Back", 400, 450, 150, 40, Color.RED, Color.WHITE);

        add(add);
        add(back);

        // Frame settings
        setSize(800, 600);
        setLocation(300, 100);
        setVisible(true);
    }

    private void fetchEmployeeData() {
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId = '" + empId + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                lbldob.setText(rs.getString("dob"));
                tfaddress.setText(rs.getString("address"));
                tfsalary.setText(rs.getString("salary"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfeducation.setText(rs.getString("education"));
                lblaadhar.setText(rs.getString("aadhar"));
                lblempId.setText(rs.getString("empId"));
                tfdesignation.setText(rs.getString("designation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to create buttons with consistent styling
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
    private void addLabelAndField(String labelText, int x, int y, JComponent component, boolean isEditable) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        add(label);

        if (component instanceof JTextField) {
            JTextField textField = (JTextField) component;
            textField.setBounds(x + 150, y, 200, 30);
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            add(textField);
        } else {
            JLabel lblField = (JLabel) component;
            lblField.setBounds(x + 150, y, 200, 30);
            lblField.setFont(new Font("Arial", Font.PLAIN, 16));
            add(lblField);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String fname = tffname.getText();
            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = tfeducation.getText();
            String designation = tfdesignation.getText();

            try {
                Conn conn = new Conn();
                String query = "update employee set fname = '" + fname + "', salary = '" + salary + "', address = '" + address + "', phone = '" + phone + "', email =  '" + email + "', education = '" + education + "', designation = '" + designation + "' where empId = '" + empId + "'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
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
        new UpdateEmployee("");
    }
}
