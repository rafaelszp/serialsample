package szp.rafael.ss;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import java.awt.*;

public class PacketListener implements SerialPortPacketListener {

  @Override
  public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

  @Override
  public int getPacketSize() { return 12; }

  @Override
  public void serialEvent(SerialPortEvent event)
  {
    byte[] newData = event.getReceivedData();
    System.out.println("Received data of size: " + newData.length);
    String hex = "";
    for (int i = 0; i < newData.length; ++i) {
      String rcvd = i>-1 ?  String.format("%02x", newData[i]): "";
      hex += rcvd;
      System.out.printf(rcvd+" ");
    }
    hex = hex.substring(2,hex.length()-6);
    String ascii = hexToAscii(hex);
    long decimal = Long.parseLong(ascii.trim(), 16);
    System.out.printf("HEX: [%s] \t ASCII: [%s] \t DECIMAL: [%s]\n",hex, ascii,decimal);
    SystemClipboard.copy(String.valueOf(decimal));
    try {
      SystemClipboard.paste();
    } catch (AWTException e) {
      e.printStackTrace();
    }

//    hex = "";3812275778
    System.out.println(" \n");
  }

  private static String hexToAscii(String hexStr) {
    StringBuilder output = new StringBuilder("");

    for (int i = 0; i < hexStr.length(); i += 2) {
      String str = hexStr.substring(i, i + 2);
      output.append((char) Integer.parseInt(str, 16));
    }

    return output.toString();
  }

}
