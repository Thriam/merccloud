import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class CalculatorLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        try {
            // 1. Parse the JSON body from the POST request
            String body = request.getBody();
            Map<String, Object> inputData = objectMapper.readValue(body, Map.class);

            // 2. Extract values safely
            int a = Integer.parseInt(inputData.get("a").toString());
            int b = Integer.parseInt(inputData.get("b").toString());

            int sum = a + b;

            // 3. Build the successful response
            response.setStatusCode(200);
            response.setBody("{\"result\": " + sum + "}");

        } catch (Exception e) {
            response.setStatusCode(400);
            response.setBody("{\"error\": \"Invalid input format\"}");
        }

        return response;
    }
}
