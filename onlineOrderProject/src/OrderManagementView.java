import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderManagementView {
    private JPanel mainPanel;
    private JButton fulfillButton;
    private JButton backButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel ordersPanel;
    private JPanel viewOrderPanel;
    private JPanel viewItemsPanel;
    private JLabel titleLabel;

    private Order currentOrder;

    private Service service;

    private Client client;




    public OrderManagementView(Service service) throws IOException, ClassNotFoundException {


        backButton.setVisible(false);
        fulfillButton.setVisible(false);
        this.service = service;

        reorderOrdersPanel();
        ordersPanel.setVisible(true);
        viewOrderPanel.setVisible(false);
        viewItemsPanel.setVisible(false);

        Timer timer = new Timer(5000,null);
        timer.start();



        backButton.addActionListener(e -> {
            reorderOrdersPanel();
            if(viewOrderPanel.isVisible()){
                titleLabel.setText("Order Management");
                backButton.setVisible(false);
                fulfillButton.setVisible(false);
                viewOrderPanel.setVisible(false);
                ordersPanel.setVisible(true);
            } else if (viewItemsPanel.isVisible()) {
                viewOrder(currentOrder);
            }
        });

        fulfillButton.addActionListener(e -> {
            service.removeOrder(currentOrder);
            reorderOrdersPanel();
            fulfillButton.setVisible(false);
            backButton.setVisible(false);
            titleLabel.setText("Order Mangement");
            viewItemsPanel.setVisible(false);
            viewOrderPanel.setVisible(false);
            ordersPanel.setVisible(true);
        });
        timer.addActionListener(e -> {
            service.writeOrderListFile();

            try {
                client = new Client();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ArrayList<Order> temp = new ArrayList<Order>();
            try {
                temp = client.requestCollectedOrders();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            for (Order order: temp){
                service.addOrder(order);
            }

            reorderOrdersPanel();
            if (ordersPanel.isVisible()){
                ordersPanel.setVisible(false);
                ordersPanel.setVisible(true);
            }
            timer.restart();

        });

    }

    public void reorderOrdersPanel(){
        ordersPanel.removeAll();
        service.sortOrders();
        ordersPanel.setLayout(new BoxLayout(ordersPanel,BoxLayout.Y_AXIS));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        for(Order order: service.Orders){
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout());
            if (order.getCustomerName().equals("Class Delivery")){
                p.add(new JLabel("D - "));
            }
            p.add(new JLabel(order.getCustomerName() + " - "));
            p.add(new JLabel(String.valueOf(order.getOrderNumber())  + " - "));
            p.add(new JLabel(order.getPickupDate().format(formatter)));
            JButton viewBtn = new JButton("View Order");
            p.add(viewBtn);
            ordersPanel.add(p);


            p.setVisible(true);

            viewBtn.addActionListener(e -> {
                viewOrder(order);
            });
        }


    }


    public void viewOrder(Order order){
        viewOrderPanel.removeAll();
        currentOrder = order;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        titleLabel.setText(order.getCustomerName() + " - " + String.valueOf(order.getOrderNumber()));
        backButton.setVisible(true);
        fulfillButton.setVisible(true);
        ordersPanel.setVisible(false);
        viewOrderPanel.setVisible(true);
        viewItemsPanel.setVisible(false);

        viewOrderPanel.setLayout(new BoxLayout(viewOrderPanel,BoxLayout.Y_AXIS));

        JPanel n = new JPanel();
        n.setLayout(new FlowLayout());
        n.add(new JLabel("Customer Name: " + order.getCustomerName()));

        JPanel o = new JPanel();
        o.setLayout(new FlowLayout());
        o.add(new JLabel(" - Order Number: " + String.valueOf(order.getOrderNumber())));

        JPanel s = new JPanel();
        s.setLayout(new FlowLayout());
        s.add(new JLabel(" - Order Size: " + String.valueOf(order.getSize())));

        JPanel t = new JPanel();
        t.setLayout(new FlowLayout());
        t.add(new JLabel(" - Total Price: " + String.format("%.2f",order.getTotalPrice())));

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(new JLabel(" - Pickup Date: " + order.getPickupDate().format(formatter)));

        JPanel w = new JPanel();
        w.setLayout(new FlowLayout());
        w.add(new JLabel(" - Order Weight: " + String.valueOf(order.getWeight())));

        viewOrderPanel.add(n);
        viewOrderPanel.add(o);
        viewOrderPanel.add(s);
        viewOrderPanel.add(t);
        viewOrderPanel.add(p);
        viewOrderPanel.add(w);

        JButton viewItemsBtn = new JButton("View Items");
        viewOrderPanel.add(viewItemsBtn);


        viewItemsBtn.addActionListener(e -> {
            viewItems(order);
        });





    }

    public void viewItems(Order order){
        viewItemsPanel.removeAll();
        viewOrderPanel.setVisible(false);
        viewItemsPanel.setVisible(true);
        viewOrderPanel.setVisible(false);

        viewItemsPanel.setLayout(new BoxLayout(viewItemsPanel,BoxLayout.Y_AXIS));

        for (Item item: order.getItems()){
            JPanel p  = new JPanel();
            p.setLayout(new FlowLayout());
            p.add(new JLabel(item.getName()));
            p.add(new JLabel(String.valueOf(item.getPrice())));
            viewItemsPanel.add(p);
            p.setVisible(true);
        }





    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Delivery test = new Delivery(null,Service.Items,null,null);
        System.out.println(test.getClass());
        OrderManagementView view  = new OrderManagementView(new Service());
        JFrame frame = new JFrame();
        frame.setContentPane(view.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }
}
