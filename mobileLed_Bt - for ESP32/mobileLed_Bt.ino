#include <BluetoothSerial.h>

BluetoothSerial SerialBT;

const int ledPin = 15;
bool ledStatus = false;

void setup() {
  SerialBT.begin("ESP32_LED_Control"); 
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);
}

void loop() {
  if (SerialBT.available()) {
    char command = SerialBT.read();
    if (command == '1') {
      ledStatus = true;
      digitalWrite(ledPin, HIGH);
    } else if (command == '0') {
      ledStatus = false;
      digitalWrite(ledPin, LOW);
    }
  }
}