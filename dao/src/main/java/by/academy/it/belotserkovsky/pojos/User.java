package by.academy.it.belotserkovsky.pojos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Kostya on 08.04.2016.
 */

@Entity
public class User implements Serializable{
    private Long uid;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private UserContacts userContacts;
    private Set<Bid> bids;

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUid() {
        return uid;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    @Column
    public String getSecondName() {
        return secondName;
    }

    @Column
    public String getLogin() {
        return login;
    }

    @Column
    public String getPassword() {
        return password;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public UserContacts getUserContacts() {
        return userContacts;
    }

    @OneToMany(mappedBy = "user")
    public Set<Bid> getBids() {
        return bids;
    }


    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserContacts(UserContacts userContacts) {
        this.userContacts = userContacts;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
