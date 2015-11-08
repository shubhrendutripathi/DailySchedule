/**
* The MIT License (MIT)
* 
* Copyright (c) 2015 Shubhrendu Tripathi
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
* 
**/

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMLRead {
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	NodeList nList;
	List<Task> tasks = new ArrayList<Task>();
	Task task = null;
	
	public XMLRead(String strPath) throws ParserConfigurationException, SAXException, IOException{
		//Get Document Builder
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		 
		//Build Document
		document = builder.parse(new File(strPath));
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
	}
	
	public void getTasks(){
		//Get all task
		nList = document.getElementsByTagName("task");
		
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 System.out.println("");    //Just a separator
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    //Print each employee's detail
		    Element eElement = (Element) node;

            task = new Task();
            task.setId(Integer.parseInt(eElement.getAttribute("id")));
            task.setHour(Integer.parseInt(eElement.getElementsByTagName("hour").item(0).getTextContent()));
            task.setMinute(Integer.parseInt(eElement.getElementsByTagName("minute").item(0).getTextContent()));
            task.setSecond(Integer.parseInt(eElement.getElementsByTagName("second").item(0).getTextContent()));
            task.setAM(Boolean.parseBoolean((eElement.getElementsByTagName("am").item(0).getTextContent())));
            task.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
            task.setChecked(Boolean.parseBoolean((eElement.getElementsByTagName("checked").item(0).getTextContent())));
            
            System.out.println(task);
            
            tasks.add(task);
		 }
		}
	}
}
