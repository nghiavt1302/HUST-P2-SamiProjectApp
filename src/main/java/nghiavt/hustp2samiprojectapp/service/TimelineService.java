package nghiavt.hustp2samiprojectapp.service;

import java.sql.Timestamp;

public interface TimelineService {
    String addEvent(String event, Timestamp expire);
}
