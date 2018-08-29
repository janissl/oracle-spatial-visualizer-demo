import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class LoginFrame extends JDialog {
    private JTextField tfUserName;
    private JPasswordField pfPassword;

    private String username;
    private String password;

    LoginFrame(JFrame parent) {
        super(parent, "Login", true);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUserName = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        mainPanel.add(lblUserName, cs);

        tfUserName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        mainPanel.add(tfUserName, cs);

        JLabel lblPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        mainPanel.add(lblPassword, cs);

        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        mainPanel.add(pfPassword, cs);
        mainPanel.setBorder(new LineBorder(Color.GRAY));

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> {
            this.username = tfUserName.getText().trim();
            this.password = new String(pfPassword.getPassword());
            setVisible(false);
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> { dispose(); System.exit(-1); });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCancel);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        SwingUtilities.getRootPane(btnLogin).setDefaultButton(btnLogin);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    String getUserName() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }
}
