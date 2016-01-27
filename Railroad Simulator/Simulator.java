import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Simulator extends JFrame
{
	private Railroad railroad;
	  private Train[] train;
	  private JPanel buttonPanel;
	  private JButton[] stopButton;
	  private JButton[] startButton;
	  private JButton[] clockButton;
	  private JButton[] countButton;

	  public Simulator(Railroad paramRailroad)
	  {
	    super("Railroad Simulation");

	    this.railroad = paramRailroad;

	    this.stopButton = new JButton[2];
	    this.startButton = new JButton[2];
	    this.clockButton = new JButton[2];
	    this.countButton = new JButton[2];

	    this.stopButton[0] = new JButton("Stop 1");
	    this.stopButton[0].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[0].stop();
	      }
	    });
	    this.startButton[0] = new JButton("Start 1");
	    this.startButton[0].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[0].start();
	      }
	    });
	    this.clockButton[0] = new JButton("<__| 1");
	    this.clockButton[0].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[0].clock();
	      }
	    });
	    this.countButton[0] = new JButton("|__> 1");
	    this.countButton[0].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[0].count();
	      }
	    });
	    this.stopButton[1] = new JButton("Stop 2");
	    this.stopButton[1].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[1].stop();
	      }
	    });
	    this.startButton[1] = new JButton("Start 2");
	    this.startButton[1].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[1].start();
	      }
	    });
	    this.clockButton[1] = new JButton("<__| 2");
	    this.clockButton[1].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[1].clock();
	      }
	    });
	    this.countButton[1] = new JButton("|__> 2");
	    this.countButton[1].addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
	      {
	        Simulator.this.train[1].count();
	      }
	    });
	    this.buttonPanel = new JPanel();
	    this.buttonPanel.setLayout(new GridLayout(1, 8));
	    this.buttonPanel.add(this.countButton[0]);
	    this.buttonPanel.add(this.stopButton[0]);
	    this.buttonPanel.add(this.startButton[0]);
	    this.buttonPanel.add(this.clockButton[0]);
	    this.buttonPanel.add(this.countButton[1]);
	    this.buttonPanel.add(this.stopButton[1]);
	    this.buttonPanel.add(this.startButton[1]);
	    this.buttonPanel.add(this.clockButton[1]);

	    Container localContainer = getContentPane();
	    localContainer.add(paramRailroad, "Center");
	    localContainer.add(this.buttonPanel, "South");

	    setSize(700, 250);
	    setVisible(true);
	  }

	  public static void main(String[] paramArrayOfString)
	  {
	    Railroad localRailroad = new Railroad();
	    Simulator localSimulator = new Simulator(localRailroad);
	    localSimulator.setDefaultCloseOperation(3);

	    localSimulator.train = new Train[2];
	    localSimulator.train[0] = new Train(localRailroad, 300, localSimulator,1);
	    localSimulator.train[1] = new Train(localRailroad, 500, localSimulator,2);

	    Thread[] arrayOfThread = new Thread[2];
	    arrayOfThread[0] = new Thread(localSimulator.train[0]);
	    arrayOfThread[1] = new Thread(localSimulator.train[1]);

	    arrayOfThread[0].start();
	    arrayOfThread[1].start();
	  }

}
