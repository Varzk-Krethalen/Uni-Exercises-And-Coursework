String input;

void setup() 
{
  Serial.begin(9600);
  Serial.print("Input BR in range -32768 to 32767 : ");
}

void loop() 
{
  input = Serial.readString();
  if(input != NULL)
  {
    int val = readInt();
    word BR = (word) val;
    Serial.println(val);
    
    Serial.print("Input AD in range -32768 to 32767 : ");
    
    input = Serial.readString();
    while(input == NULL){
      input = Serial.readString();
    }
    
    val = readInt();
    word AD = (word) val;
    Serial.println(val);
    
    Serial.print("Input n in range 1 to 8 : ");
    
    input = Serial.readString();
    while(input == NULL){
      input = Serial.readString();
    }
    
    int n = readInt();
    Serial.println(n);
    Serial.println();
    
    trace(BR, AD, n);
    
    Serial.println();
    
    Serial.print("Input BR in range -32768 to 32767 : ");
  }
}

void trace(word BR, word AD, byte n)
{
  byte byteB = highByte(BR);
  byte byteR = lowByte(BR);
  byte byteA = highByte(AD);
  byte byteD = lowByte(AD);

  Serial.print(" n=" + n);
  Serial.print(n);
  Serial.println(decimalToBinary(" D=", byteD));
  Serial.println(decimalToBinary(" B=", byteB));
  Serial.println(decimalToBinary(" A=", byteA));
  Serial.println(decimalToBinary(" R=", byteR));
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

  for (int i = 1; i < 8; i++) {
    if (binVal >= temp) {
      bin[i] = '1';
      binVal -= temp;
    }
    temp = temp/2;
  }

  result = message + bin;
  return result;
}

int readInt(void)
{
  char str[16];
  int val;
  
  for (int i = 0; i < input.length(); i++)
  {
    str[i] = input[i];
  }
  val = atoi(str);
  return val;
}
