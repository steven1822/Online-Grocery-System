import javax.swing.*;
import java.io.IOException;

public class ServerView {
    private JPanel mainPanel;
    private JButton startServerButton;
    private JButton closeServerAndExitButton;

    private Server server = new Server();




    public ServerView() throws IOException {
        startServerButton.addActionListener(e -> {
            try {
                server.startServer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        closeServerAndExitButton.addActionListener(e -> {
            try {
                server.closeServer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            System.exit(0);

        });
    }

    public static void main(String[] args) throws IOException {
        ServerView view  = new ServerView();
        JFrame frame = new JFrame();
        frame.setContentPane(view.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }
}
