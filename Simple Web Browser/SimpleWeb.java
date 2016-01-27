import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class SimpleWeb extends JFrame

implements HyperlinkListener
{
	
    private JButton backButton, forwardButton;
    
   
    private JTextField locationTextField;
    
   
    private JEditorPane displayEditorPane;
    
    
    private ArrayList<String> pageList = new ArrayList<String>();
    
    
    public SimpleWeb() 
    {
       
        super("SimpleWeb");
        setSize(640, 480);
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                Exit();
            }
        });
        
     
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit",KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Exit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("< Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Back();
            }
        });
        backButton.setEnabled(false);
        buttonPanel.add(backButton);
        forwardButton = new JButton("Forward >");
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Forward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel.add(forwardButton);
        locationTextField = new JTextField(35);
        locationTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Link();
                }
            }
        });
        buttonPanel.add(locationTextField);
        JButton goButton = new JButton("GO");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Link();
            }
        });
        buttonPanel.add(goButton);
        
        
        displayEditorPane = new JEditorPane();
        displayEditorPane.setContentType("text/html");
        displayEditorPane.setEditable(false);
        displayEditorPane.addHyperlinkListener(this);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(displayEditorPane),
                BorderLayout.CENTER);
    }
    
   
    private void Exit() 
    {
        System.exit(0);
    }
    
    
    private void Back() 
    {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try 
        {
        	showPage(new URL(pageList.get(pageIndex - 1)), false);
        } catch (Exception e) {}
    }
    
    
    private void Forward() 
    {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try 
        {
            showPage(new URL(pageList.get(pageIndex + 1)), false);
        } catch (Exception e) {}
    }
    
    
    private void Link() {
        URL verifiedUrl = verifyUrl(locationTextField.getText());
        if (verifiedUrl == null) 
        {
        	showError("Invalid URL");
        } 
        else 
        {
        	showPage(verifiedUrl, true);
        }
    }
    
    
    private void showError(String errorMessage) 
    {
        JOptionPane.showMessageDialog(this, errorMessage,"Error", JOptionPane.ERROR_MESSAGE);
    }
    
   
    private URL verifyUrl(String url) 
    {
        
        if (!url.toLowerCase().startsWith("http://"))
            return null;
        URL verifiedUrl = null;
        try 
        {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }
        
        return verifiedUrl;
    }
    
  
    private void showPage(URL pageUrl, boolean addToList) 
    {
       
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        try {
            URL currentUrl = displayEditorPane.getPage();           
            displayEditorPane.setPage(pageUrl);      
            URL newUrl = displayEditorPane.getPage();                     
            if (addToList) 
            {
                int listSize = pageList.size();
                if (listSize > 0) 
                {
                    int pageIndex = pageList.indexOf(currentUrl.toString());
                    if (pageIndex < listSize - 1) 
                    {
                    	int i=listSize - 1;
                    	while(i>pageIndex)
                    	{
                    		pageList.remove(i);
                    		i--;
                    	}
                    }
                }
                pageList.add(newUrl.toString());
            }
            locationTextField.setText(newUrl.toString());
            updateButtons();
        } catch (Exception e) {
            showError("Unable to load page");
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void updateButtons() {
        if (pageList.size() >= 2) {
        	URL currentUrl = displayEditorPane.getPage();
            int pageIndex = pageList.indexOf(currentUrl.toString());
            backButton.setEnabled(pageIndex > 0);
            forwardButton.setEnabled(pageIndex < (pageList.size() - 1));
            
        } 
        else 
        {
        	backButton.setEnabled(false);
            forwardButton.setEnabled(false);
        }
    }
    
 
    public void hyperlinkUpdate(HyperlinkEvent event) {
        HyperlinkEvent.EventType eventType = event.getEventType();
        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
            if (event instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent linkEvent =
                        (HTMLFrameHyperlinkEvent) event;
                HTMLDocument document =
                        (HTMLDocument) displayEditorPane.getDocument();
                document.processHTMLFrameHyperlinkEvent(linkEvent);
            } else {
                showPage(event.getURL(), true);
            }
        }
    }
    
    
    public static void main(String[] args) {
    	SimpleWeb browser = new SimpleWeb();
        browser.show();
    }
}
