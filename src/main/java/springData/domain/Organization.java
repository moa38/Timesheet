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

@Entity(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int organisationid;

    @Column(unique = true, nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String address;

    @Column(unique = true, nullable = false)
    String contactnumber;
}