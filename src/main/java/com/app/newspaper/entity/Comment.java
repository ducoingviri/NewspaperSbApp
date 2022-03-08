package com.app.newspaper.entity;

import java.util.Set;
import java.sql.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String content;

    @Column
    private Date createdat;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

}