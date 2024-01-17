package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository //Data access layer
public interface StudentRepository 
        extends JpaRepository<Student, Long>
{
    //SELECT * FROM student WHERE email=?
    @Query("SELECT s FROM Student s WHERE s.email= ?1 ")
    Optional<Student> findStudentByEmail(String email);
    //@Query("UPDATE Student s set s.name = ?2, u.email = ?3 where u.id=?1")
    //void updateStudentInfo(Long studentId, String name, String email);
}
