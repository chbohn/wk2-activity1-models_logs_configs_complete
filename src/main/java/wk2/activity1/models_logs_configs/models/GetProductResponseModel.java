package wk2.activity1.models_logs_configs.models;

public class GetProductResponseModel {
    private int product;
    private String message;

    public GetProductResponseModel() { }

    public GetProductResponseModel(int product, String message) {
        this.product = product;
        this.message = message;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
