package model;

import java.util.Random;

public class Board {

    //Relations
    private Node last;
    private Node first;
    Random random = new Random();

    //Methods
    public void setFirst(Node first) {
        this.first = first;
    }

    public Node search(Node current, int column, int row) {
        boolean aux = true;
        while (aux != false) {
            if (current.getColum() == column && current.getRow() == row) {
                aux = false;
            } else {
                current = current.getNext();
            }
        }
        return current;
    }

    public String up(Node current) {
        int up = current.getRow();
        up--;
        int column = current.getColum();
        if (up < 0) {
            return "NodeUp";
        } else {
            Node nodeUp = search(first, column, up);
            return nodeUp.getType();
        }
    }

    public String down(Node current) {
        int down = current.getRow();
        down++;
        int column = current.getColum();
        Node nodeDown = search(first, column, down);
        return nodeDown.getType();
    }

    public void newPos(String type, int colum, int row) {
        Node newPos = new Node(type, colum, row);
        if (first == null) {
            first = newPos;
            last = newPos;
        } else {
            newPos.setPrev(last);
            last.setNext(newPos);
            last = last.getNext();

        }
    }

    public void newGame(String name) {
        random = new Random();
        int numberF = random.nextInt(15 - 5 + 1) + 5;
        int numberD = random.nextInt(50 - 38 + 1) + 38;
        int row = 0;
        int column = 0;
        for (int i = 0; i < 64; i++) {
            if (numberF == i) {
                newPos("F", column, row);
            } else if (numberD == i) {
                newPos("D", column, row);
            } else {
                newPos("x", column, row);
            }
            if ((column % 7) == 0 && column != 0) {
                column = 0;
                row++;
            } else {
                column++;
            }
        }
    }

    public void print() {
        print(first);
    }

    private void print(Node current) {
        if (current == null) {
            return;
        }
        if (current.getColum() == 7) {
            System.out.print(current.getType() + "\n");
        } else {
            System.out.print(current.getType() + "  ");
        }
        print(current.getNext());
    }

    public void changePipe(int row, int line, String type) {
        changePipe(first, row, line, type);
    }

    private void changePipe(Node current, int row, int line, String type) {
        if (current == null) {
            return;
        }
        if (current.getRow() == row && current.getColum() == line) {
            current.setType(type);
        }
        changePipe(current.getNext(), row, line, type);
    }

    public String simulate() {
        boolean goodAsw = simulate(true, first);
        if (goodAsw == true) {
            return "You've won!";
        } else {
            return "It doesn't work.";
        }
    }

    private boolean simulate(boolean goodAsw, Node current) {
        if (current == null) {
        } else if (current.getType().equals("o") && current.getNext().getType().equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("=") && current.getNext().getType().equals("||")) {
            goodAsw = false;
        } else if (current.getType().equals("=") && current.getNext().getType().equals("x")) {
            goodAsw = false;
        } else if (current.getType().equals("||") && current.getNext().getType().equals("=")) {
            goodAsw = false;
        } else if (current.getType().equals("F") && current.getNext().equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("D") && current.getNext().equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("F") && current.getPrev().equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("D") && current.getPrev().equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("=") && current.getPrev().getType().equals("x")) {
            goodAsw = false;
        } else if (current.getType().equals("o") && current.getPrev().getType().equals("||")) {
            goodAsw = false;
        } else if (current.getType().equals("o") && down(current).equals("=")) {
            goodAsw = false;
        } else if (current.getType().equals("||") && down(current).equals("=")) {
            goodAsw = false;
        } else if (current.getType().equals("||") && down(current).equals("x")) {
            goodAsw = false;
        } else if (current.getType().equals("F") && down(current).equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("D") && down(current).equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("F") && up(current).equals("o")) {
            goodAsw = false;
        } else if (current.getType().equals("D") && up(current).equals("o")) {
            goodAsw = false;
        } else {
            goodAsw = simulate(goodAsw, current.getNext());
        }
        return goodAsw;
    }
}
