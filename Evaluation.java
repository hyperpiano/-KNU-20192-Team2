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

import GUI.OperationsDialog.cancelButtonMouseListener;
import Person.Employee;
import SW.UserTable;

public class Evaluation extends JDialog{
	JPanel MainPanel = new JPanel();
	JPanel TopPanel = new JPanel();
	JPanel BottomPanel = new JPanel();
	JPanel addPanel = new JPanel();
	
	JButton submit;
	JButton cancel = new JButton("취소");

	JTextField name;
	JTextField id;
	JTextField score1;
	JTextField score2;
	
	public Evaluation (JFrame frame,Object obj[]) {
		super(frame,true);
		setTitle("직원 평가");
		add(MainPanel);
		MainPanel.add(TopPanel);
		MainPanel.add(BottomPanel);
		
		TopPanel.setLayout(new GridLayout(4, 2));
		
		TopPanel.add(new JLabel("      ID : "));
		TopPanel.add(id = new JTextField());
		id.setPreferredSize(new Dimension(120, 20));	// id만 크기 지정해줘도 모든 Grid에 적용됨
		id.setText(obj[0].toString());
		id.setEditable(false);
		
		TopPanel.add(new JLabel("      이름 : "));
		TopPanel.add(name = new JTextField());
		name.setText(obj[1].toString());
		name.setEditable(false);
		
		
		TopPanel.add(new JLabel("      실적 점수 : "));
		TopPanel.add(score1 = new JTextField());
		
		TopPanel.add(new JLabel("       업무 점수 : "));
		TopPanel.add(score2 = new JTextField());
		
		BottomPanel.add(cancel);
		

		cancel.addMouseListener(new cancelButtonMouseListener());
		
		setSize(250, 200);
		
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// 스크린의 중앙에 배치
		
		setResizable(false);
		setVisible(true);
		}
	
	class cancelButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			dispose();
		}
	}
}