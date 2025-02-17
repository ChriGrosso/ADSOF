package orders;

public abstract class Order {
    private String item;
    private double basePrice;
    private String customer;
    private double discount = 0.0; 

    public Order(String item, double price, String customer) {
        this.item = item;
        this.basePrice = price;
        this.customer = customer;
    }
	
	 public void setBasePrice(double price) { 
        this.basePrice = price;
    }

    public void setDiscount(double discount) {
        if (discount < 0.0 || discount > 100.0) {
            System.out.println("Invalid discount percentage!");
            return;
        }
        this.discount = discount;
    }
	
	public double totalPrice() {
        return basePrice * (1 - discount / 100.0); 
    }

    @Override
    public String toString() {
        return "Order of '" + this.item + "' for " + this.customer + " (" + this.totalPrice() + "$)";
    }

}
