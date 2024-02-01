package clientLogic;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) {
        int port = 9876;
        Scanner scanner = new Scanner(System.in); 

        while(true){
            System.out.println("Enter server ip or Z to finish terminal");

            String serverIP = scanner.nextLine();  
            if(serverIP.equals("Z")){
                break;
            }

            try {
                Socket socket = new Socket(serverIP, port);
                System.out.println("Conectado al servidor.");
    
                while(true){
                    System.out.println("Enter file name or X to finish conection");
                    String fileName = scanner.nextLine(); 

                    if(fileName.equals("X")){
                        break;
                    }

                    File archivo = new File(fileName);
                    FileInputStream fileInputStream = new FileInputStream(archivo);
                    OutputStream outputStream = socket.getOutputStream();
        
                    byte[] buffer = new byte[4096];
                    int bytesRead;
        
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
        
                    fileInputStream.close();
                    outputStream.close();
                }
                socket.close();
    
                System.out.println("Archivo enviado con éxito.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}