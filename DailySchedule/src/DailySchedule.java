/**
* The MIT License (MIT)
* 
* Copyright (c) 2015 Shubhrendu Tripathi
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
* 
**/

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class DailySchedule {
	
    private enum CBStatus { NORMAL, RED, GREEN }

    private static final long serialVersionUID = 1L;
    private JFrame frame = new JFrame();
    private JButton jButton1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private CBStatus cbs;
    private XMLRead xr;
    
    private final List<JCheckBox> checkBoxes = new LinkedList<JCheckBox>();

    public DailySchedule() {
    	try {
			xr = new XMLRead("daily.xml");
			xr.getTasks();
		} 
    	catch (ParserConfigurationException e) { e.printStackTrace(); } 
    	catch (SAXException e) { e.printStackTrace(); } 
    	catch (IOException e) { e.printStackTrace(); }
    	
        jPanel1 = new JPanel();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jScrollPane1 = new JScrollPane(jPanel1);
        jButton1 = new JButton("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	for (JCheckBox checkBox : checkBoxes) {
            	      if (checkBox.isSelected()) {
            	    	  checkBox.setSelected(false);
            	      }
            	}            	
            }
        });
        frame.add(jScrollPane1);
        frame.add(jButton1, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                //if (JOptionPane.showConfirmDialog(frame, 
                //    "Are you sure to close this window?", "Really Closing?", 
                //    JOptionPane.YES_NO_OPTION,
                //    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                //    System.exit(0);
                //}
            }
        });
        
        frame.setTitle("Daily Schedule");
        ImageIcon img = new ImageIcon("res/dailyschedule.png");
        frame.setIconImage(img.getImage());
        frame.setSize(400, 600);
        //frame.pack();
        frame.setLocationRelativeTo(null);
		
		for(Task t : xr.tasks){
			addCB(createCBDesc(t), t.getChecked());
		}
		
        frame.setVisible(true);
    }
    
    public String createCBDesc(Task t){
    	String strTemp = "";
    	
    	strTemp += t.getHour() + ":";
    	strTemp += t.getMinute() + " ";
    	
    	if(t.getAM() == true){
    		strTemp += " AM ";
    	}
    	else{
    		strTemp += " PM ";
    	}
    	strTemp += t.getDesc();
    	
    	return strTemp;
    }
    
    public void addCB(String strDesc, boolean bChecked){
        JCheckBox cb = new JCheckBox(strDesc);
        CBStatus cbs = CBStatus.NORMAL;
        
        cb.setSelected(bChecked);
    	switch(cbs){
    	case NORMAL:
    		cb.setBackground(new Color(255,255,255));
    		cb.setForeground(Color.BLACK);
    		break;
    	case RED:
            cb.setBackground(new Color(200,0,0));
            cb.setForeground(Color.WHITE);
            break;
    	case GREEN:
            cb.setBackground(new Color(0,200,0));
            cb.setForeground(Color.WHITE);
            break;
    	}
        this.checkBoxes.add(cb); //add to global list of checkboxes
        
        jPanel1.add(cb);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public static void main(String args[]) {

        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }*/
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DailySchedule();
            }
        });
    }
}

