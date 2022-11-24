package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.NhanVien_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import util.HibernateUtil;

public class NhanVien_impl extends UnicastRemoteObject implements NhanVien_DAO{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	public NhanVien_impl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	public List<NhanVien> getAllDsNhanvien() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		String sql = "select * from NhanVien";
		try {
			tr.begin();
			dsnv = em.createNativeQuery(sql,NhanVien.class).getResultList();
			System.out.println("list dao"+dsnv);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return dsnv;
	}

//	public List<SanPham> getAllDsSanpham() throws RemoteException {
//		List<SanPham> dsSanpham = new ArrayList<SanPham>();
//		EntityTransaction tr = em.getTransaction();
//		String sql = "select * from Xe";
//		try {
//			tr.begin();
//			dsSanpham =em.createNativeQuery(sql,SanPham.class).getResultList();
//			System.out.println("list dao"+dsSanpham);
//			tr.commit();
//
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			tr.rollback();
//		}
//		return dsSanpham;

//	}
	public NhanVien getTheoMaNV(String MaNV) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		NhanVien nv = new NhanVien();
		try {
			tr.begin();
			nv = em.find(NhanVien.class, MaNV);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return nv;
	}

	@Override
	public boolean create(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql ="INSERT [dbo].[NhanVien] ([Ma_NV], [Ten_NV], [SDT_NV],[Diachi_NV],[Email_NV]) VALUES (N'"+nv.getMaNV()+"', N'"+nv.getTenNV()+"', N'"+nv.getSdtNV()+"', N'"+nv.getDiachiNV()+"',N'"+nv.getEmailNV()+"')";
			int n = em.createNativeQuery(sql,NhanVien.class).executeUpdate();
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

	@Override
	public boolean update(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean delete(NhanVien nv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(NhanVien.class,nv));
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
}
