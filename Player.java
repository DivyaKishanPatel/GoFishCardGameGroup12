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

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private ArrayList<Card> collectedSets;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.collectedSets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public void collectSet(Card card) {
        collectedSets.add(card);
    }

    public ArrayList<Card> getCollectedSets() {
        return collectedSets;
    }

    public boolean hasCard(String rank) {
        for (Card card : hand) {
            if (((GoFishCard) card).getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    @Override
    public String toString() {
        return name + " with cards: " + hand.toString();
    }
}

