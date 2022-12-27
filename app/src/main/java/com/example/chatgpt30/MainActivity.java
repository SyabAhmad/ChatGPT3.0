package com.example.chatgpt30;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

///import for openAIAPI
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.IOException;

interface OpenAIAPI {
    @FormUrlEncoded
    @POST("chat")
    Call<ChatResponse> complete(
            @Field("model") String model,
            @Field("message") String prompt,
            @Field("max_tokens") int maxTokens
            //@Field("temperature") double temperature,
            //@Field("top_p") double topP,
            //@Field("frequency_penalty") double frequencyPenalty,
            //@Field("presence_penalty") double presencePenalty
    );
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //// android app interface

        EditText query = (EditText) findViewById(R.id.editText);
        TextView result = (TextView) findViewById(R.id.result1);
        TextView queryshoww = (TextView) findViewById(R.id.queryshow);
        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (query.getText().toString().equals(""))
                {
                    result.setText("Please write something first");
                    query.setText("");
                } else
                {
                    queryshoww.setText(query.getText().toString());
                    result.setText("Server overload");
                    query.setText("");
                }
            }
        });

        /// open AI API

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/")
                //.baseUrl("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")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenAIAPI api = retrofit.create(OpenAIAPI.class);

        Call<ChatResponse> call = api.complete(
                "text-davinci-002",
                query.getText().toString(),
                10
               // 0.7,
               // 0.9,
               // 0.0,
              //  0.0
        );
        Response<ChatResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isSuccessful()) {
            ChatResponse chatResponse = response.body();
            String responseText = chatResponse.getResponse();
            result.setText(responseText);
        }
    }
}

//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