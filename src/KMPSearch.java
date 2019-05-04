/**
 * This class uses the preprocessing of the Knuth Morris Pratt Sorting algorithm.
 */
public class KMPSearch {

    //this tells the program if there are any repeats by directing the next index.
    private int[] lsp;

    /**
     * This method populates the frequency array, to be used in sorting.
     * @param pattern  string with the pattern to look for.
     * @return returns the internally stored frequency array.
     * @throws IllegalArgumentException if string empty or null.
     */
    public int[] preprocess(String pattern) throws IllegalArgumentException {
        if(pattern == null || pattern.equals(""))
            throw new IllegalArgumentException();
        lsp = new int[pattern.length()];
        int resetIndex = 0;
        lsp[0] = 0;
        for(int i = 1; i < pattern.length(); i++) {
            if(pattern.charAt(resetIndex) == pattern.charAt(i)){
                resetIndex++;
            } else {
                resetIndex = 0;
            }
            lsp[i] = resetIndex;
        }
        return lsp;
    }

    /**
     * This method searches the content string for matches of the pattern, using the lsp array
     * @param content string to be searched for pattern.
     * @param pattern pattern to be searched for.
     * @return count of how many times the pattern is found.
     */
    public int search(String content, String pattern) {
        int[] lsp = preprocess(pattern);
        int count = 0;
        int resetIndex = 0;
        for(int i = 0; i < content.length(); i++) {
            if(content.charAt(i) == pattern.charAt(resetIndex)) {
                if(resetIndex < lsp.length-1){
                    resetIndex++;
                } else {
                    resetIndex = lsp[resetIndex];
                    count++;
                }
            } else {
                if(resetIndex > 0) {
                    resetIndex = lsp[resetIndex - 1];
                    i--;
                }
            }
        }
        return count;
    }

    /**
     * This method returns the internal LSP for testing.
     * @return array of frequency.
     */
    public int[] getLsp(){
        return lsp;
    }
}
