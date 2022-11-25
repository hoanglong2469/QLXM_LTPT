package entity;

import java.io.Serializable;
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
@Table(name = "NhanVien")

public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8430079688285256068L;
	/**
	 * 
	 */
	
	@Id
	@Column(name="Ma_NV")
	private String maNV;
	
	@Column(name="Ten_NV")
	private String tenNV;
	
	@Column(name="SDT_NV")
	private String sdtNV;
	
	@Column(name="Diachi_NV")
	private String diachiNV;
	
	@Column(name="Email_NV")
	private String emailNV;
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSdtNV() {
		return sdtNV;
	}
	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}
	public String getDiachiNV() {
		return diachiNV;
	}
	public void setDiachiNV(String diachiNV) {
		this.diachiNV = diachiNV;
	}
	public String getEmailNV() {
		return emailNV;
	}
	public void setEmailNV(String emailNV) {
		this.emailNV = emailNV;
	}

	
	
	public NhanVien(String maNV, String tenNV, String sdtNV, String diachiNV, String emailNV) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdtNV = sdtNV;
		this.diachiNV = diachiNV;
		this.emailNV = emailNV;
	}
	public NhanVien() {
		super();
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", sdtNV=" + sdtNV + ", diachiNV=" + diachiNV + ", emailNV="
				+ emailNV + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	}
