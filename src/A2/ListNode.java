//Rohan Jay Arun
//Lab06
//March24th, 2022
package A2;
import java.util.Objects;
import java.lang.reflect.Type;
public class ListNode<T> {

    private ListNode<T> next;
    private final T item;

    public ListNode() {
        next = null;
        item = null;
    }

    public ListNode(T item) { //constructor for the node we use
        next = null;
        this.item = item;
    }

    public ListNode<T> getNext() { return next; } //returns the next of the node
    public T getItem() {return item;}
    public void setNext(ListNode<T> nextNode) { next = nextNode; } //sets the next node


    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param item element to be appended to this list
     * @return {@code true}
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
    public boolean add(Object item) { //adds an item to the end of the list
        if (item == null) { throw new NullPointerException(); }
        T objectItem = (T) item; //declaration of item
        ListNode<T> newNode = new ListNode<>(objectItem); //item to add to the lst
        ListNode<T> currentNode = this;
        while (currentNode.getNext() != null) { currentNode = currentNode.getNext(); }
        currentNode.setNext(newNode);
        return true;
    }
    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param position   index at which the specified element is to be inserted
     * @param item element to be inserted
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    public void add(int position, Object item) { //adds an item at a certain position of the list
        if (position < 0) { throw new IllegalArgumentException(); }

        T objectItem = (T) item;
        ListNode<T> currentNode = this;
        ListNode<T> nextNode = next;
        ListNode<T> newNode = new ListNode<>(objectItem);
        for (int i = 0; i < position-1; i++) {
            currentNode = nextNode;
            nextNode = currentNode.getNext();
            if (nextNode == null) { throw new IndexOutOfBoundsException(); }
        }
        newNode.setNext(nextNode);
        currentNode.setNext(newNode);
        System.out.println(this.toString());
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param item element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     */
    public boolean contains(Object item) { //checks if it is there
        T objectItem = (T) item;
        ListNode<T> currentNode = next;
        System.out.println(item);
        System.out.println(toString());
        while (currentNode != null) {
            if (currentNode.item == objectItem) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param position index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    public Object get(int position) { //returns the node
        if (position < 0) { throw new IndexOutOfBoundsException(); }

        ListNode<T> currentNode = next;

        for (int i = 0; i < position; i++) { //traverses the list to find the node
            if (currentNode.next == null) { throw new IndexOutOfBoundsException(); }
            currentNode = currentNode.next;
        }

        return currentNode.item; //returns the next item of node before given node
    }

    /**
     * Removes the last element in this list. Returns the element that was removed from the
     * list.
     *
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public Object removeLast() { //removes the last item and returns it
        ListNode<T> currentNode = next;
        while (currentNode.next != null) {
            currentNode = currentNode.getNext();
        }
        return currentNode.item;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param item element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     */
    public boolean remove(Object item) { //removes item from the linked list and then returns it
        T objectItem = (T) item;
        ListNode<T> previousNode = this;
        ListNode<T> currentNode = next;

        while (currentNode != null) {
            if (currentNode.item == objectItem) { //if at head then return head and set next to head
                previousNode.next = currentNode.next;
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Removes the element at the specified position from the end of the list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the head of the list.
     *
     * @param position the index of the item the end of the list to be removed
     * @return the head of the list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    public ListNode removeFromEnd(int position){
        return new ListNode(null); //returns the node at the end
    }

    /**
     * Shows ListNode as a String, with each object in parentheses separated by “arrows” (->).
     * A ListNode of {1, 2, 3} should return the String "(1) -> (2) -> (3)".
     * @return String representation of the ListNode
     */
    @Override
    public String toString() { //returns a string
        String listString = "";
        ListNode<T> currentNode = next; //sets the current node to next

        while (currentNode != null) { //setting the parameter

            String itemString = "";
            itemString = "(" + currentNode.item.toString() + ") -> "; //seperate it by the arrow
            listString = listString + itemString;
            currentNode = currentNode.next;
        }

        if (listString.length() > 1) { //parses the if its greater than 1
            return listString.substring(0, listString.length()-3); //returns the output
        }

        return listString; //returns the string
    }
    
    private T attemptCast(Object item, Class<T> itemClass) //attempts the cast onto the other catching the exception
    {
        try{
            return itemClass.cast(item);
        }
        catch (Exception e)
        { return null;}
    }

    public void main(){
        System.out.println("Hello gradle!");
    } //prints hello gradle

}
