package jamus;

public class ItemObject extends Instance
{
  public ItemObject(Enviroment enviroment, String type)
  {
    this.type = type;
    if (enviroment.getObject(type).getType()==Constants.ITEM_FAKE)
      age = Constants.ITEM_NOAGE;
    else
      age = Constants.ITEM_AGE_NEW;
    wield = false;
  }

  public String getAge()
  {
    if (age==Constants.ITEM_AGE_NEW)
      return "\nAlter: neu";
    else
      return "";
  }
}