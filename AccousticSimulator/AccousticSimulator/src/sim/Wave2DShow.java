package sim;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;

public class Wave2DShow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButton = null;

	private Wave2DArea wave2D;
	
	private long counter;
	
	private MouseEvent lastMouseEvent = null;
	
	/**
	 * This is the default constructor
	 */
	public Wave2DShow() {
		super();
		initialize();
		wave2D = new Wave2DArea(getWidth(), getHeight());
		Timer timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step();
			}
		});
		timer.start();
	}

	private void step() {
		counter++;
		
		//excitate
		if(lastMouseEvent!=null) {
			wave2D.setPos(lastMouseEvent.getX(), lastMouseEvent.getY()+20, (float)Math.cos(Math.toRadians(counter*3)) * 200);
		}
		
		//step tank
		wave2D.step();
		
		//draw wave tank
		BufferedImage im = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_BYTE_GRAY);
		float[][] pos = wave2D.getPos();
		for(int x=0; x<wave2D.getWidth(); x++) {
			for(int y=0; y<wave2D.getHeight(); y++) {
				int v = 0x80 + (int)pos[x][y];
				im.setRGB(x,y,v*0x10101);
			}
		}
		getGraphics().drawImage(im, 0, 0, new ImageObserver() {
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return true;
			}
		});
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(410, 333);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridy = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJButton(), gridBagConstraints);
			jContentPane.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					lastMouseEvent = e;
				}
				public void mouseReleased(MouseEvent e) {
					lastMouseEvent = null;
				}
			});
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Do!");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					step();
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
