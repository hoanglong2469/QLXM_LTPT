package entity;


import java.io.Serializable;
import java.sql.*;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author HP
*/
@Entity
@Table(name="HoaDon")

public class HoaDon implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Ma_HD")
	private String maHD;
	
	@ManyToOne
	@JoinColumn(name="Ma_KhachHang")
	private KhachHang khachhang;
	
	@ManyToOne
	@JoinColumn(name="Ma_Xe")
	private SanPham sanpham;
	
	@ManyToOne
	@JoinColumn(name="Ma_NV")
	private NhanVien nhanvien;
	
	@Column(name="NgayLap_HD")
	private Date ngaylapHD;
	
	@Column(name="DonGia")
	
	private int donGia;
	@Column(name="SoLuong")
	private int soluong;
	
	@Column(name="ThanhTien")
	private int thanhTien;
	
	public HoaDon() {
		super();
	}

	public HoaDon(String maHD, KhachHang khachhang, SanPham sanpham, NhanVien nhanvien, Date ngaylapHD, int donGia,
			int soluong, int thanhTien) {
		super();
		this.maHD = maHD;
		this.khachhang = khachhang;
		this.sanpham = sanpham;
		this.nhanvien = nhanvien;
		this.ngaylapHD = ngaylapHD;
		this.donGia = donGia;
		this.soluong = soluong;
		this.thanhTien = thanhTien;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", khachhang=" + khachhang + ", sanpham=" + sanpham + ", nhanvien=" + nhanvien
				+ ", ngaylapHD=" + ngaylapHD + ", donGia=" + donGia + ", soluong=" + soluong + ", thanhTien="
				+ thanhTien + "]";
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public KhachHang getKhachhang() {
		return khachhang;
	}

	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}

	public SanPham getSanpham() {
		return sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Date getNgaylapHD() {
		return ngaylapHD;
	}

	public void setNgaylapHD(Date ngaylapHD) {
		this.ngaylapHD = ngaylapHD;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + donGia;
		result = prime * result + ((khachhang == null) ? 0 : khachhang.hashCode());
		result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
		result = prime * result + ((ngaylapHD == null) ? 0 : ngaylapHD.hashCode());
		result = prime * result + ((nhanvien == null) ? 0 : nhanvien.hashCode());
		result = prime * result + ((sanpham == null) ? 0 : sanpham.hashCode());
		result = prime * result + soluong;
		result = prime * result + thanhTien;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		if (donGia != other.donGia)
			return false;
		if (khachhang == null) {
			if (other.khachhang != null)
				return false;
		} else if (!khachhang.equals(other.khachhang))
			return false;
		if (maHD == null) {
			if (other.maHD != null)
				return false;
		} else if (!maHD.equals(other.maHD))
			return false;
		if (ngaylapHD == null) {
			if (other.ngaylapHD != null)
				return false;
		} else if (!ngaylapHD.equals(other.ngaylapHD))
			return false;
		if (nhanvien == null) {
			if (other.nhanvien != null)
				return false;
		} else if (!nhanvien.equals(other.nhanvien))
			return false;
		if (sanpham == null) {
			if (other.sanpham != null)
				return false;
		} else if (!sanpham.equals(other.sanpham))
			return false;
		if (soluong != other.soluong)
			return false;
		if (thanhTien != other.thanhTien)
			return false;
		return true;
	}
   
	
}
