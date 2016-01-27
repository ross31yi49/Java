/**
 * Shopping is the class for generating shopping customers
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class Shopping
{
    /**
     * Constructor
     */
    private Queue shopqueue;
    private int arrival;
    private int service;
    private int shoppingTime = 0;
    private int queuestates;
    private boolean hasCustomer = false;
    private Customer person;
    private Distribution computeTime; 
    private Shopping()
    {}

    /**
     * Constructor with given queue, arrival mean, and service mean
     *
     * @param queue                customers waiting queue
     * @param arrivalMean          average arrival interval of customers
     * @param serviceMean          average service time of customers
     */
    public Shopping(Queue queue, int arrivalMean, int serviceMean)
    {
		shopqueue = queue;
		arrival = arrivalMean;
		service = serviceMean;
    }

    /**
     * What need to do in the one second period
     */
    public void oneSecondPass()
    {
		// If no customer, generate a customer with given arrivalMean and serviceMean
		if(hasCustomer == false)
		{
			computeTime = new Distribution();
			person = new Customer((int)computeTime.nextExponential(service));
				// The customer's task comes from nextExponential with serviceMean
            shoppingTime = (int)computeTime.nextExponential(arrival);
				// The customer's shopping time comes from nextExponential with arrivalMean
			hasCustomer = true;
		}
		else
		{
			if(shoppingTime == 0)
			{
				queuestates=shopqueue.addCustomer(person);
				hasCustomer = false;
			}
			else
				shoppingTime--;
		}
		// If a customer generated, wait until he done with shopping, i.e. shopping time elapse
		// If a customer done with shopping, add him into queue for checkout
    }
}
