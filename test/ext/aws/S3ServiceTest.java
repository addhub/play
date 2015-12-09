package ext.aws;

import org.junit.Ignore;
import play.libs.F;

import java.io.File;

import static org.junit.Assert.*;
@Ignore
public class S3ServiceTest {
    static S3Service s3service;

    @org.junit.Test
    public void testUploadAdImg() throws Exception {
        s3service = new S3Service();

        final F.Promise<S3Result> promise = s3service.uploadAdImg(new File(this.getClass().getResource("/exercise1.jpg").getFile()),"userjacalpha");

        S3Result result = promise.get((50000));

        System.out.println(result.getUploadUrl());


    }
}