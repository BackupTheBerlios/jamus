package jamus;

import java.util.*;

public abstract class MudObject
{
  protected String id;
  protected String name;
  protected Vector shorts = new Vector();
  protected String description;
  protected int type;
  protected int gender;
  protected float attackskill;
  protected float defenseskill;
  protected int minattack, maxattack;
  protected int mindefense, maxdefense;

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
  }

  public int getType()
  {
    return type;
  }

  public int getGender()
  {
    return gender;
  }

  public boolean checkName(String obj)
  {
    if (name.toLowerCase().equals(obj.toLowerCase()))
      return true;
    for (int i=0; i<shorts.size(); i++)
      if (((String)shorts.elementAt(i)).toLowerCase().equals(obj.toLowerCase()))
        return true;
    return false;
  }

  public int attackValue()
  {
    Random r = new Random();
    return r.nextInt(maxattack-minattack)+minattack;
  }

  public int defenseValue()
  {
    Random r = new Random();
    return r.nextInt(maxdefense-mindefense)+mindefense;
  }
}