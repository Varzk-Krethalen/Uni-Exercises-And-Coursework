const int ledPin = 13; // the pin that the LED is attached to

void setup() {
  Serial1.begin(9600);
  pinMode(ledPin, OUTPUT);    // initialize the LED pin as an output
}

void loop() {
  Serial1.print('H');
  #ifdef DEBUG
      digitalWrite(ledPin, HIGH); // …turn on the LED
  #endif
  delay(500);
  Serial1.print('L');
  #ifdef DEBUG
      digitalWrite(ledPin, LOW); // …turn on the LED
  #endif
  delay(500);
}
