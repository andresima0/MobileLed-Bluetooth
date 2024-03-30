package com.example.mobileled_bt_v6_2;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private boolean isConnected = false;
    private boolean isLed01_On = false;
    private boolean isLed02_On = false;
    private boolean isLed03_On = false;
    private boolean isLed04_On = false;
    private boolean isLed05_On = false;
    private boolean isLed06_On = false;
    private boolean isLed07_On = false;
    private boolean isLed08_On = false;


    private EditText macAddressEditText;

    private Button buttonConnect;
    private Button buttonLed01;
    private Button buttonLed02;
    private Button buttonLed03;
    private Button buttonLed04;
    private Button buttonLed05;
    private Button buttonLed06;
    private Button buttonLed07;
    private Button buttonLed08;
    private Button buttonMonitor;

    private static final UUID UUID_BT = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                String receivedData = (String) msg.obj;
                updateMonitorButton(receivedData);
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        macAddressEditText = findViewById(R.id.macAddressEditText);
        macAddressEditText.addTextChangedListener(new MacAddressTextWatcher(macAddressEditText));
        buttonConnect = findViewById(R.id.buttonConnect);
        buttonLed01 = findViewById(R.id.buttonLed01);
        buttonLed02 = findViewById(R.id.buttonLed02);
        buttonLed03 = findViewById(R.id.buttonLed03);
        buttonLed04 = findViewById(R.id.buttonLed04);
        buttonLed05 = findViewById(R.id.buttonLed05);
        buttonLed06 = findViewById(R.id.buttonLed06);
        buttonLed07 = findViewById(R.id.buttonLed07);
        buttonLed08 = findViewById(R.id.buttonLed08);
        buttonMonitor = findViewById(R.id.buttonMonitor);

        // Verificar a permissão BLUETOOTH_CONNECT em tempo de execução
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            // Permissão ainda não concedida, solicite-a ao usuário
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    REQUEST_BLUETOOTH_PERMISSION);
        } else {
            // Permissão já concedida, continue com a inicialização do Bluetooth
            initializeBluetooth();
        }

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected) {
                    connectToBluetooth();
                } else {
                    disconnectBluetooth();
                }
            }
        });

        buttonLed01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed01_On ? "a" : "A");
                    isLed01_On = !isLed01_On;
                    updateButtonLed01Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed02_On ? "b" : "B");
                    isLed02_On = !isLed02_On;
                    updateButtonLed02Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed03_On ? "c" : "C");
                    isLed03_On = !isLed03_On;
                    updateButtonLed03Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed04_On ? "d" : "D");
                    isLed04_On = !isLed04_On;
                    updateButtonLed04Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed05_On ? "e" : "E");
                    isLed05_On = !isLed05_On;
                    updateButtonLed05Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed06_On ? "f" : "F");
                    isLed06_On = !isLed06_On;
                    updateButtonLed06Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed07_On ? "g" : "G");
                    isLed07_On = !isLed07_On;
                    updateButtonLed07Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed08_On ? "h" : "H");
                    isLed08_On = !isLed08_On;
                    updateButtonLed08Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Este botão é apenas para monitorar, não tem ação direta
            }
        });
    }

    // Atualização das cores do botão
    private void updateButtonLed01Color() {
        int color = isLed01_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed01.setBackgroundColor(color);
    }

    private void updateButtonLed02Color() {
        int color = isLed02_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed02.setBackgroundColor(color);
    }

    private void updateButtonLed03Color() {
        int color = isLed03_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed03.setBackgroundColor(color);
    }

    private void updateButtonLed04Color() {
        int color = isLed04_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed04.setBackgroundColor(color);
    }

    private void updateButtonLed05Color() {
        int color = isLed05_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed05.setBackgroundColor(color);
    }

    private void updateButtonLed06Color() {
        int color = isLed06_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed06.setBackgroundColor(color);
    }

    private void updateButtonLed07Color() {
        int color = isLed07_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed07.setBackgroundColor(color);
    }

    private void updateButtonLed08Color() {
        int color = isLed08_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed08.setBackgroundColor(color);
    }

    // Atualização das cores do botão de monitoramento
    private void updateMonitorButton(String receivedData) {
        int color = receivedData.equals("1") ? ContextCompat.getColor(this, R.color.red) :
                ContextCompat.getColor(this, R.color.gray);
        buttonMonitor.setBackgroundColor(color);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initializeBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            showToast("Bluetooth not supported on this device.");
            finish();
            return;
        }
    }

    private void connectToBluetooth() {

        String macAddress = macAddressEditText.getText().toString();
        if (macAddress.isEmpty()) {
            showToast("Please enter a MAC address.");
            return;
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);

        try {
            bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID_BT);
            bluetoothSocket.connect();
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();
            isConnected = true;

            showToast("Connected to ESP32 via Bluetooth.");
            buttonConnect.setText("Disconnect");
            buttonLed01.setEnabled(true);
            buttonLed02.setEnabled(true);
            buttonLed03.setEnabled(true);
            buttonLed04.setEnabled(true);
            buttonLed05.setEnabled(true);
            buttonLed06.setEnabled(true);
            buttonLed07.setEnabled(true);
            buttonLed08.setEnabled(true);
            updateButtonLed01Color();
            updateButtonLed02Color();
            updateButtonLed03Color();
            updateButtonLed04Color();
            updateButtonLed05Color();
            updateButtonLed06Color();
            updateButtonLed07Color();
            updateButtonLed08Color();
            startListening();
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to connect to ESP32 via Bluetooth.");
        } catch (SecurityException e) {
            e.printStackTrace();
            showToast("Bluetooth permission not granted.");
        }
    }

    private void disconnectBluetooth() {
        try {
            bluetoothSocket.close();
            isConnected = false;

            showToast("Disconnected from ESP32.");
            buttonConnect.setText("Connect to ESP32");
            buttonLed01.setEnabled(false);
            buttonLed02.setEnabled(false);
            buttonLed03.setEnabled(false);
            buttonLed04.setEnabled(false);
            buttonLed05.setEnabled(false);
            buttonLed06.setEnabled(false);
            buttonLed07.setEnabled(false);
            buttonLed08.setEnabled(false);
            updateButtonLed01Color();
            updateButtonLed02Color();
            updateButtonLed03Color();
            updateButtonLed04Color();
            updateButtonLed05Color();
            updateButtonLed06Color();
            updateButtonLed07Color();
            updateButtonLed08Color();
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error disconnecting from ESP32.");
        }
    }

    private void sendBluetoothData(String data) {
        if (isConnected) {
            try {
                outputStream.write(data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                showToast("Error sending data to ESP32.");
            }
        } else {
            showToast("Not connected to ESP32.");
        }
    }

    private void startListening() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[1024];
                int bytes;

                while (true) {
                    try {
                        bytes = inputStream.read(buffer);
                        String receivedData = new String(buffer, 0, bytes);
                        handler.obtainMessage(0, bytes, -1, receivedData).sendToTarget();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, continue com a inicialização do Bluetooth
                initializeBluetooth();
            } else {
                // Permissão negada pelo usuário, exiba uma mensagem de erro ou solicitação
                showToast("Bluetooth permission is required to use the app.");
                finish(); // Finalize a atividade se a permissão for negada.
            }
        }

        // Chame o método da superclasse para garantir o comportamento correto
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectBluetooth();
    }
}