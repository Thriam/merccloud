
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Map;

public class CalculatorLambda implements RequestHandler<Map<String, Object>, String> {

    @Override
    public String handleRequest(Map<String, Object> input, Context context) {

        int a = (int) input.get("a");
        int b = (int) input.get("b");

        int sum = a + b;

        return "Sum is: " + sum;
    }
}