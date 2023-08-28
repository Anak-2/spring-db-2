package study.springtx.propagation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "users")
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    public Member(){
    }

    public Member(String username){
        this.username = username;
    }
}
