package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame implements ActionListener {

    JLabel heading;
    JButton clickhere;

    Splash() {
        // Create a gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.decode("#00416A"), 0, getHeight(), Color.decode("#E4E5E6"));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);
        
        // Modern heading with subtle shadow and letter spacing
        heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(50, 50, 1200, 80);
        heading.setFont(new Font("Arial", Font.BOLD, 52));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setOpaque(false);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setText("<html><span style='letter-spacing: 2px;'>EMPLOYEE MANAGEMENT SYSTEM</span></html>");
        add(heading);
        
        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(100, 150, 1000, 450);
        add(image);
        
        // Click button with rounded corners, shadow, and hover effect
        clickhere = new JButton("CLICK HERE TO CONTINUE");
        clickhere.setBounds(400, 550, 300, 50);
        clickhere.setFont(new Font("Arial", Font.BOLD, 18));
        clickhere.setBackground(Color.decode("#00796B"));
        clickhere.setForeground(Color.WHITE);
        clickhere.setFocusPainted(false);
        clickhere.setBorder(BorderFactory.createEmptyBorder());
        clickhere.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clickhere.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#004D40"), 1, true), 
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        addHoverEffect(clickhere);
        addShadowEffect(clickhere);
        clickhere.addActionListener(this);
        add(clickhere);
        
        // Frame properties
        setSize(1170, 700);
        setLocation(200, 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Blink effect for heading
        new Timer(500, new ActionListener() {
            private boolean visible = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                heading.setVisible(visible);
                visible = !visible;
            }
        }).start();
    }
    
    // Action for button click
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    // Hover effect for the button with smooth transition
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#004D40"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#00796B"));
            }
        });
    }
    
    // Add shadow effect to the button
    private void addShadowEffect(JButton button) {
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(1, 1, 5, 1),
                        BorderFactory.createEmptyBorder()
                )));
        button.setOpaque(true);
    }

    public static void main(String args[]) {
        new Splash();
    }
}
