package orders;

public class OnlineOrder extends Order {
    private String email;

    public OnlineOrder(String item, double price, String customer, String email) {
        super(item, price, customer);
        this.email = email;
    }

    @Override
    public double totalPrice() {
        return super.totalPrice() + 5.0; 
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmail: " + email;
    }
}
