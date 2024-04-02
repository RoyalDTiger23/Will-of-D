/* Game0.java
 * This example uses two classes for the basic set-up. There are two reasons for this.
 * 	1. To fix the coordinate problem.
 *     If you draw on the JFrame, (0,0) if the top-left of the frame, above the title bar.
 *  2. Fix flickering
 *     JPanels implement what is called "double buffering." Without this your graphics 
 *     flicker when you draw images to the screen.
 **/
 
 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;
 import java.util.ArrayList;
 
 // Main game logic                        Interface
 //                                        
 class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
     Timer timer;
     int boxx = 0;
     boolean []keys;
     Image back;
     Font fnt;
     
     public Image loadImage(String str){
         return  new ImageIcon(str).getImage();
     }
     
     
     public void move(){
         if(keys[KeyEvent.VK_A]){
             boxx -= 5;
         }
         if(keys[KeyEvent.VK_D]){
             boxx += 5;
         }		
     }
     
     @Override
     public void actionPerformed(ActionEvent e){
         move();
         repaint();
     }
     @Override
     public void keyPressed(KeyEvent e){
         keys[e.getKeyCode()] = true;
     }
     @Override
     public void keyReleased(KeyEvent e){
         keys[e.getKeyCode()] = false;		
     }
     @Override
     public void keyTyped(KeyEvent e){}
     @Override
     public void	mouseClicked(MouseEvent e){}
 
     @Override
     public void	mouseEntered(MouseEvent e){}
 
     @Override
     public void	mouseExited(MouseEvent e){}
     
     @Override
     public void	mousePressed(MouseEvent e){
         int x = e.getX();
         int y = e.getY();
         Rectangle rec = new Rectangle(boxx, 200, 40, 40);
         if(rec.contains(x,y)){
             System.out.printf("%d %d\n",x, y);
         } 
     }
     
     @Override
     public void	mouseReleased(MouseEvent e){}
 
     public GamePanel(){
         setPreferredSize(new Dimension(800, 600));
         back = loadImage("OuterSpace.jpg");
         
         fnt = new Font("Comic Sans MS", Font.PLAIN, 32);
             
         keys = new boolean[1000];
         
         timer = new Timer(20, this);
         timer.start();
         setFocusable(true);
         requestFocus();
         addKeyListener(this);
         addMouseListener(this);
     }
 
     @Override
     public void paint(Graphics g){
         //g.setColor(new Color(111,222,111));
         //g.fillRect(0,0,getWidth(),getHeight());
         g.drawImage(back, 0, 0, null);
         g.setColor(Color.RED);
         g.fillRect(boxx,200,40,40);
         
         Point mouse = MouseInfo.getPointerInfo().getLocation();
         Point offset = getLocationOnScreen();
         int mx = mouse.x - offset.x;
         int my = mouse.y - offset.y;
         g.setFont(fnt);
         g.drawString(String.format("(%d, %d)", mx, my), 50, 50);
     }
     
 }