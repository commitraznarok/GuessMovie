import java.util.*;

public class Game {

    private boolean isVictory;
    private int triesAtBeginning;
    private int triesLeft;
    private String displayedTitle;

    private Movie randomizedMovie;

    private ArrayList<Character> guessedLetters;
    private ArrayList<Character> failedLetters;

    Game(int triesAllowed){

        this.triesAtBeginning = triesAllowed;
        this.triesLeft = triesAllowed;
        this.randomizedMovie = new Movie();
        this.displayedTitle = randomizedMovie.getObfuscaedMovieTitle();

        this.guessedLetters = new ArrayList<>();
        this.failedLetters = new ArrayList<>();
        this.isVictory = false;
    }

    public void displayCurrentInfo(){
        System.out.println("Plansza: "+getDisplayedTitle());
        //System.out.println("Cheat: "+randomizedMovie.getMovieTitle());
        System.out.println("Masz "+getTriesLeft()+" prób");
        System.out.println("Litery, które znajdują się w tytule: "+displayListWithComas(getGuessedLetters()));
        System.out.println("Litery, których w tytule nie ma: "+displayListWithComas(getFailedLetters()));
    }

    public boolean currentGame (){

        System.out.println("Zgadnij film");String result = "";

        int numberOfTries =  getTriesAtBeginning();
        while (getTriesLeft() > 0){

            displayCurrentInfo();


            String userInput = getUserInput();

            if (userInput.length() > 0 ) {
                if (userInput.length() > 1) {

                    if (tryToGuessTitle(userInput)) {
                        System.out.println("Wygrałeś!");
                        setVictory(true);
                        return isVictory();

                    } else {
                        System.out.println("Nie odgadleś tytułu filmu.");
                        triesLeft--;
                    }
                }
                else {

                    if (!isLetterUsed(userInput.charAt(0))) {
                        tryToGuessLetter(userInput.charAt(0));
                    }
                }
            }
            if (!getDisplayedTitle().contains("⎵")){
                System.out.println(getDisplayedTitle());
                System.out.println("Wszystkie pola odkryte, wygrałeś");
                setVictory(true);
                return true;
            }

        }

        if (!isVictory()){
            System.out.println("Przegrałeś!");
        }

        return isVictory();
    }

    private boolean isLetterUsed(char c){

        boolean result = false;

        if (getGuessedLetters().contains(c)){
            System.out.println("Już zgadłeś te literę. Nadal masz tyle samo prób ("+triesLeft+")");
            result = true;
        }

        if (getFailedLetters().contains(c))  {
            System.out.println("Próbowano użyć tej litery. Nadal masz tyle samo prób. ("+triesLeft+")");
            result = true;
        }

        return result;
    }

    private boolean tryToGuessTitle(String s){

        boolean result = randomizedMovie.getMovieTitle().toLowerCase().equals(s.toLowerCase());

        return result;
    }

    private boolean tryToGuessLetter(char guessedLetter){

        boolean result= false;

        char c = Character.toLowerCase(guessedLetter);
        boolean czyZnaleziono = uncoverLetter(c);

        if (czyZnaleziono){
            this.guessedLetters.add(c);
        }
        else{
            this.failedLetters.add(c);
            triesLeft--;
        }

        return result;
    }

    private boolean uncoverLetter(Character guesedLetter) {

        boolean isNewLetteUncovered = false;

        char[] displayedTitleArray = getDisplayedTitle().toCharArray();
        char[] randomizedTitleArray = randomizedMovie.getMovieTitle().toCharArray();

        for (int i = 0; i< randomizedMovie.getMovieTitle().length(); i++){
            if (Character.toLowerCase(randomizedTitleArray[i]) == guesedLetter ){
                displayedTitleArray[i] = randomizedTitleArray[i];
                isNewLetteUncovered = true;

            }
        }

        setDisplayedTitle(String.valueOf(displayedTitleArray));

        return isNewLetteUncovered;
    }

    private String displayListWithComas(ArrayList<Character> al){

        String result = "";

        return al.toString();
    }

    private boolean isVictory() {
        return isVictory;
    }

    private void setVictory(boolean victory) {
        isVictory = victory;
    }

    private ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }

    private ArrayList<Character> getFailedLetters() {
        return failedLetters;
    }

    private String getDisplayedTitle() {
        return displayedTitle;
    }

    private void setDisplayedTitle(String displayedTitle) {
        this.displayedTitle = displayedTitle;
    }

    private String getUserInput(){
        return new Scanner(System.in).nextLine();
    }

    private int getTriesLeft() {
        return triesLeft;
    }

    private int getTriesAtBeginning() {
        return triesAtBeginning;
    }

}