<<<<<<< HEAD
package cardGame;

=======
// Diego Regalado Garcia
// Jun 20th, 2025
// CPSC39- Kanemoto
// A simple card game that reads a deck of cards from a file, shuffles them, deals a hand to the player.

package cardGame;
>>>>>>> 41f1e21 (Fixed card logic, final submission)
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
<<<<<<< HEAD

public class CardGame {

	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();


	public static void main(String[] args) {

		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 5 cards
		for(int i = 0; i < 4; i++) {
			playerCards.add(deckOfCards.remove(i));
		}
		
		System.out.println("players cards");
		for(Card c: playerCards)
			System.out.println(c);

		System.out.println("pairs is " + checkFor2Kind());

	}//end main

	public static void shuffle() {

		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}

	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {

		int count = 0;
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);
			Card next = playerCards.get(i+1);
			
			for(int j = i+1; j < playerCards.size(); j++) {
				next = playerCards.get(j);
				//System.out.println(" comparing " + current);
				//System.out.println(" to " + next);
				if(current.equals(next))
					count++;
			}//end of inner for
			if(count == 1)
				return true;

		}//end outer for
		return false;
	}
=======
import java.util.HashMap; // Add this import

public class CardGame {

    // an arraylist to hold the deck of cards and the player's hand
    private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
    private static ArrayList<Card> playerCards = new ArrayList<Card>();

    // Copy constructor for CardGame
    public CardGame(CardGame other) {
        // Deep copy of deckOfCards
        this.deckOfCards = new ArrayList<>(other.deckOfCards);

        // Deep copy of playerCards
        this.playerCards = new ArrayList<>(other.playerCards);
    }

    // main method to run the card game
    public static void main(String[] args) {

        // read the cards from the file and add them to the deck
        Scanner input = null;
        try {
            input = new Scanner(new File("cards.txt"));
        } catch (FileNotFoundException e) {
            // error
            System.out.println("error");
            e.printStackTrace();
        }

        while (input.hasNext()) {
            String[] fields = input.nextLine().split(",");
            if (fields.length != 4) {
                System.err.println("Error: Malformed card data. Each line must have 4 fields.");
                continue; // Skip invalid lines
            }
            try {
                Card newCard = new Card(fields[0], fields[1].trim(),
                        Integer.parseInt(fields[2].trim()), fields[3]);
                deckOfCards.add(newCard);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid card value. Skipping line.");
            }
        }

        shuffle();

        // deal the player 5 cards
        for (int i = 0; i < 5; i++) {
            playerCards.add(deckOfCards.remove(i));
        }
        
        System.out.println("players cards");
        for (Card c : playerCards)
            System.out.println(c);

        System.out.println("pairs is " + checkFor2Kind());

        // Display the remaining cards in the deck
        displayRemainingCards(); // New feature added here
    }

    public static void shuffle() {
        // shuffling the cards by deleting and reinserting
        for (int i = 0; i < deckOfCards.size(); i++) {
            int index = (int) (Math.random() * deckOfCards.size());
            Card c = deckOfCards.remove(index);
            deckOfCards.add(c);
        }
    }

    // check for 2 of a kind in the players hand
    public static boolean checkFor2Kind() {
        HashMap<Card, Integer> cardCount = new HashMap<>();
        for (Card card : playerCards) {
            cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
        }
        for (int count : cardCount.values()) {
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    // This method prints all the cards left in the deck after dealing
    public static void displayRemainingCards() {
        System.out.println("\nRemaining cards in the deck:");
        for (Card c : deckOfCards) {
            System.out.println(c);
        }
    }
>>>>>>> 41f1e21 (Fixed card logic, final submission)
}//end class
