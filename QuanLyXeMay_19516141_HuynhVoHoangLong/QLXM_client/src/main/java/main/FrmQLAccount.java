package main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;

import dao.Account_DAO;
import entity.Account;
import entity.NhanVien;

import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;
import java.sql.*;

public class FrmQLAccount extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername, txtPass, txtLoai;
	private JTextField txtmaNV;
	private JButton btnTim, btnThem, btnXoaTrang, btnXoa, btnSua;
	String[] cols = { "Loại Tài khoản", "UserName", "PassWord","MaNV" };
	private DefaultTableModel model;
	private JTable table;
	private JButton btnQuaylai, btnDangxuat, btnThoat;

	private Account_DAO acc_DAO;

	private ArrayList<Account> dsAccount;
	private JTextField txtTim;

	public FrmQLAccount() {
		
		try {
			String urlACC ="rmi://localhost:9090/tkDao";
            acc_DAO = (Account_DAO) Naming.lookup(urlACC);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		setTitle("Quản Lý Tài khoản");
		setSize(1300, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Khai báo hình ảnh icon
		ImageIcon icon1=new ImageIcon("img/Gear-icon.png");
		ImageIcon icon2=new ImageIcon("img/exit.png");
		ImageIcon icon3=new ImageIcon("img/thoat.png");
		
		//Panel Chính
		JPanel pnMain = new JPanel();
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, 1300, 750);
		
		//Title
		JLabel lbTitle = new JLabel("Quản Lý Tài Khoản");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 25));
		lbTitle.setForeground(Color.BLUE);
		
		//Khai báo Label, TextField, Button
		JLabel lbLoai = new JLabel("Loại Tài khoản: ");
		JLabel lbUsername = new JLabel("Username: ");
		JLabel lbPass = new JLabel("Password : ");
		JLabel lbmaNV = new JLabel("Mã Nhân Viên: ");
		JLabel lbTim = new JLabel("Tìm Username: ");
		lbTim.setFont(new Font("Arial", Font.BOLD, 17));
		lbTim.setForeground(Color.RED);
		
		txtLoai = new JTextField();
		txtUsername = new JTextField();
		txtPass = new JTextField();
		txtmaNV = new JTextField();
		
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
			
		//Set bounds Title
		lbTitle.setBounds(500, 10, 300, 30);
		
		//Panel nhập thông tin
		JPanel pnThongtin = new JPanel();
		pnThongtin.setLayout(null);
		pnThongtin.setBounds(10,50,1260,150);
		pnThongtin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 2), "Thông tin tài khoản", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
		lbLoai.setBounds(320, 20, 100, 30); txtLoai.setBounds(430, 20, 450, 25);
		lbUsername.setBounds(320, 60, 100, 30); txtUsername.setBounds(430, 60, 450, 25);
		lbPass.setBounds(320, 100, 100, 30); txtPass.setBounds(430, 100, 450, 25);

			
		pnThongtin.add(lbLoai);
		pnThongtin.add(lbUsername);
		pnThongtin.add(lbPass);
		pnThongtin.add(txtLoai);
		pnThongtin.add(txtUsername);
		pnThongtin.add(txtPass);

		
		
		//Panel hiển thị danh sách
		JPanel pnDanhsach = new JPanel();
		pnDanhsach.setLayout(null);
		pnDanhsach.setBounds(10,210,1260,380);
		pnDanhsach.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 2), "Danh sách tài khoản", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
		
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
		
		
		//Panel chức năng
		JPanel pnChucnang = new JPanel();
		pnChucnang.setLayout(null);
		pnChucnang.setBounds(10,600,1260,100);
		pnChucnang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 2), "Chức năng", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14))));
		
		lbTim.setBounds(10, 40, 150, 30);
		txtTim.setBounds(145, 40, 145, 30);
		btnTim.setBounds(300, 40, 70, 30);
		btnThem.setBounds(375, 40, 70, 30);
		btnXoaTrang.setBounds(450, 40, 95, 30);
		btnXoa.setBounds(550, 40, 70, 30);
		btnSua.setBounds(625, 40, 70, 30);
		
		btnQuaylai.setBounds(800,30,130,50);
		btnDangxuat.setBounds(950,30,130,50);
		btnThoat.setBounds(1100,30,130,50);
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

		
		//Thêm vào Panel chính
		pnMain.add(lbTitle);
		pnMain.add(pnThongtin);
		pnMain.add(pnDanhsach);
		pnMain.add(pnChucnang);


		getContentPane().add(pnMain);
		
		//Lắng nghe sự kiện
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
			DocDuLieuVaoModel(acc_DAO.getAllDsAccount());
			System.out.println("done");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FrmQLAccount().setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXoaTrang)) {
			txtLoai.setText("");
			txtUsername.setText("");
			txtPass.setText("");
			txtTim.setText("");
			txtLoai.requestFocus();
		} else if (o.equals(btnThem)) {
			if (txtLoai.getText().equals("") || txtUsername.getText().equals("") || txtPass.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "thiếu dữ liệu đầu vào");
			} else if (KiemTraLoai() && ktdulieu()) {
				Account acc = getSelectedDataTable();
				if (timUser(acc.getUsername())) {
					JOptionPane.showMessageDialog(this, "Username đã tồn tại");
				} else
					try {
						model.addRow(new Object[] { acc.getLoai_Account(), acc.getUsername(), acc.getPass(),acc.getNhanvien().getMaNV()  });
					} catch (Exception e1) {

					}
			}

		} else if (o.equals(btnXoa)) {
			Account acc = getSelectedDataTable();
			int row = table.getSelectedRow();
			try {
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn tài khoản cần xoá !!!");
				} else {
					int select;
					select = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá tài khoản đã chọn ?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if (select == JOptionPane.YES_OPTION) {
						//acc_DAO.delete(acc);
						model.removeRow(row);
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Xóa thất bại");
			}
		} else if (o.equals(btnSua)) {
			if (txtLoai.getText().equals("") || txtUsername.getText().equals("") || txtPass.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Thiếu dữ liệu đầu vào");
			} else if(KiemTraLoai() && ktdulieu()){
				Account acc = getSelectedDataTable();
				if (timUser(acc.getUsername()) == false) {
					JOptionPane.showMessageDialog(this, "Không được sửa Username");
				} else {
					try {
						//acc_DAO.update(acc);
						int row = table.getSelectedRow();
						model.setValueAt(acc.getLoai_Account(), row, 0);
						model.setValueAt(acc.getUsername(), row, 1);
						model.setValueAt(acc.getPass(), row, 2);

					} catch (Exception e3) {
						JOptionPane.showMessageDialog(this, "Sửa thông tin thất bại");
					}
				}
			}
		} else if (o.equals(btnTim)) {
			String User = txtTim.getText().toString();
			String temp = "";
			if (User.equals("")) {
				JOptionPane.showMessageDialog(this, "Cần nhập Username cần tìm");
			} else if (timUser(User)) {
				int row = table.getSelectedRow();
				txtLoai.setText(model.getValueAt(row, 0).toString());
				txtUsername.setText(model.getValueAt(row, 1).toString());
				txtPass.setText(model.getValueAt(row, 2).toString());

			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy Username");
			}

			int row = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				temp = (String) table.getValueAt(i, 0);
				if (temp.equals(User)) {
					row = i;
				}
			}

		}else if(o.equals(btnQuaylai)) {
			FrmChucnangAdmin adm=new FrmChucnangAdmin();
			adm.setVisible(true);
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
		int row = table.getSelectedRow();
		txtLoai.setText(model.getValueAt(row, 0).toString());
		txtUsername.setText(model.getValueAt(row, 1).toString());
		txtPass.setText(model.getValueAt(row, 2).toString());
		txtmaNV.setText(model.getValueAt(row, 3).toString());

	}

	
	public void mousePressed(MouseEvent e) {

	}

	
	public void mouseReleased(MouseEvent e) {

	}

	
	public void mouseEntered(MouseEvent e) {

	}

	
	public void mouseExited(MouseEvent e) {

	}

	

	private boolean KiemTraLoai() {
		String Loai = txtLoai.getText().trim();
		if (!(Loai.equals("NhanVien") || (Loai.equals("Admin")))) {
			JOptionPane.showMessageDialog(this, "Loại tài khoản phải là NhanVien hoặc Admin");
			return false;
		}
		return true;
	}

	private Account getSelectedDataTable() {
		String Loai = txtLoai.getText().trim();
		String User = txtUsername.getText().trim();
		String Pass = txtPass.getText().trim();
		Account acc = new Account(Loai, User, Pass,null);
		return acc;
	}

	public boolean timUser(String User) {
		String temp = "";
		for (int i = 0; i < table.getRowCount(); i++) {
			temp = (String) table.getValueAt(i, 1);
			if (temp.equals(User)) {
				table.setRowSelectionInterval(i, i);
				// scroll đến dòng được chọn
				Rectangle cellRect = table.getCellRect(i, 1, true);
				table.scrollRectToVisible(cellRect);
				return true;
			}
		}
		return false;
	}
	private boolean ktdulieu() {
		String username = txtUsername.getText().trim();
		if(!(username.length()>0 && username.matches("^[a-zA-Z0-9._-]{3,}$"))) {
			JOptionPane.showMessageDialog(this, "Username bắt đầu bằng chữ, số hoặc kí tự ., _, - và tối thiểu là 3 kí tự");
			txtUsername.selectAll();
			txtUsername.requestFocus();
			return false;
		}
		
		return true;
	}
	private void DocDuLieuVaoModel(List<Account> list) {
		for (int i = 0; i < list.size(); i++) {
			Account acc = list.get(i);
			model.addRow(new Object[] { acc.getLoai_Account(), acc.getUsername(), acc.getPass(),acc.getNhanvien().getMaNV() });
		}
	}
}