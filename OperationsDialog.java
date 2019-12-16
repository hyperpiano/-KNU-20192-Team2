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

public class OperationsDialog extends JDialog {
	JPanel MainPanel = new JPanel();
	JPanel TopPanel = new JPanel();
	JPanel BottomPanel = new JPanel();
	JPanel addPanel = new JPanel();
	JTextField id;
	JTextField name;
	JTextField address;
	JTextField email;
	JTextField phone;
	String addr;
	String mail;
	String num;
	JComboBox depart;
	JComboBox type;
	JComboBox manager;
	JComboBox crit1;
	JComboBox crit2;
	JButton submit;
	JButton cancel = new JButton("���");
	
	ButtonGroup group = new ButtonGroup();
	JRadioButton radioId = new JRadioButton("      ID :  ", true);
	JRadioButton radioName = new JRadioButton("      �̸� :  ");
	JRadioButton radioDepart = new JRadioButton("      �μ� :  ");
	JRadioButton radioManager = new JRadioButton("      �Ŵ��� :  ");
	
	String item1[] = {"�񼭽�", "�ѹ���", "�λ���", "����1��", "����2��", "����3��"};
	String item2[] = {"Staff"};
	String item3[] = {"SW Developer", "SW Tester"};
	String item4[] = new String[6];
	String item5[] = {"ID", "�̸�", "�μ�", "��å", "�Ŵ���"};
	String item6[] = {"��������", "��������"};
	
	public OperationsDialog (JFrame frame, String cmd) {
		super(frame, cmd, true);
		setTitle(cmd);
		add(MainPanel);
		MainPanel.add(TopPanel);
		MainPanel.add(addPanel);
		MainPanel.add(BottomPanel);
		
		
		if(cmd.equals("�ű����� �߰�")) {
			TopPanel.setLayout(new GridLayout(4, 2));
			submit = new JButton("Ȯ��");
			TopPanel.add(new JLabel("      ID : "));
			TopPanel.add(id = new JTextField());
			id.setPreferredSize(new Dimension(120, 20));	// id�� ũ�� �������൵ ��� Grid�� �����
			
			TopPanel.add(new JLabel("      �̸� : "));
			TopPanel.add(name = new JTextField());
			
			TopPanel.add(new JLabel("      �μ� : "));
			TopPanel.add(depart = new JComboBox(item1));
			depart.addItemListener(new DepartItemListener());
			
			TopPanel.add(new JLabel("      ��å : "));
			TopPanel.add(type = new JComboBox(item2));
			
			addPanel.setLayout(new GridLayout(3, 2));
			
			addPanel.add(new JLabel("                               �ּ� : "));
			addPanel.add(address = new JTextField());
			address.setPreferredSize(new Dimension(200, 20));
			
			addPanel.add(new JLabel("                              e-mail :"));
			addPanel.add(email = new JTextField());
			
			addPanel.add(new JLabel("                             ��ȭ��ȣ :"));
			addPanel.add(phone = new JTextField());
			
			BottomPanel.add(submit);
			BottomPanel.add(cancel);
			
			submit.addMouseListener(new submitButtonMouseListener());
			cancel.addMouseListener(new cancelButtonMouseListener());
			
			setSize(500, 300);
		}
		else if(cmd.equals("��� ����")) {
			submit = new JButton("Ȯ��");
			TopPanel.setLayout(new GridLayout(2, 2));

			TopPanel.add(new JLabel("  ���� ���� : "));
			TopPanel.add(crit1 = new JComboBox(item5));
			crit1.setPreferredSize(new Dimension(80, 20));
			
			TopPanel.add(new JLabel("  ���� ��� : "));
			TopPanel.add(crit2 = new JComboBox(item6));
			BottomPanel.add(submit);
			BottomPanel.add(cancel);
			
			submit.addMouseListener(new sortButtonMouseListener());
			cancel.addMouseListener(new cancelButtonMouseListener());
			
			setSize(200, 150);
		}
		else {	// ����, ����, �˻� => �켱 �˻� �� ���� or ����
			TopPanel.setLayout(new GridLayout(4, 2));
			setItem4();
			
			group.add(radioId);
			group.add(radioName);
			group.add(radioDepart);
			group.add(radioManager);
			
			submit = new JButton("�˻�");
			TopPanel.add(radioId);
			TopPanel.add(id = new JTextField());
			id.setPreferredSize(new Dimension(120, 20));
			
			TopPanel.add(radioName);
			TopPanel.add(name = new JTextField());
			
			TopPanel.add(radioDepart);
			TopPanel.add(depart = new JComboBox(item1));
			
			TopPanel.add(radioManager);
			TopPanel.add(manager = new JComboBox(item4));
			
			BottomPanel.add(submit);
			BottomPanel.add(cancel);
			
			radioId.addActionListener(new RadioButtonListener());
			radioName.addActionListener(new RadioButtonListener());
			radioDepart.addActionListener(new RadioButtonListener());
			radioManager.addActionListener(new RadioButtonListener());
			
			submit.addMouseListener(new searchButtonMouseListener());
			cancel.addMouseListener(new cancelButtonMouseListener());
			
			id.setEnabled(true);
			name.setEnabled(false);
			depart.setEnabled(false);
			manager.setEnabled(false);
			setSize(250, 200);
		}
		
		
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// ��ũ���� �߾ӿ� ��ġ
		
		setResizable(false);
		setVisible(true);
	}
	public OperationsDialog(JFrame frame, Object obj[]) {		// ����
		super(frame, true);
		setTitle("�������� ����");
		add(MainPanel);
		MainPanel.add(TopPanel);
		MainPanel.add(addPanel);
		MainPanel.add(BottomPanel);
	
		TopPanel.setLayout(new GridLayout(4, 2));
		
		submit = new JButton("����");
		TopPanel.add(new JLabel("      ID : "));
		TopPanel.add(id = new JTextField());
		id.setPreferredSize(new Dimension(120, 20));	// id�� ũ�� �������൵ ��� Grid�� �����
		id.setText(obj[0].toString());
		id.setEditable(false);
			
		TopPanel.add(new JLabel("      �̸� : "));
		TopPanel.add(name = new JTextField());
		name.setText(obj[1].toString());
		
		TopPanel.add(new JLabel("      �μ� : "));
		TopPanel.add(depart = new JComboBox(item1));
		depart.setSelectedItem(obj[2]);
		depart.addItemListener(new DepartItemListener());
			
		TopPanel.add(new JLabel("      ��å : "));
		if(obj[2].toString().equals("�񼭽�") || obj[2].toString().equals("�ѹ���")|| obj[2].toString().equals("�λ���"))
			TopPanel.add(type = new JComboBox(item2));
		else
			TopPanel.add(type = new JComboBox(item3));
		type.setSelectedItem(obj[3]);
		
		addPanel.setLayout(new GridLayout(3,2));
		
		addPanel.add(new JLabel("                               �ּ� : "));
		addPanel.add(address = new JTextField());
		address.setPreferredSize(new Dimension(200, 20));	// id�� ũ�� �������൵ ��� Grid�� �����
		System.out.println(this.addr);
		address.setText(this.addr);
		
		addPanel.add(new JLabel("                               email : "));
		addPanel.add(email = new JTextField());
		System.out.println(this.mail);
		email.setText(this.mail);
		
		addPanel.add(new JLabel("                               ��ȭ��ȣ : "));
		addPanel.add(phone = new JTextField());
		System.out.println(this.num);
		email.setText(this.num);
		
		BottomPanel.add(submit);
		BottomPanel.add(cancel);
			
		submit.addMouseListener(new correctButtonMouseListener());
		cancel.addMouseListener(new cancelButtonMouseListener());
		
		setSize(500, 300);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// ��ũ���� �߾ӿ� ��ġ
		
		setResizable(false);
		setVisible(true);
	}
	
	public void setItem4() {
		for(int i = 0; i < item4.length; i++)
			item4[i] = UserTable.departTable[i][1];
	}
	
	class DepartItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			String item = e.getItem().toString();
			if(item.equals("�񼭽�") || item.equals("�ѹ���") || item.equals("�λ���")) {
				if(type != null)
					type.removeAllItems();
				for(int i = 0; i < item2.length; i++)
					type.addItem(item2[i]);
			}
			else {
				if(type != null)
					type.removeAllItems();
				for(int i = 0; i < item3.length; i++)
					type.addItem(item3[i]);
			}
		}
	}
	
	class RadioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(radioId.isSelected()) {
				id.setEnabled(true);
				name.setEnabled(false);
				depart.setEnabled(false);
				manager.setEnabled(false);
			}
			else if(radioName.isSelected()) {
				id.setEnabled(false);
				name.setEnabled(true);
				depart.setEnabled(false);
				manager.setEnabled(false);
			}
			else if(radioDepart.isSelected()) {
				id.setEnabled(false);
				name.setEnabled(false);
				depart.setEnabled(true);
				manager.setEnabled(false);
			}
			else if(radioManager.isSelected()) {
				id.setEnabled(false);
				name.setEnabled(false);
				depart.setEnabled(false);
				manager.setEnabled(true);
			}
		}
	}
	
	class submitButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			Object[] temporaryObject = {id.getText(), name.getText(), depart.getSelectedItem(), type.getSelectedItem(), " ",address.getText(),email.getText(),phone.getText()};
			if(temporaryObject[0].toString().equals("") || temporaryObject[1].toString().equals("")) {
				new ErrorDialog(SW.Main.mainFrame, "ID�� �̸��� �ݵ�� �Է��ؾ��մϴ�!");
				return;
			}
			if(!SW.UserTable.checkId(id.getText())) {		// checkId�� TRUE�� �ߺ�����. FALSE�� �ߺ�.
				new ErrorDialog(SW.Main.mainFrame, "�ش� ID�� �̹� ������Դϴ�.");
				return;
			}
			addr = address.getText();
			num = phone.getText();
			mail = email.getText();
			System.out.println();
			System.out.println(addr);
			System.out.println(mail);
			System.out.println(num);
			SW.UserTable.addUser(temporaryObject);
			dispose();
			SW.HRSW.logging("�ű����� �߰� - ID(" + temporaryObject[0].toString() + ")");
		}
	}
	
	class sortButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(crit2.getSelectedItem().toString().equals("��������")) {		// �������� "ID", "�̸�", "�μ�", "��å", "�Ŵ���"
				if(crit1.getSelectedItem().toString().equals("ID"))
					Collections.sort(UserTable.User, new IdAscComparator());
				else if(crit1.getSelectedItem().toString().equals("�̸�"))
					Collections.sort(UserTable.User, new NameAscComparator());
				else if(crit1.getSelectedItem().toString().equals("�μ�"))
					Collections.sort(UserTable.User, new DepartAscComparator());
				else if(crit1.getSelectedItem().toString().equals("��å"))
					Collections.sort(UserTable.User, new TypeAscComparator());
				else if(crit1.getSelectedItem().toString().equals("�Ŵ���"))
					Collections.sort(UserTable.User, new ManagerAscComparator());
			}	
			else {		// ��������
				if(crit1.getSelectedItem().toString().equals("ID"))
					Collections.sort(UserTable.User, new IdDescComparator());
				else if(crit1.getSelectedItem().toString().equals("�̸�"))
					Collections.sort(UserTable.User, new NameDescComparator());
				else if(crit1.getSelectedItem().toString().equals("�μ�"))
					Collections.sort(UserTable.User, new DepartDescComparator());
				else if(crit1.getSelectedItem().toString().equals("��å"))
					Collections.sort(UserTable.User, new TypeDescComparator());
				else if(crit1.getSelectedItem().toString().equals("�Ŵ���"))
					Collections.sort(UserTable.User, new ManagerDescComparator());
			}
			
			MainFrame.refreshDBPane();
			dispose();
			SW.HRSW.logging("��� ���� - ����(" + crit1.getSelectedItem().toString() + "," + crit2.getSelectedItem().toString() + ")");
		}
	}
	
	class correctButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			Object[] temporaryObject = {id.getText(), name.getText(), depart.getSelectedItem(), type.getSelectedItem()};
			MainFrame.correctUser(temporaryObject);
			dispose();
			SW.HRSW.logging("�������� ���� - ID(" + temporaryObject[0].toString() + ")");
		}
	}
	
	class searchButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			Object[] tempObject = {id.getText(), name.getText(), depart.getSelectedItem(), manager.getSelectedItem()};
			int selected = -1;	// �˻�����
			/*
			 * ���� - 	ID : 0
			 * 		 	�̸� : 1
			 * 			�μ� : 2
			 * 			�Ŵ����� : 3
			 */
			
			if(radioId.isSelected())
				selected = 0;
			else if(radioName.isSelected())
				selected = 1;
			else if(radioDepart.isSelected())
				selected = 2;
			else if(radioManager.isSelected())
				selected = 3;
			
			dispose();
			MainFrame.searchingUser(tempObject, selected);
		}
	}
	
	class cancelButtonMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			dispose();
		}
	}
}

/*
 * Comparator...
 */

class IdAscComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg0).getId().compareTo(((Employee)arg1).getId());
	}
}

class IdDescComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg1).getId().compareTo(((Employee)arg0).getId());
	}
}

class NameAscComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg0).getName().compareTo(((Employee)arg1).getName());
	}
}

class NameDescComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg1).getName().compareTo(((Employee)arg0).getName());
	}
}

class DepartAscComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg0).getDepart().compareTo(((Employee)arg1).getDepart());
	}
}

class DepartDescComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg1).getDepart().compareTo(((Employee)arg0).getDepart());
	}
}

class TypeAscComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg0).getType().compareTo(((Employee)arg1).getType());
	}
}

class TypeDescComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg1).getType().compareTo(((Employee)arg0).getType());
	}
}

class ManagerAscComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg1).getManager().compareTo(((Employee)arg0).getManager());
	}
}

class ManagerDescComparator implements Comparator {
	public int compare(Object arg0, Object arg1) {
		return ((Employee)arg0).getManager().compareTo(((Employee)arg1).getManager());
	}
}
