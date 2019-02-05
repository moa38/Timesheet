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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int userid;

    @Column(unique = true, nullable = false)
    String username;

    @Column(unique = true, nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String fname;

    @Column(unique = true, nullable = false)
    String lname;

    @Column(unique = true, nullable = false)
    int shiftid;


    public User(){}
    public User(String username, String password, String fname, String lname){
        this.setUserName(username);
        this.setPassword(password);
        this.setFname(fname);
        this.setLname(lname);
    }
    public int getUserId(){
        return userid;
    }
    public void setUserId(int userid){
         this.userid=userid;
    }  
    public String getUserName(){
        return username;
    }
    public void setUserName(String username){
        this.username=username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname=fname;
    }
    public String getLname(){
        return lname;
    }
    public void setlname(String lname){
        this.lname=lname;
    }
    public int getShiftId(){
        return shiftid;
    }
    public void setShiftId(int shiftid){
         this.shiftid=shiftid;
    }  




}