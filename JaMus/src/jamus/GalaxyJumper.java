package jamus;

import java.net.*;
import java.io.*;
import java.util.*;

public class GalaxyJumper
{
  private ServerSocket server;
  private Socket client;
  private BufferedReader in;
  private PrintWriter out;
  private Enviroment enviroment;
  private CommandParser command;

  private Properties properties;

  public GalaxyJumper()
  {
    String line;

    properties = new Properties();
    try
    {
      properties.load(new FileInputStream("gj.ini"));
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }

    //Create the enviroment
    enviroment = new Enviroment(properties);

    //Generate command parser
    command = new CommandParser(enviroment);

    try
    {
/*      server = new ServerSocket(2233);
      client = server.accept();
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new PrintWriter(client.getOutputStream(), true);*/
      System.out.println(command.parse("s"));
      System.out.println(command.parse("betrachte mich"));
      System.out.println(command.parse("fuehre schwert"));
      System.out.println(command.parse("nimm schwert"));
      System.out.println(command.parse("betrachte mich"));
      System.out.println(command.parse("schau"));
      System.out.println(command.parse("fuehre schwert"));
      System.out.println(command.parse("betrachte mich"));
//      out.println(room.getCompleteDescription());
/*      while (!(line = in.readLine()).toLowerCase().equals("ende"))
      {
        System.out.println(line);
      }
      out.flush();
      out.close();
      in.close();
      client.close();
      server.close();*/
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    new GalaxyJumper();
  }
}