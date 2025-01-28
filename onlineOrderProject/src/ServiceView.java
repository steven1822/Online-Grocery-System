import javax.swing.*;
import java.io.IOException;

public class ServiceView {
    private JPanel mainPanel;
    private JLabel titelLabel;
    private JButton orderManagementButton;
    private JButton orderCreationButton;

    public ServiceView() throws IOException, ClassNotFoundException {
        Service service = new Service();





        orderCreationButton.addActionListener(e -> {
            OrderCreationView ocView = null;
            try {
                ocView = new OrderCreationView(service);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            for (Order o: service.Orders){
                System.out.println(o.getCustomerName());
            }
            JFrame frame = new JFrame();
            frame.setContentPane(ocView.getMainPanel());
            frame.setSize(500,400);
            frame.setVisible(true);
        });

        orderManagementButton.addActionListener(e -> {
            OrderManagementView omView = null;
            try {
                omView = new OrderManagementView(service);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            JFrame frame = new JFrame();
            frame.setContentPane(omView.getMainPanel());
            frame.setSize(500,400);
            frame.setVisible(true);
        });

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServiceView sv = new ServiceView();
        JFrame frame = new JFrame();
        frame.setContentPane(sv.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }
}
