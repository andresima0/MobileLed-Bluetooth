#include <BluetoothSerial.h>

BluetoothSerial SerialBT;

const int LED_PIN_1 = 2;   // Pino do LED 1 (GPIO 2)
const int LED_PIN_2 = 4;   // Pino do LED 2 (GPIO 4)
const int LED_PIN_3 = 5;   // Pino do LED 3 (GPIO 5)
const int LED_PIN_4 = 18;  // Pino do LED 4 (GPIO 18)
const int LED_PIN_5 = 19;  // Pino do LED 5 (GPIO 19)
const int LED_PIN_6 = 21;  // Pino do LED 6 (GPIO 21)
const int LED_PIN_7 = 22;  // Pino do LED 7 (GPIO 22)
const int LED_PIN_8 = 23;  // Pino do LED 8 (GPIO 23)

const int SWITCH_PIN = 13;  // Pino do switch (GPIO 13)

const int PRESET_1 = 25;  // Pino do PRESET 1
const int PRESET_2 = 26;  // Pino do PRESET 2
const int PRESET_3 = 27;  // Pino do PRESET 3

int currentPreset = 0;
int buttonState = 0;
int lastButtonState = HIGH;
unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 50;

bool presetState[] = { false, false, false };

void setup() {
  Serial.begin(115200);
  SerialBT.begin("ESP32_BT");  // Nome do dispositivo Bluetooth

  // Configuração dos pinos dos LEDs
  pinMode(LED_PIN_1, OUTPUT);
  pinMode(LED_PIN_2, OUTPUT);
  pinMode(LED_PIN_3, OUTPUT);
  pinMode(LED_PIN_4, OUTPUT);
  pinMode(LED_PIN_5, OUTPUT);
  pinMode(LED_PIN_6, OUTPUT);
  pinMode(LED_PIN_7, OUTPUT);
  pinMode(LED_PIN_8, OUTPUT);

  // Configuração do pino do switch
  pinMode(SWITCH_PIN, INPUT_PULLUP);

  // Configuração dos pinos dos presets
  pinMode(PRESET_1, OUTPUT);
  pinMode(PRESET_2, OUTPUT);
  pinMode(PRESET_3, OUTPUT);

  // Inicialmente, os LEDs e presets estão desligados
  digitalWrite(LED_PIN_1, LOW);
  digitalWrite(LED_PIN_2, LOW);
  digitalWrite(LED_PIN_3, LOW);
  digitalWrite(LED_PIN_4, LOW);
  digitalWrite(LED_PIN_5, LOW);
  digitalWrite(LED_PIN_6, LOW);
  digitalWrite(LED_PIN_7, LOW);
  digitalWrite(LED_PIN_8, LOW);
  digitalWrite(PRESET_1, LOW);
  digitalWrite(PRESET_2, LOW);
  digitalWrite(PRESET_3, LOW);
}

void loop() {
  // Controle dos presets
  int reading = digitalRead(SWITCH_PIN);

  if (reading != lastButtonState) {
    lastDebounceTime = millis();
  }

  if ((millis() - lastDebounceTime) > debounceDelay) {
    if (reading != buttonState) {
      buttonState = reading;

      if (buttonState == LOW) {
        // Alterna o estado do preset atual e desliga o preset anterior
        presetState[currentPreset] = !presetState[currentPreset];
        digitalWrite(PRESET_1, presetState[0]);
        digitalWrite(PRESET_2, presetState[1]);
        digitalWrite(PRESET_3, presetState[2]);

        // Desliga o preset anterior
        int previousPreset = (currentPreset + 2) % 3;
        presetState[previousPreset] = false;
        digitalWrite(PRESET_1, presetState[0]);
        digitalWrite(PRESET_2, presetState[1]);
        digitalWrite(PRESET_3, presetState[2]);

        // Envia o sinal Bluetooth com base no preset atual
        char presetChar = '0' + (currentPreset + 1);  // Convertendo o número do preset para char
        SerialBT.write(presetChar);

        // Atualiza o preset atual para o próximo
        currentPreset = (currentPreset + 1) % 3;
      }
    }
  }

  lastButtonState = reading;

  // Controle dos LEDs via Bluetooth
  if (SerialBT.available()) {
    char receivedChar = SerialBT.read();
    // Verifica qual botão foi pressionado e liga ou desliga o LED correspondente
    if (currentPreset == 1) {
      switch (receivedChar) {
        case 'A':
          digitalWrite(LED_PIN_1, HIGH);
          break;
        case 'a':
          digitalWrite(LED_PIN_1, LOW);
          break;
        case 'B':
          digitalWrite(LED_PIN_2, HIGH);
          break;
        case 'b':
          digitalWrite(LED_PIN_2, LOW);
          break;
        case 'C':
          digitalWrite(LED_PIN_3, HIGH);
          break;
        case 'c':
          digitalWrite(LED_PIN_3, LOW);
          break;
        case 'D':
          digitalWrite(LED_PIN_4, HIGH);
          break;
        case 'd':
          digitalWrite(LED_PIN_4, LOW);
          break;
        case 'E':
          digitalWrite(LED_PIN_5, HIGH);
          break;
        case 'e':
          digitalWrite(LED_PIN_5, LOW);
          break;
        case 'F':
          digitalWrite(LED_PIN_6, HIGH);
          break;
        case 'f':
          digitalWrite(LED_PIN_6, LOW);
          break;
        case 'G':
          digitalWrite(LED_PIN_7, HIGH);
          break;
        case 'g':
          digitalWrite(LED_PIN_7, LOW);
          break;
        case 'H':
          digitalWrite(LED_PIN_8, HIGH);
          break;
        case 'h':
          digitalWrite(LED_PIN_8, LOW);
          break;
      }
    } else if (currentPreset == 2) {
      switch (receivedChar) {
        case 'I':
          digitalWrite(LED_PIN_1, HIGH);
          break;
        case 'i':
          digitalWrite(LED_PIN_1, LOW);
          break;
        case 'J':
          digitalWrite(LED_PIN_2, HIGH);
          break;
        case 'j':
          digitalWrite(LED_PIN_2, LOW);
          break;
        case 'K':
          digitalWrite(LED_PIN_3, HIGH);
          break;
        case 'k':
          digitalWrite(LED_PIN_3, LOW);
          break;
        case 'L':
          digitalWrite(LED_PIN_4, HIGH);
          break;
        case 'l':
          digitalWrite(LED_PIN_4, LOW);
          break;
        case 'M':
          digitalWrite(LED_PIN_5, HIGH);
          break;
        case 'm':
          digitalWrite(LED_PIN_5, LOW);
          break;
        case 'N':
          digitalWrite(LED_PIN_6, HIGH);
          break;
        case 'n':
          digitalWrite(LED_PIN_6, LOW);
          break;
        case 'O':
          digitalWrite(LED_PIN_7, HIGH);
          break;
        case 'o':
          digitalWrite(LED_PIN_7, LOW);
          break;
        case 'P':
          digitalWrite(LED_PIN_8, HIGH);
          break;
        case 'p':
          digitalWrite(LED_PIN_8, LOW);
          break;
      }
    } else if (currentPreset == 0) {
      switch (receivedChar) {
        case 'Q':
          digitalWrite(LED_PIN_1, HIGH);
          break;
        case 'q':
          digitalWrite(LED_PIN_1, LOW);
          break;
        case 'R':
          digitalWrite(LED_PIN_2, HIGH);
          break;
        case 'r':
          digitalWrite(LED_PIN_2, LOW);
          break;
        case 'S':
          digitalWrite(LED_PIN_3, HIGH);
          break;
        case 's':
          digitalWrite(LED_PIN_3, LOW);
          break;
        case 'T':
          digitalWrite(LED_PIN_4, HIGH);
          break;
        case 't':
          digitalWrite(LED_PIN_4, LOW);
          break;
        case 'U':
          digitalWrite(LED_PIN_5, HIGH);
          break;
        case 'u':
          digitalWrite(LED_PIN_5, LOW);
          break;
        case 'V':
          digitalWrite(LED_PIN_6, HIGH);
          break;
        case 'v':
          digitalWrite(LED_PIN_6, LOW);
          break;
        case 'W':
          digitalWrite(LED_PIN_7, HIGH);
          break;
        case 'w':
          digitalWrite(LED_PIN_7, LOW);
          break;
        case 'X':
          digitalWrite(LED_PIN_8, HIGH);
          break;
        case 'x':
          digitalWrite(LED_PIN_8, LOW);
          break;
      }
    }
  }
}