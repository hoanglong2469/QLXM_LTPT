package entity;

import java.io.Serializable;

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

	public Account() {
		
	}

	public Account(String loai_Account, String username, String pass) {
		super();
		Loai_Account = loai_Account;
		Username = username;
		Pass = pass;
	}
	@Override
	public String toString() {
		return "Account [Loai_Account=" + Loai_Account + ", Username=" + Username + ", Pass=" + Pass + "]";
	}
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((Username == null) ? 0 : Username.hashCode());
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
	        Account other = (Account) obj;
	        if (Username == null) {
	            if (other.Username != null)
	                return false;
	        } else if (!Username.equals(other.Username))
	            return false;
	        return true;
	    }

}
