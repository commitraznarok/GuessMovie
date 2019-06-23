import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Movie {

    private String movieTitle;
    private String obfuscaedMovieTitle;

    Movie() {

        int randomNumber = getRandomNumber();
        this.movieTitle = findMovieInFile(randomNumber);
        this.obfuscaedMovieTitle = getObfuscaedMovieTitle(this.movieTitle);
    }

    private String findMovieInFile (int randomNumber){

        int currentLine = 0;
        String line;

        File f = new File("movies.txt");

        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                currentLine++;
                line = s.nextLine();
                if (currentLine == randomNumber) {
                    movieTitle = line;
                }
            }
        }
        catch ( FileNotFoundException e){
            System.out.println(("Nie odnaleziono pliku."));
        }

        if (movieTitle.isEmpty()){
            System.out.println("Nie udało się wylosować filmu. Upewnij się, że plik zawiera 50 pozycji.");
        }

        return movieTitle;
    }

    public String getObfuscaedMovieTitle (String movieTitle){
        return movieTitle.replaceAll("[A-Za-z0-9]", "⎵");
    }

    public int getRandomNumber() {
        return (int) ((Math.random() * 50) + 1);
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getObfuscaedMovieTitle() {
        return obfuscaedMovieTitle;
    }
}
