#include <SoftwareSerial.h>
  #include <WiFlyHQ.h>
  #define WIFI_RX_PIN 10    // Wi-Fi RX pin.
  #define WIFI_TX_PIN 9     // Wi-Fi TX pin.
  #define PORT 50001        // Port to connect to.
  #define IPV4_MAX_LEN 16   // Maximum length of an IPv4 address in characters plus the terminating character.

  SoftwareSerial wifiSerial(WIFI_RX_PIN, WIFI_TX_PIN);
  WiFly wifly;
  

  void setup()
  {
    
    Serial.begin(9600);
    connectToRouter();
    connectToServer();
  }
  
  void loop()
  {

  }


void connectToRouter(void) {

  /* Here you want to connect to the router of your choice         *
   * You need to store the ssid and password and use the functions *
   * provided within the WiFlyHQ library to achieve this           */
}




// connect to server
void connectToServer() {


}
