package com.example.securitydemo.student;

import com.example.securitydemo.student.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  private static final List<Student> STUDENTS =
      Arrays.asList(
          new Student(1, "Bob Parry"), new Student(2, "Aaron Lennon"), new Student(3, "Bill Bull"));

  @GetMapping("/{studentId}")
  public Student getStudent(@PathVariable Integer studentId) {
    System.out.println("I got here >>>>>>>>>>>>>>>>>>>>>>");
    return STUDENTS
        .stream()
        .filter(s -> s.getStudentId().equals(studentId))
        .findFirst()
        .orElseThrow(
            () ->
                new IllegalStateException(
                    String.format("Student with id %s not found", studentId)));
  }
}
