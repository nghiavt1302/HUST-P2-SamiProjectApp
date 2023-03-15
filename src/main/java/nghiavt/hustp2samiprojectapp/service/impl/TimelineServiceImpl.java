package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.model.entity.Timeline;
import nghiavt.hustp2samiprojectapp.repository.TimelineRepository;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.ParametersRepo;
import nghiavt.hustp2samiprojectapp.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class TimelineServiceImpl implements TimelineService {
    @Autowired
    TimelineRepository timelineRepository;
    @Autowired
    ParametersRepo parametersRepo;
    @Override
    public String addEvent(String event, Timestamp expire) {
        String term = parametersRepo.findByCurrent(true).get(0).getTerm();
        Random ran = new Random();
        String index = String.valueOf(ran.nextInt(100));
        Timeline add = new Timeline(term+index, event, expire);
        timelineRepository.save(add);
        return "Added event " + event + " date: " + expire;
    }
}
