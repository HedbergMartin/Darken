package compiler;

public class Utilities {


    /**
     * Sets the right bit length of a
     * string with binary form.
     * @param nrBits the wanted length of the binary string.
     * @param bits the binary string.
     * @return the modified string.
     */
    static String checkBits(int nrBits, String bits){
        int nr = nrBits - bits.length();
        for(int i = 0; i<nr;i++){
            bits = "0" + bits;
        }
        return bits;
    }


    /**
     * Convert integer into a binary string.
     * @param number integer to be converted.
     * @return The converted string.
     */
    static String getBinary(Integer number){
        return Integer.toString(number,2);
    }

    /**
     * Convert binary form to hex decimal form.
     * @param binaryForm String to convert.
     * @return the converted String.
     */
    static String getHex(String binaryForm){
        //System.out.println("hexhex: " + binaryForm);
        long hex = Long.parseLong(binaryForm,2);
        return Long.toString(hex,16);
    }

}
