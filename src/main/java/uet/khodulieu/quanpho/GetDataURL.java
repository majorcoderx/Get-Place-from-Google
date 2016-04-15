package uet.khodulieu.quanpho;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * For stored database subbject
 * @author Nguyễn Văn Đô K57CB
 * @Date 2016/04/14
 *
 */

public class GetDataURL {

	public static String headURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location"
			+ "=xx.xxxxxx,xxx.xxxxxx&radius=";
	public static String distURL = "&types=food&name=";
	public static String keyURL = "&key=xxxxxxxxxxxxxxxxxxxxxx";

	public static String[] argsUrl = { "phở+hà+nội", "phở+nam+định", "phở+huế", "bún+huế", "phở+sài+gòn",
			"phở+gia+truyền", "phở+ngon", "bún+hà+nội", "bún+bình+dân", "phở+bình+dân", "phở+lý+quốc+sư",
			"phở+gà", "phở+bò", "bún+bò+huế", "bún+hà+nội", "bún+mọc", "bún+bò",
			"phở+cá", "bún+cá", "bún+chả", "bún+chả+mắm+tôm", "quán+ăn", "phở", "phở+cuốn", "phở+tây+hồ",
			"phở+xào", "phở+trộn", "phở+khô", "quán+phở" };
	
	public static void main(String[] args) {
		GetDataURL getDataURL = new GetDataURL();
		
		int distance = 500;
		
		for(int i = 0 ;i < 80; ++i) {
			for(int j = 0 ;j < argsUrl.length; ++j) {
				String url = headURL + distance + distURL + argsUrl[j] + keyURL;
				try {
					getDataURL.readXMLFromUrl(url);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
			distance+=500;
		}
		ConnectDb.getConn().close();
	}
	
	public void readXMLFromUrl(String url) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory fBuilderFactory = 
                 DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = fBuilderFactory.newDocumentBuilder();
         Document document = builder.parse(url);

         document.getDocumentElement().normalize();
    
         // loop through each item
         NodeList items = document.getElementsByTagName("result");
         for (int i = 0; i < items.getLength(); i++)
         {
             Node node = items.item(i);
             if (node.getNodeType() != Node.ELEMENT_NODE)
                 continue;
             Element element = (Element) node;

             // get the "title elem" in this item (only one)
             //NodeList nameList = element.getElementsByTagName("name");
             Element nameElem = (Element) element.getElementsByTagName("name").item(0);
             Element addressElem = (Element) element.getElementsByTagName("vicinity").item(0);
             Element latElem = (Element) element.getElementsByTagName("lat").item(0);
             Element lngElem = (Element) element.getElementsByTagName("lng").item(0);
             
             // get the "text node" in the title (only one)
             Node nameNode = nameElem.getChildNodes().item(0);
             Node addressNode = addressElem.getChildNodes().item(0);
             Node latNode = latElem.getChildNodes().item(0);
             Node lngNode = lngElem.getChildNodes().item(0);
             
             ConnectDb.getConn().insertToDB(new QuanPho(nameNode.getNodeValue(), addressNode.getNodeValue(),
            		 latNode.getNodeValue(), lngNode.getNodeValue()));
         }
	}
}
