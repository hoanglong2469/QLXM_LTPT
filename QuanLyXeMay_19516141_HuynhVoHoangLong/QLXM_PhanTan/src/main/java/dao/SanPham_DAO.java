package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;

import entity.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
*
* @author HP
*/

public  interface SanPham_DAO  extends Remote {
	public List<SanPham> getAllDsSanpham() throws RemoteException;
	public SanPham getTheoMaSanpham(String MaXe)throws RemoteException;
	public boolean create(SanPham xe) throws RemoteException;
	public boolean update(SanPham xe) throws RemoteException;
	public boolean delete(SanPham xe) throws RemoteException;
}
