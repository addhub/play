package ext.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.transfer.model.UploadResult;
import global.AppConfig;
import play.Logger;
import play.libs.F;
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
        upload.addProgressListener((ProgressEvent progressEvent) -> {
            if (progressEvent.getEventType() == ProgressEventType.TRANSFER_CANCELED_EVENT) {
                scalaPromise.failure(new RuntimeException("canceled " + filename));
            } else if (progressEvent.getEventType() == ProgressEventType.TRANSFER_FAILED_EVENT) {
                scalaPromise.failure(new RuntimeException("failed " + filename));
            } else if (progressEvent.getEventType() == ProgressEventType.TRANSFER_COMPLETED_EVENT) {
                Logger.info("done " + filename);
                try {
                    UploadResult uploadResult = upload.waitForUploadResult();
                    scalaPromise.success(asString(uploadResult));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            upload.waitForUploadResult();
            scalaPromise.success("uploaded to S3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return F.Promise.wrap(scalaPromise.future());
    }

    private static String asString(UploadResult result) {
        return "UploadResult{bucketName=" + result.getBucketName() + ", key=" + result.getKey() + ", version=" + result.getVersionId() + ", ETag=" + result.getETag() + "}";
    }

}
