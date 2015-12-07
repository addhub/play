package aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.transfer.model.UploadResult;
import global.AppConfig;
import play.Logger;
import play.libs.F;
import play.mvc.Http;
import scala.concurrent.Promise$;

import java.io.File;


public class S3Service {
    private static TransferManager tm;
    private static final String BUCKET_NAME = "addhub01";


    public S3Service() {
        final String accessKey = AppConfig.getString("aws.AWS_ACCESS");
        final String secretKey = AppConfig.getString("aws.AWS_SECRET");
        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        tm = new TransferManager(credentials);
    }


    public F.Promise<String> uploadAdImg(File file) {
        System.out.printf("Start to call upload method");
        final Upload upload = tm.upload(BUCKET_NAME, file.getName(), file);

        return asPromise(file.getName(), upload);
    }


    private static F.Promise<String> asPromise(final String filename, final Upload upload) {
        final scala.concurrent.Promise<String> scalaPromise = Promise$.MODULE$.apply();
        try {
            upload.waitForUploadResult();
            scalaPromise.success("yay");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//
        return F.Promise.wrap(scalaPromise.future());
    }

    private static String asString(UploadResult result) {
        return "UploadResult{bucketName=" + result.getBucketName() + ", key=" + result.getKey() + ", version=" + result.getVersionId() + ", ETag=" + result.getETag() + "}";
    }

}
