package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.model.dataObject.Parameters;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.ParametersRepo;
import nghiavt.hustp2samiprojectapp.service.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametersServiceImpl implements ParametersService {
    @Autowired
    ParametersRepo parametersRepo;
    @Override
    public String getCurTerm() {
        return parametersRepo.findByCurrent(true).get(0).getTerm();
    }

    @Override
    public String changeParam(String term, float bmtcb, float bmtt, float bmtud) {
        Parameters thisParam = parametersRepo.findByTerm(term).get(0);
        Parameters param = new Parameters();
        param.setTerm(thisParam.getTerm());
        param.setBmtcbParam(bmtcb);
        param.setBmttParam(bmtt);
        param.setBmtudParam(bmtud);
        param.setCurrent(thisParam.getCurrent());
        parametersRepo.save(param);
        return "Parameters changed: " + param;
    }

    @Override
    public List<Parameters> findByCurrent(boolean current) {
        return parametersRepo.findByCurrent(current);
    }
}
