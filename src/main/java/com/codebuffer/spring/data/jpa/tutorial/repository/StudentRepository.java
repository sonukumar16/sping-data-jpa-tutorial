package com.codebuffer.spring.data.jpa.tutorial.repository;

import com.codebuffer.spring.data.jpa.tutorial.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findByFirstName(String firstName);
    List<StudentEntity> findByFirstNameContaining(String name);
    List<StudentEntity> findByLastNameNotNull();
    List<StudentEntity> findByGuardianName(String guardianName);

    StudentEntity findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL : it works based on Classes and attribute not on table and column name
    @Query("select s from StudentEntity s where s.emailId=?1")
    StudentEntity getStudentByEmailAddress(String emailId);

    @Query("select s.firstName from StudentEntity s where s.emailId=?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    // Native Query
    @Query(value = "SELECT * from tbl_student s where s.email_address=?1", nativeQuery = true)
    StudentEntity getStudentByEmailAddressNativeQuery(String emailId);

    // Native Query with Named Param (it helps when we have to pass more than one parameters in query)
    @Query(value = "SELECT * from tbl_student s where s.email_address=:emailId", nativeQuery = true)
    StudentEntity getStudentByEmailAddressNativeQueryNamedParam(@Param("emailId") String emailId);

    @Modifying // use whenever want to update the DB
    @Transactional // To commit the changes to DB , Generally use this at service layer here i have defined because i'm checking only repository layer
    @Query(value = "update tbl_student set first_name=?1 where email_address=?2", nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);

}
