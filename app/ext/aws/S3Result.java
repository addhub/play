package ext.aws;

/**
 * Created by sasinda on 12/8/15.
 */
public class S3Result {
    private boolean success;
    private String uploadUrl;

    public S3Result() {
        this.setSuccess(false);
    }

    public S3Result(String uploadUrl) {
        this.setSuccess(true);
        this.setUploadUrl(uploadUrl);
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
}
