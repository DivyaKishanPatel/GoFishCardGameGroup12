/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofishcard;

/**
 *
 * @author divya
 */
public class Main {
    public static void main(String[] args) {
        GoFishGame game = new GoFishGame();

        // Create and add players
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));

        // Start the game
        game.startGame();
    }
}

