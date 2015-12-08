package ext.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.s3.transfer.model.UploadResult;
import global.AppConfig;
import play.Logger;
import play.libs.F;
import scala.concurrent.Promise$;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;


public class S3Service {
    public static final String BUCKET_NAME = "addhubcornell";

    private static final String AMAZON_URL = "https://s3.amazonaws.com/";
    private static TransferManager tm;
    private static AmazonS3Client client = null;

    public S3Service() {
        final String accessKey = AppConfig.getString("aws.AWS_ACCESS");
        final String secretKey = AppConfig.getString("aws.AWS_SECRET");
        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        tm = new TransferManager(credentials);

        client = new AmazonS3Client();
    }


    public static String createFolder(String bucketName, String userFolder) {
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        // create empty content
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

        // create a PutObjectRequest passing the folder name suffixed by /
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
                userFolder , emptyContent, metadata);

        // send request to S3 to create folder
        client.putObject(putObjectRequest);
        String folderName = putObjectRequest.toString();
        return folderName;
    }

    public F.Promise<String> uploadAdImg(File file, String folderName) {
        System.out.printf("Start to call upload method");
        String targetFolder = BUCKET_NAME+"/"+folderName;
        final Upload upload = tm.upload(targetFolder, file.getName(), file);

        //final Upload upload = tm.upload(BUCKET_NAME, file.getName(), file);
        return asPromise(folderName,file.getName(), upload);
    }


    private static F.Promise<String> asPromise(final String folderName, final String filename, final Upload upload) {
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
                    scalaPromise.success(AMAZON_URL+uploadResult.getBucketName()+"/"+folderName+"/"+filename); //cannot call sucess multiple times

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            UploadResult uploadResult = upload.waitForUploadResult();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return F.Promise.wrap(scalaPromise.future());
    }

    //
    // private static String asString(UploadResult result) {
    //    return "UploadResult{bucketName=" + result.getBucketName() + ", key=" + result.getKey() + ", version=" + result.getVersionId() + ", ETag=" + result.getETag() + "}";
    //}
    //

}
