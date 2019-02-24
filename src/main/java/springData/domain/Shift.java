package springData.domain;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author mohde
 */
@Entity
public class Shift {

    @Column(unique = true, updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shiftId;

    @Basic
    private LocalDateTime startTime;

    @Basic
    private LocalDateTime endTime;

    @Basic
    private int overtimeHours;

    @Basic
    private boolean bankHoliday;

    @Basic
    private boolean holiday;

    public Shift(int shiftId, LocalDateTime startTime, LocalDateTime endTime, int overtimeHours, boolean bankHoliday,
            boolean holiday) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.overtimeHours = overtimeHours;
        this.bankHoliday = bankHoliday;
        this.holiday = holiday;
    }

    public Shift() {
    }

    public int getShiftId() {
        return this.shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getOvertimeHours() {
        return this.overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public boolean isBankHoliday() {
        return this.bankHoliday;
    }

    public void setBankHoliday(boolean bankHoliday) {
        this.bankHoliday = bankHoliday;
    }

    public boolean isHoliday() {
        return this.holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

}