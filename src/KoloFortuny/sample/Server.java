package KoloFortuny.sample;

import java.io.*;
import java.net.*;
import java.util.Random;


public class Server {
   private static String[][] hasla = {{"Ala ma", "Powiedzenia"}, {"Krol Karol kupil krolowej Karolinie korale koloru koralowego", "aaa"}, {"Stol z powylamywanymi nogami", "bbb"}};
   private static final int generatedNumber = randomValue();
   private static String password;
   private static StringBuilder passwordCompleted = new StringBuilder();
   private static String category;
   private static int length;
    // Socket connection = null;
    //  ServerSocket providerSocket;
    //  ObjectOutputStream out;
    //  ObjectInputStream in;
    //  Messages message;

    private static final int PORT=2004;

    private static int randomValue()
    {
        Random generator = new Random();
        return generator.nextInt(3);   //ilosc hasel i kategorii
    }



    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;
        private ObjectInputStream in;
        private OutputStream os;
        private ObjectOutputStream out;
        private InputStream is;

        public Handler(Socket socket) throws IOException {
            this.socket = socket;
        }

        public void run(){

            try{
                is = socket.getInputStream();
                in = new ObjectInputStream(is);
                os = socket.getOutputStream();
                out = new ObjectOutputStream(os);
                System.out.println("Server ON");
                Messages firstMessage = (Messages) in.readObject();
                System.out.println(firstMessage.getMsg());
                sendNotification(firstMessage);

                while(socket.isConnected())
                {
                    Messages inputmsg = (Messages) in.readObject();
                    if (inputmsg != null) {
                        switch (inputmsg.getType()) {
                            case SIGN:
                                Thread t = Thread.currentThread();
                                System.out.println("Server"+t);
                                System.out.println(inputmsg.getMsg());
                                checkSign(inputmsg.getMsg());
                                String msg = new String(passwordCompleted);
                                System.out.println(passwordCompleted);
                                Messages Pass = new Messages();
                                Pass.setMsg(msg);
                                Pass.setType(MessageType.PASSWORD);
                                sendMessage(Pass);
                                out.flush();
                                break;
                            case CATEGORY:
                                sendMessage(inputmsg);
                                break;
                            case START:
                                Messages StartWord = new Messages();
                                Messages StartCategory = new Messages();

                                StartWord.setType(MessageType.PASSWORD);
                                password=randomWord();
                                hideWord();
                                StartWord.setMsg(password);
                                System.out.println(StartWord.getMsg());
                                category=randomCategory();
                                StartCategory.setMsg(category);
                                StartCategory.setType(MessageType.CATEGORY);
                                sendMessage(StartWord);
                                sendMessage(StartCategory);

                                break;
                            case NOTIFICATION:
                                sendMessage(inputmsg);
                        }
                    }
                }
            } catch (SocketException socketException) {


            } catch (Exception e){

            } finally {
                try {
                    in.close();
                    out.close();
                    socket.close();
                }catch (IOException e)
                {

                }

            }

            }


        void sendMessage(Messages msg)
        {
            try{
                out.writeObject(msg);
                out.flush();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }

        private void sendNotification(Messages firstMessage) throws IOException {
            Messages msg = new Messages();
            msg.setMsg(firstMessage.getMsg());
            msg.setType(firstMessage.getType());
            sendMessage(msg);
        }


        String randomWord()
        {
            return hasla[generatedNumber][0].toUpperCase();
        }

        String randomCategory()
        {
            return hasla[generatedNumber][1].toUpperCase();
        }

        void checkSign(String sign) {
            int countSigns=0;
            for (int i = 0; i < length; i++) {
                char ClickedSign;
                ClickedSign = sign.charAt(0);
                char CurrSign = password.charAt(i);
                if (CurrSign == ClickedSign) {
                    countSigns++;
                    setHiddenWord(i, ClickedSign);
                }
            }
        }

        private void hideWord() {
            length=password.length();
            passwordCompleted = new StringBuilder(password);
            for (int i = 0; i < length; i++)
                if (password.charAt(i) == ' ')
                    passwordCompleted.setCharAt(i, '_');
                else
                    passwordCompleted.setCharAt(i, '#');
        //System.out.println(passwordCompleted);
        }

        void setHiddenWord(int i, char ClickedSign) {
            passwordCompleted.setCharAt(i, ClickedSign);
        }

    }

}
    /*void run()
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
                    message = (Messages)in.readObject();
                   if(message.getType()== MessageType.START) {
                        message.setType(MessageType.PASSWORD);
                        message.setMsg(randomWord());
                        sendMessage(message);
                        message.setType(MessageType.CATEGORY);
                        message.setMsg(randomCategory());
                        sendMessage(message);
                    }
                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }
            }while(in.available()!=0);
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
    }*/
   /* void sendMessage(Messages msg)
    {
        try{
            out.writeObject(msg.getMsg());
            out.flush();
            System.out.println("server>" + msg.getMsg());
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    void sendMessage(String msg)
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

    int randomValue()
    {
        Random generator = new Random();
        return generator.nextInt(3);   //ilosc hasel i kategorii
    }

    String randomWord()
    {
        return hasla[generatedNumber][0];
    }

    String randomCategory()
    {
        return hasla[generatedNumber][1];
    }

   private static class Handler extends Thread
    {
        Socket client;
        ObjectOutputStream out;
        ObjectInputStream in;
        Socket connection = null;
        Messages message;
        public Handler(Socket client)
        {
            this.client=client;
        }
        public void run()
        {
            try {
                out = new ObjectOutputStream(connection.getOutputStream());
                out.flush();
                in = new ObjectInputStream(connection.getInputStream());
                message=(Messages) in.readObject();
                sendMessage(message);
                while (client.isConnected())
                {
                    Messages inputmsg = (Messages) in.readObject();

                }
            }catch(IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally{
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
    }

    public static void main(String args[]) throws Exception
    {
        ServerSocket listener = new ServerSocket(2004);
        //Server server = new Server();
        //server.run();
        try{
        while(true)
        {
            new Handler(listener.accept()).start();
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            listener.close();
        }
    }
}*/