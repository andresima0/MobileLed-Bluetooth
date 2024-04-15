package br.com.local.mobileled_v7_1;

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
    private boolean isLed09_On = false;
    private boolean isLed10_On = false;
    private boolean isLed11_On = false;
    private boolean isLed12_On = false;
    private boolean isLed13_On = false;
    private boolean isLed14_On = false;
    private boolean isLed15_On = false;
    private boolean isLed16_On = false;
    private boolean isLed17_On = false;
    private boolean isLed18_On = false;
    private boolean isLed19_On = false;
    private boolean isLed20_On = false;
    private boolean isLed21_On = false;
    private boolean isLed22_On = false;
    private boolean isLed23_On = false;
    private boolean isLed24_On = false;

    private EditText macAddressEditText;
    private Button buttonConnect;
    private Button presetSwitchButton;
    private Button backMainButton;

    private Button back01SwitchButton;
    private Button back02SwitchButton;
    private Button back03SwitchButton;

    private Button buttonLed01;
    private Button buttonLed02;
    private Button buttonLed03;
    private Button buttonLed04;
    private Button buttonLed05;
    private Button buttonLed06;
    private Button buttonLed07;
    private Button buttonLed08;
    private Button buttonLed09;
    private Button buttonLed10;
    private Button buttonLed11;
    private Button buttonLed12;
    private Button buttonLed13;
    private Button buttonLed14;
    private Button buttonLed15;
    private Button buttonLed16;
    private Button buttonLed17;
    private Button buttonLed18;
    private Button buttonLed19;
    private Button buttonLed20;
    private Button buttonLed21;
    private Button buttonLed22;
    private Button buttonLed23;
    private Button buttonLed24;
    private Button buttonMonitor01;
    private Button buttonMonitor02;
    private Button buttonMonitor03;
    private Button presetButton01;
    private Button presetButton02;
    private Button presetButton03;
    private View mainLayout;

    private View presetsLayout;
    private View preset01Layout;
    private View preset02Layout;
    private View preset03Layout;

    private static final UUID UUID_BT = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                String receivedData = (String) msg.obj;
                updateMonitorButton01(receivedData);
                updateMonitorButton02(receivedData);
                updateMonitorButton03(receivedData);
                updateButtonPreset01Color(receivedData);
                updateButtonPreset02Color(receivedData);
                updateButtonPreset03Color(receivedData);
            }
            return true;
        }
    });

    private static final int UPDATE_INTERVAL = 100;

    private final Handler updateHandler = new Handler();
    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {

            if (isLed01_On) {
                sendBluetoothData("A");
            } else {
                sendBluetoothData("a");
            }
            if (isLed02_On) {
                sendBluetoothData("B");
            } else {
                sendBluetoothData("b");
            }
            if (isLed03_On) {
                sendBluetoothData("C");
            } else {
                sendBluetoothData("c");
            }
            if (isLed04_On) {
                sendBluetoothData("D");
            } else {
                sendBluetoothData("d");
            }
            if (isLed05_On) {
                sendBluetoothData("E");
            } else {
                sendBluetoothData("e");
            }
            if (isLed06_On) {
                sendBluetoothData("F");
            } else {
                sendBluetoothData("f");
            }
            if (isLed07_On) {
                sendBluetoothData("G");
            } else {
                sendBluetoothData("g");
            }
            if (isLed08_On) {
                sendBluetoothData("H");
            } else {
                sendBluetoothData("h");
            }
            if (isLed09_On) {
                sendBluetoothData("I");
            } else {
                sendBluetoothData("i");
            }
            if (isLed10_On) {
                sendBluetoothData("J");
            } else {
                sendBluetoothData("j");
            }
            if (isLed11_On) {
                sendBluetoothData("K");
            } else {
                sendBluetoothData("k");
            }
            if (isLed12_On) {
                sendBluetoothData("L");
            } else {
                sendBluetoothData("l");
            }
            if (isLed13_On) {
                sendBluetoothData("M");
            } else {
                sendBluetoothData("m");
            }
            if (isLed14_On) {
                sendBluetoothData("N");
            } else {
                sendBluetoothData("n");
            }
            if (isLed15_On) {
                sendBluetoothData("O");
            } else {
                sendBluetoothData("o");
            }
            if (isLed16_On) {
                sendBluetoothData("P");
            } else {
                sendBluetoothData("p");
            }
            if (isLed17_On) {
                sendBluetoothData("Q");
            } else {
                sendBluetoothData("q");
            }
            if (isLed18_On) {
                sendBluetoothData("R");
            } else {
                sendBluetoothData("r");
            }
            if (isLed19_On) {
                sendBluetoothData("S");
            } else {
                sendBluetoothData("s");
            }
            if (isLed20_On) {
                sendBluetoothData("T");
            } else {
                sendBluetoothData("t");
            }
            if (isLed21_On) {
                sendBluetoothData("U");
            } else {
                sendBluetoothData("u");
            }
            if (isLed22_On) {
                sendBluetoothData("V");
            } else {
                sendBluetoothData("v");
            }
            if (isLed23_On) {
                sendBluetoothData("W");
            } else {
                sendBluetoothData("w");
            }
            if (isLed24_On) {
                sendBluetoothData("X");
            } else {
                sendBluetoothData("x");
            }
            updateHandler.postDelayed(this, UPDATE_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayout = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        presetsLayout = LayoutInflater.from(this).inflate(R.layout.activity_presets, null);
        preset01Layout = LayoutInflater.from(this).inflate(R.layout.activity_preset01, null);
        preset02Layout = LayoutInflater.from(this).inflate(R.layout.activity_preset02, null);
        preset03Layout = LayoutInflater.from(this).inflate(R.layout.activity_preset03, null);

        setContentView(mainLayout);

        macAddressEditText = mainLayout.findViewById(R.id.macAddressEditText);
        macAddressEditText.addTextChangedListener(new MacAddressTextWatcher(macAddressEditText));
        buttonConnect = mainLayout.findViewById(R.id.buttonConnect);
        presetSwitchButton = mainLayout.findViewById(R.id.presetSwitchButton);
        presetButton01 = presetsLayout.findViewById(R.id.presetButton01);
        presetButton02 = presetsLayout.findViewById(R.id.presetButton02);
        presetButton03 = presetsLayout.findViewById(R.id.presetButton03);
        backMainButton = presetsLayout.findViewById((R.id.backMainButton));
        back01SwitchButton = preset01Layout.findViewById(R.id.back01SwitchButton);
        back02SwitchButton = preset02Layout.findViewById(R.id.back02SwitchButton);
        back03SwitchButton = preset03Layout.findViewById(R.id.back03SwitchButton);
        buttonLed01 = preset01Layout.findViewById(R.id.buttonLed01);
        buttonLed02 = preset01Layout.findViewById(R.id.buttonLed02);
        buttonLed03 = preset01Layout.findViewById(R.id.buttonLed03);
        buttonLed04 = preset01Layout.findViewById(R.id.buttonLed04);
        buttonLed05 = preset01Layout.findViewById(R.id.buttonLed05);
        buttonLed06 = preset01Layout.findViewById(R.id.buttonLed06);
        buttonLed07 = preset01Layout.findViewById(R.id.buttonLed07);
        buttonLed08 = preset01Layout.findViewById(R.id.buttonLed08);
        buttonLed09 = preset02Layout.findViewById(R.id.buttonLed09);
        buttonLed10 = preset02Layout.findViewById(R.id.buttonLed10);
        buttonLed11 = preset02Layout.findViewById(R.id.buttonLed11);
        buttonLed12 = preset02Layout.findViewById(R.id.buttonLed12);
        buttonLed13 = preset02Layout.findViewById(R.id.buttonLed13);
        buttonLed14 = preset02Layout.findViewById(R.id.buttonLed14);
        buttonLed15 = preset02Layout.findViewById(R.id.buttonLed15);
        buttonLed16 = preset02Layout.findViewById(R.id.buttonLed16);
        buttonLed17 = preset03Layout.findViewById(R.id.buttonLed17);
        buttonLed18 = preset03Layout.findViewById(R.id.buttonLed18);
        buttonLed19 = preset03Layout.findViewById(R.id.buttonLed19);
        buttonLed20 = preset03Layout.findViewById(R.id.buttonLed20);
        buttonLed21 = preset03Layout.findViewById(R.id.buttonLed21);
        buttonLed22 = preset03Layout.findViewById(R.id.buttonLed22);
        buttonLed23 = preset03Layout.findViewById(R.id.buttonLed23);
        buttonLed24 = preset03Layout.findViewById(R.id.buttonLed24);
        buttonMonitor01 = preset01Layout.findViewById(R.id.buttonMonitor);
        buttonMonitor02 = preset02Layout.findViewById(R.id.buttonMonitor);
        buttonMonitor03 = preset03Layout.findViewById(R.id.buttonMonitor);
        updateMonitorButton01("0");
        updateMonitorButton02("0");
        updateMonitorButton03("0");
        updateButtonPreset01Color("0");
        updateButtonPreset03Color("0");
        updateButtonPreset01Color("0");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    REQUEST_BLUETOOTH_PERMISSION);
        } else {
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

        presetSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainLayout.getParent() != null) {
                    setContentView(presetsLayout);
                } else {
                    setContentView(mainLayout);
                }
            }
        });

        backMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presetsLayout.getParent() != null) {
                    setContentView(mainLayout);
                } else {
                    setContentView(presetsLayout);
                }
            }
        });

        presetButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presetsLayout.getParent() != null) {
                    setContentView(preset01Layout);
                } else {
                    setContentView(presetsLayout);
                }
            }
        });

        back01SwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preset01Layout.getParent() != null) {
                    setContentView(presetsLayout);
                } else {
                    setContentView(preset01Layout);
                }
            }
        });

        presetButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presetsLayout.getParent() != null) {
                    setContentView(preset02Layout);
                } else {
                    setContentView(presetsLayout);
                }
            }
        });

        back02SwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preset02Layout.getParent() != null) {
                    setContentView(presetsLayout);
                } else {
                    setContentView(preset02Layout);
                }
            }
        });

        presetButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presetsLayout.getParent() != null) {
                    setContentView(preset03Layout);
                } else {
                    setContentView(presetsLayout);
                }
            }
        });

        back03SwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preset03Layout.getParent() != null) {
                    setContentView(presetsLayout);
                } else {
                    setContentView(preset01Layout);
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

        buttonLed09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed09_On ? "i" : "I");
                    isLed09_On = !isLed09_On;
                    updateButtonLed09Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed10_On ? "j" : "J");
                    isLed10_On = !isLed10_On;
                    updateButtonLed10Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed11_On ? "k" : "K");
                    isLed11_On = !isLed11_On;
                    updateButtonLed11Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed12_On ? "l" : "L");
                    isLed12_On = !isLed12_On;
                    updateButtonLed12Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed13_On ? "m" : "M");
                    isLed13_On = !isLed13_On;
                    updateButtonLed13Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed14_On ? "n" : "N");
                    isLed14_On = !isLed14_On;
                    updateButtonLed14Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed15_On ? "o" : "O");
                    isLed15_On = !isLed15_On;
                    updateButtonLed15Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed16_On ? "p" : "P");
                    isLed16_On = !isLed16_On;
                    updateButtonLed16Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed17_On ? "q" : "Q");
                    isLed17_On = !isLed17_On;
                    updateButtonLed17Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed18_On ? "r" : "R");
                    isLed18_On = !isLed18_On;
                    updateButtonLed18Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed19_On ? "s" : "S");
                    isLed19_On = !isLed19_On;
                    updateButtonLed19Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed20_On ? "t" : "T");
                    isLed20_On = !isLed20_On;
                    updateButtonLed20Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed21_On ? "u" : "U");
                    isLed21_On = !isLed21_On;
                    updateButtonLed21Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed22_On ? "v" : "V");
                    isLed22_On = !isLed22_On;
                    updateButtonLed22Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed23_On ? "w" : "W");
                    isLed23_On = !isLed23_On;
                    updateButtonLed23Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        buttonLed24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    sendBluetoothData(isLed24_On ? "x" : "X");
                    isLed24_On = !isLed24_On;
                    updateButtonLed24Color();
                } else {
                    showToast("Not connected to ESP32.");
                }
            }
        });

        startButtonUpdates();
    }

    private void toggleLedState(boolean currentState, char onChar, char offChar) {
        if (isConnected) {
            sendBluetoothData(String.valueOf(currentState ? offChar : onChar));
            currentState = !currentState;
        } else {
            showToast("Not connected to ESP32.");
        }
    }

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

    private void updateButtonLed09Color() {
        int color = isLed09_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed09.setBackgroundColor(color);
    }

    private void updateButtonLed10Color() {
        int color = isLed10_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed10.setBackgroundColor(color);
    }

    private void updateButtonLed11Color() {
        int color = isLed11_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed11.setBackgroundColor(color);
    }

    private void updateButtonLed12Color() {
        int color = isLed12_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed12.setBackgroundColor(color);
    }

    private void updateButtonLed13Color() {
        int color = isLed13_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed13.setBackgroundColor(color);
    }

    private void updateButtonLed14Color() {
        int color = isLed14_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed14.setBackgroundColor(color);
    }

    private void updateButtonLed15Color() {
        int color = isLed15_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed15.setBackgroundColor(color);
    }

    private void updateButtonLed16Color() {
        int color = isLed16_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed16.setBackgroundColor(color);
    }

    private void updateButtonLed17Color() {
        int color = isLed17_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed17.setBackgroundColor(color);
    }

    private void updateButtonLed18Color() {
        int color = isLed18_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed18.setBackgroundColor(color);
    }

    private void updateButtonLed19Color() {
        int color = isLed19_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed19.setBackgroundColor(color);
    }

    private void updateButtonLed20Color() {
        int color = isLed20_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed20.setBackgroundColor(color);
    }

    private void updateButtonLed21Color() {
        int color = isLed21_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed21.setBackgroundColor(color);
    }

    private void updateButtonLed22Color() {
        int color = isLed22_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed22.setBackgroundColor(color);
    }

    private void updateButtonLed23Color() {
        int color = isLed23_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed23.setBackgroundColor(color);
    }

    private void updateButtonLed24Color() {
        int color = isLed24_On ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        buttonLed24.setBackgroundColor(color);
    }

    private void updateMonitorButton01(String receivedData) {
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
        buttonMonitor01.setBackgroundColor(color);
    }

    private void updateMonitorButton02(String receivedData) {
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
        buttonMonitor02.setBackgroundColor(color);
    }

    private void updateMonitorButton03(String receivedData) {
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
        buttonMonitor03.setBackgroundColor(color);

        updateButtonPreset01Color(receivedData);
        updateButtonPreset02Color(receivedData);
        updateButtonPreset03Color(receivedData);
    }

    private void updateButtonPreset01Color(String receivedData) {
        int color = receivedData.equals("1") ? ContextCompat.getColor(this, R.color.red) :
                ContextCompat.getColor(this, R.color.gray);
        presetButton01.setBackgroundColor(color);
    }

    private void updateButtonPreset02Color(String receivedData) {
        int color = receivedData.equals("2") ? ContextCompat.getColor(this, R.color.green) :
                ContextCompat.getColor(this, R.color.gray);
        presetButton02.setBackgroundColor(color);
    }

    private void updateButtonPreset03Color(String receivedData) {
        int color = receivedData.equals("3") ? ContextCompat.getColor(this, R.color.blue) :
                ContextCompat.getColor(this, R.color.gray);
        presetButton03.setBackgroundColor(color);
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

            showToast("Connected to ESP32.");
            buttonConnect.setText("Disconnect");
            enableControlButtons(true);

            startListening();

        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to connect to ESP32.");
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
            enableControlButtons(false);
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

    private void enableControlButtons(boolean enable) {
        buttonLed01.setEnabled(enable);
        buttonLed02.setEnabled(enable);
        buttonLed03.setEnabled(enable);
        buttonLed04.setEnabled(enable);
        buttonLed05.setEnabled(enable);
        buttonLed06.setEnabled(enable);
        buttonLed07.setEnabled(enable);
        buttonLed08.setEnabled(enable);
        buttonLed09.setEnabled(enable);
        buttonLed10.setEnabled(enable);
        buttonLed11.setEnabled(enable);
        buttonLed12.setEnabled(enable);
        buttonLed13.setEnabled(enable);
        buttonLed14.setEnabled(enable);
        buttonLed15.setEnabled(enable);
        buttonLed16.setEnabled(enable);
        buttonLed17.setEnabled(enable);
        buttonLed18.setEnabled(enable);
        buttonLed19.setEnabled(enable);
        buttonLed20.setEnabled(enable);
        buttonLed21.setEnabled(enable);
        buttonLed22.setEnabled(enable);
        buttonLed23.setEnabled(enable);
        buttonLed24.setEnabled(enable);
        updateButtonLed01Color();
        updateButtonLed02Color();
        updateButtonLed03Color();
        updateButtonLed04Color();
        updateButtonLed05Color();
        updateButtonLed06Color();
        updateButtonLed07Color();
        updateButtonLed08Color();
        updateButtonLed09Color();
        updateButtonLed10Color();
        updateButtonLed11Color();
        updateButtonLed12Color();
        updateButtonLed13Color();
        updateButtonLed14Color();
        updateButtonLed15Color();
        updateButtonLed16Color();
        updateButtonLed17Color();
        updateButtonLed18Color();
        updateButtonLed19Color();
        updateButtonLed20Color();
        updateButtonLed21Color();
        updateButtonLed22Color();
        updateButtonLed23Color();
        updateButtonLed24Color();
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
        stopButtonUpdates();
        disconnectBluetooth();
    }

    private void startButtonUpdates() {
        updateHandler.postDelayed(updateRunnable, UPDATE_INTERVAL);
    }

    private void stopButtonUpdates() {
        updateHandler.removeCallbacks(updateRunnable);
    }
}
