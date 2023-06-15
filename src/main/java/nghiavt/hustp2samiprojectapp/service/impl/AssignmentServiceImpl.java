package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.model.entity.Assignment;
import nghiavt.hustp2samiprojectapp.model.testing.TeacherAssignments;
import nghiavt.hustp2samiprojectapp.repository.AssignmentRepository;
import nghiavt.hustp2samiprojectapp.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public String acceptAssign(String asgId, AssignStatusEnum status) {
        List<Assignment> assignments = assignmentRepository.findByAssignId(asgId);
        if (!assignments.isEmpty()){
            Assignment add = assignments.get(0);
            add.setStatus(AssignStatusEnum.ACCEPTED);
            assignmentRepository.save(add);
        }
        return "Success!";
    }

    public Collection<AssignmentRepository.teacherAssign> getTeacherAssignmentRp(String email){
        return assignmentRepository.getTeacherAsgm1(email);
    }

    public Collection<TeacherAssignments> mapToTeacherAsgm(String email){
        Collection<AssignmentRepository.teacherAssign> collection = getTeacherAssignmentRp(email);
        Collection<TeacherAssignments> res = new ArrayList<>();
        for (AssignmentRepository.teacherAssign t : collection) {
            res.add(new TeacherAssignments(t.getAssign_id(), t.getInstructor_id(), t.getProject_type(),
                                        t.getProject_id(), t.getStatus(), t.getStudent_id(), t.getTerm()));
        }
        return res;
    }
}
