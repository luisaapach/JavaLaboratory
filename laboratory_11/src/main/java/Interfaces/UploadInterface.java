package Interfaces;

import Beans.UploadBean;
import Entities.Upload;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public interface UploadInterface {
    public boolean addUpload(Upload m);
    public List<UploadBean> getUploads();
    public Upload createUpload(InputStream is);
    public Upload findUpload(BigDecimal id);
    public void deleteUpload(Upload upload);
    public void updateUpload(Upload upload);
    public List<Upload> getUploadsEntities();
}
