
void setup() 
{
  Serial.begin(9600);
  Serial.print("Input a number in range -128 to 127 : ");
}

void loop() 
{
  if(Serial.available() > 0){
    int val = readInt();
    byte n = (byte) val;  
    
    Serial.println(decimalToBinary("bin=", n) + " " + val);
    Serial.print("Input a number in range -128 to 127 : ");
  }
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

  result = message + bin;
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
