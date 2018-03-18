package letters;

/**
 *
 * @author ivan knapp
 */
public class Letter {
     
    public char[] getRussianLetters(){
        char[] letters = new char[32];
        for(int i=0; i < 32; i++){
            letters[i] = (char)(1040+i);
        }
        return letters;
    }
}
