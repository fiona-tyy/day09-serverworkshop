package revision;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public final class App{
    private App() {
    }

    public static void main(String[] args) throws IOException {
        
        // Need the random class to carry out randomised operation
        Random random = new Random();

        // generate random number between 0 and 99 (specified number is exclusive)
        int randomNumber = random.nextInt(100);

        // create variable to store guess
        int myGuess=0;

        // Opens the server socket to listed on port 1234 for input from client
        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Server running on port 1234...");
        Socket s = ss.accept();


        // Prepare input coming in through socket from client
        InputStream is = s.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // Prepare output to be sent out through socket to client
        OutputStream os = s.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        String msgRecv = "";

        while (!msgRecv.equalsIgnoreCase("quit")){
            msgRecv = dis.readUTF();

            // user must key in "guess xx"
            if (msgRecv.contains("guess")){
                myGuess = Integer.parseInt(msgRecv.substring(6));
            } 
        
            if (myGuess < randomNumber){
                dos.writeUTF("Your guess is too low");

            } else if (myGuess > randomNumber){
                dos.writeUTF("Your guess is too high");
            } else {
                dos.writeUTF("You have guessed it right!");
            }
            // flush to ensure records are written and sent across the socket
            dos.flush();
        }
        // close the input and output streams
        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();
    }
}
