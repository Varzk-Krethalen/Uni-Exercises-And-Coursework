const int dataPin0 = 2; // 2,3,4,5,6,7,8,9 - one per bit
const int parityPin = 10;
const int rts = 11;
const int cts = 12;
const int outgoingDataPin = 22;
const int incomingDataPin = 23;
const int bitDuration = 25; //microseconds. Total time is bitDuration * 2
const int paritySetting = 1; //1 = even, 0 = odd

void setup()
{
  set_io_state(INPUT);                        //set the data & parity pins to INPUT
  pinMode(rts, OUTPUT);
  pinMode(cts, INPUT);
  pinMode(outgoingDataPin, OUTPUT);
  pinMode(incomingDataPin, INPUT);
  set_ready(true);                            //set rts HIGH
  Serial.begin(9600);
}

void loop()
{
  if (Serial.available() > 0)                 //64 char buffer here
  {
    set_ready(false);                         //no longer ready to recieve
    signal_outgoing_data();                   //send signal showing that message is about to be sent
    byte dataTemp = Serial.read();
    Serial.print((char)dataTemp);
    send_data(dataTemp);                 //send parallel data
    delayMicroseconds(bitDuration / 2);
    set_ready(true);                          //ready to recieve again
  }
  if (data_incoming())                        //if signal is recieved on the incoming data pin
  {
    byte dataReceived;
    bool parityBit;
    recieve_frame_data(&dataReceived, &parityBit);  //read the parallel data pins
    
    set_ready(false);                               //no longer ready to recieve
    if (frame_is_valid(dataReceived, parityBit))    //validate parity
    {
      Serial.print((char)dataReceived);             //print recieved message to serial monitor
    }
    delayMicroseconds(bitDuration / 2);
    set_ready(true);                                //ready to recieve again
  }
}

void send_data(byte data)
{
  if (clear_to_send())                        //if cts pin is high
  {
    bool parityBit = get_parity(data);

    for (int i = 0; i < 8; i++)
    {
      digitalWrite(dataPin0 + i, bitRead(data, i));
    }
    digitalWrite(parityPin, parityBit);
    
    delayMicroseconds(bitDuration);

    for (int i = 0; i < 8; i++)
    {
      digitalWrite(dataPin0 + i, LOW);
    }
    digitalWrite(parityPin, LOW);

    //output_info(data, parityBit, "sending: ");
  }
  else
  {
    Serial.println("Not clear to send");
  }
}

int get_parity(byte data)
{
  int bitTotal = 0;
  for (int i = 0; i < 8; i++)
  {
    bool bitToSend = bitRead(data, i);
    bitTotal += bitToSend;
  }
  
  if (paritySetting == 1)
  {
    return (bitTotal % 2 == 0) ? 0 : 1;
  }
  else
  {
    return (bitTotal % 2 == 1) ? 0 : 1;
  }
}

void recieve_frame_data(byte* data, bool* parityBit)
{
  set_io_state(INPUT);
  
  delayMicroseconds((bitDuration));

  for (int i = 0; i < 8; i++)                               //recieve data
  {
    bitWrite(*data, i, digitalRead(dataPin0 + i));
  }
  *parityBit = digitalRead(parityPin);                           //get parity bit
  
  set_io_state(OUTPUT);
}

bool frame_is_valid(byte data, bool parityBit)
{
  int bitTotal = parityBit;
  for (int i = 0; i < 8; i++)
  {
    bitTotal += bitRead(data, i);
  }

  if (parity_valid(bitTotal))
  {
    return true;
  }
  else
  {
    Serial.println("invalid frame");
    return false;
  }
}

bool parity_valid(int bitTotal)
{
  if (paritySetting == 1)
  {
    return (bitTotal % 2 == 0);
  }
  else
  {
    return (bitTotal % 2 == 1);
  }
}

bool clear_to_send()
{
  return digitalRead(cts);
}

void set_ready(bool state)
{
  digitalWrite(rts, state);
}

void signal_outgoing_data()
{
  digitalWrite(outgoingDataPin, HIGH);
  delayMicroseconds(bitDuration / 2);
  digitalWrite(outgoingDataPin, LOW);
}

bool data_incoming()
{
  return digitalRead(incomingDataPin);
}

void output_info(byte data, bool parityBit, String type)
{
    Serial.print(type);
    Serial.print(data);
    Serial.print(" data: ");
    for (int i = 0; i < 8; i++)
    {
      Serial.print(bitRead(data, i));
    }
    Serial.print(" ,parity: ");
    Serial.println(parityBit);
}

void set_io_state(int state)
{
  for (int i = 0; i < 8; i++)
  {
    pinMode(dataPin0 + i, state);
  }
  pinMode(parityPin, state);
}
