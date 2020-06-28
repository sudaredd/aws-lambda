package app.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;


public class S3Handler implements RequestHandler<S3Event, String> {

    public String handleRequest(S3Event event, Context context) {
        LambdaLogger logger = context.getLogger();
        String response = new String("200 OK");
        S3EventNotification.S3EventNotificationRecord record = event.getRecords().get(0);
        //log region bucket and object
        logger.log("region is :" + record.getAwsRegion());
        logger.log("bucket is :" + record.getS3().getBucket().getName());
        logger.log("filename key is :" + record.getS3().getObject().getKey());
        logger.log("filename etag is :" + record.getS3().getObject().geteTag());
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + System.getenv());
        logger.log("CONTEXT: " + context);
        // process event
        logger.log("EVENT: " + event);
        logger.log("EVENT TYPE: " + event.getClass().toString());
        return response;
    }
}
