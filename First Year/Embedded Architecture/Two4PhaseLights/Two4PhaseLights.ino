const int green1 = 13;
const int yellow1 = 12;
const int red1 = 11;
const int green2 = 10;
const int yellow2 = 9;
const int red2 = 8;

void setup() {
  // set the digital pin as output:
  pinMode(green1, OUTPUT);
  pinMode(yellow1, OUTPUT);
  pinMode(red1, OUTPUT);
  pinMode(green2, OUTPUT);
  pinMode(yellow2, OUTPUT);
  pinMode(red2, OUTPUT);
  digitalWrite(green1, HIGH);
  digitalWrite(red2, HIGH);
}

void loop() {
  digitalWrite(green1, LOW);
  digitalWrite(yellow1, HIGH);
  digitalWrite(yellow2, HIGH);
  delay(1000);
  digitalWrite(yellow1, LOW);
  digitalWrite(red1, HIGH);
  digitalWrite(yellow2, LOW);
  digitalWrite(red2, LOW);
  digitalWrite(green2, HIGH);
  delay(5000);
  digitalWrite(green2, LOW);
  digitalWrite(yellow2, HIGH);
  digitalWrite(yellow1, HIGH);
  delay(1000);
  digitalWrite(yellow2, LOW);
  digitalWrite(red2, HIGH);
  digitalWrite(yellow1, LOW);
  digitalWrite(red1, LOW);
  digitalWrite(green1, HIGH);
  delay(5000);
}
