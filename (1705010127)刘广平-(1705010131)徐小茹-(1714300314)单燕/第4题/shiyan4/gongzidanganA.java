package shiyan4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.org.*;

import shuju.DbProcess;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.Choice;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.*;
import java.sql.*;

public class gongzidanganA extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField name;
	private JTextField money;
	private JTextField people;
	private JTextField year;
	private JTextField newmoney;
	String n;
	double m;
    double p;
    double y;
    double nm;
    private static DbProcesss dbProcesss=new DbProcesss();
    Vector personVector = new Vector();
	Vector titleVector = new Vector();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gongzidanganA frame = new gongzidanganA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gongzidanganA() {
		setTitle("���ʵ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 10, 430, 117);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"����", "��������", "��������", "�깤�ʣ�ԭ��", "�깤�ʣ��£�"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(95);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		scrollPane.setViewportView(table);
		
		
		JLabel label = new JLabel("������");
		label.setBounds(32, 145, 54, 15);
		contentPane.add(label);
		
		name = new JTextField();
		name.setBounds(82, 142, 66, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel label_1 = new JLabel("�깤�ʣ��ɣ���");
		label_1.setBounds(182, 145, 90, 15);
		contentPane.add(label_1);
		
		money = new JTextField();
		money.setText("25000");
		money.setBounds(268, 142, 66, 21);
		contentPane.add(money);
		money.setColumns(10);
		m=new Double(money.getText()).doubleValue();
		
		JLabel label_2 = new JLabel("����������");
		label_2.setBounds(22, 173, 74, 15);
		contentPane.add(label_2);
		
		people = new JTextField();
		people.setText("2");
		people.setBounds(82, 170, 66, 21);
		contentPane.add(people);
		people.setColumns(10);
		p=new Double(people.getText()).doubleValue();
		
		JLabel label_3 = new JLabel("�������ڣ�");
		label_3.setBounds(182, 173, 74, 15);
		contentPane.add(label_3);
		
		year = new JTextField();
		year.setText("2018");
		year.setBounds(268, 170, 66, 21);
		contentPane.add(year);
		year.setColumns(10);
		y=new Double(year.getText()).doubleValue();
		//����
		JButton button_1 = new JButton("����");
		button_1.setBounds(32, 215, 93, 23);
		contentPane.add(button_1);
		//��ѯ
		JButton button_2 = new JButton("��ѯ");
		button_2.setBounds(238, 215, 93, 23);
		contentPane.add(button_2);
		//ɾ��
		JButton button_3 = new JButton("ɾ��");
		button_3.setBounds(135, 215, 93, 23);
		contentPane.add(button_3);
		
		//���ʼ���
		JButton button_4 = new JButton("ȷ��");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				y=new Double(year.getText()).doubleValue();
				m=new Double(money.getText()).doubleValue();
				p=new Double(people.getText()).doubleValue();
				nm=function.newmoney(p,m,y);
				newmoney.setText(nm+"");
			}
		});
		button_4.setBounds(182, 248, 93, 23);
		contentPane.add(button_4);
		
		JLabel label_4 = new JLabel("�깤�ʣ��£���");
		label_4.setBounds(32, 290, 93, 15);
		contentPane.add(label_4);
		
		newmoney = new JTextField();
		newmoney.setBounds(115, 287, 113, 21);
		contentPane.add(newmoney);
		newmoney.setColumns(10);
		
		JLabel label_5 = new JLabel("Ԫ");
		label_5.setBounds(238, 290, 54, 15);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Ԫ");
		label_6.setBounds(341, 145, 54, 15);
		contentPane.add(label_6);
		//��ѯȫ��
		JButton button = new JButton("��ѯȫ��");
		button.setBounds(342, 215, 93, 23);
		contentPane.add(button);
		
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("actionPerformed(). ����");
			    dbProcesss.connect();
			    n=name.getText();
			    y=new Double(year.getText()).doubleValue();
				m=new Double(money.getText()).doubleValue();
				p=new Double(people.getText()).doubleValue();
				nm=function.newmoney(p,m,y);
				newmoney.setText(nm+"");
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"����", "5", "2017", "25000", "25600"},
							{"����", "6", "2018", "25000", "25650"},
							{"����", "4", "2016", "25000", "25550"},
							{n,p,y,m,nm},
						},
						new String[] {
							"����", "��������", "��������", "�깤�ʣ�ԭ��", "�깤�ʣ��£�"
						}
					));
			}
		});//����
		
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				n=name.getText();
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"����", "3", "2015", "25000", "25500"},
						},
						new String[] {
							"����", "��������", "��������", "�깤�ʣ�ԭ��", "�깤�ʣ��£�"
						}
					));
			}
		});//��ѯ
		
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("actionPerformed(). ɾ����ǰ��¼");
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{"����", "5", "2017", "25000", "25600"},
							{"����", "6", "2018", "25000", "25650"},
							{"����", "4", "2016", "25000", "25550"},
						},
						new String[] {
							"����", "��������", "��������", "�깤�ʣ�ԭ��", "�깤�ʣ��£�"
						}
					));
			}
		});//ɾ��
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("actionPerformed(). ��ѯȫ��");
				queryAllProcess();
			}
		});//��ѯȫ��
		
		
		JButton button_5 = new JButton("����");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainview MV=new mainview();
				MV.setVisible(true);
				setVisible(false);
			}
		});
		button_5.setBounds(375, 307, 93, 23);
		contentPane.add(button_5);
	}
	
	public void queryAllProcess() {
		String sql = "select * from dangan;";
		System.out.println("queryAllProcess(). sql = " + sql);
		
		dbProcesss.connect();
		ResultSet rs = dbProcesss.executeQuery(sql);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"����", "5", "2016", "25000", "25650"},
					{"����", "2", "2018", "25000", "25250"},
					{"����", "4", "2015", "25000", "25600"},
				},
				new String[] {
					"����", "��������", "��������", "�깤�ʣ�ԭ��", "�깤�ʣ��£�"
				}
			));
	}
}
