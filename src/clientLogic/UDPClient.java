package clientLogic;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        FileInputStream fileInputStream = null;
        Scanner scanner = new Scanner(System.in); 

        while (true){
            System.out.println("Enter server ip or Z to finish terminal");

            String serverIP = scanner.nextLine();  
            if(serverIP.equals("Z")){
                break;
            }
        
            try {
                InetAddress serverAddress = InetAddress.getByName(serverIP); 
                int serverPort = 9876; 
                socket = new DatagramSocket();

                while(true){
                    System.out.println("Enter image name or X to finish conection");
                    String fileName = scanner.nextLine(); 

                    if(fileName.equals("X")){
                        break;
                    }

                    //fileInputStream = new FileInputStream(fileName);
                    BufferedImage img = ImageIO.read(new File(fileName));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();        
                    ImageIO.write(img, "jpg", baos);
                    baos.flush();
                    byte[] buffer = baos.toByteArray();
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
                    socket.send(packet);
    
                    /*int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        socket.send(packet);
                    }*/
                }
                socket.close();

            } catch (IOException e) {
                e.printStackTrace(); // Puedes personalizar el manejo de la excepción según tus necesidades
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace(); // Puedes personalizar el manejo de la excepción según tus necesidades
                }
            }
        }
        scanner.close();
    }
}