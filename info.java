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

public class info extends JDialog{
	JPanel MainPanel = new JPanel();
	JPanel TopPanel = new JPanel();
	JPanel BottomPanel = new JPanel();
	JPanel addPanel = new JPanel();
	
	JButton submit;
	JButton cancel = new JButton("���");

	JTextField name;
	JTextField id;
	JTextField depart;
	JTextField type;
	JTextField address;
	JTextField phone;
	JTextField email;
	
	public info (JFrame frame,Object obj[]) {
		super(frame,true);
		setTitle("�� ����");
		add(MainPanel);
		MainPanel.add(TopPanel);
		MainPanel.add(BottomPanel);
		
		TopPanel.setLayout(new GridLayout(4, 2));
		
		TopPanel.add(new JLabel("      ID : "));
		TopPanel.add(id = new JTextField());
		id.setPreferredSize(new Dimension(120, 20));	// id�� ũ�� �������൵ ��� Grid�� �����
		id.setText(obj[0].toString());
		id.setEditable(false);
		
		TopPanel.add(new JLabel("      �̸� : "));
		TopPanel.add(name = new JTextField());
		name.setText(obj[1].toString());
		name.setEditable(false);
		
		
		TopPanel.add(new JLabel("      �μ� : "));
		TopPanel.add(depart = new JTextField());
		depart.setText(obj[2].toString());
		depart.setEditable(false);
		
		TopPanel.add(new JLabel("       ��å : "));
		TopPanel.add(type = new JTextField());
		type.setText(obj[3].toString());
		type.setEditable(false);
		
		TopPanel.add(new JLabel("       �ּ� : "));
		TopPanel.add(address = new JTextField());
		address.setEditable(false);
		
		TopPanel.add(new JLabel("       ��ȭ ��ȣ : "));
		TopPanel.add(phone = new JTextField());
		phone.setEditable(false);
		
		TopPanel.add(new JLabel("       �̸��� : "));
		TopPanel.add(email = new JTextField());
		email.setEditable(false);
		
		BottomPanel.add(cancel);
		
		cancel.addMouseListener(new cancelButtonMouseListener());
		
		setSize(500, 300);
		
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// ��ũ���� �߾ӿ� ��ġ
		
		setResizable(false);
		setVisible(true);
		}
	
	class cancelButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			dispose();
		}
	}
}