package nghiavt.hustp2samiprojectapp.service;

import nghiavt.hustp2samiprojectapp.model.dataObject.Parameters;

import java.util.List;

public interface ParametersService {
    String getCurTerm();
    String changeParam(String term, float bmtcb, float bmtt, float bmtud);
    List<Parameters> findByCurrent(boolean current);
}
