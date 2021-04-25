package nl.themagrid.helper.exceptions;


import javax.ws.rs.core.Response;

public class ApiException extends RuntimeException {
    public Response.Status httpStatus;
    public String message;

    public ApiException(Response.Status httpStatus) {
        super();
        this.httpStatus = httpStatus;
    }

    public ApiException(Response.Status httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public static class Builder{
        public Response.Status httpStatus;
        public String message;

        public Builder status(Response.Status status){
            this.httpStatus = status;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public ApiException build(){
            if(httpStatus == null){
                httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
            }
            return new ApiException(httpStatus, message);
        }
    }
}
