package jamus;

import java.util.*;

public class CommandParser
{
  private String command;
  private Vector arguments;
  private Enviroment enviroment;

  public CommandParser(Enviroment ev)
  {
    enviroment = ev;
  }

  public String parse(String commandline)
  {
    arguments = new Vector();

    StringTokenizer st = new StringTokenizer(commandline);
    if (st.hasMoreTokens())
      command = st.nextToken();
    while (st.hasMoreTokens())
      arguments.add(st.nextToken());
    if (command.toLowerCase().equals("hoch"))
      return doMove(Constants.UP);
    else if (command.toLowerCase().equals("runter"))
      return doMove(Constants.DOWN);
    else if (command.toLowerCase().equals("nw") ||
             command.toLowerCase().equals("nordwesten"))
      return doMove(Constants.NORTHWEST);
    else if (command.toLowerCase().equals("n") ||
             command.toLowerCase().equals("norden"))
      return doMove(Constants.NORTH);
    else if (command.toLowerCase().equals("no") ||
             command.toLowerCase().equals("nordosten"))
      return doMove(Constants.NORTHEAST);
    else if (command.toLowerCase().equals("w") ||
             command.toLowerCase().equals("westen"))
      return doMove(Constants.WEST);
    else if (command.toLowerCase().equals("o") ||
             command.toLowerCase().equals("osten"))
      return doMove(Constants.EAST);
    else if (command.toLowerCase().equals("sw") ||
             command.toLowerCase().equals("suedwesten"))
      return doMove(Constants.SOUTHWEST);
    else if (command.toLowerCase().equals("s") ||
             command.toLowerCase().equals("sueden"))
      return doMove(Constants.SOUTH);
    else if (command.toLowerCase().equals("so") ||
             command.toLowerCase().equals("suedosten"))
      return doMove(Constants.SOUTHEAST);
    else if ((command.toLowerCase().equals("schau")) || (command.toLowerCase().equals("betrachte")))
      return doLook();
    else if (command.toLowerCase().equals("nimm"))
      return doGet();
    else if (command.toLowerCase().equals("fuehre"))
      return doWield();
    else if (command.toLowerCase().equals("toete"))
      return doKill();
    return "Was willst Du mit '"+commandline+"' sagen?";
  }

  private String doMove(int direction)
  {
    int roomId=-1;
    roomId = enviroment.getRoomId(enviroment.getPlayer().getRoomId(), direction);
    if (roomId==-1)
      return "In dieser Richtung gibt es keinen Ausgang.";
    else
    {
      enviroment.getPlayer().setRoomId(Integer.toString(roomId));
      return doLook();
    }
  }

  private String doLook()
  {
    if (arguments.size()==0)
      return (enviroment.getRoom(enviroment.getPlayer().getRoomId())).getCompleteDescription();
    else if ((((String)arguments.elementAt(0)).toLowerCase().equals("ich")) ||
             (((String)arguments.elementAt(0)).toLowerCase().equals("mich")))
    {
      return enviroment.getPlayer().getDescription();
    }
    else
    {
      Room room = enviroment.getRoom(enviroment.getPlayer().getRoomId());
      Instance instance = room.isObjectInRoom((String)arguments.elementAt(0));
      if (instance!=null)
        return enviroment.getObject(instance.getType()).getDescription()+instance.getAge();
      else
        return "Hier gibt es soetwas nicht.";
    }
  }

  private String doGet()
  {
    if (arguments.size()!=0)
    {
      Room room = enviroment.getRoom(enviroment.getPlayer().getRoomId());
      Instance instance = room.isObjectInRoom((String)arguments.elementAt(0));
      if (instance!=null)
      {
        MudObject mo = enviroment.getObject(instance.getType());
        room.remove(instance);
        enviroment.getPlayer().add(instance);
        return "Du nimmst "+Constants.AMOUNT_VALUE[mo.getGender()].toLowerCase()+" "+mo.getName()+".";
      }
      else
        return "Hier gibt es soetwas nicht.";
    }
    else
      return "Was willst Du nehmen?";
  }

  public String doWield()
  {
    if (arguments.size()!=0)
    {
      Instance instance = enviroment.getPlayer().hasPlayerObject((String)arguments.elementAt(0));
      if (instance!=null)
      {
        MudObject mo = enviroment.getObject(instance.getType());
        if (mo.getType()==Constants.ITEM_WEAPON)
        {
          if (instance.getWield())
            return "Du fuehrst "+Constants.AMOUNT_VALUE[mo.getGender()].toLowerCase()+" "+mo.getName()+"schon.";
          else
          {
            instance.setWield();
            return "Du fuehrst "+Constants.AMOUNT_VALUE[mo.getGender()].toLowerCase()+" "+mo.getName()+".";
          }
        }
        else
          return "Das ist keine Waffe.";
      }
      else
        return "Du hast soetwas nicht.";
    }
    else
      return "Was willst Du den fuehren?";
  }

  private String doKill()
  {
    if (arguments.size()!=0)
    {
      Instance instance = enviroment.getPlayer().hasPlayerObject((String)arguments.elementAt(0));
      if (instance!=null)
        if (enviroment.getObject(instance.getType()).getType()!=Constants.MOB)
        {
          return "";
        }
        else
          return "Das kannst Du nicht toeten.";
      else
        return "Soetwas gibt es hier nicht.";
    }
    else
      return "Was moechtest Du toeten?";
  }
}