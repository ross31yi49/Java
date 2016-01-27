import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Frame extends JFrame
{
	private Container container; 	//frame container
	private JPanel panel;			//use to stop button
	private BorderLayout latout; 	//use to cut frame
	private JButton stopButton;		//stop button
	private boolean ifStop;		    //1=stop 0=write
	private DateFormat timeStart,timeEnd;
									//Start time and stop time
	private JTextArea writearea;	//text Area
	private JMenuBar choose;		//Menu;
	private JMenu file,edit;		//we have two menu
	private JMenuItem newAct,openAct,saveAct,aboutAct,exitAct,reportAct;
									//function of the editor
	private JFileChooser fileCh;	//choose file list
    private String[] reportuse;		//String for report
    private String comment;			//string for text
    private Date Sdate,Edate,start;	//get date
    private int reportWalk;			//use to remember where does string walk
    private boolean ifBegin;
    
    public Frame() 					//constructor
    {
    	//layout
    	super("PSP Edier");
    	container = getContentPane();// use to control panel;
    	
    	//date
    	start = new Date();
    	timeStart = new SimpleDateFormat("yyyy.MM.dd   HH:mm   ");//set the output form
    	timeEnd = new SimpleDateFormat("HH:mm   ");
    	
    	//report
    	reportSet();
    	reportWalk=2;
    	ifBegin=true; //when this program execute default "STOP" 
    	
    	//file
    	fileCh = new JFileChooser();
    	
    	//menu set
    	choose = new JMenuBar();
		choose.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setJMenuBar(choose);
		file = new JMenu("File");
		edit = new JMenu("Edit");
		newAct=new JMenuItem("New");
		New();
		openAct=new JMenuItem("Open");
		Open();
		saveAct=new JMenuItem("Save");
		Save();
		aboutAct=new JMenuItem("About");
		About();
		exitAct=new JMenuItem("Exit");
		Exit();
		reportAct=new JMenuItem("Generate Report");
		Generate();
		
		//text Area
		writearea=new JTextArea("",20,50);
		writearea.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent event)
			{
				if(ifStop==true)
				{
					Sdate = new Date();
					if(ifBegin == true)
						Report();
					else
					{
						ifStop=false;
						Report();
					}
					stopButton.setText("STOP");
				}
			}
		});	
		container.add(new JScrollPane(writearea),"Center");
		
		//button
		panel=new JPanel();
		ifStop=true;
		stopButton = new JButton("START");
		stopButton.setVisible(true);
		buttonSet();
		panel.add(stopButton,"West");
		container.add(panel,"South");
    }
	private void reportSet()
	{
		// set the report output form
		reportuse =new String[500];
		reportuse[0]="Date               Start    Stop  Delta  Activity  Comments\n";
		reportuse[1]="-------------------------------------------------------------------------------------\n";
	}
	private void New()
	{
		newAct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				createnew();
			}
		});
		file.add(newAct);
	}
	private void Open()
	{
		openAct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				int list = fileCh.showOpenDialog(Frame.this);
				if(list == JFileChooser.APPROVE_OPTION)
				{	
					try
					{
						File tag = fileCh.getSelectedFile();
						createnew();
						Scanner input = new Scanner(tag);
						String load;
						while(input.hasNextLine())
						{
							load=input.nextLine();
							writearea.append(load+"\n");
						}
						input.close();
					}catch (IOException exception)
					{
						exception.printStackTrace();
					}
				}
			}
		});
		file.add(openAct);
	}
	private void Save()
	{
		saveAct.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
            	try
            	{
            		int list=fileCh.showSaveDialog(Frame.this);
                    	if (list==JFileChooser.APPROVE_OPTION)
                    	{
                    		File save=fileCh.getSelectedFile();
                            BufferedWriter output=new BufferedWriter(new FileWriter(save.getAbsolutePath()));
                            String[] write=writearea.getText().split("\n");
                            for (int i=0;i<write.length;i++)
                            {
                            	output.write(write[i]);
                                output.newLine();
                            }
                            output.close();
                        }
                }catch (IOException exception)
                {
                    exception.printStackTrace();
                }
            }
        });
		file.add(saveAct);
	}
	private void About()
	{
		aboutAct.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
            	JOptionPane.showMessageDialog(null,"This is JAVA homework 2","About",JOptionPane.PLAIN_MESSAGE);
            }
		});

		file.add(aboutAct);
	}
	private void Exit()
	{
		 exitAct.addActionListener(new ActionListener()
		 {
             public void actionPerformed(ActionEvent event)
             {
            	 System.exit(0);
             }
		 });
		file.add(exitAct);
		choose.add(file);
	}
	private void Generate()
	{
		reportAct.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
            	JOptionPane.showMessageDialog(null,reportuse,"Report",JOptionPane.PLAIN_MESSAGE);
            }
		});
		edit.add(reportAct);
		choose.add(edit);
	}
	private void Report()
	{
		long timepass;
		if(ifStop==true)
		{
			if(ifBegin==true)//when we begin program but does not enter char
			{
				timepass=(Sdate.getTime()-start.getTime())/(1000*60);
				reportuse[reportWalk]=timeStart.format(start)+timeEnd.format(Sdate)+timepass+"       break   Execute program but not edit\n";
				ifBegin=false;
			}
			else
			{
				timepass=(Edate.getTime()-Sdate.getTime())/(1000*60);
				reportuse[reportWalk]=timeStart.format(Sdate)+timeEnd.format(Edate)+timepass+"       edit\n";
			}
		}
		else
		{
			 timepass=(Sdate.getTime()-Edate.getTime())/(1000*60);
             reportuse[reportWalk]=timeStart.format(Edate)+timeEnd.format(Sdate)+timepass+"       break   "+comment+"\n";
		}
		reportWalk++;
	}
	private void buttonSet()
	{
		stopButton.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent event)
            {
            	if (ifStop==true)//Start to Stop
            	{
            		Sdate=new Date();
            		ifStop=false;
            		Report();
                    stopButton.setText("STOP");
                }
                else
                {
                	//Stop to Start
                	comment=JOptionPane.showInputDialog("Please enter the log for this time period");
                	Edate=new Date();
                	ifStop=true;
                	Report();
                    stopButton.setText("START");
                }
            }
		});
	}
	private void createnew()
	{
		//New a project and reset all variable
		reportWalk=2;
    	ifBegin=true;
    	ifStop=true;
    	start=new Date();
    	stopButton.setText("Start");
    	reportSet();
    	writearea.setText("");
	}
}
	