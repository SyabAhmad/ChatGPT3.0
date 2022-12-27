package com.example.chatgpt30;

import com.google.gson.annotations.SerializedName;

public class ChatResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("model")
    private String model;
    @SerializedName("prompt")
    private String prompt;
    @SerializedName("response")
    private String response;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

