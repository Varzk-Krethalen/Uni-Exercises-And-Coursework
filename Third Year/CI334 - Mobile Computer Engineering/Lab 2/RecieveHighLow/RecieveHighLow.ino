const int ledPin = 13; // the pin that the LED is attached to
int incomingByte;      // a variable to read incoming serial data into

void setup() 
{  
  Serial1.begin(9600);      // initialize serial communication
  pinMode(ledPin, OUTPUT);    // initialize the LED pin as an output
}

void loop() 
{
  if (Serial1.available() > 0)   // If there's incoming serial data:
  {   
    #ifdef DEBUG
      digitalWrite(ledPin, HIGH); // …turn on the LED
      delay(500);
      digitalWrite(ledPin, LOW); // …turn on the LED
    #endif
    incomingByte = Serial1.read(); // read it…    
    if (incomingByte == 'H')    // if it's an ‘H’…
    { 
      digitalWrite(ledPin, HIGH); // …turn on the LED
    } 
    if (incomingByte == 'L')    // if it's an L’’…
    {
      digitalWrite(ledPin, LOW);  // …turn off the LED
    }           // (otherwise ignore)
  }
}
