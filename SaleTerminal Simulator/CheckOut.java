/**                                
 * CheckOut is the class for implemeting the checkout process
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class CheckOut
{
    /**
     * If queue is crowdy for this GIVEN PERIOD, need to open a new register 
     */
    final private int DELAY_OPEN = 10;

    /**
     * Array of registers or sale terminals in check out area 
     */
    SaleTerminal register[] = null;

    /**
     * Constructor
     */
    public int terminalnum = 1;
    private Queue nowqueue;
    private int registernum,i;
    private CheckOut()
    {}

    /**
     * Constructor with given queue and number of registers
     *
     * @param queue                customers waiting queue
     * @param numberOfRegister     number of registers
     */
    public CheckOut(Queue queue, int numberOfRegister)
    {
        nowqueue=queue;
        registernum=numberOfRegister;
        register = new SaleTerminal[registernum];
        for(i=0;i<registernum;i++)
			register[i]=new SaleTerminal(nowqueue);
		register[0].open();
    }

    /**
     * What need to do in the one second period
     */
    public SaleTerminal[] getTer()
    {
		return register;
    }
    public void oneSecondPass()
    {
        if(nowqueue.isCrowdy() == true && terminalnum < registernum)
        {
            for(i=0;i<registernum;i++)
            {
				if(register[i].isOpen()== false)
				{
					register[i].open();
					terminalnum++;
					break;
				}
			}
        }
        // need to check if queue is too crowdy for a GIVEN PERIOD
        // if yes, open new register

        for(i=0;i<registernum;i++)
        {
			if(register[i].isOpen()== true)
			{
				register[i].oneSecondPass();
			}
		}
        // one second pass for each register

        if(nowqueue.isTooShort() == true && terminalnum > 1)
        {
			for(i=0;i<terminalnum;i++)
			{
				if(register[i].isOpen()== true)
				{
					terminalnum--;
					register[i].close();
					break;
				}
			}
        }
        // need to check if queue if too short
        // if yes, close idle register
    }
}
