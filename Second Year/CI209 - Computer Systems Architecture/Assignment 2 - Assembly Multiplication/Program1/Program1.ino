
void setup() 
{
  Serial.begin(9600);
  Serial.print("Please input an integer : ");
}

void loop() 
{
  if(Serial.available() > 0) {
    int val = readInt();
    Serial.println(val);
    Serial.print("Please input an integer : ");
  }
}

int readInt(void)
{
  char input[8];
  int val;
  
  Serial.readBytes(input, 8);
  
  val = atoi(input);
  return val;
}
