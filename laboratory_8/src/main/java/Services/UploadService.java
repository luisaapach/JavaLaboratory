package Services;

import Beans.UploadBean;
import Beans.UserBean;
import Entities.Upload;
import Entities.UserEntity;
import Interceptor.Logged;
import Interfaces.UploadInterface;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UploadService extends DataRepository<Upload,Integer> implements UploadInterface {

    public UploadService(){super(Upload.class);}

    @Override
    public boolean addUpload(Upload m){
        try{
        this.persist(m);}
        catch (Exception e){
            return false;
        }
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

}
