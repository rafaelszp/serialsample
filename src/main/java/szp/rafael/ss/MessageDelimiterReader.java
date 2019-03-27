package szp.rafael.ss;

import com.fazecast.jSerialComm.SerialPort;

public class MessageDelimiterReader {

  static public void main(String[] args)
  {
    SerialPort comPort = SerialPort.getCommPorts()[0];
    comPort.openPort();
    MessageListener listener = new MessageListener();
    comPort.addDataListener(listener);
    try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
    comPort.removeDataListener();
    comPort.closePort();
  }
}
