////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CI209 Lab Activity 2 - 2017-2018                          (c) Alan Thomas 2016
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// DO NOT USE ARDUINO 1.8.3
// THIS ONLY PROPERLY COMPILES ON ARDUINO 1.6.7


/// Function prototypes (signatures)
int readInt(void);                                            // (from program 1) reads an integer from the keyboard (serial port)
String decimalToBinary(String message ,byte n);               // (from program 2) converts an 8 bit number to binary
extern void trace(word BR, word AD, byte n) asm ("TRACE");    // (from program 3) dump the register contents for trace purposes
int signedMult(char multiplier, char multiplicand);           // Assembly language implementation of 4 register multiply alg

void setup()
{
  Serial.begin(9600);
}

void loop()
{
    
  Serial.print("Input multiplicand : ");
  while (Serial.available() == 0){}
  int multiplicand = readInt();
  Serial.print(multiplicand);
  Serial.println(decimalToBinary(" ", multiplicand));
  
  Serial.print("Input multiplier   : ");
  while (Serial.available() == 0){}
  int multiplier = readInt();
  Serial.print(multiplier);
  Serial.println(decimalToBinary(" ", multiplier));
  
  Serial.println("");

  int expected = multiplier * multiplicand;
  int actual = signedMult(multiplier, multiplicand);
  
  Serial.print("Product = ");
  Serial.print(actual);
  Serial.print(" Expected = ");
  Serial.print(expected);
  
  if (actual == expected) {
    Serial.println(" Ok :)");
  } else {
    Serial.println(" Incorrect :(");
  }
  
  Serial.println("");
  delay(1000);
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Program 4 - Signed Multiplication using 4 registers with correction factor
// 
// Input parameters are automatically passed in the following registers:
//    r24 - multiplier          alias (R)
//    r22 - multiplicand        alias (D)
//
// IMPORTANT - use the following machine registers for your other varialbles:
//    r25 - Product high byte   alias (B)
//    r23 - Product low  byte   alias (A)
//    r20 - Iteration counter  alias (n)
// 
// The contents of registers r25 and r24 are returned from the function
// You must ensure that you copy the product to these if necessary i.e. B->r25 A->r24
//
// to find out more about C function parameter passing, see https://gcc.gnu.org/wiki/avr-gcc#Register_Layout
//  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
int signedMult(char multiplier, char multiplicand)
{
    asm volatile(
      "mult:  nop                ;                                     \n"
      "       ldi r20, 8         ; set loop counter n                  \n"
      "       ldi r23, 0         ; set A to 0                          \n"
      "       ldi r25, 0         ; set B to 0                          \n"
      "       call dumpRegs      ; calls dumpRegs to print the outcome \n"
      
      "loop1: nop                ; main loop                           \n"
      "       sbrc r24, 0        ; if R ends in 0, skip to shift       \n"
      "       call addRegs       ; calls addRegs to add D to B         \n"
      "       call shiftRegs     ; calls shiftRegs for product & R     \n"
      "       dec r20            ; decrements loop counter n           \n"
      "       brne loop1         ; if n > 0, continue looping          \n"
      
      "end:   nop                ; after loop                          \n"      
      "       sbrc r24, 7        ; skip next if R is positive          \n"    
      "       call FPP           ; if R negative, jump to F.P.P adding \n"
      "       call dumpRegs      ; calls dumpRegs to print the outcome \n"
      "       mov r24, r23       ; moves A so it can be returned       \n"
      "       ret                ; returns back to the C loop          \n"
    );
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Global inline assembly language subroutines
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

asm volatile(
//-----------------------------------------------------------------------
// Wrapper for the C trace function, which saves and restores
// registers and flags, so we can basically do anything we want in it ;)
//-----------------------------------------------------------------------
"dumpRegs:   push r20           ; save the loop count n               \n"
"            push r22           ; save the multiplicand, Yreg         \n"
"            push r23           ; save the loop count, Nreg           \n"
"            push r24           ; save the product lo byte Pl         \n"
"            push r25           ; save the product hi byte Ph         \n"  
"            in r16, __SREG__   ; save the status register SREG       \n"
"            call TRACE         ; call the C function trace()         \n"
"            out __SREG__, r16  ; restore the status register, SREG   \n"
"            pop r25            ; restore working registers....       \n"
"            pop r24            ; note that these must be popped off  \n"
"            pop r23            ; the stack in reverse order to that  \n"
"            pop r22            ; in which they were pushed           \n"
"            pop r20            ; in which they were pushed           \n"
"            ret                ; return back to multiplication       \n"     

// put your add and shift subroutines here

"addRegs:    add r25, r22       ; add the two registers               \n"
"            call dumpRegs      ; calls dumpRegs to print the outcome \n"
"            ret                ; return back to multiplication       \n"

"shiftRegs:  asr r25            ; shift the designated register       \n"
"            ror r23            ; rotate A with carry from asr r25    \n"
"            asr r24            ; shift multiplier                    \n"
"            call dumpRegs      ; calls dumpRegs to print the outcome \n"
"            ret                ; return back to multiplication       \n"

"FPP:       neg r22             ; two's complements D                 \n"
"           add r25, r22        ; adds the two's FPP to B             \n"
"           ret                 ; returns to end                      \n"

);


void trace(word BR, word AD, byte n)
{
  byte byteB = highByte(BR);
  byte byteR = lowByte(BR);
  byte byteA = highByte(AD);
  byte byteD = lowByte(AD);
      
  Serial.print(" n=");
  Serial.print(n);
  Serial.print(decimalToBinary(" D=", byteD));
  Serial.print(decimalToBinary(" B=", byteB));
  Serial.print(decimalToBinary(" A=", byteA));
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




