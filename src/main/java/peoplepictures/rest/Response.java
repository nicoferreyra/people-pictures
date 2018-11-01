package peoplepictures.rest;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private final Integer httpStatus;
    private final String message;
    private final Iterable data;

    private Response(Response.ResponseBuilder responseBuilder) {
        this.httpStatus = responseBuilder.httpStatus;
        this.message = responseBuilder.message;
        this.data = responseBuilder.data;
    }

    public static Response.ResponseBuilder builder() {
        return new Response.ResponseBuilder();
    }

    public Map<String, Object> asMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("http_status", httpStatus);
        hashMap.put("message", message);
        hashMap.put("data", data);
        return hashMap;
    }

    public static final class ResponseBuilder {
        private Integer httpStatus;
        private String message;
        private Iterable data;

        public ResponseBuilder() {
        }

        public Response.ResponseBuilder httpStatus(Integer httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Response.ResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Response.ResponseBuilder data(Iterable data) {
            this.data = data;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

}
