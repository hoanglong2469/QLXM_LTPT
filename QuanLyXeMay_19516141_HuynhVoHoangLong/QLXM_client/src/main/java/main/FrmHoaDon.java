package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.Date;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

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
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;



public class FrmHoaDon extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	String[] cols = { "Mã hóa đơn", "Mã khách hàng", "Mã xe", "Mã nhân viên", "Ngày lập HD", "Đơn giá",
			"Số Lượng", "Thành Tiền" };
	private DefaultTableModel model;
	private JTable table;
	private JButton btnQuaylai, btnDangxuat, btnThoat;
	private JTextField txtTim;
	private JTextField txtmaHD;
	private JTextField txtmaKH;
	private JTextField txtmaXe;
	private JTextField txtmaNV;
	private JTextField txtngaylapHD;
	private JTextField txtDongia;
	private JTextField txtSoluong;
	private JTextField txtThanhtien;
	private JComboBox<String> cboKH;
	private KhachHang_DAO kh_dao;
	private HoaDon_DAO hd_dao;
	private NhanVien_DAO nv_dao;
	private SanPham_DAO sp_dao;
	 

	public FrmHoaDon() throws RemoteException {
		
		try {
			String urlKH = "rmi://localhost:9090/khDao";
			kh_dao = (KhachHang_DAO) Naming.lookup(urlKH);
            String urlNV = "rmi://localhost:9090/nvDao";
            nv_dao = (NhanVien_DAO) Naming.lookup(urlNV);
            String urlHD ="rmi://localhost:9090/hdDao";
            hd_dao = (HoaDon_DAO) Naming.lookup(urlHD);
            String urlSP ="rmi://localhost:9090/spDao";
            sp_dao = (SanPham_DAO) Naming.lookup(urlSP);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		setTitle("Danh mục hóa đơn");
		setSize(1300, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon icon1 = new ImageIcon("img/Gear-icon.png");
		ImageIcon icon2 = new ImageIcon("img/exit.png");
		ImageIcon icon3 = new ImageIcon("img/thoat.png");

		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, 1300, 750);

		JLabel lbTitle = new JLabel("Danh mục hóa đơn");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setBounds(530, 10, 1200, 30);

		JLabel lbmaHD = new JLabel("Mã hóa đơn: ");
		JLabel lbmaKH = new JLabel("Mã khách hàng: ");
		JLabel lbmaXe = new JLabel("Mã xe:");
		JLabel lbmaNV = new JLabel("Mã nhân viên: ");
		JLabel lbngayHD = new JLabel("Ngày lập HD: ");
		JLabel lbDongia = new JLabel("Đơn giá: ");
		JLabel lbSoluong = new JLabel("Số Lượng: ");
		JLabel lbthanhtien = new JLabel("Thành tiền: ");
		JLabel lbTim = new JLabel("Tìm theo mã: ");
		JLabel lbTimtheokh = new JLabel("Lọc theo mã khách hàng: ");
		lbTim.setFont(new Font("Arial", Font.BOLD, 20));
		lbTim.setForeground(Color.RED);

		txtmaHD = new JTextField();
		txtmaKH = new JTextField();
		txtmaXe = new JTextField();
		txtmaNV = new JTextField();
		txtngaylapHD = new JTextField();
		txtDongia = new JTextField();
		txtSoluong = new JTextField();
		txtThanhtien = new JTextField();
		txtThanhtien.setEditable(false);
		txtTim = new JTextField();
		cboKH = new JComboBox<String>();
		cboKH.setEditable(false);

		cboKH.setPreferredSize(new Dimension(100, 25));
		cboKH.addItem("");
		ArrayList<KhachHang> listKH = (ArrayList<KhachHang>) kh_dao.getAllDsKhachhang();
		for (KhachHang kh : listKH) {
			cboKH.addItem(kh.getMaKH());
		}

		btnTim = new JButton("Tìm");
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xoá Trắng");
		btnXoa = new JButton("Xoá");
		btnSua = new JButton("Sửa");

		btnQuaylai = new JButton("Quay lại");
		btnQuaylai.setIcon(icon1);
		btnDangxuat = new JButton("Đăng xuất");
		btnDangxuat.setIcon(icon2);
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(icon3);
		// pn Thongtin
		JPanel pnThongtin = new JPanel();
		pnThongtin.setLayout(null);
		pnThongtin.setBounds(10, 50, 1260, 170);
		pnThongtin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Thông tin hóa đơn", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));

		lbmaHD.setBounds(20, 30, 100, 20);
		txtmaHD.setBounds(110, 30, 450, 22);
		lbmaKH.setBounds(650, 30, 100, 20);
		txtmaKH.setBounds(750, 30, 450, 22);
		lbmaXe.setBounds(20, 60, 100, 20);
		txtmaXe.setBounds(110, 60, 450, 22);
		lbmaNV.setBounds(650, 60, 100, 20);
		txtmaNV.setBounds(750, 60, 450, 22);
		lbngayHD.setBounds(20, 90, 110, 20);
		txtngaylapHD.setBounds(110, 90, 450, 22);
		lbDongia.setBounds(650, 90, 100, 20);
		txtDongia.setBounds(750, 90, 450, 22);
		lbSoluong.setBounds(20, 120, 100, 20);
		txtSoluong.setBounds(110, 120, 450, 22);
		lbthanhtien.setBounds(650, 120, 100, 20);
		txtThanhtien.setBounds(750, 120, 450, 22);

		pnThongtin.add(lbmaHD);
		pnThongtin.add(txtmaHD);
		pnThongtin.add(lbmaKH);
		pnThongtin.add(txtmaKH);
		pnThongtin.add(lbmaXe);
		pnThongtin.add(txtmaXe);
		pnThongtin.add(lbmaNV);
		pnThongtin.add(txtmaNV);
		pnThongtin.add(lbngayHD);
		pnThongtin.add(txtngaylapHD);
		pnThongtin.add(lbDongia);
		pnThongtin.add(txtDongia);
		pnThongtin.add(lbSoluong);
		pnThongtin.add(txtSoluong);
		pnThongtin.add(lbthanhtien);
		pnThongtin.add(txtThanhtien);

		// int widthLb = 85, widthPn = 700, widthBtn = 70, h = 25, hTxt = 25, xTxt =
		// 105;
		lbTimtheokh.setBounds(800, 240, 300, 30);
		lbTimtheokh.setFont(new Font("Arial", Font.BOLD, 17));
		lbTimtheokh.setForeground(Color.BLACK);
		cboKH.setBounds(1020, 240, 120, 30);

		// Panel danh sách
		JPanel pnTable = new JPanel();
		pnTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Danh sách hóa đơn", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
		pnTable.setLayout(null);
		model = new DefaultTableModel(cols, 0) {
			// khóa không cho người dùng nhập trên table
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		table = new JTable(model);
		JScrollPane sql = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTable.add(sql);
		pnTable.setBounds(10, 265, 1260, 320);
		sql.setBounds(10, 25, 1235, 280);

		// Panel chức năng
		JPanel pnChucnang = new JPanel();
		pnChucnang.setLayout(null);
		pnChucnang.setBounds(10, 590, 1260, 100);
		pnChucnang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Chức năng", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));

		lbTim.setBounds(10, 40, 150, 30);
		txtTim.setBounds(145, 40, 145, 30);
		btnTim.setBounds(300, 40, 70, 30);
		btnThem.setBounds(375, 40, 70, 30);
		btnXoaTrang.setBounds(450, 40, 95, 30);
		btnXoa.setBounds(550, 40, 70, 30);
		btnSua.setBounds(625, 40, 70, 30);

		btnQuaylai.setBounds(800, 30, 130, 50);
		btnDangxuat.setBounds(950, 30, 130, 50);
		btnThoat.setBounds(1100, 30, 130, 50);
		btnQuaylai.setBackground(Color.YELLOW);
		btnDangxuat.setBackground(Color.GREEN);
		btnThoat.setBackground(Color.PINK);

		pnChucnang.add(lbTim);
		pnChucnang.add(txtTim);
		pnChucnang.add(btnTim);
		pnChucnang.add(btnThem);
		pnChucnang.add(btnXoaTrang);
		pnChucnang.add(btnXoa);
		pnChucnang.add(btnSua);
		pnChucnang.add(btnQuaylai);
		pnChucnang.add(btnDangxuat);
		pnChucnang.add(btnThoat);

		// pnMain.add(cboLoai);
		pnMain.add(lbTitle);
		pnMain.add(lbTimtheokh);
		pnMain.add(cboKH);
		pnMain.add(pnThongtin);
		pnMain.add(pnTable);
		pnMain.add(pnChucnang);

		getContentPane().add(pnMain);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnQuaylai.addActionListener(this);
		btnDangxuat.addActionListener(this);
		btnThoat.addActionListener(this);
		cboKH.addActionListener(this);
		table.addMouseListener(this);
		DocDuLieuDatabaseVaoTable();
	}

	public static void main(String[] args) throws RemoteException {
		new FrmHoaDon().setVisible(true);

	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			txtmaHD.setText("");
			txtmaKH.setText("");
			txtmaXe.setText("");
			txtmaNV.setText("");
			txtngaylapHD.setText("");
			txtDongia.setText("");
			txtSoluong.setText("");
			txtThanhtien.setText("");
			txtTim.setText("");
			cboKH.setSelectedItem(0);
			txtmaHD.requestFocus();
		} else if (o.equals(btnThem)) {
			if (txtmaHD.getText().equals("") || txtmaKH.getText().equals("") || txtmaXe.getText().equals("")
					|| txtmaNV.getText().equals("") || txtngaylapHD.getText().equals("")
					|| txtDongia.getText().equals("") || txtSoluong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đầu vào");
			} else
				try {
					if (ktdulieu()) {
						String maHD = txtmaHD.getText().trim();
						String maKH = txtmaKH.getText().trim();
						String maxe = txtmaXe.getText().trim();
						String manv = txtmaNV.getText().trim();
						KhachHang kh = new KhachHang(maKH);
						SanPham sp = new SanPham(maxe);
						NhanVien nv = new NhanVien(manv);
						Date ngaylapHD = Date.valueOf(txtngaylapHD.getText().trim());
						int dongia = Integer.parseInt(txtDongia.getText().trim());
						int soluong = Integer.parseInt(txtSoluong.getText().trim());
						int thanhtien = dongia * soluong;
						HoaDon hd = new HoaDon(maHD, kh, sp, nv, ngaylapHD, dongia, soluong, thanhtien);

						if (Timhoadon(hd.getMaHD())) {
							JOptionPane.showMessageDialog(this, "Mã hóa đơn đã tồn tại");
						} else
							try {
								hd_dao.create(hd);
								model.addRow(new Object[] { hd.getMaHD(), hd.getKhachhang().getMaKH(),hd.getSanpham().getMaXe(),
									 hd.getNhanvien().getMaNV(), hd.getNgaylapHD(),hd.getDonGia(),hd.getSoluong(),hd.getThanhTien()
										 });
							} catch (Exception e1) {

							}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}

		else if (o.equals(btnXoa)) {
			String maHD = txtmaHD.getText().trim();
			String maKH = txtmaKH.getText().trim();
			String maxe = txtmaXe.getText().trim();
			String manv = txtmaNV.getText().trim();
			KhachHang kh = new KhachHang(maKH);
			SanPham sp = new SanPham(maxe);
			NhanVien nv = new NhanVien(manv);
			Date ngaylapHD = Date.valueOf(txtngaylapHD.getText().trim());
			int dongia = Integer.parseInt(txtDongia.getText().trim());
			int soluong = Integer.parseInt(txtSoluong.getText().trim());
			int thanhtien = dongia * soluong;
			HoaDon hd = new HoaDon(maHD, kh, sp, nv, ngaylapHD, thanhtien, thanhtien, thanhtien);
			int row = table.getSelectedRow();
			try {
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn cần xoá !!!");
				} else {
					int select;
					select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá hóa đơn đã chọn ?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (select == JOptionPane.YES_OPTION) {
						hd_dao.delete(hd);
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		} else if (o.equals(btnSua)) {
			if (txtmaHD.getText().equals("") || txtmaKH.getText().equals("") || txtmaXe.getText().equals("")
					|| txtmaNV.getText().equals("") || txtngaylapHD.getText().equals("")
					|| txtDongia.getText().equals("") || txtSoluong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đầu vào");
			} else
				try {
					if(ktdulieu()) {
						String maHD = txtmaHD.getText().trim();
						String maKH = txtmaKH.getText().trim();
						String maxe = txtmaXe.getText().trim();
						String manv = txtmaNV.getText().trim();
						KhachHang kh = new KhachHang(maKH);
						SanPham sp = new SanPham(maxe);
						NhanVien nv = new NhanVien(manv);
						Date ngaylapHD = Date.valueOf(txtngaylapHD.getText().trim());
						int dongia = Integer.parseInt(txtDongia.getText().trim());
						int soluong = Integer.parseInt(txtSoluong.getText().trim());
						int thanhtien = dongia * soluong;
						HoaDon hd = new HoaDon(maHD, kh, sp, nv, ngaylapHD, thanhtien, thanhtien, thanhtien);
						if (Timhoadon(hd.getMaHD()) == false) {
							JOptionPane.showMessageDialog(this, "Không được sửa mã hóa đơn");
						} else {
							try {
								hd_dao.update(hd);
								int row = table.getSelectedRow();
								model.setValueAt(hd.getMaHD(), row, 0);
								model.setValueAt(hd.getKhachhang().getMaKH(), row, 1);
								model.setValueAt(hd.getNhanvien().getMaNV(), row, 2);
								model.setValueAt(hd.getNgaylapHD(), row, 4);

							} catch (Exception e3) {
								JOptionPane.showMessageDialog(this, "Sửa thông tin thất bại");
							}
						}
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} else if (o.equals(btnTim)) {
			String ma = txtTim.getText().toString();
			String temp = "";
			if (ma.equals("")) {
				JOptionPane.showMessageDialog(this, "Cần nhập mã hóa đơn cần tìm");
			} else if (Timhoadon(ma)) {
				int row = table.getSelectedRow();
				txtmaHD.setText(model.getValueAt(row, 0).toString());
				txtmaKH.setText(model.getValueAt(row, 1).toString());
				txtmaXe.setText(model.getValueAt(row, 2).toString());
				txtmaNV.setText(model.getValueAt(row, 3).toString());
				txtngaylapHD.setText(model.getValueAt(row, 4).toString());
				txtDongia.setText(model.getValueAt(row, 5).toString());
				txtSoluong.setText(model.getValueAt(row, 6).toString());
				txtThanhtien.setText(model.getValueAt(row, 7).toString());

			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã hóa đơn");
			}

			int row = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				temp = (String) table.getValueAt(i, 0);
				if (temp.equals(ma)) {
					row = i;
				}
			}

		} else if (o.equals(btnQuaylai)) {
			FrmChucnangNhanVien nv = new FrmChucnangNhanVien();
			nv.setVisible(true);
			this.dispose();

		} else if (o.equals(btnDangxuat)) {
			FrmDangnhap dn = new FrmDangnhap();
			dn.setVisible(true);
			this.dispose();
			JOptionPane.showMessageDialog(this, "Đăng xuất thành công");

		} else if (o.equals(btnThoat)) {
			System.exit(0);

		} else if (o.equals(cboKH)) {
			int rowCount = model.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			String idkh = (String) cboKH.getSelectedItem();
			if (idkh.equals("")) {
				try {
					DocDuLieuDatabaseVaoTable();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {

				

			}
			
		}
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtmaHD.setText(model.getValueAt(row, 0).toString());
		txtmaKH.setText(model.getValueAt(row, 1).toString());
		txtmaXe.setText(model.getValueAt(row, 2).toString());
		txtmaNV.setText(model.getValueAt(row, 3).toString());
		txtngaylapHD.setText(model.getValueAt(row, 4).toString());
		txtDongia.setText(model.getValueAt(row, 5).toString());
		txtSoluong.setText(model.getValueAt(row, 6).toString());
		txtThanhtien.setText(model.getValueAt(row, 7).toString());

	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
		private boolean ktdulieu() throws HeadlessException, RemoteException {
			String mahd = txtmaHD.getText().trim();
			String makh = txtmaKH.getText().trim();
			String maxe = txtmaXe.getText().trim();
			String manv = txtmaNV.getText().trim();
			String ngaylaphd = txtngaylapHD.getText().trim();
			String dongia = txtDongia.getText().trim();
			String soluong = txtSoluong.getText().trim();
			
			if (!(mahd.length() > 0 && mahd.matches("^[A-Z]{2,}+[0-9]{1,}"))) {
				JOptionPane.showMessageDialog(this,
						"Mã hóa đơn bắt đầu bằng hai kí tự hoa tiếp theo là các kí tự số");
				txtmaHD.selectAll();
				txtmaHD.requestFocus();
				return false;
			}
			if (!(makh.length() > 0 && makh.matches("^[A-Z]{2,}+[0-9]{1,}"))) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng bắt đầu bằng hai kí tự hoa tiếp theo là các kí tự số, và mã khách hàng phải có trong danh sách khách hàng! ");
				txtmaKH.selectAll();
				txtmaKH.requestFocus();
				return false;
				
			}
			if(!(timtheomakh(makh))) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng không có trong danh sách khách hàng! ");
				return false;
			}
			if (!(maxe.length() > 0 && maxe.matches("^[A-Z]{2,}+[0-9]{1,}"))) {
				JOptionPane.showMessageDialog(this, "Mã xe bắt đầu bằng hai kí tự hoa tiếp theo là các kí tự số, và mã xe phải có trong danh sách sản phẩm! ");
				txtmaXe.selectAll();
				txtmaXe.requestFocus();
				return false;
				
			}
			if(!(timtheomaxe(maxe))) {
				JOptionPane.showMessageDialog(this, "Mã xe không có trong danh sách sản phẩm! ");
				return false;
			}
			if (!(manv.length() > 0 && manv.matches("(NV)[0-9]{1,}"))) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên bắt đầu bằng hai kí tự NV viết hoa tiếp theo là các kí tự số, và phải có trong danh sách nhân viên ");
				txtmaNV.selectAll();
				txtmaNV.requestFocus();
				return false;
				
			}
			if(!(timtheomanv(manv))) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên không có trong danh sách nhân viên! ");
				return false;
			}
			if (!(ngaylaphd.length() > 0 && ngaylaphd.matches("^[0-9]{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$"))) {
				JOptionPane.showMessageDialog(this, "Ngày lập phải theo đúng định dạng yyyy-mm-dd");
				txtngaylapHD.selectAll();
				txtngaylapHD.requestFocus();
				return false;
			}
			if (!(dongia.length() > 0 && dongia.matches("^[0-9]{1,}$"))) {
				JOptionPane.showMessageDialog(this, "Đơn giá chỉ bao gồm các kí tự số");
				txtDongia.selectAll();
				txtDongia.requestFocus();
				return false;
			}
			if (!(soluong.length() > 0 && soluong.matches("^[0-9]{1,}$"))) {
				JOptionPane.showMessageDialog(this, "Đơn giá chỉ bao gồm các kí tự số");
				txtSoluong.selectAll();
				txtSoluong.requestFocus();
				return false;
			}
			
			return true;

		
	}

	public boolean Timhoadon(String Mahd) {
		String temp = "";
		for (int i = 0; i < table.getRowCount(); i++) {
			temp = (String) table.getValueAt(i, 0);
			if (temp.equals(Mahd)) {
				table.setRowSelectionInterval(i, i);
				// scroll đến dòng được chọn
				Rectangle cellRect = table.getCellRect(i, 0, true);
				table.scrollRectToVisible(cellRect);
				return true;
			}
		}
		return false;
	}

	public void DocDuLieuDatabaseVaoTable() throws RemoteException {

		List<HoaDon> list = hd_dao.getAllDsHoadon();

		for (HoaDon hd : list) {
			model.addRow(new Object[] { hd.getMaHD(), hd.getKhachhang().getMaKH(),hd.getSanpham().getMaXe(),
					hd.getNhanvien().getMaNV(), hd.getNgaylapHD(),hd.getDonGia(), hd.getSoluong() ,hd.getThanhTien()});
		}
	}
	public boolean timtheomakh(String ma) throws RemoteException {
		
		ArrayList<KhachHang> dsAccount;
		dsAccount = (ArrayList<KhachHang>) kh_dao.getAllDsKhachhang();
		int i;
		for (i = 0; i < dsAccount.size(); i++) {
			KhachHang kh = dsAccount.get(i);
			if (ma.equals(kh.getMaKH())) {
				
				return true;
			}
		}
		return false;
	}
	public boolean timtheomaxe(String ma)  throws RemoteException {
		 
		ArrayList<SanPham> dsSanpham;
		dsSanpham = (ArrayList<SanPham>) sp_dao.getAllDsSanpham();
		int i;
		for (i = 0; i < dsSanpham.size(); i++) {
			SanPham sp = dsSanpham.get(i);
			if (ma.equals(sp.getMaXe())) {
				
				return true;
			}
		}
		return false;
	}
	public boolean timtheomanv(String ma) throws RemoteException {
		
		List<NhanVien> dsnv;
		dsnv = nv_dao.getAllDsNhanvien();
		int i;
		for (i = 0; i < dsnv.size(); i++) {
			NhanVien nv = dsnv.get(i);
			if (ma.equals(nv.getMaNV())) {
				
				return true;
			}
		}
		return false;
	}
	
	
}
