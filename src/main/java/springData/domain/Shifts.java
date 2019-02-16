package springData.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.*;

@Entity(name = "shifts")
public class Shifts {
    
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="Shift ID", nullable = false)
    int shiftid;

    @Column(name="Start Time", unique = true, nullable = false)
    String starttime;

    @Column(name="End Time", unique = true, nullable = false)
    String endtime;

    @Column(name="Overtime Hours", unique = true, nullable = false)
    int overtimehours;

    @Column(name="Bank Holiday?", unique = true, nullable = false)
    boolean bankholiday;

    @Column(name="Holiday", unique = true, nullable = false)
    boolean holiday;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable=true)
    private User user;

	public int getShiftid() {
		return shiftid;
	}

	public void setShiftid(int shiftid) {
		this.shiftid = shiftid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getOvertimehours() {
		return overtimehours;
	}

	public void setOvertimehours(int overtimehours) {
		this.overtimehours = overtimehours;
	}

	public boolean isBankholiday() {
		return bankholiday;
	}

	public void setBankholiday(boolean bankholiday) {
		this.bankholiday = bankholiday;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}
