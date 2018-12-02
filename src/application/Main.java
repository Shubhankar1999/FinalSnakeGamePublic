package application;
    
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.text.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class is used to save score and date of score to a file
 * @author shubh
 *
 */
class AppendingObjectOutputStream extends ObjectOutputStream {

	  public AppendingObjectOutputStream(OutputStream out) throws IOException {
	    super(out);
	  }

	  @Override
	  protected void writeStreamHeader() throws IOException {
	    // do not write a header, but reset:
	    // this line added after another question
	    // showed a problem with the original
	    reset();
	  }

	}

class pair implements Serializable{
	int score;
	String date;
	pair(int s,String d){
		score = s;
		date = d;
	}
}

class sorrt implements Comparator<pair>{
	public int compare(pair a,pair b) {
		return b.score - a.score;
	}
}

/**
 * @author shubh
 *
 */
/**
 * @author shubh
 *
 */
/**
 * @author shubh
 *
 */
/**
 * @author shubh
 *
 */
/**
 * @author shubh
 *
 */
public class Main extends Application {
	
	PrintStream oo = System.out;
	GameScreen gmsc;
	static String filename;TextArea tnew2;
	Writer wr,wr2;Reader rdr;
	FileReader rdr2;
	Writer snakeWriter;private int score = 0;
	int LastScore = 0;
	//static File f1;
	ArrayList <Integer> snakeData;
	ArrayList <pair> arrLead;
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	String today_date = df.format(new Date());
//	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	AppendingObjectOutputStream out = null;
	FileWriter mm;
	static String s(int n) {
		return Integer.toString(n);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
	
		//wr = new FileWriter("SnakeBlk.txt",true);
		wr2 = new FileWriter("LastScore.txt",true);
	    rdr2 = new FileReader("LastScore.txt");
		snakeWriter = new FileWriter("DataToSave.txt",true);
		//wr.write("101");
		System.out.println("Done");
//		in = new ObjectInputStream(new FileInputStream("SnakeBlk.txt"));
//		out = new ObjectOutputStream(new FileOutputStream("SnakeBlk.txt"));
		tnew2 = new TextArea();tnew2.setPrefHeight(10);
        //tnew.setPrefWidth(100);
        tnew2.setPadding(new Insets(10));
        //
		
		//wr.flush();
	//	scoreWriter = new FileWriter("ScoreFile.txt");
		arrLead = new ArrayList<pair>();snakeData = new ArrayList<Integer>();
		File f=null;
	    File f2=null;
	    TextArea  tnew= new TextArea();tnew.setPrefHeight(10);
        tnew.setPrefWidth(100);
        tnew.setPadding(new Insets(10));
		Scanner scan=null;
		Scanner scan2=null;
		boolean iii = true;
		try {
			int i = 0;ArrayList<Character> yy = new ArrayList<>() ;
			File fileyy = 
				      new File("LastScore.txt"); 
				    Scanner scyy = new Scanner(fileyy); 
				  
				    
				      String yy3 = (scyy.nextLine()); 
			if(yy3.isEmpty()) {
				tnew2.setText("No last Score");
				
			}else {
				
				tnew2.setText(" "+yy3);
			}
		}catch(Exception exp) {
			System.out.println("error");
		}
		try{
			f = new File("SnakeBlk.txt");
			in = new ObjectInputStream(new FileInputStream("SnakeBlk.txt"));
			out = new AppendingObjectOutputStream(new FileOutputStream("SnakeBlk.txt",true));
//			while(iii){
//				pair op = (pair)in.readObject();
//				if (op != null) arrLead.add(op);
//				else break;
//			}
			try
			{
			    for (;;)
			    {	if (in == null) break;
			    	pair op = (pair)in.readObject();
			    	arrLead.add(op);
			    }
			}
			catch (EOFException exc)
			{
			    // end of stream
			}
		   
		}

		catch(Exception e){
			e.printStackTrace();
		}
		
		
		try{
			
			   f2 = new File("DataToSave.txt");
			
			   scan2 = new Scanner(f2);
			}

			catch(Exception e2){
			   System.exit(0);
			}
		System.out.println("Here");
		while(scan2.hasNext())
		{
		   snakeData.add(scan2.nextInt());
		   System.out.println(" ??"+snakeData);
		
		}
		
		//Collections.sort(arrLead);
		System.out.println("adding "+arrLead);
	    try {
	    	
			
	        Button startGamebtn = new Button("Start");
	        Button resumebtn = new Button("Resume");
	        Button leadBoardbtn = new Button("Leader Board");
	        startGamebtn.setPadding(new Insets(10));
	        resumebtn.setPadding(new Insets(10));
	        leadBoardbtn.setPadding(new Insets(10));
	        //
	        
	       
	        
	        VBox root = new VBox();root.setSpacing(20);
	        
	        //Add buttons to layout
	        root.getChildren().addAll(startGamebtn,resumebtn,leadBoardbtn,tnew, tnew2);
	        
	        root.setAlignment(Pos.CENTER);
	        
	        //
	        
	        startGamebtn.setOnMouseClicked(e->{
	        //    System.out.println("starting");
	            gmsc = new GameScreen();
	            	try {
						gmsc.go(5);
						tnew.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
	        });
	        leadBoardbtn.setOnMouseClicked(e->{
	        	LeaderBoard ld =new LeaderBoard();
	        	tnew.setText("");
	        	Collections.sort(arrLead, new sorrt());
	        	if(arrLead.size()>10) {
	        		//ld.t.setText(arrLead.subList(arrLead.size()-10, arrLead.size()).toString());
	        		int wh = -1 ;
	        		pair[] ar = new pair[10];int p = -1;
	        		for (int i = 0; i < 10; i++) {
	        			ar[i] = arrLead.get(i);
	        		}
	        		ld.t.setText(s(1) + "     " + s(ar[0].score) + "     " + ar[0].date + "\n"+
	        				s(2) + "     " + s(ar[1].score) + "     " + ar[1].date + "\n"+
	        				s(3) + "     " + s(ar[2].score) + "     " + ar[2].date + "\n"+
	        				s(4) + "     " + s(ar[3].score) + "     " + ar[3].date + "\n"+
	        				s(5) + "     " + s(ar[4].score) + "     " + ar[4].date + "\n"+
	        				s(6) + "     " + s(ar[5].score) + "     " + ar[5].date + "\n"+
	        				s(7) + "     " + s(ar[6].score) + "     " + ar[6].date + "\n"+
	        				s(8) + "     " + s(ar[7].score) + "     " + ar[7].date + "\n"+
	        				s(9) + "     " + s(ar[8].score) + "     " + ar[8].date + "\n"+
	        				s(10) + "    " + s(ar[9].score) + "     " + ar[9].date + "\n");
	        		
	        		
	        	}else {
	        		//ld.t.setText(arrLead.toString());
	        		int sz = arrLead.size(); 
	        		if (sz == 0) {
	        			ld.t.setText("no previous score");
	        		}
	        		else if (sz == 1) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date);
	        		}
	        		else if (sz == 2) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date);
	        		}
	        		else if (sz == 3) {    
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date );
	        		}
	        		else if (sz == 4) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date + "\n"+
	        					s(4) + "     " + s(arrLead.get(3).score) + "     "+  arrLead.get(3).date );
	        		}
	        		else if (sz == 5) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date + "\n"+
	        					s(4) + "     " + s(arrLead.get(3).score) + "     "+  arrLead.get(3).date + "\n" +
	        					s(5) + "     " + s(arrLead.get(4).score) + "     "+  arrLead.get(4).date);
	        		}
	        		else if (sz == 6) {    
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date + "\n"+
	        					s(4) + "     " + s(arrLead.get(3).score) + "     "+  arrLead.get(3).date + "\n" +
	        					s(5) + "     " + s(arrLead.get(4).score) + "     "+  arrLead.get(4).date + "\n" +
	        					s(6) + "     " + s(arrLead.get(5).score) + "     "+  arrLead.get(5).date);
	        		}
	        		else if (sz == 7) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date + "\n"+
	        					s(4) + "     " + s(arrLead.get(3).score) + "     "+  arrLead.get(3).date + "\n" +
	        					s(5) + "     " + s(arrLead.get(4).score) + "     "+  arrLead.get(4).date + "\n" +
	        					s(6) + "     " + s(arrLead.get(5).score) + "     "+  arrLead.get(5).date + "\n" + 
	        					s(7) + "     " + s(arrLead.get(6).score) + "     "+  arrLead.get(6).date);
	        		}
	        		else if (sz == 8) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date + "\n"+
	        					s(4) + "     " + s(arrLead.get(3).score) + "     "+  arrLead.get(3).date + "\n" +
	        					s(5) + "     " + s(arrLead.get(4).score) + "     "+  arrLead.get(4).date + "\n" +
	        					s(6) + "     " + s(arrLead.get(5).score) + "     "+  arrLead.get(5).date + "\n" + 
	        					s(7) + "     " + s(arrLead.get(6).score) + "     "+  arrLead.get(6).date + "\n" + 
	        					s(8) + "     " + s(arrLead.get(7).score) + "     "+  arrLead.get(7).date);
	        		}
	        		else if (sz == 9) {
	        			ld.t.setText(s(1) + "     " + s(arrLead.get(0).score) + "     "+  arrLead.get(0).date + "\n"+
	        					s(2) + "     " + s(arrLead.get(1).score) + "     "+  arrLead.get(1).date + "\n" + 
	        					s(3) + "     " + s(arrLead.get(2).score) + "     "+  arrLead.get(2).date + "\n"+
	        					s(4) + "     " + s(arrLead.get(3).score) + "     "+  arrLead.get(3).date + "\n" +
	        					s(5) + "     " + s(arrLead.get(4).score) + "     "+  arrLead.get(4).date + "\n" +
	        					s(6) + "     " + s(arrLead.get(5).score) + "     "+  arrLead.get(5).date + "\n" + 
	        					s(7) + "     " + s(arrLead.get(6).score) + "     "+  arrLead.get(6).date + "\n" + 
	        					s(8) + "     " + s(arrLead.get(7).score) + "     "+  arrLead.get(7).date + "\n" +
	        					s(9) + "     " + s(arrLead.get(8).score) + "     "+  arrLead.get(8).date);
	        		}
	        	}
	        });
	        resumebtn.setOnMouseClicked(e->{
	        	
	        	gmsc = new GameScreen();
	        	
            	try {
            		
            		if(snakeData.size()>0) {
            			
						gmsc.go(snakeData.get(0));
						//gmsc.snake1.len=snakeData.get(0);
						score = snakeData.get(1);
            		}else {
            			tnew.setText("  There is no game to resume");
            		}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
	        });
	        Scene scene = new Scene(root,600,600);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
	    launch(args);
	}
	
	/**
	 * This is the class of the stage that contains the game itself
	 * @author shubh
	 *
	 */
	class GameScreen extends Stage implements Serializable{
		Explosion exp ;
		double timeSinceExp = 0;
		private int count = 0;
		private boolean immortal = false;
		private boolean gameOver = false;
		private boolean pause = false;
		private int uncertainity = 1200, trackLen = 0, spaceToLeave = 80 , sq_of_mgt_dist = 200*200,lenOfWall = 100;
		private Snake snake1;
		private Pane pn = new Pane();
		private Random rd = new Random();private float initYposOfsnake = 350,radOfsnake = 9,radOfBall = 15;
		private int widthOfScreen = 510, heightOfScreen = 500;
		private ArrayList <Ball> b1;private ArrayList <Ball> b2;private ArrayList <Block> bk;
		private ArrayList <DestroyBlock> dbk;private ArrayList<Shield> shields;
		private ArrayList<Magnet> mgts;private ArrayList<Wall> walls;
		private double time = 0;
		private double moveDownSpeed = 5.0;
		private double initspeed = 5.0;
		ArrayList<Double> xbits = new ArrayList<>();
		ArrayList<Double> ybits = new ArrayList<>();
		ArrayList<Bits> bits;
		//ArrayList<Integer> forBall = new ArrayList<>();
		//ArrayList<Integer> forBlock = new ArrayList<>();
		//ArrayList<Integer> forDes = new ArrayList<>();
		boolean hasShield = false,hasMagnet = false;
		long shieldTime = System.currentTimeMillis()+5000,magnetTime = System.currentTimeMillis()+5000;
		//Ann bl = new Ann(0,heightOfScreen-100,"Game Over");
		Image image = new Image("Unknown.jpg");
		Text scoreTxt = new Text();
		Text lenTxt = new Text();
		ImagePattern imagePattern = new ImagePattern(image);
		Image image2 = new Image("Unknown-2.png");

		ImagePattern imgpt = new ImagePattern(image2);
		Image image3 = new Image("images.png");

		ImagePattern mgtPic = new ImagePattern(image3);
		
		class Bits extends Rectangle {
			int width = 7,height = 7;
			Bits(){
				super.setWidth(width);super.setHeight(height);
				super.setVisible(false);super.setFill(Color.BLACK);
			}
		}
		/**
		 * The class that displays burst when snake eats a block
		 * @author shubh
		 *
		 */
		class Explosion{
			
		    
			Explosion(){
				for (int i=0;i<100;i++) {
					bits.add(new Bits());
				}
			}
			
			void disp() {
				for (Bits b:bits) {
					b.setVisible(false);
				}
			}
			public void assembleAt(double x,double y,Paint cl) {
				for (Bits b:bits) {
					b.setTranslateX(x+rd.nextDouble()*30);b.setTranslateY(y+rd.nextDouble()*30);
					b.setVisible(true);b.setFill(cl);
				}
			}
			void adapt() {
				for (int i = 0;i<100;i++) {
					bits.get(i).setTranslateX(bits.get(i).getTranslateX()+xbits.get(i));
					bits.get(i).setTranslateY(bits.get(i).getTranslateY()+ybits.get(i)+moveDownSpeed);
				}
			}
		}
		class Snake implements Serializable{
		    int len=0;
		    SnakeC head;
		    ArrayList<SnakeC> snakeV;
		    Snake(){
		        head = new SnakeC(radOfsnake+3,widthOfScreen/2,initYposOfsnake);
		        head.setVisible(true);
		        snakeV = new ArrayList<>();
		        snakeV.add(head);
		        
		        this.addLen(500);
		        
		        //len = 0;
		        
		    }
		    
		    /**
		     * Method used to move snake to the left
		     */
		    void moveLeft() {
		        if(!pause) {
		            for (Wall e:walls) {
		                if(e.hasCollideWithWall()==1) {
		                    System.out.println("Cant move left");
		                    return;
		                }
		            }
		            if (head.getTranslateX()>radOfsnake) {
			            for (int i = 0;i<500;i++) {
			                
			                    head.setTranslateX(head.getTranslateX()-0.1);
			                
			               // int trueX = (int)(head.getTranslateX());
			                
			            }
		            }
		            for (int i = 1;i<len+100;i++) {
		                snakeV.get(i).setTranslateX(snakeV.get(i-1).getTranslateX());
		            }
		        }
		    }
		    
		    /**
		     * method used to move snake right
		     */
		    void moveRight() {
		        if(!pause) {
		            for (Wall e:walls) {
		                if(e.hasCollideWithWall()==-1) {
		                    System.out.println("Cant move right");
		                    return;
		                }
		            }
		            if (head.getTranslateX()<widthOfScreen-radOfsnake) {
		            	for (int i = 0;i<500;i++) {
		                
		                    head.setTranslateX(head.getTranslateX()+0.1);
		                }
		            }
		            for (int i = 1;i<len+100;i++) {
		            //    long t2 = System.currentTimeMillis();
		                
		                snakeV.get(i).setTranslateX(snakeV.get(i-1).getTranslateX());
		            }
		        }
		    }
		    void addLen() {
		        SnakeC parentofIt = snakeV.get(snakeV.size()-1);
		        SnakeC x = new SnakeC(radOfsnake,parentofIt.x,parentofIt.y+2*radOfsnake);
		        snakeV.add(x);
		        /*
		         
		         
		         */
		    }
		    void addLen(int no) {
		        for (int i = 0;i<no;i++) {
		            addLen();
		        }
		    }
		    void addNo(int no) {
		        for (int i = 0;i<no;i++) {
		            if(len<100) {
		                this.snakeV.get(++len).setVisible(true);
		                
		            }
		        }
		    }
		    void hideNo(int no) {
		        for (int i = 0;i<no;i++) {
		            
		            if(len>0)this.snakeV.get(len--).setVisible(false);
		            
		        }
		    }
		}
		/**
		 * @author shubh
		 *The class of the balls that the snake is made of
		 */
		class SnakeC extends Circle implements Serializable{
		    double x,y,rad;//SnakeC parent;
		    
		    SnakeC(double rad,double x,double y){
		        super(rad,Color.DARKCYAN);
		        this.rad = rad;
		        this.x = x;
		        this.y = y;
		        //this.parent = parent;
		        
		        this.setVisible(false);
		        
		        setTranslateX(x);
		        setTranslateY(y);
		    //    this.parent = null;
		    }
		    
		}
		/**
		 * @author shubh
		 *superclass of all the various tokens since they all move downwards on the screen
		 */
		class TextShapeMoveDown extends StackPane implements Serializable{
		    private int value;Text val;
		    private boolean alive = true;
		    double x,y;
		    TextShapeMoveDown(){}
		    
		    public int getValue() {
		        return this.value;
		    }
		    public void setValue(int V) {
		        this.value = V;
		    }
		    
		    public Text getVal() {
		        return this.val;
		    }
		    
		    public void setVal(Text ss) {
		        this.val = ss;
		    }
		    public boolean getAlive() {
		        return alive;
		    }

		    public void setAlive(boolean alive) {
		        this.alive = alive;
		    }

		    public double getX() {
		        return x;
		    }

		    public void setX(double x) {
		        this.x = x;
		    }

		    public double getY() {
		        return y;
		    }

		    public void setY(double y) {
		        this.y = y;
		    }
		    
		    TextShapeMoveDown(double x,double y,int value){
		        
		        this.x = x;this.y=y;
		        this.value= value;
		        val = new Text(""+value);
		        val.setFont(Font.font("Verdana", 20));
		        
		        setTranslateX(x);setTranslateY(y);
		    }
		    TextShapeMoveDown(double x,double y){
		        
		        this.x = x;this.y=y;
		        //this.value= value;
		    //    val = new Text(""+value);
		        
		    //    val.setFont(Font.font("Verdana", 20));
		        setTranslateX(x);setTranslateY(y);
		    }
		    
		    void moveDown() {
		        setTranslateY(getTranslateY()+moveDownSpeed);
		    }
		    
		}

		        class Wall extends TextShapeMoveDown implements Serializable{
		            int id;Rectangle rect;
		            Wall(double x,double y,int id){
		                super(x,y);
		                rect = new Rectangle(5,lenOfWall , Color.BLACK);
		                
		                rect.setX(x);rect.setY(y);this.id = id;
		                this.getChildren().add(rect);
		                this.setVisible(true);
		            }
		            int hasCollideWithWall() {
		                if(this.getTranslateX()-snake1.head.getTranslateX()<60 && snake1.head.getTranslateX()<this.getTranslateX()&& snake1.head.getTranslateY()<this.getTranslateY()+lenOfWall
		                        && snake1.head.getTranslateY()>this.getTranslateY()) {
		                    return -1;
		                }
		                if(snake1.head.getTranslateX()-this.getTranslateX()<60 && snake1.head.getTranslateX()>this.getTranslateX() && snake1.head.getTranslateY()<this.getTranslateY()+lenOfWall
		                        && snake1.head.getTranslateY()>this.getTranslateY()) {
		                    return 1;
		                }
		                return 0;
		            }
		        }
		        class Ball extends TextShapeMoveDown implements Serializable{
		            int id;
		            Circle cir;
		            Ball(double x,double y,int value,int id){
		                super(x,y,value);
		                cir = new Circle(radOfBall,Color.GOLD);
		                this.id = id;
		                this.getChildren().addAll(cir,this.getVal());
		            }
		            boolean hasCollideWithBall() {
		                if(snake1.head.getBoundsInParent().intersects(this.getBoundsInParent()) && this.getAlive()) {
		                //    System.out.println("COLLISION");
		                    this.setVisible(false);
		                    this.setAlive(false) ;
		                    return true;
		                }
		                return false;
		            }
		            
		        }
		        class Block extends TextShapeMoveDown implements Serializable{
		            private int id;
		            private Rectangle rect;
		            Block(double x,double y,int value,int id){
		                super(x,y,value);
		                rect = new Rectangle(90,60 , Color.rgb(rd.nextInt(120)+100, rd.nextInt(120)+100, rd.nextInt(120)+100));
		                rect.setArcHeight(20);rect.setArcWidth(20);
		                rect.setX(x);rect.setY(y);this.id = id;
		                
		                this.getChildren().addAll(rect,this.getVal());
		                
		            }
		            boolean hasCollideWithBlock() throws InterruptedException {
		                if(snake1.head.getBoundsInParent().intersects(this.getBoundsInParent()) && this.getAlive()) {
		                //    System.out.println("COLLISION with block value of block: "+i.value+"  len of snake: "+snake1.len);
		                    if (this.getValue()>snake1.len &&!hasShield &&!immortal) {
		                        gameOver = true;
		                        
		                        //Thread.sleep(100000);
		                    }
		                    else {
		                    //    System.out.println("hiding: "+i.value);
		                        
		                        
		                        if (this.getValue()>5) {
		                            
		                            Thread.sleep(300);
		                            
		                            
		                        }
		                        
		                        this.setVisible(false);
		                        this.setAlive(false);
		                        
		                    }
		                    
		                    return true;
		                }
		                return false;
		            }

		            public void setId(int id) {
		                this.id = id;
		            }
		            public Rectangle getRect() {
		                return rect;
		            }
		            public void setRect(Rectangle rect) {
		                this.rect = rect;
		            }
		            
		        }
		        class DestroyBlock extends TextShapeMoveDown implements Serializable{
		            int id;
		            Rectangle rect;
		            DestroyBlock(double x,double y,int id){
		                super(x,y);
		                rect = new Rectangle(30, 30);
		                rect.setFill(imgpt);
		                rect.setX(x);rect.setY(y);this.id = id;
		                
		                this.getChildren().addAll(rect);
		                
		            }
		            boolean hasCollideWithDestroyBlock() {
		                if(snake1.head.getBoundsInParent().intersects(this.getBoundsInParent()) && this.getAlive()) {
		                    for (int u = 0;u<bk.size();u++) {
		                        if(bk.get(u).getTranslateY()<heightOfScreen && bk.get(u).getTranslateY()>-50) {
		                            bk.get(u).setAlive(false);
		                            bk.get(u).setVisible(false);
		                            
		                        }
		                    }
		                    this.setVisible(false);
		                    setAlive(false);
		                    return true;
		                }
		                return false;
		            }
		            
		        }
		        class Shield extends TextShapeMoveDown implements Serializable{
		            int id;
		            Rectangle rect;
		            Shield(double x,double y,int id){
		                super(x,y);
		                rect = new Rectangle(35, 30);
		                rect.setFill(imagePattern);
		                
		                rect.setX(x);rect.setY(y);
		                this.id = id;
		                
		                this.getChildren().addAll(rect);
		                
		            }
		            boolean hasCollideWithShield() {
		                if(snake1.head.getBoundsInParent().intersects(this.getBoundsInParent()) && this.getAlive()) {
		                    hasShield = true;
		                    setAlive(false);
		                    this.setVisible(false);
		                    return true;
		                }
		                return false;
		            }
		            
		        }
		        class Magnet extends TextShapeMoveDown implements Serializable{
		            int id;
		            Rectangle rect;
		            Magnet(double x,double y,int id){
		                super(x,y);
		                rect = new Rectangle(35, 30);
		                rect.setFill(mgtPic);
		                
		                rect.setX(x);rect.setY(y);this.id = id;
		                
		                this.getChildren().addAll(rect);
		                
		            }
		            boolean hasCollideWithMagnet() {
		                if(snake1.head.getBoundsInParent().intersects(this.getBoundsInParent()) && this.getAlive()) {
		                    hasMagnet = true;
		                    magnetTime = System.currentTimeMillis()+5000;
		                    this.setVisible(false);
		                    setAlive(false);
		                    return true;
		                }
		                return false;
		            }
		        }
		        class Ann extends StackPane implements Serializable{
		            
		            Rectangle rect;
		            Ann(double x,double y,String value){
		                
		                rect = new Rectangle(widthOfScreen+100, 30, Color.RED);
		                
		                rect.setTranslateX(x);rect.setTranslateY(y);
		       
		                Text val = new Text(value);
		                val.setTranslateX(x);val.setTranslateY(y);
		                this.getChildren().addAll(rect,val);
		                
		            }
		            
		        }


		
		/**
		 * @param lenSnake the integer that decides what length of the snake is initialized to.
		 * @throws IOException
		 * This method initializes various aspects of the game and creates the graphic content. It tanes an int as input and initialises length of snake to that number
		 */
		public void go(int lenSnake) throws IOException {
			this.setResizable(false);
			score=0;
		    Scene sc = new Scene(createContent());
		    this.setScene(sc);
		    bits = new ArrayList<>();
		    exp = new Explosion();
		    snake1 = new Snake();
		   // snake1.len = lenSnake;
		    snake1.addNo(lenSnake);
		    Button bnSel = new Button("OK");
		    pn.getChildren().add(bnSel);
		    bnSel.setTranslateX(widthOfScreen/2+100);
		    pn.getChildren().addAll(snake1.snakeV);
		    pn.setPrefSize(widthOfScreen+50, heightOfScreen);
		    pn.getChildren().addAll(scoreTxt,lenTxt);
		    lenTxt.setTranslateY(initYposOfsnake-20);
		    scoreTxt.setTranslateX(widthOfScreen-30);
		    scoreTxt.setTranslateY(heightOfScreen-30);
		    ObservableList<String> options = 
				    FXCollections.observableArrayList(
				    	
				        "Restart",
				        "Exit"
				        
				    );
			final ComboBox comboBox = new ComboBox(options);
			comboBox.setTranslateX(widthOfScreen/2);
			bnSel.setOnMouseClicked(e->{
				String sel = comboBox.getValue().toString();
				if(sel.equalsIgnoreCase("exit")) {
					try {
						FileOutputStream writer = new FileOutputStream("file.txt");
						writer.write(("").getBytes());
						writer.close();
						snakeWriter.write(snake1.len+" ");
						snakeWriter.write(score+" ");
						snakeWriter.flush();
						snakeData.clear();snakeData.add(snake1.len);snakeData.add(score);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					this.close();
					//this.go();
				}
				else if (sel.equalsIgnoreCase("restart")) {
					GameScreen gmsc = new GameScreen();
					try {
						gmsc.go(5);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.close();
				}
			});
			pn.getChildren().add(comboBox);
			for (int i = 0;i<100;i++) {
				xbits.add(5*(rd.nextDouble()-rd.nextDouble()));
				ybits.add(5*(rd.nextDouble()-rd.nextDouble()));
			}
		    //scoreTxt.setViewOrder(100);
		    // Generate sprite
		    b1 = new ArrayList<>();
		    
		    for (int i=0;i<5;i++) {
		        b1.add(new Ball(i*100+(35),2000,rd.nextInt(3)+1,i));
		    }
		    b2 = new ArrayList<>();
		    
		    for (int i=0;i<5;i++) {
		        b2.add(new Ball(i*100+(35),2000,rd.nextInt(3)+1,i));
		    }
		    
		    bk = new ArrayList<>();
		    for (int i=0;i<5;i++) {
		        bk.add(new Block(i*100+10,2000,rd.nextInt(snake1.len+20)+1,i));
		    }
		    dbk = new ArrayList<>();
		    for (int i=0;i<5;i++) {
		        dbk.add(new DestroyBlock(i*100+35,2000,i));
		    }
		    shields = new ArrayList<>();
		    for (int i=0;i<5;i++) {
		        shields.add(new Shield(i*100+35,2000,i));
		    }
		    mgts = new ArrayList<>();
		    for (int i=0;i<5;i++) {
		        mgts.add(new Magnet(i*100+35,2000,i));
		    }
		    walls = new ArrayList<>();
		    for (int i =0 ;i<5;i++) {
		        walls.add(new Wall(80+100*i,2000,i));
		    }
		    //
		    
		    
		    pn.getChildren().addAll(bits);
		    pn.getChildren().addAll(b1);pn.getChildren().addAll(b2);
		    pn.getChildren().addAll(bk);
		    pn.getChildren().addAll(dbk);
		    pn.getChildren().addAll(shields);
		    pn.getChildren().addAll(mgts);
		    pn.getChildren().addAll(walls);
		    
		    this.show();
		    
		    lenTxt.setFont(Font.font("Verdana", 20));
		    scoreTxt.setFont(Font.font("Verdana", 12));
		    
		    sc.setOnKeyPressed(e->{
		        switch(e.getCode()) {
		            case A:
		                snake1.moveLeft();
		                
		                break;
		            case L:
		            	immortal = true;break;
		            case K:
		            	immortal = false;break;
		            case D:
		                snake1.moveRight();
		                break;
		            case P:
		            	if(!gameOver) {
			                double prev=1.5;
			                if(moveDownSpeed==0) {
			                    moveDownSpeed = prev;
			                    pause = false;
			                }else {
			                    prev = moveDownSpeed;
			                    moveDownSpeed=0;
			                    pause = true;
			                }
		            	}
		        }
		    });
		    
		}
		/**
		 * This method runs in a loop and updates various aspects of game like position of tokens, score etc
		 */
		void update() {
			
		    time+=0.01;
		    //int forBall=0,forBlock=0,forDes=0;
		    
		    //
		    //exp.adapt();
		    timeSinceExp+=0.02;
		    
		    //
		    if(timeSinceExp<1) {
		    	exp.adapt();
		    }else {
		    	exp.disp();
		    }
		    if(time>5 && !pause) {
		        
		        time=2;
		    //    System.out.println("Length of snake : "+(snake1.len+1));
		        
		    }
		    if(!pause && !gameOver)// Change speed of snake based on length
		        
		        moveDownSpeed = initspeed+(0.03*snake1.len);
		    if(gameOver && !pause && !immortal) {
		        //System.out.println("Game over");
		     //   System.out.println("adding b1");
		       //Collections.sort(arrLead);
		        moveDownSpeed=0;
		        exp.disp();System.out.println("Game over disp");
		        pause = true;
		        
		        	System.out.println("// ");
		        	arrLead.add(new pair(score,today_date));
		        
		        	try {
		        			oo.println("/*/*/*/*/*/*/* written /*/*/*/*/*/*/*");
		        			mm = new FileWriter("LastScore.txt");
		        			wr2.write(""+score);
		        			wr2.flush();
							out.writeObject(new pair(score,today_date));
							out.flush();
						//	System.out.println("Written "+score+" "+wr.toString());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
       
		        	//Collections.sort(arrLead);
		        try {
		        	tnew2.setText(" "+score);
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        this.close();
		        return;
		    }
		    else if(pause) {}
		    else {
		        //for wall
		        for (int u=0;u<walls.size();u++) {
		            Wall bx= walls.get(u);
		            bx.moveDown();
		            if(bx.getTranslateY()>heightOfScreen+10*radOfBall) {
		            	int ra;
		            	do {
			                ra = rd.nextInt(20*uncertainity);
			                }while(mod(-ra-bk.get(u).getTranslateY())<spaceToLeave  
			                		|| mod(-ra-dbk.get(u).getTranslateY())<spaceToLeave || 
			                        mod(-ra-shields.get(u).getTranslateY())<spaceToLeave
			                        ||mod(-ra-b2.get(u).getTranslateY())<spaceToLeave
			                        ||mod(-ra-b1.get(u).getTranslateY())<spaceToLeave
			                        ||mod(-ra-mgts.get(u).getTranslateY())<spaceToLeave
			                        );
		                bx.setTranslateY(-ra-lenOfWall);
		                    
		            }
		            bx.setVisible(true);
		        }
		        //For ball
		        for (int u = 0;u<b1.size();u++) {
		            
		            Ball bx = b1.get(u);
		            bx.moveDown();
		            
		            if(bx.getTranslateY()>heightOfScreen+2*radOfBall) {
		                int ra;
		                do {
		                ra = rd.nextInt(uncertainity);
		                }while(mod(-ra-bk.get(u).getTranslateY())<spaceToLeave  || mod(-ra-dbk.get(u).getTranslateY())<spaceToLeave || 
		                        mod(-ra-shields.get(u).getTranslateY())<spaceToLeave||mod(-ra-b2.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-mgts.get(u).getTranslateY())<spaceToLeave
		                        );
		                
		                bx.setTranslateY(-ra);
		            //    bx.setTranslateX(bx.id*100+10);
		                bx.setValue(rd.nextInt(5)+1);
		                bx.val.setText(""+bx.getValue());
		                bx.setVisible(true);
		                bx.setAlive(true);
		            }
		            if (bx.hasCollideWithBall()) {
		        //        System.out.println("adding length");
		                snake1.addNo(bx.getValue());
		            }
		        }
		        
		        for (int u = 0;u<b1.size();u++) {
		            
		            Ball bx = b2.get(u);
		            bx.moveDown();
		            
		            if(bx.getTranslateY()>heightOfScreen+2*radOfBall) {
		                int ra;
		                do {
		                ra = rd.nextInt(uncertainity);
		                }while(mod(-ra-bk.get(u).getTranslateY())<spaceToLeave  || mod(-ra-dbk.get(u).getTranslateY())<spaceToLeave || 
		                        mod(-ra-shields.get(u).getTranslateY())<spaceToLeave||mod(-ra-b1.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-mgts.get(u).getTranslateY())<spaceToLeave
		                        );
		                
		                bx.setTranslateY(-ra);
		            //    bx.setTranslateX(bx.id*100+10);
		                bx.setValue(rd.nextInt(5)+1);
		                bx.val.setText(""+bx.getValue());
		                bx.setVisible(true);
		                bx.setAlive(true);
		            }
		            if (bx.hasCollideWithBall()) {
		        //        System.out.println("adding length");
		                snake1.addNo(bx.getValue());
		            }
		        }


		        //For block
		        trackLen++;
		        trackLen%=5;  // to equate with id
		        for (int u = 0;u<bk.size();u++) {
		            
		            Block bx = bk.get(u);
		            bx.moveDown();
		           // System.out.println(bx.id+":"+bx.getTranslateY());
		            if(bx.getTranslateY()>heightOfScreen+2*radOfBall) {
		                bx.rect.setFill(Color.rgb(rd.nextInt(120)+100, rd.nextInt(120)+100, rd.nextInt(120)+100));
		                int ra;
		                do {
		                ra = rd.nextInt(2*uncertainity);
		                }while(mod(-ra-b1.get(u).getTranslateY())<spaceToLeave  || mod(-ra-dbk.get(u).getTranslateY())<spaceToLeave ||
		                        mod(-ra-shields.get(u).getTranslateY())<spaceToLeave||mod(-ra-b2.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-mgts.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-walls.get(u).getTranslateY())<spaceToLeave
		                        );
		                
		                
		                bx.setTranslateY(-ra);
		            //    bx.setTranslateX(bx.id*100);
		                
		                if(u==trackLen && snake1.len>1) bx.setValue(rd.nextInt(snake1.len-1)+1);
		                else {
		                    bx.setValue(rd.nextInt(snake1.len+10)+1);
		                }
		                bx.val.setText(""+bx.getValue());
		                bx.setVisible(true);
		                bx.setAlive(true);
		            }
		            try {
		                if (bx.hasCollideWithBlock() ) {
		                	
		            //        System.out.println("dec length");
		                    if (!hasShield) {
		                        snake1.hideNo(bx.getValue());
		                    }
		                    if(!gameOver) {
		                    	timeSinceExp = 0;
		                        score+=bx.getValue();
		                    }
		                    exp.assembleAt(bx.getTranslateX(), bx.getTranslateY(),bx.rect.getFill());
		                    
		                }
		            } catch (InterruptedException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		        }
		        boolean allR = true;
		        
		        int rand = rd.nextInt(10);
		        for (int u = 0;u<bk.size();u++) {
		            
		            
		            if(bk.get(u).getTranslateY()>-50||snake1.len<2||rand>5||time<2) {
		                allR= false;break;
		            }
		        }
		        
		        if(allR) {
		        	System.out.println("True...");
		            for (int u = 0;u<bk.size();u++) {
		                if(u==trackLen) {
		                    bk.get(u).setValue( rd.nextInt(snake1.len-1)+1) ;
		                }
		                
		                bk.get(u).setTranslateY(-49);
		            }
		        }
		        // FOR MAGNETS
		        
		        for (int u = 0;u<mgts.size();u++) {
		            Magnet bx = mgts.get(u);
		            bx.moveDown();
		            if(bx.getTranslateY()>heightOfScreen+2*radOfBall) {
		                int ra;
		                do {
		                ra = rd.nextInt(15*uncertainity);
		                }while(mod(-ra-bk.get(u).getTranslateY())<spaceToLeave  || mod(-ra-b1.get(u).getTranslateY())<spaceToLeave ||
		                        mod(-ra-shields.get(u).getTranslateY())<spaceToLeave||mod(-ra-b2.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-dbk.get(u).getTranslateY())<spaceToLeave
		                        );
		                
		                bx.setTranslateY(-ra);
		                
		                bx.setVisible(true);
		                bx.setAlive(true);
		            }
		            if(bx.hasCollideWithMagnet()) {
		            	for (SnakeC x:snake1.snakeV) {
		                    x.setFill(Color.BLACK);
		                }
		            }
		        }
		        if(hasMagnet) {
		        	for (int u = 0;u<b1.size();u++) {
		                //    double dist_2 = (b1.get(u).getTranslateX()-snake1.head.getTranslateX())*(b1.get(u).getTranslateX()-snake1.head.getTranslateX())
		                //            + (b1.get(u).getTranslateY()-snake1.head.getTranslateY())*(b1.get(u).getTranslateY()-snake1.head.getTranslateY())
		                    if (b1.get(u).getAlive() &&
		                            (b1.get(u).getTranslateX()-snake1.head.getTranslateX())*(b1.get(u).getTranslateX()-snake1.head.getTranslateX())
		                            + (b1.get(u).getTranslateY()-snake1.head.getTranslateY())*(b1.get(u).getTranslateY()-snake1.head.getTranslateY())
		                            <sq_of_mgt_dist
		                            ) 
		                    {
		                        snake1.addNo(b1.get(u).getValue());b1.get(u).setVisible(false);
		                        b1.get(u).setAlive(false);
		                    }
		                }
		                for (int u = 0;u<b2.size();u++) {
		                    
		                        if (b2.get(u).getAlive() &&
		                                (b2.get(u).getTranslateX()-snake1.head.getTranslateX())*(b2.get(u).getTranslateX()-snake1.head.getTranslateX())
		                                + (b2.get(u).getTranslateY()-snake1.head.getTranslateY())*(b2.get(u).getTranslateY()-snake1.head.getTranslateY())<sq_of_mgt_dist
		                                ) 
		                        {
		                            snake1.addNo(b2.get(u).getValue());
		                            b2.get(u).setVisible(false);b2.get(u).setAlive(false);
		                        }
		                    }
		        }
		        // for DESTROY BLOCKS
		        for (int u = 0;u<dbk.size();u++) {
		            
		            DestroyBlock bx = dbk.get(u);
		            bx.moveDown();
		            
		            if(bx.getTranslateY()>heightOfScreen+2*radOfBall) {
		                int ra;
		                do {
		                ra = rd.nextInt(15*uncertainity);
		                }while(mod(-ra-bk.get(u).getTranslateY())<spaceToLeave  || mod(-ra-b1.get(u).getTranslateY())<spaceToLeave ||
		                        mod(-ra-shields.get(u).getTranslateY())<spaceToLeave||mod(-ra-b2.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-mgts.get(u).getTranslateY())<spaceToLeave
		                        );
		                
		                
		                bx.setTranslateY(-ra);
		            //    bx.setTranslateX(bx.id*100);
		            //    bx.value = rd.nextInt(snake1.len+10)+1;
		            //    bx.val.setText(""+bx.value);
		                bx.setVisible(true);
		                bx.setAlive(true);
		            }
		            bx.hasCollideWithDestroyBlock();
		        
		        }
		        
		        for (int u = 0;u<shields.size();u++) {
		            
		            Shield bx = shields.get(u);
		            bx.moveDown();
		            
		            if(bx.getTranslateY()>heightOfScreen+2*radOfBall) {
		                int ra;
		                do {
		                ra = rd.nextInt(20*uncertainity);
		                }while(mod(-ra-bk.get(u).getTranslateY())<spaceToLeave  || mod(-ra-dbk.get(u).getTranslateY())<spaceToLeave || 
		                        mod(-ra-b1.get(u).getTranslateY())<spaceToLeave||mod(-ra-b2.get(u).getTranslateY())<spaceToLeave
		                        ||mod(-ra-mgts.get(u).getTranslateY())<spaceToLeave
		                        );
		                
		                bx.setTranslateY(-ra);
		        //        bx.setTranslateX(bx.id*100);
		            //    bx.value = rd.nextInt(snake1.len+10)+1;
		            //    bx.val.setText(""+bx.value);
		                bx.setVisible(true);
		                bx.setAlive(true);
		            }
		            if (bx.hasCollideWithShield()) {
		            //    System.out.println("shield");
		                
		                for (SnakeC x:snake1.snakeV) {
		                    x.setFill(Color.RED);
		                }
		                shieldTime = System.currentTimeMillis();
		            }
		        }
		        if(System.currentTimeMillis()>shieldTime+5000 && hasShield) {
		            System.out.println("Switching off shield");
		            hasShield=false;
		           
		        }
		        if(System.currentTimeMillis()>magnetTime+5000 && hasMagnet) {
		        	hasMagnet = false;
		        	
		        }
		        if(!hasShield && !hasMagnet) {
		        	for (SnakeC x:snake1.snakeV) {
		                 x.setFill(Color.DARKCYAN);
		             }
		        }
		        if(hasShield && !hasMagnet) {
		       	for (SnakeC x:snake1.snakeV) {
		                x.setFill(Color.RED);
		            }
		       }
		        if(!hasShield && hasMagnet) {
		       	for (SnakeC x:snake1.snakeV) {
		                x.setFill(Color.BLACK);
		            }
		       }
		        if(hasShield && hasMagnet) {
		       	for (SnakeC x:snake1.snakeV) {
		                x.setFill(Color.DARKRED);
		            }
		       }
		        
		    }
		}
		private Parent createContent() {
		    // TODO Auto-generated method stub
		    //VBox vb = new VBox();
			
			//pn.getChildren().add(bl);
		    
		//   bl.setVisible(false);
//		    pn.setBackground(new Background());
//		    vb.getChildren().addAll(pn);
		    AnimationTimer at = new AnimationTimer() {
		        
		        @Override
		        public void handle(long now) {
		            
		            update();
		           
		            scoreTxt.setText("score:"+score);
		            lenTxt.setText(""+(snake1.len+1));
		            lenTxt.setTranslateX(snake1.head.getTranslateX());    
		            /*
		            long t = (System.currentTimeMillis());
		            if(t%10000<50) {
		                System.out.println(t);
		            }
		            */
		            
		            
		        }
		    };
		    at.start();
		    
		    return pn;
		}
		double mod(double x) {
		    if (x<0) return -x;
		    return x;
		}
		double minm(double a,double b,double c,double d) {
		    double mm = Double.MAX_VALUE;
		    if(a<mm)mm=a;
		    if(b<mm)mm=b;
		    if(c<mm)mm=c;
		    if(c<mm)mm=d;
		    return mm;
		}
		}

}


class LeaderBoard extends Stage{
	TextArea t;
	
    public LeaderBoard() {
		// TODO Auto-generated constructor stub
    	VBox p = new VBox();
    	this.setResizable(false);
    	Button jb1 = new Button("Close");
    	jb1.setTranslateX(180);
    	jb1.setTranslateY(130);
    	jb1.setOnMouseClicked(e->{
    		this.close();
    	});
    	t = new TextArea();
    	t.setPrefSize(200, 200);
    	t.setEditable(false);
    	p.setPrefSize(400, 400);
    	Text txtLead = new Text("Leader Board");
    	t.setTranslateX(0);t.setTranslateY(60);
    	txtLead.setTranslateX(170);txtLead.setTranslateY(20);
    	p.getChildren().add(txtLead);
    	p.getChildren().addAll(t);
    	p.getChildren().add(jb1);
    	
    	this.setScene(new Scene(p));
    	this.show();
	}
}
