//Alexander Zaurov
//COMP 407 - Embedded Systems
//Lab 1: Thermostat

#include<Servo.h>
Servo servo;
float a, temp;
float convert;

void setup() {
 servo.attach(13);   //Servo motor which will be used to indicate the temperature
 Serial.begin(9600); //sending output to serial monitor
 pinMode(13,OUTPUT);  //Servo motor output to pin 13
 pinMode(A0,INPUT);   //Thermistor connected to pin A0 input
 pinMode(3,OUTPUT);   // Blue LED light connected to pin 3
 pinMode(4,OUTPUT);   // White LED light connected to pin 4
 pinMode(5,OUTPUT);   // Yellow LED light connected to pin 5
 pinMode(7,OUTPUT);   // Fan 
}

void loop() {
  
  //digitalWrite(13,HIGH); //turning on the servo
  //delay(1000);   //delaying 1 second
  //digitalWrite(13,LOW); //turning off the servo
  //delay(500);  //delay 1/2 second
  
  a=analogRead(A0);
  //convert = a / 6.79167;  //my own conversion formula using the room temp reading divided by room temp 71 F
  temp = map(a, 471, 590, 69.5, 90);  //using the map function to map the lowest and highest thermistor reading, and map it to the lowest and highest fahrenheit reading
  Serial.println("Temperature: ");
  Serial.println(temp);  // printing the thermistat reading to the screen
 // servo.write("Temperature: ");
  servo.write(temp);  //sending the thermostat reading to servo
  
  //setting up conditions below when the LED lights go on
  
  if(temp < 76)  //if the temperature falls below 76 turn off LED 3
  {
    digitalWrite(3,LOW);
  }
  if(temp >= 76 && temp < 80) //if the temperature is between 76 and below 80 F, turn on LED 3, turn off LED 4,5,6
  {
    digitalWrite(3,HIGH);
    digitalWrite(4,LOW);
    digitalWrite(5,LOW);
    digitalWrite(6,LOW);   
  }
  else if(temp >= 80 && temp < 85) //if the temperature is betweeen 80 and below 85, turn on LED 3, 4, turn off LED 5, 6
  {
    digitalWrite(3,HIGH);
    digitalWrite(4,HIGH);
    digitalWrite(5,LOW);
    digitalWrite(6,LOW);
  }
  else if(temp >= 85 && temp < 90) //if the temperature is between 85 and below 90, turn on LED 3,4,5, turn off LED 6
  {
    digitalWrite(3,HIGH);
    digitalWrite(4,HIGH);
    digitalWrite(5,HIGH);
    digitalWrite(6,LOW);
  }
  else if(temp  >=90)  //if the temperature is equal to and above 90 turn on ALL LEDs
  {
    digitalWrite(3,HIGH);
    digitalWrite(4,HIGH);
    digitalWrite(5,HIGH);
    digitalWrite(6,HIGH);
  }

 //below are the conditions to regulate the motor fan and the relay
 
  if(temp >= 85)  //if the temperature is equal to and above 85 F turn on the motor fan
  {
    digitalWrite(7,HIGH);
  }
  else if(temp <= 75)  //if the temperature is equal to 75 and below turn off the motor fan
  {
    digitalWrite(7,LOW);
  }
}
