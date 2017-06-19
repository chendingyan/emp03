package emp.DAO;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import emp.model.Dep;
import emp.model.Emp;
import emp.model.empException;
import emp.util.XMLUtil;

public class EmpDao {
	private Document ed;
	private DepDao depdao;
	public EmpDao() {
		ed = XMLUtil.getEmpDocument();
		depdao = new DepDao();
	}
	
	private void write(){
		XMLUtil.write2XML(ed, "emps");
	}
	
	private Element loadElementById(int id){
		String path = "/emps/emp[id = '"+id + "']";
		Element e = (Element) ed.selectSingleNode(path);
		return e;
	}
	
	private Emp Element2Emp(Element e) {
		if(e==null) return null;
		Emp emp = new Emp();
		emp.setId(Integer.parseInt(e.elementText("id")));
		emp.setName(e.elementText("name"));
		emp.setSex(e.elementText("sex"));
		emp.setSalary(Double.parseDouble(e.elementText("salary")));
		emp.setDepId(Integer.parseInt(e.elementText ("depId")));
		return emp;
	}
	
	public void add(Emp emp, int depId){
		//检测部门是否存在
		Dep d =depdao.load(depId);
		if(d==null) throw new empException("The department doesn't exists");
		Element root = ed.getRootElement();
		Element ee = root.addElement("emp"); 
		String path = "/emps/emp/id";
		ee.addElement("id").setText(String.valueOf(XMLUtil.getMaxId(ed, path)+1));
		ee.addElement("name").setText(emp.getName());
		ee.addElement("sex").setText(emp.getSex());
		ee.addElement("salary").setText(String.valueOf(emp.getSalary()));
		ee.addElement("depId").setText(String.valueOf(depId));
		write();
	}
	
	public void delete(int id){
		Element e = loadElementById(id);
		if(e==null) throw new empException("there is no "+id+" employee");
		Element root = ed.getRootElement();
		root.remove(e);
		write();
	}
	
	public void update(Emp emp, int depId){
		Dep d =depdao.load(depId);
		if(d==null) throw new empException("The department doesn't exists"); 
		Element e = loadElementById(emp.getId());
		e.element("name").setText(emp.getName());
		e.element("sex").setText(emp.getSex());
		e.element("salary").setText(String.valueOf(emp.getSalary()));
		e.element("depId").setText(String.valueOf(depId));
		write();
	} 
	
	public Emp load(int id){
		Element e = loadElementById(id);
		if(e==null) return null;
		Emp emp = Element2Emp(e);
		return emp;
	}
	
	public List<Emp> list(){ 
		String path="/emps/emp"; 
		List<Element> list = ed.selectNodes(path);
		List<Emp> dlist = new ArrayList<Emp>();
		for(Element e: list){
			Emp emp = Element2Emp(e);
			dlist.add(emp);
		}
			return dlist;
	}
	
	public List<Emp> list(int depId, String name){
		if(depId == 0 && "".equals(name.trim())){
			return list();
		}
		String path="/emps/emp["; 
		if(depId >0 ){
			path+="depId="+depId;
			if(name != null &&!"".equals(name.trim())){
				path+=" and contains(name,'"+name+"')";
			}
		}else{
			if(name != null &&!"".equals(name.trim())){
				path+="contains(name,'"+name+"')";
			}
		}
		path+="]";
		List<Element> list = ed.selectNodes(path);
		List<Emp> dlist = new ArrayList<Emp>();
		for(Element e: list){
			Emp emp = Element2Emp(e);
			dlist.add(emp);
		}
			return dlist;
	}
	
}
