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
public class UserAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int userid;

    @Column(unique = true, nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String fname;

    @Column(unique = true, nullable = false)
    String lname;

    @Column(unique = true, nullable = false)
    int shiftid;
}
