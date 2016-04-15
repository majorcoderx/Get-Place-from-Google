package uet.khodulieu.quanpho;

/**
 * For stored database subbject
 * @author Nguyễn Văn Đô
 * @Date 2016/04/14
 *
 */

public class QuanPho {

	private String name;
	private String address;
	private String xLoc;
	private String yLoc;
	
	public QuanPho() {
		super();
	}

	public QuanPho(String name, String address, String xLoc, String yLoc) {
		super();
		this.name = name;
		this.address = address;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	
	@Override
	public String toString() {
		return "name: " + this.name + " have a address: " + this.address + " at: "
					+ this.xLoc + "," + this.yLoc;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getxLoc() {
		return xLoc;
	}
	
	public void setxLoc(String xLoc) {
		this.xLoc = xLoc;
	}
	
	public String getyLoc() {
		return yLoc;
	}
	
	public void setyLoc(String yLoc) {
		this.yLoc = yLoc;
	}
}
