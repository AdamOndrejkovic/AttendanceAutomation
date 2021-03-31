package bll;

import dal.IClassRepository;
import dal.db.DBClassRepository;

public class ClassManager {
    private IClassRepository classRepository;

    public ClassManager() {
        classRepository = new DBClassRepository();
    }
}
