import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintStream;
import javax.swing.JPanel;

public class Railroad extends JPanel
{

	public static final int firstLenght = 10;
	public static final int secLenght =10;
	

	public static final int firstWidth = 30;
	public static final int secWidth = 17;
    
	
	public static final int firstTrack = 2 * (firstLenght + firstWidth - 2); 
	public static final int secTrack= 2 * (secLenght + secWidth - 2) ;

	
	public static int[] firstStart =  {3,0};
	public static int[] secStart= {3,0};

	
	public static int[] firstCheckEnter = {29,0} ;
	public static int[] secCheckEnter =  {0,0} ;
	
	
	public static int[] firstCheckLeave = {29,9};
	public static int[] secCheckLeave = {0,9};
	
	
	// start draw in (130,40)
	
	private static final int FIRSTWIDTH = firstWidth * 10;
	private static final int SECWIDTH =  secWidth* 10 ;
	
	private static final int FIRSTLENGTH = firstLenght * 10;
	private static final int SECLENGTH =  secLenght * 10;
	
	private static final int FIRST_X = 130;
	private static final int SEC_X = 130 + FIRSTWIDTH - 10;
	
	private static final int FIRSR_Y = 40;
	private static final int SEC_Y = 40;
	
	private Color backgroundColor;
	
	private int[] firstPos = firstStart;
	private int[] secPos = secStart;

	private boolean isFree = true;

	  public Railroad()
	  {
	    backgroundColor = getBackground();
	  }

	  public void paint(Graphics paramGraphics)
	  {
	    super.paint(paramGraphics);

	    //first Railroad
	    paramGraphics.setColor(new Color(255, 0, 0));
	    paramGraphics.fillRect(FIRST_X, FIRSR_Y, FIRSTLENGTH, SECLENGTH);

	    paramGraphics.setColor(this.backgroundColor);
	    paramGraphics.fillRect( FIRST_X + 10, FIRSR_Y + 10, FIRSTLENGTH - 20, SECLENGTH - 20);

	    
	    //second Railroad
	    paramGraphics.setColor(new Color(0, 255, 255));
	    paramGraphics.fillRect(SEC_X, SEC_Y,SECWIDTH ,SECLENGTH );

	    paramGraphics.setColor(this.backgroundColor);
	    paramGraphics.fillRect(SEC_X + 10, SEC_Y + 10, SECWIDTH - 20, SECLENGTH - 20);

	    
	    //
	    paramGraphics.setColor(new Color(0, 0, 255));
	    paramGraphics.fillRect(SEC_X, SEC_Y, 10, SECLENGTH);

	    paramGraphics.setColor(new Color(0, 255, 0));
        paramGraphics.fillRect(FIRST_X+firstPos[0]*10,FIRSR_Y+firstPos[1]*10,10,10);

	    paramGraphics.setColor(new Color(255, 0, 255));
	    paramGraphics.fillRect(SEC_X+secPos[0]*10, SEC_Y+secPos[1]*10, 10, 10);
	  }

	  public synchronized void getToken()
	  {
	    while (!this.isFree) {
	      try {
	        wait();
	      } catch (InterruptedException localInterruptedException) {
	        System.err.println(localInterruptedException);
	      }
	    }

	    this.isFree = false;
	    notifyAll();
	  }

	  public synchronized void putToken()
	  {
	    this.isFree = true;
	    notifyAll();
	  }

	  public void setPos(int point,int id)
	  {
	    if(id==1)
	    {
	    	switch(point)
	    	{
	    		case'0':
	    			firstPos[0]++;
	    			break;
	    		case'1':
	    			firstPos[1]++;
	    			break;
	    		case'2':
	    			firstPos[0]--;
	    			break;
	    		case'3':
	    			firstPos[1]--;
	    			break;
	    		case'4':
	    			firstPos[1]++;
	    			break;
	    		case'5':
	    			firstPos[0]++;
	    			break;
	    		case'6':
	    			firstPos[1]--;
	    			break;
	    		case'7':
	    			firstPos[0]--;
	    			break;
	    	}
	    }
	    if(id==2)
	    {
	    	switch(point)
	    	{
	    		case'0':
	    			secPos[0]++;
	    			break;
	    		case'1':
	    			secPos[1]++;
	    			break;
	    		case'2':
	    			secPos[0]--;
	    			break;
	    		case'3':
	    			secPos[1]--;
	    			break;
	    		case'4':
	    			secPos[1]++;
	    			break;
	    		case'5':
	    			secPos[0]++;
	    			break;
	    		case'6':
	    			secPos[1]--;
	    			break;
	    		case'7':
	    			secPos[0]--;
	    			break;
	    	}
	    }
	  }
	  public int[] getnowpos(int number)
	  {
		  if(number==1)
			  return firstPos;
		  else
			  return secPos;
	  }

}
