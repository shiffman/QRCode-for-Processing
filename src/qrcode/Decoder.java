/*
 * Processing QRCode Library
 * Gottfried Haider, 12/2/2017
 * Daniel Shiffman, 6/26/2007
 */

package qrcode;

import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.RGBLuminanceSource;
import processing.core.PApplet;
import processing.core.PImage;


public class Decoder {

  PApplet parent;
  QRCodeReader reader;


  public Decoder(PApplet parent) {
    this.parent = parent;
    reader = new QRCodeReader();
  }

  /**
   *  Decodes a QR Code in a PImage
   *  @param img image to decode
   *  @return String or null if none found
   */
  public String decode(PImage img) {
    // convert to luminance source
    img.loadPixels();
    RGBLuminanceSource source = new RGBLuminanceSource(img.width, img.height, img.pixels);
    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

    // run decoder
    try {
      Result result = reader.decode(bitmap);
      return result.getText();
    } catch (NotFoundException e) {
    } catch (ChecksumException e) {
    } catch (FormatException e) {
    }
    return null;
  }
}
