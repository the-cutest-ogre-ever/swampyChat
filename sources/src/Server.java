import java.net.*;
import java.io.*;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for a client...");
            Socket socket = serverSocket.accept();
            System.out.println("Got a client!\n");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String message = null;

            while (true) {
                message = dataInputStream.readUTF();
                System.out.println("Message received!\nMessage: " +
                        message + "\nSending answer to the client\n");
                dataOutputStream.writeUTF("Thanks for your message: " +
                        message + "!");
                dataOutputStream.flush();
                System.out.println("The message was delivered back. Waiting for new messages\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getClass().getSimpleName() + "error occurred:" + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println(ex.getClass().getSimpleName() + "error occurred:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

}