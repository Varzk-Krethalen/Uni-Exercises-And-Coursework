////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CI209 Lab Activity 2 - 2017-2018                          (c) Alan Thomas 2016
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/// Function prototypes (signatures)
int readInt(void);                                            // (from program 1) reads an integer from the keyboard (serial port)
String decimalToBinary(String message ,byte n);               // (from program 2) converts an 8 bit number to binary
extern void trace(word BR, word AD, byte n) asm ("TRACE");    // (from program 3) dump the register contents for trace purposes
int signedMult(char multiplier, char multiplicand);           // Assembly language implementation of 4 register multiply alg

void setup()
{
}

void loop()
{
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
//    r20 - Interation counter  alias (n)
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
      "loop:  nop               ;                                     \n"
      "                         ;                                     \n"
      "       subi r20, $1      ;                                     \n"
      "       cp r20, $0        ;                                     \n"
      "       brne loop         ;                                     \n"
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

"addRegs:    add r, r           ; add the two registers               \n"
"            call dumpRegs      ; calls dumpRegs to print the outcome \n"
"            ret                ; return back to multiplication       \n"

"shiftReg:   asr r              ; shift the designated register       \n"
"            call dumpRegs      ; calls dumpRegs to print the outcome \n"
"            ret                ; return back to multiplication       \n"

);






