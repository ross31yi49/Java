/**
 * Store simulator
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class Simulator
{
    /**
     * Kick off
     */
    public static void main(String[] args)
    {
        if ( args.length < 3 ) {
            System.err.println("java Simulator <arrival mean> <service mean> <number of registers>");
            System.err.println("e.g. java Simulator 5 20 5");
            System.exit(-1);
        }

        int arrivalMean = Integer.parseInt(args[0]);
        int serviceMean = Integer.parseInt(args[1]);
        int numberOfRegister = Integer.parseInt(args[2]);

        Store store = new Store(arrivalMean, serviceMean, numberOfRegister);

        store.runBusiness();

        return;
    }
}
