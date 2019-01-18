package automationExam.utils;

public class EbayResultObject{
	private String name;
	private double price;
	private double shipping;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getShipping() {
		return shipping;
	}
	
	public double getTotalPrice() {
		return price + shipping;
	}
	
	public void setShipping(double shipping) {
		this.shipping = shipping;
	}	
	
	public EbayResultObject(String name, double price, double shipping) {
		super();
		this.name = name;
		this.price = price;
		this.shipping = shipping;
	}
	
	/*
	 @Override
	  public int compareTo(EbayResultObject ero) {
	    if (getName() == null || ero.getName() == null) {
	      return 0;
	    }
	    return getName().compareTo(ero.getName());
	  }*/
}
