package nghiavt.hustp2samiprojectapp.service;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;

public interface AssignmentService {
    String changeInstructor(String assignId, String instructorId);
    boolean checkDuplicate(Integer studentId, String instructorId);
    String acceptAssign(String asgId, AssignStatusEnum status);
}
