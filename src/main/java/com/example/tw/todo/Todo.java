package com.example.tw.todo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "title" , unique = true, nullable = false)
    private String title;

    @Column(name = "description", length = 100, nullable = true)
    private String description;

    @Column(name = "isCompleted", nullable = true)
    private Boolean isCompleted;

    @CreationTimestamp
    @Column(updatable = false)
    protected Date createdAt;

    @UpdateTimestamp
    protected Date updatedAt;
}
