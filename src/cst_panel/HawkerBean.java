package cst_panel;

public class HawkerBean {
	public String hname;
	String mobile;
	String alloareas;
	String doj;
	String em;
		
	public HawkerBean(String hname, String mobile, String alloareas, String doj, String em) {
		super();
		System.out.println("bean->"+alloareas);
		this.hname = hname;
		this.mobile = mobile;
		this.alloareas = alloareas;
		this.em=em;
		this.doj = doj;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAlloareas() {
		return alloareas;
	}
	public void setAlloareas(String alloareas) {
		this.alloareas = alloareas;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getEm() {
		return em;
	}
	public void setEm(String em) {
		this.em = em;
	}
	
	
	
}
