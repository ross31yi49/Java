import java.io.PrintStream;
import javax.swing.SwingUtilities;

public class Train implements Runnable{
	  private Railroad railroad;
	  private int id;
	  private int[] firstpos;
	  private int[] secpos;
	  private int interval;
	  private boolean isRunning = false;
	  private boolean clockwise = false;
	  private Object obj;
	  private int point=0;

	  public Train(Railroad paramRailroad, int paramInt, Object paramObject,int number)
	  {
	    this.railroad = paramRailroad;
	    this.interval = paramInt;
	    this.obj = paramObject;
	    this.id = number;
	    this.isRunning = false;
	    this.clockwise = false;
	    this.firstpos = railroad.getnowpos(1);
	    this.secpos = railroad.getnowpos(2);
	  }

	  public synchronized void stop()
	  {
	    synchronized (this.obj) 
	    {
	      this.isRunning = false;
	      this.obj.notifyAll();
	    }
	  }

	  public synchronized void start()
	  {
	    synchronized (this.obj) 
	    {
	      this.isRunning = true;
	      this.obj.notifyAll();
	    }
	  }

	  public synchronized void clock()
	  {
	    synchronized (this.obj)
	    {
	      this.clockwise = true;
	      this.obj.notifyAll();
	    }
	  }

	  public synchronized void count()
	  {
	    synchronized (this.obj) 
	    {
	      this.clockwise = false;
	      this.obj.notifyAll();
	    }
	  }

	  public void run()
	  {
	    Thread localThread = Thread.currentThread();
	    while (true)
	    {
	      if (!this.isRunning) 
	      {
	        try 
	        {
	          synchronized (this.obj) 
	          {
	            this.obj.wait();
	          }
	        } catch (InterruptedException localInterruptedException1) 
	        {
	          System.err.println(localInterruptedException1);
	        }
	      }
	      else 
	      {
	    	  if(id==1)
	    	  {
	    		  if (!this.clockwise) 
	    		  {
	    			  if (this.firstpos == Railroad.firstCheckEnter) 
	    			  {
	    				  this.railroad.getToken();
	    			  }

	    			  if (this.firstpos == Railroad.firstCheckLeave) 
	    			  {
	    	    	 	  this.railroad.putToken();
	    			  }

	    			  //this.pos = ((this.pos + 1) % Railroad.RAIL_TRACK[this.id]);
	    		 } 
	    		 else 
	    		 {
	    			 if (this.firstpos == Railroad.firstCheckLeave) 
	    			 {
	    				 this.railroad.getToken();
	    			 }

	    			 if (this.firstpos == Railroad.firstCheckEnter) 
	    			 {
	    				 this.railroad.putToken();
	    			 }

	    			// this.pos -= 1;

	    			// if (this.firstpos < 0) 
	    			// {
	    				// this.po = (Railroad.RAIL_TRACK[this.id] - 1);
	    			// }
	    		 }
	    	  }
	    	  else if(id==2)
	    	  {
	    		  if (!this.clockwise) 
	    		  {
	    			  if (this.secpos == Railroad.secCheckEnter) 
	    			  {
	    				  this.railroad.getToken();
	    			  }

	    			  if (this.secpos == Railroad.secCheckLeave) 
	    			  {
	    	    	 	  this.railroad.putToken();
	    			  }

	    			  //this.pos = ((this.pos + 1) % Railroad.RAIL_TRACK[this.id]);
	    		 } 
	    		 else 
	    		 {
	    			 if (this.secpos == Railroad.secCheckLeave) 
	    			 {
	    				 this.railroad.getToken();
	    			 }

	    			 if (this.secpos == Railroad.secCheckEnter) 
	    			 {
	    				 this.railroad.putToken();
	    			 }

	    			// this.pos -= 1;

	    			// if (this.firstpos < 0) 
	    			// {
	    				// this.po = (Railroad.RAIL_TRACK[this.id] - 1);
	    			// }
	    		 }
	    	  }
	        }

	        SwingUtilities.invokeLater(new Runnable()
	        {
	          public void run() 
	          {
	        	  if(id==1)
	        	  {
	        		  if(!clockwise)
	        		  {
	        			  if(firstpos[0]==0&&firstpos[1]==0)
	        				  point=0;
	        			  else if(firstpos[0]==29&&firstpos[1]==0)
	        				  point=1;
	        			  else if(firstpos[0]==29&&firstpos[1]==9)
	        				  point=2;
	        			  else if(firstpos[0]==0&&firstpos[1]==9)
	        				  point=3;
	        		  }
	        		  else
	        		  {
	        			  if(firstpos[0]==0&&firstpos[1]==0)
	        				  point=4;
	        			  else if(firstpos[0]==29&&firstpos[1]==0)
	        				  point=5;
	        			  else if(firstpos[0]==29&&firstpos[1]==9)
	        				  point=6;
	        			  else if(firstpos[0]==0&&firstpos[1]==9)
	        				  point=7;
	        		  }
	        		  Train.this.railroad.setPos(point,id);
	        	  }
	        	  else if(id==2)
	        	  {
	        		  if(!clockwise)
	        		  {
	        			  if(secpos[0]==0&&secpos[1]==0)
	        				  point=0;
	        			  else if(secpos[0]==17&&secpos[1]==0)
	        				  point=1;
	        			  else if(secpos[0]==17&&secpos[1]==9)
	        				  point=2;
	        			  else if(secpos[0]==0&&secpos[1]==9)
	        				  point=3;
	        		  }
	        		  else
	        		  {
	        			  if(secpos[0]==0&&secpos[1]==0)
	        				  point=4;
	        			  else if(secpos[0]==17&&secpos[1]==0)
	        				  point=5;
	        			  else if(secpos[0]==17&&secpos[1]==9)
	        				  point=6;
	        			  else if(secpos[0]==0&&secpos[1]==9)
	        				  point=7;
	        		  }
	        		  Train.this.railroad.setPos(point,id);
	        	  }
	            Train.this.railroad.repaint();
	          }

	        });
	        try
	        {
	          Thread.sleep(this.interval);
	        } catch (InterruptedException localInterruptedException2) {
	          System.err.println(localInterruptedException2);
	        }
	      }
	    }

}
