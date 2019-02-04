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

@Entity(name = "shifts")
public class Shifts {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int shiftid;

    @Column(unique = true, nullable = false)
    String starttime;

    @Column(unique = true, nullable = false)
    String endtime;

    @Column(unique = true, nullable = false)
    int overtimehours;

    @Column(unique = true, nullable = false)
    boolean bankholiday;

    @Column(unique = true, nullable = false)
    boolean holiday;
}
