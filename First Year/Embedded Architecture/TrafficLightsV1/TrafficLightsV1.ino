const int green1 = 13;
const int yellow1 = 12;
const int red1 = 11;
const int green2 = 10;
const int yellow2 = 9;
const int red2 = 8;
const int pedLight1 = 7;
const int pedLight2 = 6;
const int button1 = 5;
const int button2 = 4;

int buttonState1 = 0;
int buttonState2 = 0;
int timePassed = 0;
int lightOn = 1;
int pedLight = 0;

void setup() {
  // set the digital pin as output:
  pinMode(green1, OUTPUT);
  pinMode(yellow1, OUTPUT);
  pinMode(red1, OUTPUT);
  pinMode(green2, OUTPUT);
  pinMode(yellow2, OUTPUT);
  pinMode(red2, OUTPUT);
  pinMode(pedLight1, OUTPUT);
  pinMode(pedLight2, OUTPUT);
  pinMode(button1, INPUT);
  pinMode(button2, INPUT);
  digitalWrite(green1, HIGH);
  digitalWrite(red2, HIGH);
  digitalWrite(pedLight1, HIGH);
  digitalWrite(button1, HIGH);
  digitalWrite(button2, HIGH);
  Serial.begin(9600);
}

void loop() {
  buttonWaitCheck();
  if(lightOn == 2)
  {
    switch2Red();
    lightOn = 1;
  }
  else if(lightOn == 1)
  {
    switch1Red();
    lightOn = 2;
  }
  delay(5000);
}

void buttonWaitCheck() {
  timePassed = 0;
  while(timePassed < 10000)
  {
    buttonState1 = digitalRead(button1);
    buttonState2 = digitalRead(button2);
    if((buttonState1 == LOW) && (lightOn == 2))
    {
       switch2Red();
       lightOn = 1;
       break;
    }
    else if((buttonState2 == LOW) && (lightOn == 1))
    {
      switch1Red();
      lightOn = 2;
      break;
    }
    delay(10);
    timePassed += 10;
  }
  delay(10000 - timePassed);
}

void pedFlash() {
  if(lightOn == 1)
  {
    pedLight = pedLight1;
  }
  else
  {
    pedLight = pedLight2;
  }
  timePassed = 0;
  while(timePassed < 1000)
  {
      digitalWrite(pedLight, LOW);
      delay(100);
      digitalWrite(pedLight, HIGH);
      delay(100);
      timePassed += 200;
  }
  digitalWrite(pedLight, LOW);
}

void switch1Red() {
  pedFlash();
  digitalWrite(green1, LOW);
  digitalWrite(yellow1, HIGH);
  digitalWrite(yellow2, HIGH);
  delay(1000);
  digitalWrite(yellow1, LOW);
  digitalWrite(yellow2, LOW);
  digitalWrite(red2, LOW);
  digitalWrite(red1, HIGH);
  digitalWrite(green2, HIGH);
  digitalWrite(pedLight2, HIGH);
}

void switch2Red() {
  pedFlash();
  digitalWrite(green2, LOW);
  digitalWrite(yellow1, HIGH);
  digitalWrite(yellow2, HIGH);
  delay(1000);
  digitalWrite(yellow1, LOW);
  digitalWrite(yellow2, LOW);
  digitalWrite(red1, LOW);
  digitalWrite(red2, HIGH);
  digitalWrite(green1, HIGH);
  digitalWrite(pedLight1, HIGH);
}

