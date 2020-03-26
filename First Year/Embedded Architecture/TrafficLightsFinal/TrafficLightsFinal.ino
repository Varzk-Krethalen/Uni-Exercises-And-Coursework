// Alex Shafee, CI116.


// Sets variables for each pin.
// Traffic LED's on pins 8-13,
// Pedestrian LED on pin 7, and
// input button on pin 5.
const int green1 = 13;
const int yellow1 = 12;
const int red1 = 11;
const int green2 = 10;
const int yellow2 = 9;
const int red2 = 8;
const int pedLight = 7;
const int button = 5;

// Initialises the changing variables:
int buttonState = 0;
int timePassed = 0;
int lightOn = 1;
String stateString = "";

void setup() {
  // Set the LED pins as output:
  pinMode(green1, OUTPUT);
  pinMode(yellow1, OUTPUT);
  pinMode(red1, OUTPUT);
  pinMode(green2, OUTPUT);
  pinMode(yellow2, OUTPUT);
  pinMode(red2, OUTPUT);
  pinMode(pedLight, OUTPUT);
  // Set button as input:
  pinMode(button, INPUT);
  // Initialises the two traffic lights:
  digitalWrite(green1, HIGH);
  digitalWrite(red2, HIGH);
  // Sets the input button to HIGH, so a
  // button press inputs LOW, and button
  // does not need to be connected to 5V.
  digitalWrite(button, HIGH);
  // Begin serial communication.
  Serial.begin(9600);
}

void loop() {
  // For 10 seconds, checks if the button is being
  // pressed.
  buttonWaitCheck();
  // Flips the states of the two Traffic Lights,
  // using a variable that holds which traffic light
  // is currently on green.
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
  // Waits 5 seconds for traffic flow.
  delay(5000);
}

void buttonWaitCheck() {
  // Set a variable for time passed to 0, then while
  // 10 seconds has not passed, checks for the button
  // being pressed.
  timePassed = 0;
  while(timePassed < 10000)
  {
    // Gets button state and checks if pressed.
    buttonState = digitalRead(button);
    if(buttonState == LOW)
    {
       // Sets both traffic lights to Red, and turns on
       // the Pedestrian Light. Then waits 5 seconds, then
       // flashes the Pedestrian Light before setting the
       // lights to the inital state, waiting, and beginning
       // the Traffic light loop again.
       setBothRed();
       digitalWrite(pedLight, HIGH);
       printState();
       delay(5000);
       pedFlash();
       resetLights();
       delay(1000);
       // Resets variable defining which light is on.
       lightOn = 1;
       break;
    }
    // Adds to the time count, and waits an
    // equivalent amount of time.
    timePassed += 10;
    delay(10);
  }
  // Waits until the rest of the 10 seconds has passed.
  delay(10000 - timePassed);
}

void pedFlash() {
  // The function to flash the Pedestrian Light.
  // Sets a variable for time passed, then runs
  // a loop while the time passed is less than
  // 1 second. Flashes 5 times.
  timePassed = 0;
  while(timePassed < 1000)
  {
      // flashes the light once, and adds to the time
      // variable.
      digitalWrite(pedLight, LOW);
      delay(100);
      digitalWrite(pedLight, HIGH);
      delay(100);
      timePassed += 200;
  }
  // switches the Pedestrian Light off.
  digitalWrite(pedLight, LOW);
  printState();
}

void switch1Red() {
  // Switches Light 1 to be red, and Light 2 to be green,
  // in accordance to the phases - switching them. It has
  // a one second delay for the middle of the phases.
  digitalWrite(green1, LOW);
  digitalWrite(yellow1, HIGH);
  digitalWrite(yellow2, HIGH);
  printState();
  delay(1000);
  digitalWrite(yellow1, LOW);
  digitalWrite(yellow2, LOW);
  digitalWrite(red2, LOW);
  digitalWrite(red1, HIGH);
  digitalWrite(green2, HIGH);
  printState();
}

void switch2Red() {
  // Switches Light 2 to be red, and Light 1 to be green,
  // in accordance to the phases - switching them. It has
  // a one second delay for the middle of the phases.
  digitalWrite(green2, LOW);
  digitalWrite(yellow1, HIGH);
  digitalWrite(yellow2, HIGH);
  printState();
  delay(1000);
  digitalWrite(yellow1, LOW);
  digitalWrite(yellow2, LOW);
  digitalWrite(red1, LOW);
  digitalWrite(red2, HIGH);
  digitalWrite(green1, HIGH);
  printState();
}

void setBothRed() {
  // Sets both lights to be red for traffic. If Light 1 is on
  // it sequences it to red, and the same if it is Light 2.
  if(lightOn == 1)
  {
    digitalWrite(green1, LOW);
    digitalWrite(yellow1, HIGH);
    printState();
    delay(1000);
    digitalWrite(yellow1, LOW);
    digitalWrite(red1, HIGH);
    printState();
  }
  else
  {
    digitalWrite(green2, LOW);
    digitalWrite(yellow2, HIGH);
    printState();
    delay(1000);
    digitalWrite(yellow2, LOW);
    digitalWrite(red2, HIGH);
    printState();
  }
}

void resetLights() {
  // resets Light 1 to be green, leaving Light 2 red.
  digitalWrite(yellow1, HIGH);
  printState();
  delay(1000);
  digitalWrite(yellow1, LOW);
  digitalWrite(red1, LOW);
  digitalWrite(green1, HIGH);
  printState();
}

void printState() {
  // Adds on to a string the states of each light, using digitalRead
  // to determine state - this is concatenated to the initial string,
  // to create a final status that is printed out to the serial port.
  stateString = "L1 = ";
  if(digitalRead(green1))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  if(digitalRead(yellow1))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  if(digitalRead(red1))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  stateString += " L2 = ";
  if(digitalRead(green2))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  if(digitalRead(yellow2))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  if(digitalRead(red2))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  stateString += " PL = ";
  if(digitalRead(pedLight))
  {
    stateString += "ON ";
  }
  else
  {
    stateString += "OFF ";
  }
  Serial.println(stateString);
}

