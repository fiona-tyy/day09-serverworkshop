package revision;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        String host = "localhost";
        int port = 1234;
        // variable to store keyboard input and socket return value
        String keyInput = "", msgRecv = "";

        // using console to receive input from keyboard
        Console cons = System.console();

        // Opening a socket to connect to server
        Socket socket = new Socket(host, port);

        try (OutputStream os = socket.getOutputStream()){
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            
            
            try(InputStream is = socket.getInputStream()){
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);
                
                while(!keyInput.equalsIgnoreCase("quit")){
                    keyInput = cons.readLine("Enter 'guess XX' \n");
                    dos.writeUTF(keyInput);
                    dos.flush();

                    msgRecv = dis.readUTF();
                    System.out.println("From server: " + msgRecv);
                }

                dos.close();
                bos.close();
                os.close();
                dis.close();
                bis.close();
                is.close();
                socket.close();

            } catch (EOFException e){
                e.printStackTrace();
                socket.close();
            }
            
        } catch (EOFException e) {
            e.printStackTrace();
            socket.close();
        }
      
    }
}
