import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private Socket socket;
    private ObjectOutputStream oos;


    private ObjectInputStream ois ;

    public Client() throws IOException {


    }

    public void sendOrder(Order order) throws IOException {
        socket = new Socket("localhost",1111);
        System.out.println("Connected to Server...");

        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("2");
        oos.writeObject(order);
        System.out.println("Order Sent.");

        System.out.println("Closing socket.");
        socket.close();



    }

    public ArrayList<Order> requestCollectedOrders() throws IOException, ClassNotFoundException {
        socket = new Socket("localhost",1111);

        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("1");
        System.out.println("Requested Orders.");

        ois = new ObjectInputStream(socket.getInputStream());

        ArrayList<Order> orderList = (ArrayList<Order>) ois.readObject();
        if(orderList.size() > 0) {
            System.out.println("Orders Received");
        }
        else {
            System.out.println("No new orders");
        }


        System.out.println("Closing Socket");
        socket.close();




        return orderList;





    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        Order order = Service.order2;
        client.sendOrder(order);
        System.out.println(client.requestCollectedOrders());



    }




}
