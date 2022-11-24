package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.KhachHang_DAO;
import entity.KhachHang;
import util.HibernateUtil;


public class KhachHang_impl extends UnicastRemoteObject implements KhachHang_DAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	public KhachHang_impl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	public List<KhachHang> getAllDsKhachhang() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String sql = "select * from KhachHang";
		try {
			tr.begin();
			dskh =  em.createNativeQuery(sql,KhachHang.class).getResultList();
			System.out.println("list kh"+dskh);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return dskh;
	}

	public KhachHang getTheoMaKH(String MaKH) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		KhachHang kh = new KhachHang();
		try {
			tr.begin();
			kh = em.find(KhachHang.class, MaKH);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return kh;
	}

	public boolean create(KhachHang kh) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql ="INSERT [dbo].[KhachHang] ([Ma_KhachHang], [Ten_KhachHang], [DiaChi_KhachHang],[SDT_KhachHang]) VALUES (N'"+kh.getMaKH()+"', N'"+kh.getTenKH()+"', N'"+kh.getDiachiKH()+"', N'"+kh.getSdtKH()+"')";
			int n = em.createNativeQuery(sql,KhachHang.class).executeUpdate();
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

	public boolean update(KhachHang kh) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(kh);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
	public boolean delete(KhachHang kh) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(KhachHang.class,kh));
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	

}
