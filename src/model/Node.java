package model;

public class Node {

    //Atributes
    private int colum;
    private int row;
    private String type;
    private Node prev;
    private Node next;

    //Methods
    public Node(String type, int colum, int row) {
        this.type = type;
        this.colum = colum;
        this.row = row;
    }


    public int getColum() {
        return colum;
    }

    public int getRow() {
        return row;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
