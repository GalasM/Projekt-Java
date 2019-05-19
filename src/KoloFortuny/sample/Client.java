package KoloFortuny.sample;



import java.io.*;
import java.net.*;

//import static KoloFortuny.sample.Main.newGame;
import static KoloFortuny.sample.MessageType.CONNECTED;
import static KoloFortuny.sample.MessageType.PASSWORD;
import static KoloFortuny.sample.Controller.kontroler;
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
   // public Controller controler;
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
            //controler = new Controller();
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
                            Thread t = Thread.currentThread();
                            System.out.println("Client"+t);
                            //kontroler.changeBoard(password);
                            //ogarnac jak uzywac kontrolera w kliencie
                            //controler.changeBoard(password);
                            break;
                        case CATEGORY:
                            System.out.println(message.getMsg());
                            this.category=message.getMsg();
                            newGame = new Game(password,category);
                            break;
                        case SIGN:
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
        finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

        public static void connect() throws IOException {
        Messages createMessage = new Messages();
        createMessage.setType(CONNECTED);
        createMessage.setMsg("Connected");
        out.writeObject(createMessage);
    }

    public static void sendMessage(Messages msg) throws IOException
    {
        try{
            Messages newMessage = new Messages();
            newMessage.setType(msg.getType());
            newMessage.setMsg(msg.getMsg());
            out.writeObject(newMessage);
            out.flush();
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }



}

