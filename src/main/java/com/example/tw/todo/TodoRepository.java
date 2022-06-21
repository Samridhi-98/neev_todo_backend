//package com.example.tw.todo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface TodoRepository extends JpaRepository<Todo,Long> {
//
//    @Query("select name from Todo t where t.name?=1")
//    Optional<Todo> findTaskByName(String name);
//}
