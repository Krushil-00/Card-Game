# Card Game

This project is a Java-based card game that supports multiple players, rounds, and functionalities like "Chal" (bet) and "Pack" (fold). The game includes features such as distributing cards, determining winners based on card priorities, managing player balances, and enforcing pot limits.

## Features

- **Deck Initialization**: A standard 52-card deck is created and shuffled.
- **Card Distribution**: Cards are distributed to players in a 3x2 format.
- **Game Rounds**: Players take turns in multiple rounds.
- **Chal and Pack**: Players can choose to "Chal" (bet), "Double Chal" (double bet), "Pack" (fold), or "Show" (reveal cards).
- **Winner Determination**: The winner is determined based on card priorities:
  - High Card
  - Double
  - Colour
  - Sequence
  - Pure Sequence
  - Trial
- **Player Balance Management**: Tracks the money each player has and updates it after each round.
- **Pot Limit**: Ensures the pot does not exceed a predefined limit.
- **Dynamic Gameplay**: Handles scenarios like insufficient funds, automatic winner declaration, and continuation of the game.

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <repository-folder>

2. **Compile the Code:**
    
    Use the javac command to compile the Java file:
    ```bash
    javac {filename}.java

3. **Run te Game:**
    Execute the compiled Java program:
    ```bash
    java {filename}.java

4. **Follow the Prompts:**

    - Enter the number of players (between 2 and 17).
    - Enter the starting amount for each player (between 10,000 and 214 crore).
    - Play the game by choosing actions like "Chal," "Pack," "Double Chal," or "Show."


## Gameplay Instructions
1. **Starting the Game:**

  - Enter the number of players (2–17).
  - Enter the starting balance for each player (10,000–214 crore).

2. **Player Actions:**

  - **Chal (C):** Bet the current chal amount.
  - **Double Chal (D):** Bet double the current chal amount.
  - **Pack (P):** Fold and exit the current round.
  - **Show (S):** Reveal cards to determine the winner (only allowed when 2 players remain).

3. **Winning Conditions:**

  - The player with the highest card priority wins.
  - If only one player remains, they are declared the winner.
  - If the pot limit is exceeded, the winner is determined based on card priorities.

4. **Card Priorities:**

  - **Trial:** Three cards of the same rank.

  - **Pure Sequence:** Three consecutive cards of the same suit.

  - **Sequence:** Three consecutive cards of different suits.

  - **Colour:** Three cards of the same suit.

  - **Double:** Two cards of the same rank.

  - **High Card:** The highest card among all players.
