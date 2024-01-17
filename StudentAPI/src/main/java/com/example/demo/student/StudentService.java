package com.example.demo.student;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service //This makes the StudentService class a bean, or a class that needs to be instantiated before startup. We need it active so we can use it's methods from the controller. also @component works
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

       if(studentByEmail.isPresent()){
        throw new IllegalStateException("email taken");
       }else{
        studentRepository.save(student);
       }
    }

    public void deleteStudent(Long studentId) {
       boolean exists = studentRepository.existsById(studentId);
       if(!exists){
        throw new IllegalStateException("student with id : " + studentId + " does not exist");
       }
       studentRepository.deleteById(studentId);

    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(() 
        -> new IllegalStateException("student with id : " + studentId + " does not exist"));
      
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
            student.setName(name);
        
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }else
            student.setEmail(email);
        }
    }

}
