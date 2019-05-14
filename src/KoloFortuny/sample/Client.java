package KoloFortuny.sample;



import java.io.*;
import java.net.*;

import static KoloFortuny.sample.Main.newGame;
import static KoloFortuny.sample.MessageType.CONNECTED;
import static KoloFortuny.sample.MessageType.PASSWORD;

public class Client implements Runnable{
    int port;
    Socket socket;
    private static ObjectOutputStream out;
    private InputStream is;
    private ObjectInputStream in;
    private OutputStream os;
    private String password;
    private String category;
    Messages message;
    static Game newGame;
    Client(int port){
        this.port=port;
    }

    public Messages getMessage()
    {
        return message;
    }

    public void run() {
        try {
            socket = new Socket("localhost", 2004);
            os = socket.getOutputStream();
            out = new ObjectOutputStream(os);
            out.flush();
            is = socket.getInputStream();
            in = new ObjectInputStream(is);
        } catch (IOException e) {

        }

        try {
            connect();
            while(socket.isConnected())
            {
                message = (Messages) in.readObject();
                if(message!=null)
                {

                    switch (message.getType()) {
                        case PASSWORD:
                            System.out.println(message.getMsg());
                            this.password=message.getMsg();
                            break;
                        case CATEGORY:
                            System.out.println(message.getMsg());
                            this.password=message.getMsg();
                            break;
                        case NOTIFICATION:
                            System.out.println("bozia dala"+message.getMsg());
                            break;

                        case CONNECTED:
                        System.out.println("Cennected to server");
                            break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        public static void connect() throws IOException {
        Messages createMessage = new Messages();
        createMessage.setType(CONNECTED);
        createMessage.setMsg("Connected");
        out.writeObject(createMessage);
    }
           /* do{
                try{
                    message = (Messages) in.readObject();
                    if(message.getType()==MessageType.PASSWORD)
                        newGame = new Game(message.getMsg());
                    System.out.println("server>" + message.getMsg());
                    //sendMessage(message);
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(in.available()!=0);
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
    }*/
   /* void sendMessage(int msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }*/

    public static void sendMessage(Messages msg) throws IOException
    {
        try{
            Messages newMessage = new Messages();
            newMessage.setType(msg.getType());
            newMessage.setMsg(msg.getMsg());
            out.writeObject(newMessage);
            out.flush();
           // System.out.println("client>" + msg.getMsg());
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

}

