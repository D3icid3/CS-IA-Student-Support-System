package MVC.DB;

import MVC.Model.Subject;
import MVC.Utility.DbException;


public interface SubjectDbInterface {

    /**
     * for future use
     * @param subject
     * @return
     * @throws DbException
     */

    Subject addSubject(Subject subject) throws DbException;
}
