package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    JButton view, add, update, remove;

    Home() {
        // Set layout
        setLayout(null);

        // Background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(null);
        background.setBounds(0, 0, 800, 600);
        add(background);

        // Heading
        JLabel heading = new JLabel("Employee Management Dashboard");
        heading.setBounds(50, 20, 700, 50);
        heading.setFont(new Font("Arial", Font.BOLD, 36));
        heading.setForeground(Color.WHITE);
        background.add(heading);

        // Create buttons
        add = new JButton("Add Employee");
        view = new JButton("View Employees");
        update = new JButton("Update Employee");
        remove = new JButton("Remove Employee");

        // Style buttons
        styleButton(add);
        styleButton(view);
        styleButton(update);
        styleButton(remove);

        // Set button bounds
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 20;

        add.setBounds(200, 120, buttonWidth, buttonHeight);
        view.setBounds(200, 120 + buttonHeight + spacing, buttonWidth, buttonHeight);
        update.setBounds(400, 120, buttonWidth, buttonHeight);
        remove.setBounds(400, 120 + buttonHeight + spacing, buttonWidth, buttonHeight);

        // Add action listeners
        add.addActionListener(this);
        view.addActionListener(this);
        update.addActionListener(this);
        remove.addActionListener(this);

        // Add buttons to background
        background.add(add);
        background.add(view);
        background.add(update);
        background.add(remove);

        // Frame properties
        setSize(800, 600);
        setLocation(300, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Action for buttons
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveEmployee();
        }
    }

    // Apply consistent styling to buttons
    private void styleButton(JButton button) {
        button.setBackground(Color.decode("#2C3E50"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        addHoverEffect(button);
        addShadowEffect(button);
    }

    // Hover effect for buttons
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#34495E"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#2C3E50"));
            }
        });
    }

    // Add shadow effect to buttons
    private void addShadowEffect(JButton button) {
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(1, 1, 5, 1),
                        BorderFactory.createEmptyBorder()
                )));
        button.setOpaque(true);
    }

    public static void main(String[] args) {
        new Home();
    }
}
