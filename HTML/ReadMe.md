# Teen Patti Online (HTML Version)

This project is a web-based implementation of the popular Indian card game **Teen Patti** (also known as Indian Poker or Flash). The game supports multiple players, betting rounds, and all standard Teen Patti rules, playable directly in the browser.

## Features

- **Modern UI**: Built with Tailwind CSS for a responsive and visually appealing interface.
- **Game Setup**: Choose the number of players (2–17) and the starting amount for each player.
- **Card Dealing**: Each player receives 3 cards, displayed with interactive card backs (blue for blind, red for seen).
- **Player Actions**: Supports Blind, Chaal, Double Blind, Double Chaal, Half Blind, Pack, and Show.
- **Pot & Betting**: Pot and current chal values are tracked and displayed.
- **Rounds**: Up to 4 rounds, or until only one player remains or the pot limit is reached.
- **Winner Determination**: Winner is determined by standard Teen Patti hand rankings (see below).
- **Game Log**: All actions are logged for transparency.
- **Game Info & Rules**: In-game modal explains rules and gameplay.

## How to Run

1. Open <Link> in your web browser.
2. Enter the number of players and starting amount.
3. Click **Start Game** and follow the on-screen prompts.

## Gameplay Instructions

- **Blind**: Play without seeing your cards (blue card back).
- **Seen/Chaal**: View your cards (red card back) and play at least the current stake.
- **Pack**: Fold and leave the round.
- **Show**: When only two players remain, either can request a showdown.
- **Half Blind/Double Blind/Double Chaal**: Special betting options as per Teen Patti rules.

## Hand Rankings

1. **Trail (Set/Trio)**: Three cards of the same rank (e.g., A♠ A♥ A♦)
2. **Pure Sequence (Straight Flush)**: Three consecutive cards of the same suit (e.g., 7♦ 8♦ 9♦)
3. **Sequence (Straight)**: Three consecutive cards, not all same suit (e.g., 5♠ 6♥ 7♦)
4. **Color (Flush)**: Three cards of the same suit, not in sequence (e.g., K♥ 5♥ 9♥)
5. **Pair**: Two cards of the same rank (e.g., Q♠ Q♥ 4♦)
6. **High Card**: Highest card if no other combination (e.g., A♠ 8♦ 3♥)

## Notes

- The game is for entertainment and learning purposes.
- No backend or persistent storage is used; all logic runs in the browser.
- For more details, see the in-game "Game Info & Rules" modal.

---

Enjoy playing Teen Patti online!