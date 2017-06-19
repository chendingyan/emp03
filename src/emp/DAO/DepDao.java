package emp.DAO;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import emp.model.Dep;
import emp.model.empException;
import emp.util.XMLUtil;

public class DepDao {
	private Document dd;
	private Document ed;
	
	public DepDao() {
		dd = XMLUtil.getDepDocument();
		ed = XMLUtil.getEmpDocument();
	}
	
	public int getEdNum(int depId){
		String path ="/emps/emp[depId="+depId+"]";
		List<Element> list = ed.selectNodes(path);
		if(list ==null) return 0;
		return list.size();
	}
	
	private void write(){
		XMLUtil.write2XML(dd, "deps");
	}
	
	private Element loadElementById(int id){
		String path = "/deps/dep[id = '"+id + "']";
		Element e = (Element) dd.selectSingleNode(path);
		return e;
	}
	
	private Dep Element2Dep(Element e) {
		if(e==null) return null;
		Dep d = new Dep();
		d.setId(Integer.parseInt(e.elementText("id")));
		d.setName(e.elementText("name"));
		return d;
	}
	
	public void add(Dep dep){
		Dep d = load(dep.getId());
		if(d!=null) throw new empException("The department is already exists");
		Element root = dd.getRootElement();
		Element de = root.addElement("dep");
		String path = "/deps/dep/id";
		de.addElement("id").addText(String.valueOf(XMLUtil.getMaxId(dd, path)+1));
		de.addElement("name").addText(dep.getName());
		write();
	}
	
	
	public void delete(int id){
		if (getEdNum(id)>0) throw new empException("There are employees in this department");
		Element e = loadElementById(id);
		if(e==null) throw new empException("There is no "+id+" Department");
		Element root = dd.getRootElement();
		root.remove(e);
		write();
	}
	
	public void update(Dep dep){
		Element e = loadElementById(dep.getId());
		if(e==null) throw new empException("The department doesn't exists");
		e.element("name").setText(dep.getName());
		write();
	}
	
	public Dep load(int id){
		Element e = loadElementById(id);
		if(e==null) return null;
		Dep d = Element2Dep(e);
		return d;
	}

	public List<Dep> list(){
		String path ="/deps/dep";
		List<Element> list = dd.selectNodes(path);
		List<Dep> dlist = new ArrayList<Dep>();
		for(Element e: list){
			Dep d = Element2Dep(e);
			dlist.add(d);
		}
		return dlist;
	}
}
