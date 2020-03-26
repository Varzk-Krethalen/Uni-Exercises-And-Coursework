const int ledPin = 13; // the pin that the LED is attached to

void setup() {
  Serial.begin(9600);
  Serial1.begin(9600);
  pinMode(ledPin, OUTPUT);    // initialize the LED pin as an output
}

void loop() {
  if (Serial.available())
  {
    digitalWrite(ledPin, HIGH);
    send_frame(Serial.read());
    delay(200);
    digitalWrite(ledPin, LOW);
  }
}

void send_frame(byte b)
{
  (void)b;
  Serial1.write(0x7E);
  Serial1.write(0x00);
  Serial1.write(0x18);
  Serial1.write(0x10);
  Serial1.write(0x01);

  Serial1.write(0x00); //64bit address
  Serial1.write(0x00);
  Serial1.write(0x00);
  Serial1.write(0x00);
  Serial1.write(0x00);
  Serial1.write(0x00);
  Serial1.write(0xff);
  Serial1.write(0xff);

  Serial1.write(0xff); //16bit address - fffe if unknown
  Serial1.write(0xfe);
  
  Serial1.write(0x00);  //data
  Serial1.write(0x00);
  Serial1.write(0x42);
  Serial1.write(0x72);
  Serial1.write(0x6f);
  Serial1.write(0x61);
  Serial1.write(0x64);
  Serial1.write(0x63);
  Serial1.write(0x61);
  Serial1.write(0x73);
  Serial1.write(0x74);
  Serial1.write(0x21);
  
  Serial1.write(0x3f); //checksum
}
