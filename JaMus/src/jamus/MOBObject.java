package jamus;

public class MOBObject extends Instance
{
  public MOBObject(Enviroment enviroment, String type)
  {
    this.type = type;
    age = Constants.ITEM_NOAGE;
    wield = false;
    attackskill = enviroment.getObject(type).attackskill;
    defenseskill = enviroment.getObject(type).defenseskill;
  }

  public String getAge()
  {
      return "";
  }

  public float getAttackSkill()
  {
    return attackskill;
  }

  public float getDefenseSkill()
  {
    return defenseskill;
  }
}