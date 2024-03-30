#include <BluetoothSerial.h>

BluetoothSerial SerialBT;

const int LED_PIN_A = 2;   // Pino do LED A (GPIO 2)
const int LED_PIN_B = 4;   // Pino do LED B (GPIO 4)
const int LED_PIN_C = 5;   // Pino do LED C (GPIO 5)
const int LED_PIN_D = 18;  // Pino do LED D (GPIO 18)
const int LED_PIN_E = 19;  // Pino do LED E (GPIO 19)
const int LED_PIN_F = 21;  // Pino do LED F (GPIO 21)
const int LED_PIN_G = 22;  // Pino do LED G (GPIO 22)
const int LED_PIN_H = 23;  // Pino do LED H (GPIO 23)

const int SWITCH_PIN = 13;  // Pino do switch (GPIO 13)

void setup() {
  Serial.begin(115200);
  SerialBT.begin("ESP32_BT");  // Nome do dispositivo Bluetooth

  // Configuração dos pinos dos LEDs
  pinMode(LED_PIN_A, OUTPUT);
  pinMode(LED_PIN_B, OUTPUT);
  pinMode(LED_PIN_C, OUTPUT);
  pinMode(LED_PIN_D, OUTPUT);
  pinMode(LED_PIN_E, OUTPUT);
  pinMode(LED_PIN_F, OUTPUT);
  pinMode(LED_PIN_G, OUTPUT);
  pinMode(LED_PIN_H, OUTPUT);

  // Configuração do pino do switch
  pinMode(SWITCH_PIN, INPUT_PULLUP);

  // Inicialmente, os LEDs estão desligados
  digitalWrite(LED_PIN_A, LOW);
  digitalWrite(LED_PIN_B, LOW);
  digitalWrite(LED_PIN_C, LOW);
  digitalWrite(LED_PIN_D, LOW);
  digitalWrite(LED_PIN_E, LOW);
  digitalWrite(LED_PIN_F, LOW);
  digitalWrite(LED_PIN_G, LOW);
  digitalWrite(LED_PIN_H, LOW);
}

void loop() {
  if (SerialBT.available()) {
    char receivedChar = SerialBT.read();
    // Verifica qual botão foi pressionado e liga ou desliga o LED correspondente
    switch (receivedChar) {
      case 'A':
        digitalWrite(LED_PIN_A, HIGH);
        break;
      case 'a':
        digitalWrite(LED_PIN_A, LOW);
        break;
      case 'B':
        digitalWrite(LED_PIN_B, HIGH);
        break;
      case 'b':
        digitalWrite(LED_PIN_B, LOW);
        break;
      case 'C':
        digitalWrite(LED_PIN_C, HIGH);
        break;
      case 'c':
        digitalWrite(LED_PIN_C, LOW);
        break;
      case 'D':
        digitalWrite(LED_PIN_D, HIGH);
        break;
      case 'd':
        digitalWrite(LED_PIN_D, LOW);
        break;
      case 'E':
        digitalWrite(LED_PIN_E, HIGH);
        break;
      case 'e':
        digitalWrite(LED_PIN_E, LOW);
        break;
      case 'F':
        digitalWrite(LED_PIN_F, HIGH);
        break;
      case 'f':
        digitalWrite(LED_PIN_F, LOW);
        break;
      case 'G':
        digitalWrite(LED_PIN_G, HIGH);
        break;
      case 'g':
        digitalWrite(LED_PIN_G, LOW);
        break;
      case 'H':
        digitalWrite(LED_PIN_H, HIGH);
        break;
      case 'h':
        digitalWrite(LED_PIN_H, LOW);
        break;
      default:
        // Se o caractere recebido não corresponder a nenhum botão conhecido, não faz nada
        break;
    }
  }

  int switchState = digitalRead(SWITCH_PIN);
  if (switchState == HIGH) {
    SerialBT.write('1');  // Envia '1' se o switch estiver ligado
  } else {
    SerialBT.write('0');  // Envia '0' se o switch estiver desligado
  }

  delay(100);
}