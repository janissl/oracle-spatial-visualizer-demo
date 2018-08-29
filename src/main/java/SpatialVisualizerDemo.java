import javax.swing.*;

public class SpatialVisualizerDemo {
    private SpatialVisualizerDemo() {

        JFrame frame = new JFrame("Oracle Spatial Demo");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        LoginFrame loginFrame = new LoginFrame(frame);
        loginFrame.setVisible(true);

        String user = loginFrame.getUserName();
        String password = loginFrame.getPassword();

        loginFrame.dispose();

        DBConnection dbconn = new DBConnection();
        dbconn.connect(user, password);

        try {
            dbconn.getData();
        }
        catch (NullPointerException e) {
            System.exit(-1);
        }

        JPanel panel = new Visualizer(dbconn);
        dbconn.close();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpatialVisualizerDemo::new);
    }
}
