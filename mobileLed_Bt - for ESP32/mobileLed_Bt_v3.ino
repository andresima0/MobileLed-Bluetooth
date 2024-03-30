#include <BluetoothSerial.h>

BluetoothSerial SerialBT;

const int LED_PIN = 2; // Pino do LED (GPIO 2)
const int MONITOR_PIN = 13; // Pino do monitor (GPIO 13)

void setup() {
  Serial.begin(115200);
  SerialBT.begin("ESP32_BT"); // Nome do dispositivo Bluetooth

  pinMode(LED_PIN, OUTPUT);
  pinMode(MONITOR_PIN, INPUT);

  digitalWrite(LED_PIN, LOW); // Inicialmente, o LED está desligado
}

void loop() {
  if (SerialBT.available()) {
    char receivedChar = SerialBT.read();
    if (receivedChar == 'A') {
      digitalWrite(LED_PIN, HIGH); // Liga o LED
    } else if (receivedChar == 'a') {
      digitalWrite(LED_PIN, LOW); // Desliga o LED
    }
  }

  int monitorState = digitalRead(MONITOR_PIN);
  if (monitorState == HIGH) {
    SerialBT.write('1'); // Envia '1' se o monitor estiver ligado
  } else {
    SerialBT.write('0'); // Envia '0' se o monitor estiver desligado
  }

  delay(100);
}