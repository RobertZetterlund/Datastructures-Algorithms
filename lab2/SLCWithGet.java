import java.util.List;

public class SLCWithGet<E extends Comparable<? super E>> extends LinkedCollection<E>
        implements CollectionWithGet<E> {


    /**
     * Iterates through the List and adds a new Entry with element
     * when it has reached an element that larger then itself.
     * @param element the object to add into the list
     * @return
     */
    @Override
    public boolean add(E element) {
        if(element == null){
            return false;
        }

            if (head == null) {
                return super.add(element);
            }
            if (element.compareTo(head.element) < 0) {
                head = new Entry(element, head);
                return true;
            } else if (head.next == null) {
                head.next = new Entry(element, null);
                return true;
            }

        Entry currentEntry = head.next;
        Entry prevEntry = head;

        while(currentEntry!= null){
            if(element.compareTo(currentEntry.element)<0){
                prevEntry.next = new Entry(element,currentEntry);
                return true;
            }
            prevEntry = currentEntry;
            currentEntry = currentEntry.next;
        }
        prevEntry.next = new Entry(element,null);
        return true;
    }

    /**
     * Iterates through the list and when it finds an element that
     * is equal to @param element, it returns the element from the List.
     * @param element
     * @return
     */
    @Override
    public E get(E element ) {

        if(element == null || head == null){
            return null;
        }
        Entry current = head;
        int comp;
        while(current!= null) {
            comp = element.compareTo(current.element);
            if (comp <  0) {
                return null;
            }
            else if (comp == 0) {
                return current.element;
            }
            current = current.next;
        }
        return null;
    }

}

