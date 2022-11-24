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
@Table(name="KhachHang")

public class KhachHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Ma_KhachHang")
	private String maKH;
	@Column(name="Ten_KhachHang")
	private String tenKH;
	@Column(name="Diachi_KhachHang")
	private String diachiKH;
	@Column(name="SDT_KhachHang")
	private String sdtKH;
	
	
	
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getDiachiKH() {
		return diachiKH;
	}
	public void setDiachiKH(String diachiKH) {
		this.diachiKH = diachiKH;
	}
	public String getSdtKH() {
		return sdtKH;
	}
	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}
	
	
	public KhachHang(String maKH, String tenKH, String diachiKH, String sdtKH) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diachiKH = diachiKH;
		this.sdtKH = sdtKH;
	}
	public KhachHang() {
		super();
	}
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", diachiKH=" + diachiKH + ", sdtKH=" + sdtKH + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}
}
