package model;

public class Controladora {

    //Relations
    private Player first;
    Board b = new Board();

    //Methods
    public void setFirst(Node first) {
        b.setFirst(first);
    }

    public void newGame(String name) {
        b.newGame(name);
    }

    public void print() {
        b.print();
    }

    public void changePipe(int row, int column, String type){
        b.changePipe(row, column, type);
    }

    public double score(int usedPipes, double timeInSeconds) {
        double points = usedPipes * 100 - (60 - timeInSeconds) * 10;
        return points;
    }

    public void registerScore(String nickname, double score) {
        Player player = new Player(nickname, score);
        if (first == null) {
            first = player;
        } else {
            registerScore(player, first);
        }
    }

    private void registerScore(Player player, Player current) {
        if (player.getScore() < current.getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(player);
            } else {
                System.out.println("Registered left");
                registerScore(player, current.getLeft());

            }
        } else if (player.getScore() > current.getScore()) {
            if (current.getRight() == null) {
                current.setRight(player);
            } else {
                System.out.println("Registered RIGTH");
                registerScore(player, current.getRight());

            }
        }
    }

    public void printTree() {
        printTree(first);
    }

    private void printTree(Player current) {
        if (current == null) {
            return;
        }
        System.out.println(current.getNickname() + " " + current.getScore());
        printTree(current.getLeft());
        printTree(current.getRight());

    }

    public String simulate() {
        String message = b.simulate();
        return message;
    }
}



