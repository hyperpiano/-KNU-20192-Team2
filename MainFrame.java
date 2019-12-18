package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException; 
import java.awt.Desktop;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Person.Employee;
import SW.HRSW;

public class MainFrame extends JFrame {
	static JMenuBar MenuBar = new JMenuBar();
	static JPanel MainPane = new JPanel();
	
	static String columnNames[] = {"���� ID", "�̸�", "�Ҽ�", "��å", "��ȭ��ȣ"};
	
	static DefaultTableModel realTableModel = new DefaultTableModel(null, columnNames) {
		public boolean isCellEditable(int row, int column) {
			return false;			// ���̺��� ���� �����ϴ� ���� �Ұ����ϵ��� ��
		}
	};
	
	static DefaultTableModel fakeTableModel = new DefaultTableModel(null, columnNames) {
		public boolean isCellEditable(int row, int column) {
			return false;			// ���̺��� ���� �����ϴ� ���� �Ұ����ϵ��� ��
		}
	};
	
	static JTable realJTable = new JTable(realTableModel);
	static JTable fakeJTable = new JTable(fakeTableModel);
	static JScrollPane DBListPane = new JScrollPane(realJTable);
	static JScrollPane SDBListPane = new JScrollPane(fakeJTable);
	static JPanel ButtonPane = new JPanel();
	static JScrollPane LogListPane;
	
	static JButton correctButton;
	static JButton deleteButton;
	static JButton returnButton;
	static JButton presentButton;
	static JButton scoreButton;
	static JButton infoButton;
	
	static boolean Toggle = false;		// false : DB, true : Log
	
	
	public MainFrame() {
		
		this.setTitle("��� S/W ���� ���� ���α׷�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(MenuBar);
		
		createMenu();
		this.add(MainPane);
		MainPane.setLayout(new BorderLayout(30, 20));
		MainPane.add(DBListPane, BorderLayout.CENTER);
		
		realJTable.setSelectionMode(0);			// �ѹ��� �ϳ��� Row�� ������ �� �ֵ��� ����
		fakeJTable.setSelectionMode(0);			// �ѹ��� �ϳ��� Row�� ������ �� �ֵ��� ����
		DefaultTableCellRenderer CellRenderer = new DefaultTableCellRenderer();
		CellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel realTCM = realJTable.getColumnModel();
		TableColumnModel fakeTCM = fakeJTable.getColumnModel();
		
		for(int i = 0; i < realTCM.getColumnCount(); i++) 
			realTCM.getColumn(i).setCellRenderer(CellRenderer);
		for(int i = 0; i < fakeTCM.getColumnCount(); i++) 
			fakeTCM.getColumn(i).setCellRenderer(CellRenderer);
		
		this.setSize(600, 500);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		// ��ũ���� �߾ӿ� ��ġ
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	void createMenu() {
		String MenuTitle[] = {"Database", "Operations", "History", "Quit"};
		final JMenu Menu[] = new JMenu[4];
		
		for(int i = 0; i < Menu.length; i++)
			Menu[i] = new JMenu(MenuTitle[i]);
		
		JMenuItem DBMenuItem[] = new JMenuItem[2];	// Database Menu
		JMenuItem OPMenuItem[] = new JMenuItem[7];	// Operations Menu
		JMenuItem HIMenuItem[] = new JMenuItem[2];	// History Menu
		JMenuItem QUMenuItem[] = new JMenuItem[1];	// Quit Menu
		
		DBMenuItem[0] = new JMenuItem("Load");
		DBMenuItem[1] = new JMenuItem("Save");
		
		for(int i = 0; i < DBMenuItem.length; i++) {
			DBMenuItem[i].addActionListener(new MenuActionListener());
			Menu[0].add(DBMenuItem[i]);
		}
		
		OPMenuItem[0] = new JMenuItem("�ű����� �߰�");
		OPMenuItem[1] = new JMenuItem("�������� ����");
		OPMenuItem[2] = new JMenuItem("�������� ����");
		OPMenuItem[3] = new JMenuItem("���� �˻�");
		OPMenuItem[4] = new JMenuItem("��� ����");
		OPMenuItem[5] = new JMenuItem("�����ϱ�");
		OPMenuItem[6] = new JMenuItem("���� ��");
		
		for(int i = 0; i < OPMenuItem.length; i++) {
			OPMenuItem[i].addActionListener(new MenuActionListener());
			Menu[1].add(OPMenuItem[i]);
		}
		
		HIMenuItem[0] = new JMenuItem("Change the Log File");
		HIMenuItem[1] = new JCheckBoxMenuItem("Show the Log File");
		
		for(int i = 0; i < HIMenuItem.length; i++) {
			HIMenuItem[i].addActionListener(new MenuActionListener());
			Menu[2].add(HIMenuItem[i]);
		}
		
		QUMenuItem[0] = new JMenuItem("Quit");
		
		for(int i = 0; i < QUMenuItem.length; i++) {		// QuitMenu is Just One... But it provides me expandability.
			QUMenuItem[i].addActionListener(new MenuActionListener());
			Menu[3].add(QUMenuItem[i]);
		}

		for(int i = 0; i < Menu.length; i++)
			MenuBar.add(Menu[i]);
	}
	
	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("Load")) {
				//implementation
				FileDialog FD = new FileDialog();
				String FileName = FD.openFile();
				if(FileName == null)
					return;
				
				int TableLength = realTableModel.getRowCount();
				for(int i = 0; i < TableLength; i++)
					realTableModel.removeRow(0);	// Table Initialize
				
				SW.UserTable.User.removeAllElements();
				SW.HRSW.loadFromFile(FileName);
				for(int i = 0; i < SW.UserTable.User.size(); i++)
					addUser(SW.UserTable.User.elementAt(i));
				
				SW.HRSW.logging(FileName + "�� ����");
			}
			else if(cmd.equals("Save")) {
				FileDialog FD = new FileDialog();
				String FileName = FD.saveFile();
				if(FileName == null)
					return;
				SW.HRSW.saveToFile(FileName);
				SW.HRSW.logging(FileName + "�� ����");
			}
			else if(cmd.equals("�ű����� �߰�")) {
				new OperationsDialog(SW.Main.mainFrame, cmd);
			}
			else if(cmd.equals("�������� ����")) {
				new OperationsDialog(SW.Main.mainFrame, cmd);
			}
			else if(cmd.equals("�������� ����")) {
				new OperationsDialog(SW.Main.mainFrame, cmd);
			}
			else if(cmd.equals("���� �˻�")) {
				new OperationsDialog(SW.Main.mainFrame, cmd);
			}
			else if(cmd.equals("��� ����")) {
				new OperationsDialog(SW.Main.mainFrame, cmd);
			}
			else if(cmd.equals("�����ϱ�")) {
				new OperationsDialog(SW.Main.mainFrame,cmd);
			}
			else if(cmd.contentEquals("���� ��")){
				new OperationsDialog(SW.Main.mainFrame,cmd);
			}
			else if(cmd.equals("Change the Log File")) {
				FileDialog FD = new FileDialog();
				String LogFileName = FD.openFile();
				HRSW.changeLogFile(LogFileName);
				if(LogFileName == null)
					return;
				SW.HRSW.logging(LogFileName + "�� �α� ���� ����");
			}
			else if(cmd.equals("Show the Log File")) {
				if(Toggle) {		// Show the DB
					showDBPane();
					Toggle = !Toggle;
				}
				else {				// Show the Log
					showLogPane();
					Toggle = !Toggle;
				}
			}
			else if(cmd.equals("Quit")) {
				new SaveDialog(SW.Main.mainFrame, "<html><div style=text-align:center>�̴�� �����ϸ� ���ݱ��� �۾��� �����͸� "
						+ "<br>�Ҿ���� �� �ֽ��ϴ�. �����Ͻðڽ��ϱ�?</div></html>");
			}
			else 
				System.out.println("�߸��� �޴� ó��");
		}
	}
	
	public static void showDBPane() {
		MainPane.removeAll();
		MainPane.add(DBListPane, BorderLayout.CENTER);
		MainPane.revalidate();
	}
	
	public static void showLogPane() {
		MainPane.removeAll();
		JTextArea TA = new JTextArea(SW.HRSW.loadLog());
		LogListPane = new JScrollPane(TA);
		TA.setEditable(false);
		MainPane.add(LogListPane, BorderLayout.CENTER);
		MainPane.revalidate();
	}
	
	public static void refreshDBPane() {
		int size = realTableModel.getRowCount();
		for(int i = 0; i < size; i++)
			realTableModel.removeRow(0);
		
		for(int i = 0; i < SW.UserTable.User.size(); i++)
			GUI.MainFrame.addUser(SW.UserTable.User.elementAt(i));
		
	}
	
	public static int findRow(String id) {
		for(int i = 0; i < realTableModel.getRowCount(); i++) {
			if(realTableModel.getValueAt(i, 0).toString().equals(id))
				return i;
		}
		return -1;
	}
	
	public static void addUser(Employee submitedData) {
		Object obj[] = new Object[8];
		obj[0] = submitedData.getId();
		obj[1] = submitedData.getName();
		obj[2] = submitedData.getDepart();
		obj[3] = submitedData.getType();
		obj[4] = submitedData.getManager();
		obj[5] = submitedData.getAddress();
		obj[6] = submitedData.getMail();
		obj[7] = submitedData.getPhoneNum();
		realTableModel.addRow(obj);
	}
	
	public static void addSearchedUser(Employee searchedData) {
		Object obj[] = new Object[8];
		obj[0] = searchedData.getId();
		obj[1] = searchedData.getName();
		obj[2] = searchedData.getDepart();
		obj[3] = searchedData.getType();
		obj[4] = searchedData.getManager();
		obj[5] = searchedData.getAddress();
		obj[6] = searchedData.getMail();
		obj[7] = searchedData.getPhoneNum();
		fakeTableModel.addRow(obj);
	}
	
	public static void correctUser(Object obj[]) {
		int selected = -1;
		selected = fakeJTable.getSelectedRow();
		System.out.println(fakeJTable.getSelectedRow()+" "+fakeJTable.getSelectedColumn());
		for(int i = 0; i < fakeJTable.getColumnCount() - 1; i++) {
			fakeTableModel.setValueAt(obj[i], selected, i);
		}
		
		for(int i = 0; i < realJTable.getRowCount(); i++) {
			if(realTableModel.getValueAt(i, 0).equals(obj[0])) {
				for(int j = 0; j < realJTable.getColumnCount() - 1; j++) {
					realTableModel.setValueAt(obj[j], i, j);
				}
				break;
			}
		}
		
		SW.UserTable.correctUser(obj);
	}
	
	public static void searchingUser(Object tempObject[], int selected) {
		/*
		 * ���� - 	ID : 0
		 * 		 	�̸� : 1
		 * 			�μ� : 2
		 * 			�Ŵ����� : 3
		 */
		String str = tempObject[selected].toString();
		fakeTableModel.setRowCount(0);				// �ʱ�ȭ
		
		switch(selected) {
		case 0:		// ID
			SW.UserTable.searchById(str);
			SW.HRSW.logging("���� �˻� - ����:ID(" + str + ")");
			break;
		case 1:		// �̸�
			SW.UserTable.searchByName(str);
			SW.HRSW.logging("���� �˻� - ����:�̸�(" + str + ")");
			break;
		case 2:		// �μ�
			SW.UserTable.searchByDepart(str);
			SW.HRSW.logging("���� �˻� - ����:�μ�(" + str + ")");
			break;
		case 3:		// �Ŵ�����
			SW.UserTable.searchByManager(str);
			SW.HRSW.logging("���� �˻� - ����:�Ŵ�����(" + str + ")");
			break;
		default:
			break;
			
		}
		MainPane.removeAll();
		ButtonPane.removeAll();
		MainPane.add(SDBListPane, BorderLayout.CENTER);
		
		if(fakeTableModel.getRowCount() == 0) {
			new ErrorDialog(SW.Main.mainFrame, "<html><div style=text-align:center>���ǿ� �´� �����Ͱ� �����ϴ�.<br>������� �����ɴϴ�.</div></html>");
			SW.HRSW.logging("���� �˻� - �˻� ���� ��� ���� ���");
			SW.UserTable.addAllUser();
		}
		
		correctButton = new JButton("����");
		deleteButton = new JButton("����");
		returnButton = new JButton("���ư���");
		presentButton = new JButton("�����ϱ�");
		scoreButton = new JButton("���� ��");
		infoButton = new JButton("�� ����");
		
		correctButton.addMouseListener(new ButtonListener());
		deleteButton.addMouseListener(new ButtonListener());
		returnButton.addMouseListener(new ButtonListener());
		presentButton.addMouseListener(new ButtonListener());
		scoreButton.addMouseListener(new ButtonListener());
		infoButton.addMouseListener(new ButtonListener());
		
		ButtonPane.add(correctButton);
		ButtonPane.add(deleteButton);
		ButtonPane.add(returnButton);
		ButtonPane.add(presentButton);
		ButtonPane.add(scoreButton);
		ButtonPane.add(infoButton);
		
		
		MainPane.add(ButtonPane, BorderLayout.SOUTH);
		MainPane.revalidate();
		ButtonPane.revalidate();
	}
	
	static class ButtonListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(correctButton)) {
				int selected = fakeJTable.getSelectedRow();
				Object tempObject[] = new Object[5];
				for(int i = 0; i < tempObject.length; i++)
					tempObject[i] = fakeJTable.getValueAt(selected, i);
				if(tempObject[4].toString().equals("O")) {				// �Ŵ����� ���� �Ұ���
					new ErrorDialog(SW.Main.mainFrame, "�Ŵ����� ���� �Ұ����մϴ�!");
					return;
				}
				new OperationsDialog(SW.Main.mainFrame, tempObject);
			}
			else if(e.getSource().equals(deleteButton)) {
				int selected = fakeJTable.getSelectedRow();
				Object tempObject[] = new Object[9];
				for(int i = 0; i < tempObject.length; i++)
					tempObject[i] = fakeJTable.getValueAt(selected, i);
				if(tempObject[4].toString().equals("O")) {				// �Ŵ����� ���� �Ұ���
					new ErrorDialog(SW.Main.mainFrame, "�Ŵ����� ���� �Ұ����մϴ�!");
					return;
				}
				
				fakeTableModel.removeRow(selected);
				SW.UserTable.deleteUser(tempObject[0].toString());
				realTableModel.removeRow(findRow(tempObject[0].toString()));
				SW.HRSW.logging("�������� ���� - ID(" + tempObject[0].toString() + ")");
			}
			else if(e.getSource().equals(returnButton)) {
				if(Toggle)
					showLogPane();
				else
					showDBPane();
			}
			else if(e.getSource().equals(presentButton)){
				try { 
					Desktop.getDesktop().browse(new URI("http://mdago.tistory.com/")); 
					} catch (IOException o) 
				{
						o.printStackTrace(); 
						} catch (URISyntaxException o) 
				{
							o.printStackTrace(); 
							}

			}
			else if(e.getSource().equals(scoreButton)) {
				int selected = fakeJTable.getSelectedRow();
				Object tempObject[] = new Object[5];
				for(int i = 0; i < tempObject.length; i++)
					tempObject[i] = fakeJTable.getValueAt(selected, i);
				if(tempObject[4].toString().equals("O")) {				// �Ŵ����� ���� �Ұ���
					new ErrorDialog(SW.Main.mainFrame, "�Ŵ����� ���� �Ұ����մϴ�!");
					return;
				}
				new Evaluation(SW.Main.mainFrame, tempObject);
			}
			else if(e.getSource().equals(infoButton)) {
				int selected = fakeJTable.getSelectedRow();
				Object tempObject[] = new Object[5];
				for(int i = 0; i < tempObject.length; i++)
					tempObject[i] = fakeJTable.getValueAt(selected, i);
				if(tempObject[4].toString().equals("O")) {				// �Ŵ����� ���� �Ұ���
					new ErrorDialog(SW.Main.mainFrame, "�Ŵ����� ���� �Ұ����մϴ�!");
					return;
				}
				new info(SW.Main.mainFrame, tempObject);
			}
		}
	}
}
