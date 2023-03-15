package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.model.dataObject.ApplicationList;
import nghiavt.hustp2samiprojectapp.model.entity.Application;
import nghiavt.hustp2samiprojectapp.repository.ApplicationRepository;
import nghiavt.hustp2samiprojectapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class FunctionService {
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    StudentRepository studentRepository;
    public List<ApplicationList> getApplicationList(){
        List<ApplicationList> list = new ArrayList<>();
        List<Application> apps = applicationRepository.findAll();
        for (Application app : apps) {
            ApplicationList add = new ApplicationList();
            add.setAppId(app.getAppId());
            add.setStudentId(app.getStudentId());
            add.setOrient1(app.getOrient1());
            add.setOrient2(app.getOrient2());
            add.setOpt1(app.getOpt1());
            add.setOpt2(app.getOpt2());
            add.setOpt3(app.getOpt3());
            add.setCpa(studentRepository.findByStudentId(app.getStudentId()).get(0).getCpa());
            list.add(add);
        }
        Collections.sort(list, Comparator.comparing(ApplicationList::getCpa));
        ApplicationList middle = list.get(list.size()/2);
        List<ApplicationList> left = new ArrayList<>();
        List<ApplicationList> right = new ArrayList<>();
        for (ApplicationList a : list) {
            if (a.getCpa() < middle.getCpa()){
                left.add(a);
            }else {
                right.add(a);
            }
        }
        Collections.shuffle(left);
        Collections.shuffle(right);

        List<ApplicationList> result = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()){
            try {
                result.add(left.remove(0));
                result.add(right.remove(0));
            }catch (Exception e){
            }
        }
        return result;
    }
}
