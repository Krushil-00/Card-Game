import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class FullCardGame { 
    static int[][] deck = new int[52][2];
    static Scanner sc1 = new Scanner(System.in);

    static int[] Shape = {17, 18, 19, 20};
    static int temporaryShapeI = 0;
    static int tempSh = Shape[temporaryShapeI];
    static int[] Number = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    static int temporaryNumberI = 0;
    static int tempNum = Number[temporaryNumberI];
    static int temporarySwap = 0;
    static int x = 0;
    static int y = 0;
    
    static int distributiveNextElement = 0;
    
    static { System.out.print("Enter number of total player (Between 2 and 17) : " );}
    static int totalPlayer;
    static{
        while(!(sc1.hasNextInt())){
            System.out.println("Please enter total player between 2 and 17.\nEnter again : ");
            sc1.nextInt();
        }
        totalPlayer = sc1.nextInt();
    }

    static int[][][] distributedCard = new int[totalPlayer][3][2];

    static int[] numOfPriority = new int[6];
    static int[] playersWithPriority = new int[totalPlayer];

    static int maxPriority = 0;

    static int[] sortWinCard = new int[3];
    static int[] sortCompareCard = new int[3];

    static { System.out.print("\nEnter amount that each player will have at staring of the game (Between 10K and 214Cr) : "); }
    static int amountHavingAllPlayer;
    static{
        while(!(sc1.hasNextInt())){
            System.out.print("Please enter valid amount : ");
            sc1.nextInt();
            System.out.println();
        }
        amountHavingAllPlayer = sc1.nextInt();
    }

    static int isWinnerDeclared = 0;

    static int currChal = (int) Math.ceil(amountHavingAllPlayer * 2 / 100);

    static int potLimit = (int) Math.ceil(totalPlayer * amountHavingAllPlayer / 2);
    static int currPot = totalPlayer * currChal;

    static int[] money = new int[totalPlayer];

    static {
        for(int i=0; i<totalPlayer; i++){
            money[i] = amountHavingAllPlayer;
        }
    }
    
    public static void main(String[] args) {


        if( ((totalPlayer > 1) && (totalPlayer < 18)) && ((amountHavingAllPlayer >= 10000) && (amountHavingAllPlayer <= 2140000000)) ){
            
            initializeDeckOfCard();
            

            doShuffle();


            distributeSuffledCard();


            for(int i=0; i<6; i++){
                numOfPriority[i] = 0;
            }
            for(int i=0; i<totalPlayer; i++){
                playersWithPriority[i] = 0;
            }

            withChalAndPackFunctionality(distributedCard);

        }else if( ((totalPlayer < 2) && (totalPlayer > 17)) && ((amountHavingAllPlayer < 10000) && (amountHavingAllPlayer > 2140000000)) ) {
            System.out.println("Enter player between 2-17, and amount between 10K-214Cr.");
        }else if((totalPlayer < 2) && (totalPlayer > 17)){
            System.out.println("Enter player between 2-17.");
        }else if( (amountHavingAllPlayer < 10000) && (amountHavingAllPlayer > 2140000000) ){
            System.out.println("Enter amount between 10K-214Cr.");
        }
    }  

    public static int returnCardNumber(int p){
        int tempStr = deck[p][0];
        return tempStr;
    }
    public static int returnCardShape(int p){
        int tempStr = deck[p][1];
        distributiveNextElement++;
        return tempStr;
    }

    public static void initializeDeckOfCard(){
         temporaryShapeI = 0;
         tempSh = Shape[temporaryShapeI];
         temporaryNumberI = 0;
         tempNum = Number[temporaryNumberI];
         temporarySwap = 0;
         x = 0;
         y = 0;
        for(int i=0; i<52; i++){
            for(int j=0; j<2; j++){
                if(j == 0){
                    deck[i][j]=tempNum;
                    temporaryNumberI++;
                    if(temporaryNumberI == 13){
                        temporaryNumberI = 0;
                    }
                    tempNum = Number[temporaryNumberI];
                }else{
                    deck[i][j] = tempSh;
                    x++;
                    if(x == 13){
                        x=0;
                        y++;
                        if(y == 4){
                            y=0;
                            temporaryShapeI--;
                        }
                        temporaryShapeI++;
                        tempSh = Shape[temporaryShapeI];
                    }
                }
            }   
        }
    }

    public static void printInitialCard() {
        System.out.println("Printing the initial deck of card");
        for(int i=0; i<52; i++){
            for(int j=0; j<2; j++){
                if(j == 0){
                    System.out.print("["+ printNumber(deck[i][j]) );
                }else{
                    System.out.print(","+ printShape(deck[i][j])+"]");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static void doShuffle(){
        for(int j=0; j<10; j++){
            for(int i=0; i<52; i++){
                int randomIndex = (int)(Math.random()*52);
                temporarySwap = deck[i][0];
                deck[i][0] = deck[randomIndex][0];
                deck[randomIndex][0] = temporarySwap;
            
                temporarySwap = deck[i][1];
                deck[i][1] = deck[randomIndex][1];
                deck[randomIndex][1] = temporarySwap;
            }
        }
    }
    
    public static void printShuffledDeck(){
        System.out.println("Printing the shuffled deck of card");
        for(int i = 0; i<52; i++){
            for(int j = 0; j<2; j++){
                if(j == 0){
                    System.out.print("["+ printNumber(deck[i][j]) );
                }else{
                    System.out.print(","+ printShape(deck[i][j]) +"]");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static void distributeSuffledCard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<totalPlayer; j++){
                for(int k=0; k<2; k++){
                    if(k==0){
                        distributedCard[j][i][k] = returnCardNumber(distributiveNextElement);
                    }else{
                        distributedCard[j][i][k] = returnCardShape(distributiveNextElement);
                    }
                }
            }
        }
        distributiveNextElement = 0;
    }

    public static void printDistributedDeck(){
        System.out.println("Printing the distributed deck of card");
        for(int i=0; i<totalPlayer; i++){
            System.out.print("Player "+(i+1)+"'s card :\t");
            for(int j=0; j<3; j++){
                for(int k=0; k<2; k++){
                    if(k==0){
                        System.out.print("["+ printNumber(distributedCard[i][j][k]) );
                    }else{
                        System.out.print(","+ printShape(distributedCard[i][j][k])+"]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static void printCurrentPlayerCard(int[][][] distributedNewCard, int i){
        for(int j=0; j<3; j++){
            for(int k=0; k<2; k++){
                if(k==0){
                    System.out.print("["+ printNumber(distributedNewCard[i][j][k]) );
                }else{
                    System.out.print(","+ printShape(distributedNewCard[i][j][k]) +"]");
                }
            }
        }
    }

    public static void printMaxPriorityArrayWithCards(int[][][] maxPriorityArrayWithCards, int numOfPlayersWithMaxPriorityCards){
        System.out.println("Printing the max proirity card from the distributed card");
        for(int i=0; i<numOfPlayersWithMaxPriorityCards; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<2; k++){
                    if(k==0){
                        System.out.print("["+ printNumber(maxPriorityArrayWithCards[i][j][k]) );
                    }else{
                        System.out.print(","+ printShape(maxPriorityArrayWithCards[i][j][k]) +"]");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public static String printNumber(int i){
        switch (i) {
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            default:
                return "";
        }
    }

    public static String printShape(int i){
        switch (i) {
            case 17:
                return "S";
            case 18:
                return "D";
            case 19:
                return "H";
            case 20:
                return "C";
            default:
                return "";
        }
    }

    public static void winnerAmongHighCard(int[][][] arrayHighCard, int samePriorCard, int[] priorCardPlayer){
        winnerFromAnyPriorityCards(arrayHighCard, samePriorCard);
    }

    public static void winnerAmongDouble(int[][][] arrayDouble, int samePriorCard, int[] priorCardPlayer){
        int winnerIndex = 0;
        if (samePriorCard == 1) {
            winnerIndex = 0;
        } else {
            for(int i=0; i<samePriorCard; i++){
                sortWinCard = sortWinnerCard(arrayDouble[winnerIndex][0][0], arrayDouble[winnerIndex][1][0], arrayDouble[winnerIndex][2][0]);
                sortCompareCard = sortComparingCard(arrayDouble[i][0][0], arrayDouble[i][1][0], arrayDouble[i][2][0]);
                if( (arrayDouble[i][0][0] == arrayDouble[i][1][0]) ){
                    if( (arrayDouble[winnerIndex][0][0] == arrayDouble[winnerIndex][1][0]) ){
                        if( (arrayDouble[i][0][0] > arrayDouble[winnerIndex][0][0]) ){
                            winnerIndex = i;
                            swapWinnerAndComparingCard();
                        }
                    }else{
                        if( (arrayDouble[i][0][0] > arrayDouble[winnerIndex][1][0]) ){
                            winnerIndex = i;
                            swapWinnerAndComparingCard();
                        }
                    }
                }else{
                    if( (arrayDouble[winnerIndex][0][0] == arrayDouble[winnerIndex][1][0]) ){
                        if( (arrayDouble[i][1][0] > arrayDouble[winnerIndex][0][0]) ){
                            winnerIndex = i;
                            swapWinnerAndComparingCard();
                        }
                    }else{
                        if( (arrayDouble[i][1][0] > arrayDouble[winnerIndex][1][0]) ){
                            winnerIndex = i;
                            swapWinnerAndComparingCard();
                        }
                    }
                }
            }
        }

        addMoneyToWinner(arrayDouble, winnerIndex);
        printWinner(winnerIndex);
    }

    public static void winnerAmongColour(int[][][] arrayColour, int samePriorCard, int[] priorCardPlayer){
        winnerFromAnyPriorityCards(arrayColour, samePriorCard);
    }

    public static void winnerAmongSequence(int[][][] arraySequence, int samePriorCard, int[] priorCardPlayer){
        winnerFromAnyPriorityCards(arraySequence, samePriorCard);
    }

    public static void winnerAmongPureSequence(int[][][] arrayPureSequence, int samePriorCard, int[] priorCardPlayer){
        winnerFromAnyPriorityCards(arrayPureSequence, samePriorCard);
        
    }

    public static void winnerAmongTrial(int[][][] arrayTrail, int samePriorCard, int[] priorCardPlayer){
        int winnerIndex = 0;
        if(samePriorCard == 1){
            winnerIndex = 0;
        }else{
            for(int i=0; i<samePriorCard; i++){
                if(arrayTrail[winnerIndex][0][0] < arrayTrail[i][0][0]){
                    winnerIndex = i;
                }
            }
        }
        addMoneyToWinner(arrayTrail ,winnerIndex);
        printWinner(winnerIndex);
    }

    public static void winnerFromAnyPriorityCards(int[][][] array, int samePriCard) { 
        int winnerIndex = 0;
        if (samePriCard == 1) {
            winnerIndex = 0;
        } else {
            if (array.length > 0 && array[0].length > 0 && array[0][0].length > 0) {
                sortWinCard = sortWinnerCard(array[winnerIndex][0][0], array[winnerIndex][1][0], array[winnerIndex][2][0]);
                for (int i = 1; i < samePriCard; i++) {
                    if (array[i].length > 0 && array[i][0].length > 0) {
                        sortCompareCard = sortComparingCard(array[i][0][0], array[i][1][0], array[i][2][0]);
                        if (sortWinCard[0] < sortCompareCard[0]) {
                            winnerIndex = i;
                            swapWinnerAndComparingCard();
                        } else if (sortWinCard[0] == sortCompareCard[0]) {
                            if (sortWinCard[1] < sortCompareCard[1]) {
                                winnerIndex = i;
                                swapWinnerAndComparingCard();
                            } else if (sortWinCard[1] == sortCompareCard[1]) {
                                if (sortWinCard[2] < sortCompareCard[2]) {
                                    winnerIndex = i;
                                    swapWinnerAndComparingCard();
                                }
                            }
                        }
                    }
                }
            }
        }
        addMoneyToWinner(array, winnerIndex);
        printWinner(winnerIndex);
    }

    public static void addMoneyToWinner(int[][][] arr ,int winIndex){
        for(int i=0; i<totalPlayer; i++){
            if( (arr[winIndex][0][0] == distributedCard[i][0][0]) && (arr[winIndex][0][1] == distributedCard[i][0][1]) && (arr[winIndex][1][0] == distributedCard[i][1][0]) && (arr[winIndex][1][1] == distributedCard[i][1][1]) && (arr[winIndex][2][0] == distributedCard[i][2][0]) && (arr[winIndex][2][1] == distributedCard[i][2][1]) ){
                money[i] += currPot;
                System.out.println( "Player " + (i+1) + ", You won so your balance is " + money[i]);
            }
        }
    }


    public static void printWinner(int winIndex) {
        int count = -1;
        int winnerInDistributedCard = -1;
        for(int i=0; i<playersWithPriority.length; i++){
            if(playersWithPriority[i] == maxPriority){
                count++;
                if(count == winIndex){
                    winnerInDistributedCard = i;
                    break;
                }
            }
        }
        System.out.print("Player "+ (winnerInDistributedCard+1) +" won the game; And the card is");
        for(int i=0; i<3; i++){
            for(int j=0; j<2; j++){
                if(j==0){
                    System.out.print("["+ printNumber(distributedCard[winnerInDistributedCard][i][j]) );
                }else{
                    System.out.print(","+ printShape(distributedCard[winnerInDistributedCard][i][j]) +"]");
                }
            }
        }
    }

    public static int[] sortWinnerCard(int i, int j, int k) {
        Integer[] oldCard = {i, j, k};
        Arrays.sort(oldCard, Collections.reverseOrder());

        int[] cards = new int[3];
        for(int x=0; x<cards.length; x++){
            cards[x] = oldCard[x];
        }
        return cards;
    }

    public static int[] sortComparingCard(int i, int j, int k) {
        Integer[] oldCard = {i, j, k};
        Arrays.sort(oldCard, Collections.reverseOrder());

        int[] cards = new int[3];
        for(int x=0; x<cards.length; x++){
            cards[x] = oldCard[x];
        }
        return cards;
    }

    public static void swapWinnerAndComparingCard(){
        sortWinCard[0] = sortCompareCard[0];
        sortWinCard[1] = sortCompareCard[1];
        sortWinCard[2] = sortCompareCard[2];
    }

    public static void clearLine() {
        System.out.print("\033[F");
        System.out.print("\033[2K");
    }



    public static void withChalAndPackFunctionality(int[][][] distributedNewCard){
        Scanner sc = new Scanner(System.in);
        String whatToDoStr = "A";
        char whatToDo = 'a';
        String wantToContinueStr = "Y";
        char wantToContinue = 'y';
        int round = 1;
        int remainingPlayer = totalPlayer;
        int[] currentPlayerNumber = new int[totalPlayer];
        int currentPlayerNumberI = 0;
        int[] hasSeenTheCard = new int[totalPlayer];
        String wantToSeeYourCardStr = "A";
        char wantToSeeYourCard = 'a';
        String whatLastDone = "a";
        int initialChal = currChal;
        int hasGiveReplyOfSeeCard = 0;
        for(int i=0; i<totalPlayer; i++){
            currentPlayerNumber[i] = 1;
        }

        int switchHandle = 0;

        for(int i=0; i<totalPlayer; i++){
            money[i] -= currChal;
        }

        do{
            if( isWinnerDeclared == 1 || wantToContinue == 'a'){
                isWinnerDeclared = 0;
                System.out.println("\n\n\n\n\n\nDo you want to continue this game?(Y/N) : ");
                wantToContinueStr = sc.nextLine();
                
                if( wantToContinueStr.isEmpty() ){
                    System.out.println("Null character is not allowed, So enter valid operator");
                    continue;
                }
                wantToContinue = wantToContinueStr.charAt(0);
                if( !(wantToContinue == 'y' || wantToContinue == 'Y' || wantToContinue == 'n' || wantToContinue == 'N') ){
                    continue;
                }

                if( wantToContinue == 'n' || wantToContinue == 'N' ){
                    continue;
                }
                round = 1;
                whatToDo = 'a';
                whatToDoStr = "A";
                wantToSeeYourCardStr = "A";
                wantToSeeYourCard = 'a';
                whatLastDone = "a";
                remainingPlayer = totalPlayer;
                switchHandle = 0;
                currentPlayerNumberI = 0;
                hasGiveReplyOfSeeCard = 0;
                for(int i=0; i<totalPlayer; i++){
                    currentPlayerNumber[i] = 1;
                }
                currChal = (int) Math.ceil(amountHavingAllPlayer * 2 / 100);
                currPot = totalPlayer * currChal;
                initialChal = currChal;
                for(int i=0; i<totalPlayer; i++){
                    if(money[i] < currChal){
                        System.err.println("Player " + (i+1) + " has no sufficient money, So game is forcefully over");
                        break;
                    }
                    money[i] -= currChal;
                }
                initializeDeckOfCard();
                doShuffle();
                distributeSuffledCard();
                Arrays.fill(hasSeenTheCard, 0);
            }
                
            switch (wantToContinue) {
                case 'Y':
                case 'y':
                    while( (remainingPlayer > 1) && (round < 5) ){
                        System.out.println("\n\n\nIt is round "+ round);
                        System.out.println("It's player " + (currentPlayerNumberI+1) +"'s trun");
                    
                        do{
                            if( (whatToDoStr == "S" || whatToDoStr == "s") && remainingPlayer == 2 ){
                                break;
                            }
                            System.out.println("Current pot amount is " + currPot + "(Pot limit is : " + potLimit + ")");
                            System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance is "+money[currentPlayerNumberI]);

                            if(hasSeenTheCard[currentPlayerNumberI] == 0 && hasGiveReplyOfSeeCard == 0){
                                do{
                                    System.out.println("Do you want to see you card?");
                                    wantToSeeYourCardStr = sc.nextLine();
                                    if(wantToSeeYourCardStr.isEmpty()){
                                        System.out.println("Null character is not allowed, So enter valid operator");
                                        continue;
                                    }
                                    wantToSeeYourCard = wantToSeeYourCardStr.charAt(0);
                                }while( !(wantToSeeYourCard == 'Y' || wantToSeeYourCard == 'y' ||wantToSeeYourCard == 'n' ||wantToSeeYourCard == 'n') );
                                if(wantToSeeYourCard == 'Y' || wantToSeeYourCard == 'y'){
                                    hasSeenTheCard[currentPlayerNumberI] = 1;
                                    wantToSeeYourCard = 'a';
                                }
                                hasGiveReplyOfSeeCard = 1;
                            }

                            System.out.println("What you will do?");
                            if(hasSeenTheCard[currentPlayerNumberI] == 1){
                                System.out.println("Chal (C)");
                                System.out.println("Double Chal (D)");
                                System.out.println("Pack (P)");
                                System.out.println("Show (S)");
                                System.out.println("Current chal and show, required amount is " + currChal);
                                System.out.println("And for double chal, you require " + (currChal * 2));
                                System.out.print("Player "+ (currentPlayerNumberI+1) + ", Your card is : "); 
                                printCurrentPlayerCard(distributedNewCard, currentPlayerNumberI);
                                System.out.println();
                                whatToDoStr = sc.nextLine();
                                clearLine();
                                clearLine();
                            }else{
                                if(!(initialChal == currChal) && whatLastDone == "c"){
                                    System.out.println("Half Blind (H)");
                                }
                                System.out.println("Simple Blind (B)");
                                System.out.println("Double Blind (E)");
                                System.out.println("Pack (P)");
                                System.out.println("Show (S)");
                                if( !(initialChal == currChal) && whatLastDone == "c" ){
                                    System.out.println("Current Half Blind, requried amount is " + (currChal /2));
                                    System.out.println("For show, required amount is " + (currChal /2));
                                }else{
                                    System.out.println("For show, required amount is " + currChal);
                                }
                                System.out.println("For Simple Blind, required amount is " + currChal);
                                System.out.println("For Double Blind, required amount is " + (currChal * 2));
                                whatToDoStr = sc.nextLine();
                            }
                            
                            
                            
                            if( whatToDoStr.isEmpty() ){
                                System.out.println("Null character is not allowed, So enter valid operator");
                                switchHandle = 1;
                                continue;
                            }
                            whatToDo = whatToDoStr.charAt(0);
                            switch (whatToDo) {
                                case 'P':
                                case 'p':
                                    currentPlayerNumber[currentPlayerNumberI] = 0;
                                    remainingPlayer--;
                                    switchHandle = 0;
                                    whatToDoStr = "p";
                                    whatLastDone = "p";
                                    if( whatLastDone == "b" ){
                                        currChal /= 2;
                                    }
                            
                                    for(int j=0; j<3; j++){
                                        for(int k=0; k<2; k++ ){
                                            distributedNewCard[currentPlayerNumberI][j][k] = -1;
                                        }
                                    }
                                    System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after pack is "+money[currentPlayerNumberI]);
                                    System.out.println("Player " + (currentPlayerNumberI+1) + " is out from the game");
                                    System.out.println("Pack");
                                    System.out.println("Current pot after you pack is " + currPot);
                                    break;
                                
                                case 'C':
                                case 'c':
                                    if(hasSeenTheCard[currentPlayerNumberI] == 0){
                                        System.out.println("Enter valid operaor\n");
                                        switchHandle = 1;
                                        continue;
                                    }
                                    switchHandle = 0;
                                    whatToDoStr = "c";

                                
                                    if( money[currentPlayerNumberI] >= currChal ){
                                        money[currentPlayerNumberI] -= currChal;
                                        System.out.println("Chal");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after chal is "+money[currentPlayerNumberI]);
                                        whatLastDone = "c";
                                        currPot += currChal;
                                    }else{
                                        System.out.println("You don't have enough money to chal, So you must have to pack\n");
                                        switchHandle = 1;
                                    }
                                
                                    System.out.println("Player " + (currentPlayerNumberI+1) + " is in the game");
                                    System.out.println("Current pot after your chal is " + currPot);
                                    break;
                                
                                case 'D':
                                case 'd':
                                    if(hasSeenTheCard[currentPlayerNumberI] == 0){
                                        System.out.println("Enter valid operaor\n");
                                        switchHandle = 1;
                                        continue;
                                    }
                                    switchHandle = 0;
                                    whatToDoStr = "d";
                                
                                    if( money[currentPlayerNumberI] >= currChal*2 ){
                                        money[currentPlayerNumberI] -= (currChal * 2);
                                        System.out.println("Double chal");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after double chal is "+money[currentPlayerNumberI]);
                                        whatLastDone = "c";
                                        currChal *= 2;
                                        currPot += currChal;
                                    }else if( money[currentPlayerNumberI] >= currChal ){
                                        System.out.println("You have enough money to chal but you don't have enough money to double chal");
                                        System.out.println("You can do chal but in next turn you can't chal and furthur proceed\n");
                                        switchHandle = 1;
                                    }else{
                                        System.out.println("You don't have enough money to chal so pack\n");
                                        switchHandle = 1;
                                    }
                                    System.out.println("Player " + (currentPlayerNumberI+1) + " is in the game");
                                    System.out.println("Current pot after you double chal is " + currPot);
                                    break;

                                case 'H':
                                case 'h':
                                    if(hasSeenTheCard[currentPlayerNumberI] == 1 || whatLastDone == "h" || whatLastDone == "b"){
                                        System.out.println("Enter valid operaor\n");
                                        switchHandle = 1;
                                        continue;
                                    }
                                    switchHandle = 0;
                                    whatToDoStr = "h";
                                
                                    if( money[currentPlayerNumberI] >= (currChal/2) ){
                                        money[currentPlayerNumberI] -= (currChal/2);
                                        System.out.println("Half Blind");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after Half Blind is "+money[currentPlayerNumberI]);
                                        whatLastDone = "h";
                                        currPot += (currChal/2);
                                    }else{
                                        System.out.println("You don't have enough money to half blind, So you must have to pack\n");
                                        switchHandle = 1;
                                    }
                                
                                    System.out.println("Player " + (currentPlayerNumberI+1) + " is in the game");
                                    System.out.println("Current pot after your blind is " + currPot);
                                    break;

                                
                                case 'B':
                                case 'b':
                                    if(hasSeenTheCard[currentPlayerNumberI] == 1){
                                        System.out.println("Enter valid operaor\n");
                                        switchHandle = 1;
                                        continue;
                                    }
                                    switchHandle = 0;
                                    whatToDoStr = "b";
                                
                                    if( money[currentPlayerNumberI] >= currChal ){
                                        money[currentPlayerNumberI] -= currChal;
                                        System.out.println("Blind");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after blind is "+money[currentPlayerNumberI]);
                                        whatLastDone = "b";
                                        currPot += currChal;
                                    }else{
                                        System.out.println("You don't have enough money to blind, So you must have to pack\n");
                                        switchHandle = 1;
                                    }
                                
                                    System.out.println("Player " + (currentPlayerNumberI+1) + " is in the game");
                                    System.out.println("Current pot after your blind is " + currPot);
                                    break;


                                case 'E':
                                case 'e':
                                    if(hasSeenTheCard[currentPlayerNumberI] == 1){
                                        System.out.println("Enter valid operaor\n");
                                        switchHandle = 1;
                                        continue;
                                    }
                                    switchHandle = 0;
                                    whatToDoStr = "e";
                                
                                    if( money[currentPlayerNumberI] >= currChal*2 ){
                                        money[currentPlayerNumberI] -= (currChal * 2);
                                        System.out.println("Double Blind");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after double blind is "+money[currentPlayerNumberI]);
                                        whatLastDone = "b";
                                        currChal *= 2;
                                        currPot += currChal;
                                    }else if( money[currentPlayerNumberI] >= currChal ){
                                        System.out.println("You have enough money to blind but you don't have enough money to double blind");
                                        System.out.println("You can do blind but in next turn you can't blind and furthur proceed\n");
                                        switchHandle = 1;
                                    }else{
                                        System.out.println("You don't have enough money to chal so pack\n");
                                        switchHandle = 1;
                                    }
                                    System.out.println("Player " + (currentPlayerNumberI+1) + " is in the game");
                                    System.out.println("Current pot after you double blind is " + currPot);
                                    break;
                                
                                case 'S':
                                case 's':
                                    switchHandle = 0;
                                    if( hasSeenTheCard[currentPlayerNumberI] == 0 && (remainingPlayer == 2) && (money[currentPlayerNumberI] >= (currChal/2)) ){
                                        money[currentPlayerNumberI] -= (currChal/2);
                                        System.out.println("Show");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after show is "+money[currentPlayerNumberI]);
                                        whatToDoStr = "s";
                                        isWinnerDeclared = 1;
                                        goToWinner(distributedNewCard);
                                        break;
                                    }else if( (remainingPlayer == 2) && (money[currentPlayerNumberI] >= currChal) ){
                                        money[currentPlayerNumberI] -= currChal;
                                        System.out.println("Show");
                                        System.out.println("Player "+ (currentPlayerNumberI+1) + ", Your current balance after show is "+money[currentPlayerNumberI]);
                                        whatToDoStr = "s";
                                        isWinnerDeclared = 1;
                                        goToWinner(distributedNewCard);
                                        break;
                                    }else if( remainingPlayer > 2 ){
                                        System.out.println("You can't do show when there is more than two player, So do chal or pack\n");
                                        switchHandle = 1;
                                        whatToDoStr = "a";
                                    }else if( money[currentPlayerNumberI] < currChal ){
                                        System.out.println("You don't have enough money to show, So you must have to pack\n");
                                        switchHandle = 1;
                                        whatToDoStr = "a";
                                    }
                                    System.out.println("Current pot after you turn is " + currPot);
                                    break;
                                
                                default:
                                    System.out.println("Enter valid operator\n");
                                    switchHandle = 1;
                                    whatToDoStr = "a";
                                    break;
                            }
                            hasGiveReplyOfSeeCard = 0;
                        
                            if( (whatToDo == 'S' || whatToDo == 's') && (remainingPlayer == 2) ){
                                break;
                            }
                        }while ( switchHandle != 0);
                    
                        do{
                            if(currentPlayerNumberI == totalPlayer){
                                currentPlayerNumberI = 0;
                            }else{
                                currentPlayerNumberI++;
                                if( currentPlayerNumberI == totalPlayer){
                                    currentPlayerNumberI = 0;
                                    round++;
                                }
                            }
                        }while( currentPlayerNumber[currentPlayerNumberI] == 0 );
                    
                        if( remainingPlayer == 1){
                            System.out.println("\n\nOnly one player is remianing");
                            System.out.print("Player " + (currentPlayerNumberI+1) + " is winner and its card is : ");
                            for(int j=0; j<3; j++){
                                for(int k=0; k<2; k++ ){
                                    if(k == 0){
                                        System.out.print( "[" + printNumber(distributedNewCard[currentPlayerNumberI][j][k]) + ",");
                                    }else{
                                        System.out.print( printShape(distributedNewCard[currentPlayerNumberI][j][k]) + "]");
                                    }
                                }
                            }
                            money[currentPlayerNumberI] += currPot;
                            System.out.println( "\nPlayer " + (currentPlayerNumberI+1) + ", You won so your balance is " + money[currentPlayerNumberI]);
                            isWinnerDeclared = 1;
                            break;
                        }
                    
                        if( round == 5 ){
                            System.out.println("\n\nThe last 4th round is over");
                            isWinnerDeclared = 1;
                            goToWinner(distributedNewCard);
                            break;
                        }
                    
                        if( currPot > potLimit){
                            System.out.println("\n\nPotLimit is crossed");
                            isWinnerDeclared = 1;
                            goToWinner(distributedNewCard);
                            break;
                        }
                    
                        if( (whatToDoStr == "S" || whatToDoStr == "s") && remainingPlayer == 2){
                            break;
                        }
                    
                    }
        
                break;

                case 'N':
                case 'n':
                    System.out.println("Game is over");
                    wantToContinue = 'n';
                break;

                default:
                    System.out.println("Enter only Yes or No (Y/N).");
                    wantToContinue = 'a';
                    break;
            }
            

            
        }while(wantToContinue != 'n');

        
        sc.close();
    }

    public static void goToWinner(int[][][] distributedNewCard){
        for(int i=0; i<totalPlayer; i++){

            if( distributedNewCard[i][0][0] == -1 ){
                playersWithPriority[i] = -1;
            }
            else if((distributedNewCard[i][0][0] == distributedNewCard[i][1][0]) && (distributedNewCard[i][1][0] == distributedNewCard[i][2][0]) && (distributedNewCard[i][0][0] == distributedNewCard[i][2][0])){
                playersWithPriority[i] = 5;
                numOfPriority[5]++;
                maxPriority = 5;
            }
            else if((/* A23 */(((distributedNewCard[i][0][0] == 14) || (distributedNewCard[i][1][0] == 14) || (distributedNewCard[i][2][0] == 14))  && ((distributedNewCard[i][0][0] == 2) || (distributedNewCard[i][1][0] == 2) || (distributedNewCard[i][2][0] == 2))  && ((distributedNewCard[i][0][0] == 3) || (distributedNewCard[i][1][0] == 3) || (distributedNewCard[i][2][0] == 3))) || /* 234 */ (((distributedNewCard[i][0][0] == 2) || (distributedNewCard[i][1][0] == 2) || (distributedNewCard[i][2][0] == 2))  && ((distributedNewCard[i][0][0] == 3) || (distributedNewCard[i][1][0] == 3) || (distributedNewCard[i][2][0] == 3))  && ((distributedNewCard[i][0][0] == 4) || (distributedNewCard[i][1][0] == 4) || (distributedNewCard[i][2][0] == 4))) || /* 345 */ (((distributedNewCard[i][0][0] == 3) || (distributedNewCard[i][1][0] == 3) || (distributedNewCard[i][2][0] == 3))  && ((distributedNewCard[i][0][0] == 4) || (distributedNewCard[i][1][0] == 4) || (distributedNewCard[i][2][0] == 4))  && ((distributedNewCard[i][0][0] == 5) || (distributedNewCard[i][1][0] == 5) || (distributedNewCard[i][2][0] == 5))) || /* 456 */ (((distributedNewCard[i][0][0] == 4) || (distributedNewCard[i][1][0] == 4) || (distributedNewCard[i][2][0] == 4))  && ((distributedNewCard[i][0][0] == 5) || (distributedNewCard[i][1][0] == 5) || (distributedNewCard[i][2][0] == 5))  && ((distributedNewCard[i][0][0] == 6) || (distributedNewCard[i][1][0] == 6) || (distributedNewCard[i][2][0] == 6))) || /* 567 */ (((distributedNewCard[i][0][0] == 5) || (distributedNewCard[i][1][0] == 5) || (distributedNewCard[i][2][0] == 5))  && ((distributedNewCard[i][0][0] == 6) || (distributedNewCard[i][1][0] == 6) || (distributedNewCard[i][2][0] == 6))  && ((distributedNewCard[i][0][0] == 7) || (distributedNewCard[i][1][0] == 7) || (distributedNewCard[i][2][0] == 7))) || /* 678 */ (((distributedNewCard[i][0][0] == 6) || (distributedNewCard[i][1][0] == 6) || (distributedNewCard[i][2][0] == 6))  && ((distributedNewCard[i][0][0] == 7) || (distributedNewCard[i][1][0] == 7) || (distributedNewCard[i][2][0] == 7))  && ((distributedNewCard[i][0][0] == 8) || (distributedNewCard[i][1][0] == 8) || (distributedNewCard[i][2][0] == 8))) || /* 789 */ (((distributedNewCard[i][0][0] == 7) || (distributedNewCard[i][1][0] == 7) || (distributedNewCard[i][2][0] == 7))  && ((distributedNewCard[i][0][0] == 8) || (distributedNewCard[i][1][0] == 8) || (distributedNewCard[i][2][0] == 8))  && ((distributedNewCard[i][0][0] == 9) || (distributedNewCard[i][1][0] == 9) || (distributedNewCard[i][2][0] == 9))) || /* 8910 */(((distributedNewCard[i][0][0] == 8) || (distributedNewCard[i][1][0] == 8) || (distributedNewCard[i][2][0] == 8))  && ((distributedNewCard[i][0][0] == 9) || (distributedNewCard[i][1][0] == 9) || (distributedNewCard[i][2][0] == 9))  && ((distributedNewCard[i][0][0] == 10) || (distributedNewCard[i][1][0] == 10) || (distributedNewCard[i][2][0] == 10))) || /* 910J */ (((distributedNewCard[i][0][0] == 9) || (distributedNewCard[i][1][0] == 9) || (distributedNewCard[i][2][0] == 9))  && ((distributedNewCard[i][0][0] == 10) || (distributedNewCard[i][1][0] == 10) || (distributedNewCard[i][2][0] == 10))  && ((distributedNewCard[i][0][0] == 11) || (distributedNewCard[i][1][0] == 11) || (distributedNewCard[i][2][0] == 11))) || /* 10JQ */(((distributedNewCard[i][0][0] == 10) || (distributedNewCard[i][1][0] == 10) || (distributedNewCard[i][2][0] == 10))  && ((distributedNewCard[i][0][0] == 11) || (distributedNewCard[i][1][0] == 11) || (distributedNewCard[i][2][0] == 11))  && ((distributedNewCard[i][0][0] == 12) || (distributedNewCard[i][1][0] == 12) || (distributedNewCard[i][2][0] == 12))) || /* JQK */ (((distributedNewCard[i][0][0] == 11) || (distributedNewCard[i][1][0] == 11) || (distributedNewCard[i][2][0] == 11))  && ((distributedNewCard[i][0][0] == 12) || (distributedNewCard[i][1][0] == 12) || (distributedNewCard[i][2][0] == 12))  && ((distributedNewCard[i][0][0] == 13) || (distributedNewCard[i][1][0] == 13) || (distributedNewCard[i][2][0] == 13))) || /* QKA */ (((distributedNewCard[i][0][0] == 14) || (distributedNewCard[i][1][0] == 14) || (distributedNewCard[i][2][0] == 14))  && ((distributedNewCard[i][0][0] == 13) || (distributedNewCard[i][1][0] == 13) || (distributedNewCard[i][2][0] == 13))  && ((distributedNewCard[i][0][0] == 12) || (distributedNewCard[i][1][0] == 12) || (distributedNewCard[i][2][0] == 12))) && ((((distributedNewCard[i][0][1] == 17) && (distributedNewCard[i][1][1] == 17) && (distributedNewCard[i][2][1] == 17)) || ((distributedNewCard[i][0][1] == 18) && (distributedNewCard[i][1][1] == 18) && (distributedNewCard[i][2][1] == 18)) || ((distributedNewCard[i][0][1] == 19) && (distributedNewCard[i][1][1] == 19) && (distributedNewCard[i][2][1] == 19)) || ((distributedNewCard[i][0][1] == 20) && (distributedNewCard[i][1][1] == 20) && (distributedNewCard[i][2][1] == 20)))))){

                playersWithPriority[i] = 4;
                numOfPriority[4]++;
                if(maxPriority < 4){
                    maxPriority = 4;
                }

            }
            else if((/* A23 */(((distributedNewCard[i][0][0] == 14) || (distributedNewCard[i][1][0] == 14) || (distributedNewCard[i][2][0] == 14))  && ((distributedNewCard[i][0][0] == 2) || (distributedNewCard[i][1][0] == 2) || (distributedNewCard[i][2][0] == 2))  && ((distributedNewCard[i][0][0] == 3) || (distributedNewCard[i][1][0] == 3) || (distributedNewCard[i][2][0] == 3))) || /* 234 */ (((distributedNewCard[i][0][0] == 2) || (distributedNewCard[i][1][0] == 2) || (distributedNewCard[i][2][0] == 2))  && ((distributedNewCard[i][0][0] == 3) || (distributedNewCard[i][1][0] == 3) || (distributedNewCard[i][2][0] == 3))  && ((distributedNewCard[i][0][0] == 4) || (distributedNewCard[i][1][0] == 4) || (distributedNewCard[i][2][0] == 4))) || /* 345 */ (((distributedNewCard[i][0][0] == 3) || (distributedNewCard[i][1][0] == 3) || (distributedNewCard[i][2][0] == 3))  && ((distributedNewCard[i][0][0] == 4) || (distributedNewCard[i][1][0] == 4) || (distributedNewCard[i][2][0] == 4))  && ((distributedNewCard[i][0][0] == 5) || (distributedNewCard[i][1][0] == 5) || (distributedNewCard[i][2][0] == 5))) || /* 456 */ (((distributedNewCard[i][0][0] == 4) || (distributedNewCard[i][1][0] == 4) || (distributedNewCard[i][2][0] == 4))  && ((distributedNewCard[i][0][0] == 5) || (distributedNewCard[i][1][0] == 5) || (distributedNewCard[i][2][0] == 5))  && ((distributedNewCard[i][0][0] == 6) || (distributedNewCard[i][1][0] == 6) || (distributedNewCard[i][2][0] == 6))) || /* 567 */ (((distributedNewCard[i][0][0] == 5) || (distributedNewCard[i][1][0] == 5) || (distributedNewCard[i][2][0] == 5))  && ((distributedNewCard[i][0][0] == 6) || (distributedNewCard[i][1][0] == 6) || (distributedNewCard[i][2][0] == 6))  && ((distributedNewCard[i][0][0] == 7) || (distributedNewCard[i][1][0] == 7) || (distributedNewCard[i][2][0] == 7))) || /* 678 */ (((distributedNewCard[i][0][0] == 6) || (distributedNewCard[i][1][0] == 6) || (distributedNewCard[i][2][0] == 6))  && ((distributedNewCard[i][0][0] == 7) || (distributedNewCard[i][1][0] == 7) || (distributedNewCard[i][2][0] == 7))  && ((distributedNewCard[i][0][0] == 8) || (distributedNewCard[i][1][0] == 8) || (distributedNewCard[i][2][0] == 8))) || /* 789 */ (((distributedNewCard[i][0][0] == 7) || (distributedNewCard[i][1][0] == 7) || (distributedNewCard[i][2][0] == 7))  && ((distributedNewCard[i][0][0] == 8) || (distributedNewCard[i][1][0] == 8) || (distributedNewCard[i][2][0] == 8))  && ((distributedNewCard[i][0][0] == 9) || (distributedNewCard[i][1][0] == 9) || (distributedNewCard[i][2][0] == 9))) || /* 8910 */(((distributedNewCard[i][0][0] == 8) || (distributedNewCard[i][1][0] == 8) || (distributedNewCard[i][2][0] == 8))  && ((distributedNewCard[i][0][0] == 9) || (distributedNewCard[i][1][0] == 9) || (distributedNewCard[i][2][0] == 9))  && ((distributedNewCard[i][0][0] == 10) || (distributedNewCard[i][1][0] == 10) || (distributedNewCard[i][2][0] == 10))) || /* 910J */ (((distributedNewCard[i][0][0] == 9) || (distributedNewCard[i][1][0] == 9) || (distributedNewCard[i][2][0] == 9))  && ((distributedNewCard[i][0][0] == 10) || (distributedNewCard[i][1][0] == 10) || (distributedNewCard[i][2][0] == 10))  && ((distributedNewCard[i][0][0] == 11) || (distributedNewCard[i][1][0] == 11) || (distributedNewCard[i][2][0] == 11))) || /* 10JQ */(((distributedNewCard[i][0][0] == 10) || (distributedNewCard[i][1][0] == 10) || (distributedNewCard[i][2][0] == 10))  && ((distributedNewCard[i][0][0] == 11) || (distributedNewCard[i][1][0] == 11) || (distributedNewCard[i][2][0] == 11))  && ((distributedNewCard[i][0][0] == 12) || (distributedNewCard[i][1][0] == 12) || (distributedNewCard[i][2][0] == 12))) || /* JQK */ (((distributedNewCard[i][0][0] == 11) || (distributedNewCard[i][1][0] == 11) || (distributedNewCard[i][2][0] == 11))  && ((distributedNewCard[i][0][0] == 12) || (distributedNewCard[i][1][0] == 12) || (distributedNewCard[i][2][0] == 12))  && ((distributedNewCard[i][0][0] == 13) || (distributedNewCard[i][1][0] == 13) || (distributedNewCard[i][2][0] == 13))) || /* QKA */ (((distributedNewCard[i][0][0] == 14) || (distributedNewCard[i][1][0] == 14) || (distributedNewCard[i][2][0] == 14))  && ((distributedNewCard[i][0][0] == 13) || (distributedNewCard[i][1][0] == 13) || (distributedNewCard[i][2][0] == 13))  && ((distributedNewCard[i][0][0] == 12) || (distributedNewCard[i][1][0] == 12) || (distributedNewCard[i][2][0] == 12))))){

                playersWithPriority[i] = 3;
                numOfPriority[3]++;
                if(maxPriority<3){
                    maxPriority = 3;
                }

            }
            else if((distributedNewCard[i][0][1] == distributedNewCard[i][1][1]) && (distributedNewCard[i][1][1] == distributedNewCard[i][2][1]) && (distributedNewCard[i][0][1] == distributedNewCard[i][2][1])){
                playersWithPriority[i] = 2;
                numOfPriority[2]++;
                if(maxPriority < 2){
                    maxPriority = 2;
                }
            }
            else if((distributedNewCard[i][0][0] == distributedNewCard[i][1][0]) || (distributedNewCard[i][1][0] == distributedNewCard[i][2][0]) || (distributedNewCard[i][0][0] == distributedNewCard[i][2][0])){
                    playersWithPriority[i] = 1;
                    numOfPriority[1]++;
                    if(maxPriority < 1){
                        maxPriority = 1;
                    }
                }
            else if(maxPriority == 0){
                playersWithPriority[i] = 0;
                numOfPriority[0]++;
                maxPriority = 0;
            }
        }

        int numOfPlayersWithMaxPriorityCards = numOfPriority[maxPriority];

        int[][][] maxPriorityArrayWithCards = new int[numOfPlayersWithMaxPriorityCards][3][2];


        int tempI = 0;
        for(int i=0; i<totalPlayer; i++){
            if(playersWithPriority[i] == maxPriority){
                for(int j=0; j<3; j++){
                    for(int k=0; k<2; k++){
                        maxPriorityArrayWithCards[tempI][j][k] = distributedNewCard[i][j][k];
                    }
                }
                tempI++;
            }
        }

        printMaxPriorityArrayWithCards(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards);

        switch (maxPriority) {
            case 0:
                winnerAmongHighCard(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards, playersWithPriority);
                break;

            case 1:
                winnerAmongDouble(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards, playersWithPriority);
                break;

            case 2:
                winnerAmongColour(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards, playersWithPriority);
                break;

            case 3:
                winnerAmongSequence(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards, playersWithPriority);
                break;

            case 4:
                winnerAmongPureSequence(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards, playersWithPriority);
                break;

            case 5:
                winnerAmongTrial(maxPriorityArrayWithCards, numOfPlayersWithMaxPriorityCards, playersWithPriority);
                break;
        
            default:
                break;
        }
    }
}
