package study.springtx.txsynch.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Message {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    Member member;

    String message;

}
