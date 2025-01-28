import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrderCreationView {
    private JPanel mainPanel;

    private Client client;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JButton viewOrderButton;
    private JPanel itemsPanel;
    private JLabel titleLabel;
    private JButton checkOutButton;
    private JScrollPane itemSP;
    private JScrollPane viewOrderSP;
    private JPanel viewOrderPanel;
    private JTextField nameTF;
    private JTextField pdTF;
    private JButton submitOrderButton;
    private JPanel submitPanel;
    private JComboBox pdCB;
    private JCheckBox deliveryCheckBox;
    private ArrayList<Item> itemsList  = new ArrayList<Item>();

    private Double total = 0.00;
    public OrderCreationView(Service service) throws IOException {

        setPdCB();



        itemsPanel.setVisible(true);
        viewOrderPanel.setVisible(false);
        submitPanel.setVisible(false);



        for (Item item : service.Items){
            JPanel p  = new JPanel();
            p.setLayout(new FlowLayout());
            JButton addBtn = new JButton("Add" );
            p.add(new JLabel(item.getName()));
            p.add(new JLabel(String.valueOf(item.getPrice())));
            p.add(addBtn);
            itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));
            itemsPanel.add(p);
            p.setVisible(true);


            addBtn.addActionListener(e -> {
                itemsList.add(item);
            });
        }

        viewOrderButton.addActionListener(e -> {

            reorderViewOrderPanel();
            if(itemsPanel.isVisible()){
                titleLabel.setText("My Order");
                viewOrderButton.setText("Back");
                viewOrderPanel.setVisible(true);
                itemsPanel.setVisible(false);
                submitPanel.setVisible(false);
                System.out.println("here");

            } else if (viewOrderPanel.isVisible()) {
                titleLabel.setText("Order Creation");
                viewOrderButton.setText("View Order");
                viewOrderPanel.setVisible(false);
                itemsPanel.setVisible(true);
                submitPanel.setVisible(false);
                System.out.println("Now Im here");

            } else if (submitPanel.isVisible()) {
                viewOrderButton.setText("View Order");
                titleLabel.setText("Order Creation");
                viewOrderButton.setVisible(true);
                submitPanel.setVisible(false);
                itemsPanel.setVisible(true);
            }


        });

        checkOutButton.addActionListener(e -> {
            viewOrderButton.setText("Back");
            itemsPanel.setVisible(false);
            viewOrderPanel.setVisible(false);
            submitPanel.setVisible(true);

        });

        submitOrderButton.addActionListener(e -> {
            Order order = null;
            if(!deliveryCheckBox.isSelected()){
                try {
                    order = new Order(nameTF.getText(),itemsList,localDates.get(pdCB.getSelectedIndex()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (deliveryCheckBox.isSelected()) {
                try {
                    order = new Delivery(nameTF.getText(),itemsList,localDates.get(pdCB.getSelectedIndex()),service.assignDriver());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            //service.addOrder(order);
            try {
                client = new Client();
                client.sendOrder(order);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            nameTF.setText("");
            itemsList.clear();
            JOptionPane.showMessageDialog(new JOptionPane(),"Order Submitted");
            System.out.println(order.toString());
            submitPanel.setVisible(false);
            itemsPanel.setVisible(true);
            viewOrderButton.setText("View Order");

            for (Order o: service.Orders){
                System.out.println(o.getCustomerName());
            }
        });


    }
    private ArrayList<LocalDate> localDates = new ArrayList<LocalDate>();
    public void setPdCB(){
        LocalDate today = LocalDate.now();


        ArrayList<String> listOFDays = new ArrayList<>();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");


        for (int i = 0; i < 14; i++) {

            LocalDate date = today.plusDays(i);

            localDates.add(date);

            String formattedDate = date.format(formatter);

            listOFDays.add(formattedDate);
        }

        for (String day: listOFDays){
            pdCB.addItem(day);
        }
    }
    public void reorderViewOrderPanel(){
        viewOrderPanel.setVisible(false);
        viewOrderPanel.removeAll();
        viewOrderPanel.setLayout(new BoxLayout(viewOrderPanel,BoxLayout.Y_AXIS));
        for (Item item :  itemsList){
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout());

            p.add(new JLabel(item.getName()));
            p.add(new JLabel(String.valueOf(item.getPrice())));
            JButton remove  = new JButton("Remove");
            p.add(remove);
            p.setVisible(true);
            viewOrderPanel.add(p);

            remove.addActionListener(e -> {
                itemsList.remove(item);
                reorderViewOrderPanel();
            });

        }

        JPanel t = new JPanel();
        t.setLayout(new FlowLayout());
        t.add(new JLabel("Total:"));
        //t.add(new JLabel(String.valueOf(getTotal())));
        t.add(new JLabel(String.format("%.2f",getTotal())));

        viewOrderPanel.add(t);
        t.setVisible(true);


        viewOrderPanel.setVisible(true);
    }

    public double getTotal()
    {
        total = 0.00;
        for (Item item: itemsList){
            //total = Double.sum(total,item.getPrice());
            total += item.getPrice();
            System.out.println(total);
        }
        return total;
    }
    public static void main(String[] args) throws IOException {
        OrderCreationView view  = new OrderCreationView(new Service());
        JFrame frame = new JFrame();
        frame.setContentPane(view.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);
        frame.setVisible(true);
    }
}
