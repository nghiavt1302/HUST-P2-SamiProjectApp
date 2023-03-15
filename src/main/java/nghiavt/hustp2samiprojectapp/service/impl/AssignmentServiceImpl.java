package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.model.entity.Assignment;
import nghiavt.hustp2samiprojectapp.repository.AssignmentRepository;
import nghiavt.hustp2samiprojectapp.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public String changeInstructor(String assignId, String instructorId) {
        Assignment change = assignmentRepository.findByAssignId(assignId).get(0);
        change.setInstructorId(instructorId);
        assignmentRepository.save(change);
        return "Assignment " + assignId + " instructor changed: " + instructorId;
    }

    @Override
    public boolean checkDuplicate(Integer studentId, String instructorId) {
        List<Assignment> check = assignmentRepository.findByStudentId(studentId);
        if (!check.isEmpty()){
            for (Assignment a : check) {
                if (a.getInstructorId()!=null){
                    if (a.getInstructorId().equals(instructorId)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
