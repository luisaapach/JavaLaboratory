package Interfaces;

import Beans.UploadBean;
import Entities.Upload;

import java.util.List;

public interface UploadInterface {
    public boolean addUpload(Upload m);
    public List<UploadBean> getUploads();
}
