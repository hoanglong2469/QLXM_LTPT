package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.Account_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao.impl.Account_impl;
import dao.impl.HoaDon_impl;
import dao.impl.KhachHang_impl;
import dao.impl.NhanVien_impl;
import dao.impl.SanPham_impl;


public class AppServer {

    public static void main(String[] args) throws NamingException {
        Hashtable<String, String> env  = new Hashtable<String, String>();
    	env.put("java.security.policy", "policy/policy.policy");
    	Context context = new InitialContext(env);

        try {
        	Account_DAO tkDao = new Account_impl();
			NhanVien_DAO nvDao = new NhanVien_impl();
			KhachHang_DAO khDao = new KhachHang_impl();
			SanPham_DAO spDao = new SanPham_impl();
			HoaDon_DAO hdDao = new HoaDon_impl();

            LocateRegistry.createRegistry(2342);
            context.bind("rmi://localhost:2342/nvDao", nvDao);
            context.bind("rmi://localhost:2342/khDao", khDao);
            context.bind("rmi://localhost:2342/tkDao", tkDao);
            context.bind("rmi://localhost:2342/spDao", spDao);
            context.bind("rmi://localhost:2342/hdDao", hdDao);
            
            System.out.println("Server RMI ready....");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}