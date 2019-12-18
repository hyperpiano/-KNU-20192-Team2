package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Person.Employee;
import SW.UserTable;

public class present extends JDialog{
	JPanel MainPanel = new JPanel();
	JPanel TopPanel = new JPanel();
	JTextField url;
	JButton cancel = new JButton("취소");
	public present (JFrame frame) {
		setTitle("선물하기");
		add(MainPanel);
		MainPanel.add(TopPanel);
		TopPanel.setLayout(new GridLayout(1, 1));
		TopPanel.add(new JLabel("      선물하기 : "));
		TopPanel.add(url = new JTextField());
		url.setPreferredSize(new Dimension(120, 20));
		url.setText("www.naver.com");
		setSize(250, 100);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// 스크린의 중앙에 배치
		
		setResizable(false);
		setVisible(true);
		}
}


