package app;

import java.rmi.RemoteException;
import java.util.List;

import dao.Account_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao.impl.Account_impl;
import dao.impl.HoaDon_impl;
import dao.impl.KhachHang_impl;
import dao.impl.NhanVien_impl;
import dao.impl.SanPham_impl;
import entity.Account;
import entity.SanPham;

public class Test {
	public static void main(String[] args) {
	try {
		Account_DAO tkDao = new Account_impl();
		
		
		//tim sp theo ma
		List<Account> tk = tkDao.getAllDsAccount();
		System.out.println("Tai Khoan"+tk);
		
//		SanPham sp = tkDao.getTheoMaSanpham("XZ01");
//		System.out.println("san pham "+sp);
		
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
