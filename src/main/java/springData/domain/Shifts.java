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
    @Column(name="Shift_ID", nullable = false)
    int shiftId;

    @Column(name="Start_Time", unique = true, nullable = false)
    String startTime;

    @Column(name="End_Time", unique = true, nullable = false)
    String endTime;

    @Column(name="Overtime_Hours", unique = true, nullable = false)
    int overtimeHours;

    @Column(name="Bank_Holiday?", unique = true, nullable = false)
    boolean bankHoliday;

    @Column(name="Holiday", unique = true, nullable = false)
    boolean holiday;
    
    @ManyToOne
    @JoinColumn(name="user_Id",nullable=true)
    private User user;

	public int getShiftId() {
		return shiftid;
	}

	public void setShiftId(int shiftId) {
		this.shiftid = shiftId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getOvertimeHours() {
		return overtimeHours;
	}

	public void setOvertimeHours(int overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	public boolean isBankHoliday() {
		return bankHoliday;
	}

	public void setBankHoliday(boolean bankHoliday) {
		this.bankHoliday = bankHoliday;
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
