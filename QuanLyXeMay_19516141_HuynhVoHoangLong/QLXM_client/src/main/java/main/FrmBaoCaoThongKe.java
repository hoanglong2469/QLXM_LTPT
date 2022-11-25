package main;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import entity.HoaDon;
import entity.KhachHang;



public class FrmBaoCaoThongKe extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	String[] cols1 = { "Mã hoá Đơn", "Mã Khách Hàng", "Mã Xe", "Mã Nhân Viên", "Ngày Lập HD", "Đơn Giá",
			"Số Lượng", "thành tiền" };
	String[] cols2 = { "Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại" };

	private DefaultTableModel model1;
	private JTable table1;
	private DefaultTableModel model2;
	private JTable table2;

	private JButton btnQuaylai, btnDangxuat, btnThoat;

	private JComboBox<String> cboKH;
	private JComboBox<String> cboHDkh;
	private JTextField txtDoanhthu;
	private KhachHang_DAO kh_dao;
	private HoaDon_DAO hd_dao;
	private List<KhachHang> dsKhachhang;

	public FrmBaoCaoThongKe() {
//		// khá»Ÿi táº¡o káº¿t ná»‘i Ä‘áº¿n CSDL
//		try {
//			ConnectDB.getInstance().connect();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
            String urlHD = "rmi://localhost:9090/hdDao";
            hd_dao = (HoaDon_DAO) Naming.lookup(urlHD);
            String urlKH ="rmi://localhost:9090/khDao";
            kh_dao = (KhachHang_DAO) Naming.lookup(urlKH);
        } catch (Exception e) {
        	e.printStackTrace();
       }
		setTitle("Báo Cáo Thống Kê");
		setSize(1300, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon1 = new ImageIcon("img/Gear-icon.png");
		ImageIcon icon2 = new ImageIcon("img/exit.png");
		ImageIcon icon3 = new ImageIcon("img/thoat.png");
		ImageIcon icon4 = new ImageIcon("img/doanhthu.png");

		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, 1300, 750);

		JLabel lbdoanhthu = new JLabel("Tổng Doanh thu:");
		lbdoanhthu.setIcon(icon4);
		lbdoanhthu.setFont(new Font("Arial", Font.BOLD, 20));
		lbdoanhthu.setForeground(Color.RED);
		JLabel lbVnd = new JLabel("VND");
		lbVnd.setFont(new Font("Arial", Font.BOLD, 20));
		lbVnd.setForeground(Color.RED);
		txtDoanhthu = new JTextField();
		txtDoanhthu.setEditable(false);

		btnQuaylai = new JButton("Quay lại");
		btnQuaylai.setIcon(icon1);
		btnDangxuat = new JButton("Đăng Xuất");
		btnDangxuat.setIcon(icon2);
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(icon3);

		cboHDkh = new JComboBox<String>();
		JLabel lbLocHD = new JLabel("Lọc theo mã hoá đơn :");
		lbLocHD.setFont(new Font("Arial", Font.BOLD, 15));
		lbLocHD.setForeground(Color.RED);
		cboKH = new JComboBox<String>();
		JLabel lbLocKH = new JLabel("Lọc theo mã khách hàng:");
		lbLocKH.setFont(new Font("Arial", Font.BOLD, 15));
		lbLocKH.setForeground(Color.RED);

		// pn HoÌ�a Ä‘Æ¡n
		lbLocHD.setBounds(800, 20, 300, 30);
		cboHDkh.setBounds(1000, 20, 100, 30);
		JPanel pnHoaDon = new JPanel();
		pnHoaDon.setLayout(null);
		pnHoaDon.setBounds(10, 50, 1260, 260);
		pnHoaDon.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "chi tiết hoá đơn", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));

		model1 = new DefaultTableModel(cols1, 0) {
			// khÃ³a khÃ´ng cho ngÆ°á»�i dÃ¹ng nháº­p trÃªn table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table1 = new JTable(model1);
		JScrollPane sql1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnHoaDon.add(sql1);
		sql1.setBounds(10, 25, 1235, 230);

		// pnKhachhang
		lbLocKH.setBounds(800, 320, 300, 30);
		cboKH.setBounds(1000, 320, 100, 30);
		JPanel pnKhachhang = new JPanel();
		pnKhachhang.setLayout(null);
		pnKhachhang.setBounds(10, 360, 1260, 270);
		pnKhachhang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Thống kê khách hàng",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));

		model2 = new DefaultTableModel(cols2, 0) {
			// khÃ³a khÃ´ng cho ngÆ°á»�i dÃ¹ng nháº­p trÃªn table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table2 = new JTable(model2);
		JScrollPane sql2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnKhachhang.add(sql2);
		sql2.setBounds(10, 25, 1235, 240);

		btnQuaylai.setBounds(800, 650, 130, 50);
		btnDangxuat.setBounds(950, 650, 130, 50);
		btnThoat.setBounds(1100, 650, 130, 50);
		btnQuaylai.setBackground(Color.YELLOW);
		btnDangxuat.setBackground(Color.GREEN);
		btnThoat.setBackground(Color.PINK);

		lbdoanhthu.setBounds(10, 640, 250, 60);
		txtDoanhthu.setBounds(250, 655, 400, 30);
		txtDoanhthu.setFont(new Font("Arial", Font.BOLD, 20));
		txtDoanhthu.setForeground(Color.GREEN);
		lbVnd.setBounds(660, 655, 100, 30);

		pnMain.add(lbLocHD);
		pnMain.add(cboHDkh);
		pnMain.add(lbLocKH);
		pnMain.add(cboKH);
		pnMain.add(pnHoaDon);
		pnMain.add(pnKhachhang);
		pnMain.add(lbdoanhthu);
		pnMain.add(lbVnd);
		pnMain.add(txtDoanhthu);
		pnMain.add(btnQuaylai);
		pnMain.add(btnDangxuat);
		pnMain.add(btnThoat);

		getContentPane().add(pnMain);
		btnQuaylai.addActionListener(this);
		btnDangxuat.addActionListener(this);
		btnThoat.addActionListener(this);
		cboHDkh.addActionListener(this);
		cboKH.addActionListener(this);
		
		int tong = Tinhtongdoanhthu();
		String doanhthu = String.valueOf(tong);
		txtDoanhthu.setText(doanhthu);
	}

//	public static void main(String[] args) {
//		new FrmBaoCaoThongKe().setVisible(true);
//
//	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangxuat)) {
			FrmDangnhap dn = new FrmDangnhap();
			dn.setVisible(true);
			this.dispose();
			JOptionPane.showMessageDialog(this, "Đăng xuất thành công");

		} else if (o.equals(btnQuaylai)) {
			FrmChucnangNhanVien nv = new FrmChucnangNhanVien();
			nv.setVisible(true);
			this.dispose();

		} else if (o.equals(btnThoat)) {
			System.exit(0);

		} else if (o.equals(cboHDkh)) {
			int rowCount = model1.getRowCount();
			// Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				model1.removeRow(i);
			}
			String idkh = (String) cboHDkh.getSelectedItem();
			if (idkh.equals("")) {
				

			} else {

			

			}
		} else if (o.equals(cboKH)) {
			int rowCount = model2.getRowCount();
			// Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				model2.removeRow(i);
			}
			String idkh = (String) cboKH.getSelectedItem();
			if (idkh.equals("")) {
				try {
					DocDuLieuVaoModel2(kh_dao.getAllDsKhachhang());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {

				

			}
		}
	}

	public void DocDuLieuDatabaseVaoTable1() throws RemoteException {

		List<HoaDon> list = hd_dao.getAllDsHoadon();

		for (HoaDon hd : list) {
			model1.addRow(new Object[] { hd.getMaHD(), hd.getKhachhang().getMaKH(),
					hd.getNhanvien().getMaNV(), hd.getNgaylapHD()
					 });
		}
	}

	private void DocDuLieuVaoModel2(List<KhachHang> list) {
		for (int i = 0; i < list.size(); i++) {
			KhachHang kh = list.get(i);
			model2.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getDiachiKH(), kh.getSdtKH() });
		}
	}

	public int Tinhtongdoanhthu() {
		int tong = 0;
		for (int i = 0; i < table1.getRowCount(); i++) {
			tong = (Integer) table1.getValueAt(i, 7) + tong;

		}
		return tong;
	}

	public void Docdulieukhachhang(String idkh) throws RemoteException {

//		List<HoaDon> list = (List<HoaDon>) hd_dao.getHoaDontheomaKH(idkh);
//
//		for (HoaDon hd : list) {
//			model1.addRow(new Object[] { hd.getMaHD(), hd.getKhachhang().getMaKH(),
//					hd.getNhanvien().getMaNV(), hd.getNgaylapHD() });
//		}
	}

	public void Docdulieukhachhangtheoma(String idkh) throws RemoteException {

		List<KhachHang> list = (List<KhachHang>) kh_dao.getTheoMaKH(idkh);

		for (KhachHang hd : list) {
			model2.addRow(new Object[] { hd.getMaKH(), hd.getTenKH(), hd.getDiachiKH(), hd.getSdtKH() });
		}
	}

	private void DocDuLieuKhachHangcaocbo() throws RemoteException  {
		cboHDkh.addItem("");
		cboKH.addItem("");
		dsKhachhang = kh_dao.getAllDsKhachhang();
		for (KhachHang item : dsKhachhang) {
			cboHDkh.addItem(item.getMaKH());
			cboKH.addItem(item.getMaKH());
		}
	}
}
