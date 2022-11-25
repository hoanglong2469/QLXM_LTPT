package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FrmChucnangAdmin extends JFrame implements ActionListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Khai báo
	private JLabel lbladmin;
	private JButton btnQLnhanvien;
	private JButton btnQLsanpham;
	private JButton btnthoat;
	private JButton btnDangxuat;
	private JButton btnTieude;
	private JButton btnQLacc;
	
	public FrmChucnangAdmin() {
		setTitle("ADMIN");
		setSize(700, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		btnTieude = new JButton("");
		//Set các hình ảnh
		ImageIcon icon1=new ImageIcon("img/account.png");
		ImageIcon icon2=new ImageIcon("img/nhanvien.png");
		ImageIcon icon3=new ImageIcon("img/sanpham.png");
		ImageIcon icon4=new ImageIcon("img/exit.png");
		ImageIcon icon5=new ImageIcon("img/thoat.png");
		ImageIcon icon6=new ImageIcon("img/account.png");
		
		btnTieude.setIcon(icon1);
		btnTieude.setPreferredSize(new Dimension(70,70));
		lbladmin = new JLabel("Bạn đang đăng nhập với chức năng Admin: ");
		lbladmin.setFont(new Font("Arial", Font.BOLD, 20));
		lbladmin.setForeground(Color.RED);
		btnQLacc = new JButton("Quản Lý Tài khoản");
		btnQLacc.setIcon(icon6);
		btnQLnhanvien = new JButton("Quản Lý Nhân Viên");
		btnQLnhanvien.setIcon(icon2);
		btnQLsanpham = new JButton("Quản Lý sản phẩm ");
		btnQLsanpham.setIcon(icon3);
		btnDangxuat = new JButton("Đăng xuất");
		btnDangxuat.setIcon(icon4);
		btnthoat = new JButton("Thoát");
		btnthoat.setIcon(icon5);
		//Set Font
		btnQLacc.setFont(new Font("Arial", Font.BOLD, 18));
		btnQLnhanvien.setFont(new Font("Arial", Font.BOLD, 18));
		btnQLsanpham.setFont(new Font("Arial", Font.BOLD, 18));
		btnthoat.setFont(new Font("Arial", Font.BOLD, 18));
		btnDangxuat.setFont(new Font("Arial", Font.BOLD, 18));
		btnthoat.setBackground(Color.PINK);
		btnDangxuat.setBackground(Color.GREEN);
		
		//Khai báo Panel
		JPanel pChinh = new JPanel();
		pChinh.setLayout(new BorderLayout());
		JPanel pNorth = new JPanel();
		JPanel pCen = new JPanel();
		JPanel pSouth = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.add(btnTieude);
		p2.add(lbladmin);
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		pNorth.add(p1);
		pNorth.add(p2);
		pCen.setLayout(new BoxLayout(pCen, BoxLayout.Y_AXIS));
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		b1.add(btnQLacc);
		b2.add(btnQLnhanvien);
		b3.add(btnQLsanpham);
		pCen.add(Box.createVerticalStrut(15));
		pCen.add(b1);
		pCen.add(Box.createVerticalStrut(15));
		pCen.add(b2);
		pCen.add(Box.createVerticalStrut(15));
		pCen.add(b3);
		pCen.add(Box.createVerticalStrut(15));
		pCen.add(b4);
		pSouth.add(Box.createHorizontalStrut(300));
		pSouth.add(btnDangxuat);
		pSouth.add(btnthoat);
		
		//Thêm pNorth, pCen, pSouth vào pChính
		pChinh.add(pNorth, BorderLayout.NORTH);
		pChinh.add(pCen, BorderLayout.CENTER);
		pChinh.add(pSouth, BorderLayout.SOUTH);
		this.add(pChinh);
		
		//Thêm các sự kiện
		btnQLnhanvien.addActionListener(this);
		btnQLsanpham.addActionListener(this);
		btnDangxuat.addActionListener(this);
		btnthoat.addActionListener(this);
		btnQLacc.addActionListener(this);
		
	}
//	public static void main(String[] args) {
//		new FrmChucnangAdmin().setVisible(true);
//	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnDangxuat)) {
			//Người dùng đăng xuất, trở lại form đăng nhập
			FrmDangnhap dn = new FrmDangnhap();
			dn.setVisible(true);
			this.dispose();
			JOptionPane.showMessageDialog(this, "Đăng Xuất thành công");
		}
		else if(obj.equals(btnthoat)) {
			System.exit(0);
		}
		else if(obj.equals(btnQLacc)) {
			//Chức năng Quản Lý tài khoản
			FrmQLAccount acc=new FrmQLAccount();
			acc.setVisible(true);
			this.dispose();
		}
		else if(obj.equals(btnQLnhanvien)) {
			FrmQLNhanVien nv=new FrmQLNhanVien();
			nv.setVisible(true);
			this.dispose();
		}
		else if(obj.equals(btnQLsanpham)) {
			FrmQLSanPham_ADM sp=new FrmQLSanPham_ADM();
			sp.setVisible(true);
			this.dispose();
	}
	}
}
