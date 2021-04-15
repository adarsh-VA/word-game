package GameH;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JTextField;

class JTextFieldLimit extends PlainDocument {
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private int limit;
	   JTextFieldLimit(int limit) {
	      super();
	      this.limit = limit;
	   }
	   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	      if (str == null)
	         return;
	      if ((getLength() + str.length()) <= limit) {
	         super.insertString(offset, str, attr);
	      }
	   }
	}

public class GameW implements KeyListener{

	private JFrame frame;
	private JTextField answerF;
	private JPanel mainpanel,insidepanel;
	private JButton playB,okB,backB,replayB;
	private JLabel welcomeL,wordL,Label1,Label2,Label3,booleanL,clueL,wL2,errorL;
	private String  s[]={"fApple","aRabbit","bSamsung","cIndia","fBanana","aElephant","bRedmi","cAmerica","fPapaya","aParrot","bOneplus","cLondon","fGrapes","aSheep","bAdidas","cRussia","fCherry","aDeer",
			"bIntel","cItaly","fGuava","aHorse","bMicrosoft","cJapan","fOrange","aDuck","bGoogle","cBrazil"};
	private int ub,ran,flag=0,c1,f1,f2;
	private StringBuilder as = new StringBuilder();
	private char c='*';
	private String g;
	private char l;
	private boolean f3=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameW window = new GameW();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameW() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Toolkit t = Toolkit.getDefaultToolkit(); 
		Image icon = t.getImage("img\\logo.png");  	       
		frame = new JFrame("Game");
		frame.setBounds(100, 100, 582, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(icon); 
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		mainpanel = new JPanel();
		frame.getContentPane().add(mainpanel, "name_33855528468500");
		mainpanel.setLayout(null);
		mainpanel.setVisible(true);
		
		playB = new JButton("Play Now");
		playB.setFont(new Font("BatmanForeverAlternate", Font.PLAIN, 16));
		playB.setBackground(UIManager.getColor("Button.background"));
		playB.setForeground(new Color(0, 0, 0));
		playB.addActionListener( e-> {
				mainpanel.setVisible(false);
				random();
				clue();
				wset();
				bset();
				okB.setEnabled(true);
				answerF.setEnabled(true);
				insidepanel.setVisible(true);				
		});
		playB.setBounds(188, 239, 166, 54);
		mainpanel.add(playB);
		
		welcomeL = new JLabel("Welcome To Word Game");
		welcomeL.setFont(new Font("Bunch Blossoms Personal Use", Font.PLAIN, 26));
		welcomeL.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeL.setForeground(new Color(0, 120, 215));
		welcomeL.setBackground(SystemColor.windowText);
		welcomeL.setBounds(92, 25, 377, 54);
		mainpanel.add(welcomeL);
		
		JSeparator mainS = new JSeparator();
		mainS.setBounds(0, 208, 566, 2);
		mainpanel.add(mainS);
		
		wL2 = new JLabel("---- Find the Word and Win ----");
		wL2.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		wL2.setHorizontalAlignment(SwingConstants.CENTER);
		wL2.setBounds(150, 90, 231, 14);
		mainpanel.add(wL2);
		
		insidepanel = new JPanel();
		frame.getContentPane().add(insidepanel, "name_33863170312100");
		insidepanel.setLayout(null);
		
		wordL = new JLabel();
		wordL.setFont(new Font("Lucida Console", Font.PLAIN, 27));
		wordL.setHorizontalAlignment(SwingConstants.LEFT);
		wordL.setBounds(84, 23, 387, 62);
		insidepanel.add(wordL);
		
		answerF = new JTextField();
		answerF.setBounds(120, 234, 26, 20);
		answerF.setColumns(10);
		answerF.addKeyListener(this);
		answerF.setDocument(new JTextFieldLimit(1));
		insidepanel.add(answerF);
		
		JSeparator insideS = new JSeparator();
		insideS.setBounds(0, 126, 566, 2);
		insidepanel.add(insideS);
		
		Label1 = new JLabel("Guess each letter in the above Word...");
		Label1.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		Label1.setBackground(UIManager.getColor("Button.disabledForeground"));
		Label1.setBounds(120, 139, 321, 14);
		insidepanel.add(Label1);
		
		Label2 = new JLabel();
		Label2.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		Label2.setBounds(120, 196, 351, 14);
		insidepanel.add(Label2);
		
		Label3 = new JLabel("Letter Here ->");
		Label3.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
		Label3.setBounds(10, 236, 93, 14);
		insidepanel.add(Label3);
		
		okB = new JButton("OK");
		okB.addActionListener( e-> {
			runw();	
			System.out.println(answerF.getText());
			answerF.setText(null);
		});
		okB.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		okB.setBounds(120, 281, 93, 38);
		insidepanel.add(okB);
		
		backB = new JButton("Go Back");
		backB.addActionListener( e-> {
			int res=JOptionPane.showConfirmDialog(frame," Are You Sure..","Go Back!!",JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION) {
				bset();
				insidepanel.setVisible(false);
				mainpanel.setVisible(true);	}				
		});
		backB.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		backB.setBounds(251, 281, 93, 38);
		insidepanel.add(backB);
		
		replayB = new JButton("Replay");
		replayB.addActionListener(e-> {
			random();
			clue();
			wset();		
			bset();
			okB.setEnabled(true);
			answerF.setEnabled(true);
		});
		replayB.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		replayB.setBounds(383, 281, 93, 38);
		insidepanel.add(replayB);
		
		booleanL = new JLabel();
		booleanL.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		booleanL.setHorizontalAlignment(SwingConstants.CENTER);
		booleanL.setBounds(167, 96, 233, 20);
		insidepanel.add(booleanL);
		
		clueL = new JLabel();
		clueL.setFont(new Font("Bodoni MT", Font.PLAIN, 16));
		clueL.setBounds(120, 168, 250, 14);
		insidepanel.add(clueL);
		
		errorL = new JLabel("* * Please Enter Any Letter * *");
		errorL.setFont(new Font("Bodoni MT", Font.PLAIN, 14));
		errorL.setForeground(new Color(128, 0, 0));
		errorL.setBounds(167, 234, 250, 20);
		errorL.setVisible(false);
		insidepanel.add(errorL);
	}
	
	private  void random() {
		as.delete(0, as.length());
		Random rand = new Random();
		ub=28;
		ran = rand.nextInt(ub);
		for(int i=1;i<s[ran].length();i++) 
			as.append(c);  
		f1=as.length();
		c1=f1+5;
		Label2.setText("You have "+c1+" Chances ");//+s[ran].substring(1));
	}
	private void clue() {
		l=s[ran].charAt(0);
		if(l=='f')
			clueL.setText("The Word is a fruit");
		else if(l=='a')
			clueL.setText("The Word is an Animal");
		else if(l=='b')
			clueL.setText("The Word is a BrandName");
		else if(l=='c')
			clueL.setText("The Word is a CountryName");
	}
	private void wset() {
		wordL.setText("Word is : "+as);
		if(c1<f1)
			wordL.setText("The Answer Is :"+s[ran].substring(1));
	}
	private void bset() {
		booleanL.setText("");
	}
	private void runw() {	
			f2=0;
			g=answerF.getText();
			if(!g.isEmpty()) {
				errorL.setVisible(false);
		for(int i=1;i<s[ran].length();i++) {
			if(g.charAt(0)==s[ran].charAt(i) && as.charAt(i-1)=='*') { 
				as.setCharAt(i-1, s[ran].charAt(i));
				f1--;
				f2++;
				break;}
			if(g.toUpperCase().charAt(0)==s[ran].charAt(i) && as.charAt(i-1)=='*') { 
				as.setCharAt(i-1, s[ran].charAt(i));
				f1--;
				f2++;
				break;
				}
			if(g.toLowerCase().charAt(0)==s[ran].charAt(i) && as.charAt(i-1)=='*') { 
				as.setCharAt(i-1, s[ran].charAt(i));
				f1--;
				f2++;
				break;
				}

		}

		for(int i=0;i<as.length();i++) {
			if(as.charAt(i)!='*')
				flag++;
		}
		c1--;
		if(flag==as.length()) {
			booleanL.setForeground(new Color(0, 128, 0));
			//System.out.println("Congrats you Won the Game");
			booleanL.setText("Congrats you Won the Game");
			System.out.println("won");
			flag=0;
			f3=true;
			okB.setEnabled(false);
			answerF.setEnabled(false);
		}
		if(c1<f1) {
			booleanL.setForeground(Color.RED);
			booleanL.setText("You lost The Game!!");
			System.out.println("lost");
			//System.out.println("You lost The Game!!");
			//System.out.println("The Answer Is :"+s[ran].substring(1));
			flag=0;
			f3=true;
			okB.setEnabled(false);
			answerF.setEnabled(false);
		}
		if(f2==0 && !f3) {
			booleanL.setForeground(Color.RED);
			//System.out.println("Wrong Answer!! Try Another");
			booleanL.setText("Wrong Answer!! Try Another");
			//System.in.read();
			}
		else if(f2!=0 && !f3) {
			booleanL.setForeground(new Color(0, 128, 0));
			booleanL.setText("Correct Answer");}
	
		if(flag!=as.length())
			flag=0;
		Label2.setText("You have "+c1+" Chances ");//+s[ran].substring(1));
		f3=false;
		wset();}
		else
			errorL.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
            runw();
			System.out.println(answerF.getText());
			answerF.setText(null);
        }		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
