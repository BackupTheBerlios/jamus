package jamus;

import java.util.*;

public class Player
{
  private String name;
  private int gender;
  private Enviroment enviroment;
  private Integer roomId;
  private Vector items;

  public Player(Enviroment env, String n, int g)
  {
    items = new Vector();
    enviroment = env;
    name = n;
    gender = g;
  }

  public void setRoomId(String id)
  {
    roomId = new Integer(id);
  }

  public Integer getRoomId()
  {
    return roomId;
  }

  public void add(Instance item)
  {
    items.add(item);
  }

  public String getDescription()
  {
    String descr = "Du traegst bei Dir:";
    MudObject mo;
    for (int i=0; i<items.size(); i++)
    {
      mo = enviroment.getObject(((Instance)items.elementAt(i)).getType());
      descr += ("\n  "+Constants.AMOUNT_VALUE[mo.getGender()]+" "+mo.getName());
      if (((Instance)items.elementAt(i)).getWield())
        descr += " [gefuehrt]";
    }
    return descr;
  }

  public Instance hasPlayerObject(String obj)
  {
    MudObject mo;
    for (int i=0; i<items.size(); i++)
    {
      mo = enviroment.getObject(((Instance)items.elementAt(i)).getType());
      if (mo.checkName(obj))
        return (Instance)items.elementAt(i);
    }
    return null;
  }
}