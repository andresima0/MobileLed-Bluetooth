package br.com.local.mobileled_bt_v5;

public class Led03 {
    private final MainActivity mainActivity;
    private int ledState = 4;

    public Led03(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void toggleLed03() {
        if (mainActivity.bluetoothConnectionManager.isConnected) {
            ledState = (ledState == 4) ? 5 : 4;
            updateButtonColor();
            mainActivity.bluetoothConnectionManager.sendBluetoothData(Integer.toString(ledState));
        } else {
            mainActivity.showToast("Not connected to ESP32.");
        }
    }

    public void updateButtonColor() {
        int colorLed = (ledState == 5) ? mainActivity.getResources().getColor(R.color.green) :
                mainActivity.getResources().getColor(R.color.gray);
        mainActivity.buttonLed03.setBackgroundColor(colorLed);
    }
}