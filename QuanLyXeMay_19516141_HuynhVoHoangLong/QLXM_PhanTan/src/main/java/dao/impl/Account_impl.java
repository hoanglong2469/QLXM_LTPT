package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.Account_DAO;
import entity.*;
import util.HibernateUtil;

public class Account_impl extends UnicastRemoteObject implements Account_DAO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8020974713419487070L;
	/**
	 * 
	 */
	
	private EntityManager em;
    public Account_impl() throws RemoteException {
    	em = HibernateUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}
	@Override
	public ArrayList<Account> getAllDsAccount() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		ArrayList<Account> acc = new ArrayList<Account>();
		String sql = "select * from Account";
		try {
			tr.begin();
			acc = (ArrayList<Account>) em.createNamedQuery(sql).getResultList();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return acc;
	}
	@Override
	public Account getTheoUsername(String Username) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		Account acc = new Account();
		try {
			tr.begin();
			acc = em.find(Account.class, Username);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return acc;
	}

	

    
}
