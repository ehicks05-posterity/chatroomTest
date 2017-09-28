package com.steven.hicks;

import java.io.IOException;

/*
* This is just to start the chatroom server on localhost, post 8585
* */
public class ServerStart
{
    public static void main(String[] args)
    {
        try
        {
            ChatServer server = new ChatServer(8585);
            server.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ChatClient client = new ChatClient();
        client.chat();

    }
}
