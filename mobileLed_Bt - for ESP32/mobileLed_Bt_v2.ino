#include <BluetoothSerial.h>

BluetoothSerial SerialBT;

const int led01 = 2;  
const int led02 = 4;  
const int led03 = 5; 
const int led04 = 18;  
const int led05 = 19;   

void setup() {
  SerialBT.begin("ESP32_LED_Control"); 
  pinMode(led01, OUTPUT);
  pinMode(led02, OUTPUT);
  pinMode(led03, OUTPUT);
  pinMode(led04, OUTPUT);
  pinMode(led05, OUTPUT);
  
  // Inicialmente, todos os botões estão desligados
  digitalWrite(led01, LOW);
  digitalWrite(led02, LOW);
  digitalWrite(led03, LOW);
  digitalWrite(led04, LOW);
  digitalWrite(led05, LOW);
}

void loop() {
  if (SerialBT.available()) {
    char command = SerialBT.read();
    
    // Converta o comando para uma string
    String value = String(command);
    
    // Verifique qual botão foi acionado com base no comando Bluetooth
    if (value == "1") {
      digitalWrite(led01, HIGH); 
    } else if (value == "0") {
      digitalWrite(led01, LOW);
    } else if (value == "3") {
      digitalWrite(led02, HIGH);
    } else if (value == "2") {
      digitalWrite(led02, LOW); 
    } else if (value == "5") {
      digitalWrite(led03, HIGH); 
    } else if (value == "4") {
      digitalWrite(led03, LOW);
    } else if (value == "7") {
      digitalWrite(led04, HIGH); 
    } else if (value == "6") {
      digitalWrite(led04, LOW);
    } else if (value == "9") {
      digitalWrite(led05, HIGH); 
    } else if (value == "8") {
      digitalWrite(led05, LOW);
    }
  }
}
