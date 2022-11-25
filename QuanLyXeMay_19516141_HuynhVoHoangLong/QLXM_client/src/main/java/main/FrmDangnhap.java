package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Account_DAO;
import entity.Account;

public class FrmDangnhap extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblDangnhap;
	private JLabel lblUser;
	private JTextField txtUser;
	private JLabel lblPS;
	private JPasswordField txtPS;
	private JButton btnDangnhap;
	private JButton btnThoat;
	private Account_DAO acc_DAO;

	public FrmDangnhap() {

		try {
			String urlACC = "rmi://localhost:9090/tkDao";
			acc_DAO = (Account_DAO) Naming.lookup(urlACC);

		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("-----Đăng nhập-----");
		setSize(600, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		JPanel pChinh = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		pChinh.setLayout(new BoxLayout(pChinh, BoxLayout.Y_AXIS));
		JButton btn = new JButton("");
		ImageIcon icon = new ImageIcon("img/account.png");
		btn.setIcon(icon);
		btn.setPreferredSize(new Dimension(70, 70));

		lblDangnhap = new JLabel("Đăng nhập bằng tài khoản của bạn:");
		lblDangnhap.setFont(new Font("Arial", Font.BOLD, 18));
		lblUser = new JLabel("UserName: ");
		lblUser.setPreferredSize(new Dimension(80, 25));
		txtUser = new JTextField(20);
		txtUser.setPreferredSize(new Dimension(200, 25));
		lblPS = new JLabel("Password: ");
		lblPS.setPreferredSize(new Dimension(70, 25));
		txtPS = new JPasswordField(20);
		txtPS.setPreferredSize(new Dimension(200, 25));
		btnDangnhap = new JButton("Đăng nhập");
		btnDangnhap.setPreferredSize(new Dimension(120, 40));
		btnDangnhap.setBackground(Color.CYAN);
		btnDangnhap.setFont(new Font("Arial", Font.BOLD, 14));
		btnThoat = new JButton("Thoát");
		btnThoat.setPreferredSize(new Dimension(120, 40));
		btnThoat.setBackground(Color.RED);
		btnThoat.setFont(new Font("Arial", Font.BOLD, 14));
		p1.add(btn);
		p2.add(lblDangnhap);
		p3.add(lblUser);
		p3.add(txtUser);
		p4.add(lblPS);
		p4.add(txtPS);
		p5.add(btnDangnhap);
		p5.add(btnThoat);

		// Thêm vào pChinh
		pChinh.add(p1);
		pChinh.add(p2);
		pChinh.add(p3);
		pChinh.add(p4);
		pChinh.add(p5);
		this.add(pChinh);
		// Thêm sự kiện
		btnThoat.addActionListener(this);
		btnDangnhap.addActionListener(this);
		txtPS.addActionListener(this);
		txtUser.addActionListener(this);
	}

	public static void main(String[] args) {
		new FrmDangnhap().setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		// Kiểm tra dữ liệu đăng nhập
		if (obj.equals(txtPS) || obj.equals(btnDangnhap) || obj.equals(txtUser)) {
			if (txtUser.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đăng nhập ! ");
				txtUser.requestFocus();
			} else if (txtPS.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đăng nhập ! ");
				txtPS.requestFocus();
			} else if (txtUser.getText().equals("admin") && txtPS.getText().equals("admin")) {
				// Tài khoản mặt định của Admin khi list đăng nhập trống
				FrmChucnangAdmin Admin = new FrmChucnangAdmin();
				Admin.setVisible(true);
				this.dispose();
			} else if (txtUser.getText().equals("nv") && txtPS.getText().equals("nv")) {
				FrmChucnangNhanVien NV = new FrmChucnangNhanVien();
				NV.setVisible(true);
				this.dispose();
			}
			
		} else if (obj.equals(btnThoat)) {
			System.exit(0);
		}

	}
}
