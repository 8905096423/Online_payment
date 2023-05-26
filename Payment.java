package intern;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

 class OnlinePaymentSystem extends JFrame {
    private JTextField tfUsername;
    private JTextField tfamount;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    public OnlinePaymentSystem() {
        setTitle("Online Payment System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create components
        JLabel lblUsername = new JLabel("UPI Id:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblamount = new JLabel("Amount:");
        tfUsername = new JTextField(25);
        pfPassword = new JPasswordField(25);
        tfamount = new JTextField(25);
        btnLogin = new JButton("Pay Now");

        // Add components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(lblUsername);
        panel.add(tfUsername);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblamount);
        panel.add(tfamount);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnLogin);
        Container container = getContentPane(); // define and get the content pane
        container.add(panel);
        // add the panel to your container
        container.add(panel);

        // Add action listener to the login button
        btnLogin.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = new String(pfPassword.getPassword());

                // Validate user credentials
                if (validateUser(username, password)) {//validateUser is used defined method
                    JOptionPane.showMessageDialog(null, "Payment Succesfull!!");
                    // TODO: Implement payment system logic
                } else {
                    JOptionPane.showMessageDialog(null, "Payment Unsuccessfull!!");
                }
            }
        });

        pack();//resize a window to fit its preferred size
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to validate user credentials against the MySQL database
    private boolean validateUser(String username, String password) {
       String url = "jdbc:mysql://localhost:3306/online_payment";
        String user = "root";
        String pass= "RNSIT#123h";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);

            String query = "SELECT * FROM payment WHERE user = ? AND pass = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch ( SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

 
}

public class Online {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SwingUtilities.invokeLater(new Runnable(){
	       
	            public void run() {
	                OnlinePaymentSystem paymentSystem = new OnlinePaymentSystem();
	                paymentSystem.setVisible(true);
	            }

		 
	  });
	}
}

		 
	

