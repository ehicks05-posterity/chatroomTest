package com.steven.hicks;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient
{
//    public static void main(String[] args)
//    {
    public void chat()
    {
        try
        {
            Scanner chat = new Scanner(System.in);
            String serverName = "127.0.0.1";
            int serverPort = 8585;

            System.out.println("Connecting to " + serverName + " on port " + serverPort);
            Socket server = new Socket(serverName, serverPort);

            OutputStream outToServer = server.getOutputStream();
            PrintWriter toServer = new PrintWriter(outToServer, true);

            InputStream inFromServer = server.getInputStream();
            Scanner fromServer = new Scanner(inFromServer);

            while (fromServer.hasNext())
            {
                System.out.println(fromServer.nextLine());

                String message = chat.nextLine();

                if (message.length() > 0)
                    toServer.println(message);
            }

            server.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
//    }

}
