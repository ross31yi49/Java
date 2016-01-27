/**
 * Queue is the class for implemeting a waiting queue for customers
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class Queue
{

    /**
     * Threshold for checking if the customer will abort the shopping and leave the store
     */
    final public static int QUEUE_LENGTH = 20;

    /**
     * Threshold for checking if the waiting queue is too long
     */
    final public static int CROWDY_LENGTH = 10;

    /**
     * Threshold for checking if the waiting queue is too short
     */
    final public static int ENOUGH_LENGTH = 3;

    /**
     * Constructor
     */
    private Customer [] people = null;
    private int queuenum=0,front=0,back=0,abort=0;
    public Queue()
    {
        people = new Customer[QUEUE_LENGTH];
    }

    /**
     * Check if the waiting queue is too long
     *
     * @return:            true if number for customer >= threshold; false otherwise
     */
    public boolean isCrowdy()
    {
        if(queuenum >= CROWDY_LENGTH)
			return true;
        else
			return false;
    }

    /**
     * Check if the waiting queue is too short
     *
     * @return:            true if number for customer <= threshold; false otherwise
     */
    public boolean isTooShort()
    {
		if(queuenum <= ENOUGH_LENGTH)
			return true;
		else
			return false;
    }

    /**
     * Check if the waiting queue is full
     *
     * @return:            true if number for customer >= length; false otherwise
     */
    public boolean isFull()
    {
		if(queuenum >= QUEUE_LENGTH)
			return true;
        else
			return false;
    }
    
    /**
     * Add a customer to queue
     *
     * @param customer     a customer done with shopping
     * @return:            0 if succeeds; 
     *                    -1 if waiting queue is full and customer abort the shopping
     */
    public int addCustomer(Customer customer)
    {
        if(queuenum < QUEUE_LENGTH)
        {
			people[back] = customer;
			back = (back+1)%20;
			queuenum++;
			return 0;
        }
        else
        {
			abort++;;
			return -1;
		}
    }

    /**
     * get the next customer in queue
     *
     * @return:            the next customer in queue if succeeds; null if fails
     */
    public Customer getCustomer()
    {
		if(queuenum > 0)
		{
			Customer temp;
			temp = people[front];
			front = (front+1)%20;
			queuenum--;
			return temp;
        }
        else
			return null;
    }

    /**
     * get the number of customers aborting the shopping and leave the store
     *
     * @return:            the number of customers aborting the shopping and leave the store
     */
    public int getNumOfCustomerGone()
    {
        return abort;
    }
    public int getSize()
    {
		return queuenum;
    }
}
