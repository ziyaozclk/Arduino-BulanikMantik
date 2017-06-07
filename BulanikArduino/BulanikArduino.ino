int lm35Pin = A0;

void setup() {
  Serial.begin(9600);
  analogReference(INTERNAL);
}

void loop() {
  int sicaklikVolt = analogRead(lm35Pin);
  float sicaklikC = sicaklikC = sicaklikVolt / 9.31;
  Serial.println(sicaklikC);
  delay(100);
}
