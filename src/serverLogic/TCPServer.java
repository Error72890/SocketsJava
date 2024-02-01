package serverLogic;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        int puerto = 9876;

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Esperando la conexión de clientes...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado.");

                // Crear un hilo para manejar la conexión del cliente
                Thread clientHandler = new Thread(new ClientHandler(socket));
                clientHandler.start();
            }
            //CERAR SOCKET
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("archivo_recibido_" + Thread.currentThread() + ".jpg");

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.close();
                inputStream.close();
                socket.close();

                System.out.println("Archivo recibido de cliente " + Thread.currentThread());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
