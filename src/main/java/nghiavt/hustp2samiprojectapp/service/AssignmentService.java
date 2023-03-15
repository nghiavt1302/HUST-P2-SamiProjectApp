package nghiavt.hustp2samiprojectapp.service;

public interface AssignmentService {
    String changeInstructor(String assignId, String instructorId);
    boolean checkDuplicate(Integer studentId, String instructorId);
}
