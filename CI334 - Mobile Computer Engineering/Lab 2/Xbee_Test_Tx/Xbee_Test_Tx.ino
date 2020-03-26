/*

XBee Series 1 Tranmitter
16-Bit 

Source: http://code.google.com/p/xbee-arduino/
Xbee ver 0.3 wont compile unless you change NewSoftSerial.h to SoftwareSerial.h. 
see: http://arduino.cc/forum/index.php?topic=84789.0
How to configure xBees http://code.google.com/p/xbee-api/wiki/XBeeConfiguration
Good explanation of sketch http://code.google.com/p/xbee-arduino/wiki/DevelopersGuide
xbee.h Documentation http://xbee-arduino.googlecode.com/svn/trunk/docs/api/index.html



Configure this Xbee with X-CTU (values are hex)
 PAN 3131  Personal Area Network ID - all xBees need to be on same PAN
 DL 51     Lower Byte Address (not used in 16-bit addressing, but I like to set something unuque anyway)
 MY 100    ID of xBee, all xBees need to be different
 CE 0      Coordinator - sets this XBee as the END NODE
 AP 2      Use API Mode
 Firmware ver 10E8

To send data to a particular xbee, this transmitter uses the Receiver's MY address, not the DL address
See http://www.digi.com/support/kbase/kbaseresultdetl?id=2187

 
 */
 
#include <XBee.h>  //http://code.google.com/p/xbee-arduino/     Modified per http://arduino.cc/forum/index.php/topic,111354.0.html
#include <SoftwareSerial.h>

#define PRINT_DEBUG       // comment out if you don't want anything to go to serial monitor
#define MY_ADDR_RX 0x250  // The MY address of the Rx XBee 
#define BAUD_RATE 9600    // Baud for both Xbee and serial monitor



unsigned long start = millis();

#define NUM_DATA_PTS   3  // Number of integers (data points) to upload. Can't exceed 100 bytes or 50 integers unless you change MAX_FRAME_DATA_SIZE in XBee.h

// allocate array to hold bytes to send to other xbee.  Size is 2x the numer if integers being sent  
uint8_t payload[NUM_DATA_PTS * 2];

XBee xbee = XBee();
// 16-bit addressing: Enter address of remote XBee, typically the coordinator
Tx16Request tx = Tx16Request(MY_ADDR_RX, payload, sizeof(payload));
TxStatusResponse txStatus = TxStatusResponse();

#define LED_DATA     10
#define LED_STATUS   11
#define LED_ERROR    12


//============================================================================
//  Flash LEDs to indicate varius states
//============================================================================
void flashLed(int pin, int times, int wait) 
{
    for (int i = 0; i < times; i++) 
    {
      digitalWrite(pin, HIGH);
      delay(wait);
      digitalWrite(pin, LOW);
      
      if (i + 1 < times) 
      {delay(wait);}
    }
} // flashLed()



//============================================================================
//  Initialize 
//============================================================================
void setup() 
{
  pinMode(LED_STATUS, OUTPUT);
  pinMode(LED_ERROR,  OUTPUT);
  pinMode(LED_DATA,   OUTPUT);
  
  #ifdef PRINT_DEBUG
    Serial.begin(BAUD_RATE);
    Serial.println("Xbee Tx/End Node - setup()");
  #endif
  
  xbee.begin(BAUD_RATE);
  
  flashLed(LED_DATA,   5, 50);
  flashLed(LED_STATUS, 5, 50);
  flashLed(LED_ERROR,  5, 50);
  flashLed(LED_DATA,   5, 50);
  flashLed(LED_STATUS, 5, 50);
  flashLed(LED_ERROR,  5, 50);
  
} // setup()

//============================================================================
//  
//============================================================================
void loop() 
{
    int16_t xbeeData[NUM_DATA_PTS];  // Array to hold integers that will be sent to other xbee
  
   // start transmitting after a startup delay.  Note: this will rollover to 0 eventually so not best way to handle
    if (millis() - start > 15000) 
    {

      // Test data to send to other xbee
      xbeeData[0] = 1234;
      xbeeData[1] = 5678;
      xbeeData[2] = -500;
      
  // SRG put the xbee code in it's own function   bool xbSend =  sendToXBee(&xbeeData); 
      
      // break down integers into two bytes and place in payload
      for(int i=0; i<NUM_DATA_PTS; i++)
      {
        payload[i*2]     = xbeeData[i] >> 8 & 0xff; // High byte - shift bits 8 places, 0xff masks off the upper 8 bits
        payload[(i*2)+1] = xbeeData[i] & 0xff;      // low byte, just mask off the upper 8 bits

        #ifdef PRINT_DEBUG
          Serial.print("Data = ");
          Serial.print(xbeeData[i]);
          Serial.print(", payload high byte = ");
          Serial.print(payload[i*2]);
          Serial.print(", payload low byte = ");
          Serial.println(payload[(i*2)+1]);
        #endif
      }
      
      xbee.send(tx);

      flashLed(LED_DATA, 1, 300);
    }
  
    // after sending a tx request, we expect a status response
    // wait up to 5 seconds for the status response
    if (xbee.readPacket(5000)) 
    {
        // got a response!
        #ifdef PRINT_DEBUG
          Serial.println("\nGot a response from receiver");
        #endif
        
        // should be a znet tx status            	
        if (xbee.getResponse().getApiId() == TX_STATUS_RESPONSE) 
        {
          xbee.getResponse().getZBTxStatusResponse(txStatus);
    		
    	   // get the delivery status, 0 = OK, 1 = Error, 2 = Invalid Command, 3 = Invalid Parameter 
          if (txStatus.getStatus() == SUCCESS) 
          {
            // success.  time to celebrate
            flashLed(LED_STATUS, 5, 150);
            #ifdef PRINT_DEBUG
              Serial.println("Tx Succeeded");
            #endif
            
          } 
          else 
          {
            // the remote XBee did not receive our packet. is it powered on?
            flashLed(LED_ERROR, 1, 250);
            #ifdef PRINT_DEBUG
              Serial.print("Tx Failed, xbee status = ");
              Serial.println(txStatus.getStatus());
            #endif
           }
        }      
    } 
    else if (xbee.getResponse().isError()) 
    {
      flashLed(LED_ERROR, 2, 250);
      #ifdef PRINT_DEBUG
        Serial.print("Error reading packet.  Error code: ");  
        Serial.println(xbee.getResponse().getErrorCode());
      #endif
      
    } 
    else 
    {
      // local XBee did not provide a timely TX Status Response.  Radio is not configured properly or connected
      flashLed(LED_ERROR, 3, 250);
      #ifdef PRINT_DEBUG
        Serial.println("XBee did not provide a timely Tx Status Response");  
      #endif
      
    } // Finished waiting for XBee packet
    
    delay(1000);
    
}  // void()
