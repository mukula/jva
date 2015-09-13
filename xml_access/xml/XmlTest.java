import java.util.*;
import java.io.*;
import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.StringProperty;
class XmlData 
{
public String name;
XmlData(){}
XmlData(String name)
{
this.name=name;
}
public String get()
{
return this.name;
}
public XmlData getRef()
{
return this;
}
}
public class XmlTest{
public XmlDataWrapper wrapper;
public List<XmlData> xmlDataList=new ArrayList<>();
public void loadXmlDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(XmlDataWrapper.class);
        Unmarshaller um = context.createUnmarshaller();
        wrapper = (XmlDataWrapper) um.unmarshal(file);
        System.out.println(wrapper.getXmlDataList().get(0).get());
        } catch (Exception e) {System.out.println(e);
       
    }
}
public void saveXmlDataToFile(File file) {

xmlDataList.add(new XmlData("java").getRef());
xmlDataList.add(new XmlData("javascript").getRef());
xmlDataList.add(new XmlData("javabeans").getRef());
    try {
        JAXBContext context = JAXBContext.newInstance(XmlDataWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         wrapper = new XmlDataWrapper();
        wrapper.setXmlDataList(xmlDataList);
        m.marshal(wrapper, file);
    } catch (Exception e) { // catches ANY exception
       
    }
}
public static void main(String bs[])
{
XmlTest t=new XmlTest();
//t.saveXmlDataToFile(new File("C:\\java\\xml\\xmlfile.xml"));
t.loadXmlDataFromFile(new File("C:\\java\\xml\\xmlfile.xml"));
  System.out.println(t.wrapper.getXmlDataList().get(1).get());
}
}