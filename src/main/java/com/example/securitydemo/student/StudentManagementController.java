package com.example.securitydemo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management")
public class StudentManagementController {

  private static final List<Student> STUDENTS =
      Arrays.asList(
          new Student(1, "Bob Parry"), new Student(2, "Aaron Lennon"), new Student(3, "Bill Bull"));

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
  public List<Student> getStudents() {
    return STUDENTS;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('student:write')")
  public void registerNewStudent(@RequestBody Student student) {
    System.out.println(student);
  }

  @DeleteMapping("/{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void deleteStudent(@PathVariable Integer studentId) {
    System.out.println(studentId);
  }

  @PutMapping("/{studentId}")
  @PreAuthorize("hasAuthority('student:write')")
  public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
    System.out.println(String.format("%s %s", student, studentId));
  }
}
