package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*
* @author HP
*/
@Entity
@Table(name = "Account")

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1394204450423726077L;
	/**
	 * 
	 */
	
	@Column(name = "Loai_Account")
	private String Loai_Account;
	@Id
	@Column(name = "Username")
	private String Username;
	@Column(name = "Pass")
	private String Pass;
	
	@ManyToOne
	@JoinColumn(name="Ma_NV")
	private NhanVien nhanvien;
	
	public Account() {
		
	}

	public Account(String loai_Account, String username, String pass, NhanVien nhanvien) {
		super();
		Loai_Account = loai_Account;
		Username = username;
		Pass = pass;
		this.nhanvien = nhanvien;
	}

	public String getLoai_Account() {
		return Loai_Account;
	}

	public void setLoai_Account(String loai_Account) {
		Loai_Account = loai_Account;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Account [Loai_Account=" + Loai_Account + ", Username=" + Username + ", Pass=" + Pass + ", nhanvien="
				+ nhanvien + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Loai_Account, Pass, Username, nhanvien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(Loai_Account, other.Loai_Account) && Objects.equals(Pass, other.Pass)
				&& Objects.equals(Username, other.Username) && Objects.equals(nhanvien, other.nhanvien);
	}
	
	

}
