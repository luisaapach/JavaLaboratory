package Decorators;

import Beans.UploadBean;
import Entities.Upload;
import Interfaces.UploadInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Decorator
public abstract class TimeFrameValidationDecorator implements UploadInterface {
    @Inject
    @Delegate
    @Any
    UploadInterface uploadInterface;

    public static final String registrationStartDate = "01/01/2021 00:00";
    public static final String registrationEndDate = "15/01/2021 00:00";

    public boolean addUpload(Upload m) {
        try {
            Date start=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(registrationStartDate);
            Date end=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(registrationEndDate);
            Date currentDate = new Date(System.currentTimeMillis());
            if(currentDate.after(start) && currentDate.before(end)) {
                return uploadInterface.addUpload(m);
            }
            return false;

        } catch (ParseException e) {
            return false;
        }
    }

    public abstract List<UploadBean> getUploads();
}
