package wk2.activity1.models_logs_configs.models;

public class GetProductRequestModel {
    private int num1;
    private int num2;
    private String message;

    public GetProductRequestModel() { }

    public GetProductRequestModel(int num1, int num2, String message) {
        this.num1 = num1;
        this.num2 = num2;
        this.message = message;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
