/**
 * The MainActivity class is the main activity of the application.
 * It handles the Bluetooth connection, sending and receiving data,
 * and updating the UI based on the received data.
 */
package br.com.local.mobileled_bt_v6_3;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
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

    // Request code for Bluetooth permission
    private static final int REQUEST_BLUETOOTH_PERMISSION = 1;

    // Bluetooth variables
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private boolean isConnected = false;

    // LED variables
    private boolean isLed01_On = false;
    private boolean isLed02_On = false;
    private boolean isLed03_On = false;
    private boolean isLed04_On = false;
    private boolean isLed05_On = false;
    private boolean isLed06_On = false;
    private boolean isLed07_On = false;
    private boolean isLed08_On = false;

    // UI elements
    private EditText macAddressEditText;
    private Button buttonConnect;
    private Button switchButton;
    private Button buttonLed01;
    private Button buttonLed02;
    private Button buttonLed03;
    private Button buttonLed04;
    private Button buttonLed05;
    private Button buttonLed06;
    private Button buttonLed07;
    private Button buttonLed08;
    private Button buttonMonitor;
    private View mainLayout;
    private View controlLayout;

    // Bluetooth UUID
    private static final UUID UUID_BT = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Handler for receiving data from Bluetooth
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

        // Inflate the main and control layouts
        mainLayout = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        controlLayout = LayoutInflater.from(this).inflate(R.layout.activity_control, null);

        // Set the main layout as the content view
        setContentView(mainLayout);

        // Initialize UI elements
        macAddressEditText = mainLayout.findViewById(R.id.macAddressEditText);
        macAddressEditText.addTextChangedListener(new MacAddressTextWatcher(macAddressEditText));
        buttonConnect = mainLayout.findViewById(R.id.buttonConnect);
        switchButton = mainLayout.findViewById(R.id.switchButton);
        buttonLed01 = controlLayout.findViewById(R.id.buttonLed01);
        buttonLed02 = controlLayout.findViewById(R.id.buttonLed02);
        buttonLed03 = controlLayout.findViewById(R.id.buttonLed03);
        buttonLed04 = controlLayout.findViewById(R.id.buttonLed04);
        buttonLed05 = controlLayout.findViewById(R.id.buttonLed05);
        buttonLed06 = controlLayout.findViewById(R.id.buttonLed06);
        buttonLed07 = controlLayout.findViewById(R.id.buttonLed07);
        buttonLed08 = controlLayout.findViewById(R.id.buttonLed08);
        buttonMonitor = controlLayout.findViewById(R.id.buttonMonitor);

        // Check Bluetooth permission at runtime
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    REQUEST_BLUETOOTH_PERMISSION);
        } else {
            // Permission granted, continue with Bluetooth initialization
            initializeBluetooth();
        }

        // Set click listeners for buttons
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

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle between main and control layouts
                if (mainLayout.getParent() != null) {
                    setContentView(controlLayout);
                } else {
                    setContentView(mainLayout);
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
    }

    /**
     * Updates the color of buttonLed01 based on the state of isLed01_On.
     */
    private void updateButtonLed01Color() {
        int color = isLed01_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed01.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed02 based on the state of isLed02_On.
     */
    private void updateButtonLed02Color() {
        int color = isLed02_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed02.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed03 based on the state of isLed03_On.
     */
    private void updateButtonLed03Color() {
        int color = isLed03_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed03.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed04 based on the state of isLed04_On.
     */
    private void updateButtonLed04Color() {
        int color = isLed04_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed04.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed05 based on the state of isLed05_On.
     */
    private void updateButtonLed05Color() {
        int color = isLed05_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed05.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed06 based on the state of isLed06_On.
     */
    private void updateButtonLed06Color() {
        int color = isLed06_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed06.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed07 based on the state of isLed07_On.
     */
    private void updateButtonLed07Color() {
        int color = isLed07_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed07.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonLed08 based on the state of isLed08_On.
     */
    private void updateButtonLed08Color() {
        int color = isLed08_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed08.setBackgroundColor(color);
    }

    /**
     * Updates the color of buttonMonitor based on the received data.
     * If the received data is "1", the color is set to red. Otherwise, it is set to gray.
     * @param receivedData The received data from Bluetooth.
     */
    /**
     * Updates the color of buttonMonitor based on the received data.
     * If the received data is "1", the color is set to red.
     * If the received data is "2", the color is set to green.
     * If the received data is "3", the color is set to blue.
     * Otherwise, it is set to gray.
     *
     * @param receivedData The received data from Bluetooth.
     */
    private void updateMonitorButton(String receivedData) {
        int color;
        switch (receivedData) {
            case "1":
                color = ContextCompat.getColor(this, R.color.red);
                break;
            case "2":
                color = ContextCompat.getColor(this, R.color.green);
                break;
            case "3":
                color = ContextCompat.getColor(this, R.color.blue);
                break;
            default:
                color = ContextCompat.getColor(this, R.color.gray);
                break;
        }
        buttonMonitor.setBackgroundColor(color);
    }


    /**
     * Shows a toast message with the given message.
     *
     * @param message The message to be shown in the toast.
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Initializes the Bluetooth adapter.
     * If Bluetooth is not supported on the device, it shows a toast message and finishes the activity.
     */
    private void initializeBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            showToast("Bluetooth not supported on this device.");
            finish();
            return;
        }
    }

    /**
     * Connects to the Bluetooth device with the MAC address entered in the macAddressEditText.
     * If the MAC address is empty, it shows a toast message.
     * If the connection is successful, it sets isConnected to true, updates the UI, and starts listening for data.
     * If the connection fails, it shows a toast message.
     * If the Bluetooth permission is not granted, it shows a toast message.
     */
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
            enableControlButtons(true);

            startListening();

        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to connect to ESP32 via Bluetooth.");
        } catch (SecurityException e) {
            e.printStackTrace();
            showToast("Bluetooth permission not granted.");
        }
    }

    /**
     * Disconnects from the Bluetooth device.
     * If the disconnection is successful, it sets isConnected to false, updates the UI, and disables control buttons.
     * If the disconnection fails, it shows a toast message.
     */
    private void disconnectBluetooth() {
        try {
            bluetoothSocket.close();
            isConnected = false;

            showToast("Disconnected from ESP32.");
            buttonConnect.setText("Connect to ESP32");
            enableControlButtons(false);
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error disconnecting from ESP32.");
        }
    }

    /**
     * Sends data to the Bluetooth device.
     * If connected, it sends the data through the outputStream.
     * If not connected, it shows a toast message.
     *
     * @param data The data to be sent.
     */
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

    /**
     * Starts listening for data from the Bluetooth device in a separate thread.
     * When data is received, it sends a message to the handler to update the UI.
     */
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

    /**
     * Enables or disables the control buttons based on the given enable parameter.
     * If enable is true, it enables the buttons and updates their colors.
     * If enable is false, it disables the buttons.
     *
     * @param enable Whether to enable or disable the buttons.
     */
    private void enableControlButtons(boolean enable) {
        buttonLed01.setEnabled(enable);
        buttonLed02.setEnabled(enable);
        buttonLed03.setEnabled(enable);
        buttonLed04.setEnabled(enable);
        buttonLed05.setEnabled(enable);
        buttonLed06.setEnabled(enable);
        buttonLed07.setEnabled(enable);
        buttonLed08.setEnabled(enable);
        updateButtonLed01Color();
        updateButtonLed02Color();
        updateButtonLed03Color();
        updateButtonLed04Color();
        updateButtonLed05Color();
        updateButtonLed06Color();
        updateButtonLed07Color();
        updateButtonLed08Color();
    }

    @Override
    public void onBackPressed() {
        if (controlLayout.getParent() != null) {
            setContentView(mainLayout);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_BLUETOOTH_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeBluetooth();
            } else {
                showToast("Bluetooth permission is required to use the app.");
                finish();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectBluetooth();
    }
}