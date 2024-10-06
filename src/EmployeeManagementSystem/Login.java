package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton login;

    Login() {
        // Gradient background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.decode("#283048"), 0, getHeight(), Color.decode("#859398"));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Logo Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel logo = new JLabel(i3);
        logo.setBounds(220, 10, 150, 150);
        add(logo);

        // Heading with modern font and color
        JLabel lblheading = new JLabel("LOGIN");
        lblheading.setBounds(220, 180, 150, 50);
        lblheading.setFont(new Font("Arial", Font.BOLD, 36));
        lblheading.setForeground(Color.WHITE);
        lblheading.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblheading);

        // Username Label and Text Field
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 250, 100, 30);
        lblusername.setForeground(Color.WHITE);
        lblusername.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(220, 250, 250, 30);
        tfusername.setFont(new Font("Arial", Font.PLAIN, 16));
        add(tfusername);

        // Password Label and Text Field
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 300, 100, 30);
        lblpassword.setForeground(Color.WHITE);
        lblpassword.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(220, 300, 250, 30);
        tfpassword.setFont(new Font("Arial", Font.PLAIN, 16));
        add(tfpassword);

        // Login Button with hover effect and shadow
        login = new JButton("LOGIN");
        login.setBounds(220, 370, 250, 40);
        login.setBackground(Color.decode("#009688"));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.BOLD, 18));
        login.setBorder(BorderFactory.createEmptyBorder());
        login.setFocusPainted(false);
        addHoverEffect(login);
        addShadowEffect(login);
        login.addActionListener(this);
        add(login);

        // Frame properties
        setSize(600, 500);
        setLocation(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Action for login button
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Home();
    }

    // Hover effect for buttons
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#00796B"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#009688"));
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

    public static void main(String[] args) {
        new Login();
    }
}
