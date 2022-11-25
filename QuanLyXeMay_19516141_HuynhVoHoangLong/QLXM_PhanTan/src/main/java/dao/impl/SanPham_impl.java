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

import dao.SanPham_DAO;
import entity.SanPham;
import util.HibernateUtil;

public class SanPham_impl extends UnicastRemoteObject implements SanPham_DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;

	public SanPham_impl() throws RemoteException {
		em = HibernateUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	public List<SanPham> getAllDsSanpham() throws RemoteException {
		List<SanPham> dsSanpham = new ArrayList<SanPham>();
		EntityTransaction tr = em.getTransaction();
		String sql = "select * from Xe";
		try {
			tr.begin();
			dsSanpham =em.createNativeQuery(sql,SanPham.class).getResultList();
			System.out.println("list dao"+dsSanpham);
			tr.commit();

		} catch (RuntimeException e) {
			e.printStackTrace();
			tr.rollback();
		}
		return dsSanpham;

	}

	public SanPham getTheoMaSanpham(String Ma_Xe) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		SanPham sp = new SanPham();
		try {
			tr.begin();
			sp = em.find(SanPham.class, Ma_Xe);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return sp;
	}

	public boolean create(SanPham xe) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String sql ="INSERT [dbo].[Xe] ( [Ma_Xe], [Ten_Xe], [Loai_Xe], [Nam_SX], [So_PK], [So_Khung], [So_Suon],[Mau_Xe],[Gia_Xe]) VALUES ('"+xe.getMaXe()+"' , '"+xe.getTenXe()+"', '"+xe.getLoaiXe()+"', '"+xe.getNamSX()+"', '"+xe.getSoPK()+"', '"+xe.getSoKhung()+"', '"+xe.getSoSuon()+"','"+xe.getMauXe()+"','"+xe.getGiaXe()+"')";
			int n = em.createNativeQuery(sql,SanPham.class).executeUpdate();
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

	public boolean update(SanPham xe) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(xe);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	public boolean delete(SanPham xe) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(SanPham.class, xe));
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	
}
