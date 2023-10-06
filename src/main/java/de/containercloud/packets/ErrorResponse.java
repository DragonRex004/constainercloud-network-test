package de.containercloud.packets;

public class ErrorResponse {
    private String res;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public ErrorResponse(String res) {
        this.res = res;
    }

    public ErrorResponse() {

    }
}
