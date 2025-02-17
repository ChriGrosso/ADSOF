package orders;

public class InStoreOrder extends Order {
    private BookStore store;

    public InStoreOrder(String item, double price, String customer, BookStore store) {
        super(item, price, customer);
        this.store = store;
    }

    @Override
    public double totalPrice() {
        double storeDiscount = switch (store) {
            case BROOKLYN -> 1.0;
            case NEWARK -> 2.0;
            default -> 0.0;
        };
        return super.totalPrice() - storeDiscount;
    }

    @Override
    public String toString() {
        return super.toString() + "\nStore: " + this.store;
    }
}
