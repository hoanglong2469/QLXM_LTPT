package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.SanPham;




public class FrmQLSanPham_NV extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	String[] cols = { "Mã xe", "Tên xe", "Loại xe", "Năm Sản Xuất", "Số Phân khối", "Số Khung", "Số Sườn", "Màu Xe",
			"Giá xe" };
	private DefaultTableModel model;
	private JTable table;
	private JButton btnQuaylai, btnDangxuat, btnThoat;
	private JTextField txtTim;
	private JTextField txtmaXe;
	private JTextField txttenXe;
	private JTextField txtloaiXe;
	private JTextField txtNamSX;
	private JTextField txtsoPK;
	private JTextField txtsoKhung;
	private JTextField txtsoSuon;
	private JTextField txtmauXe;
	private JTextField txtgiaXe;
	private SanPham_DAO sp_DAO;
	private KhachHang_DAO kh_Dao;
	private ArrayList<SanPham> dsSanpham;

	public FrmQLSanPham_NV() {
	

		 try {
	            String urlSP = "rmi://localhost:9090/spDao";
	            sp_DAO = (SanPham_DAO) Naming.lookup(urlSP);
	            String urlKH ="rmi://localhost:9090/khDao";
	            kh_Dao = (KhachHang_DAO) Naming.lookup(urlKH);
	        } catch (Exception e) {
	       }
		setTitle("Quản Lý Sản Phẩm");
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

		JLabel lbTitle = new JLabel("Quản Lý Sản phẩm");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLUE);
		lbTitle.setBounds(530, 10, 1200, 30);

		JLabel lbmaXe = new JLabel("Mã xe: ");
		JLabel lbtenXe = new JLabel("Tên xe: ");
		JLabel lbloaiXe = new JLabel("Loại Xe:");
		JLabel lbNamSX = new JLabel("Năm Sản Xuất: ");
		JLabel lbsoPK = new JLabel("Số Phân khối: ");
		JLabel lbsoKhung = new JLabel("Số Khung: ");
		JLabel lbsoSuon = new JLabel("Số Sườn: ");
		JLabel lbmauXe = new JLabel("Màu Xe: ");
		JLabel lbgiaXe = new JLabel("Giá xe: ");
		JLabel lbTim = new JLabel("Tìm theo mã: ");
		lbTim.setFont(new Font("Arial", Font.BOLD, 20));
		lbTim.setForeground(Color.RED);

		txtmaXe = new JTextField();
		txttenXe = new JTextField();
		txtloaiXe = new JTextField();
		txtNamSX = new JTextField();
		txtsoPK = new JTextField();
		txtsoKhung = new JTextField();
		txtsoSuon = new JTextField();
		txtmauXe = new JTextField();
		txtgiaXe = new JTextField();
		txtTim = new JTextField();

		btnTim = new JButton("Tìm");
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xoá Trắng");
		btnXoa = new JButton("Xoá");
		btnSua = new JButton("Sửa");

		btnQuaylai = new JButton("Quay lại");
		btnQuaylai.setIcon(icon1);
		btnDangxuat = new JButton("Đăng xuất");
		btnDangxuat.setIcon(icon2);
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(icon3);
		// pn Thongtin
		JPanel pnThongtin = new JPanel();
		pnThongtin.setLayout(null);
		pnThongtin.setBounds(10, 50, 1260, 185);
		pnThongtin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Thông tin sản phảm",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));

		lbmaXe.setBounds(20, 30, 100, 20);
		txtmaXe.setBounds(110, 30, 450, 22);
		lbtenXe.setBounds(650, 30, 100, 20);
		txttenXe.setBounds(750, 30, 450, 22);
		lbloaiXe.setBounds(20, 60, 100, 20);
		txtloaiXe.setBounds(110, 60, 450, 22);
		lbNamSX.setBounds(650, 60, 100, 20);
		txtNamSX.setBounds(750, 60, 450, 22);
		lbsoPK.setBounds(20, 90, 110, 20);
		txtsoPK.setBounds(110, 90, 450, 22);
		lbsoKhung.setBounds(650, 90, 100, 20);
		txtsoKhung.setBounds(750, 90, 450, 22);
		lbsoSuon.setBounds(20, 120, 100, 20);
		txtsoSuon.setBounds(110, 120, 450, 22);
		lbmauXe.setBounds(650, 120, 100, 20);
		txtmauXe.setBounds(750, 120, 450, 22);
		lbgiaXe.setBounds(20, 150, 100, 20);
		txtgiaXe.setBounds(110, 150, 450, 22);

		pnThongtin.add(lbmaXe);
		pnThongtin.add(txtmaXe);
		pnThongtin.add(lbtenXe);
		pnThongtin.add(txttenXe);
		pnThongtin.add(lbloaiXe);
		pnThongtin.add(txtloaiXe);
		pnThongtin.add(lbNamSX);
		pnThongtin.add(txtNamSX);
		pnThongtin.add(lbsoPK);
		pnThongtin.add(txtsoPK);
		pnThongtin.add(lbsoKhung);
		pnThongtin.add(txtsoKhung);
		pnThongtin.add(lbsoSuon);
		pnThongtin.add(txtsoSuon);
		pnThongtin.add(lbmauXe);
		pnThongtin.add(txtmauXe);
		pnThongtin.add(lbgiaXe);
		pnThongtin.add(txtgiaXe);

		// int widthLb = 85, widthPn = 700, widthBtn = 70, h = 25, hTxt = 25, xTxt =
		// 105;

		JPanel pnTable = new JPanel();
		pnTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Danh sách sản phẩm",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
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
		pnTable.setBounds(10, 240, 1260, 350);
		sql.setBounds(10, 25, 1235, 310);

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
		pnMain.add(pnThongtin);
		pnMain.add(pnTable);
		pnMain.add(pnChucnang);

		getContentPane().add(pnMain);

		// Lắng nghe sự kiện
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnQuaylai.addActionListener(this);
		btnDangxuat.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
		try {
			DocDuLieuVaoModel(sp_DAO.getAllDsSanpham());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public static void main(String[] args) {
//		new FrmQLSanPham_NV().setVisible(true);
//
//	}

	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			txtmaXe.setText("");
			txttenXe.setText("");
			txtloaiXe.setText("");
			txtNamSX.setText("");
			txtsoPK.setText("");
			txtsoKhung.setText("");
			txtsoSuon.setText("");
			txtmauXe.setText("");
			txtgiaXe.setText("");
			txtmaXe.requestFocus();
		} else if (o.equals(btnThem)) {
			if (txtmaXe.getText().equals("") || txttenXe.getText().equals("") || txtloaiXe.getText().equals("")
					|| txtNamSX.getText().equals("") || txtsoPK.getText().equals("") || txtsoKhung.getText().equals("")
					|| txtsoSuon.getText().equals("") || txtmauXe.getText().equals("")
					|| txtgiaXe.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đầu vào");
			} else if (ktdulieu()) {
				SanPham sp = getSelectedDataTable();
				if (timmaxe(sp.getMaXe())) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại");
				} else
					try {
						sp_DAO.create(sp);
						model.addRow(new Object[] { sp.getMaXe(), sp.getTenXe(), sp.getLoaiXe(), sp.getNamSX(),
								sp.getSoPK(), sp.getSoKhung(), sp.getSoSuon(), sp.getMauXe(), sp.getGiaXe() });
					} catch (Exception e1) {

					}
			}

		} else if (o.equals(btnXoa)) {
			SanPham sp = getSelectedDataTable();
			int row = table.getSelectedRow();
			try {
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn sẩn phẩm cần xoá !!!");
				} else {
					int select;
					select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá sản phẩm đã chọn ?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (select == JOptionPane.YES_OPTION) {
						sp_DAO.delete(sp);
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		} else if (o.equals(btnSua)) {
			if (txtmaXe.getText().equals("") || txttenXe.getText().equals("") || txtloaiXe.getText().equals("")
					|| txtNamSX.getText().equals("") || txtsoPK.getText().equals("") || txtsoKhung.getText().equals("")
					|| txtsoSuon.getText().equals("") || txtmauXe.getText().equals("")
					|| txtgiaXe.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đầu vào");
			} else if(ktdulieu()){
				SanPham sp = getSelectedDataTable();
				if (timmaxe(sp.getMaXe()) == false) {
					JOptionPane.showMessageDialog(this,
							"Không được sửa mã sản phẩm, nếu mã mới hay chọn chức năng thêm");
				} else {
					try {
						sp_DAO.update(sp);
						int row = table.getSelectedRow();
						txtmaXe.setText(model.getValueAt(row, 0).toString());
						txttenXe.setText(model.getValueAt(row, 1).toString());
						txtloaiXe.setText(model.getValueAt(row, 2).toString());
						txtNamSX.setText(model.getValueAt(row, 3).toString());
						txtsoPK.setText(model.getValueAt(row, 4).toString());
						txtsoKhung.setText(model.getValueAt(row, 5).toString());
						txtsoSuon.setText(model.getValueAt(row, 6).toString());
						txtmauXe.setText(model.getValueAt(row, 7).toString());
						txtgiaXe.setText(model.getValueAt(row, 8).toString());

					} catch (Exception e3) {
						JOptionPane.showMessageDialog(this, "Sửa thông tin sản phẩm thất bại");
					}
				}
			}
		} else if (o.equals(btnTim)) {
			String ma = txtTim.getText().toString();
			String temp = "";
			if (ma.equals("")) {
				JOptionPane.showMessageDialog(this, "Cần nhập mã xe cần tìm");
			} else if (timmaxe(ma)) {
				int row = table.getSelectedRow();
				txtmaXe.setText(model.getValueAt(row, 0).toString());
				txttenXe.setText(model.getValueAt(row, 1).toString());
				txtloaiXe.setText(model.getValueAt(row, 2).toString());
				txtNamSX.setText(model.getValueAt(row, 3).toString());
				txtsoPK.setText(model.getValueAt(row, 4).toString());
				txtsoKhung.setText(model.getValueAt(row, 5).toString());
				txtsoSuon.setText(model.getValueAt(row, 6).toString());
				txtmauXe.setText(model.getValueAt(row, 7).toString());
				txtgiaXe.setText(model.getValueAt(row, 8).toString());

			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã xe cần tìm");
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
			JOptionPane.showMessageDialog(this, "Đăng xuất thành công");

		} else if (o.equals(btnThoat)) {
			System.exit(0);
		}
		// TODO Auto-generated method stub

	}

	private SanPham getSelectedDataTable() {
		String maxe = txtmaXe.getText().trim();
		String tenXe = txttenXe.getText().trim();
		String loaiXe = txtloaiXe.getText().trim();
		String NamSX = txtNamSX.getText().trim();
		String soPK = txtsoPK.getText().trim();
		String soKhung = txtsoKhung.getText().trim();
		String soSuon = txtsoSuon.getText().trim();
		String mauXe = txtmauXe.getText().trim();
		String giaXe = txtgiaXe.getText().trim();

		SanPham sp = new SanPham(maxe, tenXe, loaiXe, Integer.parseInt(NamSX), Integer.parseInt(soPK),
				Integer.parseInt(soKhung), Integer.parseInt(soSuon), mauXe, Integer.parseInt(giaXe));
		return sp;
	}

	public boolean timmaxe(String ma) {
		String temp = "";
		for (int i = 0; i < table.getRowCount(); i++) {
			temp = (String) table.getValueAt(i, 0);
			if (temp.equals(ma)) {
				table.setRowSelectionInterval(i, i);
				// scroll đến dòng được chọn
				Rectangle cellRect = table.getCellRect(i, 0, true);
				table.scrollRectToVisible(cellRect);
				return true;
			}
		}
		return false;
	}

	private boolean ktdulieu() {
		String maxe = txtmaXe.getText().trim();
		String tenXe = txttenXe.getText().trim();
		String loaiXe = txtloaiXe.getText().trim();
		String NamSX = txtNamSX.getText().trim();
		String soPK = txtsoPK.getText().trim();
		String soKhung = txtsoKhung.getText().trim();
		String soSuon = txtsoSuon.getText().trim();
		String mauXe = txtmauXe.getText().trim();
		String giaXe = txtgiaXe.getText().trim();
		if (!(maxe.length() > 0 && maxe.matches("^[A-Z]{2,}+[0-9]{1,}"))) {
			JOptionPane.showMessageDialog(this,
					"Mã xe bắt đầu bằng hai kí tự hoa tiếp theo là các kí tự số");
			txtmaXe.selectAll();
			txtmaXe.requestFocus();
			return false;
		}
		if (!(NamSX.length() > 0 && NamSX.matches("^[0-9]{4}$"))) {
			// if (!(TenKH.length() > 0 && TenKH.matches("^[a-zA-Z
			// \sAÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ]+$")))
			// {
			JOptionPane.showMessageDialog(this, "Năm sản xuất gổm 4 kí  tự số");
			txtNamSX.selectAll();
			txtNamSX.requestFocus();
			return false;
		}

		if (!(soPK.length() > 0 && soPK.matches("^[0-9]{1,}$"))) {
			JOptionPane.showMessageDialog(this, "Số phân khối chỉ gồm các kí tự số");
			txtsoPK.selectAll();
			txtsoPK.requestFocus();
			return false;
		}
		if (!(soKhung.length() > 0 && soKhung.matches("^[0-9]{1,}$"))) {
			JOptionPane.showMessageDialog(this, "Số khung chỉ gồm các kí tự số");
			txtsoKhung.selectAll();
			txtsoKhung.requestFocus();
			return false;
		}
		if (!(soSuon.length() > 0 && soSuon.matches("^[0-9]{1,}$"))) {
			JOptionPane.showMessageDialog(this, "Số sườn chỉ gồm các kí tự số");
			txtsoSuon.selectAll();
			txtsoSuon.requestFocus();
			return false;
		}
		if (!(mauXe.length() > 0 && mauXe.matches("^[^0-9]{2,}$"))) {
			JOptionPane.showMessageDialog(this, "Dữ liệu màu xe không được chứa số");
			txtmauXe.selectAll();
			txtmauXe.requestFocus();
			return false;
		}
		if (!(giaXe.length() > 0 && giaXe.matches("^[0-9]{1,}$"))) {
			JOptionPane.showMessageDialog(this, "Nhập sai dữ liệu giá xe");
			txtgiaXe.selectAll();
			txtgiaXe.requestFocus();
			return false;
		}

		return true;

	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtmaXe.setText(model.getValueAt(row, 0).toString());
		txttenXe.setText(model.getValueAt(row, 1).toString());
		txtloaiXe.setText(model.getValueAt(row, 2).toString());
		txtNamSX.setText(model.getValueAt(row, 3).toString());
		txtsoPK.setText(model.getValueAt(row, 4).toString());
		txtsoKhung.setText(model.getValueAt(row, 5).toString());
		txtsoSuon.setText(model.getValueAt(row, 6).toString());
		txtmauXe.setText(model.getValueAt(row, 7).toString());
		txtgiaXe.setText(model.getValueAt(row, 8).toString());

	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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

	private void DocDuLieuVaoModel(List<SanPham> list) {
		for (int i = 0; i < list.size(); i++) {
			SanPham sp = list.get(i);
			model.addRow(new Object[] { sp.getMaXe(), sp.getTenXe(), sp.getLoaiXe(), sp.getNamSX(), sp.getSoPK(),
					sp.getSoKhung(), sp.getSoSuon(), sp.getMauXe(), sp.getGiaXe() });
		}
	}
}
