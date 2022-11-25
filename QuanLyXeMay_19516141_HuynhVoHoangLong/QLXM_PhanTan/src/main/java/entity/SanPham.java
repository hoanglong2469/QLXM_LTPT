package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author HP
*/
@Entity
@Table(name="Xe")

public class SanPham implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Ma_Xe")
	private String maXe;
	
	@Column(name="Ten_Xe")
	private String tenXe;
	
	@Column(name="Loai_Xe")
	private String loaiXe;
	
	@Column(name="Nam_SX")
	private int namSX;
	
	@Column(name="So_PK")
	private int soPK;
	
	@Column(name="So_Khung")
	private int soKhung;
	
	@Column(name="So_Suon")
	private int soSuon;
	
	@Column(name="Mau_Xe")
	private String mauXe;
	
	@Column(name="Gia_Xe")
	private int giaXe;
	
	
	public String getMaXe() {
		return maXe;
	}
	public void setMaXe(String maXe) {
		this.maXe = maXe;
	}
	public String getTenXe() {
		return tenXe;
	}
	public void setTenXe(String tenXe) {
		this.tenXe = tenXe;
	}
	public String getLoaiXe() {
		return loaiXe;
	}
	public void setLoaiXe(String loaiXe) {
		this.loaiXe = loaiXe;
	}
	public int getNamSX() {
		return namSX;
	}
	public void setNamSX(int namSX) {
		this.namSX = namSX;
	}
	public int getSoPK() {
		return soPK;
	}
	public void setSoPK(int soPK) {
		this.soPK = soPK;
	}
	public int getSoKhung() {
		return soKhung;
	}
	public void setSoKhung(int soKhung) {
		this.soKhung = soKhung;
	}
	public int getSoSuon() {
		return soSuon;
	}
	public void setSoSuon(int soSuon) {
		this.soSuon = soSuon;
	}
	public String getMauXe() {
		return mauXe;
	}
	public void setMauXe(String mauXe) {
		this.mauXe = mauXe;
	}
	public int getGiaXe() {
		return giaXe;
	}
	public void setGiaXe(int giaXe) {
		this.giaXe = giaXe;
	}
	
	
	public SanPham(String maXe, String tenXe, String loaiXe, int namSX, int soPK, int soKhung, int soSuon, String mauXe,
			int giaXe) {
		super();
		this.maXe = maXe;
		this.tenXe = tenXe;
		this.loaiXe = loaiXe;
		this.namSX = namSX;
		this.soPK = soPK;
		this.soKhung = soKhung;
		this.soSuon = soSuon;
		this.mauXe = mauXe;
		this.giaXe = giaXe;
	}
	public SanPham() {
		super();
	}
	public SanPham(String maXe) {
		super();
		this.maXe = maXe;
	}
	@Override
	public String toString() {
		return "SanPham [maXe=" + maXe + ", tenXe=" + tenXe + ", loaiXe=" + loaiXe + ", namSX=" + namSX + ", soPK=" + soPK
				+ ", soKhung=" + soKhung + ", soSuon=" + soSuon + ", mauXe=" + mauXe + ", giaXe=" + giaXe + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maXe == null) ? 0 : maXe.hashCode());
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
		SanPham other = (SanPham) obj;
		if (maXe == null) {
			if (other.maXe != null)
				return false;
		} else if (!maXe.equals(other.maXe))
			return false;
		return true;
	}

}
