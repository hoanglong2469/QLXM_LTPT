package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.KhachHang;
import entity.NhanVien;

public interface NhanVien_DAO extends Remote {
	public List<NhanVien> getAllDsNhanvien() throws RemoteException;
	public NhanVien getTheoMaNV(String MaNV)throws RemoteException;
	public boolean create(NhanVien nv)throws RemoteException;

	public boolean update(NhanVien nv)throws RemoteException;
	
	public boolean delete(NhanVien nv)throws RemoteException;
}
