package ext.aws;

import com.amazonaws.services.s3.transfer.model.UploadResult;

/**
 * Created by sasinda on 12/8/15.
 */
public class S3Result {
    private boolean success;
    private String uploadUrl;
    UploadResult uploadResult;

    public S3Result() {
        this.setSuccess(false);
    }

    public S3Result(String uploadUrl, UploadResult uploadReuslt) {
        this.setSuccess(true);
        this.setUploadUrl(uploadUrl);
        this.uploadResult=uploadResult;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public UploadResult getUploadResult() {
        return uploadResult;
    }

    public void setUploadResult(UploadResult uploadResult) {
        this.uploadResult = uploadResult;
    }
}
