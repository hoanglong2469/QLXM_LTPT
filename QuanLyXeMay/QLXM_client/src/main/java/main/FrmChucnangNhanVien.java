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
import java.rmi.RemoteException;

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

public class FrmChucnangNhanVien extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel lbladmin;
	private JButton btnQLsanpham;
	private JButton btnthoat;
	private JButton btnDangxuat;
	private JButton btnTieude;

	private JButton btnQLkhachhang;

	private JButton btnQLHoadon;

	private JButton btnQLbcthongke;

	public FrmChucnangNhanVien() {
		setTitle("NHÂN VIÊN");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		btnTieude = new JButton("");

		// Thêm các hình ảnh
		ImageIcon icon1 = new ImageIcon("img/nhanvien.png");
		ImageIcon icon2 = new ImageIcon("img/sanpham.png");
		ImageIcon icon3 = new ImageIcon("img/hoadon.png");
		ImageIcon icon4 = new ImageIcon("img/khachhang.png");
		ImageIcon icon5 = new ImageIcon("img/doanhthu.png");
		ImageIcon icon6 = new ImageIcon("img/exit.png");
		ImageIcon icon7 = new ImageIcon("img/thoat.png");

		// Set hình ảnh cho các nút
		btnTieude.setIcon(icon1);
		btnTieude.setPreferredSize(new Dimension(70, 70));
		lbladmin = new JLabel("Bạn đang đăng nhập với chức năng Nhân Viên: ");
		lbladmin.setFont(new Font("Arial", Font.BOLD, 24));
		lbladmin.setForeground(Color.RED);
		btnQLsanpham = new JButton("Quản lý sản phẩm ");
		btnQLsanpham.setIcon(icon2);
		btnQLkhachhang = new JButton("Quản lý khách hàng");
		btnQLkhachhang.setIcon(icon4);
		btnQLHoadon = new JButton("Danh mục hóa đơn");
		btnQLHoadon.setIcon(icon3);
		btnQLbcthongke = new JButton("Báo cáo thống kê    ");
		btnQLbcthongke.setIcon(icon5);
		btnDangxuat = new JButton("Đăng xuất");
		btnDangxuat.setIcon(icon6);
		btnthoat = new JButton("Thoát");
		btnthoat.setIcon(icon7);

		// Set font, Color
		btnQLsanpham.setFont(new Font("Arial", Font.BOLD, 18));
		btnQLkhachhang.setFont(new Font("Arial", Font.BOLD, 18));
		btnQLHoadon.setFont(new Font("Arial", Font.BOLD, 18));
		btnQLbcthongke.setFont(new Font("Arial", Font.BOLD, 18));
		btnthoat.setFont(new Font("Arial", Font.BOLD, 18));
		btnDangxuat.setFont(new Font("Arial", Font.BOLD, 18));
		btnthoat.setBackground(Color.PINK);
		btnDangxuat.setBackground(Color.GREEN);

		// Khai báo Panel
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

		b1.add(btnQLsanpham);
		b2.add(btnQLkhachhang);
		b3.add(btnQLHoadon);
		b4.add(btnQLbcthongke);
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
		pChinh.add(pNorth, BorderLayout.NORTH);
		pChinh.add(pCen, BorderLayout.CENTER);
		pChinh.add(pSouth, BorderLayout.SOUTH);
		this.add(pChinh);
		// Lắng nghe sự kiện
		btnQLsanpham.addActionListener(this);
		btnQLkhachhang.addActionListener(this);
		btnQLHoadon.addActionListener(this);
		btnQLbcthongke.addActionListener(this);
		btnDangxuat.addActionListener(this);
		btnthoat.addActionListener(this);

	}

	public static void main(String[] args) {
		new FrmChucnangNhanVien().setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnDangxuat)) {
			// Người dùng đăng xuất, trở lại form đăng nhập
			FrmDangnhap dn = new FrmDangnhap();
			dn.setVisible(true);
			this.dispose();
			JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
		} else if (obj.equals(btnQLkhachhang)) {
			FrmQLKhachHang kh = new FrmQLKhachHang();
			kh.setVisible(true);
			this.dispose();
		} else if (obj.equals(btnQLsanpham)) {
			FrmQLSanPham_NV sp = new FrmQLSanPham_NV();
			sp.setVisible(true);
			this.dispose();
		} else if (obj.equals(btnQLbcthongke)) {
			FrmBaoCaoThongKe tk = new FrmBaoCaoThongKe();
			tk.setVisible(true);
			this.dispose();
		} else if (obj.equals(btnQLHoadon)) {
			FrmHoaDon hd;
			try {
				hd = new FrmHoaDon();
				hd.setVisible(true);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		} else if (obj.equals(btnthoat)) {
			System.exit(0);
		}

	}

}
