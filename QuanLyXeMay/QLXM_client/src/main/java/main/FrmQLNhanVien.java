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
import dao.NhanVien_DAO;
import entity.NhanVien;




public class FrmQLNhanVien extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	// Khai báo cột của Table
	String[] cols = { "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Địa chỉ", "Email" };
	private DefaultTableModel model;
	private JTable table;
	private JButton btnQuaylai, btnDangxuat, btnThoat;
	private JTextField txtTim;
	private JTextField txtmaNV;
	private JTextField txttenNV;
	private JTextField txtsdtNV;
	private JTextField txtdiachiNV;
	private JTextField txtemailNV;

	private NhanVien_DAO nv_DAO;
	private Account_DAO acc_DAO;
	
	private ArrayList<NhanVien> dsNhanVien;

	public FrmQLNhanVien() {
	
		 try {
	            String urlNV = "rmi://localhost:9090/nvDao";
	            nv_DAO = (NhanVien_DAO) Naming.lookup(urlNV);
	            String urlACC ="rmi://localhost:9090/tkDao";
	            acc_DAO = (Account_DAO) Naming.lookup(urlACC);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		setTitle("Quản Lý Nhân Viên");
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
		JLabel lbTitle = new JLabel("Quản Lý Nhân Viên");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLUE);

		// Khai báo Label, TextField, Button
		JLabel lbmaNV = new JLabel("Mã Nhân Viên: ");
		JLabel lbtenNV = new JLabel("Tên Nhân Viên: ");
		JLabel lbsdtNV = new JLabel("Số Điện Thoại: ");
		JLabel lbdiachiNV = new JLabel("Địa Chỉ: ");
		JLabel lbemailNV = new JLabel("Email: ");
		JLabel lbTim = new JLabel("Tìm theo mã: ");
		lbTim.setFont(new Font("Arial", Font.BOLD, 20));
		lbTim.setForeground(Color.RED);

		txtmaNV = new JTextField();
		txttenNV = new JTextField();
		txtsdtNV = new JTextField();
		txtdiachiNV = new JTextField();
		txtemailNV = new JTextField();
		txtTim = new JTextField();

		btnTim = new JButton("Tìm");
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xoá Trắng");
		btnXoa = new JButton("Xoá");
		btnSua = new JButton("Sửa");

		btnQuaylai = new JButton("Quay lại");
		btnQuaylai.setIcon(icon1);
		btnDangxuat = new JButton("Đăng Xuất");
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
				BorderFactory.createLineBorder(Color.RED, 2), "Thông tin nhân viên", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
		lbmaNV.setBounds(20, 20, 100, 30);
		txtmaNV.setBounds(130, 20, 450, 25);
		lbtenNV.setBounds(650, 20, 100, 30);
		txttenNV.setBounds(770, 20, 450, 25);
		lbsdtNV.setBounds(20, 60, 100, 30);
		txtsdtNV.setBounds(130, 60, 450, 25);
		lbdiachiNV.setBounds(650, 60, 100, 30);
		txtdiachiNV.setBounds(770, 60, 450, 25);
		lbemailNV.setBounds(20, 100, 100, 30);
		txtemailNV.setBounds(130, 100, 450, 25);

		pnThongtin.add(lbmaNV);
		pnThongtin.add(lbtenNV);
		pnThongtin.add(lbsdtNV);
		pnThongtin.add(lbdiachiNV);
		pnThongtin.add(lbemailNV);
		pnThongtin.add(txtmaNV);
		pnThongtin.add(txttenNV);
		pnThongtin.add(txtsdtNV);
		pnThongtin.add(txtdiachiNV);
		pnThongtin.add(txtemailNV);

		// Panel hiển thị danh sách
		JPanel pnDanhsach = new JPanel();
		pnDanhsach.setLayout(null);
		pnDanhsach.setBounds(10, 210, 1260, 380);
		pnDanhsach.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.RED, 2), "Danh sách nhân viên",
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
			DocDuLieuVaoModel(nv_DAO.getAllDsNhanvien());
			System.out.println("done");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FrmQLNhanVien().setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			txtmaNV.setText("");
			txttenNV.setText("");
			txtsdtNV.setText("");
			txtdiachiNV.setText("");
			txtemailNV.setText("");
			txtTim.setText("");
			txtmaNV.requestFocus();
		} else if (o.equals(btnThem)) {
			if (txtmaNV.getText().equals("") || txttenNV.getText().equals("") || txtsdtNV.getText().equals("") || txtdiachiNV.getText().equals("") || txtemailNV.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "thiếu dữ liệu đầu vào");
			} else if (ktdulieu()) {
				NhanVien nv = getSelectedDataTable();
				if (timmaNV(nv.getMaNV())) {
					JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại");
				} else
					try {
						nv_DAO.create(nv);
						model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSdtNV(), nv.getDiachiNV(),nv.getEmailNV() });
					} catch (Exception e1) {

					}
			}

		} else if (o.equals(btnXoa)) {
			NhanVien nv = getSelectedDataTable();
			int row = table.getSelectedRow();
			try {
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên cần xoá !!!");
				} else {
					int select;
					select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhân viên đã chọn ?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (select == JOptionPane.YES_OPTION) {
						nv_DAO.delete(nv);
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		} else if (o.equals(btnSua)) {
			if (txtmaNV.getText().equals("") || txttenNV.getText().equals("") || txtsdtNV.getText().equals("") || txtdiachiNV.getText().equals("") || txtemailNV.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "thiếu dữ liệu đầu vào");
			} else if (ktdulieu()) {
				NhanVien nv = getSelectedDataTable();
				if (timmaNV(nv.getMaNV()) == false) {
					JOptionPane.showMessageDialog(this, "Không được sửa mã nhân viên, nếu mã mới hay chọn chức năng thêm");
				} else {
					try {
						nv_DAO.update(nv);
						int row = table.getSelectedRow();
						model.setValueAt(nv.getMaNV(), row, 0);
						model.setValueAt(nv.getTenNV(), row, 1);
						model.setValueAt(nv.getSdtNV(), row, 2);
						model.setValueAt(nv.getDiachiNV(), row, 3);
						model.setValueAt(nv.getEmailNV(), row, 4);
						

					} catch (Exception e3) {
						JOptionPane.showMessageDialog(this, "Sửa thông tin thất bại");
					}
				}
			}
		} else if (o.equals(btnTim)) {
			String ma = txtTim.getText().toString();
			String temp = "";
			if (ma.equals("")) {
				JOptionPane.showMessageDialog(this, "Cần nhập mã của nhân viên cần tìm");
			} else if (timmaNV(ma)) {
				int row = table.getSelectedRow();
				txtmaNV.setText(model.getValueAt(row, 0).toString());
				txttenNV.setText(model.getValueAt(row, 1).toString());
				txtsdtNV.setText(model.getValueAt(row, 2).toString());
				txtdiachiNV.setText(model.getValueAt(row, 3).toString());
				txtemailNV.setText(model.getValueAt(row, 4).toString());

			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên theo mã đó");
			}

			int row = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				temp = (String) table.getValueAt(i, 0);
				if (temp.equals(ma)) {
					row = i;
				}
			}

		}else if(o.equals(btnQuaylai)) {
			FrmChucnangAdmin ad=new FrmChucnangAdmin();
			ad.setVisible(true);
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

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtmaNV.setText(model.getValueAt(row, 0).toString());
		txttenNV.setText(model.getValueAt(row, 1).toString());
		txtsdtNV.setText(model.getValueAt(row, 2).toString());
		txtdiachiNV.setText(model.getValueAt(row, 3).toString());
		txtemailNV.setText(model.getValueAt(row, 4).toString());


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
	private NhanVien getSelectedDataTable() {
		String MaNV = txtmaNV.getText().trim();
		String TenNV = txttenNV.getText().trim();
		String SdtNV = txtsdtNV.getText().trim();
		String DiaChiNV = txtdiachiNV.getText().trim();
		String EmailNV = txtemailNV.getText().trim();
		NhanVien nv = new NhanVien(MaNV, TenNV, SdtNV,DiaChiNV,EmailNV);
		return nv;
	}

	public boolean timmaNV(String ma) {
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
		String MaNV = txtmaNV.getText().trim();
		String TenNV = txttenNV.getText().trim();
		String SdtNV = txtsdtNV.getText().trim();
		String DiaChiNV = txtdiachiNV.getText().trim();
		String EmailNV = txtemailNV.getText().trim();
		if(!(MaNV.length()>0 && MaNV.matches("(NV)[0-9]{1,}"))) {
			JOptionPane.showMessageDialog(this, "Mã nhân viên bắt dầu bằng hai kí tự NV viết hoa tiếp theo là các kí tự số");
			txtmaNV.selectAll();
			txtmaNV.requestFocus();
			return false;
		}
		if (!(TenNV.length() > 0 && TenNV.matches("^[^0-9]{2,}$"))) {
			JOptionPane.showMessageDialog(this, "Tên không được chứa số");
			txttenNV.selectAll();
			txttenNV.requestFocus();
			return false;
		}
		

		if (!(SdtNV.length() > 0 && SdtNV.matches("(0+[0-9]{9})"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số, bắt đầu từ số 0");
			txtsdtNV.selectAll();
			txtsdtNV.requestFocus();
			return false;
		}
		if (!(EmailNV.length() > 0 && EmailNV.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))) {
			JOptionPane.showMessageDialog(this, "Nhập email không đúng");
			txtemailNV.selectAll();
			txtemailNV.requestFocus();
			return false;
		}
		
		return true;
	}


	private void DocDuLieuVaoModel(List<NhanVien> list) {
		for (int i = 0; i < list.size(); i++) {
			NhanVien nv = list.get(i);
			model.addRow(
					new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getSdtNV(), nv.getDiachiNV(), nv.getEmailNV() });
		}
	}
}