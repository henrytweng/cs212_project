public abstract class CarList {
    protected CarNode first;
    protected CarNode last;
    protected int length;

    public CarList() { 
        first = new CarNode();
        last = first;
        length = 0;
    }
    public int getLength() {
        return length;
    }
    public void append(Car c) {
        CarNode newNode = new CarNode(c);
        last.next = newNode;
        last = newNode;
        length++;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();     
        for (CarNode i = first.next; i != null; i = i.next)
            sb.append(i.data.toString() + "\n");
        return sb.toString();
    }
}