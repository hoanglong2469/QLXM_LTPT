package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;

import entity.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
*
* @author HP
*/

public interface KhachHang_DAO extends Remote {
	public List<KhachHang> getAllDsKhachhang()throws RemoteException;

	public KhachHang getTheoMaKH(String MaKH)throws RemoteException;
	
	public boolean create(KhachHang kh)throws RemoteException;

	public boolean update(KhachHang kh)throws RemoteException;
	
	public boolean delete(KhachHang kh)throws RemoteException;

}
