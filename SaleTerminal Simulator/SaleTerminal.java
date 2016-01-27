/**
 * SaleTerminal is the class for implemeting a sale terminal or register
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class SaleTerminal
{
    /**
     * Constructor
     */
	private Customer person;
    private boolean hascustomer,openstatus;
    private Queue salequeue;
    private int worktime;
    private SaleTerminal()
    {}

    /**
     * Constructor with given queue
     *
     * @param queue                customers waiting queue
     */
    public SaleTerminal(Queue queue)
    {
        salequeue=queue;
    }

    /**
     * Check if the sale terminal is idle
     *
     * @return:            true if idle; false otherwise
     */
    public boolean isIdle()
    {
        if(hascustomer == true)
			return true;
        else
			return false;
        // whether working on a custommer
    }

    /**
     * What the sale terminal need to do in the one second period
     */
    public void oneSecondPass()
    {
        if(hascustomer == true)
        {
			// If idle, try to get next customer from queue
			person=salequeue.getCustomer();
			if(person!=null)
			{
				worktime=person.getTask();
				hascustomer=false;
			}
        }
        else
        {
			// If working on a customer, decrement his remaining task
			if(worktime != 0)
				worktime--;
			// If finishing the current customer, stay idel for one second
			else
				hascustomer=true;
        }
    }

    /**
     * Open the sale terminal
     */
    public void open()
    {
        openstatus = true;
        // can do checkout
    }

    /**
     * Close the sale terminal
     */
    public void close()
    {
        openstatus = false;

        // cannot do checkout
    }

    /**
     * Check if the sale terminal is open
     *
     * @return:            true if open; false otherwise
     */     
    public boolean isOpen()
    {
        if(openstatus == true)
			return true;
        else
			return false;
    }
}                          
