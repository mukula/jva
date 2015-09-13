import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XmlDataTree")
public class XmlDataWrapper {

    private List<XmlData> xmlData;

    @XmlElement(name = "XmlData")
    public List<XmlData> getXmlDataList() {
        return xmlData;
    }

    public void setXmlDataList(List<XmlData> xmlData) {
        this.xmlData = xmlData;
    }
}