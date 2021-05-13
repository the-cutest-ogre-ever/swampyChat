import java.net.*;
import java.io.*;

public class Client {

    private static final int SERVER_PORT = 8080;
    private static final String IP_ADDRESS = "127.0.0.1";

    public static void main(String[] args) {

        try {
            InetAddress ipAdress = InetAddress.getByName(IP_ADDRESS);
            System.out.println("Trying to create a socket");
            Socket socket = new Socket(ipAdress, SERVER_PORT);
            System.out.println("A socket created!");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            String message = null;
            System.out.println("Enter your message and press enter please!\n");

            while (true) {
                message = keyboardReader.readLine();
                System.out.println("Sending your message to the server...");
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
                message = dataInputStream.readUTF();
                System.out.println("Got the answer from the server!\nIt sent: " + message);
                System.out.println("Looks like you can send one more message\n");
            }
        } catch (UnknownHostException ex) {
            System.out.println(ex.getClass().getSimpleName() + "error occurred:" + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getClass().getSimpleName() + "error occurred:" + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getClass().getSimpleName() + "error occurred:" + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
