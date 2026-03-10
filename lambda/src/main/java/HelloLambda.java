
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

public class HelloLambda implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        context.getLogger().log("Input received: " + input);

        String name = input.getOrDefault("name", "Student");
        return "Hello " + name + ", welcome to your first Java AWS Lambda!";
    }
}
