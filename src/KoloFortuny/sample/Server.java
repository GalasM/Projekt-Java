package KoloFortuny.sample;

import java.io.*;
import java.net.*;
public class Server{
    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    int i;
    int message;
    Server(){}
    void run()
    {
        try{
            //1. creating a server socket
            providerSocket = new ServerSocket(2004);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            //sendMessage(0);
            //4. The two parts communicate via the input and output streams
            do{
                try{
                    message = (Integer)in.readObject();
                   // System.out.println("client>" + message.getVim());
                    i++;
                    sendMessage(i);
                    message=message+1;
                  /*  int x =message;
                    x++;
                    message=x;
                    //Object.parseInt(message);
                    if (message!=4)
                        sendMessage(message);*/
                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }
            }while(i!=1);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                providerSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(int msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}