/**
 * Customer is the class for implemeting a customer
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class Customer
{
    /**
     * Constructor
     */
    int shoptime;
    public Customer()
    {}

    /**
     * Constructor with given arrival mean, service mean, and number of registers
     *
     * @param task          number of seconds the customer need to be served
     */
    public Customer(int task)
    {
		shoptime = task;
    }

    /**
     * set the number of seconds the customer need to be served
     *
     * @param task          number of seconds the customer need to be served
     */
    public void setTask(int task)
    {
        shoptime = task;
    }

    /**
     * get the number of seconds the customer need to be served
     *
     * @return          number of seconds the customer need to be served
     */
    public int getTask()
    {
        return shoptime;
    }
}
