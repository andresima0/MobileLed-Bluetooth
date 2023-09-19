package br.com.local.mobileled_bt_v4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    BluetoothConnectionManager bluetoothConnectionManager;
    LEDControlManager ledControlManager;
    Button buttonConnect;
    Button buttonLed01;
    EditText macAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        macAddressEditText = findViewById(R.id.macAddressEditText);

        bluetoothConnectionManager = new BluetoothConnectionManager(this, macAddressEditText);
        ledControlManager = new LEDControlManager(this);

        buttonConnect = findViewById(R.id.buttonConnect);
        buttonLed01 = findViewById(R.id.buttonLed01);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothConnectionManager.toggleBluetoothConnection();
            }
        });

        buttonLed01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ledControlManager.toggleLED();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        bluetoothConnectionManager.onRequestPermissionsResult(requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothConnectionManager.disconnectBluetooth();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}