public class CarNode {
    protected Car data;
    protected CarNode next;

    public CarNode() {
    }

    public CarNode(Car data) {
        this.data = data;
    }
    public CarNode(Car data, CarNode next) {
        this.data = data;
        this.next = next;
    }
}