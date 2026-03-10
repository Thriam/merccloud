
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class S3Handler implements RequestHandler<S3Event, String> {
    @Override
    public String handleRequest(S3Event s3event, Context context) {
        // Grab the file name from the event
        String fileName = s3event.getRecords().get(0).getS3().getObject().getKey();

        System.out.println("Success! You uploaded: " + fileName);
        return "Processed " + fileName;
    }
}