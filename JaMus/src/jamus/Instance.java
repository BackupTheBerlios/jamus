package jamus;

public abstract class Instance
{
  protected String type;
  protected int age;
  protected boolean wield;
  protected float attackskill;
  protected float defenseskill;

  public String getType()
  {
    return type;
  }

  public abstract String getAge();

  public boolean getWield()
  {
    return wield;
  }

  public void setWield()
  {
    wield = true;
  }
}
