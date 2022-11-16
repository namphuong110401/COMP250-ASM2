/**
 * Your name here: Kristen Hoang
 * Your McGill ID here: 260978837
 **/

public class SolitaireCipher {
    public Deck key;

    public SolitaireCipher (Deck key) {
        this.key = new Deck(key); // deep copy of the deck
    }

    /*
     * TODO: Generates a keystream of the given size
     */
    public int[] getKeystream(int size) {
        /**** ADD CODE HERE ****/
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = key.generateNextKeystreamValue();
        }
        return array;
    }


    /*
     * TODO: Encodes the input message using the algorithm described in the pdf.
     */
    public String encode(String msg) {
        /**** ADD CODE HERE ****/
        String message = msg.toUpperCase().replaceAll("[^A-Z]", "");
        int[] keystream = getKeystream(message.length());
        String encodedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            encodedMessage += (char)((message.charAt(i) - 'A' + keystream[i]) % 26 + 'A');
        }
        return encodedMessage;
    }

    /*
     * TODO: Decodes the input message using the algorithm described in the pdf.
     */
    public String decode(String msg) {
        /**** ADD CODE HERE ****/
        int[] keystream = getKeystream(msg.length());
        String decodedMessage = "";
        for (int i = 0; i < msg.length(); i++) {
            decodedMessage += (char)((msg.charAt(i) - 'A' - keystream[i] + 52) % 26 + 'A');
        }
        return decodedMessage;
    }

}
