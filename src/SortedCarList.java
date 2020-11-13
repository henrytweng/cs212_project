public class SortedCarList extends CarList {
    public SortedCarList () {
        super();
    }
    
    public void add(Car c) {
        CarNode newNode = new CarNode(c);
        CarNode i = first;
        while (i.next != null && c.getMake().compareTo(i.next.data.getMake()) > 0) {
            i = i.next;
        }
        newNode.next = i.next;
        i.next = newNode;
        length++;
        if (i == last) last = newNode;
    }
}