package br.com.local.mobileled_bt_v5;

public class Led05 {
    private final MainActivity mainActivity;
    private int ledState = 8;

    public Led05(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void toggleLed05() {
        if (mainActivity.bluetoothConnectionManager.isConnected) {
            ledState = (ledState == 8) ? 9 : 8;
            updateButtonColor();
            mainActivity.bluetoothConnectionManager.sendBluetoothData(Integer.toString(ledState));
        } else {
            mainActivity.showToast("Not connected to ESP32.");
        }
    }

    public void updateButtonColor() {
        int colorLed = (ledState == 9) ? mainActivity.getResources().getColor(R.color.green) :
                mainActivity.getResources().getColor(R.color.gray);
        mainActivity.buttonLed05.setBackgroundColor(colorLed);
    }
}