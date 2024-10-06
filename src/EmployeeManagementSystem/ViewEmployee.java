package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {
    
    JTable table;
    Choice cemployeeId;
    JButton search, print, update, back;
    
    ViewEmployee() {
        
        // Setting frame properties
        setTitle("Employee Management System");
        setSize(1000, 700);
        setLocation(250, 80);
        setLayout(new BorderLayout());

        // Content Panel
        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BorderLayout());
        add(content, BorderLayout.CENTER);
        
        // Header section
        JPanel header = new JPanel();
        header.setBackground(new Color(54, 57, 63));
        header.setPreferredSize(new Dimension(getWidth(), 60));
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        content.add(header, BorderLayout.NORTH);

        JLabel searchlbl = new JLabel("Search by Employee ID: ");
        searchlbl.setForeground(Color.WHITE);
        searchlbl.setFont(new Font("Arial", Font.PLAIN, 16));
        header.add(searchlbl);

        cemployeeId = new Choice();
        cemployeeId.setFont(new Font("Arial", Font.PLAIN, 14));
        header.add(cemployeeId);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            
            while(rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Table for displaying employees
        table = new JTable();
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(new Color(50, 50, 50));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(69, 90, 100));
        table.getTableHeader().setForeground(Color.WHITE);
        
        // Setting table model
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        content.add(jsp, BorderLayout.CENTER);

        // Bottom Action Panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        content.add(actionsPanel, BorderLayout.SOUTH);

        search = new JButton("Search");
        customizeButton(search);
        actionsPanel.add(search);
        
        print = new JButton("Print");
        customizeButton(print);
        actionsPanel.add(print);
        
        update = new JButton("Update");
        customizeButton(update);
        actionsPanel.add(update);
        
        back = new JButton("Back");
        customizeButton(back);
        actionsPanel.add(back);

        setVisible(true);
    }

    // Method to customize action buttons
    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 120, 170));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "select * from employee where empId = '" + cemployeeId.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }
    
    public static void main (String [] args) {
        new ViewEmployee();
    }
}
