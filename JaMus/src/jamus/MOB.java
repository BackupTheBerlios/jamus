package galaxyjumper;

import org.w3c.dom.*;

public class MOB extends MudObject
{
  public MOB(Element node)
  {
    id = "M"+node.getAttribute("id");
    name = node.getElementsByTagName("Name").item(0).getFirstChild().getNodeValue();
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
    type = Constants.MOB;
    nl = node.getElementsByTagName("Skills");
    attackskill = Float.parseFloat(((Element)nl.item(0)).getAttribute("attack"))/100;
    defenseskill = Float.parseFloat(((Element)nl.item(0)).getAttribute("defense"))/100;
    nl = node.getElementsByTagName("Attack");
    minattack = Integer.parseInt(((Element)nl.item(0)).getAttribute("number"))+Integer.parseInt(((Element)nl.item(0)).getAttribute("const"));
    maxattack = Integer.parseInt(((Element)nl.item(0)).getAttribute("number"))*Integer.parseInt(((Element)nl.item(0)).getAttribute("amount"))+Integer.parseInt(((Element)nl.item(0)).getAttribute("const"));
package jamus;    nl = node.getElementsByTagName("Defense");
    mindefense = Integer.parseInt(((Element)nl.item(0)).getAttribute("number"))+Integer.parseInt(((Element)nl.item(0)).getAttribute("const"));
    maxdefense = Integer.parseInt(((Element)nl.item(0)).getAttribute("number"))*Integer.parseInt(((Element)nl.item(0)).getAttribute("amount"))+Integer.parseInt(((Element)nl.item(0)).getAttribute("const"));
  }
}