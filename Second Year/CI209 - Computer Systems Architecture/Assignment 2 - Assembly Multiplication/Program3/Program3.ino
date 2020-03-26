
void setup() 
{
  Serial.begin(9600);
  Serial.print("Input BR in range -32768 to 32767 : ");
}

void loop() 
{
  if (Serial.available() > 0) {
    int val = readInt();
    word BR = (word) val;
    
    Serial.println(val);
    
    Serial.print("Input AD in range -32768 to 32767 : ");
    
    while(!(Serial.available() > 0)){}
    
    val = readInt();
    word AD = (word) val;
    Serial.println(val);

    Serial.print("Input n in range 1 to 8 : ");

    while(!(Serial.available() > 0)){}

    int n = readInt();
    Serial.println(n);

    trace(BR, AD, n);

    Serial.print("Input BR in range -32768 to 32767 : ");
 }
}

void trace(word BR, word AD, byte n)
{
  byte byteB = highByte(BR);
  byte byteR = lowByte(BR);
  byte byteA = highByte(AD);
  byte byteD = lowByte(AD);
  
  Serial.println("");
    
  Serial.print(" n=");
  Serial.print(n);
  Serial.print(decimalToBinary(" D=", byteD));
  Serial.print(decimalToBinary(" B=", byteB));
  Serial.print(decimalToBinary(" A=", byteA));
  Serial.println(decimalToBinary(" R=", byteR));
  
  Serial.println("");
}

String decimalToBinary(String message, byte n)
{  
  char bin[8] = {'0','0','0','0','0','0','0','0'};
  int binVal = (int) n;
  int temp = 64;
  String result;

  if (binVal > 127) {
    binVal -= 256;
  }
  
  if (binVal < 0) { 
    bin[0] = '1';
    binVal += 128;
  }

  for (int i = 1; i <= 7; i++) {
    if (binVal >= temp) {
      bin[i] = '1';
      binVal -= temp;
    }
    temp = temp/2;
  }

  String binStr = (String) bin;
  if (binStr[8] != NULL) {
    binStr[8] = NULL;
  }
  result = message + binStr;
  return result;
}

int readInt(void)
{
  char input[8];
  int val;
  
  Serial.readBytes(input, 8);
  
  val = atoi(input);
  return val;
}
