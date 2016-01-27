/**
 * Queue is the class for implemeting a store running business
 *
 * @author      Your name and ID number
 * @since       JDK1.1
 */

public class Store
{
    /**
     * Constructor
     */
    private int i;
    private int arrival;
    private int service;
    private int registernumber;
    private Queue shopqueue;
    private Store()
    {}

    /**
     * Constructor with given arrival mean, service mean, and number of registers
     *
     * @param arrivalMean          average arrival interval of customers
     * @param serviceMean          average service time of customers
     * @param numberOfRegister     number of registers
     */
    public Store(int arrivalMean, int serviceMean, int numberOfRegister)
    {
		arrival = arrivalMean;
		service = serviceMean;
		registernumber = numberOfRegister;
		shopqueue=new Queue();
    }

    /**
     * Running business
     */
    public void runBusiness()
    {
        // Initialization
        Shopping shopact = new Shopping(shopqueue,arrival,service);
        CheckOut pay= new CheckOut(shopqueue,registernumber);
        SaleTerminal termin[] = new SaleTerminal[registernumber];
        // run for 1000 times

        for (int time = 0; time < 1000; time++) {
			System.out.printf("=========================\n");
            shopact.oneSecondPass();

            System.out.printf("QueueSize   :%d\n",shopqueue.getSize());
            System.out.printf("Abort Number:%d\n",shopqueue.getNumOfCustomerGone());
           
            pay.oneSecondPass();
            termin=pay.getTer();
            for(int i=0;i<registernumber;i++)
            {
				if(termin[i].isOpen()== true)
				{
					System.out.printf("Terminalnum:%d : Open ",i+1);
					if(termin[i].isIdle()==true)
						System.out.printf("and Idle\n");
					else
						System.out.printf("and Busy\n");
				}
				else
				System.out.printf("Terminalnum:%d : Close\n",i+1);
            }

            // one second passed

			System.out.printf("=========================\n\n");
            // output status of queue: size? number of customers aborting shopping?
            // output status of each register: open? idle? customer's remaining task?

            try {
                // wait fot 100 ms
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        return;
    }
}
