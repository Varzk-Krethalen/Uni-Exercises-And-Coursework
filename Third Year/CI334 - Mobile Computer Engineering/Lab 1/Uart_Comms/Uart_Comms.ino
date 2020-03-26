void setup() {
  pinMode(led, OUTPUT);
  Serial.begin(9600);
  Serial1.begin(9600, SERIAL_8E2);
}

void loop() 
  if (Serial.available() > 0)         //if Serial data incoming
  {
    byte data = Serial.read();        //read in data
    Serial1.write(data);              //send data over Serial1
  }
  if (Serial1.available() > 0)        //if Serial1 data incoming
  {
    byte data = Serial1.read();       //read in data
    Serial.write(data);               //send data over Serial1
  }
}
