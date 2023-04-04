package auto_click;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class AutoClick extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoClick auto_click = new AutoClick("Auto Click");
		
		auto_click.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		auto_click.setVisible(true);
	}
	
	AutoClick(String title) {
		LeftClickRun runTh = new LeftClickRun();
		Thread th = new Thread(runTh);
		th.start();
		
		setTitle(title);
		setBounds( 10, 10, 300, 200);
		
		JLabel label = new JLabel("");
		
		JButton onBtn = new JButton("On");
		onBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runTh.setRunState(true);
			}
		});
		JButton offBtn = new JButton("Off");
		offBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runTh.setRunState(false);
			}
		});
		
		JPanel p = new JPanel();
		p.add(label);
		p.add(onBtn);
		p.add(offBtn);
		
		getContentPane().add(p);
	}
}

class LeftClickRun implements Runnable {
	private boolean isRunning = false;
	public void run() {
		try {
			Robot r = new Robot();
			while(true) {
				Thread.sleep(1);
				if (isRunning) {
					r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(1);
					r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				}
			}
		} catch(AWTException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setRunState(boolean bool) {
		this.isRunning = bool;
	}
}
