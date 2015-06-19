/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class to handle variables of connected clients
 * @author Marcel
 */
public class Player
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String nickname;

    public Player(ObjectOutputStream oos, ObjectInputStream ois, String nickname)
    {
        this.oos = oos;
        this.ois = ois;
        this.nickname = nickname;
    }

    public ObjectOutputStream getOos()
    {
        return oos;
    }

    public void setOos(ObjectOutputStream oos)
    {
        this.oos = oos;
    }

    public ObjectInputStream getOis()
    {
        return ois;
    }

    public void setOis(ObjectInputStream ois)
    {
        this.ois = ois;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
    
    public void closePlayer() throws IOException
    {
        oos.close();
        ois.close();
    }
}
