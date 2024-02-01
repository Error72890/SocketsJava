package serverLogic;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer{

    public static void main(String[] args) {
        /* 
        int puerto = 9876;
        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Esperando la conexión de clientes...");

            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {

                socket.receive(packet);

                Thread clientHandler = new Thread(new ClientHandler(packet));
                clientHandler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
/*
    private static class ClientHandler implements Runnable {
        private DatagramPacket packet;

        public ClientHandler(DatagramPacket packet) {
            this.packet = packet;
        }
        
        @Override
        public void run() {
            try {
                byte[] data = packet.getData();
                int length = packet.getLength();

                FileOutputStream fileOutputStream = new FileOutputStream("received.jpg");
                fileOutputStream.write(data, 0, length);

                fileOutputStream.close();

                System.out.println("Archivo recibido de cliente UDP");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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
    }*/
}
