package Services;

import Beans.UploadBean;
import Beans.UserBean;
import Entities.Upload;
import Entities.UserEntity;
import Interceptor.Logged;
import Interfaces.UploadInterface;
import org.hibernate.exception.ConstraintViolationException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Stateless
public class UploadService extends DataRepository<Upload,Integer> implements UploadInterface {

    public UploadService(){super(Upload.class);}

    @Override
    public boolean addUpload(Upload m){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Upload>> constraintViolations = validator.validate(m);
        if(constraintViolations.size() > 0){
            Iterator<ConstraintViolation<Upload>> iterator = constraintViolations.iterator();
            while(iterator.hasNext()){
                ConstraintViolation<Upload> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName()+"."+cv.getPropertyPath() + " " +cv.getMessage());

                System.out.println(cv.getRootBeanClass().getSimpleName()+"."+cv.getPropertyPath() + " " +cv.getMessage());
            }
        }else{
        try{
        this.persist(m);}

        catch (Exception e){
            return false;
        }}
        return true;
    }
    @Override
    public List<UploadBean> getUploads(){
        List<Upload> entities = this.findAll();

        List<UploadBean> ls = new ArrayList<>();
        for (Upload entity : entities)
        {
            UploadBean uploadBean = new UploadBean();
            uploadBean.setEntity(entity);

            ls.add(uploadBean);
        }
        return ls;
    }

    @Override
    public List<Upload> getUploadsEntities(){
        return this.findAll();
    }

    @Override
    public Upload createUpload(InputStream is) {
        return this.createEntity(is);
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public Upload findUpload(BigDecimal id) {
//        return this.findById(id);
        String qlString =
                "select e from " + entityClass.getSimpleName() + " e where e.id = "+id.toString();
        List<Upload> userList = (List<Upload>)em.createQuery(qlString).getResultList();
        if(userList.size()==0){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public void deleteUpload(Upload upload) {
        remove(upload);
    }

    @Override
    public void updateUpload(Upload upload) {
        this.update(upload);
    }
}
