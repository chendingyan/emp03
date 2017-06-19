package emp.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtil {
	private static Document UserDocument;
	private static Document DepDocument;
	private static Document EmpDocument;
	
	public static Document getUserDocument(){
		if(UserDocument!=null) return UserDocument;
		try {
			SAXReader reader = new SAXReader();
			UserDocument = reader.read(XMLUtil.class.getClassLoader().getResourceAsStream("xml/users.xml"));
			return UserDocument;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Document getDepDocument(){
		if(DepDocument!=null) return DepDocument;
		try {
			SAXReader reader = new SAXReader();
			DepDocument = reader.read(XMLUtil.class.getClassLoader().getResourceAsStream("xml/deps.xml"));
			return DepDocument;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Document getEmpDocument(){
		if(EmpDocument!=null) return EmpDocument;
		try {
			SAXReader reader = new SAXReader();
			EmpDocument = reader.read(XMLUtil.class.getClassLoader().getResourceAsStream("xml/emps.xml"));
			return EmpDocument;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void write2XML(Document d, String name){
		XMLWriter out = null;
		try {
			String path = XMLUtil.class.getClassLoader().getResource("xml/"+name+".xml").getPath().replace("bin", "src");
			 out = new XMLWriter(new FileWriter(path), OutputFormat.createPrettyPrint());
			out.write(d);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(out!=null) out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static int getMaxId(Document d,String path){
		List<Element> eids = d .selectNodes(path);
		if(eids == null||eids.size()==0){
			return 0;
		}
		List<Integer> ids = new ArrayList<Integer>();
		for(Element e:eids){
			if(e.getTextTrim()==null || "".equals(e.getTextTrim())) continue;
			ids.add(Integer.parseInt(e.getText()));
		}
		if(ids.size()<=0) return 0;
		Collections.sort(ids);
		return ids.get(ids.size()-1);
	}
	
}
