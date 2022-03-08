package com.app.newspaper.entity;

import java.util.Set;
import java.sql.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String brief;

    @Column
    private String content;

    @Column
    private Date createdat;

    @Column
    private Boolean ispublished;

}