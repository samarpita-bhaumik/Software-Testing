package org.example;

import org.example.Game.RaceGame.RaceGameGUI;
import org.example.Game.SnakesandLadder.GameGUI;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner myObj = new Scanner(System.in);
        while(true) {
            System.out.println("Which Game do you wish to play?");
            System.out.println("1 -> Snakes and Ladders");
            System.out.println("2 -> 4 Player Race Game");
            int a = myObj.nextInt();
            switch (a){
                case 1: new GameGUI();
                    break;
                case 2: new RaceGameGUI();
                    break;
                default: System.out.println("Wrong Choice!!");
            }
        }
    }
}
