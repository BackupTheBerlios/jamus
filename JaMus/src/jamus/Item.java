package jamus;

import org.w3c.dom.*;

public class Item extends MudObject
{
  public Item(Element node)
  {
    id = "I"+node.getAttribute("id");
    name = node.getAttribute("name");
    NodeList nl = node.getElementsByTagName("Shortname");
    for (int i=0; i<nl.getLength(); i++)
      shorts.add(nl.item(i).getFirstChild().getNodeValue());
    description = node.getElementsByTagName("Description").item(0).getFirstChild().getNodeValue();
    if (node.getAttribute("gender").toLowerCase().equals("male"))
      gender = Constants.MALE;
    else if (node.getAttribute("gender").toLowerCase().equals("female"))
      gender = Constants.FEMALE;
    else
      gender = Constants.NEUTRUM;
    if (node.getAttribute("type").toLowerCase().equals("weapon"))
      type = Constants.ITEM_WEAPON;
    else
      type = Constants.ITEM_FAKE;
  }
}
