package aws;

import play.libs.F;

import java.io.File;

import static org.junit.Assert.*;

public class S3ServiceTest {
    static S3Service s3service;

    @org.junit.Test
    public void testUploadAdImg() throws Exception {
        s3service = new S3Service();

        final F.Promise<String> promise = s3service.uploadAdImg(new File(this.getClass().getResource("/exercise1.jpg").getFile()));

        String result = promise.get((50000));

        System.out.println(result);


    }
}