/*
 * QR Code reader
 * Use with codes generated from http://qrcode.kaywa.com/
 * (or similar)
 */

import processing.video.*;
import qrcode.*;

Capture video;
Decoder decoder;
String decoded = "";

void setup() {
  size(640, 480);
  // open the camera
  video = new Capture(this);
  video.start();
  // create a decoder object
  decoder = new Decoder(this);
}

void captureEvent(Capture video) {
  video.read();

  String attempt;
  attempt = decoder.decode(video);
  if (attempt != null) {
    // success
    decoded = attempt;
  }
}

void draw() {
  background(0);
  image(video, 0, 0, width, height);
  // display most recent code
  text(decoded, 10, 20);
}