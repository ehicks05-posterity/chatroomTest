package com.steven.hicks;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ChatServer implements Runnable
{
    public ServerSocket chatServer;
    public Thread thread;

    public ChatServer(int port) throws IOException
    {
        chatServer = new ServerSocket(port);
        chatServer.setSoTimeout(10000);
    }

    public void run()
    {
        while (true)
        {
            try
            {
                Socket server = chatServer.accept();

                Scanner in = new Scanner(server.getInputStream());
                PrintWriter out = new PrintWriter(server.getOutputStream(), true);

                out.println("Enter a message");
                boolean done = false;
                while (!done && in.hasNextLine())
                {
                    String line = in.nextLine();

                    out.println("echo"  + line);
                    System.out.println(server.getRemoteSocketAddress() + " entered " + line);

                    if (line.equals("exit"))
                        done = true;
                }

                System.out.println("goobye");

                server.close();
            }
            catch (SocketTimeoutException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }

    public void start()
    {
        if (thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }

}
