import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txtUser;
    private JPasswordField txtPass;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 539, 359);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLabel = new JLabel("Login Form");
        lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblLabel.setBounds(201, 10, 136, 38);
        contentPane.add(lblLabel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(10, 74, 103, 38);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(10, 163, 103, 38);
        contentPane.add(lblPassword);

        txtUser = new JTextField();
        txtUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUser.setBounds(158, 74, 357, 38);
        contentPane.add(txtUser);
        txtUser.setColumns(10);

        txtPass = new JPasswordField();
        txtPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPass.setBounds(158, 163, 357, 38);
        contentPane.add(txtPass);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();

                System.exit(0);
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnExit.setBounds(340, 255, 103, 33);
        contentPane.add(btnExit);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtUser.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền Username!");
                } else if (txtPass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền Password!");
                } else {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");

                        String sql = "SELECT * FROM tbusers\n" + "WHERE txtuser=? AND txtpass=?";

                        PreparedStatement pstm = conn.prepareStatement(sql);
                        pstm.setString(1, txtUser.getText().replaceAll("\\s+", ""));
                        pstm.setString(2, txtPass.getText());

                        ResultSet rs = pstm.executeQuery();

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Chúc mừng đăng nhập thành công!");
                            txtUser.setText("");
                            txtPass.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");
                            txtUser.setText("");
                            txtPass.setText("");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogin.setBounds(83, 255, 103, 33);
        contentPane.add(btnLogin);
    }
}
