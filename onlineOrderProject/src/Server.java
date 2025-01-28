import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Server {

    private ServerSocket server = new ServerSocket(1111);

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private ArrayList<Order> collectedOrders = new ArrayList<Order>();

    public Server() throws IOException {
    }

    public void startServer() throws IOException, ClassNotFoundException {
        Order tempO = Service.order2;
        System.out.println("Server started and awaiting connections...");
        while(true){
            Socket socket = server.accept();
            System.out.println("Connected to ( " + socket + " )");


            ois = new ObjectInputStream(socket.getInputStream());

            String request = (String) ois.readObject();
            System.out.println("Client request: " + request);

            if (request.equals("2")){
                Order clientOrder = (Order) ois.readObject();
                collectedOrders.add(clientOrder);
                System.out.println("Received Order: " + clientOrder.toString() + ".");
                System.out.println("Uncollected Orders: " + collectedOrders.size());
            } else if (request.equals("1")) {
                System.out.println("Client requests collected orders.");

                oos = new ObjectOutputStream(socket.getOutputStream());
                sendCollectedOrders(oos);
                System.out.println("Orders sent.");
            }
            else {
                System.out.println("Client sent unkown request.");

            }









            System.out.println("Closing socket.");
            socket.close();




        }


    }
    public void closeServer() throws IOException {
        System.out.println("Closing Server");
        server.close();
    }

    public void sendCollectedOrders(ObjectOutputStream oos) throws IOException {
        oos.writeObject(collectedOrders);
        collectedOrders.clear();


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Server server1 = new Server();
        server1.startServer();


    }
}
