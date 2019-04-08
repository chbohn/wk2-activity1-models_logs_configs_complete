package wk2.activity1.models_logs_configs.resources;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import wk2.activity1.models_logs_configs.logger.ServiceLogger;
import wk2.activity1.models_logs_configs.models.GetProductRequestModel;
import wk2.activity1.models_logs_configs.models.GetProductResponseModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;

@Path("test")
public class TestPage {
    @Path("hello")
    @GET
    public Response helloWorld() {
        ServiceLogger.LOGGER.info("Hello world!");
        return Response.status(Status.OK).build();
    }

    @Path("product")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(String jsonText) {
        ServiceLogger.LOGGER.info("Getting product...");
        ObjectMapper mapper = new ObjectMapper();
        int num1;
        int num2;
        String message;
        int product;
        String resultJSON = "";

        try {
            num1 = mapper.readTree(jsonText).get("num1").asInt();
            num2 = mapper.readTree(jsonText).get("num2").asInt();
            message = mapper.readTree(jsonText).get("message").asText();
            ServiceLogger.LOGGER.info(message);
            product = num1 * num2;
            ServiceLogger.LOGGER.info(num1 + " * " + num2 + " = " + product);
            resultJSON = Integer.toString(product);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Status.OK).entity(resultJSON).build();
    }

    @Path("betterProduct")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBetterProduct(String jsonText) {
        ServiceLogger.LOGGER.info("Getting product...");
        ObjectMapper mapper = new ObjectMapper();
        GetProductRequestModel requestModel;
        GetProductResponseModel responseModel;

        try {
            requestModel = new GetProductRequestModel(
                    mapper.readTree(jsonText).get("num1").asInt(),
                    mapper.readTree(jsonText).get("num2").asInt(),
                    mapper.readTree(jsonText).get("message").asText()
            );
            ServiceLogger.LOGGER.info(requestModel.getMessage());
            int product = multiplyNumbers(requestModel);
            ServiceLogger.LOGGER.info(requestModel.getNum1() + " * " + requestModel.getNum2() + " = " + product);
            responseModel = new GetProductResponseModel(product, "Hello from the server.");
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Status.OK).entity(responseModel).build();
    }

    @Path("bestProduct")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestProduct(String jsonText) {
        ServiceLogger.LOGGER.info("Getting product...");
        ObjectMapper mapper = new ObjectMapper();
        GetProductRequestModel requestModel;
        GetProductResponseModel responseModel;

        try {
            requestModel = mapper.readValue(jsonText, GetProductRequestModel.class);
            ServiceLogger.LOGGER.info(requestModel.getMessage());
            responseModel = new GetProductResponseModel(
                    multiplyNumbers(requestModel),
                    "Hello from the server!"
            );
            ServiceLogger.LOGGER.info(requestModel.getNum1() + " * " + requestModel.getNum2() + " = " + responseModel.getProduct());
        } catch (IOException e) {
            e.printStackTrace();
            if (e instanceof JsonMappingException) {
                ServiceLogger.LOGGER.warning("Could not map JSON to POJO.");
            } else if (e instanceof JsonParseException) {
                ServiceLogger.LOGGER.warning("Could not Parse JSON.");
            } else {
                ServiceLogger.LOGGER.warning("IOException.");
            }
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Status.OK).entity(responseModel).build();
    }

    private int multiplyNumbers(GetProductRequestModel req) {
        return req.getNum1() * req.getNum2();
    }
}
