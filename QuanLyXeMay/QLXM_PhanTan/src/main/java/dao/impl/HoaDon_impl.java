package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;
import entity.HoaDon;
import entity.SanPham;
import util.HibernateUtil;

public class HoaDon_impl extends UnicastRemoteObject implements HoaDon_DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	public HoaDon_impl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	public List<HoaDon> getAllDsHoadon() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDon> dshd = new ArrayList<HoaDon>();
		String sql = "select * from HoaDon";
		try {
			tr.begin();
			dshd =em.createNativeQuery(sql,HoaDon.class).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return dshd;
	}

	public boolean create(HoaDon hd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql ="INSERT [dbo].[HoaDon]([Ma_HD], [Ma_KhachHang], [Ma_Xe], [Ma_NV],[NgayLap_HD], [Do_nGia], [So_Luong], [Thanh_Tien]) VALUES ( '"+hd.getMaHD()+"', N'"+hd.getKhachhang().getMaKH()+"', N'"+hd.getSanpham().getMaXe()+"',N'"+hd.getNhanvien().getMaNV()+"',N'"+hd.getNgaylapHD()+"',N'"+hd.getDonGia()+"',N'"+hd.getSoluong()+"',N'"+hd.getThanhTien()+"')";
			int n = em.createNativeQuery(sql,HoaDon.class).executeUpdate();
			tr.commit();
			if(n>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	public HoaDon getTheoMaHoadon(String MaHD) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		HoaDon hd = new HoaDon();
		try {
			tr.begin();
			hd = em.find(HoaDon.class, MaHD);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return hd;
	}

	public boolean update(HoaDon hd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(hd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	public boolean delete(HoaDon hd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(HoaDon.class, hd));
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
}
