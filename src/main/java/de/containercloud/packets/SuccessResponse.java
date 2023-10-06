package de.containercloud.packets;

public class SuccessResponse {

    private String res;

    public SuccessResponse(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }

    public SuccessResponse() {}
}
