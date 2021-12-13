package com.codebuffer.spring.data.jpa.tutorial.repository;

import com.codebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.codebuffer.spring.data.jpa.tutorial.entity.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@DataJpaTest generally we use this, it doesn't impact anything on DB so once tests are completed the data will be flashed out
// But here i want to impact the DB
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    // without embedded
//    @Test
//    public void saveStudent() {
//        StudentEntity student = StudentEntity.builder()
//                .emailId("sonu@gmail.com")
//                .firstName("Sonu")
//                .lastName("Kumar")
//                .guardianName("Saurabh")
//                .guardianEmail("saurabh@gmail.com")
//                .guardianMobile("8788878778")
//                .build();
//
//        studentRepository.save(student);
//    }

    @Test
    public void printAllStudent(){
        List<StudentEntity> students = studentRepository.findAll();
        System.out.println("Student List ::::: " +students);
    }

    @Test
    public void saveStudentWithEmbeddedGurdian() {
        Guardian guardian = Guardian.builder()
                .name("Mahi")
                .email("mahi@gmail.com")
                .mobile("9999999999")
                .build();
        StudentEntity student = StudentEntity.builder()
                .emailId("sonu222@gmail.com")
                .firstName("Sonu")
                .lastName("Kumar")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentByFirstName(){
        List<StudentEntity> students = studentRepository.findByFirstName("Sonu");
        System.out.println("Student List ::::: " +students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<StudentEntity> students = studentRepository.findByFirstNameContaining("So");
        System.out.println("Student List ::::: " +students);
    }

    @Test
    public void printStudentByGuardianName(){
        List<StudentEntity> students = studentRepository.findByGuardianName("Mahi");
        System.out.println("Student List with Guardian name ::::: " +students);
    }

    @Test
    public void printStudentByEmailId(){
        StudentEntity students = studentRepository.getStudentByEmailAddress("mahi@gmail.com");
        System.out.println("Student List with Guardian name ::::: " +students);
    }

    @Test
    public void printStudentFirstNameByEmailAddress() {
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("sonu@gmail.com" );
        System.out.println("firstName = " + firstName);
    }

    @Test
    public void printStudentByEmailAddressNativeQuery() {
        StudentEntity student = studentRepository.getStudentByEmailAddressNativeQuery("sonu@gmail.com" );
        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddressNativeQueryNamedParam() {
        StudentEntity student = studentRepository.getStudentByEmailAddressNativeQueryNamedParam("sonu@gmail.com" );
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentByEmail() {
        int updated = studentRepository.updateStudentNameByEmailId("sonu kr", "sonu@gmail.com" );
        System.out.println("updated = " + updated);
    }


}
