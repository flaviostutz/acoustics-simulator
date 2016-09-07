import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

/**
 * Demonstration of the 2D wave equation.
 * @author <a href="mailto:nagydani@mast.queensu.ca">Daniel A. Nagy</a>
 */
public class CopyOfWave2D
  extends Applet
  implements ActionListener,MouseListener,MouseMotionListener
{
   public static final int SIZE=200;
   /* periodic excitation: d^2(ex)/(dt)^2=-c*ex */
   private double ex=0; // value
   private double dex=100; // d/dt
   /* sample array */
   private double[] f=new double[SIZE*SIZE]; // value
   private double[] df=new double[SIZE*SIZE]; // d/dt
   /* image */
   private BufferedImage im=
     new BufferedImage(SIZE,SIZE,BufferedImage.TYPE_BYTE_GRAY);
   /* mouse */
   boolean pressed=false;
   int mx,my;
   /* timer */
   Timer t=new Timer(10,this);

   /* initialization */
   public void init()
     {
	addMouseListener(this);
	addMouseMotionListener(this);
     }

   /* start */
   public void start()
     {
	t.start();
     }

   /* stop */
   public void stop()
     {
	t.stop();
     }

   /* every 10 milliseconds */ 
   public void actionPerformed(ActionEvent e)
     {
	int x,y,z,off;
	double avg;
	Graphics g=getGraphics();
	/* excitation */
	dex-=ex*0.1;
	ex+=dex;
	/* f:=f+df */
	y=SIZE*SIZE;
	while(y-->0) f[y]+=df[y];
	/* df:=df+ddf */
	y=SIZE-1;
	while(y-->1)
	  {
	     x=SIZE-1;
	     while(x-->1)
	       {
		  off=SIZE*y+x;
		  avg=(f[off-1]+f[off+1]+f[off-SIZE]+f[off+SIZE])/4;
		  df[off]-=0.5*(f[off]-avg);
	       }
	  }
	/* graphics */
	y=SIZE;
	while(y-->0)
	  {
	     x=SIZE;
	     while(x-->0){
		z=0x80+(int)f[SIZE*y+x];
		im.setRGB(x,y,z*0x10101);
	     }
	  }
	if(pressed&&(mx>=0)&&(my>=0)&&(mx<SIZE)&&(my<SIZE))
	  {
	     f[SIZE*my+mx]=ex;
	     df[SIZE*my+mx]=dex;
	  }	
	g.drawImage(im,0,0,this);
     }
   
   public void mouseClicked(MouseEvent e)
     {
     }
   
   public void mouseEntered(MouseEvent e)
     {
     }
   
   public void mouseExited(MouseEvent e)
     {
     }
   
   public void mousePressed(MouseEvent e)
     {
	pressed=true;
     }
   
   public void mouseReleased(MouseEvent e)
     {
	pressed=false;
     }
   
   public void mouseDragged(MouseEvent e)
     {
	mx=e.getX();
	my=e.getY();
     }
   
   public void mouseMoved(MouseEvent e)
     {
	mx=e.getX();
	my=e.getY();
     }   
}
