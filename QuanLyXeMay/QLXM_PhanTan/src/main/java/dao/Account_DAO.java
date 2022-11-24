package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.*;
import util.HibernateUtil;

public interface Account_DAO extends Remote {

	 public List<Account> getAllDsAccount() throws RemoteException;
	 public Account getTheoUsername(String Username) throws RemoteException;
	 
    
}
