package ui;
import model.*;
import java.util.Date;
import java.util.Scanner;

/*
@Authors: Sara Cardona and Juan Yustes
 */
public class Main {
    public static Controladora control = new Controladora();
    public static int contPipes=0;

    public static void main(String[] args) {
        Date date=new Date();
        long sec1=0;
        long sec2=0;
        String nickName=" ";
        Scanner lect = new Scanner(System.in);
        int answEntry;
        boolean entry = true;
        while (entry != false) {
            mainMenu();
            control.setFirst(null);
            answEntry = lect.nextInt();
            lect.nextLine();
            switch (answEntry) {
                case 1:
                    boolean entrySecondMenu=true;
                    while(entrySecondMenu==true) {
                        System.out.println("Write your nickname: ");
                        nickName = lect.nextLine();
                        control.newGame(nickName);
                        control.print();
                         sec1=System.currentTimeMillis();
                        sec1=sec1/1000;
                        boolean entryAMenu=true;
                        while (entryAMenu==true) {
                            secondMenu();

                            int ansMenu = lect.nextInt();
                            lect.nextLine();
                            if (ansMenu == 1) {
                                System.out.println("To enter the position of the pipe ");
                                System.out.print("Enter the row: ");
                                int row = lect.nextInt();
                                lect.nextLine();
                                System.out.print("Enter the column: ");
                                int column = lect.nextInt();
                                lect.nextLine();
                                System.out.println("Write the type of the pipe ´=´ , ´||´ , ´o´ ");
                                String ptype = lect.nextLine();
                                control.changePipe(row, column, ptype);
                                control.print();
                                contPipes++;
                                System.out.println("Used pipes: "+contPipes);
                            } else if (ansMenu == 2) {
                                String message = control.simulate();
                                System.out.println(message);
                                if(message == "You've won!"){
                                    entryAMenu=false;
                                    entrySecondMenu = false;
                                    sec2=System.currentTimeMillis();
                                    sec2=sec2/1000;
                                } else if(message == "It doesn't work."){
                                    control.print();
                                }
                            } else {
                                entryAMenu=false;
                                entrySecondMenu = false;
                                 sec2=System.currentTimeMillis();
                                sec2=sec2/1000;
                            }
                        }

                    }
                    break;
                case 2:
                    long sec=sec2-sec1;
                    double score = control.score(contPipes, sec);
                    control.registerScore(nickName,score);
                    control.printTree();

                    break;
                case 3:
                    entry = false;
                    break;
            }
        }
    }

    static void mainMenu(){

        System.out.println("             WELCOME TO PIPEMANIA             ");
        System.out.println("             --------------------             ");
        System.out.println("1. New Game");
        System.out.println("2. See Score");
        System.out.println("3. Get Out");
        System.out.println("Enter a option... ");

    }

    static void secondMenu(){
        System.out.println(" ");
        System.out.println("             ...What do you want to do?...             ");
        System.out.println("1. Put a pipe");
        System.out.println("2. Simulate");
        System.out.println("3. Get out");
        System.out.print("Enter a option... ");
    }
}
