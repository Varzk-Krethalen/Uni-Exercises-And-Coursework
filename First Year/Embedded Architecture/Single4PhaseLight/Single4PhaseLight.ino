const int green = 13;
const int yellow = 12;
const int red = 11;

void setup() {
  // set the digital pin as output:
  pinMode(green, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(red, OUTPUT);
  digitalWrite(green, HIGH);
}

void loop() {
  digitalWrite(green, LOW);
  digitalWrite(yellow, HIGH);
  delay(1000);
  digitalWrite(yellow, LOW);
  digitalWrite(red, HIGH);
  delay(5000);
  digitalWrite(yellow, HIGH);
  delay(1000);
  digitalWrite(yellow, LOW);
  digitalWrite(red, LOW);
  digitalWrite(green, HIGH);
  delay(5000);
}
