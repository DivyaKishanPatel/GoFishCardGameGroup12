/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gofishcard;

/**
 *
 * @author divya
 */
import java.util.ArrayList;
import java.util.Scanner;

public class GoFishGame {
    private ArrayList<Player> players;
    private GroupOfCards deck;
    private int currentPlayerIndex;

    public GoFishGame() {
        players = new ArrayList<>();
        deck = new GroupOfCards(52);
        deck.shuffle();
        currentPlayerIndex = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        // Deal cards to players
        for (Player player : players) {
            for (int i = 0; i < 7; i++) { // Dealing 7 cards to each player
                player.addCard(deck.drawCard());
            }
        }
        play();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (deck.getSize() > 0) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn!");
            System.out.println("Your hand: " + currentPlayer.getHand());

            // Ask another player for a card
            System.out.print("Enter rank to request: ");
            String requestedRank = scanner.nextLine();

            // Find the next player to ask
            Player targetPlayer = players.get((currentPlayerIndex + 1) % players.size());

          if (targetPlayer.hasCard(requestedRank)) {
    System.out.println(targetPlayer.getName() + " has the " + requestedRank + "!");
    // Transfer the card
    Card card = null;
    // Iterate over the cards to find the matching card
    for (Card c : targetPlayer.getHand()) {
        if (c instanceof GoFishCard && ((GoFishCard) c).getRank().equals(requestedRank)) {
            card = c;
            break; // Exit the loop once the card is found
        }
    }
    if (card != null) {
        targetPlayer.removeCard(card);
        currentPlayer.addCard(card);
    }
} else {
    System.out.println(targetPlayer.getName() + " does not have it. Drawing a card.");
    currentPlayer.addCard(deck.drawCard());
}


            // Check for matches
            checkForMatches(currentPlayer);

            // End turn
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        declareWinner();
    }

 private void checkForMatches(Player player) {
    ArrayList<Card> hand = player.getHand();
    ArrayList<Card> toRemove = new ArrayList<>();
    ArrayList<String> ranksChecked = new ArrayList<>();

    // Check for sets of 4 cards with the same rank
    for (Card card : hand) {
        if (card instanceof GoFishCard goFishCard) {
            String rank = goFishCard.getRank();
            if (!ranksChecked.contains(rank)) {
                ranksChecked.add(rank);
                int count = 0;
                for (Card c : hand) {
                    if (c instanceof GoFishCard && ((GoFishCard) c).getRank().equals(rank)) {
                        count++;
                    }
                }

                if (count == 4) {
                    System.out.println(player.getName() + " has a set of four " + rank + "!");
                    // Remove the 4 cards from the hand and add them to collected sets
                    for (Card c : hand) {
                        if (c instanceof GoFishCard && ((GoFishCard) c).getRank().equals(rank)) {
                            toRemove.add(c);
                        }
                    }
                    player.collectSet(toRemove.get(0));  // Assuming all cards are the same rank
                    hand.removeAll(toRemove);
                }
            }
        }
    }
}

    private void declareWinner() {
        // Declare winner based on the most sets collected
        Player winner = null;
        int maxSets = 0;
        for (Player player : players) {
            int setCount = player.getCollectedSets().size();
            if (setCount > maxSets) {
                maxSets = setCount;
                winner = player;
            }
        }
        System.out.println(winner.getName() + " wins with " + maxSets + " sets of cards!");
    }
}
