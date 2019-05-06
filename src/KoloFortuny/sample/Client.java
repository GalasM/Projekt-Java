package KoloFortuny.sample;



import java.io.*;
import java.net.*;
public class Client{
    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    int message;
    Client(){}
    void run()
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", 2004);
            System.out.println("Connected to localhost in port 2004");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
           // Auto audi = new Auto(123);
            //System.out.println(audi.getVim());
            //sendMessage(audi);
            //sendMessage(0);
            //3: Communicating with the server
            do{
                try{
                    message = (Integer)in.readObject();
                    System.out.println("server>" + message);
                    //sendMessage(message);
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(message!=1);
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
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
            System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
   /* private void sendMessage(Auto obkt)
    {
        try{
            out.writeObject(obkt);
            out.flush();
            System.out.println("client>" + obkt.getVim());
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }*/
}

