package jamus;

import java.util.*;
import java.io.*;
//XML
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Enviroment
{
  private Properties properties;
  private Hashtable rooms = new Hashtable();
  private Hashtable items = new Hashtable();
  private Hashtable mobs = new Hashtable();
  private Player player;

  public Enviroment(Properties p)
  {
    player = new Player(this, "Ralph", Constants.MALE);
    properties = p;
    loadItems();
    loadMobs();
    loadRooms();
    player.setRoomId(properties.getProperty("START_ROOM"));
  }

  private void loadRooms()
  {
    File f = new File(properties.getProperty("ROOM_FILE"));

    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(f);
      NodeList list = doc.getElementsByTagName("Room");
      Element element;
      for (int i=0; i<list.getLength(); i++)
      {
        element = (Element)list.item(i);
        rooms.put(new Integer(element.getAttribute("id")), new Room(this, element));
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public Room getRoom(Integer key)
  {
    return (Room)rooms.get(key);
  }

  public int getRoomId(Integer key, int direction)
  {
    return getRoom(key).getExit(direction);
  }

  public Player getPlayer()
  {
    return player;
  }

  private void loadItems()
  {
    File f = new File(properties.getProperty("ITEM_FILE"));

    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(f);
      NodeList list = doc.getElementsByTagName("Item");
      Element element;
      for (int i=0; i<list.getLength(); i++)
      {
        element = (Element)list.item(i);
        items.put(new String("I"+element.getAttribute("id")), new Item(element));
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private void loadMobs()
  {
    File f = new File(properties.getProperty("MOB_FILE"));

    try
    {
      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document doc = builder.parse(f);
      NodeList list = doc.getElementsByTagName("MOB");
      Element element;
      for (int i=0; i<list.getLength(); i++)
      {
        element = (Element)list.item(i);
        mobs.put(new String("M"+element.getAttribute("id")), new MOB(element));
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public MudObject getObject(String key)
  {
    if (key.substring(0, 1).equals("I"))
      return (MudObject)items.get(key);
    else
      return (MudObject)mobs.get(key);
  }
}