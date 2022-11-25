package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import entity.HoaDon;

/**
*
* @author HP
*/
public interface HoaDon_DAO extends Remote {
	public List<HoaDon> getAllDsHoadon() throws RemoteException;
	public boolean create(HoaDon hd) throws RemoteException;
	public HoaDon getTheoMaHoadon(String MaHD) throws RemoteException;
	public boolean update(HoaDon hd) throws RemoteException;
	public boolean delete(HoaDon hd) throws RemoteException;
	
}
