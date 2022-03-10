package com.intellicar.dummylafproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.intellicar.blesdk.LAFM;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONException;
import org.json.JSONObject;


import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityJava extends AppCompatActivity {


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    LAFM lafm;
//    String Token = "";
//    String privateKey = "18C49D7E68B243A87BC27BA983A98D6A90E4BE93D9504D5B258B93673C25585A";
    byte[] privateKey;
    byte[] publicKey;

    String TAG = "MainActivityJava";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        AsymmetricCipherKeyPair keyPairX25519 = generateX25519KeyPair();
        privateKey = ((X25519PrivateKeyParameters)keyPairX25519.getPrivate()).getEncoded();
        publicKey = ((X25519PublicKeyParameters)keyPairX25519.getPublic()).getEncoded();

        lafm = LAFM.getInstance(this);

        lafm.callbackHandler(new Function1<JSONObject, Unit>() {
            @Override
            public Unit invoke(JSONObject json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView debugText = findViewById(R.id.debugMsg);
                        debugText.append(json.toString());
                        debugText.append("\n\n");
                    }
                });
                return Unit.INSTANCE;
            }
        });


        findViewById(R.id.getTokenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText deviceNameEdt = findViewById(R.id.deviceNameEdt);
                ArrayList<Integer> modulesArr = new ArrayList<>();
                modulesArr.add(1);
                modulesArr.add(2);
                modulesArr.add(3);
                modulesArr.add(4);
                modulesArr.add(5);
                modulesArr.add(6);
                modulesArr.add(12);
                GetTokenRequest getTokenRequest = new GetTokenRequest(
                        deviceNameEdt.getText().toString(),
                        System.currentTimeMillis() - (1000 * 60 * 60 * 24),
                        System.currentTimeMillis() + (365L * 1000 * 60 * 60 * 24),
                        modulesArr,
                        LittleEndianUtils.printHexArray(publicKey)
                );
                TextView debugText = findViewById(R.id.debugMsg);
                debugText.setText("Generating Token");
                debugText.append("\n");
                Call<GetTokenResponse> call = RetrofitClient.getInstance().getMyApi().getLafToken(getTokenRequest);
                call.enqueue(new Callback<GetTokenResponse>() {
                    @Override
                    public void onResponse(Call<GetTokenResponse> call, Response<GetTokenResponse> response) {

                        debugText.append("TOKEN GENERATED");
                        debugText.append("\n");
                        findViewById(R.id.execDeviceInfo).setEnabled(true);
                        findViewById(R.id.clearLog).setEnabled(true);
                        findViewById(R.id.execGps).setEnabled(true);
                        findViewById(R.id.execGsm).setEnabled(true);
                        findViewById(R.id.execAcc).setEnabled(true);
                        findViewById(R.id.execIO).setEnabled(true);
                        findViewById(R.id.execCan).setEnabled(true);
                        findViewById(R.id.disconnect).setEnabled(true);


                        TokenData tokenData = response.body().getData();
                        debugText.append(tokenData.getToken());
                        debugText.append("\n\n");
                        try {
                            setToken(new JSONObject(new Gson().toJson(tokenData)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetTokenResponse> call, Throwable t) {

                    }
                });
            }
        });

        findViewById(R.id.execDeviceInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","get_device_info");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.clearLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView debug = findViewById(R.id.debugMsg);
                debug.setText("");
            }
        });
        findViewById(R.id.execGps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","get_gps");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.execGsm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","get_gsm");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.execAcc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","get_acc");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.execIO).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","get_device_io");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.execCan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","get_can");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.disconnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject command = new JSONObject();
                    command.accumulate("action","disconnect");
                    lafm.exec(command);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void setToken(JSONObject jsonToken){
        try {
//            JSONObject jsonObject = new JSONObject();
////        jsonObject.accumulate("device_name","3B0D2AE9F56B7A34")
//            jsonObject.accumulate("device_name", "0A605F136BC0F433");
//            jsonObject.accumulate("device_id", "0A605F136BC0F433");//BD31A09CE51609FA
//            jsonObject.accumulate("token", Token);
            lafm.setToken(jsonToken, privateKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    AsymmetricCipherKeyPair generateX25519KeyPair(){
        try {
            SecureRandom secureRandom = new SecureRandom();
            X25519KeyPairGenerator keyPairGenerator = new X25519KeyPairGenerator();
            keyPairGenerator.init(new X25519KeyGenerationParameters(secureRandom));
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            Log.e(TAG, "Exception while generating keypair", e);
        }
        return null;
    }
}
