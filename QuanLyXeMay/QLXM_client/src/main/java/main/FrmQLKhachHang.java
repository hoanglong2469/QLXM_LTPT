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

import dao.Account_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.KhachHang;




public class FrmQLKhachHang extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	// Khai báo cột của Table
	String[] cols = { "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điên thoại" };
	private DefaultTableModel model;
	private JTable table;
	private JButton btnQuaylai, btnDangxuat, btnThoat;
	private JTextField txtTim;
	private JTextField txtMaKh;
	private JTextField txtTenKh;
	private JTextField txtDcKh;
	private JTextField txtSdtKh;


	private KhachHang_DAO kh_DAO;
	private NhanVien_DAO nv_DAO;
	private Account_DAO acc_DAO;
	private ArrayList<KhachHang> dsKhachhang;

	public FrmQLKhachHang() {
		try {
			String urlKH = "rmi://localhost:9090/khDao";
            kh_DAO = (KhachHang_DAO) Naming.lookup(urlKH);
            String urlNV = "rmi://localhost:9090/nvDao";
            nv_DAO = (NhanVien_DAO) Naming.lookup(urlNV);
            String urlACC ="rmi://localhost:9090/tkDao";
            acc_DAO = (Account_DAO) Naming.lookup(urlACC);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		setTitle("Quản Lý Khách Hàng");
		setSize(1300, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Khai báo hình ảnh icon
		ImageIcon icon1 = new ImageIcon("img/Gear-icon.png");
		ImageIcon icon2 = new ImageIcon("img/exit.png");
		ImageIcon icon3 = new ImageIcon("img/thoat.png");

		// Panel Chính
		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, 1300, 750);

		// Title
		JLabel lbTitle = new JLabel("Quản Lý Khách Hàng");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLUE);

		// Khai báo Label, TextField, Button
		JLabel lbmaKH = new JLabel("Mã Khách Hàng: ");
		JLabel lbtenKH = new JLabel("Tên Khách Hàng: ");
		JLabel lbdiachiKH = new JLabel("Địa Chỉ: ");
		JLabel lbsdtKH = new JLabel("Số Điện Thoại: ");
		
		JLabel lbTim = new JLabel("Tìm theo mã: ");
		lbTim.setFont(new Font("Arial", Font.BOLD, 20));
		lbTim.setForeground(Color.RED);

		txtMaKh = new JTextField();
		txtTenKh = new JTextField();
		txtDcKh = new JTextField();
		txtSdtKh = new JTextField();
		txtTim = new JTextField();

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

		// Set bounds Title
		lbTitle.setBounds(500, 10, 300, 30);

		// Panel nhập thông tin
		JPanel pnThongtin = new JPanel();
		pnThongtin.setLayout(null);
		pnThongtin.setBounds(10, 50, 1260, 150);
		pnThongtin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Thông tin khách hàng", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
		lbmaKH.setBounds(20, 30, 100, 30);
		txtMaKh.setBounds(130, 30, 450, 25);
		lbtenKH.setBounds(640, 30, 110, 30);
		txtTenKh.setBounds(770, 30, 450, 25);
		lbdiachiKH.setBounds(20, 70, 100, 30);
		txtDcKh.setBounds(130, 70, 450, 25);
		lbsdtKH.setBounds(640, 70, 110, 30);
		txtSdtKh.setBounds(770, 70, 450, 25);

		pnThongtin.add(lbmaKH);
		pnThongtin.add(lbtenKH);
		pnThongtin.add(lbdiachiKH);
		pnThongtin.add(lbsdtKH);
		pnThongtin.add(txtMaKh);
		pnThongtin.add(txtTenKh);
		pnThongtin.add(txtDcKh);
		pnThongtin.add(txtSdtKh);


		// Panel hiển thị danh sách
		JPanel pnDanhsach = new JPanel();
		pnDanhsach.setLayout(null);
		pnDanhsach.setBounds(10, 210, 1260, 380);
		pnDanhsach.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Danh sách khách hàng",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));

		JPanel pnTable = new JPanel();
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
		pnTable.setBounds(10, 20, 1240, 350);
		sql.setBounds(0, 0, 1240, 350);
		pnDanhsach.add(pnTable);

		// Panel chức năng
		JPanel pnChucnang = new JPanel();
		pnChucnang.setLayout(null);
		pnChucnang.setBounds(10, 600, 1260, 100);
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

		// Thêm vào Panel chính
		pnMain.add(lbTitle);
		pnMain.add(pnThongtin);
		pnMain.add(pnDanhsach);
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
			DocDuLieuVaoModel(kh_DAO.getAllDsKhachhang());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FrmQLKhachHang().setVisible(true);

	}

	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			txtMaKh.setText("");
			txtTenKh.setText("");
			txtDcKh.setText("");
			txtTim.setText("");
			txtSdtKh.setText("");
			txtSdtKh.requestFocus();
		} else if (o.equals(btnThem)) {
			if (txtMaKh.getText().equals("") || txtTenKh.getText().equals("") || txtDcKh.getText().equals("") || txtSdtKh.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "thiếu dữ liệu đầu vào");
			} else if (ktdulieu()) {
				KhachHang kh = getSelectedDataTable();
				if (timmaKH(kh.getMaKH())) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại");
				} else
					try {
						kh_DAO.create(kh);
						model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getDiachiKH(), kh.getSdtKH() });
					} catch (Exception e1) {

					}
			}

		} else if (o.equals(btnXoa)) {
			KhachHang kh = getSelectedDataTable();
			int row = table.getSelectedRow();
			try {
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng cần xoá !!!");
				} else {
					int select;
					select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá khách hàng đã chọn ?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (select == JOptionPane.YES_OPTION) {
						kh_DAO.delete(kh);
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		} else if (o.equals(btnSua)) {
			if (txtMaKh.getText().equals("") || txtTenKh.getText().equals("") || txtDcKh.getText().equals("") || txtSdtKh.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "thiếu dữ liệu đầu vào");
			} else if(ktdulieu()){
				KhachHang kh = getSelectedDataTable();
				if (timmaKH(kh.getMaKH()) == false) {
					JOptionPane.showMessageDialog(this, "Không được sửa mã khách hàng, nếu mã mới hay chọn chức năng thêm");
				} else {
					try {
						kh_DAO.update(kh);
						int row = table.getSelectedRow();
						model.setValueAt(kh.getMaKH(), row, 0);
						model.setValueAt(kh.getTenKH(), row, 1);
						model.setValueAt(kh.getDiachiKH(), row, 2);
						model.setValueAt(kh.getSdtKH(), row, 3);
						

					} catch (Exception e3) {
						JOptionPane.showMessageDialog(this, "Sửa thông tin thất bại");
					}
				}
			}
		} else if (o.equals(btnTim)) {
			String ma = txtTim.getText().toString();
			String temp = "";
			if (ma.equals("")) {
				JOptionPane.showMessageDialog(this, "Cần nhập mã khách hàng cần tìm");
			} else if (timmaKH(ma)) {
				int row = table.getSelectedRow();
				txtMaKh.setText(model.getValueAt(row, 0).toString());
				txtTenKh.setText(model.getValueAt(row, 1).toString());
				txtDcKh.setText(model.getValueAt(row, 2).toString());
				txtSdtKh.setText(model.getValueAt(row, 2).toString());

			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã khách hàng");
			}

			int row = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				temp = (String) table.getValueAt(i, 0);
				if (temp.equals(ma)) {
					row = i;
				}
			}

		}else if(o.equals(btnQuaylai)) {
			FrmChucnangNhanVien nv=new FrmChucnangNhanVien();
			nv.setVisible(true);
			this.dispose();
			
		}else if(o.equals(btnDangxuat)) {
			FrmDangnhap dn = new FrmDangnhap();
			dn.setVisible(true);
			this.dispose();
			JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
			
		}
		else if(o.equals(btnThoat)) {
			System.exit(0);
		}
		// TODO Auto-generated method stub

	}


	private KhachHang getSelectedDataTable() {
		String MaKH = txtMaKh.getText().trim();
		String TenKH = txtTenKh.getText().trim();
		String DiaChiKH = txtDcKh.getText().trim();
		String SdtKH = txtSdtKh.getText().trim();
		KhachHang kh = new KhachHang(MaKH, TenKH, DiaChiKH,SdtKH);
		return kh;
	}

	public boolean timmaKH(String ma) {
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
		String MaKH = txtMaKh.getText().trim();
		String TenKH = txtTenKh.getText().trim();
		String DiaChiKH = txtDcKh.getText().trim();
		String SdtKH = txtSdtKh.getText().trim();
		if(!(MaKH.length()>0 && MaKH.matches("^[A-Z]{2,}+[0-9]{1,}"))) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng bắt đầu bằng hai kí tự hoa tiếp theo là các kí tự số");
			txtMaKh.selectAll();
			txtMaKh.requestFocus();
			return false;
		}
		if (!(TenKH.length() > 0 && TenKH.matches("^[^0-9]{2,}$"))) {
			//if (!(TenKH.length() > 0 && TenKH.matches("^[a-zA-Z \sAÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ]+$"))) {
			JOptionPane.showMessageDialog(this, "Tên không được chứa số");
			txtTenKh.selectAll();
			txtTenKh.requestFocus();
			return false;
		}
		

		if (!(SdtKH.length() > 0 && SdtKH.matches("(0+[0-9]{9})"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số, bắt đầu từ số 0");
			txtSdtKh.selectAll();
			txtSdtKh.requestFocus();
			return false;
		}
		
		return true;
	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaKh.setText(model.getValueAt(row, 0).toString());
		txtTenKh.setText(model.getValueAt(row, 1).toString());
		txtDcKh.setText(model.getValueAt(row, 2).toString());
		txtSdtKh.setText(model.getValueAt(row, 3).toString());

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

	private void DocDuLieuVaoModel(List<KhachHang> list) {
		for (int i = 0; i < list.size(); i++) {
			KhachHang kh = list.get(i);
			model.addRow(
					new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getDiachiKH(), kh.getSdtKH() });
		}
	}
}