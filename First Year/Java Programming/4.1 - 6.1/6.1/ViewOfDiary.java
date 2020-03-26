import java.util.Observable;
import java.util.Observer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * View of a diary (Graphical)
 */
public class ViewOfDiary extends JFrame implements Observer
{
  private final int YEAR  = 0;            // year area
  private final int MONTH = 1;
  private final int DAY   = 2;
  
  private static final int H = 620;       // Height of window pixels
  private static final int W = 280;       // Width  of window pixels

  private JLabel      theMes     = new JLabel();       // Message area
  private JTextArea   theOutput1 = new JTextArea();    // Input number area
  private JTextArea   theOutput2 = new JTextArea();    // Result area
  private JScrollPane theSP      = new JScrollPane();
  private JTextField date[]      = new JTextField[3];  // Year Month Day

  private WindowClosed  onWindowClosed  = new WindowClosed();
  private CharTyped     onWindowInput   = new CharTyped();
  
  private boolean changedMessage = false;
  private Controller controller;
  
  public ViewOfDiary()
  {
    Container cp        = getContentPane();   // Content Pane  
    cp.setLayout(null);                       // No layout manager  
    setSize( W, H );                          // Size of Window  
    
     String labels[] = {
        "+Y", "+M", "+D",
        "*",  "*",  "*",
        "-Y", "-M", "-D",
      };

    final int LABELS = labels.length;      // # Button Labels

    final int GAP   = 10;                  // Horizontal/ vertical Gap

    final int LABH  = 20;                  // Label absolute height
    final int LAB_Y = 15;                  // Start height co-ordinate label
    final int ELABH = LAB_Y+LABH;          // Label height

    final int A1H   = LAB_Y+GAP;           // Output area 1 Height
    final int A1_Y  = ELABH + GAP;         // Start pos height A1
    final int EA1H  = A1_Y + A1H;          // End Height A1

    final int A2H   = 350;                 // Output area 2 Height
    final int SA2H  = EA1H + GAP;          // Start pos Height A2
    final int A2_Y  = SA2H + A2H;          // Height A2

    final int HBUT  = 120;                 // Buttons height

    final int BNR   = 3;                   // Number of rows of buttons
    final int BNC   = 3;                   // number of cols of buttons
    final int BW    = (W-2*GAP)/BNC;       //
    final int BH    = HBUT/BNR;            // Size of landscape for button
    final int SBH   = A2_Y+GAP;            // Start Height Buttons

    final int WAREA = BNC*BW-2*GAP;        // Width of single component

    int dateS = 0;
    JButton buttons[] = new JButton[LABELS];
    Font font = null;
    for ( int i=0; i<LABELS; i++ )
    {
      if ( labels[i].length() >= 1 )
      {
        if( labels[i].charAt(0) == '*' )   // Special case
        {
          JTextField dateA = new JTextField();      // JTextField
          dateA.setHorizontalAlignment(JTextField.CENTER);
          final int col = i%BNC * BW, row = i/BNC * BH;
          dateA.setBounds( GAP+col, SBH+row, BW-20, BH-10 );
          dateA.setText("");                        // Blank
          font = new Font("Serif",Font.BOLD,26);    // Date font
          dateA.setFont( font );                    //  Uses font
          dateA.setEditable(false);
          date[dateS++] = dateA;
          cp.add( dateA );
        } else {
          if ( labels[i].length() <= 10 )
            font = new Font("Serif",Font.BOLD,16);  // Button font
          else
            font = new Font("Serif",Font.BOLD,14);  // Button font
          buttons[i] = new JButton( labels[i] );
          final int col = i%BNC * BW, row = i/BNC * BH;
          buttons[i].setBounds( GAP+col, SBH+row, BW-20, BH-10 );
          buttons[i].addActionListener( e ->
            {
              String label = e.getActionCommand();  // Button label
              if ( changedMessage )
              {
                controller.process( label, theOutput2.getText() );
                changedMessage = false;
              } else {
               controller.process( label, null );
              }
            }
          );
          buttons[i].setFont( font );
          cp.add( buttons[i] );
        }
      }
    }

    font = new Font("Serif",Font.BOLD,14);         // Font is

    theMes.setBounds( GAP, LAB_Y, WAREA, LABH );   // Message area
    theMes.setText( "" );                          // Blank
    theOutput1.setFont( font );                    //  Uses font 
    theOutput1.setEditable(false);                 //  Read only (User)
    cp.add( theMes );                              //  Add to canvas

    theOutput1.setBounds(GAP, A1_Y, WAREA, A1H);   // Input Area
    theOutput1.setText("");                        // Blank
    theOutput1.setFont( font );                    //  Uses font 
    cp.add( theOutput1 );                          //  Add to canvas

    font = new Font("Serif",Font.BOLD,14);         // Font is

    theSP.setBounds( GAP, SA2H, WAREA, A2H );      // Scrolling pane
    theOutput2.setText( "" );                      //  Blank
    theOutput2.setFont( font );                    //  Uses font
    theOutput2.setLineWrap( true );                //
    theOutput2.setWrapStyleWord( true );           //  Wrap words
    theOutput2.addKeyListener( onWindowInput );    //  Input listener
   
    cp.add( theSP );                               //  Add to canvas
    theSP.getViewport().add( theOutput2 );         //  In TextArea
    cp.add( theSP );                               //  Add to canvas
    setVisible( true );                            // Make visible

    theMes.setText( "Diary" );                     // Opening message
    addWindowListener(  onWindowClosed );
  }

  /**
   * Tell the view where the controller is
   * @param cont The controller
   */
  public void setController( Controller cont )
  {
    controller = cont;
  }
  
  /**
   * Called when the model has changed
   * @param aModelOfDiary The model of the ticket machine
   * @param arg Any arguments
   */
  @Override
  public void update( Observable aModelOfDiary, Object arg )
  {
    ModelOfDiary model = (ModelOfDiary) aModelOfDiary;

    date[DAY].setText  (String.format("%02d", model.getDay()));
    date[MONTH].setText(String.format("%02d", model.getMonth()));
    date[YEAR].setText (String.format("%04d", model.getYear()));

    String message = model.getMessageForToday();
    String cMessage= message.replace('\n', ' ');
    
    int len = message.length();
    if ( len > 30) len = 30;
      theOutput1.setText( cMessage.substring(0, len) );
    theOutput2.setText( message );

    theMes.setText( String.format( "%04d  %02d  %02d",
                         model.getYear(), model.getMonth(),
                         model.getDay() )
                       );
  }
  
  
  class WindowClosed extends WindowAdapter
  {
    public void windowClosing( WindowEvent e)
    {
      if ( changedMessage )
      {
        controller.process( "", theOutput2.getText() );
      }
      controller.close();
      System.exit(0);
    }
  }
  
  class CharTyped implements KeyListener
  {
    public void keyPressed( KeyEvent ke )  { }

    public void keyReleased( KeyEvent ke ) { }

    public void keyTyped( KeyEvent ke )
    {
      // record key has been typed in diary entry
      changedMessage = true;
    }
  }

}

