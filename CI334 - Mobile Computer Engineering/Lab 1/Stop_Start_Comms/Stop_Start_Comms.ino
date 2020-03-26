const int rx = 6;                           //pin assignments
const int tx = 7;                           //rx and tx are pins 7 & 8
const int rts = 8;                          //using hardware handshake
const int cts = 9;                          //RTS is pin 8, CTS is pin 9
const int stopBits = 2;                     //1 or 2
const int bitDuration = 150;                //microseconds per bit
const int paritySetting = 1;                //1 = even, 0 = odd
const int totalBits = 10 + stopBits;

static bool frameBuffer[totalBits] = {};    //holds the bits of the current frame

void setup()
{
  set_ready(true);
  pinMode(rx, INPUT);
  pinMode(tx, OUTPUT);
  Serial.begin(9600);
}

void loop()
{
  if (Serial.available() > 0)               //64 char buffer here
  {
    set_ready(false);                       //No longer ready to receive data
    frame_data(Serial.read());              //create the frame in the frameBuffer
    send_frame();                           //transmit the frame
    set_ready(true);                        //ready to receive again
  }
  if (data_incoming())                      //if Rx is high, data is incoming
  {
    byte dataRecieved = 0;
    if (recieve_frame_data(&dataRecieved))  //receive frame, validate it, return frame data
    {
      set_ready(false);                     //No longer ready to receive data
      Serial.print((char)dataRecieved);     //print the recieved character to Serial
      set_ready(true);                      //ready to receive again
    }
  }
}

void frame_data(byte dataToSend)            //frame byte into an array of bits
{
  clear_buffer();                           //reset the bit array
  frameBuffer[0] = 1;                       //Start Bit will always be 1
  
  int bitTotal = 0;                         //sum total of the data bits
  for (int i = 0; i < 8; i++)               //adding the data bits to the frame array
  {
    bool bitToSend = bitRead(dataToSend, i);//get bit from byte
    frameBuffer[i + 1] = bitToSend;         //put bit into frame array
    bitTotal += bitToSend;                  //update bit total
  }

  int parityBit = get_parity(bitTotal);     //calls to get the parity based on the bit total
  frameBuffer[9] = parityBit;               //adding parity based on total data bit value
  frameBuffer[10] = 1;                      //first stop bit will always be 1
  if (stopBits == 2) frameBuffer[11] = 1;   //adding second stop bit if required.
}

int get_parity(int bitTotal)                //return parity bit for a given bit total
{
  if (paritySetting == 1)                   //if parity is even
  {
    return (bitTotal % 2 == 0) ? 0 : 1;     //parityBit = 0 if totalBits are even, else = 1
  }
  else                                      //if parity is odd
  {
    return (bitTotal % 2 == 1) ? 0 : 1;     //parityBit = 0 if totalBits are odd, else = 1
  }
}

void send_frame()                           //transmit frame over Tx pin
{
  if (clear_to_send())                      //if CTS is high, sending is okay
  {
    for(int i = 0; i < totalBits; i++)      //send each bit in the frame
    {
      send_bit(frameBuffer[i]);             //send current bit
    }
    delayMicroseconds(bitDuration);         //waiting time after every message, to ensure separation
  }
  else
  {
    Serial.println("Not clear to send");
  }
}

void send_bit(bool state)                   //transmit a bit over Tx
{
  digitalWrite(tx, state);                  //set bit value
  delayMicroseconds(bitDuration);           //wait until bit duration has passed
  digitalWrite(tx, LOW);                    //reset bit to 0
}

bool clear_to_send()                        //return whether CTS is high
{
  return digitalRead(cts);
}

void clear_buffer()                         //reset frameBuffer to be all 0
{
  memset(frameBuffer, 0, sizeof(frameBuffer));
}

void set_ready(bool state)                  //set the current state of RTS
{
  digitalWrite(rts, state);
}

bool recieve_frame_data(byte* data)
{
  delayMicroseconds((bitDuration / 2));                       //wait until halfway through first bit
  if (data_incoming() == HIGH)                                //checking valid start bit
  {
    for (int i = 0; i < 8; i++)                               //recieve data
    {
      bitWrite(*data, i, receive_bit());                      //copy incoming bits into byte
    }

    bool parityBit = receive_bit();                           //get parity bit
    bool firstStopBit = receive_bit();                        //main stop bit
    bool secondStopBit = (stopBits == 2) ? receive_bit(): 1;  //defaults second stop bit to 1 if not required

    delayMicroseconds((bitDuration));                         //waiting time after every message, to ensure separation
    
    if (validate_frame(*data, parityBit,
                        firstStopBit, secondStopBit))         //check that parity and stop bits are correct
    {
      return true;                                            //if so, return
    }
    else                                                      //otherwise, print error message on invalid frame
    {
      Serial.println("invalid frame");
      Serial.print("data: ");
      for (int i = 0; i < 8; i++)
      {
        Serial.print(bitRead(*data, i));
      }
      Serial.print(" ,parity: ");
      Serial.print(parityBit);
      Serial.print(" ,firstStopBit: ");      
      Serial.print(firstStopBit);
      Serial.print(" ,secondStopBit: ");
      Serial.println(secondStopBit);
    }
  }
  else                                                        //if start bit is invalid, print error message
  {
    Serial.println("invalid start length");
  }
  return false;
}

bool receive_bit()                                            //return incoming bit value
{
  delayMicroseconds(bitDuration);
  return data_incoming();
}

bool data_incoming()                                          //return state of Rx
{
  return digitalRead(rx);
}

bool validate_frame(byte data, bool parityBit, bool firstStopBit, bool secondStopBit)
{
  int bitTotal = parityBit;                                   //add parity bit to bit total
  for (int i = 0; i < 8; i++)                                 //add sum total of bits
  {
    bitTotal += bitRead(data, i);
  }

  if (parity_valid(bitTotal) && firstStopBit == true)         //check parity is correct and first stop bit is valid
  {
    return (stopBits == 2) ? secondStopBit: true;             //if 2 stopbits, check second stopbit is valid
  }
  
  return false;
}

bool parity_valid(int bitTotal)                               //validate the parity
{
  if (paritySetting == 1)                                     //if even parity
  {
    return (bitTotal % 2 == 0);                               //return if bitTotal is even
  }
  else                                                        //else if odd parity
  {
    return (bitTotal % 2 == 1);                               //return if bitTotal is odd
  }
}
