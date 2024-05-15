package ma.emsi.javaproject.entities;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UploadableField {
    String mapping();
    String fileNameProperty();
}
