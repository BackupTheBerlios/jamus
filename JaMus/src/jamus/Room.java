package jamus;

import org.w3c.dom.*;
import java.util.*;

public class Room
{
  //Room id
  private int id;
  //Room description
  private String description;
  //0-up, 1-down, 2-northwest, 3-north, 4-northeast, 5-west,
  //6-east, 7-southwest, 8-south, 9-southeast
  private int[] exits = new int[10];
  //Items in the room
  private Vector objects = null;
  private Enviroment enviroment;

  public Room(Enviroment ev, Element node)
  {
    enviroment = ev;

    NodeList nl;

    id = Integer.parseInt(node.getAttribute("id"));
    description = node.getElementsByTagName("Description").item(0).getFirstChild().getNodeValue();
    //Up
    nl = node.getElementsByTagName("Up");
    if (nl.getLength()!=0)
      exits[Constants.UP] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.UP] = -1;
    //Down
    nl = node.getElementsByTagName("Down");
    if (nl.getLength()!=0)
      exits[Constants.DOWN] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.DOWN] = -1;
    //Northwest
    nl = node.getElementsByTagName("Northwest");
    if (nl.getLength()!=0)
      exits[Constants.NORTHWEST] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.NORTHWEST] = -1;
    //North
    nl = node.getElementsByTagName("North");
    if (nl.getLength()!=0)
      exits[Constants.NORTH] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.NORTH] = -1;
    //Northeast
    nl = node.getElementsByTagName("Northeast");
    if (nl.getLength()!=0)
      exits[Constants.NORTHEAST] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.NORTHEAST] = -1;
    //West
    nl = node.getElementsByTagName("West");
    if (nl.getLength()!=0)
      exits[Constants.WEST] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.WEST] = -1;
    //East
    nl = node.getElementsByTagName("East");
    if (nl.getLength()!=0)
      exits[Constants.EAST] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.EAST] = -1;
    //Southwest
    nl = node.getElementsByTagName("Southwest");
    if (nl.getLength()!=0)
      exits[Constants.SOUTHWEST] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.SOUTHWEST] = -1;
    //South
    nl = node.getElementsByTagName("South");
    if (nl.getLength()!=0)
      exits[Constants.SOUTH] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.SOUTH] = -1;
    //Southeast
    nl = node.getElementsByTagName("Southeast");
    if (nl.getLength()!=0)
      exits[Constants.SOUTHEAST] = Integer.parseInt(nl.item(0).getFirstChild().getNodeValue());
    else
      exits[Constants.SOUTHEAST] = -1;
    //Items
    if ((node.getElementsByTagName("Items")!=null) || (node.getElementsByTagName("MOBs")!=null))
    {
      objects = new Vector();
      nl = node.getElementsByTagName("Item");
      for (int i=0; i<nl.getLength(); i++)
        objects.add(new ItemObject(enviroment, new String("I"+((Element)nl.item(i)).getAttribute("id"))));
      nl = node.getElementsByTagName("MOB");
      for (int i=0; i<nl.getLength(); i++)
        objects.add(new MOBObject(enviroment, new String("M"+((Element)nl.item(i)).getAttribute("id"))));
    }
  }

  public String getDescription()
  {
    return description;
  }

  public String getCompleteDescription()
  {
    String desc = "  "+description+"\n"+"Ausgaenge: ";
    boolean first = true;
    for (int i=0; i<10; i++)
      if (exits[i]!=-1)
        if (first)
        {
          desc += Constants.EXIT_STRINGS[i];
          first = false;
        }
        else
          desc += ", "+Constants.EXIT_STRINGS[i];
    MudObject mo;
    for (int i=0; i<objects.size(); i++)
    {
      mo = enviroment.getObject(((Instance)objects.elementAt(i)).getType());
      desc += "\n"+Constants.AMOUNT_VALUE[mo.getGender()]+" "+mo.getName();
    }
    return desc;
  }

  public int getExit(int direction)
  {
    return exits[direction];
  }

  public Instance isObjectInRoom(String obj)
  {
    MudObject mo;
    for (int i=0; i<objects.size(); i++)
    {
      mo = enviroment.getObject(((Instance)objects.elementAt(i)).getType());
      if (mo.checkName(obj))
        return (Instance)objects.elementAt(i);
    }
    return null;
  }

  public void remove(Instance instance)
  {
    objects.remove(instance);
  }
}